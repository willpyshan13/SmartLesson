package cn.smart.smartlesson;

import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import cn.smart.baselibrary.utils.SharePreferenceUtils;
import cn.smart.baselibrary.view.BaseActivity;
import cn.smart.smartlesson.bean.VideoBeans;

import static android.view.View.GONE;
import static android.view.View.INVISIBLE;
import static android.view.View.OnClickListener;
import static android.view.View.OnTouchListener;

/**
 * @Desc: TODO
 * @Author: pengysh
 * @CreatedTime: 2017/6/22 20:01
 */
public class VideoPlayActivity extends BaseActivity implements SurfaceHolder.Callback, MediaPlayer.OnCompletionListener, SeekBar.OnSeekBarChangeListener {
    private static final String TAG = "VideoPlayActivity";
    public static final String VIDEO_PLAY_LIST_EXTRA = "video_play_list";
    public static final String VIDEO_PLAY_POSITION_EXTRA = "video_play_position";
    public static final String VIDEO_PLAY_POSITION_TYPE = "video_type";
    public static final String VIDEO_PLAY_FROM = "video_play_from";
    public static final String VIDEO_PLAY_FROM_LAUNCHER = "video_play_from_launcher";
    public static final String VIDEO_PLAY_FROM_VOICE = "video_play_from_voice";
    public static final String VIDEO_PLAY_TYPE = "video_play_type";
    public static final String VIDEO_PLAY_TYPE_VIDEO = "movie";
    public static final String VIDEO_PLAY_TYPE_MUSIC = "music";
    public static final String VIDEO_PLAY_TYPE_STORY = "story";
    private String mPlayType = "";
    MediaPlayer mediaPlayer;
    SurfaceView mSurfaceView;
    SurfaceHolder mSurfaceHolder;
    int currentPosition = 0;
    private GalleryAdapter mAdapter;
    private SeekBar mSeekbar;
    private RecyclerView mVideoList;
    private TextView mTime;
    private int HANDLER_MESSAGE_GET_PROGRESS = 1;
    private int HANDLER_MESSAGE_DISMISS_CONTROL = 2;
    private long GET_PROGRESS_TIME = 1 * 1000;
    private long DISMISS_CONTROL_TIME = 5 * 1000;
    private int mVideoSize = 0;
    private BitmapFactory.Options options;
    private boolean mIsMusic = false;
    private boolean mIsFromVoice = false;
    private LinearLayout mLlBottomControl;
    private List<VideoBeans> mVideoBeans = new ArrayList<>();
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == HANDLER_MESSAGE_GET_PROGRESS) {
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    mSeekbar.setProgress(mediaPlayer.getCurrentPosition());
                    if (mIsFromVoice) {
                        SharePreferenceUtils.setCurrentProgress(VideoPlayActivity.this, mediaPlayer.getCurrentPosition(), mPlayType);
                    }
                    mTime.setText(getFormatTime(mediaPlayer.getCurrentPosition()) + "/" + getFormatTime(mVideoSize));
                    mHandler.sendEmptyMessageDelayed(HANDLER_MESSAGE_GET_PROGRESS, GET_PROGRESS_TIME);
                }
            } else if (msg.what == HANDLER_MESSAGE_DISMISS_CONTROL) {
                setVisbility(INVISIBLE);
            }
        }
    };

    public void setVisbility(int visiblity) {
        mVideoList.setVisibility(visiblity);
        mLlBottomControl.setVisibility(visiblity);
        findView(R.id.back).setVisibility(visiblity);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                handleTouchEvent();
                break;
        }

        return super.onTouchEvent(event);
    }

    private void handleTouchEvent() {
        if (mLlBottomControl.getVisibility() == View.VISIBLE) {
            setVisbility(View.INVISIBLE);
            mHandler.removeMessages(HANDLER_MESSAGE_DISMISS_CONTROL);
        } else {
            setVisbility(View.VISIBLE);
            mHandler.sendEmptyMessageDelayed(HANDLER_MESSAGE_DISMISS_CONTROL, DISMISS_CONTROL_TIME);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_video_play;
    }

    @Override
    protected void performClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    finish();
                }
                break;
            case R.id.iv_pause:
                pauseVideo();
                break;
            case R.id.iv_next:
                playNext();
                break;
        }
    }

    @Override
    protected void initView() {
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnCompletionListener(this);
        currentPosition = getIntent().getIntExtra(VIDEO_PLAY_POSITION_EXTRA, 0);
        mIsMusic = getIntent().getBooleanExtra(VIDEO_PLAY_POSITION_TYPE, false);

        mPlayType = getIntent().getStringExtra(VIDEO_PLAY_TYPE);
        mIsFromVoice = getIntent().getBooleanExtra(VIDEO_PLAY_FROM, false);
        if (mIsFromVoice) {
            currentPosition = SharePreferenceUtils.getCurrentIndex(this, mPlayType);
        }
        mLlBottomControl = findView(R.id.video_control);
        mSurfaceView = findView(R.id.sv_video_play);
        findView(R.id.back).setOnClickListener(this);
        findView(R.id.iv_pause).setOnClickListener(this);
        findView(R.id.iv_next).setOnClickListener(this);
        mSeekbar = findView(R.id.video_progress);
        mTime = findView(R.id.tv_time);
        mSeekbar.setOnSeekBarChangeListener(this);
        mSurfaceView.getHolder().addCallback(this);

        options = new BitmapFactory.Options();
        mAdapter = new GalleryAdapter();
        mVideoList = findView(R.id.video_recycle);
        mVideoList.setLayoutManager(new GridLayoutManager(this, 1));
        mVideoList.setAdapter(mAdapter);
        requestDataSource();
        mHandler.sendEmptyMessageDelayed(HANDLER_MESSAGE_DISMISS_CONTROL, DISMISS_CONTROL_TIME);
        mVideoList.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mHandler.removeMessages(HANDLER_MESSAGE_DISMISS_CONTROL);
                        break;
                    case MotionEvent.ACTION_UP:
                        mHandler.sendEmptyMessageDelayed(HANDLER_MESSAGE_DISMISS_CONTROL, DISMISS_CONTROL_TIME);
                        break;
                }
                return false;
            }
        });
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onPause() {
        super.onPause();
        mHandler.removeMessages(HANDLER_MESSAGE_GET_PROGRESS);
        mHandler.removeMessages(HANDLER_MESSAGE_DISMISS_CONTROL);
        pauseVideo();
    }

    public void playNext() {
        currentPosition++;
        if (currentPosition == mVideoBeans.size()) {
            currentPosition = 0;
        }
        pauseVideo();
        SharePreferenceUtils.setCurrentProgress(this, 0, mPlayType);
        playVideo();
    }

    public void pauseVideo() {
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                SharePreferenceUtils.setCurrentProgress(this,mediaPlayer.getCurrentPosition(),mPlayType);
                mediaPlayer.pause();
//                ((ImageView) findView(R.id.iv_pause)).setImageResource(R.drawable.bfq_zt_1);
            } else {
                mediaPlayer.start();
//                ((ImageView) findView(R.id.iv_pause)).setImageResource(R.drawable.bfq_zt_2);
            }
        }
    }

    public void playPre() {
        currentPosition--;
        if (currentPosition == -1) {
            currentPosition = mVideoBeans.size() - 1;
        }
        pauseVideo();
        SharePreferenceUtils.setCurrentProgress(this, 0, mPlayType);
        playVideo();
    }

    public void playVideo() {
        if (mVideoBeans != null&&mVideoBeans.size()>0) {
            if (mIsFromVoice) {
                SharePreferenceUtils.setCurrentIndex(this, currentPosition, mPlayType);
                Log.d("music", "progress:" + SharePreferenceUtils.getCurrentProgress(this, mPlayType));
            }
            try {
                mediaPlayer.setDataSource(mVideoBeans.get(currentPosition).path);
                mediaPlayer.prepare();
                mediaPlayer.start();
                if (mIsFromVoice) {
                    mediaPlayer.seekTo(SharePreferenceUtils.getCurrentProgress(this, mPlayType));
                }
                mVideoSize = mediaPlayer.getDuration();
                mSeekbar.setMax(mVideoSize);
//                ((ImageView) findView(R.id.iv_pause)).setImageResource(R.drawable.bfq_zt_2);
                mHandler.sendEmptyMessageDelayed(HANDLER_MESSAGE_GET_PROGRESS, GET_PROGRESS_TIME);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (IllegalStateException e) {
                mediaPlayer.release();
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setSurface(mSurfaceHolder.getSurface());
                mediaPlayer.setOnCompletionListener(this);
                playVideo();
            }
        }
    }

    //格式化时间格式
    public String getFormatTime(int time) {
        int second = time / 1000;
        int min = second / 60;
        int hour = min / 60;
        if (second >= 60)
            second = second % 60;
        if (min >= 60)
            min = min % 60;
        return String.format("%02d", hour) + " : " + String.format("%02d", min) + " : " + String.format("%02d", second);
    }


    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        mSurfaceHolder = surfaceHolder;
        mediaPlayer.setSurface(mSurfaceHolder.getSurface());
        playVideo();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopMediaplayer();
    }

    private void stopMediaplayer() {
        mediaPlayer.release();
        mediaPlayer = null;
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        playNext();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        if (b) {
            if (mIsFromVoice) {
                SharePreferenceUtils.setCurrentProgress(this, i, mPlayType);
            }
            mediaPlayer.seekTo(i);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    public void requestDataSource() {

        mAdapter.notifyDataSetChanged();
    }


    class GalleryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new GalleryHolder(LayoutInflater.from(VideoPlayActivity.this).inflate(R.layout.activity_video_image, null));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            if (mIsMusic) {
                ((GalleryHolder) holder).image.setVisibility(GONE);
                ((GalleryHolder) holder).mTitle.setVisibility(View.VISIBLE);
            } else {

            }
            holder.itemView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    currentPosition = position;
                    SharePreferenceUtils.setCurrentProgress(VideoPlayActivity.this, 0, mPlayType);
                    playVideo();
                }
            });
            ((GalleryHolder) holder).mTitle.setText(mVideoBeans.get(position).title);
        }

        @Override
        public int getItemCount() {
            return mVideoBeans.size();
        }

        class GalleryHolder extends RecyclerView.ViewHolder {
            private ImageView image, mIvBg;
            private TextView mTitle;

            public GalleryHolder(View itemView) {
                super(itemView);
                image = itemView.findViewById(R.id.gallery_item_iv);
                mTitle = itemView.findViewById(R.id.tv_title);
                mTitle.setVisibility(View.INVISIBLE);
                mIvBg = itemView.findViewById(R.id.gallery_item_bg);
                mIvBg.setVisibility(View.GONE);
            }
        }
    }
}

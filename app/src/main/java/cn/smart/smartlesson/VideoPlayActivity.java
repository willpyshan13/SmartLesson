package cn.smart.smartlesson;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.io.IOException;


import cn.smart.baselibrary.utils.SharePreferenceUtils;
import cn.smart.baselibrary.view.BaseActivity;
import cn.smart.smartlesson.bean.LearnDetailfBeans;
import cn.smart.smartlesson.bean.LearnInfoBean;
import cn.smart.smartlesson.utils.Constants;
import cn.smart.smartlesson.utils.RetrofitUtils;
import cn.smart.smartlesson.widget.CircleSurfaceView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.view.View.INVISIBLE;
import static android.view.View.OnClickListener;
import static android.view.View.OnTouchListener;

/**
 * @Desc: TODO
 * @Author: pengysh
 * @CreatedTime: 2017/6/22 20:01
 */
public class VideoPlayActivity extends BaseActivity implements SurfaceHolder.Callback, MediaPlayer.OnCompletionListener, SeekBar
        .OnSeekBarChangeListener {
    private static final String TAG = "VideoPlayActivity";
    private LearnInfoBean.DataBean.ContentBean mLearnInfo;
    private String mPlayType = "";
    MediaPlayer mMediaPlayer;
    CircleSurfaceView mSurfaceView;
    SurfaceHolder mSurfaceHolder;
    private RelativeLayout mVideoLayout;
    int currentPosition = 0;
    boolean mCurrentDisplayBig = false;
    boolean mIsFinishFirstVideo = false;
    private GalleryAdapter mAdapter;
    private SeekBar mSeekbar;
    private RecyclerView mVideoList;
    private TextView mTime, mTvComplete, mTvTitle, mTvProgress;
    private int HANDLER_MESSAGE_GET_PROGRESS = 1;
    private int HANDLER_MESSAGE_DISMISS_CONTROL = 2;
    private int HANDLER_MESSAGE_PLAY_VIDEO = 3;
    private static final int MESSAGE_WHAT_NOTIFY_CHANGE = 4;
    private static final int MESSAGE_WHAT_AUTO_MAX = 5;
    // 暂停
    public static final int CMD_PAUSE = 6;

    public static final int CMD_REPLAY = 7;
    public static final int CMD_NEXT = 8;
    public static final int CMD_PRE = 9;
    public static final int CMD_EXIT = 10;
    private long GET_PROGRESS_TIME = 1 * 1000;
    private long DISMISS_CONTROL_TIME = 5 * 1000;
    private int mVideoSize = 0;
    private BitmapFactory.Options options;
    private OkHttpClient okHttpClient;
    private Request mRequest;
    private LinearLayout mLlBottomControl;
    LearnDetailfBeans mLearnDetail;
    RelativeLayout.LayoutParams mPreParams;
    MyReceive myReceive;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == HANDLER_MESSAGE_GET_PROGRESS) {
                showCurrentTime();
            } else if (msg.what == HANDLER_MESSAGE_DISMISS_CONTROL) {
                Log.d(TAG, "HANDLER_MESSAGE_DISMISS_CONTROL     " + INVISIBLE);
                setVisbility(INVISIBLE);
                if (mCurrentDisplayBig) {
                    findView(R.id.tv_title).setVisibility(INVISIBLE);
                    findView(R.id.back).setVisibility(INVISIBLE);
                } else {
                    findView(R.id.tv_title).setVisibility(View.VISIBLE);
                }
            } else if (msg.what == HANDLER_MESSAGE_PLAY_VIDEO) {
                playVideoWIthUrl("http://videos.smart-dog.cn/good%20night.mp4");
            } else if (msg.what == MESSAGE_WHAT_NOTIFY_CHANGE) {
                startVideoPlay();
            } else if (msg.what == MESSAGE_WHAT_AUTO_MAX) {
                setMaxVideo();
            }
        }
    };

    class MyReceive extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("cmd_action")) {
                int control = intent.getIntExtra("cmd_action", 0);
                if (control == CMD_PAUSE) {
                    pauseVideo();
                } else if (control == CMD_REPLAY) {
                    rePlayVideo();
                } else if (control == CMD_NEXT) {
                    playNext();
                } else if (control == CMD_PRE) {
                    playPre();
                } else if (control == CMD_EXIT) {
                    pauseVideo();
                    VideoPlayActivity.this.finish();
                }
            }
        }
    }

    private void showCurrentTime() {
        if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
            mSeekbar.setProgress(mMediaPlayer.getCurrentPosition());
            mTime.setText(getFormatTime(mVideoSize));
            mTvProgress.setText(getFormatTime(mMediaPlayer.getCurrentPosition()));
            mHandler.sendEmptyMessageDelayed(HANDLER_MESSAGE_GET_PROGRESS, GET_PROGRESS_TIME);
        }
    }

    private void startVideoPlay() {
        if (mLearnDetail.getData() != null && mLearnDetail.getData().getLearnInfos().size() > 0) {
            findView(R.id.iv_play).setVisibility(View.INVISIBLE);
            ((ImageView) findView(R.id.iv_pause)).setImageResource(R.drawable.kt_play_model);
            playVideoWIthUrl(mLearnDetail.getData().getLearnInfos().get(0).getPath());
            mAdapter.notifyDataSetChanged();
            mHandler.sendEmptyMessageDelayed(MESSAGE_WHAT_AUTO_MAX, 5 * 1000);
        }
    }

    public void setVisbility(int visiblity) {
        Log.d(TAG, "setVisbility  " + visiblity);
        mLlBottomControl.setVisibility(visiblity);
        findView(R.id.video_control_top).setVisibility(visiblity);
        findView(R.id.tv_title).setVisibility(visiblity);
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
        Log.d(TAG, "handleTouchEvent   " + mVideoList.getVisibility() + "   " + mLlBottomControl.getVisibility());
        if (mVideoList.getVisibility() != View.VISIBLE) {
            Log.d(TAG, "mVideoList.getVisibility() != View.VISIBLE   " + mVideoList.getVisibility() + "   " + mLlBottomControl.getVisibility());
            if (mLlBottomControl.getVisibility() == View.VISIBLE) {
                setVisbility(View.INVISIBLE);
                findView(R.id.back).setVisibility(View.INVISIBLE);
                mHandler.removeMessages(HANDLER_MESSAGE_DISMISS_CONTROL);
            } else {
                setVisbility(View.VISIBLE);
                findView(R.id.back).setVisibility(View.VISIBLE);
                mHandler.sendEmptyMessageDelayed(HANDLER_MESSAGE_DISMISS_CONTROL, DISMISS_CONTROL_TIME);
            }
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
                if (mCurrentDisplayBig) {
                    setMinVideo();
                } else {
                    finish();
                }
                break;
            case R.id.iv_pause:
                pauseVideo();
                break;
            case R.id.iv_next:
                playNext();
                break;
            case R.id.iv_min:
                mHandler.removeMessages(HANDLER_MESSAGE_DISMISS_CONTROL);
                mHandler.removeMessages(MESSAGE_WHAT_AUTO_MAX);
                setMinVideo();
                break;
            case R.id.iv_play:
                setMaxVideo();
                break;
        }
    }

    @Override
    protected void initView() {
        myReceive = new MyReceive();
        IntentFilter filter = new IntentFilter();
        filter.addAction("cmd_action");
        registerReceiver(myReceive, filter);

        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setOnCompletionListener(this);
        mTvProgress = findView(R.id.tv_progress_time);
        mVideoLayout = findView(R.id.video_layout);
        mLlBottomControl = findView(R.id.video_control);
        mSurfaceView = findView(R.id.sv_video_play);
        mTvComplete = findView(R.id.tv_complete);
        mTvTitle = findView(R.id.tv_title);
        findView(R.id.back).setOnClickListener(this);
        findView(R.id.iv_pause).setOnClickListener(this);
        findView(R.id.iv_next).setOnClickListener(this);
        findView(R.id.iv_play).setOnClickListener(this);
        findView(R.id.iv_min).setOnClickListener(this);
        mSeekbar = findView(R.id.video_progress);
        mTime = findView(R.id.tv_time);
        mSeekbar.setOnSeekBarChangeListener(this);
        mSurfaceView.getHolder().addCallback(this);
        hideSystemUiVisible(mSurfaceView);
        options = new BitmapFactory.Options();
        mAdapter = new GalleryAdapter();
        mVideoList = findView(R.id.video_recycle);
        mVideoList.setLayoutManager(new GridLayoutManager(this, 1));
        mVideoList.setAdapter(mAdapter);
        mSurfaceView.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (mCurrentDisplayBig) {
                            handleTouchEvent();
                        } else {
                            setMaxVideo();
                        }
                        break;
                    case MotionEvent.ACTION_UP:

                        break;
                }
                return true;
            }
        });

        mVideoList.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mHandler.removeMessages(MESSAGE_WHAT_AUTO_MAX);
                        mHandler.removeMessages(HANDLER_MESSAGE_DISMISS_CONTROL);
                        break;
                    case MotionEvent.ACTION_UP:
                        mHandler.sendEmptyMessageDelayed(HANDLER_MESSAGE_DISMISS_CONTROL, DISMISS_CONTROL_TIME);
                        break;
                }
                return false;
            }
        });

        mLearnInfo = (LearnInfoBean.DataBean.ContentBean) getIntent().getSerializableExtra(Constants.ID);
        mTvTitle.setText(mLearnInfo.getName());
        requestDataSource(mLearnInfo.getId());
    }

    public void setMaxVideo() {
        mCurrentDisplayBig = true;
        if (mPreParams == null) {
            mPreParams = (RelativeLayout.LayoutParams) mVideoLayout.getLayoutParams();
        }
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams
                .MATCH_PARENT);
        mVideoLayout.setLayoutParams(params);
        mVideoList.setVisibility(View.INVISIBLE);
        mTvComplete.setVisibility(View.INVISIBLE);
        findView(R.id.iv_play).setVisibility(View.INVISIBLE);
        mSurfaceView.setBackground(null);
        mHandler.sendEmptyMessageDelayed(HANDLER_MESSAGE_DISMISS_CONTROL, DISMISS_CONTROL_TIME);
        setVisbility(View.VISIBLE);
    }

    public void setMinVideo() {
        mCurrentDisplayBig = false;
        mSurfaceView.setBackground(getResources().getDrawable(R.drawable.kt_video_bg));
        mVideoLayout.setLayoutParams(mPreParams);
        mVideoList.setVisibility(View.VISIBLE);
        mTvComplete.setVisibility(View.VISIBLE);
        setVisbility(View.INVISIBLE);
        findView(R.id.back).setVisibility(View.VISIBLE);
        mTvTitle.setVisibility(View.VISIBLE);
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
        if (currentPosition == 0) {
            mIsFinishFirstVideo = true;
            mAdapter.notifyDataSetChanged();
        }
        currentPosition++;
        if (currentPosition == mLearnDetail.getData().getLearnInfos().size()) {
            currentPosition = 0;
        }
        pauseVideo();
        playVideo();
    }

    public void rePlayVideo() {
        if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
            mMediaPlayer.start();
            ((ImageView) findView(R.id.iv_pause)).setImageResource(R.drawable.kt_play_model);
        }
    }

    public void pauseVideo() {
        if (mMediaPlayer != null) {
            if (mMediaPlayer.isPlaying()) {
                SharePreferenceUtils.setCurrentProgress(this, mMediaPlayer.getCurrentPosition(), mPlayType);
                mMediaPlayer.pause();
                ((ImageView) findView(R.id.iv_pause)).setImageResource(R.drawable.kt_play_model_play);
            }
        }
    }

    public void playPre() {
        currentPosition--;
        if (currentPosition == -1) {
            currentPosition = mLearnDetail.getData().getLearnInfos().size() - 1;
        }
        pauseVideo();
        SharePreferenceUtils.setCurrentProgress(this, 0, mPlayType);
        playVideo();
    }

    public void playVideo() {
        if (mLearnDetail != null && mLearnDetail.getData() != null && mLearnDetail.getData().getLearnInfos().size() > 0) {
            try {
                playVideoWIthUrl(mLearnDetail.getData().getLearnInfos().get(currentPosition).getPath());
            } catch (IllegalStateException e) {
                mMediaPlayer.release();
                mMediaPlayer = new MediaPlayer();
                mMediaPlayer.setSurface(mSurfaceHolder.getSurface());
                mMediaPlayer.setOnCompletionListener(this);
                playVideo();
            }
        }
    }

    private void playVideoWIthUrl(String url) {
        handleTouchEvent();
        try {
            mMediaPlayer.reset();
            mMediaPlayer.setDataSource(url);
            mMediaPlayer.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMediaPlayer.start();
        mVideoSize = mMediaPlayer.getDuration();
        mSeekbar.setMax(mVideoSize);
        mHandler.sendEmptyMessageDelayed(HANDLER_MESSAGE_GET_PROGRESS, GET_PROGRESS_TIME);
    }

    //格式化时间格式
    public String getFormatTime(int time) {
        int second = time / 1000;
        int min = second / 60;
        int hour = min / 60;
        if (second >= 60) {
            second = second % 60;
        }
        if (min >= 60) {
            min = min % 60;
        }
        return String.format("%02d", hour) + ":" + String.format("%02d", min) + ":" + String.format("%02d", second);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        mSurfaceHolder = surfaceHolder;
        mMediaPlayer.setSurface(mSurfaceHolder.getSurface());
        playVideo();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopMediaplayer();
        unregisterReceiver(myReceive);
    }

    private void stopMediaplayer() {
        mMediaPlayer.release();
        mMediaPlayer = null;
    }

    public void requestDataSource(int id) {
        mRequest = new Request.Builder().url(RetrofitUtils.LEARN_DETAIL_LIST + id).build();
        okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(mRequest);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String body = response.body().string();
                Log.d(TAG, "" + body);
                Gson gson = new Gson();
                mLearnDetail = gson.fromJson(body, LearnDetailfBeans.class);
                if (mLearnDetail != null) {
                    mHandler.sendEmptyMessage(MESSAGE_WHAT_NOTIFY_CHANGE);
                }

            }
        });
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        if (!mMediaPlayer.isPlaying()) {
            playNext();
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        if (b) {
            mMediaPlayer.seekTo(i);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    class GalleryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new GalleryHolder(LayoutInflater.from(VideoPlayActivity.this).inflate(R.layout.activity_video_image, null));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            holder.itemView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mIsFinishFirstVideo) {
                        currentPosition = position;
                        if (position == getItemCount()-1){
                            pauseVideo();
                            Intent intent = new Intent(VideoPlayActivity.this,GameActivity.class);
                            intent.putExtra("game_list",mLearnDetail);
                            startActivity(intent);
                        }else {
                            playVideo();
                        }

                    } else {
                        Toast.makeText(VideoPlayActivity.this, "请先观看完第一个视频", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            if (position == 0) {
                ((GalleryHolder) holder).mIvBg.setBackground(getResources().getDrawable(R.drawable.kt_sp_click));
            }else {
                if (mIsFinishFirstVideo) {
                    ((GalleryHolder) holder).mIvBg.setBackground(getResources().getDrawable(R.drawable.kt_sp_click));
                } else {
                    ((GalleryHolder) holder).mIvBg.setBackground(getResources().getDrawable(R.drawable.kt_sp));
                }
            }
<<<<<<< .mine
            if (position == getItemCount()-1) {
                ((GalleryHolder) holder).mTitle.setText("game");
            }else {
                ((GalleryHolder) holder).mTitle.setText(mLearnDetail.getData().getLearnInfos().get(position).getWord());
                Glide.with(VideoPlayActivity.this).load(mLearnDetail.getData().getLearnInfos().get(position).getImagePath()).into(((GalleryHolder) holder).image);
            }
||||||| .r187
            ((GalleryHolder) holder).mTitle.setText(mLearnDetail.getData().getLearnInfos().get(position).getWord());
            Glide.with(VideoPlayActivity.this).load(mLearnDetail.getData().getLearnInfos().get(position).getImagePath()).into(((GalleryHolder) holder).image);
=======
            ((GalleryHolder) holder).mTitle.setText(mLearnDetail.getData().getLearnInfos().get(position).getWord());
            Glide.with(VideoPlayActivity.this).load(mLearnDetail.getData().getLearnInfos().get(position).getImagePath()).into(((GalleryHolder)
                    holder).image);
>>>>>>> .r194
        }

        @Override
        public int getItemCount() {
            if (mLearnDetail != null && mLearnDetail.getData() != null) {
                if (mLearnDetail.getData().getGameLists().size()>0) {
                    return mLearnDetail.getData().getLearnInfos().size()+1;
                }else {
                    return mLearnDetail.getData().getLearnInfos().size();
                }
            } else {
                return 0;
            }
        }

        class GalleryHolder extends RecyclerView.ViewHolder {
            private ImageView image, mIvBg;
            private TextView mTitle;

            public GalleryHolder(View itemView) {
                super(itemView);
                image = itemView.findViewById(R.id.gallery_item_iv);
                mTitle = itemView.findViewById(R.id.tv_title);
                mIvBg = itemView.findViewById(R.id.gallery_item_bg);
            }
        }
    }
}

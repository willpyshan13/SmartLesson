package cn.smart.smartlesson;

import android.graphics.BitmapFactory;
import android.graphics.Color;
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
import java.util.ArrayList;
import java.util.List;


import cn.smart.baselibrary.utils.SharePreferenceUtils;
import cn.smart.baselibrary.view.BaseActivity;
import cn.smart.smartlesson.bean.LearnDetailfBeans;
import cn.smart.smartlesson.bean.LearnInfoBean;
import cn.smart.smartlesson.bean.VideoBeans;
import cn.smart.smartlesson.utils.Constants;
import cn.smart.smartlesson.utils.RetrofitUtils;
import cn.smart.smartlesson.widget.CircleSurfaceView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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
    private LearnInfoBean.DataBean.ContentBean mLearnInfo;
    private String mPlayType = "";
    MediaPlayer mediaPlayer;
    CircleSurfaceView mSurfaceView;
    SurfaceHolder mSurfaceHolder;
    private RelativeLayout mVideoLayout;
    int currentPosition = 0;
    boolean mCurrentDisplayBig = false;
    boolean mIsFinishFirstVideo = false;
    private GalleryAdapter mAdapter;
    private SeekBar mSeekbar;
    private RecyclerView mVideoList;
    private TextView mTime, mTvComplete, mTvTitle,mTvProgress;
    private int HANDLER_MESSAGE_GET_PROGRESS = 1;
    private int HANDLER_MESSAGE_DISMISS_CONTROL = 2;
    private int HANDLER_MESSAGE_PLAYVIDEO = 3;
    private static final int MESSAGE_WHAT_NOTIFY_CHANGE = 4;
    private static final int MESSAGE_WHAT_AUTO_MAX = 5;
    private long GET_PROGRESS_TIME = 1 * 1000;
    private long DISMISS_CONTROL_TIME = 5 * 1000;
    private int mVideoSize = 0;
    private BitmapFactory.Options options;
    private OkHttpClient okHttpClient;
    private Request mRequest;
    private LinearLayout mLlBottomControl;
    LearnDetailfBeans mLearnDetail;
    RelativeLayout.LayoutParams mPreParams;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == HANDLER_MESSAGE_GET_PROGRESS) {
                showCurrentTime();
            } else if (msg.what == HANDLER_MESSAGE_DISMISS_CONTROL) {
                setVisbility(INVISIBLE);
                findView(R.id.back).setVisibility(INVISIBLE);
            } else if (msg.what == HANDLER_MESSAGE_PLAYVIDEO) {
                playVideoWIthUrl("http://videos.smart-dog.cn/good%20night.mp4");
            } else if (msg.what == MESSAGE_WHAT_NOTIFY_CHANGE) {
                startVideoPlay();
            }else if(msg.what == MESSAGE_WHAT_AUTO_MAX){
                setMaxVideo();
            }
        }
    };

    private void showCurrentTime() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mSeekbar.setProgress(mediaPlayer.getCurrentPosition());
            mTime.setText(getFormatTime(mVideoSize));
            mTvProgress.setText(getFormatTime(mediaPlayer.getCurrentPosition()));
            mHandler.sendEmptyMessageDelayed(HANDLER_MESSAGE_GET_PROGRESS, GET_PROGRESS_TIME);
        }
    }
    private void startVideoPlay() {
        if (mLearnDetail.getData() != null && mLearnDetail.getData().size() > 0) {
            findView(R.id.iv_play).setVisibility(View.INVISIBLE);
            ((ImageView) findView(R.id.iv_pause)).setImageResource(R.drawable.bfq_zt_2);
            playVideoWIthUrl(mLearnDetail.getData().get(0).getPath());
            if (mLearnDetail.getData().size() == 1){
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mVideoList.getLayoutParams();
                params.width =1;
                mVideoList.setLayoutParams(params);
                mVideoList.setVisibility(View.INVISIBLE);
            }
            mAdapter.notifyDataSetChanged();
            mHandler.sendEmptyMessageDelayed(MESSAGE_WHAT_AUTO_MAX,5*1000);
        }
    }
    public void setVisbility(int visiblity) {
        mLlBottomControl.setVisibility(visiblity);
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
        if (!mCurrentDisplayBig){
            setMaxVideo();
        }else {
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
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_video_play;
    }

    @Override
    protected void performClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                if (mCurrentDisplayBig){
                    setMinVideo();
                }else {
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
                setMinVideo();
                break;
            case R.id.iv_play:
                setMaxVideo();
                break;
        }
    }

    @Override
    protected void initView() {
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnCompletionListener(this);

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
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        mVideoLayout.setLayoutParams(params);
        mVideoList.setVisibility(View.INVISIBLE);
        mTvComplete.setVisibility(View.INVISIBLE);
        findView(R.id.iv_play).setVisibility(View.INVISIBLE);
        mHandler.sendEmptyMessageDelayed(HANDLER_MESSAGE_DISMISS_CONTROL, DISMISS_CONTROL_TIME);
        setVisbility(View.VISIBLE);
    }

    public void setMinVideo() {
        mCurrentDisplayBig = false;
        if (mLearnDetail.getData() != null && mLearnDetail.getData().size() >1){
            mVideoList.setVisibility(View.VISIBLE);
        }

        mVideoLayout.setLayoutParams(mPreParams);
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
        if (currentPosition == 0){
            mIsFinishFirstVideo = true;
        }
        currentPosition++;
        if (currentPosition == mLearnDetail.getData().size()) {
            currentPosition = 0;
        }
        pauseVideo();
        SharePreferenceUtils.setCurrentProgress(this, 0, mPlayType);
        playVideo();
    }

    public void pauseVideo() {
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                SharePreferenceUtils.setCurrentProgress(this, mediaPlayer.getCurrentPosition(), mPlayType);
                mediaPlayer.pause();
                ((ImageView) findView(R.id.iv_pause)).setImageResource(R.drawable.bfq_zt_1);
            } else {
                mediaPlayer.start();
                ((ImageView) findView(R.id.iv_pause)).setImageResource(R.drawable.bfq_zt_2);
            }
        }
    }

    public void playPre() {
        currentPosition--;
        if (currentPosition == -1) {
            currentPosition = mLearnDetail.getData().size() - 1;
        }
        pauseVideo();
        SharePreferenceUtils.setCurrentProgress(this, 0, mPlayType);
        playVideo();
    }

    public void playVideo() {
        if (mLearnDetail != null && mLearnDetail.getData() != null && mLearnDetail.getData().size() > 0) {
            try {
                playVideoWIthUrl(mLearnDetail.getData().get(currentPosition).getPath());
            } catch (IllegalStateException e) {
                mediaPlayer.release();
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setSurface(mSurfaceHolder.getSurface());
                mediaPlayer.setOnCompletionListener(this);
                playVideo();
            }
        }
    }

    private void playVideoWIthUrl(String url) {
        try {
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mediaPlayer.start();
        mVideoSize = mediaPlayer.getDuration();
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
        playNext();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        if (b) {
            mediaPlayer.seekTo(i);
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
                        SharePreferenceUtils.setCurrentProgress(VideoPlayActivity.this, 0, mPlayType);
                        playVideo();
                    }else {
                        Toast.makeText(VideoPlayActivity.this,"请先观看完第一个视频",Toast.LENGTH_SHORT).show();
                    }
                }
            });
            ((GalleryHolder) holder).mTitle.setText(mLearnDetail.getData().get(position).getWord());
            Glide.with(VideoPlayActivity.this).load(mLearnDetail.getData().get(position).getImagePath()).into(((GalleryHolder) holder).image);
        }

        @Override
        public int getItemCount() {
            if (mLearnDetail != null && mLearnDetail.getData() != null) {
                return mLearnDetail.getData().size();
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

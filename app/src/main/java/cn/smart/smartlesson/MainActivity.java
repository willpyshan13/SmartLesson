package cn.smart.smartlesson;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.smart.smartlesson.bean.LearnDetailfBeans;
import cn.smart.smartlesson.bean.LearnInfoBean;
import cn.smart.smartlesson.utils.Constants;
import cn.smart.smartlesson.utils.RetrofitUtils;
import cn.smart.smartlesson.widget.LessonLayout;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author pengysh
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    MainAdapter mMainAdapter;
    MediaPlayer mMusicPlayer;
    private OkHttpClient okHttpClient;
    SwipeRefreshLayout mSwipeRefreshLayout;
    private Request mRequest;
    private LearnInfoBean mLearnBeans;
    private boolean mNeedRefresh = false;
    private List<LearnInfoBean.DataBean.ContentBean> mLearnInfoList;
    private RecyclerView mRecycle;
    private static final int MESSAGE_WHAT_NOTIFY_CHANGE = 3;
    private int mCurrentPage = 1;
    private int mPageSize = 20;
    RequestOptions myOptions = new RequestOptions().circleCrop();

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            if (msg.what == MESSAGE_WHAT_NOTIFY_CHANGE) {
                mSwipeRefreshLayout.setRefreshing(false);
                if (mNeedRefresh){
                    mRecycle.scrollToPosition(0);
                    mNeedRefresh = false;
                }
                if (mLearnInfoList!=null){
                    mMainAdapter.notifyDataSetChanged();
                }
            }
        };
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mRecycle = findViewById(R.id.main_recycle);
        mLearnInfoList = new ArrayList<>();
        mSwipeRefreshLayout = findViewById(R.id.swipe_refresh);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mNeedRefresh = true;
                mCurrentPage =1;
                requestDataSource(mCurrentPage,mPageSize);
            }
        });
        hideSystemUiVisible(mRecycle);
        GridLayoutManager manager = new GridLayoutManager(this,1);
        mRecycle.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (mRecycle.canScrollHorizontally(1)){
                    mCurrentPage++;
                    requestDataSource(mCurrentPage,mPageSize);
                }
            }
        });
        manager.setOrientation(GridLayoutManager.HORIZONTAL);
        mRecycle.setLayoutManager(manager);
        mMainAdapter = new MainAdapter();
        mRecycle.setAdapter(mMainAdapter);
        requestDataSource(mCurrentPage,mPageSize);
    }

    public void requestDataSource(int page,int size) {
        mRequest = new Request.Builder().url(RetrofitUtils.LEARN_LIST+"?currentPage="+page+"&pageSize"+size ).build();
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
                mLearnBeans = gson.fromJson(body, LearnInfoBean.class);
                if (mNeedRefresh){
                    mLearnInfoList.clear();
                }
                mLearnInfoList.addAll(mLearnBeans.getData().getContent());
                if (mLearnBeans != null) {
                    mHandler.sendEmptyMessage(MESSAGE_WHAT_NOTIFY_CHANGE);
                }

            }
        });
    }

    class MainAdapter extends RecyclerView.Adapter{

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == Constants.PAGE_POSITION_ONE) {
                return new MainHolder(LayoutInflater.from(MainActivity.this).inflate(R.layout.activity_lesson_one, null));
            }else if(viewType == Constants.PAGE_POSITION_TWO){
                return new MainHolder(LayoutInflater.from(MainActivity.this).inflate(R.layout.activity_lesson_two, null));
            }else if(viewType == Constants.PAGE_POSITION_THREE){
                return new MainHolder(LayoutInflater.from(MainActivity.this).inflate(R.layout.activity_lesson_three, null));
            }else if(viewType == Constants.PAGE_POSITION_FOUR){
                return new MainHolder(LayoutInflater.from(MainActivity.this).inflate(R.layout.activity_lesson_four, null));
            }else if(viewType == Constants.PAGE_POSITION_FIVE){
                return new MainHolder(LayoutInflater.from(MainActivity.this).inflate(R.layout.activity_lesson_five, null));
            }else if(viewType == Constants.PAGE_POSITION_SIX){
                return new MainHolder(LayoutInflater.from(MainActivity.this).inflate(R.layout.activity_lesson_six, null));
            }else{
                return new MainHolder(LayoutInflater.from(MainActivity.this).inflate(R.layout.activity_lesson_seven, null));
            }
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            ((MainHolder)holder).mTvTitle.setText(mLearnInfoList.get(position).getName());
            if (mLearnInfoList.get(position).getStatus() == 0){
                ((MainHolder)holder).mStatus.setBackgroundResource(R.drawable.kt_tp);
            }else {
                ((MainHolder)holder).mStatus.setBackgroundResource(R.drawable.kt_wjs);
            }
            Glide.with(MainActivity.this).load(mLearnInfoList.get(position).getImagePath()).apply(myOptions).into(((MainHolder)holder).mBg);
            ((MainHolder)holder).layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mLearnInfoList.get(position).getStatus() == 0) {
                        Intent intent = new Intent(MainActivity.this, VideoPlayActivity.class);
                        intent.putExtra(Constants.ID, mLearnInfoList.get(position));
                        MainActivity.this.startActivity(intent);
                        playTts(R.raw.openlesson);
                    }else {
                        playTts(R.raw.map_luyin_ni);
                    }
                }
            });
        }



        @Override
        public int getItemViewType(int position) {
            return position%Constants.PAGE_COUNT;
        }

        @Override
        public int getItemCount() {
            if (mLearnInfoList!=null){
                return mLearnInfoList.size();
            }else {
                return 0;
            }
        }

        class MainHolder extends RecyclerView.ViewHolder{
            private RelativeLayout layout;
            ImageView mBg,mStatus;
            TextView mTvTitle;
            public MainHolder(View itemView) {
                super(itemView);
                layout = itemView.findViewById(R.id.ll_one);
                mBg = itemView.findViewById(R.id.iv_one);
                mTvTitle = itemView.findViewById(R.id.tv_one);
                mStatus = itemView.findViewById(R.id.iv_circle);
            }
        }
    }

    private void playTts(int res) {
        if (mMusicPlayer!=null&&mMusicPlayer.isPlaying()){
            mMusicPlayer.stop();
        }
        mMusicPlayer = MediaPlayer.create(this,res);
        mMusicPlayer.start();
    }

    /**
     * 隐藏systemui以及导航栏
     * @param view
     */
    public void hideSystemUiVisible(View view){
        view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                |View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }
}

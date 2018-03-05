package cn.smart.smartlesson;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import cn.smart.baselibrary.view.BaseActivity;
import cn.smart.smartlesson.adapter.LessonLevelAdapter;
import cn.smart.smartlesson.bean.LessonPickBean;
import cn.smart.smartlesson.impl.OkHttpCallback;
import cn.smart.smartlesson.utils.HttpRequestUtils;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @desc: TODO
 * @author: pengysh
 * @createdTime: 2018-1-30 19:30
 */
public class LessonLevelPickActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = "LessonLevelPickActivity";
    private RecyclerView mRecyclerView;
    private LessonLevelAdapter mAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ImageView iv_back;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_lesson_pick;
    }

    @Override
    protected void performClick(View v) {

    }

    @Override
    protected void initView() {
        mSwipeRefreshLayout = this.findView(R.id.swipe_refresh);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView = this.findView(R.id.lesson_pick_recycle);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mRecyclerView.setAdapter(mAdapter);

        iv_back = (ImageView) this.findView(R.id.lesson_pick_iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mAdapter.setOnLessonClickListener(new LessonLevelAdapter.onLessonClickListener() {
            @Override
            public void onLessonClick(int position, LessonPickBean.DataBean dataBean) {
                //跳转到MainActivity页面里，并传入课阶等级
                Intent intent = new Intent(LessonLevelPickActivity.this, MainActivity.class);
                intent.putExtra("level", dataBean.getLevelId());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        hideSystemUiVisible(mRecyclerView);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        mAdapter = new LessonLevelAdapter();
        Observable.timer(300, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers
                        .mainThread()).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                getLearnLevelList();
            }
        });
    }

    @Override
    public void onRefresh() {

        getLearnLevelList();
    }


    /**
     * 获取课程列表
     */
    private void getLearnLevelList() {
        HttpRequestUtils.getInstance().getLearnLevelList("1", "0", new OkHttpCallback() {
            @Override
            public void onSuccess(String response) {
                Gson gson = new Gson();
                mAdapter.setData(gson.fromJson(response, LessonPickBean.class));
                if (mSwipeRefreshLayout != null) {
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            }

            @Override
            public void onError(IOException e) {
                e.printStackTrace();
                if (mSwipeRefreshLayout != null) {
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            }
        });
    }

}

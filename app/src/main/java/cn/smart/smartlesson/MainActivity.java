package cn.smart.smartlesson;

import android.os.Handler;
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

import com.google.gson.Gson;

import java.io.IOException;

import cn.smart.smartlesson.bean.LearnDetailfBeans;
import cn.smart.smartlesson.bean.LearnInfoBean;
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
    private HorizontalScrollView mScrollView;
    private LinearLayout mLInearLayout;
    private OkHttpClient okHttpClient;
    private Request mRequest;
    private LearnInfoBean mLearnBeans;
    private static final int MESSAGE_WHAT_NOTIFY_CHANGE = 3;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            if (msg.what == MESSAGE_WHAT_NOTIFY_CHANGE) {
                if (mLearnBeans!=null&&mLearnBeans.getData()!=null&&mLearnBeans.getData().getContent()!=null){
                    LessonLayout lessonLayout = new LessonLayout(MainActivity.this);
                    mLInearLayout.addView(lessonLayout);
                    lessonLayout.setData(mLearnBeans.getData().getContent());
                }
            }
        }

        ;
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mScrollView = findViewById(R.id.main_scrollview);
        hideSystemUiVisible(mScrollView);

        mLInearLayout = findViewById(R.id.main_scroll_linear);
        requestDataSource();
    }

    public void requestDataSource() {
        mRequest = new Request.Builder().url(RetrofitUtils.LEARN_LIST ).build();
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
                if (mLearnBeans != null) {
                    mHandler.sendEmptyMessage(MESSAGE_WHAT_NOTIFY_CHANGE);
                }

            }
        });
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

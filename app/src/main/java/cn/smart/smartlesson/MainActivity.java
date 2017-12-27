package cn.smart.smartlesson;

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

import cn.smart.smartlesson.widget.LessonLayout;

/**
 * @author pengysh
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private HorizontalScrollView mScrollView;
    private LinearLayout mLInearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mScrollView = findViewById(R.id.main_scrollview);
        hideSystemUiVisible(mScrollView);

        mLInearLayout = findViewById(R.id.main_scroll_linear);
        mLInearLayout.addView(new LessonLayout(this));
        mLInearLayout.addView(new LessonLayout(this));
        mLInearLayout.addView(new LessonLayout(this));
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
//4.4底下有一个条框
//        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                |
    }
}

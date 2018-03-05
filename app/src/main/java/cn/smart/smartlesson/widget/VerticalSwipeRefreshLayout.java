package cn.smart.smartlesson.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

/**
 * @author: huanghz
 * @createdTime: 2018/2/2 10:53
 * 重写 SwipeRefreshLayout 解决recyclerView左右滑动时触发刷新
 */

public class VerticalSwipeRefreshLayout extends SwipeRefreshLayout {

    private int mTouchSlop;
    // 上一次触摸时的X坐标
    private float mPrevX;

    public VerticalSwipeRefreshLayout(@NonNull Context context) {
        this(context, null);
    }

    public VerticalSwipeRefreshLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPrevX = event.getX();
                break;

            case MotionEvent.ACTION_MOVE:
                final float eventX = event.getX();
                float xDiff = Math.abs(eventX - mPrevX);
                // 下拉刷新在竖直滑动时触发
                if (xDiff > mTouchSlop + 100) {
                    return false;
                }
        }

        return super.onInterceptTouchEvent(event);
    }
}

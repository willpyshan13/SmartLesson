package cn.smart.smartlesson.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * @desc: TODO
 * @author: pengysh
 * @createdTime: 2017/12/14 15:57
 */
public class BackgroundRecycleView extends RecyclerView {
    public BackgroundRecycleView(Context context) {
        super(context);
    }

    public BackgroundRecycleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BackgroundRecycleView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);

    }
}

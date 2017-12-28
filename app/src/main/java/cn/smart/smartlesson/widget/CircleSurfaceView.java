package cn.smart.smartlesson.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.SurfaceView;

/**
 * @desc: TODO
 * @author: pengysh
 * @createdTime: 2017/12/28 14:55
 */
public class CircleSurfaceView extends SurfaceView {
    public CircleSurfaceView(Context context) {
        super(context);
    }

    public CircleSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Path path = new Path();
        //用矩形表示SurfaceView宽高
        RectF rect = new RectF(0, 0, this.getWidth(), this.getHeight());
        //15.0f即是圆角半径
        path.addRoundRect(rect, 15.0f, 15.0f, Path.Direction.CCW);
        //裁剪画布，并设置其填充方式
        canvas.clipPath(path, Region.Op.REPLACE);
        super.draw(canvas);
    }
}

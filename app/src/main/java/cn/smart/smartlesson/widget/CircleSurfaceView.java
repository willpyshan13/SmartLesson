package cn.smart.smartlesson.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceView;

/**
 * @desc: TODO
 * @author: pengysh
 * @createdTime: 2017/12/28 14:55
 */
public class CircleSurfaceView extends SurfaceView {
    private static final String TAG = "CircleSurfaceView";
    public CircleSurfaceView(Context context) {
        super(context);
    }

    public CircleSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}

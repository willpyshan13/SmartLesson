package cn.smart.smartlesson.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import cn.smart.smartlesson.R;

/**
 * @desc: TODO
 * @author: pengysh
 * @createdTime: 2017/12/14 16:45
 */
public class LessonLayout extends LinearLayout {
    View view;
    public LessonLayout(Context context) {
        super(context);
        view = LayoutInflater.from(context).inflate(R.layout.activity_lesson,null);

        addView(view);
    }

    public LessonLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LessonLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}

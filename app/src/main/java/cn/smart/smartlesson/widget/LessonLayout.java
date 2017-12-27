package cn.smart.smartlesson.widget;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import cn.smart.smartlesson.R;
import cn.smart.smartlesson.VideoPlayActivity;

/**
 * @desc: TODO
 * @author: pengysh
 * @createdTime: 2017/12/14 16:45
 */
public class LessonLayout extends LinearLayout implements View.OnClickListener{
    View view;
    private Context mContext;
    public LessonLayout(Context context) {
        super(context);
        mContext = context;
        view = LayoutInflater.from(context).inflate(R.layout.activity_lesson,null);
        view.findViewById(R.id.ll_one).setOnClickListener(this);
        view.findViewById(R.id.ll_two).setOnClickListener(this);
        view.findViewById(R.id.ll_three).setOnClickListener(this);
        view.findViewById(R.id.ll_four).setOnClickListener(this);
        view.findViewById(R.id.ll_five).setOnClickListener(this);
        view.findViewById(R.id.ll_six).setOnClickListener(this);
        view.findViewById(R.id.ll_seven).setOnClickListener(this);
        addView(view);
    }

    public LessonLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LessonLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.ll_one:
                intent = new Intent(mContext, VideoPlayActivity.class);
                mContext.startActivity(intent);
                break;
            case R.id.ll_two:
                intent = new Intent(mContext, VideoPlayActivity.class);
                mContext.startActivity(intent);
                break;
            case R.id.ll_three:
                intent = new Intent(mContext, VideoPlayActivity.class);
                mContext.startActivity(intent);
                break;
            case R.id.ll_four:
                intent = new Intent(mContext, VideoPlayActivity.class);
                mContext.startActivity(intent);
                break;
            case R.id.ll_five:
                intent = new Intent(mContext, VideoPlayActivity.class);
                mContext.startActivity(intent);
                break;
            case R.id.ll_six:
                intent = new Intent(mContext, VideoPlayActivity.class);
                mContext.startActivity(intent);
                break;
            case R.id.ll_seven:
                intent = new Intent(mContext, VideoPlayActivity.class);
                mContext.startActivity(intent);
                break;
        }
    }
}

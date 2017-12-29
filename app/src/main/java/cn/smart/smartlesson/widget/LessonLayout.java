package cn.smart.smartlesson.widget;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import cn.smart.smartlesson.R;
import cn.smart.smartlesson.VideoPlayActivity;
import cn.smart.smartlesson.bean.LearnInfoBean;
import cn.smart.smartlesson.utils.Constants;

/**
 * @desc: TODO
 * @author: pengysh
 * @createdTime: 2017/12/14 16:45
 */
public class LessonLayout extends LinearLayout implements View.OnClickListener{
    View view;
    private Context mContext;
    private LinearLayout mLlOne,mLlTwo,mLlThree,mLlFour,mLlFive,mLlSix,mLlSever;
    private ImageView mIvOne,mIvTwo,mIvThree,mIvFour,mIvFive,mIvSix,mIvSever;
    private TextView mTvOne,mTvTwo,mTvThree,mTvFour,mTvFive,mTvSix,mTvSever;
    List<LearnInfoBean.DataBean.ContentBean> mContent;
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
        mLlOne = view.findViewById(R.id.ll_one);
        mLlTwo = view.findViewById(R.id.ll_two);
        mLlThree = view.findViewById(R.id.ll_three);
        mLlFour = view.findViewById(R.id.ll_four);
        mLlFive = view.findViewById(R.id.ll_five);
        mLlSix = view.findViewById(R.id.ll_six);
        mLlSever = view.findViewById(R.id.ll_seven);

        mIvOne = view.findViewById(R.id.iv_one);
        mIvTwo = view.findViewById(R.id.iv_two);
        mIvThree = view.findViewById(R.id.iv_three);
        mIvFour = view.findViewById(R.id.iv_four);
        mIvFive = view.findViewById(R.id.iv_five);
        mIvSix = view.findViewById(R.id.iv_six);
        mIvSever = view.findViewById(R.id.iv_seven);

        mTvOne = view.findViewById(R.id.tv_one);
        mTvTwo = view.findViewById(R.id.tv_two);
        mTvThree = view.findViewById(R.id.tv_three);
        mTvFour = view.findViewById(R.id.tv_four);
        mTvFive = view.findViewById(R.id.tv_five);
        mTvSix = view.findViewById(R.id.tv_six);
        mTvSever = view.findViewById(R.id.tv_sever);
        addView(view);
    }

    public LessonLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LessonLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setData(List<LearnInfoBean.DataBean.ContentBean> content){
        mContent = content;
        for (int i = 0;i< content.size();i++){
            setLessonData(i,content.get(i));
        }
    }

    private void setLessonData(int position,LearnInfoBean.DataBean.ContentBean data){
        switch (position){
            case 0:
                setOneData(data);
                break;
            case 1:
                setTwoData(data);
                break;
            case 2:
                setThreeData(data);
                break;
            case 3:
                setFourData(data);
                break;
            case 4:
                setFiveData(data);
                break;
            case 5:
                setSixData(data);
                break;
            case 6:
                setSeverData(data);
                break;
        }
    }

    private void setOneData(LearnInfoBean.DataBean.ContentBean data){
        mTvOne.setText(data.getName());
        Glide.with(mContext).load(data.getImagePath()).into(mIvOne);
    }
    private void setTwoData(LearnInfoBean.DataBean.ContentBean data){
        mTvTwo.setText(data.getName());
        Glide.with(mContext).load(data.getImagePath()).into(mIvTwo);
    }
    private void setThreeData(LearnInfoBean.DataBean.ContentBean data){
        mTvThree.setText(data.getName());
        Glide.with(mContext).load(data.getImagePath()).into(mIvThree);
    }
    private void setFourData(LearnInfoBean.DataBean.ContentBean data){
        mTvFour.setText(data.getName());
        Glide.with(mContext).load(data.getImagePath()).into(mIvFour);
    }
    private void setFiveData(LearnInfoBean.DataBean.ContentBean data){
        mTvFive.setText(data.getName());
        Glide.with(mContext).load(data.getImagePath()).into(mIvFive);
    }
    private void setSixData(LearnInfoBean.DataBean.ContentBean data){
        mTvSix.setText(data.getName());
        Glide.with(mContext).load(data.getImagePath()).into(mIvSix);
    }
    private void setSeverData(LearnInfoBean.DataBean.ContentBean data){
        mTvSever.setText(data.getName());
        Glide.with(mContext).load(data.getImagePath()).into(mIvSever);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.ll_one:
                intent = new Intent(mContext, VideoPlayActivity.class);
                intent.putExtra(Constants.ID,mContent.get(0));
                mContext.startActivity(intent);
                break;
            case R.id.ll_two:
                intent = new Intent(mContext, VideoPlayActivity.class);
                intent.putExtra(Constants.ID,mContent.get(1));
                mContext.startActivity(intent);
                break;
            case R.id.ll_three:
                intent = new Intent(mContext, VideoPlayActivity.class);
                intent.putExtra(Constants.ID,mContent.get(2));
                mContext.startActivity(intent);
                break;
            case R.id.ll_four:
                intent = new Intent(mContext, VideoPlayActivity.class);
                intent.putExtra(Constants.ID,mContent.get(3));
                mContext.startActivity(intent);
                break;
            case R.id.ll_five:
                intent = new Intent(mContext, VideoPlayActivity.class);
                intent.putExtra(Constants.ID,mContent.get(4));
                mContext.startActivity(intent);
                break;
            case R.id.ll_six:
                intent = new Intent(mContext, VideoPlayActivity.class);
                intent.putExtra(Constants.ID,mContent.get(5));
                mContext.startActivity(intent);
                break;
            case R.id.ll_seven:
                intent = new Intent(mContext, VideoPlayActivity.class);
                intent.putExtra(Constants.ID,mContent.get(6));
                mContext.startActivity(intent);
                break;
        }
    }
}

package cn.smart.smartlesson.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.smart.smartlesson.R;
import cn.smart.smartlesson.bean.LessonPickBean;

/**
 * Created by DELL on 2018/2/2.
 */

public class LessonLevelAdapter extends RecyclerView.Adapter<LessonLevelAdapter.LessonLevelHolder> {

    private static final String TAG = "LessonLevelAdapter";

    public interface onLessonClickListener {
        void onLessonClick(int position, LessonPickBean.DataBean dataBean);
    }

    private onLessonClickListener onLessonClickListener;

    private List<LessonPickBean.DataBean> datas = new ArrayList<>();
    private int[] images = {R.drawable.course_one, R.drawable.course_two, R.drawable.course_three, R.drawable.course_four, R.drawable.course_five,
            R.drawable.course_six, R.drawable.course_seven, R.drawable.course_eight, R.drawable.course_nine};


    public LessonLevelAdapter() {
    }

    public void setData(LessonPickBean pickBean) {
        datas = pickBean.getData();
        notifyDataSetChanged();
    }

    @Override
    public LessonLevelHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_lesson_level_layout, parent, false);
        return new LessonLevelHolder(view);
    }

    @Override
    public void onBindViewHolder(LessonLevelHolder holder, int position) {
        int levelId = datas.get(position).getLevelId() - 1;
        if (levelId < 0) {
            levelId = 0;
        }
        //设置对应课阶的对应图标
        holder.iv_bg.setImageResource(images[levelId % images.length]);
        holder.tv_label.setText(datas.get(position).getDescription());

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    public void setOnLessonClickListener(LessonLevelAdapter.onLessonClickListener onLessonClickListener) {
        this.onLessonClickListener = onLessonClickListener;
    }

    class LessonLevelHolder extends RecyclerView.ViewHolder {

        private ImageView iv_bg;
        private TextView tv_label;

        public LessonLevelHolder(View itemView) {
            super(itemView);
            iv_bg = (ImageView) itemView.findViewById(R.id.adapter_lesson_level_iv_bg);
            tv_label = (TextView) itemView.findViewById(R.id.adapter_lesson_level_tv_label);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onLessonClickListener != null) {
                        onLessonClickListener.onLessonClick(getAdapterPosition(), datas.get(getAdapterPosition()));
                    }
                }
            });

        }
    }
}

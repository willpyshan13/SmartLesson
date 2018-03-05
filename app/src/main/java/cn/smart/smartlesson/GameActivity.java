package cn.smart.smartlesson;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.smart.baselibrary.view.BaseActivity;
import cn.smart.smartlesson.bean.LearnDetailfBeans;
import cn.smart.smartlesson.bean.LessonPickBean;

/**
 * @author pengysh
 */
public class GameActivity extends BaseActivity {
    LearnDetailfBeans mLearnDetail;
    private TextView mTvTitle,mTvGameName;
    private RecyclerView mRecyclerView;
    private LessonGameAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_game;
    }

    @Override
    protected void performClick(View v) {

    }

    @Override
    protected void initView() {
        mTvTitle = findViewById(R.id.tv_title);
        mTvGameName = findViewById(R.id.tv_game_name);
        mLearnDetail = (LearnDetailfBeans) getIntent().getSerializableExtra("game_list");
        if (mLearnDetail.getData().getGameLists()!=null){
            mTvGameName.setText(mLearnDetail.getData().getGameLists().get(0).getGameName());
            mTvTitle.setText(mLearnDetail.getData().getGameLists().get(0).getGameInfo());
        }
        mRecyclerView = this.findView(R.id.lesson_pick_recycle);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        mAdapter = new LessonGameAdapter();
    }

    public class LessonGameAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private static final String TAG = "LessonLevelAdapter";

        private List<LessonPickBean.DataBean> datas = new ArrayList<>();
        private int[] images = {R.drawable.kcxz_tp_rmjd_mr, R.drawable.kcxz_tp_yjkc_mr, R.drawable.kcxz_tp_ejkc_mr,
                R.drawable.kcxz_tp_sjkc_mr, R.drawable.kcxz_tp_sijkc_mr, R.drawable.kcxz_tp_wjkc_mr,
                R.drawable.kcxz_tp_ljkc_mr, R.drawable.kcxz_tp_qjkc_mr, R.drawable.kcxz_tp_bjkc_mr,
                R.drawable.kcxz_tp_jjkc_mr, R.drawable.kcxz_tp_shijkc_mr, R.drawable.kcxz_tp_shiyjkc_mr,};


        public LessonGameAdapter() {
        }

        public void setData(LessonPickBean pickBean) {
            datas = pickBean.getData();
            notifyDataSetChanged();
        }

        @Override
        public LessonGameHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_lesson_level_layout, parent, false);
            return new LessonGameHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            int levelId = datas.get(position).getLevelId() - 1;
            if (levelId < 0) {
                levelId = 0;
            }
            //设置对应课阶的对应图标
            (( LessonGameHolder)holder).iv_bg.setImageResource(images[levelId % images.length]);
            (( LessonGameHolder)holder).tv_label.setText(datas.get(position).getDescription());

        }

        @Override
        public int getItemCount() {
            return 0;
        }


        class LessonGameHolder extends RecyclerView.ViewHolder {

            private ImageView iv_bg;
            private TextView tv_label;

            public LessonGameHolder(View itemView) {
                super(itemView);
                iv_bg = (ImageView) itemView.findViewById(R.id.adapter_lesson_level_iv_bg);
                tv_label = (TextView) itemView.findViewById(R.id.adapter_lesson_level_tv_label);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

            }
        }
    }

}

package cn.smart.smartlesson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * @author pengysh
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ImageView mHomeBg;
    private RecyclerView mHomeRecycle;
    private HomeAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHomeRecycle = findViewById(R.id.home_recycle);
        mHomeBg = findViewById(R.id.home_bg);
        mAdapter = new HomeAdapter();
        mHomeRecycle.setLayoutManager(new GridLayoutManager(this,1));
        mHomeRecycle.setAdapter(mAdapter);
    }

    class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new HomeHolder(LayoutInflater.from(MainActivity.this).inflate(R.layout.home_item_layout,null));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) ((HomeHolder)holder).mBtn.getLayoutParams();
            if (position%6 == 1||position%6 == 3||position%6 == 5){
                params.removeRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                params.removeRule(RelativeLayout.ALIGN_PARENT_LEFT);
                params.addRule(RelativeLayout.CENTER_IN_PARENT);
                ((HomeHolder)holder).mBtn.setLayoutParams(params);
            }else {
                if (position % 4 == 0) {
                    params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                    params.removeRule(RelativeLayout.CENTER_IN_PARENT);
                    params.removeRule(RelativeLayout.ALIGN_PARENT_LEFT);
                    ((HomeHolder) holder).mBtn.setLayoutParams(params);
                } else {
                    params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                    params.removeRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                    params.removeRule(RelativeLayout.CENTER_IN_PARENT);
                    ((HomeHolder) holder).mBtn.setLayoutParams(params);
                }
            }
        }

        @Override
        public int getItemCount() {
            return 100;
        }

        class HomeHolder extends RecyclerView.ViewHolder{
            private Button mBtn;
            public HomeHolder(View itemView) {
                super(itemView);
                mBtn = itemView.findViewById(R.id.button);
            }
        }
    }
}

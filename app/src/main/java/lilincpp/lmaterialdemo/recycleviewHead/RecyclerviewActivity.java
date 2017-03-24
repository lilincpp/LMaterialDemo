package lilincpp.lmaterialdemo.recycleviewHead;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import lilincpp.lmaterialdemo.R;

/**
 * Created by Colin on 2017/3/23.
 */

public class RecyclerviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);

        ImageView head = (ImageView) findViewById(R.id.imageview);
        MyRecycleviewer recyclerView = (MyRecycleviewer) findViewById(R.id.recyclerview);
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 100; ++ i) {
            data.add(i + "");
        }
        //设置是否显示过度滑动的动画
        recyclerView.setOverScrollMode(View.OVER_SCROLL_ALWAYS);
        SimplerAdapter adapter = new SimplerAdapter(data);
        recyclerView.setAdapter(adapter);
        recyclerView.setmHead(head);
        MyLayoutManagert myLayoutManagert=new MyLayoutManagert(this);
        myLayoutManagert.setmHead(head);
        recyclerView.setLayoutManager(myLayoutManagert);
    }
}

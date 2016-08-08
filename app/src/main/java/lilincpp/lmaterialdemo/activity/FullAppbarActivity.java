package lilincpp.lmaterialdemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import lilincpp.lmaterialdemo.R;

/**
 * Created by lilin on 2016/7/21.
 * <p>
 * 将一个imageview填充至状态栏
 */
public class FullAppbarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //沉浸式状态栏
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        setContentView(R.layout.activity_fullappbar);


    }
}

package lilincpp.lmaterialdemo.tab;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import lilincpp.lmaterialdemo.R;

/**
 * Created by lilin on 2016/8/16.
 */
public class TabActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private Toolbar mToolbar;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        mToolbar= (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("Hello");
        mToolbar.setTitleTextColor(Color.WHITE);

    }
}

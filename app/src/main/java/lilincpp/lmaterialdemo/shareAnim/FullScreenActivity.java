package lilincpp.lmaterialdemo.shareAnim;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import lilincpp.lmaterialdemo.R;

/**
 * Created by lilin on 2016/8/1.
 */
public class FullScreenActivity extends AppCompatActivity {


    private ImageView mPhoto;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
        mPhoto = (ImageView) findViewById(R.id.photo);
    }

    public void click(View view) {
        switch (view.getId()) {
            case R.id.title:
                //
                Intent intent = new Intent(FullScreenActivity.this, FullScreenActivity2.class);
                ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(FullScreenActivity.this, mPhoto, "share");
                startActivity(intent, activityOptions.toBundle());
                break;
        }
    }
}

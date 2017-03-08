package lilincpp.lmaterialdemo.shareAnim;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import lilincpp.lmaterialdemo.R;

/**
 * Created by lilin on 2016/8/1.
 */
public class FullScreenActivity2 extends AppCompatActivity {

    private ImageView mPhoto;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        setContentView(R.layout.activity_fullscreen2);

        mPhoto = (ImageView) findViewById(R.id.photo);

        final Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.photo4);
        mPhoto.setImageBitmap(bitmap);

    }

//    @Override
//    public void onBackPressed() {
//        ObjectAnimator color = ObjectAnimator.ofArgb(mPhoto.getDrawable(), "tint",
//                0, ActivityCompat.getColor(this, R.color.tint));
//        color.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                finishAfterTransition();
//            }
//        });
//        color.start();
//    }
}

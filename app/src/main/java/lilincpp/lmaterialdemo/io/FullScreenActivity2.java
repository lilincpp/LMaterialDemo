package lilincpp.lmaterialdemo.io;

import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.transition.Transition;
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

        setContentView(R.layout.activity_fullscreen2);

        mPhoto = (ImageView) findViewById(R.id.photo);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.photo4);
        mPhoto.setImageBitmap(bitmap);

        getWindow().getEnterTransition().addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {

            }

            @Override
            public void onTransitionEnd(Transition transition) {
                ObjectAnimator color = ObjectAnimator.ofArgb(mPhoto.getDrawable(), "tint",
                        ActivityCompat.getColor(FullScreenActivity2.this, R.color.tint), 0);
                color.start();

                getWindow().getEnterTransition().removeListener(this);
            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }
        });
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

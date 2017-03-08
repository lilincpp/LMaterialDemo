package lilincpp.lmaterialdemo.pathAnim;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.ViewTreeObserver;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import lilincpp.lmaterialdemo.R;

/**
 * Created by lilin on 2017/3/8.
 */

public class PathAnimActivity extends AppCompatActivity {

    FloatingActionButton fab;
    ImageButton imageButton;
    RelativeLayout container;
    float endX, endY;
    boolean startScalAmim = true;
    boolean clickable = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pathanim);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        imageButton = (ImageButton) findViewById(R.id.imagebutton);
        container = (RelativeLayout) findViewById(R.id.container);
        imageButton.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                endX = imageButton.getX();
                endY = imageButton.getY();
                clickable = true;
            }
        });
        imageButton.setOnClickListener(mOnClickListener);
        fab.setOnClickListener(mOnClickListener);
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.fab:
                    if (clickable) start();
                    break;
                case R.id.imagebutton:
                    Log.e(TAG, "onClick imagebutton: ");
                    imageButton.animate().alpha(0f).setDuration(200)
                            .setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    Log.e(TAG, "imagebutton onAnimationEnd: ");
                                    fab.setVisibility(View.VISIBLE);
                                    container.setBackgroundColor(Color.WHITE);
                                    fab.setTranslationY(0);
                                    fab.setTranslationX(0);
                                    fab.animate().scaleX(1f).scaleY(1f).setDuration(800)
                                            .setListener(new AnimatorListenerAdapter() {
                                                @Override
                                                public void onAnimationEnd(Animator animation) {
                                                    startScalAmim = true;
                                                    fab.setImageResource(R.drawable.ic_zoom_out_map_black_24dp);
                                                }
                                            })
                                            .start();
                                }
                            })
                            .start();
                    break;
            }
        }
    };

    private void start() {
        Log.e(TAG, "start: ");
        AnimPath path = new AnimPath();
        path.moveTo(0, 0);
        path.cubicTo(-100, 200, -200, 500, -endX, endY);


        ObjectAnimator animator = ObjectAnimator.ofObject(this, "anim", new AnimTypeEvaluator(), path.getPoints().toArray());
        animator.setDuration(900);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float fraction = animation.getAnimatedFraction();
                if (fraction > 0.8 && startScalAmim) {
                    startScalAmim = false;
                    ViewPropertyAnimator scalanim = fab.animate().scaleXBy(64f).scaleYBy(64f).setDuration(800);
                    scalanim.setListener(listenerAdapter);
                    scalanim.start();
                }
            }
        });
        animator.start();
    }

    private static final String TAG = "PathAnimActivity";

    public void setAnim(AnimPoint point) {
        fab.setTranslationX(point.x);
        fab.setTranslationY(point.y);
    }

    private AnimatorListenerAdapter listenerAdapter = new AnimatorListenerAdapter() {
        @Override
        public void onAnimationEnd(Animator animation) {
            Log.e(TAG, "listenerAdapter onAnimationEnd: ");
            container.setBackgroundColor(ContextCompat.getColor(PathAnimActivity.this, R.color.colorAccent));
            fab.setVisibility(View.GONE);
//            imageButton.setVisibility(View.VISIBLE);
//            imageButton.animate().alpha(1f).setDuration(200).start();
            ObjectAnimator.ofFloat(imageButton, "alpha", 1f)
                    .setDuration(200)
                    .start();
        }

        @Override
        public void onAnimationStart(Animator animation) {
            fab.setImageDrawable(new BitmapDrawable());
        }
    };

}

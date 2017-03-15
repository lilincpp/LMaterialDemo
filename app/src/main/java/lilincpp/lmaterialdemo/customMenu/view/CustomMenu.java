package lilincpp.lmaterialdemo.customMenu.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by lilin on 2017/3/13.
 */

public class CustomMenu extends View {

    private Paint mPaint;
    private Path mPath;

    private int mArcHeight;
    private int ARC_MAX_HEIGHT;

    private MODE mode;

    private enum MODE {
        NONE, UP, DOWN
    }


    public CustomMenu(Context context) {
        super(context);
        init();
    }

    public CustomMenu(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
        mPath = new Path();

        ARC_MAX_HEIGHT = dp2px(100);

    }

    private static final String TAG = "CustomMenu";


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e(TAG, "onDraw: ");
        int currentY = 0;
        switch (mode) {
            case NONE:
                currentY = 0;
                break;
            case DOWN:
                currentY = ARC_MAX_HEIGHT;
                break;
            case UP:
                currentY = (int) (getHeight() * (1 - (float) mArcHeight / ARC_MAX_HEIGHT) + ARC_MAX_HEIGHT);
                break;
        }

        mPath.reset();
        mPath.moveTo(0, currentY);
        mPath.quadTo(getWidth() / 2, currentY - mArcHeight, getWidth(), currentY);
        mPath.lineTo(getWidth(), getHeight());
        mPath.lineTo(0, getHeight());
        mPath.close();
        canvas.drawPath(mPath, mPaint);
    }

    private int dp2px(int value) {
        return Math.round(getContext().getResources().getDisplayMetrics().density * value);
    }

    public void show() {
        if (animListener != null) {
            postDelayed(new Runnable() {
                @Override
                public void run() {
                    animListener.onEnd();
                }
            }, 320);
        }
        mode = MODE.UP;
        ValueAnimator animator = ValueAnimator.ofFloat(0, ARC_MAX_HEIGHT);
        animator.setDuration(320);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                mArcHeight = (int) value;
                Log.e(TAG, "onAnimationUpdate: " + mArcHeight);
                invalidate();
                if (mArcHeight == ARC_MAX_HEIGHT) {
                    bounce();
                }
            }
        });
        animator.start();
    }

    private void bounce() {
        mode = MODE.DOWN;
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(ARC_MAX_HEIGHT, 0);
        valueAnimator.setDuration(80);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                mArcHeight = (int) value;
                invalidate();
            }
        });
        valueAnimator.start();
    }

    public void setAnimListener(AnimListener animListener) {
        this.animListener = animListener;
    }

    AnimListener animListener;

    public interface AnimListener {
        void onEnd();
    }
}

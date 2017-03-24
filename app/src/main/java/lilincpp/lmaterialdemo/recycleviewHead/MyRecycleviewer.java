package lilincpp.lmaterialdemo.recycleviewHead;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import lilincpp.lmaterialdemo.R;

/**
 * Created by Colin on 2017/3/21.
 */

public class MyRecycleviewer extends RecyclerView {

    private static final String TAG = "MyRecycleviewer";

    public MyRecycleviewer(Context context) {
        super(context);
        init(context);
    }

    public MyRecycleviewer(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private ImageView mHead;

    private int originHeight;


    private void init(Context context) {
        originHeight = context.getResources().getDimensionPixelSize(R.dimen.image_height);
    }

    public void setmHead(ImageView mHead) {
        this.mHead = mHead;
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        Log.e(TAG, "overScrollBy: ");
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);
    }

    @Override
    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
        Log.e(TAG, "onOverScrolled: " + scrollX + "," + scrollY + "," + clampedX + "," + clampedY);
        if (scrollY < 0) {
            Log.e(TAG, "onOverScrolled: <0");
        } else {
            Log.e(TAG, "onOverScrolled: >0");
        }
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
    }

    private Animation mResetAnimation;

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        switch (e.getAction()) {
            case MotionEvent.ACTION_UP:
                ValueAnimator valueAnimator = ValueAnimator.ofFloat(mHead.getHeight(), originHeight);
                valueAnimator.setDuration(400);
                valueAnimator.setInterpolator(new DecelerateInterpolator());
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float value = (float) animation.getAnimatedValue();
                        mHead.getLayoutParams().height = (int) value;
                        mHead.requestLayout();
                    }
                });
                valueAnimator.start();
                break;
        }
        return super.onTouchEvent(e);
    }

}

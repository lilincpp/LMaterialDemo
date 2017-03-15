package lilincpp.lmaterialdemo.linearAnimFramework.framework.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;

/**
 * Created by lilin on 2017/3/15.
 */

public class MyScrollView extends ScrollView {

    MyLinearLayout mContent;

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        //mContent为MyLinearLayout
        mContent = (MyLinearLayout) getChildAt(0);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //fristView为第一个MyFrameLayout
        View fristView = mContent.getChildAt(0);
        fristView.getLayoutParams().height = getHeight();
    }

    private static final float MAX = 1f;
    private static final float MIN = 0f;

    private static float clamp(float value, float Max, float Min) {
        return Math.max(Math.min(value, Max), Min);
    }

    private static final String TAG = "MyScrollView";

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {/** t 为ScrollView顶部滑出去的部分*/
        super.onScrollChanged(l, t, oldl, oldt);
        final int scrollviewHeight = getHeight();
        for (int i = 0; i < mContent.getChildCount(); i++) {
            View child = mContent.getChildAt(i);
            if (! (child instanceof IAnimListener))
                continue;
            //child为MyFrameLayout
            IAnimListener listener = (IAnimListener) child;
            final int childTop = child.getTop();
            final int childHeight = child.getHeight();
            final int childTopFromCurrentScreen = childTop - t;
            if (childTopFromCurrentScreen <= scrollviewHeight) {
                //正在滑出来(未完全展示)，或者已经滑出来了(完全展示)
                final int showHeight = scrollviewHeight - childTopFromCurrentScreen;
                final float per = (float) showHeight / childHeight;
                listener.onChange(clamp(per, MAX, MIN));
            } else {
                //在屏幕的下面
                listener.onReset();
            }
        }
    }
}

package lilincpp.lmaterialdemo.recycleviewHead;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import lilincpp.lmaterialdemo.R;

/**
 * Created by Colin on 2017/3/23.
 */

public class MyLayoutManagert extends LinearLayoutManager {
    private static final String TAG = "MyLayoutManagert";

    public MyLayoutManagert(Context context) {
        super(context);
        init(context);
    }

    public MyLayoutManagert(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
        init(context);
    }

    public MyLayoutManagert(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    ImageView mHead;

    private int originHeight;

    public void setmHead(ImageView mHead) {
        this.mHead = mHead;
    }

    private void init(Context context) {
        originHeight = context.getResources().getDimensionPixelSize(R.dimen.image_height);
    }


    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int scrollRange = super.scrollVerticallyBy(dy, recycler, state);
        int overscroll = dy - scrollRange;
        Log.e(TAG, "scrollVerticallyBy: dy = " + dy + ",scrollRange = " + scrollRange);
        Log.e(TAG, "scrollVerticallyBy: " + overscroll);
        if (overscroll < 0) {
            //过度下拉
            mHead.getLayoutParams().height = mHead.getHeight() - overscroll;
            mHead.requestLayout();
        } else if (overscroll > 0) {
            //过度上拉

        } else {
            //正常滑动
            if (dy > 0) {
                //向上划
                if (mHead.getHeight() > originHeight) {
                    mHead.getLayoutParams().height = mHead.getHeight() - dy;
                    mHead.requestLayout();
                }
            }
        }
        return scrollRange;
    }

}

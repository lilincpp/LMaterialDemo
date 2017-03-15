package lilincpp.lmaterialdemo.linearAnimFramework.framework.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import lilincpp.lmaterialdemo.R;

/**
 * Created by lilin on 2017/3/15.
 */

public class MyLinearLayout extends LinearLayout {

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public MyLayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MyLayoutParams(getContext(), attrs);
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        MyLayoutParams myLayoutParams = (MyLayoutParams) params;
        if (needIncluding(myLayoutParams)) {
            MyFrameLayout frameLayout = new MyFrameLayout(child.getContext());
            frameLayout.addView(child);
            frameLayout.setmNeedAlphaAnim(myLayoutParams.mNeedAlphaAnim);
            frameLayout.setmNeedScaleXAnim(myLayoutParams.mNeedScaleXAnim);
            frameLayout.setmNeedScaleYAnim(myLayoutParams.mNeedScaleYAnim);
            frameLayout.setmTranslationFromColor(myLayoutParams.mTranslationFromColor);
            frameLayout.setmTranslationToColor(myLayoutParams.mTranslationToColor);
            frameLayout.setmTranslationMode(myLayoutParams.mTranslationMode);
            super.addView(frameLayout, index, params);
        } else {
            super.addView(child, index, params);
        }

    }

    private boolean needIncluding(MyLayoutParams params) {
        return (
                params.mNeedAlphaAnim
                        || params.mNeedScaleXAnim
                        || params.mNeedScaleYAnim
                        || (params.mTranslationFromColor != - 1 && params.mTranslationToColor != - 1)
                        || params.mTranslationMode != - 1
        );
    }

    static class MyLayoutParams extends LinearLayout.LayoutParams {

        private boolean mNeedAlphaAnim = false;
        private boolean mNeedScaleXAnim = false;
        private boolean mNeedScaleYAnim = false;
        private int mTranslationFromColor, mTranslationToColor;
        private int mTranslationMode;

        public MyLayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            TypedArray typedArray = c.obtainStyledAttributes(attrs, R.styleable.My);
            mNeedAlphaAnim = typedArray.getBoolean(R.styleable.My_lilin_alpha, false);
            mNeedScaleXAnim = typedArray.getBoolean(R.styleable.My_lilin_scaleX, false);
            mNeedScaleYAnim = typedArray.getBoolean(R.styleable.My_lilin_scaleY, false);
            mTranslationFromColor = typedArray.getInt(R.styleable.My_lilin_fromColor, - 1);
            mTranslationToColor = typedArray.getInt(R.styleable.My_lilin_toColor, - 1);
            mTranslationMode = typedArray.getInt(R.styleable.My_lilin_translation, - 1);
            typedArray.recycle();
        }
    }
}

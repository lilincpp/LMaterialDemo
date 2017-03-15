package lilincpp.lmaterialdemo.linearAnimFramework.framework.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;

/**
 * Created by lilin on 2017/3/15.
 */

public class MyFrameLayout extends FrameLayout implements IAnimListener {

    private static final int TRANSLATION_MODE_FROM_TOP = 0x01;
    private static final int TRANSLATION_MODE_FROM_BOTTOM = 0x02;
    private static final int TRANSLATION_MODE_FROM_LEFT = 0x04;
    private static final int TRANSLATION_MODE_FROM_RIGHT = 0x08;

    private boolean mNeedAlphaAnim = false;
    private boolean mNeedScaleXAnim = false;
    private boolean mNeedScaleYAnim = false;
    private int mTranslationFromColor, mTranslationToColor;
    private int mTranslationMode;

    public MyFrameLayout(@NonNull Context context) {
        super(context);
    }

    public MyFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setmNeedAlphaAnim(boolean mNeedAlphaAnim) {
        this.mNeedAlphaAnim = mNeedAlphaAnim;
    }

    public void setmNeedScaleXAnim(boolean mNeedScaleXAnim) {
        this.mNeedScaleXAnim = mNeedScaleXAnim;
    }

    public void setmNeedScaleYAnim(boolean mNeedScaleYAnim) {
        this.mNeedScaleYAnim = mNeedScaleYAnim;
    }

    public void setmTranslationFromColor(int mTranslationFromColor) {
        this.mTranslationFromColor = mTranslationFromColor;
    }

    public void setmTranslationToColor(int mTranslationToColor) {
        this.mTranslationToColor = mTranslationToColor;
    }

    public void setmTranslationMode(int mTranslationMode) {
        this.mTranslationMode = mTranslationMode;
    }

    private static final String TAG = "MyFrameLayout";

    @Override
    public void onChange(float per) {
        Log.e(TAG, "onChange: " + per + ",getWidth:" + getWidth());
        if (mNeedAlphaAnim) {
            setAlpha(per);
        }

        if (mNeedScaleXAnim) {
            setScaleX(per);
        }

        if (mNeedScaleYAnim) {
            setScaleY(per);
        }

        if (findFiled(TRANSLATION_MODE_FROM_TOP)) {
            setTranslationY(- getHeight() * (1 - per));
        }

        if (findFiled(TRANSLATION_MODE_FROM_BOTTOM)) {
            setTranslationY(getHeight() * (1 - per));
        }

        if (findFiled(TRANSLATION_MODE_FROM_LEFT)) {
            setTranslationX(- getWidth() * (1 - per));
            Log.e(TAG, "onChange: " + (- getWidth() * (1 - per)));
        }

        if (findFiled(TRANSLATION_MODE_FROM_RIGHT)) {
            setTranslationX(getWidth() * (1 - per));
            Log.e(TAG, "onChange: " + (getWidth() * (1 - per)));
        }
    }

    private boolean findFiled(int mask) {
        if (mTranslationMode == - 1)
            return false;
        return (mask & mTranslationMode) == mask;
    }

    @Override
    public void onReset() {
        if (mNeedAlphaAnim) {
            setAlpha(0f);
        }

        if (mNeedScaleXAnim) {
            setScaleX(0f);
        }

        if (mNeedScaleYAnim) {
            setScaleY(0f);
        }

        if (findFiled(TRANSLATION_MODE_FROM_TOP)) {
            setTranslationY(- getHeight());
        }

        if (findFiled(TRANSLATION_MODE_FROM_BOTTOM)) {
            setTranslationY(getHeight());
        }

        if (findFiled(TRANSLATION_MODE_FROM_LEFT)) {
            setTranslationX(- getWidth());
        }

        if (findFiled(TRANSLATION_MODE_FROM_RIGHT)) {
            setTranslationX(getWidth());
            Log.e(TAG, "onReset: TRANSLATION_MODE_FROM_RIGHT");
        }
    }
}

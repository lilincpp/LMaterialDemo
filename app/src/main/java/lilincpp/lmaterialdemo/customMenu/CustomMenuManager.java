package lilincpp.lmaterialdemo.customMenu;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import lilincpp.lmaterialdemo.R;
import lilincpp.lmaterialdemo.customMenu.view.CustomMenu;


/**
 * Created by lilin on 2017/3/13.
 */

public class CustomMenuManager {

    private ViewGroup mParent;
    private Context mContext;
    private View rootView;
    LinearLayout linearLayout;
    private CustomMenu customMenu;

    private CustomMenuManager(ViewGroup viewGroup) {
        mParent = viewGroup;
        mContext = viewGroup.getContext();
        rootView = LayoutInflater.from(mContext).inflate(R.layout.custom_menu, null, false);
        customMenu = (CustomMenu) rootView.findViewById(R.id.customMenu);
        linearLayout = (LinearLayout) rootView.findViewById(R.id.content);
        customMenu.setAnimListener(new CustomMenu.AnimListener() {
            @Override
            public void onEnd() {
                linearLayout.setVisibility(View.VISIBLE);
                animShowing = false;
            }
        });
    }


    public static CustomMenuManager maekView(View view) {
        CustomMenuManager customMenuManager = new CustomMenuManager(findSuitableParent(view));
        return customMenuManager;
    }

    private static final String TAG = "CustomMenuManager";
    private boolean animShowing = false;

    public void show() {
        if (animShowing)
            return;
        animShowing = true;
        if (rootView.getParent() != null) {
            mParent.removeView(rootView);
        }
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mParent.addView(rootView, layoutParams);
        customMenu.show();
    }

    public void dismiss() {
        if (animShowing)
            return;
        animShowing = true;
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(rootView, "translationY", 0, rootView.getHeight()).setDuration(560);
        objectAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                Log.e(TAG, "onAnimationEnd: ");
                mParent.removeView(rootView);
                rootView.setTranslationY(0);
                linearLayout.setVisibility(View.GONE);
                animShowing = false;
            }
        });
        objectAnimator.start();
    }

    public boolean isShowing() {
        return rootView.getParent() != null;
    }

    private static ViewGroup findSuitableParent(View view) {
        ViewGroup fallback = null;
        do {
            if (view instanceof FrameLayout) {
                if (view.getId() == android.R.id.content) {
                    fallback = (ViewGroup) view;
                }
            }
            if (view != null) {
                final ViewParent parent = view.getParent();
                view = (parent instanceof View) ? (View) parent : null;
            }
        } while (view != null);
        return fallback;
    }
}

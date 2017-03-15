package lilincpp.lmaterialdemo.customMenu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewParent;

import lilincpp.lmaterialdemo.R;
import lilincpp.lmaterialdemo.customMenu.view.CustomMenu;

/**
 * Created by lilin on 2017/3/15.
 */

public final class RootViewLayout {

    View rootView;
    CustomMenu customMenu;
    private static volatile RootViewLayout mRootViewLayout;

    ViewParent getParent() {
        return rootView.getParent();
    }

    RootViewLayout(Context context) {
        rootView = LayoutInflater.from(context).inflate(R.layout.custom_menu, null, false);
        customMenu = (CustomMenu) rootView.findViewById(R.id.customMenu);
    }

    public static RootViewLayout newInstance(Context context) {
        RootViewLayout temp = mRootViewLayout;
        if (temp == null) {
            synchronized (RootViewLayout.class) {
                temp = mRootViewLayout;
                if (temp == null) {
                    temp = new RootViewLayout(context);
                    mRootViewLayout = temp;
                }
            }
        }
        return temp;
    }
}

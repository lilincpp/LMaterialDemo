package lilincpp.lmaterialdemo.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import lilincpp.lmaterialdemo.R;

/**
 * Created by lilin on 2016/7/21.
 */
public class NormalViewFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_normal_view, container, false);
        return v;
    }

    private boolean mLoved = false;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        /**
         * style="?android:attr/borderlessButtonStyle"  无边框的样式
         * android:tint="@android:color/darker_gray" 为图片设置色调(由于我们用的是矢量图，因此此属性将填充整个图片)
         * app:srcCompat="@drawable/ic_favorite_black_24dp" 设置图片
         *
         */
        view.findViewById(R.id.ibtn_love).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageButton imageButton = (ImageButton) view;
                if (mLoved) {
                    //取消滤镜，显示imagebutton初始的颜色
                    imageButton.setColorFilter(null);
                } else {
                    //增加红色的滤镜
                    imageButton.setColorFilter(Color.RED);
                }
                mLoved = !mLoved;
            }
        });

    }
}

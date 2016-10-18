package lilincpp.lmaterialdemo.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;

import lilincpp.lmaterialdemo.R;
import lilincpp.lmaterialdemo.view.MaterialProgressDrawable;

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
        setupMaterialProgress(view);

    }

    MaterialProgressDrawable p1, p2, p3;

    private void setupMaterialProgress(View view) {

        final int backgroundColor = 0xFFFAFAFA;

        ImageView imageView1 = (ImageView) view.findViewById(R.id.p1);
        ImageView imageView2 = (ImageView) view.findViewById(R.id.p2);
        ImageView imageView3 = (ImageView) view.findViewById(R.id.p3);

        p1 = new MaterialProgressDrawable(getActivity(), imageView1);
        p2 = new MaterialProgressDrawable(getActivity(), imageView2);
        p3 = new MaterialProgressDrawable(getActivity(), imageView3);

        //设置圈圈的各种大小
        p1.setBackgroundColor(backgroundColor);
        p2.setBackgroundColor(backgroundColor);
        p3.setBackgroundColor(backgroundColor);

        p1.setColorSchemeColors(new int[]{Color.BLUE, Color.RED, Color.GREEN, Color.YELLOW});
        p1.updateSizes(MaterialProgressDrawable.LARGE);

        p2.setColorSchemeColors(new int[]{Color.BLUE, Color.RED, Color.GREEN, Color.YELLOW});
        p2.updateSizes(MaterialProgressDrawable.DEFAULT);

        p3.setColorSchemeColors(new int[]{Color.BLUE, Color.RED, Color.GREEN, Color.YELLOW});
        p3.updateSizes(MaterialProgressDrawable.DEFAULT);

        imageView1.setImageDrawable(p1);
        imageView2.setImageDrawable(p2);
        imageView3.setImageDrawable(p3);

//        start();
        p1.start();
        p2.start();
        p3.start();
    }

    private ValueAnimator valueAnimator;

    private void start() {
        if (valueAnimator == null) {
            valueAnimator = valueAnimator.ofFloat(0f, 1f);
            valueAnimator.setDuration(600);
//            valueAnimator.setRepeatMode(ValueAnimator.RESTART);
            valueAnimator.setInterpolator(new DecelerateInterpolator());
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float n = (float) animation.getAnimatedValue();
                    //圈圈的旋转角度
                    p1.setProgressRotation(n * 0.5f);
                    p2.setProgressRotation(n * 0.5f);
                    p3.setProgressRotation(n * 0.5f);
                    //圈圈周长，0f-1F
                    p1.setStartEndTrim(0f, n * 0.8f);
                    p2.setStartEndTrim(0f, n * 0.8f);
                    p3.setStartEndTrim(0f, n * 0.8f);
                    //箭头大小，0f-1F
                    p1.setArrowScale(n);
                    //透明度，0-255
                    p1.setAlpha((int) (255 * n));
                    p2.setAlpha((int) (255 * n));
                    p3.setAlpha((int) (255 * n));

                }
            });
            valueAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                }
            });
        }

        if (!valueAnimator.isRunning()) {
            p1.showArrow(true);
            valueAnimator.start();

        }
    }
}

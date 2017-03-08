package lilincpp.lmaterialdemo.pathAnim;

import android.animation.TypeEvaluator;

/**
 * Created by lilin on 2017/3/8.
 */

public class AnimTypeEvaluator implements TypeEvaluator<AnimPoint> {
    private static final String TAG = "AnimTypeEvaluator";

    @Override
    public AnimPoint evaluate(float fraction, AnimPoint startValue, AnimPoint endValue) {
//        Log.e(TAG, "evaluate: fraction = " + fraction);
//        Log.e(TAG, "evaluate: stat = " + startValue.toString() + "\n end = " + endValue.toString());
        float x = 0, y = 0;
        final float t = fraction;
        final float k = 1 - t;
        switch (endValue.mode) {
            case CUBIC:
                x = (float) (startValue.x * Math.pow(k, 3)
                        + 3 * endValue.x1 * t * Math.pow(k, 2)
                        + 3 * endValue.x2 * Math.pow(t, 2) * k
                        + endValue.x3 * Math.pow(t, 3));
                y = (float) (startValue.y * Math.pow(k, 3)
                        + 3 * endValue.y1 * t * Math.pow(k, 2)
                        + 3 * endValue.y2 * Math.pow(t, 2) * k
                        + endValue.y3 * Math.pow(t, 3));
                break;
            case LINE:
                x = startValue.x + endValue.x * fraction;
                y = startValue.y + endValue.y * fraction;
                break;
            default:
                break;
        }
        return AnimPoint.move(x, y);
    }
}

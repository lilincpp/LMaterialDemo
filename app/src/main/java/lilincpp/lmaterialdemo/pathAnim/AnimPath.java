package lilincpp.lmaterialdemo.pathAnim;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lilin on 2017/3/8.
 */

public class AnimPath {
    private List<AnimPoint> mPoints = new ArrayList<>();

    public void moveTo(float x, float y) {
        mPoints.add(AnimPoint.move(x, y));
    }

    public void lineTo(float x, float y) {
        mPoints.add(AnimPoint.line(x, y));
    }

    public void cubicTo(float x1, float y1, float x2, float y2, float x3, float y3) {
        mPoints.add(AnimPoint.cubic(x1, y1, x2, y2, x3, y3));
    }

    public List<AnimPoint> getPoints() {
        return mPoints;
    }
}

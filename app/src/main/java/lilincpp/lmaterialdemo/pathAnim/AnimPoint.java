package lilincpp.lmaterialdemo.pathAnim;

/**
 * Created by lilin on 2017/3/8.
 */

public class AnimPoint {

    public enum MODE {
        MOVE,
        LINE,
        CUBIC
    }

    MODE mode;

    float x;
    float y;

    float x1, x2, x3;
    float y1, y2, y3;

    private AnimPoint(MODE mode, float x, float y) {
        this.mode = mode;
        this.x = x;
        this.y = y;
    }

    public AnimPoint(MODE mode, float x1, float x2, float x3, float y1, float y2, float y3) {
        this.mode = mode;
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
        this.y1 = y1;
        this.y2 = y2;
        this.y3 = y3;
    }

    public MODE getMode() {
        return mode;
    }

    public static AnimPoint move(float x, float y) {
        return new AnimPoint(MODE.MOVE, x, y);
    }

    public static AnimPoint line(float x, float y) {
        return new AnimPoint(MODE.LINE, x, y);
    }

    public static AnimPoint cubic(float x1, float y1, float x2, float y2, float x3, float y3) {
        return new AnimPoint(MODE.CUBIC, x1, x2, x3, y1, y2, y3);
    }

    @Override
    public String toString() {
        return "AnimPoint{" +
                "mode=" + mode +
                ", x=" + x +
                ", y=" + y +
                ", x1=" + x1 +
                ", x2=" + x2 +
                ", x3=" + x3 +
                ", y1=" + y1 +
                ", y2=" + y2 +
                ", y3=" + y3 +
                '}';
    }
}

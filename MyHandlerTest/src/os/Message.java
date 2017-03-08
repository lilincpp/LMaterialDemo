package os;

/**
 * Created by lilin on 2017/3/6.
 */
public class Message {
    Handler targe;
    int what;
    Object obj;

    public Message(int what, Object obj) {
        this.what = what;
        this.obj = obj;
    }

    @Override
    public String toString() {
        return "Message{" +
                "what=" + what +
                ", obj=" + obj +
                '}';
    }
}

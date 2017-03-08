package os;

/**
 * Created by lilin on 2017/3/6.
 */
public class Looper {

    private static final ThreadLocal<Looper> sThreadLocal = new ThreadLocal<>();
    MessageQueue mQueue;

    private Looper() {
        mQueue = new MessageQueue();
    }

    public static Looper myLooper() {
        return sThreadLocal.get();
    }

    public static void prepared() {
        if (myLooper() != null) {
            throw new RuntimeException("一个线程只有一个Looper");
        }
        sThreadLocal.set(new Looper());
    }

    public static void loop() {
        final Looper looper = myLooper();
        if (looper == null) {
            throw new RuntimeException("没有调用prepared");
        }
        MessageQueue queue = looper.mQueue;
        for (; ; ) {
            Message message = queue.next();
            if (message != null) {
                message.targe.dispatchMessage(message);
            }
        }
    }
}

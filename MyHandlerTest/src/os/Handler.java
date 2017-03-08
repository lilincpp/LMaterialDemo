package os;

/**
 * Created by lilin on 2017/3/6.
 */
public class Handler {

    private Looper mLooper;
    private MessageQueue mQueue;

    public Handler() {
        mLooper = Looper.myLooper();
        mQueue = mLooper.mQueue;
    }

    public void handleMessage(Message msg) {

    }

    public void dispatchMessage(Message msg) {
        handleMessage(msg);
    }

    public void sendMessage(Message msg) {
        enqueueMessage(mQueue, msg);
    }

    private void enqueueMessage(MessageQueue queue, Message msg) {
        msg.targe = this;
        queue.enqueueMessage(msg);
    }
}

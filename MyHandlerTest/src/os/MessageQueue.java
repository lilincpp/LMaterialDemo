package os;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by lilin on 2017/3/6.
 */
class MessageQueue {

    private static final int MAX = 50;
    private final Message[] messages;
    private int inIndex, outIndex;
    private int count;

    private Lock lock;
    private Condition noEmpty, noFull;

    MessageQueue() {
        messages = new Message[MAX];
        inIndex = 0;
        outIndex = 0;
        count = 0;
        lock = new ReentrantLock();
        noEmpty = lock.newCondition();
        noFull = lock.newCondition();
    }

    void enqueueMessage(Message msg) {
        try {
            lock.lock();
            if (count == messages.length) {
                noFull.await();
            }
            messages[inIndex] = msg;
            inIndex = (++inIndex == MAX) ? 0 : inIndex;
            count++;
            noEmpty.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    Message next() {
        Message msg = null;
        try {
            lock.lock();
            if (count == 0) {
                noEmpty.await();
            }
            msg = messages[outIndex];
            outIndex = (++outIndex == MAX) ? 0 : outIndex;
            count--;
            noFull.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return msg;
    }
}

package test;

import os.Handler;
import os.Looper;
import os.Message;

import java.util.UUID;

/**
 * Created by lilin on 2017/3/6.
 */
public class Test {

    public static void main(String[] args) {
        Looper.prepared();
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                System.out.println(Thread.currentThread().getName() + " get Message = " + msg.toString());
            }
        };

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String m;
                    synchronized (Test.class) {
                        m = UUID.randomUUID().toString();
                    }
                    Message message = new Message(1, m);
                    handler.sendMessage(message);
                    System.out.println(Thread.currentThread().getName() + " send Message = " + message.toString());

                }
            }).start();
        }
        Looper.loop();
    }
}

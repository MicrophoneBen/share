package com.suntek.log4j2.threads;

import java.io.Serializable;

/**
 * @author :wuyuhao
 * @version： 2019年07月18日
 * @since： 2019年07月18日
 */
public class Thread2 implements Runnable, Serializable {
    private int taskId;
    public Thread2(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"--taskId: "+taskId);
    }
}

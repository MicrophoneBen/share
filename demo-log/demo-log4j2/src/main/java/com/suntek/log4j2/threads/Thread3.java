package com.suntek.log4j2.threads;

import java.util.concurrent.Callable;

/**
 * @author :wuyuhao
 * @param <T>
 * @version： 2019年07月22日
 * @since： 2019年07月22日
 */
public class Thread3<T> implements Callable<T> {

    @Override
    public T call() throws Exception {
        System.out.println("Thread3 running...");
        return null;
    }
}

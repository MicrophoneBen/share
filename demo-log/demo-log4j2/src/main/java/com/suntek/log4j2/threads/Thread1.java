package com.suntek.log4j2.threads;

import com.suntek.log4j2.util.MapUtil;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author :wuyuhao
 * @version： 2019年07月18日
 * @since： 2019年07月18日
 */
public class Thread1 extends Thread {
    private ConcurrentHashMap<String,Object> concurrentHashMap;
    private int key;

    public Thread1(ConcurrentHashMap<String, Object> concurrentHashMap,int key) {
        concurrentHashMap.put(Integer.toString(key),"thread"+key);
        this.key = key;
        this.concurrentHashMap = concurrentHashMap;
    }

    @Override
    public void run() {
        for (int i =0 ;i<5;i++) {
            MapUtil.getThreadName(concurrentHashMap,i,key);
        }
        System.out.println(concurrentHashMap);
    }


}

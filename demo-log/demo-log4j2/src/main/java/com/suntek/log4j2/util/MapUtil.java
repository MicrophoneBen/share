package com.suntek.log4j2.util;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author :wuyuhao
 * @version： 2019年07月18日
 * @since： 2019年07月18日
 */
public class MapUtil {
    public  static boolean getThreadName(ConcurrentHashMap<String, Object> concurrentHashMap, int i,int key) {
        try {
            if (i != 2) {
                concurrentHashMap.put(concurrentHashMap.get(Integer.toString(key)) + " [" + i + "] ", "success");
                System.out.println(concurrentHashMap.get(Integer.toString(key)) + " [" + i + "] success");
            }else {
                int a = 1/0;
            }
            return true;
        } catch (Exception e) {
            concurrentHashMap.put(concurrentHashMap.get(Integer.toString(key)) + " [" + i + "] ", "failed");
            System.out.println(concurrentHashMap.get(Integer.toString(key)) + " [" + i + "] failed");
            e.printStackTrace();
            return false;
        }
    }
}

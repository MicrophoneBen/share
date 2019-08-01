package com.suntek.log4j2.test;

import java.util.Scanner;

/**
 * @author： wuyuhao
 * @version： 2019/03/ 09
 * @since： 2019/03/09
 */
public class Demo {
    public static void main(String[] args) {
        while (true){
            System.out.println("姜佳敏爱吴宇浩吗?y/n");
            Scanner sc = new Scanner(System.in);
            String next = sc.next();
            if(next.equals("y")){
                System.out.println("吴宇浩也爱姜佳敏");
                return;
            }
            System.out.println("傻逼再选");
        }
    }
}

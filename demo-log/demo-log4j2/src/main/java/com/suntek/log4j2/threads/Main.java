package com.suntek.log4j2.threads;

import java.util.Map;
import java.util.concurrent.*;

/**
 * @author :wuyuhao
 * @version： 2019年07月19日
 * @since： 2019年07月19日
 */
public class Main {
    /*public ThreadPoolExecutor(int corePoolSize,
                              int maximumPoolSize,
                              long keepAliveTime,
                              TimeUnit unit,
                              BlockingQueue<Runnable> workQueue,
                              RejectedExecutionHandler handler) {
        this(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
                Executors.defaultThreadFactory(), handler);
    }*/
    /** 线程数
     *  1.如果此时线程池中的数量小于corePoolSize，即使线程池中的线程都处于空闲状态，也要创建新的线程来处理被添加的任务。
     *  2.如果此时线程池中的数量等于 corePoolSize，但是缓冲队列 workQueue未满，那么任务被放入缓冲队列。
     *  3.如果此时线程池中的数量大于corePoolSize，缓冲队列workQueue满，并且线程池中的数量小于maximumPoolSize，建新的线程来处理被添加的任务。
     *  4.如果此时线程池中的数量大于corePoolSize，缓冲队列workQueue满，并且线程池中的数量等于maximumPoolSize，那么通过 handler所指定的策略来处理此任务。
     * 也就是：处理任务的优先级为：
     *    核心线程corePoolSize、任务队列workQueue、最大线程maximumPoolSize，如果三者都满了，使用handler处理被拒绝的任务。
     *    当线程池中的线程数量大于 corePoolSize时，如果某线程空闲时间超过keepAliveTime，线程将被终止。这样，线程池可以动态的调整池中的线程数。*/
    private static int corePoolSize = 5;
    /** 线程池最大线程数，表示在线程池中最多能创建多少个线程。如果当线程池中的数量到达这个数字时，新来的任务会抛出异常。 */
    private static int maximumPoolSize = 10;
    /** 表示线程没有任务执行时最多能保持多少时间会停止，然后线程池的数目维持在corePoolSize。*/
    private static long keepAliveTime = 10;
    /** 参数keepAliveTime的时间单位*/
    private static TimeUnit  unit = TimeUnit.SECONDS;
    /** 一个阻塞队列，用来存储等待执行的任务，如果当前对线程的需求超过了corePoolSize大小，才会放在这里
     *  1. ArrayBlockingQueue 是一个有边界的阻塞队列，它的内部实现是一个数组。它的容量在初始化时就确定不变
     *
     *  2. LinkedBlockingQueue：阻塞队列大小的配置是可选的，其内部实现是一个链表。
     *
     *  3. PriorityBlockingQueue：是一个没有边界的队列，所有插入到PriorityBlockingQueue的对象必须实现java.lang.Comparable接口，
     *  队列优先级的排序就是按照我们对这个接口的实现来定义的。
     *
     *  4. SynchronousQueue队列内部仅允许容纳一个元素。当一个线程插入一个元素后会被阻塞，除非这个元素被另一个线程消费。
     *  */
    private static ArrayBlockingQueue <Runnable> workQueue = new ArrayBlockingQueue<>(100);
    /** 线程工厂，主要用来创建线程，比如指定线程的名字 */
    private static ThreadFactory carThreadFactory = new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        }
    };
    /** 如果线程池已满，新的任务处理方式 */
    private static ThreadPoolExecutor.AbortPolicy handler = new ThreadPoolExecutor.AbortPolicy();

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                unit,
                workQueue,
                carThreadFactory,
                handler);
        for (int i =0;i<5;i++) {
            threadPoolExecutor.execute(new Thread2(i));
        }
        threadPoolExecutor.shutdown();
        FutureTask<Map<String,Object>> ft = new FutureTask(new Thread3<>());
        ft.run();
    }
}

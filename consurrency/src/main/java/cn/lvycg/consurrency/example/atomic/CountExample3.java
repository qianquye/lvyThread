package cn.lvycg.consurrency.example.atomic;

import cn.lvycg.consurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * Created by cg on 2018/10/13.
 * CAS（CompareAndSwap）的原理，就是拿当前的值与底层的值作对比，如果相同就是做相加操作，
 * 不同就会继续循环操作，直至相同才执行操作完成来确保线程安全
 * LongAdder的用法 java8新增的
 *
 *  LongAdder在AtomicLong的基础上将单点的更新压力分散到各个节点，在低并发的时候通过对base的
 * 直接更新可以很好的保障和AtomicLong的性能基本保持一致，而在高并发的时候通过分散提高了性能。
 *  缺点是LongAdder在统计的时候如果有并发更新，可能导致统计的数据有误差。
 *
 */
@Slf4j
@ThreadSafe
public class CountExample3 {

    //请求总数
    public static int clientTotal = 5000;
    //并发总数
    public static int threadTotal = 200;

    public static LongAdder count = new LongAdder();

    public static void main(String[] args) throws Exception{
        //定义一个线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        //信号量；只有获得许可，才能进入
        final Semaphore semaphore = new Semaphore(threadTotal);
        //记数器
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for(int i = 0 ; i < clientTotal; i++){
            executorService.execute(() ->{
                try{
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (Exception e){
                    log.error("semaphore:"+e);
                }
                countDownLatch.countDown();
            });
        }

        countDownLatch.await(); //保证contDownLatch减为0； 这个await需求看一下
        executorService.shutdown(); //关掉当前线程池
        log.info("count:{}",count);

    }

    public static void add(){
       count.increment();

    }
}

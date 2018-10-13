package cn.lvycg.consurrency.example.count;

import cn.lvycg.consurrency.annotations.NotThreadSafe;
import cn.lvycg.consurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by cg on 2018/10/13.
 * CAS（CompareAndSwap）的原理，就是拿当前的值与底层的值作对比，如果相同就是做相加操作，
 * 不同就会继续循环操作，直至相同才执行操作完成来确保线程安全
 *
 */
@Slf4j
@ThreadSafe
public class CountExample2 {

    //请求总数
    public static int clientTotal = 5000;
    //并发总数
    public static int threadTotal = 200;

    public static AtomicInteger count = new AtomicInteger(0);

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
        log.info("count:{}",count.get());

    }

    public static void add(){
        count.incrementAndGet(); // 先加再获取
        //count.getAndIncrement();//先获取再加
    }
}

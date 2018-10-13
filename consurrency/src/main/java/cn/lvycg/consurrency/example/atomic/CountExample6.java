package cn.lvycg.consurrency.example.atomic;

import cn.lvycg.consurrency.annotations.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * Created by cg on 2018/10/13.
 * CAS（CompareAndSwap）的原理，就是拿当前的值与底层的值作对比，如果相同就是做相加操作，
 * 不同就会继续循环操作，直至相同才执行操作完成来确保线程安全
 * AtomicBoolean 可以让某断代码只执行一次，不会重复执行
 */
@Slf4j
@ThreadSafe
public class CountExample6 {
    private static AtomicBoolean atomicBoolean = new AtomicBoolean(false);

    public static int clientTotal = 5000;
    public static int ThreadTotal = 200;

    public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(ThreadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for(int i = 0; i < clientTotal; i++){
           executorService.execute(() ->{
               try {
                   semaphore.acquire();
                   test();
                   semaphore.release();
               } catch (InterruptedException e) {
                 log.error("exception:"+e);
               }
               countDownLatch.countDown();
           });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("atomicBoolean:{}",atomicBoolean.get());
    }

    public static void test(){
        if(atomicBoolean.compareAndSet(false,true)){
            log.info("test");
        }
    }
}

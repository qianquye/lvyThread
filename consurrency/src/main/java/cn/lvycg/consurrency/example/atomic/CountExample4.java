package cn.lvycg.consurrency.example.atomic;

import cn.lvycg.consurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.LongAdder;

/**
 * Created by cg on 2018/10/13.
 * CAS（CompareAndSwap）的原理，就是拿当前的值与底层的值作对比，如果相同就是做相加操作，
 * 不同就会继续循环操作，直至相同才执行操作完成来确保线程安全
 * AtomicReference 的实例
 */
@Slf4j
@ThreadSafe
public class CountExample4 {

    private static AtomicReference<Integer> count = new AtomicReference<Integer>(0);

    public static void main(String[] args) throws Exception{
       count.compareAndSet(0,2);
       count.compareAndSet(0,1);
       count.compareAndSet(1,3);
       count.compareAndSet(2,4);
       count.compareAndSet(3,5);
       log.info("count:{}",count.get());
    }

}

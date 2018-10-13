package cn.lvycg.consurrency;

import cn.lvycg.consurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by cg on 2018/10/13.
 */
@Slf4j
@NotThreadSafe
public class ConcurrencyTest {

    //请求总数
    public static int clientTotal = 5000;
    //并发总数
    public static int threadTotal = 200;

    public static int count = 0;

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
        count ++;
    }
}

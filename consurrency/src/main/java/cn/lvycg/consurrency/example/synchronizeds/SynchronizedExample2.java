package cn.lvycg.consurrency.example.synchronizeds;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by cg on 2018/10/13.
 * synchronized 修饰静态方法块与类
 *  只能执行完当前线程才能去执行下一个线程
 */
@Slf4j
public class SynchronizedExample2 {

    public static void test1(int num){
        synchronized (SynchronizedExample2.class){
            for(int i = 0 ; i < 10 ;i++){
                log.info("test1 {}:{}",num,i);
            }
        }
    }
    public  synchronized static void  test2(int num){
        for(int i = 0 ; i < 10 ;i++){
            log.info("test2 {}:{}",num,i);
        }
    }

    public static void  main(String[] args){
        SynchronizedExample2 synchronizedExample1 = new SynchronizedExample2();
        ExecutorService executorService = Executors.newCachedThreadPool();
        SynchronizedExample2 synchronizedExample2 = new SynchronizedExample2();
        executorService.execute(() ->{
            test1(1);
        });
        executorService.execute(() ->{
           test1(2);
        });
    }
}

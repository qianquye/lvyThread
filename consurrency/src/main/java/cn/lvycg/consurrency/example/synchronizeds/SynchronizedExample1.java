package cn.lvycg.consurrency.example.synchronizeds;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by cg on 2018/10/13.
 * synchronized 修饰代码块与方法
 *  只作用于当前对象，不同的线程可交替执行
 *
 *  如果当前类是父类，子类重写是不会带上synchronized的，如果需要，得自行加上去
 */
@Slf4j
public class SynchronizedExample1 {

    public void test1(int num){
        synchronized (this){
            for(int i = 0 ; i < 10 ;i++){
                log.info("test1 {}:{}",num,i);
            }
        }
    }
    public synchronized void  test2(int num){
        for(int i = 0 ; i < 10 ;i++){
            log.info("test2 {}:{}",num,i);
        }
    }

    public static void  main(String[] args){
        SynchronizedExample1 synchronizedExample1 = new SynchronizedExample1();
        ExecutorService executorService = Executors.newCachedThreadPool();
        SynchronizedExample1 synchronizedExample2 = new SynchronizedExample1();
        executorService.execute(() ->{
            synchronizedExample1.test2(1);
        });
        executorService.execute(() ->{
            synchronizedExample2.test2(2);
        });
    }
}

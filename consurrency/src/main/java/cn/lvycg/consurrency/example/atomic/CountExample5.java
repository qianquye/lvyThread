package cn.lvycg.consurrency.example.atomic;

import cn.lvycg.consurrency.annotations.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by cg on 2018/10/13.
 * CAS（CompareAndSwap）的原理，就是拿当前的值与底层的值作对比，如果相同就是做相加操作，
 * 不同就会继续循环操作，直至相同才执行操作完成来确保线程安全
 * AtomicIntegerFileUpdater 作用：指定更新某个实例字段的值
 */
@Slf4j
@ThreadSafe
public class CountExample5 {

    /**
     *  更新的字段必须用volatile来修饰，还不能用static
     */
    public static AtomicIntegerFieldUpdater atomicIntegerFieldUpdater =
            AtomicIntegerFieldUpdater.newUpdater(CountExample5.class, "count");
    @Getter
    public volatile int count = 100;

    public static void main(String[] args) throws Exception{
        CountExample5 countExample5 = new CountExample5();
        if(atomicIntegerFieldUpdater.compareAndSet(countExample5,100,200)){
            log.info("update success1: this count is{}",countExample5.getCount());
        }
        if(atomicIntegerFieldUpdater.compareAndSet(countExample5,100,200)){
            log.info("update success2: this count is{}",countExample5.getCount());
        }else{
            log.info("update fail,the count is {}",countExample5.getCount());
        }
    }

}

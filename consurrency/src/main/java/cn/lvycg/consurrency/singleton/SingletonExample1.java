package cn.lvycg.consurrency.singleton;

import cn.lvycg.consurrency.annotations.NotThreadSafe;

/**
 * Created by cg on 2018/10/14.
 * 懒汉模式
 */

@NotThreadSafe
public class SingletonExample1 {

    private static SingletonExample1 singletonExample1 = null;
    private SingletonExample1(){
    }

    /**
     *  通过静态方法
     * @return
     */
    public static SingletonExample1 getInstance(){
        if (singletonExample1 == null) {
            singletonExample1 = new SingletonExample1();
        }
        return singletonExample1;
    }
}

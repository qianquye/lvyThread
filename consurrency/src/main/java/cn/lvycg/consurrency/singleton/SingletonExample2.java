package cn.lvycg.consurrency.singleton;

import cn.lvycg.consurrency.annotations.NotThreadSafe;
import cn.lvycg.consurrency.annotations.ThreadSafe;

/**
 * Created by cg on 2018/10/14.
 * 饿汉模式
 */

@ThreadSafe
public class SingletonExample2 {

    private static SingletonExample2 singletonExample1 = new SingletonExample2();
    private SingletonExample2(){

    }
    /**
     *  通过静态方法
     * @return
     */
    public static SingletonExample2 getInstance(){
        return singletonExample1;
    }


}

package cn.lvycg.consurrency.singleton;

import cn.lvycg.consurrency.annotations.ThreadSafe;

/**
 * Created by cg on 2018/10/14.
 * 饿汉模式,通静态块来初始化
 */

@ThreadSafe
public class SingletonExample6 {
    /**
     * 这里注意了，静态块与静态变量的位置，当静态变量位置在静态块之后时，singletonExample1的实例对象为
     * null.原因是静态块实例化后，静态变量又把它设置为null了
     */
    private static SingletonExample6 singletonExample1 = null;
    static {
        singletonExample1 = new SingletonExample6();
    }
    private SingletonExample6(){

    }
    /**
     *  通过静态方法
     * @return
     */
    public static SingletonExample6 getInstance(){
        return singletonExample1;
    }


}

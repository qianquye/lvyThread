package cn.lvycg.consurrency.singleton;

import cn.lvycg.consurrency.annotations.NotRecommend;
import cn.lvycg.consurrency.annotations.NotThreadSafe;
import cn.lvycg.consurrency.annotations.ThreadSafe;

/**
 * Created by cg on 2018/10/14.
 * 懒汉模式 通过在方法上加上synchronized,但会对资源过多的浪费
 */

@ThreadSafe
@NotRecommend
public class SingletonExample3 {

    private static SingletonExample3 singletonExample1 = null;
    private SingletonExample3(){
    }

    /**
     *  通过静态方法
     * @return
     */
    public synchronized static SingletonExample3 getInstance(){
        if (singletonExample1 == null) {
            singletonExample1 = new SingletonExample3();
        }
        return singletonExample1;
    }
}

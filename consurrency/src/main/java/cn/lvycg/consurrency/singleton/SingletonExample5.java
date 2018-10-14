package cn.lvycg.consurrency.singleton;

import cn.lvycg.consurrency.annotations.NotRecommend;
import cn.lvycg.consurrency.annotations.NotThreadSafe;
import cn.lvycg.consurrency.annotations.ThreadSafe;

/**
 * Created by cg on 2018/10/14.
 * 懒汉模式（双重同步检测机制） 通过在方法内加上synchronized,但是会造成线程不安全
 *  原因：（在多线程的过程中，会发生指令重排）
 *   在实例化singletonExample1 = new SingletonExample4();会有哪些操作?
 *   1、 memory = allocate()  分配对象的内存空间
 *   2、ctorInstance() 初始化对象
 *   3、 instance = memory 设置instance指向刚分配的内存
 *   在单线程中，就是发生了指令重排，也不会有什么影响的，但是在多线程中就不一样了，如下：
 *   1、 memory = allocate()  分配对象的内存空间
 *   3、 instance = memory 设置instance指向刚分配的内存
 *   2、ctorInstance() 初始化对象
 *   如果jvm与cpu优化，2与3发生指令重排时
 *   if (singletonExample1 == null) { 线程 2
 *    synchronized(SingletonExample4.class) {
 *      if (singletonExample1 == null) { 线程1
 *      singletonExample1 = new SingletonExample4();
 *     }
 *    }
 *  }
 *   线程1刚设置instance指向刚分配的内存空间
 *   线程2就进入到判断不为null
 *   就直接把instance的值给返回了回去，但是instance并没初化对象，如果调用就会出现问题
 *   这时可以通过volatile来避免指令重排
 */

@ThreadSafe
public class SingletonExample5 {
    //单例对象，volatile+双重检测
    private volatile static SingletonExample5 singletonExample1 = null;
    private SingletonExample5(){
    }

    /**
     *  通过静态方法
     * @return
     */
    public  static SingletonExample5 getInstance(){
        if (singletonExample1 == null) { //
            synchronized(SingletonExample5.class) { //同步锁
                if (singletonExample1 == null) {
                    singletonExample1 = new SingletonExample5();
                }
            }
        }
        return singletonExample1;
    }
}

package cn.lvycg.consurrency.singleton;

import cn.lvycg.consurrency.annotations.Recommend;
import cn.lvycg.consurrency.annotations.ThreadSafe;

/**
 * Created by cg on 2018/10/14.
 * 枚举模式：单例实例化
 */

@ThreadSafe
@Recommend
public class SingletonExample7 {

    private static SingletonExample7 singletonExample1 = null;
    private SingletonExample7(){

    }
    /**
     *  通过静态方法
     * @return
     */
    public static SingletonExample7 getInstance(){
        return InstanceSingleton.INSTANCE.getInstance();
    }

    private enum InstanceSingleton{
        INSTANCE; //定义枚举一个变量

      private SingletonExample7 singletonExample7;
      // 定义一个构造方法，实例化对象,通过jvm保证这个方法绝对只调用一次
      InstanceSingleton(){
          singletonExample7 = new SingletonExample7();
      }
      public SingletonExample7 getInstance(){
          return  singletonExample7;
      }
    }

}

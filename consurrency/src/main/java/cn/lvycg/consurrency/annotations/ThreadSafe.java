package cn.lvycg.consurrency.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by cg on 2018/10/13.
 *  声明线程安全注解,只是标识线程安全的类或写法
 *  target 表示注解作用的目标
 *  retention 表示注册作用的范围
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface ThreadSafe {

    String value() default "";
}

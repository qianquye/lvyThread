package cn.lvycg.consurrency.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by cg on 2018/10/13.
 *  在项目中标识[不推荐]的类或写法
 *  target 表示注解作用的目标
 *  retention 表示注册作用的范围
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface NotRecommend {

    String value() default "";
}

package cn.lvycg.consurrency.publish;

import cn.lvycg.consurrency.annotations.NotRecommend;
import cn.lvycg.consurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by cg on 2018/10/14.
 *  对象逸出：在对象没有完全发布就使用了this,
 */
@Slf4j
@NotThreadSafe
@NotRecommend
public class Escape {

    private int thisCanBeEscape = 0;
    public Escape(){
        new InnerClass();
        log.info("init......");
        thisCanBeEscape = 1;
    }

    private class InnerClass{
        public InnerClass(){
            log.info("{}",Escape.this.thisCanBeEscape);
        }
    }

    public static void main(String[] args) {
        new Escape();
    }

}

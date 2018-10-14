package cn.lvycg.consurrency.publish;

import cn.lvycg.consurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * Created by cg on 2018/10/14.
 * 使对象能被当前代码外所用
 */
@Slf4j
@NotThreadSafe
public class UnSafePublsh {

    private String[] states = {"a","b","c"};

    public String[] getStates(){
        return states;
    }

    public static void main(String[] args) {
        UnSafePublsh unSafePublsh = new UnSafePublsh();
        log.info("{}", Arrays.toString(unSafePublsh.getStates()));
        unSafePublsh.getStates()[0] = "d";
        log.info("{}", Arrays.toString(unSafePublsh.getStates()));
    }
}

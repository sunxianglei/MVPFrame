package com.xianglei.mvpframe.utils;

import java.math.BigDecimal;
import java.util.Random;

/**
 * 数学计算工具类
 * Created by sunxianglei on 2017/10/18.
 */

public class MathUtil {

    /**
     * 两个Float相加不失精度
     * @param a
     * @param b
     * @return
     */
    public static Float addFloatNum(float a, float b){
        BigDecimal bd1 = new BigDecimal(Float.toString(a));
        BigDecimal bd2 = new BigDecimal(Float.toString(b));
        return bd1.add(bd2).floatValue();
    }

    /**
     * 两个Float相减不失精度
     * @param a
     * @param b
     * @return
     */
    public static Float subFloatNum(float a, float b){
        BigDecimal bd1 = new BigDecimal(Float.toString(a));
        BigDecimal bd2 = new BigDecimal(Float.toString(b));
        return bd1.subtract(bd2).floatValue();
    }

    /**
     * 保留小数点后几位
     * @param a
     * @param num
     * @return
     */
    public static Float keepDecimals(float a, int num){
        return (float)(Math.round(a * Math.pow(10, num))) / (float) Math.pow(10, num);
    }

    /**
     * 产生整数型的随机数
     * @param min
     * @param max
     * @return
     */
    public static int randomInt(int min, int max){
        Random random = new Random();
        int result = random.nextInt(max - min) + min;
        return result;
    }

}

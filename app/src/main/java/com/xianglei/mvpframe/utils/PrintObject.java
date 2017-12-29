package com.xianglei.mvpframe.utils;

import java.lang.reflect.Field;

/**
 * @author sunxianglei
 * @date 2017/12/29
 */

public class PrintObject {

    public static String toString(Object bean){
        StringBuilder sb = new StringBuilder();
        Class c = (Class)bean.getClass();
        Field fields[] = c.getDeclaredFields();
        for(int i=0;i<fields.length;i++){
            Field f = fields[i];
            Object value = null;
            try {
                value = f.get(bean);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            System.out.println("name:" + f.getName() + " value:" + value);
            sb.append("name:" + f.getName() + " value:" + value);
        }
        return sb.toString();
    }

}

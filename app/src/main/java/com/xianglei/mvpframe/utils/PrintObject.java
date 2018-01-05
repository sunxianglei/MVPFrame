package com.xianglei.mvpframe.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author sunxianglei
 * @date 2017/12/29
 */

public class PrintObject {

    /**
     * 通过反射打印传入对象的属性值
     * @param bean
     * @return
     */
    public static String toString(Object bean){
        StringBuilder sb = new StringBuilder();
        Class c = bean.getClass();
        sb.append(c.getSimpleName() + "[");
        Field fields[] = c.getDeclaredFields();
        for(Field f: fields){
            String name = f.getName();
            name = name.substring(0,1).toUpperCase() + name.substring(1);
            try {
                Method m = bean.getClass().getDeclaredMethod("get" + name);
                String value = (String) m.invoke(bean);
                sb.append(f.getName() + " = " + value + ",");
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        sb.append("]");
        return sb.toString();
    }

}

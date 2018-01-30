package com.xianglei.mvpframe;

import com.xianglei.mvpframe.module.home.HomeAdapter;
import com.xianglei.mvpframe.utils.Const;
import com.xianglei.mvpframe.utils.Logger;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test() throws Exception {
        HomeAdapter adapter = new HomeAdapter(null);
        for(int i=0;i<3;i++) {
            int result = adapter.getDrawableRes(Const.FULI);
            System.out.println(Const.FULI + "-->" +  result);
        }
        for(int i=0;i<3;i++) {
            int result = adapter.getDrawableRes(Const.ANDROID);
            System.out.println(Const.ANDROID + "-->" +  result);
        }
    }
}
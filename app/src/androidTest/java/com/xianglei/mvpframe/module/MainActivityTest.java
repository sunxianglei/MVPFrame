package com.xianglei.mvpframe.module;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.Swipe;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.xianglei.mvpframe.R;
import com.xianglei.mvpframe.data.bean.ArticleInfo;
import com.xianglei.mvpframe.utils.Logger;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;


/**
 * @author sunxianglei
 * @date 2018/1/29
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    private static final String TAG = "MainActivityTest";
    @Rule
    public ActivityTestRule activityTestRule = new ActivityTestRule(MainActivity.class);

    @Before
    public void setUp() throws Exception {
        Logger.d(TAG, "Test's setUp running");
    }

    @Test
    public void test() throws Exception{
        Logger.d(TAG, "Test's test running");
        Thread.sleep(5000);
        switchTheme("黑色");
        switchTheme("绿色");
        Thread.sleep(5000);
        onView(withId(R.id.recyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
    }

    private void switchTheme(String type){
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText("主题设置")).perform(click());
        onView(withText(type)).perform(click());
    }

}
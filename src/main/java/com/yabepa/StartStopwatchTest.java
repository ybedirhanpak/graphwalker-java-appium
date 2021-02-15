package com.yabepa;

import org.graphwalker.java.annotation.GraphWalker;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

@GraphWalker(value = "random(edge_coverage(100))", start = "v_clockPage")
public class StartStopwatchTest extends BaseTest implements StartStopwatch {

    @Override
    public void e_goToStopwatch() {
        // Click top navigation stopwatch button
        driver.findElementById("Stopwatch").click();
    }

    @Override
    public void e_startStopwatchWithCircle() {
        // Click center circle
        driver.findElementById("com.google.android.deskclock:id/stopwatch_time").click();
    }

    @Override
    public void e_startStopwatchWithFAB() {
        // Click FAB start button
        driver.findElementById("com.google.android.deskclock:id/fab").click();
    }

    @Override
    public void e_stopwatchPause() {
        // Click FAB pause button
        driver.findElementById("com.google.android.deskclock:id/fab").click();
    }

    @Override
    public void e_lapStopwatch() {
        // Click left lap button
        driver.findElementById("com.google.android.deskclock:id/left_button").click();
    }

    @Override
    public void e_stopwatchResume() {
        // Click FAB resume button
        driver.findElementById("com.google.android.deskclock:id/fab").click();
    }

    @Override
    public void e_resetStopwatch() {
        // Click left reset button
        driver.findElementById("com.google.android.deskclock:id/left_button").click();
    }

    @Override
    public void v_stopwatchPage() {
        // Check if stopwatch is started from 0:0:0
        String stopwatchTimeText = getStopwatchTimeText();
        Assert.assertEquals("0:0:0", stopwatchTimeText);
    }

    @Override
    public void v_stopwatchStarted() {
        // Check if stopwatch has increased after a time
        wait(2000);
        int stopwatchTimeSecond = Integer.parseInt(getStopwatchTime()[2]);
        Assert.assertTrue(stopwatchTimeSecond > 0);
    }

    @Override
    public void v_stopwatchPaused() {
        // Check if stopwatch time stays same on pause
        String firstTimeText = getStopwatchTimeText();
        wait(2000);
        String secondTimeText = getStopwatchTimeText();
        Assert.assertEquals(firstTimeText, secondTimeText);
    }

    @Override
    public void v_stopwatchPageWithLap() {
        // Check if lap list size is greater than 0
        wait(2000);
        List<WebElement> lapList = driver.findElementById("com.google.android.deskclock:id/laps_list").findElements(By.className("android.widget.LinearLayout"));
        Assert.assertTrue(lapList.size() > 0);
    }
}

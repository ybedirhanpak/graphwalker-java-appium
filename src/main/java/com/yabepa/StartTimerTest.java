package com.yabepa;

import org.graphwalker.java.annotation.GraphWalker;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@GraphWalker(value = "random(edge_coverage(100))", start = "v_clockPage")
public class StartTimerTest extends BaseTest implements StartTimer {

    @Override
    public void e_goToTimer() {
        // Click top navigation timer button
        driver.findElementById("Timer").click();
    }

    @Override
    public void e_enterTimerInput() {
        // Click two digit buttons
        WebElement firstDigit = driver.findElementById("com.google.android.deskclock:id/second").findElement(By.id("com.google.android.deskclock:id/key_left"));
        WebElement secondDigit = driver.findElementById("com.google.android.deskclock:id/second").findElement(By.id("com.google.android.deskclock:id/key_middle"));
        firstDigit.click();
        secondDigit.click();
    }

    @Override
    public void e_startTimer() {
        // Click FAB start button
        driver.findElementById("com.google.android.deskclock:id/fab").click();
    }

    @Override
    public void e_deleteTimer() {
        // Click left FAB delete button
        driver.findElementById("com.google.android.deskclock:id/left_button").click();
    }

    @Override
    public void e_pauseTimer() {
        // Click FAB pause button
        driver.findElementById("com.google.android.deskclock:id/fab").click();
    }

    @Override
    public void e_resumeTimer() {
        // Click FAB start button
        driver.findElementById("com.google.android.deskclock:id/fab").click();
    }

    @Override
    public void e_resetTimer() {
        // Click reset button
        driver.findElementById("com.google.android.deskclock:id/reset_add").click();
    }

    @Override
    public void e_addOneMinute() {
        // Click +1:00 button
        driver.findElementById("com.google.android.deskclock:id/reset_add").click();
    }

    @Override
    public void e_waitTwoSeconds() {
        wait(2000);
    }

    @Override
    public void e_doNothing() {
        // Do Nothing.
    }

    @Override
    public void v_timerPage() {
        // Check if timer is at value "00:00:00".
        assertTimerInput("0","0","0","0","00");
    }

    @Override
    public void v_timerPageInputEntered() {
        // Check if input is displayed correctly.
        assertTimerInput("0","0","0","0","45");
    }

    @Override
    public void v_timerPageStarted() {
        // Wait a second and check if time has passed.
        String firstTimeText = getTimerTimeText();
        wait(1000);
        String secondTimeText = getTimerTimeText();
        Assert.assertNotEquals(firstTimeText, secondTimeText);
    }

    @Override
    public void v_timerPageOneMinuteAdded() {
        // Check if time is increased by one minute.
    }

    @Override
    public void v_timerResetted() {
        // Check if time is initial value.
        String timerText = getTimerTimeText();
        Assert.assertEquals("0:0:45", timerText);
    }

    @Override
    public void v_timerPaused() {
        // Wait a second and check if time is stopped.
        String firstTimeText = getTimerTimeText();
        wait(1000);
        String secondTimeText = getTimerTimeText();
        Assert.assertEquals(firstTimeText, secondTimeText);
    }

    @Override
    public void v_timerResettedAndWaited() {
        // Check if time is still equal to the initial value.
        String timerText = getTimerTimeText();
        Assert.assertEquals("0:0:45", timerText);
    }
}

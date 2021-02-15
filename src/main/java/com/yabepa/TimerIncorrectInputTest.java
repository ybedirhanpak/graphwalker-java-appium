package com.yabepa;

import org.graphwalker.java.annotation.GraphWalker;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@GraphWalker(value = "random(edge_coverage(100))", start = "v_clockPage")
public class TimerIncorrectInputTest extends BaseTest implements TimerIncorrectInput {
    @Override
    public void e_goToTimer() {
        // Click top navigation timer button
        driver.findElementById("Timer").click();
    }

    @Override
    public void e_enterIncorrectInput() {
        // Enter 99:99
        WebElement thirdRow = driver.findElementById("com.google.android.deskclock:id/third");
        for(int i=0; i < 4; i++) {
            WebElement digit = thirdRow.findElement(By.id("com.google.android.deskclock:id/key_right"));
            digit.click();
        }
    }

    @Override
    public void e_startTimer() {
        // Click FAB start button
        driver.findElementById("com.google.android.deskclock:id/fab").click();
    }

    @Override
    public void e_pauseTimer() {
        // Click FAB pause button
        driver.findElementById("com.google.android.deskclock:id/fab").click();
    }

    @Override
    public void e_resetTimer() {
        // Click reset button
        driver.findElementById("com.google.android.deskclock:id/reset_add").click();
    }

    @Override
    public void e_addTimer() {
        // Click add timer button
        driver.findElementById("com.google.android.deskclock:id/right_button").click();
    }

    @Override
    public void v_timerPage() {
        // Check if timer is at value "00:00:00".
        assertTimerInput("0","0","0","0","00");
    }

    @Override
    public void v_timerWithIncorrectInput() {
        // Check if input is displayed correctly.
        assertTimerInput("0","0","9","9","99");
    }

    @Override
    public void v_timerStarted() {
        // Wait a second and check if time has passed.
        String firstTimeText = getTimerTimeText();
        wait(1000);
        String secondTimeText = getTimerTimeText();
        Assert.assertNotEquals(firstTimeText, secondTimeText);
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
    public void v_timerResetted() {
        // Check if time is initial value.
        wait(1000);
        String timerText = getTimerTimeText();
        Assert.assertEquals("1:40:39", timerText);
    }

    @Override
    public void v_addTimerPage() {
        // Check if timer is at value "00:00:00".
        assertTimerInput("0","0","0","0","00");
    }
}

package com.yabepa;

import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.graphwalker.java.annotation.GraphWalker;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

@GraphWalker(value = "random(edge_coverage(100))", start = "v_clockPage")
public class CreateAlarmTest extends BaseTest implements CreateAlarmTestModel {

    @Override
    public void e_goToAlarm() {
        // Click top navigation alarm button
        driver.findElementById("Alarm").click();
    }

    @Override
    public void e_openCreateAlarmPopup() {
        // Click FAB alarm create button
        driver.findElementById("com.google.android.deskclock:id/fab").click();
    }

    @Override
    public void e_enterAlarmInput() {
        TouchAction action = new TouchAction(driver);
        // Select hour 2
        action.tap(PointOption.point(714,883)).perform();
        wait(1000);
        // Select minute 10
        action.tap(PointOption.point(787,849)).perform();
    }

    @Override
    public void e_createAlarm() {
        // Click OK button
        driver.findElementById("com.google.android.deskclock:id/ok_button").click();
    }

    @Override
    public void e_cancelAlarm() {
        // Click cancel button
        driver.findElementById("com.google.android.deskclock:id/cancel_button").click();
    }

    @Override
    public void e_goToClockPage() {
        // Go to clock page
        driver.findElementById("Clock").click();
    }


    @Override
    public void v_alarmPage() {
        // Check if alarm list size is greater than 0
        String xPathAlarmList = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.View/android.widget.FrameLayout/android.view.View/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.widget.LinearLayout";
        List<WebElement> alarmList = driver.findElements(By.xpath(xPathAlarmList));

        Assert.assertTrue(alarmList.size() > 0);
    }

    @Override
    public void v_alarmPopup() {
        // Check if default alarm value is 00:00
        String hours = driver.findElementById("com.google.android.deskclock:id/hours").getText();
        String minutes = driver.findElementById("com.google.android.deskclock:id/minutes").getText();
        Assert.assertEquals(hours, "00");
        Assert.assertEquals(minutes, "00");
    }

    @Override
    public void v_alarmPopupWithInput() {
        // Check if alarm input is displayed correctly: 02:10
        String hours = driver.findElementById("com.google.android.deskclock:id/hours").getText();
        String minutes = driver.findElementById("com.google.android.deskclock:id/minutes").getText();
        Assert.assertEquals(hours, "02");
        Assert.assertEquals(minutes, "10");
    }

    @Override
    public void v_alarmPageUpdated() {
        // Check if alarm list size is greater than 0
        String xPathAlarmList = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.View/android.widget.FrameLayout/android.view.View/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.widget.LinearLayout";
        List<WebElement> alarmList = driver.findElements(By.xpath(xPathAlarmList));
        Assert.assertTrue(alarmList.size() > 0);

        // Check if first alarm is 02:10
        String xPathFirstElement = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.View/android.widget.FrameLayout/android.view.View/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.LinearLayout/android.widget.TextView";
        String firstAlarmText = driver.findElement(By.xpath(xPathFirstElement)).getText();
        Assert.assertEquals("02:10", firstAlarmText);
    }

    @Override
    public void v_clockPageUpdated() {
        // Check if next alarm is displayed correctly

        String nextAlarmText = driver.findElementById("com.google.android.deskclock:id/nextAlarm").getText();

        Assert.assertTrue(nextAlarmText.contains("02:10"));
    }
}

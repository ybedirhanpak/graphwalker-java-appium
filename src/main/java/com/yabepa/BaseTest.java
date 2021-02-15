package com.yabepa;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.graphwalker.core.machine.ExecutionContext;
import org.graphwalker.java.annotation.AfterExecution;
import org.graphwalker.java.annotation.BeforeExecution;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Assert;

public class BaseTest extends ExecutionContext {

    protected AppiumDriver<WebElement> driver = null;

    private String parseTimeHHmm(String rawTime) {
        Pattern pattern = Pattern.compile("(\\d\\d:\\d\\d)");
        Matcher matcher = pattern.matcher(rawTime);
        String time = "00:00";
        if (matcher.find()) {
            time = matcher.group(1);
        }
        return time;
    }

    public void wait(int milliseconds) {
        try {
            System.out.println("Thread: " + Thread.currentThread() + " will sleep.");
            Thread.sleep(milliseconds);
            System.out.println("Thread: " + Thread.currentThread() + " will awake.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void v_clockPage() {
        System.out.println("Running v_clockPage");
        Assert.assertNotEquals(null, driver);

        // Assert current time
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        String systemTime = parseTimeHHmm(dateFormat.format(System.currentTimeMillis()));
        String appTime = parseTimeHHmm(driver.findElementById("com.google.android.deskclock:id/digital_clock").getText());

        // Check if system time is equal to the app time
        Assert.assertEquals(systemTime, appTime);
    }

    public String getTimerInputHours() {
        String _hoursTens = driver.findElementById("com.google.android.deskclock:id/hours_tens").getText();
        String _hoursOnes = driver.findElementById("com.google.android.deskclock:id/hours_ones").getText();

        return _hoursTens + _hoursOnes;
    }

    public String getTimerInputMinutes() {
        String _minutesTens = driver.findElementById("com.google.android.deskclock:id/minutes_tens").getText();
        String _minutesOnes = driver.findElementById("com.google.android.deskclock:id/minutes_ones").getText();

        return _minutesTens + _minutesOnes;
    }

    public String getTimerInputSeconds() {
        return driver.findElementById("com.google.android.deskclock:id/seconds").getText();
    }

    public String getTimerInputTime() {
        return getTimerInputHours() + ":" + getTimerInputMinutes() + ":" + getTimerInputSeconds();
    }

    public void assertTimerInput(String hoursTens, String hoursOnes, String minutesTens, String minutesOnes) {
        String _hours = getTimerInputHours();
        String _minutes = getTimerInputMinutes();

        String _time = _hours + ":" + _minutes;
        String timeExpected = hoursTens + hoursOnes + ":" + minutesTens + minutesOnes;

        Assert.assertEquals(timeExpected, _time);
    }

    public void assertTimerInput(String hoursTens, String hoursOnes, String minutesTens, String minutesOnes, String seconds) {
        String _time = getTimerInputTime();
        String timeExpected = hoursTens + hoursOnes + ":" + minutesTens + minutesOnes + ":" + seconds;

        Assert.assertEquals(timeExpected, _time);
    }

    public String[] getCircleTime(String rawTime) {
        Pattern pattern = Pattern.compile("((\\d+)\\s+hours?\\s+)*((\\d+)\\s+minutes?\\s+)*(\\d+)\\s+seconds");
        Matcher matcher = pattern.matcher(rawTime);
        String seconds = "0";
        String minutes = "0";
        String hours = "0";
        if (matcher.find()) {
            hours = matcher.group(2) != null ? matcher.group(2) : hours;
            minutes = matcher.group(4) != null ? matcher.group(4) : minutes;
            seconds = matcher.group(5) != null ? matcher.group(5) : seconds;
        }
        return new String[]{hours, minutes, seconds};
    }

    public String getCircleTimeText(String[] circleTime) {
        StringBuilder result = new StringBuilder();
        for(String s : circleTime) {
            result.append(s);
            result.append(":");
        }
        result.deleteCharAt(result.length()-1);
        return result.toString();
    }

    public String[] getTimerTime() {
        String time = driver.findElementById("com.google.android.deskclock:id/timer_time_text").getAttribute("contentDescription");
        return getCircleTime(time);
    }

    public String getTimerTimeText() {
        String[] timerTime = getTimerTime();
        return getCircleTimeText(timerTime);
    }

    public String[] getStopwatchTime() {
        String time = driver.findElementById("com.google.android.deskclock:id/stopwatch_time_text").getAttribute("contentDescription");
        return getCircleTime(time);
    }

    public String getStopwatchTimeText() {
        String[] stopwatchTime = getStopwatchTime();
        return getCircleTimeText(stopwatchTime);
    }

    @BeforeExecution
    public void setUp() {
        File classpathRoot = new File(System.getProperty("user.dir"));
        File appDir = new File(classpathRoot, "src/main/resources");
        File app = new File(appDir, "Clock.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator1");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "4.4");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
        capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        capabilities.setCapability(MobileCapabilityType.FULL_RESET, true);
        capabilities.setCapability(MobileCapabilityType.NO_RESET, false);

        try {
            System.out.println("Try to find driver");
            driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
            System.out.println("Driver: " + driver);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterExecution
    public void tearDown() {
        driver.quit();
    }
}

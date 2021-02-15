# Boğaziçi University CMPE436 Asignment 3 - Yahya Bedirhan Pak - 2016400213

## Description
This is a model-based testing assignment based on graphwalker and appium. I've modeled and tested 4 scenarios for Google Clock application for this assignment.

### Versions

* Java: 11 or 8
* Android device: API level 19 (Android 4.4)
* Google Clock application: 4.4
* Appium: 1.18.3
* Nodejs (Built-in with appium desktop): 12.8.1
* Maven: 3.6.3

## How to run tests

You need to first install packages and generate graphwalker resources:

```
mvn install
mvn graphwalker:generate-sources
```

You need to have the apk file at `src/main/resources/Clock.apk`. You can instal the apk file here:

`https://www.apkmirror.com/apk/google-inc/clock/clock-4-4-release/#downloads`

You can run `Runner.java` to run the tests. Tests are executed sequentially. Tests results are printed out to the console. 
## Test Descriptions
I've created 4 test models. Tests are executed in this order:

1. CreateAlarmTest
2. StartTimerTest
3. TimerIncorrectInputTest
4. StartStopwatchTest

You can see the graphwalker JSON file in `src/main/resources/com/yabepa/ClockTestModels.json`

### CreateAlarmTest

You can see the screenshot of the graph in `screenshots/CreateAlarmTest.png`

This is the flow of this model:

1. Start from clock page, this is the page where application always starts with.

2. Go to alarm page.

3. Check if current alarm list size is greater than zero

4. Open create alarm popup

5. Check if default alarm is 00:00

6. Set alarm to 02:10

6. Create alarm

7. Check if created alarm is at 02:10

### StartTimerTest

You can see the graph in `screenshots/StartTimerTest.png`

This is the flow of this model:

1. Start from clock page, this is the page that application always starts with.

2. Go to timer page

3. Type input to set the timer

4. Check if the input is displayed correctly

5. Start the timer

6. When the timer is started, it checks if the current time is less than the input.

7. When the timer is started, it can do these actions:

  * Delete the timer
  * Pause the timer
  * Add one minute

8. When the timer is paused, it checks whether the current time doesn't change after a time.

9. When the timer is paused, it can do these actions:

  * Resume the timer
  * Delete the timer
  * Reset the timer

10. When the timer is resetted, it checks whether the current time is equal to the initial input.

11. When the timer is resetted, it can do these actions:

  * Reset the timer
  * Delete the timer
  * Wait for two seconds

### TimerIncorrectInputTest

You can see the graph in `screenshots/TimerIncorrectInputTest.png`

This is the flow of this model:

1. Start from clock page, this is the page that application always starts with.

2. Go to timer page

3. Type an incorrect input such as "99:99"

4. Check if the input is displayed correctly

5. Start the timer

6. When the timer is started, it checks if the current time is less than the input.

7. When the timer is started, it can do these actions:

  * Add a new timer
  * Pause the timer

8. When the timer is paused, it checks whether the current time doesn't change after a time.

9. When the timer is paused, it can do these actions:

  * Reset the timer

10. When the timer is resetted, it checks whether the current time is equal to the initial input.

11. When the timer is resetted, it can do these actions:

  * Add a new timer

12. When a new timer is added, it gives another "99:99" as input and checks if everything is okay.

### StartStopwatchTest

You can see the graph in `screenshots/StartTimerTest.png`

This is the flow of this model:

1. Start from clock page, this is the page that application always starts with.

2. Go to stopwatch page.

3. Check if current stopwatch is equal to the 0.

4. Start the stopwatch.

5. When the stopwatch is started, it checks whether current second is greater than 0.

6. When the stopwatch is started, it can do these actions:

  * Lap the stopwatch
  * Pause the stopwatch

7. When the stopwatch is lapped, it checks whether the laps are displayed.

8. When the stopwatch is lapped, it can do these actions:

  * Pause the stopwatch
  * Lap the stopwatch

12. When the stopwatch is paused, it checks whether current second is greater than 0.

13. When the stopwatch is paused, it can do these actions:

  * Resume the stopwatch
  * Reset the stopwatch

14. When the stopwath is resetted, it starts from the stopwatch page again.


## Conclusion

It takes about 24 minutes to run all of the tests with 100% edge coverage on my computer.

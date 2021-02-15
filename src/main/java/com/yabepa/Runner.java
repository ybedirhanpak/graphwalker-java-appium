package com.yabepa;

import org.graphwalker.java.test.Result;
import org.graphwalker.java.test.TestExecutor;

import java.io.IOException;

public class Runner {
    public static void main(String[] args) throws IOException {

        Class[] testClasses = {
                TimerIncorrectInputTest.class,
                StartStopwatchTest.class
        };

        for(Class c : testClasses) {
            System.out.println("Run Test for:" + c.toString());
            TestExecutor executor = new TestExecutor(c);

            Result result = executor.execute(true);
            System.out.println("Done for:" + c.toString() + " [" + result.getResults().toString(2) + "]");
        }
    }
}

package com.debugDemo.targetApp;

public class Startup {
    public static void main(String args[]) throws Exception {
        TestClassMethod testClassMethod = new TestClassMethod();

        int i = 0;
        while (true) {
            testClassMethod.sayHello();
            Thread.sleep(2000);
            System.out.println(i);
            i++;
        }
    }
}

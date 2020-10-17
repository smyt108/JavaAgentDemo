package com.debugDemo.attachAgent;

import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.util.LinkedList;
import java.util.List;

public class AttachAgent1 {
    private static String className = "com.debugDemo.targetApp.TestClassMethod"; // full name
    private static String methodName = "sayHello";

    public static void agentmain(String agentArgs, Instrumentation inst) {
        System.out.println("=========AttachAgent1 agentmain start========");
        System.out.println(agentArgs);

        try {
            inst.addTransformer(new AttachTransformer(className, methodName), true);
inst.redefineClasses();
            for (Class clazz : inst.getAllLoadedClasses()) {
                if (className.equals(clazz.getName())) {
                    try {
                        System.out.println(clazz.getName());
                        inst.retransformClasses(clazz);
                    } catch (UnmodifiableClassException e) {
                        e.printStackTrace();
                    }
                }
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}

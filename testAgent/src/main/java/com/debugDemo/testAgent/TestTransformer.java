package com.debugDemo.testAgent;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class TestTransformer implements ClassFileTransformer {
    private String targetClassName = "com/debugDemo/targetApp/TestClassMethod";
    private String targetMethodName = "sayHello";
    private String actualClassName = "com.debugDemo.targetApp.TestClassMethod";

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        System.out.println("ClassLoader classname:" + className);
        if (className.equals(targetClassName)) {
            try {
                System.out.println("start transform classname:" + className);
                System.out.println("try to get classpool.");
                ClassPool classPool = ClassPool.getDefault();
                System.out.println(classPool.toString());
                CtClass cls = classPool.get(actualClassName);
                System.out.println(cls.toString());
                CtMethod ctMethod = cls.getDeclaredMethod(targetMethodName);
                System.out.println(ctMethod.toString());
                ctMethod.insertBefore("{ System.out.println(\"start\"); }");
                ctMethod.insertAfter("{ System.out.println(\"end\"); }");
                return cls.toBytecode();
            } catch (Exception e) {
                System.out.println("Exception belows:");
                System.out.println(e.toString());
            }
        }

        return classfileBuffer;
    }
}

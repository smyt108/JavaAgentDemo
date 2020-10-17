package com.debugDemo.attachAgent;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class AttachTransformer implements ClassFileTransformer {
    private String actualClass;
    private String targetClass;
    private String targetMethod;

    public AttachTransformer(String className, String methodName) {
        this.targetClass = new String(className).replaceAll("\\.", "\\/");
        this.targetMethod = methodName;
        this.actualClass = className;
    }

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        System.out.println(targetClass + " " + className);
        if (className.equals(targetClass)) {
            try {
                System.out.println("Debug className: " + className);
                ClassPool classPool = ClassPool.getDefault();
                System.out.println("Debug classPool: " + classPool.toString());
                CtClass cls = classPool.get(this.actualClass);
                CtMethod ctMethod = cls.getDeclaredMethod(this.targetMethod);
                ctMethod.setBody("System.out.println(\"Hello refined World!\");");
                ctMethod.insertBefore("{ System.out.println(\"attach start\"); }");
                ctMethod.insertAfter("{ System.out.println(\"attach end\"); }");
                return cls.toBytecode();
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
        return classfileBuffer;
    }
}

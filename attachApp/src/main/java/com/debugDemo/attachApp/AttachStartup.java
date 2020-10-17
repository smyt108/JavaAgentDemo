package com.debugDemo.attachApp;

import com.sun.tools.attach.VirtualMachine;

public class AttachStartup {
    public static void main(String args[]) throws Exception {
        try {
            String agentPath = "D:\\workspace\\debugDemo\\attachAgent\\target\\attachAgent-0.0.1-SNAPSHOT.jar";//agentFilePath为agent的路径
            String jvmPid = "8248";
            System.out.println("Attaching to target JVM with PID: " + jvmPid);
            VirtualMachine jvm = VirtualMachine.attach(jvmPid);
            jvm.loadAgent(agentPath);
            jvm.detach();
            System.out.println("Attached to target JVM and loaded Java agent successfully");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

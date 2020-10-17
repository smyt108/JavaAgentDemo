package com.debugDemo.testAgent;

import java.lang.instrument.Instrumentation;

public class TestAgent1 {
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("=========testAgent1 premain start========");
        System.out.println(agentArgs);
        // 添加Transformer
        inst.addTransformer(new TestTransformer());
    }
}

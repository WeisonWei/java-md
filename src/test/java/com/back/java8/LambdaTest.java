package com.back.java8;

import com.back.lambda.Calculator;
import org.junit.Test;

public class LambdaTest {

    @Test
    public void useAnonymousClass() {
        // 使用匿名内部类
        Calculator func = new Calculator() {
            @Override
            public int calculate(int x, int y) {
                System.out.println("经过计算: x 加  y 的结果是: " + x + y);
                return x + y;
            }
        };

        // 执行
        func.calculate(8, 2);
    }

    /**
     * 入参为函数式接口的方法
     */

    public static int operation(Calculator func, int a, int b) {
        return func.calculate(a, b);
    }

    @Test
    public void useLambda() {

        // 使用lamdba表达式
        // 调用下边定义的invoke方法，把lamdba表达式作为参数传递
        int result1 = operation(((x, y) -> {
            return x + y;
        }), 8, 2);
        int result2 = operation(((x, y) -> {
            return x * y;
        }), 8, 2);
        int result3 = operation(((x, y) -> {
            return x % y;
        }), 8, 2);
        System.out.println("经过运算: x 加  y 的结果是: " + +result1);
        System.out.println("经过运算: x 乘  y 的结果是: " + +result2);
        System.out.println("经过运算: x 除  y 的结果是: " + +result3);

        // lamdba表达式引用main()方法中的局部变量param
        // param需要是一个final型的，已经确定了的值而且不能再改变
        int param = 100;
        int result4 = operation(((x, y) -> {
            return x + y + param;
        }), 8, 2);
        //param = 111;
    }
}

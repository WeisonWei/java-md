package com.back.java8;


import com.back.reference.IsReferable;
import com.back.reference.Reference;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReferenceTest {

    @Test
    public  void methodReference() {

        // 1 Lambda implementaion.
        IsReferable demoOne = () -> Reference.staticMethod();
        IsReferable demoTwo = Reference::staticMethod;

        //调用执行
        demoOne.reference();
        demoTwo.reference();

        // 2 引用特定实例方法(非静态方法这样引用)
        IsReferable commonMethod = new Reference()::commonMethod;
        commonMethod.reference();

        //3 引用任意对象（属于同一个类）的实例方法,主要用于操作集合
        //2.1 例１
        List<Reference> stringArray = new ArrayList();
        stringArray.add(new Reference("Barbara"));
        stringArray.add(new Reference("James"));
        stringArray.add(new Reference("Mary"));
        stringArray.add(new Reference("Weison"));
        stringArray.forEach(Reference::getName);
        //3.2 例2
        String[] strArray = {"c", "d", "e", "John", "f", "g", "a", "b"};
        Arrays.sort(strArray, String::compareToIgnoreCase);
        for (String s : strArray)
            System.out.println("*****" + s + "*****");

        //4 引用构造方法
        IsReferable demoSix = Reference::new;
        demoSix.reference();
    }
}

package com.back.java8;

import com.back.domain.Student;
import org.junit.Test;

import java.util.Optional;


public class OptionalTest {
    @Test
    public void use1() {
        Optional<String> str = Optional.ofNullable("123");
        String s1 = str.map(s -> s + "6").orElse("");
        String s2 = str.map(s -> s + "7").orElse("");
        Optional<Student> student = Optional.ofNullable(null);
        Optional<Student> student2 = student.map(student1 -> new Student());
        System.out.println(student2);
    }

    @Test
    public void use() {

        Object o = Optional.ofNullable(null).get();

        //构造两个Student实例　一个为null 一个非null
        Student weision = new Student("Ailice", 10, "女", 118);

        Student noName = null;

        //orElse1 存在即返回, 无则提供默认值
        Student student = getStudentOrElse(noName);
        System.out.println("getStudentOrElse-->她的名字是getStudent: " + student.getName());
        //orElse2 存在即返回, 无则提供默认值
        String name = getNameorElse(noName);
        System.out.println("getNameorElse-->她的名字是getName: " + name);
        //orElse3 存在即返回, 无则提供默认值
        String name1 = getNameorElse(weision);
        System.out.println("getNameorElse-->我的名字是: " + name1);

        //orElseGet1 存在即返回, 无则由函数来产生
        Student student1 = getStudentOrElseGet(noName);
        System.out.println("getStudentOrElseGet-->我的名字是: " + student1.getName());
        //orElseGet2 存在即返回, 无则由函数来产生
        Student student2 = getStudentOrElseGet(weision);
        System.out.println("getStudentOrElseGet-->我的名字是: " + student2.getName());

        //isPresent1 存在才对它做点什么
        StudentisPresent(weision);
        //isPresent2 存在才对它做点什么
        //StudentisPresent(noName);

        //map1 存在就对它做点加工
        String name2 = StudentMap(noName);
        System.out.println("StudentMap-->我的名字是: " + name2);
        //map2 存在就对它做点加工
        String name3 = StudentMap(weision);
        System.out.println("StudentMap-->我的名字是: " + name3);

        //flatMap1 存在就对它做点加工
        String name4 = StudentflatMap(noName);
        System.out.println("StudentflatMap-->我的名字是: " + name4);
        //flatMap2 存在就对它做点加工
        String name5 = StudentflatMap(weision);
        System.out.println("StudentflatMap-->我的名字是: " + name5);

        //filter1 存在才对它进行过滤
        String name6 = StudentFilter(noName);
        System.out.println("StudentFilter-->大于9岁的人是: " + name6);
        //filter2 存在才对它进行过滤
        String name7 = StudentFilter(weision);
        System.out.println("StudentFilter-->大于9岁的人是: " + name7);


    }


    //存在即返回, 无则提供默认值
    public static Student getStudentOrElse(Student student) {
        return Optional.ofNullable(student)
                .orElse(new Student("无名氏", 10, "女", 119));
    }

    //存在即返回, 无则提供默认值
    public static String getNameorElse(Student student) {
        return Optional.ofNullable(student)
                .map(stu -> stu.getName())
                .orElse("无名氏");
    }

    //存在即返回, 无则提供默认值
    public static Student getStudentOrElseGet(Student student) {
        return Optional.ofNullable(student).orElseGet(() ->
                new Student("无名氏", 10, "女", 120)
        );
    }

    //存在才对它做点什么
    //Optional.of(T) 适合明确T非空时使用
    public static void StudentisPresent(Student student) {
        Optional.of(student.getName())
                .ifPresent(System.out::println);
    }

    //存在才对它做点什么
    public static String StudentMap(Student student) {
        return Optional.ofNullable(student)
                .map(s -> s.getName())
                .orElse(null);
    }

    //存在才对它做点什么 flatMap与map方法　入参不一样　其他没区别
    public static String StudentflatMap(Student student) {
        return Optional.ofNullable(student)
                .flatMap(a -> Optional.of(a.getName()))
                .orElse("没名字");
    }

    //存在才对它进行过滤
    public static String StudentFilter(Student student) {
        return Optional.ofNullable(student).filter(stu -> stu.getAge() > 9)
                .map(s -> s.getName()).orElse("没有人大于９岁");
    }
}

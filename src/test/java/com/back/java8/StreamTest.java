package com.back.java8;

import com.back.domain.Student;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {

    private static List<Student> studentList1 = new ArrayList<>();
    private static List<Student> studentList2 = new ArrayList<>();
    private static List<List<Student>> students = new ArrayList<>();

    @BeforeClass
    public static void beforeClass() {
        Student student1 = new Student();
        Student student2 = new Student();
        Student student3 = new Student();
        Student student4 = new Student();
        Student student5 = new Student();
        Student student6 = new Student();

        student1.setName("Evan");
        student1.setAge(11);
        student1.setPhone(6);

        student2.setName("Wade");
        student2.setAge(12);
        student2.setPhone(5);

        student3.setName("Terence");
        student3.setAge(13);
        student3.setPhone(4);

        student4.setName("Jack");
        student4.setAge(14);
        student4.setPhone(3);

        student5.setName("Jackson");
        student5.setAge(15);
        student5.setPhone(2);

        student6.setName("Luis");
        student6.setAge(16);
        student6.setPhone(1);

        studentList1.add(student1);
        studentList1.add(student2);
        studentList1.add(student3);

        studentList2.add(student4);
        studentList2.add(student5);
        studentList2.add(student6);


        students.add(studentList1);
        students.add(studentList2);


    }

    @Test
    public void map() {
        Stream<String> arrayStream = Arrays.asList("555", "333", "444", "111", "222", "666").stream();
        arrayStream.map(this::mapString);
        arrayStream.map((s) -> mapString(s));
        arrayStream.forEach(System.out::println);
    }

    private String mapString(String str) {
        return str + ",";
    }


    @Test
    public void flatMap() {

        // flatMap 把Stream中的层级结构扁平化并返回Stream
        List<Student> studentList = students
                .stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());

        //展开多个List合并到一个新list
        studentList.stream().forEach(System.out::println);
    }

    @Test
    public void reduce() {

        String str3 = Stream.of("A", "B", "C", "D")
                .reduce("", (s1, s2) -> s1 + "1" + s2 + "2", (s1, s2) -> s1 + "*" + s2);
        System.out.println("拼接字符串str3：" + str3); //拼接字符串str3：1A21B21C21D2

        int sumValue1 = Stream.of(1, 2, 3, 4).reduce(10, Integer::sum);
        int sumValue2 = Stream.of(1, 2, 3, 4).reduce(10, (v1, v2) -> v1 + v2);
        System.out.println("求和有起始值：" + sumValue1);//求和有起始值：20
        System.out.println("求和有起始值：" + sumValue2);//求和有起始值：20

    }

    @Test
    public void mapSort() {
        //sorted()对stream进行自然顺序排序，或传入Comparator实现自定义的排序

        List<String> enrolOrders = Arrays.asList("Terence", "Wade", "Evan");
        // 按照入学顺序enrolOrders进行展示
        //1 生成一个Map<studentName,Student>

        Map<String, Student> nameStudentMap = studentList1.stream()
                .collect(Collectors.toMap(student -> student.getName(),
                        Function.identity(),
                        (student1, student2) -> student2));
        //2 按照enrolOrders中的name顺序去Map<studentName,Student>中拿Student,并生成新的List
        enrolOrders.stream()
                .map(enrolOrder -> nameStudentMap.get(enrolOrder))
                .forEach(System.out::println);
    }

    @Test
    public void groupBy() {
        List<Student> classOneStudents = new ArrayList<>();
        List<Student> classTwoStudents = new ArrayList<>();
        List<Student> classThreeStudents = new ArrayList<>();
        List<List<Student>> students = new ArrayList<>();

        classOneStudents.add(new Student("Weison", 12, "男", 111));
        classOneStudents.add(new Student("Evan", 13, "女", 110));
        classOneStudents.add(new Student("Obam", 14, "男", 109));

        classTwoStudents.add(new Student("Bush", 15, "男", 108));
        classTwoStudents.add(new Student("Jackson", 16, "男", 107));

        classThreeStudents.add(new Student("Linn", 17, "女", 106));
        classThreeStudents.add(new Student("Ellen", 18, "女", 105));

        students.add(classOneStudents);
        students.add(classTwoStudents);
        students.add(classThreeStudents);

        // groupingBy 分组并求每组的数量
        Map<String, Long> stringLongMap = students.stream()
                .flatMap(student -> student.stream())
                .collect(Collectors.groupingBy(Student::getSex, Collectors.counting()));
        System.out.println("stringLongMap--->" + stringLongMap); // {女=3, 男=4}

    }

    @Test
    public void nullStream() {
        //sorted()对stream进行自然顺序排序，或传入Comparator实现自定义的排序

        Optional<Object> o1 = Arrays.asList(null)
                .stream()
                .findAny()
                .map(o -> null);

        System.out.println();

    }


}

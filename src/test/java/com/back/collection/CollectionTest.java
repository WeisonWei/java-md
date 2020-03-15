package com.back.collection;

import com.back.domain.Student;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.*;

public class CollectionTest {
    private static List<Student> studentList = new ArrayList<Student>();
    private static Set<Student> studentSet = new HashSet<>();

    @BeforeClass
    public static void beforeClass() {
        Student student1 = new Student();
        Student student2 = new Student();
        Student student3 = new Student();

        student1.setName("Evan");
        student1.setAge(11);
        student1.setPhone(3);

        student2.setName("Wade");
        student2.setAge(12);
        student2.setPhone(2);

        student3.setName("Terence");
        student3.setAge(13);
        student3.setPhone(1);

        studentList.add(student1);
        studentSet.add(student1);

        studentList.add(student2);
        studentSet.add(student2);

        studentList.add(student3);
        studentSet.add(student3);


    }

    @Test
    public void traversalList() {
        // 1
        for (Student student : studentList) {
            System.out.println("traversal by foreach :" + student.getName());
        }

        //2
        Iterator<Student> iterator = studentList.iterator();
        while (iterator.hasNext()) {
            Student next = iterator.next();
            System.out.println("traversal by iterator :" + next.getName());
        }

        //3
        studentList.stream().forEach(student -> System.out.println("traversal by stream :" + student.getName()));

    }

    @Test
    public void traversalSet() {
        // 1
        for (Student student : studentSet) {
            System.out.println("traversal by foreach :" + student.getName());
        }

        //2
        Iterator<Student> iterator = studentSet.iterator();
        while (iterator.hasNext()) {
            Student next = iterator.next();
            System.out.println("traversal by iterator :" + next.getName());
        }

        //3
        studentSet.stream().forEach(student -> System.out.println("traversal by stream :" + student.getName()));

    }

    @Test
    public void sortList() {
        studentList.stream().forEach(student -> System.out.println("traversal by stream :" + student));
        //studentList.sort(Comparator.comparingInt(Student::getPhone));
        //Collections.sort(studentList);
        //studentList.stream().sorted(Student::compareTo).forEach(System.out::println);
        //studentList.stream().sorted(Comparator.comparingInt(Student::getPhone)).forEach(System.out::println);
        System.out.println();
        //studentList.stream().forEach(student -> System.out.println("traversal by stream :" + student));
    }

    @Test
    public void sortSet() {
        List<Student> students = new ArrayList<>();
        studentSet.stream().forEach(student -> System.out.println("traversal by stream :" + student));
        System.out.println();
        studentSet.stream().sorted(Student::compareTo).forEach(student -> students.add(student));
        students.stream().forEach(student -> System.out.println("traversal by stream :" + student));
    }

    @Test
    public void test() {
        List<Student> products = new ArrayList<>();
        List<Student> products1 = null;
        //boolean empty = CollectionUtils.isEmpty(products);
        products1.stream().forEach(System.out::println);
    }
}

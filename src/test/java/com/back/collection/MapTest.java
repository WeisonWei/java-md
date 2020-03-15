package com.back.collection;

import com.back.domain.Student;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.*;

public class MapTest {

    private static Map<String, Student> map = new HashMap<>();

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

        map.put("student1", student1);
        map.put("student2", student2);
        map.put("student3", student3);
    }


    @Test
    public void traversalByKeySet() {
        Set<String> strings = map.keySet();
        Iterator<String> iterator = strings.iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            Student student = map.get(next);
            System.out.println("traversalByKeySet --> " + student);
        }
    }

    @Test
    public void traversalByValues() {
        Collection<Student> values = map.values();
        Iterator<Student> iterator = values.iterator();
        while (iterator.hasNext()) {
            Student next = iterator.next();
            System.out.println("traversalByValues --> " + next);
        }
    }

    @Test
    public void traversalByEntrySet() {
        Set<Map.Entry<String, Student>> entries = map.entrySet();
        Iterator<Map.Entry<String, Student>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Student> next = iterator.next();
            String key = next.getKey();
            Student value = next.getValue();
            System.out.println("traversalByEntrySet --> " + value);
        }
    }
}

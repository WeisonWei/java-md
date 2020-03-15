package com.back;

import com.back.domain.Student;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Test {
    @org.junit.Test
    public void equals() {
        Student student1 = new Student("Wade", null, "男", 0);
        Student student2 = new Student("Terence", 13, "男", 2);

        if (!student1.getName().equals(student2.getName())) {
            System.out.println("their values are not equal!");
        }

        if (!student1.equals(student1)) {
            System.out.println("their values are not equal!");
        }

        if (student1.getAge() == 1) {
            System.out.println("they are equals!");
        }

    }

    @org.junit.Test
    public void date() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        String yesterday = new SimpleDateFormat("yyyy-MM-dd ").format(cal.getTime());
        System.out.println(yesterday);
    }


}

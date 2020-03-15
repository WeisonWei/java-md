package com.back.domain;

public class Student implements Comparable<Student> {

    private String name;
    private Integer age;
    private String sex;
    private int phone;

    public Student() {
    }

    public Student(String name, Integer age, String sex, int phone) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public int compareTo(Student student) {
        return this.getPhone() - student.getPhone();
    }

    @Override
    public String toString() {
        return "name: " + this.getName()
                + ",age: " + this.getAge()
                + ",phone: " + this.getPhone();
    }


}

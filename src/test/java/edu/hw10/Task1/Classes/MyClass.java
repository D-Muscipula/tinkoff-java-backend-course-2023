package edu.hw10.Task1.Classes;

import edu.hw10.Task1.annotations.Max;
import edu.hw10.Task1.annotations.Min;
import edu.hw10.Task1.annotations.NotNull;

public class MyClass {

    private final String name;

    private int age;

    private double salary;

    public MyClass(@NotNull String name, @Min(0) @Max(1) int age, @Min(1) double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public MyClass(@NotNull String name) {
        this(name, 0, 0.0);
    }

    public static MyClass defaultFabric() {
        return new MyClass("DefaultName");
    }

    public void printInfo() {
        System.out.println("Name: " + name + ", Age: " + age + ", Salary: " + salary);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public MyClass setAge(int age) {
        this.age = age;
        return this;
    }

    public double getSalary() {
        return salary;
    }

    public MyClass setSalary(double salary) {
        this.salary = salary;
        return this;
    }
}

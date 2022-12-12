package ru.kata.spring.boot_security.demo.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table (name = "user")
public class MyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @NotEmpty(message="Имя не может быть пустым")
    @Size(min = 2, max = 22, message = "2 > Имя > 22 символов")
    @Column(name="name")
    private String name;

    @NotEmpty(message="Поле не может быть пустым")
    @Size(min = 2, max = 22, message = "2 > Фамилия > 22 символов")
    @Column(name="surname")
    private String surName;

    @NotEmpty(message="Поле не может быть пустым")
    @Size(min = 2, max = 22, message = "2 > Департамент > 22 символов")
    @Column(name="department")
    private String department;

    @Column(name="salary")
    private int salary;

    @Column(name="pass")
    private String pass;
    @Column(name="role")
    private String role;

    public MyUser() {}

    public MyUser(String name, String surName, String department, int salary, String pass) {
        this.name = name;
        this.surName = surName;
        this.department = department;
        this.salary = salary;
        this.pass = pass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "MyUser{" +
                "id=" + id + ", name='" + name + '\'' +
                ", surName='" + surName + '\'' + ", department='" + department + '\'' +
                ", salary=" + salary + ", pass='" + pass + '\'' + '}';
    }
}



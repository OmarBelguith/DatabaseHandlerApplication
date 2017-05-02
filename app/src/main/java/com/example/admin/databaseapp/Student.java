package com.example.admin.databaseapp;

import java.security.PublicKey;

/**
 * Created by admin on 5/2/2017.
 */

public class Student {

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    private int id;

    public Student(String name, String surname, int grade) {
        this.name = name;
        this.surname = surname;
        this.grade = grade;
    }
    public Student(){

    }
    @Override
    public String toString() {
        return "Name: "+name+ " "+surname+"("+grade+")";
    }

    private String name;
    private String surname;
    private int grade;

}

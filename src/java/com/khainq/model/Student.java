/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khainq.model;

/**
 *
 * @author admin
 */
public class Student {
    private int id;
    private String studentCode;
    private String name;
    private String birthday;
    private String address;
    private int idNumber;
    private int phone;
    private int gender;
    private String email;
    private int status;
    private float mediumScore;

    public Student() {
    }

    public Student(String studentCode, String name, String birthday, String address, int idNumber, int phone, int gender, String email, int status, float mediumScore) {
        this.studentCode = studentCode;
        this.name = name;
        this.birthday = birthday;
        this.address = address;
        this.idNumber = idNumber;
        this.phone = phone;
        this.gender = gender;
        this.email = email;
        this.status = status;
        this.mediumScore = mediumScore;
    }

    public Student(String studentCode, String name, String birthday, String address, int idNumber, int phone, int gender, String email) {
        this.studentCode = studentCode;
        this.name = name;
        this.birthday = birthday;
        this.address = address;
        this.idNumber = idNumber;
        this.phone = phone;
        this.gender = gender;
        this.email = email;
    }
    

    public Student(int id, String studentCode, String name, String birthday, String address, int idNumber, int phone, int gender, String email, int status, float mediumScore) {
        this.id = id;
        this.studentCode = studentCode;
        this.name = name;
        this.birthday = birthday;
        this.address = address;
        this.idNumber = idNumber;
        this.phone = phone;
        this.gender = gender;
        this.email = email;
        this.status = status;
        this.mediumScore = mediumScore;
    }

    public Student(int id, String studentCode, String name, String birthday, String address, int idNumber, int phone, int gender, String email, int status) {
        this.id = id;
        this.studentCode = studentCode;
        this.name = name;
        this.birthday = birthday;
        this.address = address;
        this.idNumber = idNumber;
        this.phone = phone;
        this.gender = gender;
        this.email = email;
        this.status = status;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public float getMediumScore() {
        return mediumScore;
    }

    public void setMediumScore(float mediumScore) {
        this.mediumScore = mediumScore;
    }
    
}

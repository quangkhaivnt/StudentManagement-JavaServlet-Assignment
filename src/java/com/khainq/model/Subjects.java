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
public class Subjects {
    private int id;
    private String subjectCode;
    private String subjectName;
    private int status;

    public Subjects() {
    }

    public Subjects(String subjectCode, String subjectName, int status) {
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.status = status;
    }

    public Subjects(int id, String subjectCode, String subjectName, int status) {
        this.id = id;
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    
}

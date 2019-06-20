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
public class PointInformation {
    private int id;
    private String subjectCode;
    private String studentCode;
    private float point;

    public PointInformation() {
    }

    public PointInformation(String subjectCode, String studentCode, float point) {
        this.subjectCode = subjectCode;
        this.studentCode = studentCode;
        this.point = point;
    }

    public PointInformation(int id, String subjectCode, String studentCode, float point) {
        this.id = id;
        this.subjectCode = subjectCode;
        this.studentCode = studentCode;
        this.point = point;
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

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public float getPoint() {
        return point;
    }

    public void setPoint(float point) {
        this.point = point;
    }
    
}

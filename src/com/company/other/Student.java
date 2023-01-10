package com.company.other;

public class Student {
    private int studentId;
    private String firstName;
    private String lastName;
    private String major;
    private String date;
    Student (int studentId, String firstName, String lastName, String major, String date){
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.major = major;
        this.date = date;
    }
    public int getStudentId() {
        return studentId;
    }
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
    public String getFirstName() {return firstName;}
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {return lastName;}
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getMajor() {return major;}
    public void setMajor(String major) {
        this.major = major;
    }
    public String getDate() {return date;}
    public void setDate(String date) {this.date = date;}
}
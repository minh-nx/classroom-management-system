package com.company.other;

public class Exam {
    private String firstName;
    private String lastName;
    private int studentId;
    private String exam;
    Exam (int studentId, String firstName, String lastName, String exam){
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentId = studentId;
        this.exam = exam;
    }
    public int getStudentId() {
        return studentId;
    }
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
    public String getStudentFirstName() {return firstName;}
    public void setStudentFirstName(String Student_First_Name) {
        this.firstName = Student_First_Name;
    }
    public String getStudentLastName() {return lastName;}
    public void setStudentLastName(String Student_Last_Name) {
        this.lastName = Student_Last_Name;
    }
    public String getExamName() {return exam;}
    public void setExamName(String Exam_Name) {
        this.exam = Exam_Name;
    }
}


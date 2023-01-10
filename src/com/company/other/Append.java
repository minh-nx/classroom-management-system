package com.company.other;

import com.company.add.AddAttendance;
import com.company.add.AddStudent;
import com.company.edit.EditStudent;

class ModifyStudent {
    int id;
    String name;
    String major;

    public void edit() {
        EditStudent eds = new EditStudent();
        eds.setVisible(true);
    }
}

class ModifyAttendance extends ModifyStudent {

    public void display() {
        System.out.println("Student ID: " + id + ", Name: " +
                name + ", Major: " + major);
    }

    public void display(String date) {
        System.out.println("Student ID: " + id + ", Name: " +
                name + ", Major: " + major + ", Date: " + date);
    }
}

class AppendStudent {
    private int id;
    private String name;
    private String major;

    // getter - get id of student
    public int getId() {
        return id;
    }

    // getter - get name of student
    public String getName() {
        return name;
    }

    // getter - get major of student
    public String getMajor() {
        return major;
    }

    // setter - set major of student
    public void setMajor(String major) {
        this.major = major;
    }

    AppendStudent(){};

    public AppendStudent(int id, String name, String major) {
        this.id = id;
        this.name = name;
        this.major = major;
    }
    public void action() {
        System.out.println("Append a student:");

        // create an instance of AddStudent
        AddStudent addStudent = new AddStudent();
        // display the frame
        addStudent.setVisible(true);
    }

    public void display() {
        System.out.println("Student ID: " + this.id + ", Name: " +
                this.name + ", Major: " + this.major);
    }
}

class AppendAttendance extends AppendStudent{
    public void action() {
        System.out.println("Append an attendance:");

        // create an instance of AddAttendance
        AddAttendance addAttendance = new AddAttendance();
        // display the frame
        addAttendance.setVisible(true);
    }
}

class Execute {
    public static void main(String[] args) {
//        // create a new instance of AppendStudent
//        AppendStudent as = new AppendStudent(16209, "Minh Nguyen", "CSE");
//        System.out.println("Student ID: " + as.getId() + ", Name: " +
//                as.getName() + ", Major: " + as.getMajor());
//        as.setMajor("Java OOP");
//        as.display();

//        AppendAttendance appendAttendance = new AppendAttendance();
//
//        appendStudent.action();
//        appendAttendance.action();

        // create an object of a subclass
        ModifyAttendance mds = new ModifyAttendance();
        // access variables of superclass
        mds.id = 16209;
        mds.name = "Minh Nguyen";
        mds.major = "Java Project";
        // display frame of the superclass
        mds.edit();
        // access method of the superclass using subclass object
        mds.display();
        // access overloaded method of the subclass
        mds.display("2021-05-09");
    }
}
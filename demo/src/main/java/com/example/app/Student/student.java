package com.example.app.Student;

import java.util.List;

import com.example.app.user.user;

public class student extends user {
    private int student_id;

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public student(int student_id,String student_username,String student_password) {
        this.student_id = student_id;
        this.userName = student_username;
        this.password = student_password;
    }

    @Override
    public String toString() {
        return "student [userName=" + userName + ", password=" + password + ", student_id=" + student_id + "]";
    }

    public static void menu() {
        System.out.println("1 - see ma all marks");
    }

    public static String executeActionAccordingToStudentsChoise(int studentChoise,int student_id) {
       String resultToReturn = null;
       List <putEditMarks> myMarks = studentCRUDUtils.getMarks(student_id);
       resultToReturn = myMarks.toString();
       return resultToReturn;
    }
    
    
    
}

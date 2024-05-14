package com.example.app.Teacher;

import com.example.app.user.user;

public class teacher extends user {

    

    public teacher(String teacher_username, String teacher_password,String teacher_course, int teacher_course_id) {
        this.teacher_course = teacher_course;
        this.teacher_course_id = teacher_course_id;
        this.userName = teacher_username;
        this.password = teacher_password;
    }

    @Override
    public String toString() {
        return "teacher [userName=" + userName + ", password=" + password + ", teacher_course=" + teacher_course
                + ", teacher_course_id=" + teacher_course_id + "]";
    }
    String teacher_course;
    int teacher_course_id;
    public String getTeacher_course() {
        return teacher_course;
    }
    public void setTeacher_course(String teacher_course) {
        this.teacher_course = teacher_course;
    }
    public int getTeacher_course_id() {
        return teacher_course_id;
    }
    public void setTeacher_course_id(int teacher_course_id) {
        this.teacher_course_id = teacher_course_id;
    }

    public static String executeActionAccordingToTeachersChoise(int teacherChoise) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'executeActionAccordingToTeachersChoise'");
    }

    public static void menu() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'menu'");
    }

    
    
}

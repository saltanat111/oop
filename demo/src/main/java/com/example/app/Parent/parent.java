package com.example.app.Parent;

import com.example.app.user.user;

public class parent extends user{
    int student_id;

    @Override
    public String toString() {
        return "parent [userName=" + userName + ", password=" + password + ", student_id=" + student_id + "]";
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public parent(String parent_username, String parent_password, int student_id) {
        this.student_id = student_id;
        this.password = parent_password;
        this.userName = parent_username;
    }
    
    
}

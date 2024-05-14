package com.example.app.Admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.example.app.Parent.parent;
import com.example.app.Student.student;
import com.example.app.Teacher.teacher;
import com.example.app.user.user;


public class admin extends user {

    public static void menu() {
        System.out.println("1 - update student profile\n2 - update teacher profile\n3 - update parent profile\n4 - create student profile\n5 - create teacher profile\n6 - create parent profile\n7 - see all marks" );
    }
    public static String executeActionAccordingToAdminsChoise(int adminsChoise)
    {
        int student_id_for_new_student = 8;
        int teacher_id_for_new_teacher = 6;
        Scanner scan = new Scanner(System.in);
        String resultToReturn = "";
        if(adminsChoise == 7){
            List<com.example.app.Student.seeAllMarks> allMarks = adminCRUDUtils.getMarks("select marks.teacher_course, marks.mark, students.student_username from marks join students on students.student_id = marks.student_id");
            resultToReturn = allMarks.toString();
        }
        else if(adminsChoise == 6){
            System.out.println("Enter parent's username you want to create:");
            String parent_username = scan.next();
            System.out.println("Create password for this profile:");
            String parent_password = scan.next();
            System.out.println("Enter student's id:");
            int student_id = scan.nextInt();
            List<parent> createParent = adminCRUDUtils.createParentProfile(parent_username,parent_password,student_id);
            resultToReturn = createParent.toString();
        }
        else if (adminsChoise == 5) {
            System.out.println("Enter teacher's username you want to create:");
            String teacher_username = scan.next();
            System.out.println("Create password for this profile:");
            String teacher_password = scan.next();
            System.out.println("Enter teacher's course:");
            String teacher_course = scan.next();
            teacher_id_for_new_teacher++;
            List<teacher> createTeacher = adminCRUDUtils.createTeacherProfile(teacher_username,teacher_password,teacher_course,teacher_id_for_new_teacher);
            resultToReturn = createTeacher.toString();
        }
        else if(adminsChoise == 4){

            System.out.println("Enter username for the student's profile:");
            String username = scan.next();
            System.out.println("Enter password for this profile:");
            String password = scan.next();
            List<student> createStudent = adminCRUDUtils.createStudentProfile(student_id_for_new_student,username,password);
            student_id_for_new_student++;
            resultToReturn = createStudent.toString();
        }
        else if(adminsChoise == 3){
            System.out.println("Enter parent username you want to update:");
            String username = scan.next();
            System.out.println("Enter new password for the parent's profile:");
            String password = scan.next();
            System.out.println("Enter student's id you want to set:");
            int id = scan.nextInt();
            List<parent> updateParent = adminCRUDUtils.updateParentProfile(username,password,id);
            resultToReturn = updateParent.toString();
        }
        else if(adminsChoise == 2){
            System.out.println("Enter teacher's username you want to update :");
            String username = scan.next();
            System.out.println("Enter password you want to set:");
            String password = scan.next();
            System.out.println("Enter teacher's course name you want to set:");
            String course_name = scan.next();
            System.out.println("Enter teacher's course id you want to set:");
            int course_id = scan.nextInt();
            List<teacher> updateTeacher = adminCRUDUtils.updateTeacherProfile(username,password,course_name,course_id);
            resultToReturn = updateTeacher.toString();
        }
        else if(adminsChoise == 1){
            System.out.println("Enter student's id number you want to update :");
            int id = scan.nextInt();
            System.out.println("Enter username you want to set:");
            String username = scan.next();
            System.out.println("Enter password you want to set:");
            String password = scan.next();
            List<student> updateStudent = adminCRUDUtils.updateStudentProfile(id,username,password);
            resultToReturn = updateStudent.toString();
        }

        return resultToReturn;
    }
}

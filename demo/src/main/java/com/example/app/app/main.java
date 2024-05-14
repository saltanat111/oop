package com.example.app.app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.example.app.Student.student;
import com.example.app.Student.studentCRUDUtils;
import com.example.app.Admin.DBUtils;
import com.example.app.Admin.admin;
import com.example.app.Admin.adminCRUDUtils;
import com.example.app.Parent.parent;
import com.example.app.Student.putEditMarks;
import com.example.app.Student.seeAllMarks;
import com.example.app.Teacher.teacher;
import com.example.app.Teacher.teacherCRUDUtils;

public class main {
    public static void main(String[]args)
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter your role: \n 1 - admin \n 2 - teacher \n 3 - student \n 4 - parent");
        int role = scan.nextInt();
        scan.nextLine();
        while (role>4||role<1)
        {
            System.out.println("Please enter your role: \n 1 - admin \n 2 - teacher \n 3 - student \n 4 - parent");
            role = scan.nextInt();
        }
        System.out.println("Enter your username:");
        String username = scan.nextLine();
        System.out.println("Enter your password: ");
        String password = scan.nextLine();
        
        if (role == 1)
        {
            String adminPassword = getAdminPassword(username);
            if(password.equals(adminPassword))
            {
                System.out.println("Dear admin, welcome to the grading system!\nChoose what do you want to do and enter the number of the command you want to perform");
                admin.menu();
                int adminsChoise = scan.nextInt();
                while(adminsChoise>7||adminsChoise<1)
                {
                    System.out.println("Enter command only from 1 to 7");
                    adminsChoise = scan.nextInt();
                    scan.nextLine();
                }
                String result = admin.executeActionAccordingToAdminsChoise(adminsChoise);
                System.out.println(result);
            }
            
        }
        else if (role == 2)
        {
            String teacherPassword = getTeacherPassword(username);
            if(password.equals(teacherPassword))
            {
                System.out.println("Dear teacher welcome to the grading system!\nChoose what do you want to do and enter the number of the command:");
                teacher.menu();
                int teacherChoise = scan.nextInt();
                scan.nextLine();
                String result = teacher.executeActionAccordingToTeachersChoise(teacherChoise);
                System.out.println(result);
            }
            else
            {

            }
        }
        else if(role == 3)
        {
            String studentPassword = getStudentPassword(username);
            int student_id = getStudentIdAccordingToStudentsUsername(username);
            if(password.equals(studentPassword))
            {
                System.out.println("Dear student welcome to the grading system!\nChoose what do you want to do and enter the number of the command:");
                student.menu();
                int studentChoise = scan.nextInt();
                scan.nextLine();
                String result = student.executeActionAccordingToStudentsChoise(studentChoise,student_id);
                System.out.println(result);
            }
            else
            {

            }

        }
        else if(role == 4)
        {
            String parentPassword = getParentPassword(username);
            if(password.equals(parentPassword))
            {
                System.out.println("Dear parent welcome to the grading system!\nChoose what do you want to do and enter the number of the command:");
                parent.menu();
                int parentChoise = scan.nextInt();
                scan.nextLine();
                int child_id = getChildIdAccordingToParentUserName(username);
                String result = parent.executeActionAccordingToParentsChoise(parentChoise,child_id);
                System.out.println(result);
            }
            else
            {

            }

        }
        else
        {
            System.out.println("bye");
        }

    }
        
    private static int getChildIdAccordingToParentUserName(String username) {
        int child_id = 0;
        String queryToFindPasword = null;
             queryToFindPasword = "SELECT student_id from parents WHERE parent_username = ?";

        try (Connection connection = DBUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(queryToFindPasword))
            {
                preparedStatement.setString(1, username);
                ResultSet rs = preparedStatement.executeQuery();

                if (rs.next()){
                    child_id = rs.getInt("student_id");
                }
                    
            }catch(SQLException throwables){
                throwables.printStackTrace();;
            }
            return child_id;   
    }
    private static int getStudentIdAccordingToStudentsUsername (String username) {
        int myID = 0;
        String queryToFindPasword = null;
             queryToFindPasword = "SELECT student_id from students WHERE student_username = ?";

        try (Connection connection = DBUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(queryToFindPasword))
            {
                preparedStatement.setString(1, username);
                ResultSet rs = preparedStatement.executeQuery();

                if (rs.next()){
                    myID = rs.getInt("student_id");
                }
                    
            }catch(SQLException throwables){
                throwables.printStackTrace();;
            }
            return myID;   
    }

    public static String getAdminPassword(String username)
    {
        String password = null;
        String queryToFindPasword = null;
        
             queryToFindPasword = "SELECT * from admins WHERE admin_username = ?";

        
        try (Connection connection = DBUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(queryToFindPasword))
            {
                preparedStatement.setString(1, username);
                // preparedStatement.executeQuery();
                // PreparedStatement passwordd = connection.prepareStatement("Select admin_password from admins where admin_username = ?");
                ResultSet rs = preparedStatement.executeQuery();

                rs.next();
                    //System.err.println(rs.getString("admin_username"));
                    //System.err.println(rs.getString("admin_password"));

                    password = rs.getString("admin_password");
                
                // Integer i  = rs.next();

                // System.err.println("size = " + i);
                

            }catch(SQLException throwables){
                throwables.printStackTrace();;
            }
            return password;
    }
    public static String getTeacherPassword(String username)
    {
        String password = null;
        String queryToFindPasword = null;
             queryToFindPasword = "SELECT teacher_password from teachers WHERE teacher_username = ?";
        try (Connection connection = DBUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(queryToFindPasword)){
                preparedStatement.setString(1, username);
                ResultSet rs = preparedStatement.executeQuery();
                if(rs.next()){
                    password = rs.getString("teacher_password");

                }
            }catch(SQLException throwables){
                throwables.printStackTrace();;
            }
            return password;
    }
    public static String getParentPassword(String username)
    {
        String password = null;
        String queryToFindPasword = null;
             queryToFindPasword = "SELECT parent_password from parents WHERE parent_username = ?";
        try (Connection connection = DBUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(queryToFindPasword)){
                preparedStatement.setString(1, username);
                ResultSet rs = preparedStatement.executeQuery();
                if(rs.next()){
                    password = rs.getString("parent_password");

                }
            }catch(SQLException throwables){
                throwables.printStackTrace();;
            }
            return password;
    }
    public static String getStudentPassword(String username)
    {
        String password = null;
        String queryToFindPasword = null;
             queryToFindPasword = "SELECT student_password from students WHERE student_username = ?";
        try (Connection connection = DBUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(queryToFindPasword)){
                preparedStatement.setString(1, username);
                ResultSet rs = preparedStatement.executeQuery();
                if(rs.next()){
                    password = rs.getString("student_password");

                }
            }catch(SQLException throwables){
                throwables.printStackTrace();;
            }
            return password;
    }
    
                
}

package com.example.app.app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.example.app.Student.student;
import com.example.app.Admin.DBUtils;
import com.example.app.Admin.admin;
import com.example.app.Parent.parent;
import com.example.app.Teacher.teacher;

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
        String quit = "no";
        
        if (role == 1)
        {
            String adminPassword = getAdminPassword(username);
            if(password.equals(adminPassword))
            {
                
                System.out.println("Dear admin, welcome to the grading system!\nChoose what do you want to do and enter the number of the command you want to perform");
                while(quit.equals("no"))
                {
                    admin.menu();
                    int adminsChoise = scan.nextInt();
                    scan.nextLine();
                    while(adminsChoise>7||adminsChoise<1)
                    {
                        System.out.println("Enter command only from 1 to 7");
                        adminsChoise = scan.nextInt();
                        scan.nextLine();
                    }
                    admin.executeActionAccordingToAdminsChoise(adminsChoise);
                    System.out.println("Do you want to quit?(yes/no)");
                    quit = scan.nextLine();
                    if(quit.equals("yes"))
                    {
                        System.out.println("Goodbye");
                        System.exit(0);
                    }
                }
               
            }
            
        }
        else if (role == 2)
        {
            String teacherPassword = getTeacherPassword(username);
            if(password.equals(teacherPassword))
            {
                String courseName = getTeacherCourseNameAccordingToTeacherUserName(username);
                System.out.println("Dear "+courseName+" teacher welcome to the grading system!\nChoose what do you want to do and enter the number of the command:");
                while(quit.equals("no"))
                {
                    teacher.menu();
                    int teacherChoise = scan.nextInt();
                    scan.nextLine();
                    teacher.executeActionAccordingToTeachersChoise(teacherChoise,courseName);
                    System.out.println("Do you want to quit?(yes/no)");
                    quit = scan.nextLine();
                    if(quit.equals("yes"))
                    {
                        System.out.println("Goodbye");
                        System.exit(0);
                    }
                }
               
            }
        }
        else if(role == 3)
        {
            String studentPassword = getStudentPassword(username);
            int student_id = getStudentIdAccordingToStudentsUsername(username);
            if(password.equals(studentPassword))
            {
                System.out.println("Dear student welcome to the grading system!\nChoose what do you want to do and enter the number of the command:");
                while(quit.equals("no"))
                {
                    student.menu();
                    int studentChoise = scan.nextInt();
                    scan.nextLine();
                    student.executeActionAccordingToStudentsChoise(studentChoise,student_id);
                    System.out.println("Do you want to quit?(yes/no)");
                    quit = scan.nextLine();
                    if(quit.equals("yes"))
                    {
                        System.out.println("Goodbye");
                        System.exit(0);
                    }

                }
               
            }
        }
        else if(role == 4)
        {
            String parentPassword = getParentPassword(username);
            if(password.equals(parentPassword))
            {
                System.out.println("Dear parent welcome to the grading system!\nChoose what do you want to do and enter the number of the command:");
                while (quit.equals("no"))
                {
                    parent.menu();
                    int parentChoise = scan.nextInt();
                    scan.nextLine();
                    int child_id = getChildIdAccordingToParentUserName(username);
                    parent.executeActionAccordingToParentsChoise(parentChoise,child_id); quit = scan.nextLine();
                    if(quit.equals("yes"))
                    {
                        System.out.println("Goodbye");
                        System.exit(0);
                    }
                }
                
            }
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

    private static String getTeacherCourseNameAccordingToTeacherUserName(String username) {
        String courseName = "";
        String queryToFindPasword = null;
             queryToFindPasword = "SELECT teacher_course from teachers WHERE teacher_username = ?";

        try (Connection connection = DBUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(queryToFindPasword))
            {
                preparedStatement.setString(1, username);
                ResultSet rs = preparedStatement.executeQuery();

                if (rs.next()){
                    courseName = rs.getString("teacher_course");
                }
                    
            }catch(SQLException throwables){
                throwables.printStackTrace();;
            }
            return courseName;   
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
                ResultSet rs = preparedStatement.executeQuery();

                rs.next();
                    password = rs.getString("admin_password");
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

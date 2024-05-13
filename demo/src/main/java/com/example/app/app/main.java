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
        while (role>4||role<1)
        {
            System.out.println("Please enter your role: \n 1 - admin \n 2 - teacher \n 3 - student \n 4 - parent");
            role = scan.nextInt();
        }
        System.out.println("Enter your username:");
        String username = scan.next();
        System.out.println("Enter your password: ");
        String password = scan.next();
        String passwordCheck = passwordCheck(username, role);
        if (password.equals(passwordCheck))
        {
            System.out.println("welcome");
        }
        else{
            System.out.println("bye");
        }

        if (role == 1)
        {
            admin.menu();
        }

       // passwordCheck("admin", 1);













        // List <seeAllMarks> marks = teacherCRUDUtils.getMarks("select marks.teacher_course, marks.mark, students.student_username from marks join students on students.student_id = marks.student_id");
        // // //see all marks
        // for (int i = 0;i<marks.size();i++)
        // {
        //     System.out.println(marks.get(i));
        // }
        // //put mark
        // List <putEditMarks> mark = teacherCRUDUtils.putMarks(2,"history",89);
        // for (int i = 0;i<mark.size();i++)
        // {
        //     System.out.println(mark.get(i));
        // }
        // List <putEditMarks> mar = teacherCRUDUtils.editMarks(2,"biology",100);
        // for (int i = 0;i<mar.size();i++)
        // {
        //     System.out.println(mar.get(i));
        // }
           // List <putEditMarks> myMarks = studentCRUDUtils.getMarks(1);
            //System.out.println(myMarks);
            // List<student> newStudent = adminCRUDUtils.createStudentProfile(8, "elena", "123");
            // List<parent> newParent = adminCRUDUtils.createParentProfile( "elenaMama", "123M",8);
            // for (int i = 0;i<newParent.size();i++)
            // {
            //     System.out.println(newParent.get(i));
            // }
            
            //     System.out.println(newStudent.get(newStudent.size()-1));
                //List<teacher>  techer = adminCRUDUtils.createTeacherProfile("teacher", "123", "algebra", 9);
                //System.out.println(techer);
            
            
    
    }
        
    public static String passwordCheck(String username,int role)
    {
        String password = null;
        String queryToFindPasword = null;
        if (role == 1)
        {
             queryToFindPasword = "SELECT * from admins WHERE admin_username = ?";

        }
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
                
                
                
                
                // public static List<putEditMarks> getMarks(int student_id){
    //     List <putEditMarks> marks = new ArrayList<>();

    //     try (Connection connection = DBUtils.getConnection();
    //     PreparedStatement preparedStatement = connection.prepareStatement(queryAccordingToStudent_id)){
        
    //     preparedStatement.setInt(1, student_id);
    //     preparedStatement.executeQuery();

    //     //PreparedStatement allMarks = connection.prepareStatement("SELECT * FROM marks");
    //     ResultSet rs = preparedStatement.executeQuery();

    //     while(rs.next()){

    //         int student_idd = rs.getInt("student_id");
    //         String teacher_course = rs.getString("teacher_course");
    //         int mark = rs.getInt("mark");
    //         marks.add(new putEditMarks(student_idd,teacher_course,mark));
    //     }

    //     } catch (SQLException throwables){
    //         throwables.printStackTrace();
    //     }
    //     return marks;
    // }
}

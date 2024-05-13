package com.example.app.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.app.Admin.DBUtils;
import com.example.app.Parent.parent;
import com.example.app.Student.seeAllMarks;
import com.example.app.Student.student;
import com.example.app.Teacher.teacher;
import com.example.app.Student.putEditMarks;

public class adminCRUDUtils {

    private static final String INSERT_INTO_parents = "INSERT INTO parents (parent_username,parent_password,student_id) VALUES (? ,? ,?);";
    private static final String INSERT_INTO_students = "INSERT INTO students (student_id,student_username,student_password) VALUES (? ,? ,?);";
    private static final String INSERT_INTO_teachers = "INSERT INTO teachers (teacher_username,teacher_password,teacher_course,teacher_course_id) VALUES (?,?,?,?));";


    public static List<seeAllMarks> getMarks(String query){
        List <seeAllMarks> marks = new ArrayList<>();

        try (Connection connection = DBUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)){
        ResultSet rs = preparedStatement.executeQuery();

        while(rs.next()){

            String student_username = rs.getString("student_username");
            String teacher_course = rs.getString("teacher_course");
            int mark = rs.getInt("mark");
            marks.add(new seeAllMarks(student_username,teacher_course,mark));
        }

        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return marks;
    }

    public static List<parent> createParentProfile(String parent_username,String parent_password,int student_id){
        List <parent> createdParent = new ArrayList<>();
        try (Connection connection = DBUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_parents)){

        preparedStatement.setString(1,parent_username);
        preparedStatement.setString(2,parent_password);
        preparedStatement.setInt(3, student_id);
        preparedStatement.executeUpdate();

            PreparedStatement allMarks = connection.prepareStatement("SELECT * FROM parents");
            ResultSet rs = allMarks.executeQuery();


        while(rs.next()){
            int student_idn = rs.getInt("student_id");
            String parent_usernamee = rs.getString("parent_username");
            String parent_passwordd = rs.getString("parent_password");

            createdParent.add(new parent(parent_usernamee,parent_passwordd,student_idn));
        }

        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return createdParent;
    }

    public static List<student> createStudentProfile(int student_id, String student_username,String student_password){
        List <student> createdStudents = new ArrayList<>();
        try (Connection connection = DBUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_students)){

        preparedStatement.setInt(1, student_id);
        preparedStatement.setString(2, student_username);
        preparedStatement.setString(3,student_password);
        preparedStatement.executeUpdate();

            PreparedStatement studentCreated = connection.prepareStatement("SELECT * FROM students");
            ResultSet rs = studentCreated.executeQuery();


        while(rs.next()){
            int student_idn = rs.getInt("student_id");
            String student_usernamee = rs.getString("student_username");
            String student_passwordd = rs.getString("student_password");

            createdStudents.add(new student(student_idn,student_usernamee,student_passwordd));
        }

        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return createdStudents;
    }

    public static List<teacher> createTeacherProfile(String teacher_username, String teacher_password, String teacher_course,int teacher_course_id){
        List <teacher> createdTeacher = new ArrayList<>();
        try (Connection connection = DBUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_teachers)){

        preparedStatement.setString(1, teacher_username);
        preparedStatement.setString(2,teacher_password);
        preparedStatement.setString(3,teacher_course);
        preparedStatement.setInt(4,teacher_course_id);

        preparedStatement.executeUpdate();

            PreparedStatement teacherCreated = connection.prepareStatement("SELECT * FROM teachers");
            ResultSet rs = teacherCreated.executeQuery();


        while(rs.next()){
            int teacher_cousre_idd = rs.getInt("teacher_cousre_id");
            String teacher_usernamee = rs.getString("teacher_username");
            String teacher_passwordd = rs.getString("teacher_password");
            String teacher_coursee = rs.getString("teacher_course");

            createdTeacher.add(new teacher(teacher_usernamee,teacher_passwordd,teacher_coursee,teacher_cousre_idd));
        }

        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return createdTeacher;
    }
    
    


    
    
}

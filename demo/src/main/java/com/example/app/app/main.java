package com.example.app.app;

import java.util.List;

import com.example.app.Student.student;
import com.example.app.Student.studentCRUDUtils;
import com.example.app.Admin.adminCRUDUtils;
import com.example.app.Parent.parent;
import com.example.app.Student.putEditMarks;
import com.example.app.Student.seeAllMarks;
import com.example.app.Teacher.teacherCRUDUtils;

public class main {
    public static void main(String[]args)
    {
        // List <seeAllMarks> marks = teacherCRUDUtils.getMarks("select marks.teacher_course, marks.mark, students.student_username from marks join students on students.student_id = marks.student_id");
        // //see all marks
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

            
            
    }
    
}

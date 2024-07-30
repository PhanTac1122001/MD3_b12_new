package com.example.demo1.controller;

import com.example.demo1.entity.Student;
import com.example.demo1.service.IStudentService;
import com.example.demo1.service.impl.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/student")
public class StudentController extends HttpServlet {
    IStudentService studentService=new StudentServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action=req.getParameter("action");
        action=action==null?"":action;
        switch (action){
            case "add":
                req.getRequestDispatcher("/WEB-INF/views/add_student.jsp").forward(req,resp);
                break;
            case "update":{
                Integer id=Integer.parseInt(req.getParameter("id"));
                Student student=studentService.findById(id);
                req.setAttribute("student",student);
                req.getRequestDispatcher("/WEB-INF/views/update_student.jsp").forward(req,resp);
                break;}
            case "delete":{
                Integer id=Integer.parseInt(req.getParameter("id"));
                studentService.deleteId(id);
                req.setAttribute("students",studentService.findAll());
                req.getRequestDispatcher("/WEB-INF/views/students.jsp").forward(req,resp);
                break;}
            case "detail":
            {
                Integer id=Integer.parseInt(req.getParameter("id"));
                req.setAttribute("detail",studentService.findById(id));
                req.getRequestDispatcher("/WEB-INF/views/detail_student.jsp").forward(req,resp);
                break;}

            default:
                req.setAttribute("students",studentService.findAll());
                req.getRequestDispatcher("/WEB-INF/views/students.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action=req.getParameter("action");
        action=action==null?"":action;
        switch (action){
            case "add":{
                    String name=req.getParameter("name");
                    String email=req.getParameter("email");
                    String address=req.getParameter("address");
                    String phone=req.getParameter("phone");
                    Boolean status=Boolean.parseBoolean(req.getParameter("status"));
                Student student=new Student(null,name,email,address,phone,status);
                    studentService.addNewStudent(student);
                    resp.sendRedirect("/student");
                break;}
            case "update": {
                Integer id=Integer.parseInt(req.getParameter("id"));
                String name = req.getParameter("name");
                String email = req.getParameter("email");
                String address = req.getParameter("address");
                String phone = req.getParameter("phone");
                Boolean status = Boolean.parseBoolean(req.getParameter("status"));
                Student student = new Student(id, name, email, address, phone, status);
                studentService.updateNewStudent(student);
                resp.sendRedirect("/student");
                break;
            }
            case "search":
                String studentName = req.getParameter("studentName");
                List<Student> studentResults = studentService.sreachId(studentName);
                req.setAttribute("students", studentResults);
                req.getRequestDispatcher("/WEB-INF/views/students.jsp").forward(req, resp);
                break;

            default:
                req.setAttribute("students",studentService.findAll());
                req.getRequestDispatcher("/WEB-INF/views/students.jsp").forward(req,resp);
        }
    }
}

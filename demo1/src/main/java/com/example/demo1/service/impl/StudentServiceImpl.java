package com.example.demo1.service.impl;

import com.example.demo1.dao.IStudentDAO;
import com.example.demo1.dao.impl.StudentDaoImpl;
import com.example.demo1.entity.Student;
import com.example.demo1.service.IStudentService;
import com.example.demo1.utils.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentServiceImpl implements IStudentService {

    IStudentDAO studentDAO=new StudentDaoImpl();
    @Override
    public List<Student> findAll() {
        return studentDAO.findAll();
    }

    @Override
    public boolean addNewStudent(Student student) {
        return studentDAO.addNewStudentData(student);
    }

    @Override
    public boolean updateNewStudent(Student student) {
        return studentDAO.updateNewStudentData(student);
    }

    @Override
    public Student findById(Integer id) {
       return studentDAO.findById(id);
    }

    @Override
    public Student deleteId(Integer id) {
        return studentDAO.deleteId(id);
    }

    @Override
    public List<Student> sreachId(String studentName) {
        return studentDAO.sreachId(studentName);
    }


}

package com.example.demo1.dao;

import com.example.demo1.entity.Student;

import java.util.ArrayList;
import java.util.List;

public interface IStudentDAO {

    List<Student> findAll();

    boolean addNewStudentData(Student student);

    boolean updateNewStudentData(Student student);

    Student deleteId(Integer id);

    Student findById(Integer id);

    List<Student> sreachId(String studentName);
}

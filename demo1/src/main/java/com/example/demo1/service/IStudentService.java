package com.example.demo1.service;

import com.example.demo1.entity.Student;

import java.util.List;

public interface IStudentService {
    List<Student> findAll();
    boolean addNewStudent(Student student);

    boolean updateNewStudent(Student student);

    Student findById(Integer id);

    Student deleteId(Integer id);

    List<Student> sreachId(String studentName);

}

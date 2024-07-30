package com.example.demo1.dao.impl;

import com.example.demo1.dao.IStudentDAO;
import com.example.demo1.entity.Student;
import com.example.demo1.utils.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements IStudentDAO {


    @Override
    public List<Student> findAll() {
        //1.Tạo kết nối
        Connection connection = ConnectionDB.openConnection();
        List<Student> students = new ArrayList<>();
        try {
            //2.Thực hiện truy vấn
            PreparedStatement sql = connection.prepareStatement("select * from students");
            ResultSet rs = sql.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setEmail(rs.getString("email"));
                student.setAddress(rs.getString("address"));
                student.setPhone(rs.getString("phone"));
                student.setStatus(rs.getBoolean("status"));
                students.add(student);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            //3.Đóng kết nối
            ConnectionDB.closeConnection(connection);
        }
        return students;
    }

    @Override
    public boolean updateNewStudentData(Student student) {
        Connection connection = ConnectionDB.openConnection();
        try {
            PreparedStatement sql=connection.prepareStatement("UPDATE students set name=?,email=?,address=?,phone=?,status=? where id=?");
            sql.setString(1,student.getName());
            sql.setString(2,student.getEmail());
            sql.setString(3,student.getAddress());
            sql.setString(4,student.getPhone());
            sql.setBoolean(5,student.getStatus());
            sql.setInt(6,student.getId());
            sql.executeUpdate();
            return true;

        }catch (SQLException e){
            throw new RuntimeException(e);
        }finally {
            ConnectionDB.closeConnection(connection);
        }
    }

    @Override
    public Student deleteId(Integer id) {
        Connection connection = ConnectionDB.openConnection();
        try {
            PreparedStatement sql=connection.prepareStatement("Delete from students where id=?");
            sql.setInt(1,id);
            sql.executeUpdate();
            return null;

        }catch (SQLException e){
            throw new RuntimeException(e);
        }finally {
            ConnectionDB.closeConnection(connection);
        }
    }

    @Override
    public Student findById(Integer id) {
        Connection connection=ConnectionDB.openConnection();
        try {
            PreparedStatement sql=connection.prepareStatement("select * from students where id=?");
            sql.setInt(1,id);
            ResultSet rs=sql.executeQuery();
            if(rs.next()){
                Student student =new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setEmail(rs.getString("email"));
                student.setAddress(rs.getString("address"));
                student.setPhone(rs.getString("phone"));
                student.setStatus(rs.getBoolean("status"));
                return student;
            }else {
                return null;
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        finally {
            ConnectionDB.closeConnection(connection);
        }
    }

    @Override
    public List<Student> sreachId(String studentName) {
        List<Student> students = new ArrayList<>();
        //1. Mở kết nối đến database
        Connection con = ConnectionDB.openConnection();

        try {
            if (studentName == null || studentName.isEmpty()) {
                studentName = "%";
            }
            else {
                studentName = "%" + studentName + "%";
            }
            PreparedStatement ps = con.prepareStatement("select * from students where name like ?");
            ps.setString(1, "%" + studentName + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Student s = new Student();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setEmail(rs.getString("email"));
                s.setAddress(rs.getString("address"));
                s.setPhone(rs.getString("phone"));
                s.setStatus(rs.getBoolean("status"));
                students.add(s);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            //3. Đóng kết nối
            ConnectionDB.closeConnection(con);
        }
        return students;

    }

    @Override
    public boolean addNewStudentData(Student student) {
        //1.Tạo kết nối
        Connection connection = ConnectionDB.openConnection();
        try {
            //2.Thực hiện câu lệnh thêm mới
            PreparedStatement sql = connection.prepareStatement("INSERT INTO students (name,email,address,phone,status) values (?,?,?,?,?)");
            sql.setString(1, student.getName());
            sql.setString(2, student.getEmail());
            sql.setString(3, student.getAddress());
            sql.setString(4, student.getPhone());
            sql.setBoolean(5, student.getStatus());
            sql.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }

    }
}

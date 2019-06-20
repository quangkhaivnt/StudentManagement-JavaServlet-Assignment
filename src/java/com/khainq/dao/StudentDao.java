/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khainq.dao;

import com.khainq.model.Student;
import com.khainq.util.DbUtil;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class StudentDao {
    private Connection connection;
    
    public StudentDao() throws SQLException {
        connection = DbUtil.getConnection();
    }
    
    
    
    private static final String INSERT_STUDENT_SQL = "INSERT INTO student" + "  (studentCode, name, birthday, address, idNumber, phone, gender, email) VALUES " +
        " (?, ?, ?, ?, ?, ?, ?, ?);";

    private static final String SELECT_STUDENT_BY_ID = "select id,studentCode,name,birthday,address,idNumber,phone,gender,email,mediumScore from student where id =?";
    private static final String SELECT_ALL_STUDENT = "select student.id,student.studentCode,student.name,student.birthday,student.address,student.idNumber,student.phone,student.gender,student.email,student.status,avg(pointinformation.point) as point from student left join pointinformation on student.studentCode = pointinformation.studentCode group by pointinformation.studentCode";
    private static final String DELETE_STUDENT_SQL = "delete from student where id = ?;";
    private static final String UPDATE_STUDENT_SQL = "update student set studentCode = ?,name = ?,birthday = ?,address = ?,idNumber = ?,phone = ?,gender = ?,email = ?,mediumScore = ? where id = ?";
    private static final String SEARCH_STUDENT_SQL = "select * from student where name = ?";
    public void insertStudent(Student stu) throws SQLException {
        System.out.println(INSERT_STUDENT_SQL);
        // try-with-resource statement will auto close the connection.
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENT_SQL)) {
            preparedStatement.setString(1, stu.getStudentCode());
            preparedStatement.setString(2, stu.getName());
            preparedStatement.setString(3, stu.getBirthday());
            preparedStatement.setString(4, stu.getAddress());
            preparedStatement.setInt(5, stu.getIdNumber());
            preparedStatement.setInt(6, stu.getPhone());
            preparedStatement.setInt(7, stu.getGender());
            preparedStatement.setString(8, stu.getEmail());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }
    
    public Student selectStudent(int id) {
        Student stu = null;
        try (
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENT_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String studentCode = rs.getString("studentCode");
                String name = rs.getString("name");
                String birthday = rs.getString("birthday");
                String address = rs.getString("address");
                int idNumber = rs.getInt("idNumber");
                int phone = rs.getInt("phone");
                int gender = rs.getInt("gender");
                String email = rs.getString("email");
                int status = rs.getInt("status");
                float mediumScore = rs.getFloat("mediumScore");
                stu = new Student(id,studentCode,name,birthday,address,idNumber,phone,gender,email,status,mediumScore);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return stu;
    }
    
    public List <Student> selectAllStudent() {

        List <Student> stu = new ArrayList <> ();
        try (
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_STUDENT)) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id");
                String studentCode = rs.getString("studentCode");
                String name = rs.getString("name");
                String birthday = rs.getString("birthday");
                String address = rs.getString("address");
                int idNumber = rs.getInt("idNumber");
                int phone = rs.getInt("phone");
                int gender = rs.getInt("gender");
                String email = rs.getString("email");
                int status = rs.getInt("status");
                float mediumScore = rs.getFloat("point");
                stu.add(new Student(id, studentCode,name,birthday,address,idNumber,phone,gender,email,status,mediumScore));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return stu;
    }
    
    public List<Student> searchStudent(String name) {

        List<Student> stu = new ArrayList<>();
        try (
            PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_STUDENT_SQL);) {
            preparedStatement.setString(1, name);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();
            Student student = new Student();
            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                student.setId(rs.getInt("id"));
                student.setStudentCode(rs.getString("studentCode"));
                student.setName(rs.getString("name"));
                student.setBirthday(rs.getString("birthday"));
                student.setAddress(rs.getString("address"));
                student.setIdNumber(rs.getInt("idNumber"));
                student.setPhone(rs.getInt("phone"));
                student.setGender(rs.getInt("gender"));
                student.setEmail(rs.getString("email"));
                student.setStatus(rs.getInt("status"));
                student.setMediumScore(rs.getFloat("mediumScore"));
                stu.add(student);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return stu;
    }
    
    
    public boolean deleteStudent(int id) throws SQLException {
        boolean rowDeleted;
        try (PreparedStatement statement = connection.prepareStatement(DELETE_STUDENT_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }
    
    public boolean updateStudent(Student stu) throws SQLException {
        boolean rowUpdated;
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_STUDENT_SQL);) {
            statement.setString(1, stu.getStudentCode());
            statement.setString(2, stu.getName());
            statement.setString(3, stu.getBirthday());
            statement.setString(4, stu.getAddress());
            statement.setInt(5, stu.getIdNumber());
            statement.setInt(6, stu.getPhone());
            statement.setInt(7, stu.getGender());
            statement.setString(8, stu.getEmail());
            statement.setInt(9, stu.getStatus());
            statement.setInt(10, stu.getId());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
    
      
    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}

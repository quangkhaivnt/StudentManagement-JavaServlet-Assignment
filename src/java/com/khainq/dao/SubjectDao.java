/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khainq.dao;

import com.khainq.model.Student;
import com.khainq.model.Subjects;
import com.khainq.util.DbUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class SubjectDao {
    private Connection connection;
    
    public SubjectDao() throws SQLException {
        connection = DbUtil.getConnection();
    }
    
    private static final String INSERT_SUBJECT_SQL = "INSERT INTO subjects" + "  (subjectCode,subjectName,status) VALUES " +
        " (?, ?, ?);";

    private static final String SELECT_SUBJECT_BY_ID = "select id,subjectCode,subjectName,status from subjects where id =?";
    private static final String SELECT_ALL_SUBJECT = "select * from subjects";
    private static final String DELETE_SUBJECT_SQL = "delete from subjects where id = ?;";
    private static final String UPDATE_SUBJECT_SQL = "update subjects set subjectCode = ?,subjectName = ?,status = ? where id = ?";
    
    
    public void insertSubject(Subjects sub) throws SQLException {
        System.out.println(INSERT_SUBJECT_SQL);
        // try-with-resource statement will auto close the connection.
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SUBJECT_SQL)) {
            preparedStatement.setString(1, sub.getSubjectCode());
            preparedStatement.setString(2, sub.getSubjectName());
            preparedStatement.setInt(3, sub.getStatus());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }
    
    public Subjects selectSubject(int id) {
        Subjects sub = null;
        try (
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SUBJECT_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String subjectCode = rs.getString("subjectCode");
                String subjectName = rs.getString("subjectName");
                int status = rs.getInt("status");
                sub = new Subjects(id,subjectCode,subjectName,status);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return sub;
    }
    
    public List <Subjects> selectAllSubject() {

        List <Subjects> sub = new ArrayList <> ();
        try (
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_SUBJECT);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id");
                String subjectCode = rs.getString("subjectCode");
                String subjectName = rs.getString("subjectName");
                int status = rs.getInt("status");
                sub.add(new Subjects(id, subjectCode,subjectName,status));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return sub;
    }
    
    public boolean deleteSubject(int id) throws SQLException {
        boolean rowDeleted;
        try (PreparedStatement statement = connection.prepareStatement(DELETE_SUBJECT_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }
    
    public boolean updateSubject(Subjects sub) throws SQLException {
        boolean rowUpdated;
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_SUBJECT_SQL);) {
            statement.setString(1, sub.getSubjectCode());
            statement.setString(2, sub.getSubjectName());
            statement.setInt(3, sub.getStatus());
            statement.setInt(4, sub.getId());

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

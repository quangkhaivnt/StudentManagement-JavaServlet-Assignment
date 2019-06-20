/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khainq.dao;

import com.khainq.model.PointInformation;
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
public class PointInformationDao {
    private Connection connection;
    
    public PointInformationDao() throws SQLException {
        connection = DbUtil.getConnection();
    }
    
    private static final String INSERT_POINTINFORMATION_SQL = "INSERT INTO pointinformation" + "  (subjectCode,studentCode,point) VALUES " +
        " (?, ?, ?);";

    private static final String SELECT_POINTINFORMATION_BY_ID = "select id,subjectCode,studentCode,point from pointinformation where id =?";
    private static final String SELECT_ALL_POINTINFORMATION = "select * from pointinformation";
    private static final String DELETE_POINTINFORMATION_SQL = "delete from pointinformation where id = ?;";
    private static final String UPDATE_POINTINFORMATION_SQL = "update pointinformation set subjectCode = ?,studentCode = ?,point = ? where id = ?";
    
    
    public void insertPointInformation(PointInformation pi) throws SQLException {
        System.out.println(INSERT_POINTINFORMATION_SQL);
        // try-with-resource statement will auto close the connection.
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_POINTINFORMATION_SQL)) {
            preparedStatement.setString(1, pi.getSubjectCode());
            preparedStatement.setString(2, pi.getStudentCode());
            preparedStatement.setFloat(3, pi.getPoint());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }
    
    public PointInformation selectPointInformation(int id) {
        PointInformation pi = null;
        try (
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_POINTINFORMATION_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String subjectCode = rs.getString("subjectCode");
                String studentCode = rs.getString("studentCode");
                float status = rs.getFloat("point");
                pi = new PointInformation(id,subjectCode,studentCode,status);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return pi;
    }
    
    public List <PointInformation> selectAllPointInformation() {

        List <PointInformation> pi = new ArrayList <> ();
        try (
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_POINTINFORMATION);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id");
                String subjectCode = rs.getString("subjectCode");
                String studentCode = rs.getString("studentCode");
                float point = rs.getFloat("point");
                pi.add(new PointInformation(id, subjectCode,studentCode,point));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return pi;
    }
    
    public boolean deletePointInformation(int id) throws SQLException {
        boolean rowDeleted;
        try (PreparedStatement statement = connection.prepareStatement(DELETE_POINTINFORMATION_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }
    
    public boolean updatePointInformation(PointInformation pi) throws SQLException {
        boolean rowUpdated;
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_POINTINFORMATION_SQL);) {
            statement.setString(1, pi.getSubjectCode());
            statement.setString(2, pi.getStudentCode());
            statement.setFloat(3, pi.getPoint());
            statement.setInt(4, pi.getId());

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

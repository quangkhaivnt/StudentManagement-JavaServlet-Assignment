/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khainq.dao;

import com.khainq.common.MD5Library;
import com.khainq.model.Subjects;
import com.khainq.model.User;
import com.khainq.util.DbUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.jasper.tagplugins.jstl.core.Catch;

/**
 *
 * @author admin
 */
public class UserDao {
    private Connection connection;
    public UserDao() throws SQLException {
        connection = DbUtil.getConnection();
    }
    
    private static final String INSERT_USER_SQL = "INSERT INTO users" + "  (username,password) VALUES " +
        " (?, ?);";

    private static final String SELECT_USER_BY_ID = "select id,username,password from users where id =?";
    private static final String SELECT_ALL_USER = "select * from users";
    private static final String DELETE_USER_SQL = "delete from users where id = ?;";
    private static final String UPDATE_USER_SQL = "update users set username = ?,password = ? where id = ?";
    private static final String SEARCH_USER_SQL = "select * from users where username = ?";
    public void insertUser(User user) throws SQLException {
        System.out.println(INSERT_USER_SQL);
        // try-with-resource statement will auto close the connection.
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, MD5Library.md5(user.getPassword()));
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }
    
    public User selectUser(int id) {
        User user = null;
        try (
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                user = new User(username,password);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return user;
    }
    
    public List<User> searchUser(String name){
        List<User> user = new ArrayList<>();
        try (
            PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_USER_SQL);) {
            preparedStatement.setString(1, name);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                user.add(new User(id, username, password));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return user;
             
    }
    
    
    public List<User> selectAllUser(){

        List<User> user = new ArrayList<>();
        try (
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USER);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                user.add(new User(id, username, password));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return user;
    }
    
    
    public boolean deleteUser(int id) throws SQLException {
        boolean rowDeleted;
        try (PreparedStatement statement = connection.prepareStatement(DELETE_USER_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }
    
    public boolean updateUser(User user) throws SQLException {
        boolean rowUpdated;
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_USER_SQL);) {
            statement.setString(1, user.getUsername());
            statement.setString(2, MD5Library.md5(user.getPassword()));
            statement.setInt(3, user.getId());

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

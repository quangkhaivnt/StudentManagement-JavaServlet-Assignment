/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khainq.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author admin
 */
public class DbUtil {
    private static Connection conn = null;
    
    public static Connection getConnection() throws SQLException{
        final String url = "jdbc:mysql://localhost:3306/studentmanager";
        final String user = "root";
        final String password = "";
        if(conn != null){
            return conn;
        }else{
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection(url,user,password);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return conn;
    }
}

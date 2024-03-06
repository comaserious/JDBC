package com.ohgiraffers.section01.connection;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Application02 {

    public static void main(String[] args) {

        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost/employee";
        String user = "ohgiraffers";
        String pass = "ohgiraffers";
        // 여전히 개인정보가 노출이 되어 있다

        Connection connection = null;

        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url,user,pass);
            System.out.println("connection = " + connection);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }


        // try-catch 문과 close() 문이 반복된다


    }
}

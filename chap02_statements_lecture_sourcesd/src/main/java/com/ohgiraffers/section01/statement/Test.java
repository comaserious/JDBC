package com.ohgiraffers.section01.statement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Test {

    public static void main(String[] args) {

        Connection con = getConnection();

        Statement stmt = null;

        ResultSet rset = null;
        Scanner scr = new Scanner(System.in);
        int salary = scr.nextInt();
        String query = "select EMP_NO,EMP_NAME, SALARY from employee where SALARY > '"+salary+"'";
        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery(query);
            while(rset.next()){
            System.out.println(rset.getString("EMP_NO")+" , "+rset.getString("EMP_NAME")+" , "+rset.getString("SALARY"));}




        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally{
            close(con);
            close(stmt);
            close(rset);
        }


    }
}

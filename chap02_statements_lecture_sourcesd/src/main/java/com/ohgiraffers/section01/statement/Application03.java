package com.ohgiraffers.section01.statement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application03 {

    public static void main(String[] args) {
        System.out.print("조회하실 지원의 사번을 입력해주세요 : ");
        Scanner scr = new Scanner(System.in);
        Connection con = getConnection();

        Statement stmt = null;

        ResultSet rset = null;

        String empId = scr.nextLine();
        String query = "select EMP_ID,EMP_NAME FROM EMPLOYEE WHERE EMP_ID = '"+empId+"'";

        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery(query);
            if(rset.next()){
                System.out.println(rset.getString("EMP_ID")+" , "+rset.getString("EMP_NAME"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            close(con);
            close(stmt);
            close(rset);
        }


    }
}

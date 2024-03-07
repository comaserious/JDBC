package com.ohgiraffers.section01.statement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application02 {

    public static void main(String[] args) {

        /*필기.
        *  순서
        *   1. Connection 인터페이스 생성
        *   2. Statement 생성
        *   3. ResultSet 생성
        *   4. Connection 의 createStatement() 메소드를 이용해서 Statement 인스턴스 생성
        *   5. executeQuery() 로 쿼리문을 실행하고 결과를 ResultSet 으로 반환받음
        *   6. ResultSet 에 담긴 결과물을 컬럼 이름(label) 을 이용해서 꺼내어 출력
        *   7. 마지막으로 사요애한 자원 반납( Connection, Statement, ResultSet)*/

        // 1. Connection 인터페이스 생성

        Connection con = getConnection();

        // 2. Statement 생성

        Statement stmt =null;

        // 3. ResultSet 생성

        ResultSet rset = null;

        // 4. Connection 의 createStatement 메소드를 통해 Statement 인스턴스 생성

        try {
            stmt = con.createStatement();

            String empId = "202";
            String query = "select EMP_ID, EMP_NAME FROM employee where EMP_ID = '" + empId+"'";

            // 5.  executeQuery() 로 쿼리문을 실행하고 결과를 ResultSet 으로 반환받음

            rset=stmt.executeQuery(query);

            //6. ResultSet 에 담긴 결과물을 컬럼 이름(label) 을 이용해서 꺼내어 출력

            if(rset.next()){
                System.out.println(rset.getString("EMP_ID")+" , "+rset.getString("EMP_NAME"));
            }





        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            // 7. 마지막으로 사요애한 자원 반납( Connection, Statement, ResultSet)

            close(con);
            close(rset);
            close(stmt);

        }


    }
}

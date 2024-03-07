package com.ohgiraffers.section01.statement;

import com.ohgiraffers.common.JDBCTemplate;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.ohgiraffers.common.JDBCTemplate.close;

public class Application01 {

    public static void main(String[] args) {

        /*수업목표. statement 에 대해 이해하고 사용할 수 있다*/

        /*필기.
        *  statement 란?
        *  SQL문을 저장하고 실행한 뒤 결과를 받아주는
        *  메소드들이 묶여 있는 타입의 클래스 */

        Connection con = JDBCTemplate.getConnection();

        /*필기. 쿼리문을 저장하고 실행하는 기능을 하는 용도의 인터페이스*/
        Statement stmt = null;

        /*필기. select 조회의 결과 집합을 받아 올 용도의 인터페이스*/
        ResultSet rset = null;

        try {
            /*필기. connection 을 이용해서 statement 인스턴스 생성*/
            stmt = con.createStatement();

            /*필기. executeQuery() 메소드를 호출해서 SQL 문을 수행(SQL 문을 String 형태로 전달)*/
            rset=stmt.executeQuery("select EMP_ID, EMP_NAME from employee");

            while(rset.next()){
                /*필기. next() : ResultSet 의 커서 위치를 하나씩 내리면서 존재하면 true 그렇지않으면 false*/
                System.out.println(rset.getString("EMP_ID")+" , "+rset.getString("EMP_NAME"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally{
            /*Connection , Statement , ResultSet 을 통한 외부데이터와의 통로를 전부다
            * 오버로딩된 메소드를 통해서 닫아주었다*/
            close(con);
            close(stmt);
            close(rset);
        }

    }
}

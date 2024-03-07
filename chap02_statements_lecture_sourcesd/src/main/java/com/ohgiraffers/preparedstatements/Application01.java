package com.ohgiraffers.preparedstatements;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application01 {

    public static void main(String[] args) {

        /*수업목표. PreparedStatement 에 대해 이해하고 사용할 수있다*/

        /*필기.
        *  PreparedStatement 란 ?
        *  Statement 를 상속받은 interface
        *  더욱 효율적인 작업을 진행 할 수 있다
        *  완성된 SQL 문과 미완성된 SQL 문 동시에 사용가능하다
        *  위치홀더(placeholder) : ? */

        Connection con = getConnection();

        PreparedStatement pstmt = null;

        ResultSet rset = null;

        try {
            pstmt = con.prepareStatement("select EMP_ID,EMP_NAME FROM EMPLOYEE");
            rset = pstmt.executeQuery();
            while(rset.next()){
                System.out.println(rset.getString("EMP_ID")+" , "+rset.getString("EMP_NAME"));
            }



        } catch (SQLException e) {


        }finally{
            close(con);
            close(pstmt);
            close(rset);
        }


    }
}

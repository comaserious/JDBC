package com.ohgiraffers.section01.insert;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application01 {

    public static void main(String[] args) {

        Connection con = getConnection();

        PreparedStatement pstmt = null;

        int result = 0 ;  // crud 가 일어나면 값을 1씩 증가 시켜 변화를 시각화 하려고 만듦

        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/menu-query.xml"));

            String query = prop.getProperty("insertMenu");

            System.out.println("query = " + query);

            pstmt = con.prepareStatement(query);

            pstmt.setString(1,"봉골레 청국장");
            pstmt.setInt(2,50000);
            pstmt.setInt(3,1);
            pstmt.setString(4,"Y");

            result = pstmt.executeUpdate();     // 행이 변화하면 int 값을 반환해준다


        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(pstmt);
            close(con);
        }

        System.out.println("result = " + result);
    }
}

package com.ohgiraffers.section03.delete;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application01 {

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Connection con = getConnection();
        PreparedStatement pstmt = null;
        int result = 0;

        Properties prop = new Properties();
        try {
            System.out.print("없앨 메뉴코드를 적으시오 : ");
            int code = Integer.parseInt(br.readLine());
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/menu-query.xml"));
            pstmt = con.prepareStatement(prop.getProperty("Delete"));
            pstmt.setInt(1,code);
            result = pstmt.executeUpdate();





        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            close(con);
            close(pstmt);
        }
        if(result == 0) {
            System.out.println("실패");
        }
        else{
            System.out.println("성공");
        }
    }
}

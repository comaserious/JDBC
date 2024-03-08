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

public class Application {

    public static void main(String[] args) {

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        int result = 0 ;

        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/menu-query.xml"));

            pstmt = con.prepareStatement(prop.getProperty("Delete"));

            BufferedReader br= new BufferedReader(new InputStreamReader(System.in));

            System.out.print("삭제할 메뉴코드를 입력하세요 : ");
            int cod = Integer.parseInt(br.readLine());

            pstmt.setInt(1,cod);

            result = pstmt.executeUpdate();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            close(con);
            close(pstmt);

        }
        if(result == 0 ){
            System.out.println("실패");
        }
        else{
            System.out.println("Succes");
        }
    }
}

package com.ohgiraffers.section02.update;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application01 {

    public static void main(String[] args) {

        Connection con = getConnection();

        PreparedStatement pstmt = null;
        int result = 0;

        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/menu-query.xml"));
            String query = prop.getProperty("updateMenu");

            Scanner scr = new Scanner(System.in);
            System.out.print("변경할 메뉴코드를 입력하세요 : ");
            int menuCode = scr.nextInt();
            System.out.print("변경할 메뉴의 이름을 입력하세요 : ");
            scr.nextLine();
            String name = scr.nextLine();
            System.out.print("변경할 메뉴의 가격을 입력하세요 : ");
            int price = scr.nextInt();
            pstmt = con.prepareStatement(query);
            pstmt.setString(1 ,name);
            pstmt.setInt(2,price);
            pstmt.setInt(3,menuCode);

            result = pstmt.executeUpdate();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(pstmt);
            close(con);


        }
        if(result!=0){
            System.out.println("메뉴 수정 성공");
        }else{
            System.out.println("메뉴 수정 실패");
        }
    }
}

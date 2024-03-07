package com.ohgiraffers.section01.insert;

import com.ohgiraffers.model.dto.MenuDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application02 {

    public static void main(String[] args) {

        Scanner scr= new Scanner(System.in);
        System.out.print("메뉴의 이름을 입력하세요 : ");
        String name = scr.nextLine();
        System.out.print("메뉴의 가격을 입력하세요 : ");
        int price = scr.nextInt();
        System.out.print("카테고리 코드를 입력해주세요 : ");
        int categoryCode = scr.nextInt();
        scr.nextLine();
        System.out.print("판메 여부를 결정해주세요(Y/N) : ");
        String orderableStatus = scr.nextLine().toUpperCase();

        MenuDTO newMenu = new MenuDTO();
        newMenu.setMenuName(name);
        newMenu.setMenuPrice(price);
        newMenu.setCategoryCode(categoryCode);
        newMenu.setOrderableStatus(orderableStatus);

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        int result = 0 ;
        Properties prop = new Properties();
        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/menu-query.xml"));
            String query = prop.getProperty("insertMenu");
            pstmt = con.prepareStatement(query);
            pstmt.setString(1,newMenu.getMenuName());
            pstmt.setInt(2,newMenu.getMenuPrice());
            pstmt.setInt(3,newMenu.getCategoryCode());
            pstmt.setString(4, newMenu.getOrderableStatus());

            result = pstmt.executeUpdate();

            System.out.println("result = " + result);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {


        }finally{
            close(con);
            close(pstmt);
        }
        if(result>0){
            System.out.println("메뉴등록 성공");
        }
        else{
            System.out.println("메뉴 등록 실패");
        }
    }
}

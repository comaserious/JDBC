package com.ohgiraffers.section01.model.dao;

import com.ohgiraffers.section01.model.dto.MenuDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;

import static com.ohgiraffers.common.JDBCTemplate.close;

/*필기.
*  DAO(DataBase Access Object)
*  데이터베이스 접근용 객체
*  => CRUD 연산을 담당하는 메소드들의 집합으로 이루어진 클래스*/
public class MenuDAO {

    // 1. query 문 을 읽어야 됨
    private Properties prop = new Properties();

    public MenuDAO(){  // 기본생성자 구동부안에 넣는 경우도 있는데 이런경우 인스턴스를 생성하자마자 바로 실행되게 하기 위해서이다
        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/menu-query.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int selectLastMenuCode(Connection con) {

        Statement stmt = null;
        ResultSet rset = null;
        String query = prop.getProperty("selectLastMenuCode");
        int maxNum = 0;

        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery(query);
            if(rset.next()){
                maxNum = rset.getInt("MAX(menu_code)");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
//            close(con);        여기서 con 을 닫아버리면 뒤에 사용을 할수가 없다 따라서 Connection 인스턴스는 닫지 않는다
            close(stmt);
            close(rset);
        }
        return maxNum;

    }


    public List<Map<Integer, String>> selectAllCategory(Connection con) {
        Statement stmt   =null;
        ResultSet rset = null;
        String query = prop.getProperty("selectAllCategory");
        List<Map<Integer,String>> categoryList = null;
        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery(query);
            categoryList = new ArrayList<>();
            while(rset.next()){
                Map<Integer,String > category = new HashMap<>();
                category.put(rset.getInt("category_code"),rset.getString("category_name"));
                categoryList.add(category);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(stmt);
            close(rset);
        }
        return categoryList;
    }

    public int insertNewMenu(Connection con, MenuDTO newMenu) {
        PreparedStatement pstmt = null;
        int result = 0 ;
        String query = prop.getProperty("InsertMenu");

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1,newMenu.getMenuCode());
            pstmt.setString(2,newMenu.getMenuName());
            pstmt.setInt(3,newMenu.getMenuPrice());
            pstmt.setInt(4,newMenu.getCategoryCode());
            pstmt.setString(5,newMenu.getOrderableStatus());

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            close(pstmt);
        }
        return result;
    }
}

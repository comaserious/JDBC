package com.ohgiraffers.section02.template;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCTemplate {

    /*필기.
    *  Connection 객체를 생성하는 것도 중복해서 작성을 하기 때문에
    *  static 메소드로 객체를 생성하거나 자원을 반납하는 template 을 만들어서
    *  지속적인 재사용을 하도록 한다*/

    /*필기. void 형이 아니니 return이 필요한데 Connection을 리턴하게 한다.*/
    public static Connection getConnection() {
        Connection con = null;
        Properties prop = new Properties();
        try {
            prop.load(new FileReader("src/main/java/com/ohgiraffers/config/connection-info.properties"));
            String driver = prop.getProperty("driver");
            String url = prop.getProperty("url");


            Class.forName(driver);

            con = DriverManager.getConnection(url,prop.getProperty("user"),prop.getProperty("pass"));



        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return con;
    }

    public static void close(Connection con)  {

        /*필기.
        *  위에 getConnetion() 으로 생성한 커넥션을
        *  매개변수로 받아 닫아주는 역할을 한다
        *  자원반납시에는 null 체크여부, 이미 반납된 자원이 아닌지 확인을 해야한다*/

        try {
            if(con != null && !con.isClosed() ){
                con.close();

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

}

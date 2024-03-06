package com.ohgiraffers.section01.connection;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Application03 {

    public static void main(String[] args) {

        /*수업목표. Properties 파일로 주요 정보를 은닉할 수 있다*/

        /*필기.
        *  Properties 파일을 사용하는 이유
        *  1. 수기로 작성시 오타 발생 가능성 높음(한곳에 모아둬서 유지보수성이 용이)
        *  2. 설정 속성에 수정 사항이 발생할 경우 파일마다 번거롭게 수정해야 하므로 유지보수비용 증가
        *  3. Connection 을 사용하는 파일마다 동일한 코드의 중복을 막는다*/

        Properties prop = new Properties();

        Connection con = null;

        try {
            prop.load(new FileReader("src/main/java/com/ohgiraffers/section01/connection/jdbc-config.properties"));

            System.out.println("prop = " + prop);

            /*필기. 값 꺼내오기 getProperty*/
            String driver = prop.getProperty("driver");
            String url = prop.getProperty("url");
            String pass = prop.getProperty("pass");
            String user = prop.getProperty("user");

            // Properties 를 이용하여 user 와 pass 가 해당 클래스내에 들어나지 않는다
            // 하지만 여전히 try-catch 문과 close() 문이 반복 되고 있다

            Class.forName(driver);
            con = DriverManager.getConnection(url,user,pass);

            System.out.println("con = " + con);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            if(con != null){
                try {
                    con.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }
}

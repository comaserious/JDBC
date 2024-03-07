package com.ohgiraffers.preparedstatements;

import com.ohgiraffers.model.dto.EmployeeDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application03 {

    public static void main(String[] args) {

        /*필기.
        *  Employee 테이블에서 조회할 사원의 성씨를 입력받아
        *  해당 성씨를 가진 사원의 정보들을 모두 출력*/

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        Scanner scr = new Scanner(System.in);
        System.out.print("조회할 이름의 성을 입력하세요 : ");
        String empName = scr.nextLine();
        List<EmployeeDTO> empList = null;
        EmployeeDTO row = null;
        String query = "select * from employee where EMP_NAME like concat(?,'%')";

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1,empName);
            rset = pstmt.executeQuery();
            empList = new ArrayList<>();
            while(rset.next()){
                row = new EmployeeDTO();
                row.setEmpId(rset.getString("EMP_ID"));
                row.setEmpName(rset.getString("EMP_NAME"));
                row.setEmpNo(rset.getString("EMP_NO"));
                row.setEmail(rset.getString("EMAIL"));
                row.setPhone(rset.getString("PHONE"));
                row.setDeptCode(rset.getString("DEPT_CODE"));
                row.setJobCode(rset.getString("JOB_CODE"));
                row.setSalLevel(rset.getString("SAL_LEVEL"));
                row.setSalary(rset.getInt("SALARY"));
                row.setBonus(rset.getDouble("BONUS"));
                row.setManagerId(rset.getString("MANAGER_ID"));
                row.setHireDate(rset.getDate("HIRE_DATE"));
                row.setEntDate(rset.getDate("ENT_DATE"));
                row.setEntYn(rset.getString("ENT_YN"));
                empList.add(row);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally{
            close(con);
            close(pstmt);
            close(rset);
        }
        for(EmployeeDTO emp: empList){
            System.out.println(emp);
        }


    }
}

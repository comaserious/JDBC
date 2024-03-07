package com.ohgiraffers.section01.statement;

import com.ohgiraffers.model.dto.EmployeeDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application05 {

    public static void main(String[] args) {

        /*필기. App4 는 1명의 정보를 담았다 . 근데 이제 여러 명의 정보를 담고 싶다*/

        Connection con = getConnection();

        Statement stmt = null;

        ResultSet rset = null;

        /*필기. 한 행의 정보를 담은 EmployeeDTO*/

        EmployeeDTO row = null;

        /*필기. 여러 DTO를 하나의 인스턴스로 묶기위한 List*/

        List<EmployeeDTO> empList = null;

        //모든 사원을 조회하는 SQL문
        String query = "select * from employee";

        try {
            stmt = con.createStatement();

            rset = stmt.executeQuery(query);

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

        }finally {
            close(con);
            close(stmt);
            close(rset);
        }

        for (EmployeeDTO emp : empList   ){

            System.out.println(emp);
        }

    }
}

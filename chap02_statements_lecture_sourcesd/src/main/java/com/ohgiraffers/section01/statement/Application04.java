package com.ohgiraffers.section01.statement;

import com.ohgiraffers.model.dto.EmployeeDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application04 {

    public static void main(String[] args) {

        Connection con = getConnection();

        Statement stmt = null;

        ResultSet rset = null;

        // employeeDTO 객체에 다가 받아 온 데이터베이스 정보들을 담기 위해 인스턴스 생성
        EmployeeDTO selectedEmp = null;
        Scanner scr = new Scanner(System.in);

        System.out.print("조회하실 사번은 ? : ");
        String empId = scr.nextLine();
        String query = "select * FROM EMPLOYEE WHERE EMP_ID = '"+empId+"'";

        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery(query);
            if(rset.next()){
                selectedEmp = new EmployeeDTO();
                selectedEmp.setEmpId(rset.getString("EMP_ID"));
                selectedEmp.setEmpName(rset.getString("EMP_NAME"));
                selectedEmp.setEmpNo(rset.getString("EMP_NO"));
                selectedEmp.setEmail(rset.getString("EMAIL"));
                selectedEmp.setPhone(rset.getString("PHONE"));
                selectedEmp.setDeptCode(rset.getString("DEPT_CODE"));
                selectedEmp.setJobCode(rset.getString("JOB_CODE"));
                selectedEmp.setSalLevel(rset.getString("SAL_LEVEL"));
                selectedEmp.setSalary(rset.getInt("SALARY"));
                selectedEmp.setBonus(rset.getDouble("BONUS"));
                selectedEmp.setManagerId(rset.getString("MANAGER_ID"));
                selectedEmp.setHireDate(rset.getDate("HIRE_DATE"));
                selectedEmp.setEntDate(rset.getDate("ENT_DATE"));
                selectedEmp.setEntYn(rset.getString("ENT_YN"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con);
            close(stmt);
            close(rset);
        }

        System.out.println(selectedEmp);


    }
}

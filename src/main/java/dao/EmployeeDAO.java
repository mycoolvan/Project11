package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Employee;
import util.JDBCConnection;

public class EmployeeDAO implements IEmployee {
	public static Connection conn = JDBCConnection.getConnection();


	public List<Employee> getAllEmployee() {
		List<Employee> employees = new ArrayList<Employee>();
		try {
			String sql = "Select * from employees join department on employees.departmentid=department.departmentid";
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			while(rs.next())
			{
				employees.add(new Employee(
				rs.getInt("empid"),
				rs.getString("username"),
				rs.getString("password"),
				rs.getString("fname"),
				rs.getString("lname"),
				rs.getString("emptype"),
				rs.getInt("superid"),
				rs.getString("dname"),
				rs.getInt("departmentid")));
						
			}
			
			rs.close();

		}catch (SQLException e)
		{
			e.printStackTrace();
		}
		return employees;

	}

}

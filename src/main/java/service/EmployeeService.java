package service;

import java.util.List;

import dao.EmployeeDAO;
import model.Employee;

public class EmployeeService {
	public static EmployeeDAO ed = new EmployeeDAO();
	public static List<Employee> employees= ed.getAllEmployee();
	public static List<Employee> getAllEmployee(){
		return ed.getAllEmployee();
	}
	
	public static Employee login(String username, String password) {
		for(Employee e:getAllEmployee()) {
			if(e.getUsername().equals(username) && e.getPassword().equals(password)) {
				return e;
			}
		}
		
		return null;
	}
}

package webservice;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.Employee;
import service.EmployeeService;

public class EmployeeWebService {
	static Employee e;

	public static List<Employee> getAllEmployees(){
		return EmployeeService.getAllEmployee();
	}
	public static boolean login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Employee e=EmployeeService.login(username, password);
		ObjectMapper om = new ObjectMapper();
		try {
		if(e!=null) {
			HttpSession sess=request.getSession();
			sess.setAttribute("empid",e.getEmpid());
			String json = om.writeValueAsString(e);
			Cookie empcook = new Cookie("empid", String.valueOf(e.getEmpid()));
			response.addCookie(empcook);
			Cookie usercook = new Cookie("username", e.getUsername());
			response.addCookie(usercook);
			Cookie supcook = new Cookie("superid", String.valueOf(e.getSuperid()));
			response.addCookie(supcook);
			Cookie headcook = new Cookie("dhid", String.valueOf(e.getDhid()));
			response.addCookie(headcook);
			Cookie typecook = new Cookie("emptype", e.getEmptype().replaceAll(" ", ""));
			response.addCookie(typecook);
			Cookie depcook = new Cookie("depname",e.getDepname());
			response.addCookie(depcook);
			response.getWriter().append(json).close();
			System.out.println("Sucess!");
			return true;
		}
		else {

			String json = om.writeValueAsString(e);
			response.getWriter().append(json).close();
			System.out.println("fail!");
			return false;
		}
		}
		catch (IOException e1) {
			e1.printStackTrace();
		}
		return false;
}
}

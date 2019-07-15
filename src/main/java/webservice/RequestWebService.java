package webservice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.Employee;
import model.Request;
import service.EmployeeService;
import service.MessageService;
import service.RequestService;

public class RequestWebService {

	public static boolean userRequests(HttpServletRequest request, HttpServletResponse response) {
		Cookie cookies[] = request.getCookies();
		int id = -1;
		int balance = 0;
		int pendbal = 0;
		for (int i = 0; i < cookies.length; i++) {

			if (cookies[i].getName().equals("empid")) {
				id = Integer.parseInt(cookies[i].getValue());
			}
		}
		if (id == -1) {
			return false;
		}
		ObjectMapper om = new ObjectMapper();
		try {
			List<Request> userreq = RequestService.userRequest(id);
			for (Request r : userreq) {
				if (r.getStatus() == 300)
					switch (r.getEventtype()) {
					case "University Courses":
						balance += r.getCost() * .8;
						break;
					case "Seminars":
						balance += r.getCost() * .6;
						break;
					case "Certification Preparation Classes":
						balance += r.getCost() * .75;
						break;
					case "Certification":
						balance += r.getCost();
						break;
					case "Technical Training":
						balance += r.getCost() * .9;
						break;
					case "Other":
						balance += r.getCost() * .3;
						break;
					}
				else
					switch (r.getEventtype()) {
					case "University Courses":
						pendbal += r.getCost() * .8;
						break;
					case "Seminars":
						pendbal += r.getCost() * .6;
						break;
					case "Certification Preparation Classes":
						pendbal += r.getCost() * .75;
						break;
					case "Certification":
						pendbal += r.getCost();
						break;
					case "Technical Training":
						pendbal += r.getCost() * .9;
						break;
					case "Other":
						pendbal += r.getCost() * .3;
						break;
					}
			}
			List<Object> balanceReq = new ArrayList<Object>();
			balanceReq.add(pendbal);
			balanceReq.add(balance);
			balanceReq.add(userreq);
			String json = om.writeValueAsString(balanceReq);
			// String balancejson = om.writeValueAsString(balance);
			response.getWriter().append(json);
			// }
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	public static boolean managedRequests(HttpServletRequest request, HttpServletResponse response) {
		Cookie cookies[] = request.getCookies();
		int id = -1;
		String user = "none";
		for (int i = 0; i < cookies.length; i++) {

			if (cookies[i].getName().equals("empid")) {
				id = Integer.parseInt(cookies[i].getValue());
			}
			if (cookies[i].getName().equals("username")) {
				user = cookies[i].getValue();
			}
		}
		if (id == -1 || user.equals("none")) {
			return false;
		}
		ObjectMapper om = new ObjectMapper();
		try {
			// for(Request r: RequestService.userRequest(id)) {
			String json = om.writeValueAsString(RequestService.managedRequest(user, id));
			response.getWriter().append(json).close();
			// }
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	public static void getRequest(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("reqid"));
		Request r = RequestService.getRequests(id);
		ObjectMapper om = new ObjectMapper();

		try {
			String json = om.writeValueAsString(r);
			response.getWriter().append(json).close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean addRequest(HttpServletRequest request, HttpServletResponse response) {
		Cookie cookies[] = request.getCookies();
		int empid = -1;
		int superid = -1;
		String username = "";
		for (int i = 0; i < cookies.length; i++) {

			if (cookies[i].getName().equals("empid")) {
				empid = Integer.parseInt(cookies[i].getValue());
			}
			if (cookies[i].getName().equals("superid")) {
				superid = Integer.parseInt(cookies[i].getValue());
			}
			if (cookies[i].getName().equals("username")) {
				username = cookies[i].getValue();
			}

		}
		if (empid == -1 || superid == -1 || username.equals(""))
			return false;
		String reqdate = request.getParameter("reqdate");
		String reqdesc = request.getParameter("reqdesc");
		double cost = Double.parseDouble(request.getParameter("cost"));
		String gradef = request.getParameter("gradef");
		String gradestatus = request.getParameter("gradestatus");
		String eventtype = request.getParameter("eventtype");
		String workjust = request.getParameter("workjust");
		int status = Integer.parseInt(request.getParameter("status"));
		String balance = request.getParameter("balance");
		String pendbal = request.getParameter("pendbal");
		if (empid == superid) {// ceo
			RequestService.addRequest(
					new Request(empid, reqdate, reqdesc, cost, gradef, gradestatus, eventtype, workjust, 200));
			MessageService.autoMsg(16, username, "This is an Automated Message for Request: " + "Current Balance: "
					+ balance + " Pending Balance: " + pendbal);
		} else {
			RequestService.addRequest(
					new Request(empid, reqdate, reqdesc, cost, gradef, gradestatus, eventtype, workjust, status));
			MessageService.autoMsg(superid, username, "This is an Automated Message for Request " + "Current Balance: "
					+ balance + " Pending Balance: " + pendbal);
		}
		try {
			response.getWriter().append("Request addded to database");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	public static boolean acceptRequest(HttpServletRequest request, HttpServletResponse response) {
		int reqid = Integer.parseInt(request.getParameter("reqid"));
		String remark = request.getParameter("remark");
		int empid = Integer.parseInt(request.getParameter("empid"));
		int status = 0;
		String reqemp = "";
		String requser = "";
		String emptype = "";
		int headid = 0;
		int benid = 0;
		String depname = "";
		String username = "";
		Cookie cookies[] = request.getCookies();
		for (int i = 0; i < cookies.length; i++) {

			if (cookies[i].getName().equals("emptype")) {
				emptype = cookies[i].getValue();
			}
			if (cookies[i].getName().equals("dhid")) {
				headid = Integer.parseInt(cookies[i].getValue());
			}
			if (cookies[i].getName().equals("username")) {
				username = cookies[i].getValue();
			}
			if (cookies[i].getName().equals("depname")) {
				depname = cookies[i].getValue();
			}

		}
		for (Employee e : EmployeeService.getAllEmployee()) {
			if (e.getDepname().equals(depname)) {
				if (e.getEmptype().equals("Benefits Coordinator")) {
					benid = e.getEmpid();
				}
			}
			if (e.getEmpid() == empid) {
				reqemp = e.getEmptype();
				requser = e.getUsername();
			}
		}
		if (reqemp.equals("Benefits Coordinator") && !requser.equals("genben")) {
			switch (emptype) {
			case "Employee":
				status = 100;
				MessageService.autoMsg(headid, username,
						"This is an Automated Message for Request. Supervisor Approved");
				break;
			case "CEO":
				status = 200;
				MessageService.autoMsg(16, username,
						"This is an Automated Message for Request. Supervisor CEO Approved");
				break;
			case "DepartmentHead":
				status = 200;
				MessageService.autoMsg(16, username,
						"This is an Automated Message for Request. DepartmentHead Approved");
				break;
			case "BenefitsCoordinator":
				status = 300;
				break;
			default:
				return false;
			}
		} else if (requser.equals("genben")) {
			switch (emptype) {
			case "Employee":
				status = 100;
				MessageService.autoMsg(headid, username,
						"This is an Automated Message for Request. Supervisor Approved");
				break;
			case "CEO":
				status = 200;
				MessageService.autoMsg(18, username,
						"This is an Automated Message for Request. Supervisor CEO Approved");
				break;
			case "DepartmentHead":
				status = 200;
				MessageService.autoMsg(18, username,
						"This is an Automated Message for Request. DepartmentHead Approved");
				break;
			case "BenefitsCoordinator":
				status = 300;
				break;
			default:
				return false;
			}
		} else {
			switch (emptype) {
			case "Employee":
				status = 100;
				MessageService.autoMsg(headid, username,
						"This is an Automated Message for Request. Supervisor Approved");
				break;
			case "CEO":
				status = 200;
				MessageService.autoMsg(benid, username,
						"This is an Automated Message for Request. Supervisor CEO Approved");
				break;
			case "DepartmentHead":
				status = 200;
				MessageService.autoMsg(benid, username,
						"This is an Automated Message for Request. DepartmentHead Approved");
				break;
			case "BenefitsCoordinator":
				status = 300;
				break;
			default:
				return false;
			}
		}
		return RequestService.acceptRequest(reqid, status, remark);
	}

}

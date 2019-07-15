package serf;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webservice.EmployeeWebService;
import webservice.MessageWebService;
import webservice.RequestWebService;

public class RequestHelper {
	static boolean login = false;
	public static void Process(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String uri = request.getRequestURI();
		
		switch (uri) {
		case "/Project1/getRequest.do": {
			RequestWebService.getRequest(request, response);
			break;
		}
		case "/Project1/userRequest.do": {
			RequestWebService.userRequests(request, response);
			break;
		}
		
		case "/Project1/addRequest.do": {
			RequestWebService.addRequest(request, response);
			break;
		}
		case "/Project1/login.do": {
			EmployeeWebService.login(request, response);
			break;
		}

		case "/Project1/sendMessage.do": {
			MessageWebService.sendMessage(request, response);

		break;
		}
		case "/Project1/managedRequest.do": {
			RequestWebService.managedRequests(request, response);

		break;
		}
		case "/Project1/updateRequest.do":{
			RequestWebService.acceptRequest(request, response);
		break;
		}
		default: {
			System.out.println("out of scope");
			RequestDispatcher rd = request.getRequestDispatcher("login.html");
			rd.forward(request, response);
		}

		}
	}

}

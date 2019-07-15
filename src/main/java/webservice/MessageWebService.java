package webservice;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Message;
import service.MessageService;

public class MessageWebService {
	public static boolean sendMessage(HttpServletRequest request, HttpServletResponse response) {
		Cookie cookies[] = request.getCookies();
		String touser="";
		for(int i =0; i<cookies.length ; i++) {
		System.out.println(cookies[i].getName() + " "+ cookies[i].getValue());
		
		if(cookies[i].getName().equals("username")) {
			touser = (cookies[i].getValue());
		}
		
		}
		if(touser.equals(""))
			return false;
		int reqid = Integer.parseInt(request.getParameter("reqid"));
		String fromuser = request.getParameter("fromuser");
		String msg = request.getParameter("msg");
		
		MessageService.sendMsg(new Message(reqid, fromuser, touser, msg));
		try {
			response.getWriter().append("Message sent");
		} catch(IOException e) {
			e.printStackTrace();
		}
		return true;
	}
}

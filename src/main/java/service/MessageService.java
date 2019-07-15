package service;

import java.util.ArrayList;
import java.util.List;

import dao.EmployeeDAO;
import dao.MessageDAO;
import dao.RequestDAO;
import model.Employee;
import model.Message;
import model.Request;

public class MessageService {
	public static MessageDAO md = new MessageDAO();
	
	
	public static List<Message> getRequestMessages(int reqid){
		
		List<Message> msgs= new ArrayList<Message>(); 
		for(Message m :md.getAllMessages())
		{
			if(m.getReqid()==reqid) {
				msgs.add(m);
			}
		}
		return msgs;
	}
	
	public static boolean sendMsg(Message m) {
		return md.sendMsg(m);
	}
	
	public static boolean autoMsg(int id, String from, String msg) {
		String user="";
		EmployeeDAO ed = new EmployeeDAO();
		for(Employee e: ed.getAllEmployee()) {
			if(e.getEmpid()==id) {
				user=e.getUsername();
				System.out.println(user);
			}
		}
		if(user=="") {
			return false;
		}
		RequestDAO rd= new RequestDAO();
		List<Request> req =rd.getAllRequest();
		int reqid=req.get(req.size()-1).getReqid();
		return md.sendMsg(new Message(reqid, user, from, msg));
	}
}

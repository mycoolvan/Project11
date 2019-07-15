package service;

import java.util.ArrayList;
import java.util.List;

import dao.RequestDAO;
import model.Message;
import model.Request;

public class RequestService {
	public static RequestDAO rd= new RequestDAO();
	public static List<Request> requests;
	
	public static List<Request> getAllRequests(){
		return rd.getAllRequest();
	}
	
	public static List<Request> userRequest(int id){
		List<Request> userreq = new ArrayList<Request>();
		//System.out.println("this is the req array"+requests);
		requests = getAllRequests();
		for (Request r : requests) {
			if(r.getEmpid()==id) {
				userreq.add(r);
			}
		}
		return userreq;
	}
	
	public static List<Request> managedRequest(String username, int id){
		List<Request> managereq = new ArrayList<Request>();
		//System.out.println("this is the req array"+req	uests);
		for (Request r : getAllRequests()) {
			for(Message m : r.getMessages()) {
				if(r.getEmpid()!=id&&(m.getfromuser()==null||m.getfromuser().equals(username)||m.gettouser().equals(username)))
				{
					managereq.add(r);
					break;
				}
			}
		}
		return managereq;
	}
	
	public static Request getRequests(int id) {
		for (Request r : requests) {
			if(r.getReqid()==id) {
				return r;
			}
		
		}
		return null;
	}
	
	public static boolean addRequest (Request r) {
		return rd.addRequest(r);
	}
	
	public static boolean acceptRequest(int requestid, int status, String remark) {
		return rd.acceptRequest(requestid, status, remark);
	}
}

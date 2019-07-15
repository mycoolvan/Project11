package dao;

import java.util.List;

import model.Request;

public interface IRequest {
	
	List<Request> getAllRequest();
	boolean addRequest(Request r);
	boolean acceptRequest(int requestid, int status, String remark);
	boolean denyRequest(int requestid);

}

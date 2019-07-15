package model;

import java.util.Arrays;
import java.util.List;

public class Request {
	int reqid;
	int empid;
	String reqdate;
	String expdate;
	String reqdesc;
	double cost;
	String gradef;
	String gradestatus;
	String eventtype;
	String workjust;
	int status;
	String remark;
	String attachments[];
	List<Message> messages;
	
	
	
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}



	@Override
	public String toString() {
		return "Request [reqid=" + reqid + ", empid=" + empid + ", reqdate=" + reqdate + ", expdate=" + expdate
				+ ", reqdesc=" + reqdesc + ", cost=" + cost + ", gradef=" + gradef + ", gradestatus=" + gradestatus
				+ ", eventtype=" + eventtype + ", workjust=" + workjust + ", status=" + status + ", remark=" + remark
				+ ", attachments=" + Arrays.toString(attachments) + ", messages=" + messages + "]";
	}

	public Request(int reqid, int empid, String reqdate, String expdate, String reqdesc, double cost, String gradef,
			String gradestatus, String eventtype, String workjust, int status, String remark, String[] attachments) {
		super();
		this.reqid = reqid;
		this.empid = empid;
		this.reqdate = reqdate;
		this.expdate = expdate;
		this.reqdesc = reqdesc;
		this.cost = cost;
		this.gradef = gradef;
		this.gradestatus = gradestatus;
		this.eventtype = eventtype;
		this.workjust = workjust;
		this.status = status;
		this.remark = remark;
		this.attachments = attachments;
	}
	
	//add req constructor
	public Request(int empid, String reqdate, String reqdesc, double cost, String gradef, String gradestatus,
			String eventtype, String workjust, int status) {
		super();
		this.empid = empid;
		this.reqdate = reqdate;
		this.reqdesc = reqdesc;
		this.cost = cost;
		this.gradef = gradef;
		this.gradestatus = gradestatus;
		this.eventtype = eventtype;
		this.workjust = workjust;
		this.status = status;
	}
	
	public int getReqid() {
		return reqid;
	}


	public void setReqid(int reqid) {
		this.reqid = reqid;
	}
	public int getEmpid() {
		return empid;
	}
	public void setEmpid(int empid) {
		this.empid = empid;
	}
	public String getReqdate() {
		return reqdate;
	}
	public void setReqdate(String reqdate) {
		this.reqdate = reqdate;
	}
	
	public String getExpdate() {
		return expdate;
	}
	public void setExpdate(String expdate) {
		this.expdate = expdate;
	}
	public String getReqdesc() {
		return reqdesc;
	}
	public void setReqdesc(String reqdesc) {
		this.reqdesc = reqdesc;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public String getGradef() {
		return gradef;
	}
	public void setGradef(String gradef) {
		this.gradef = gradef;
	}
	public String getGradestatus() {
		return gradestatus;
	}
	public void setGradestatus(String gradestatus) {
		this.gradestatus = gradestatus;
	}
	public String getEventtype() {
		return eventtype;
	}
	public void setEventtype(String eventtype) {
		this.eventtype = eventtype;
	}
	public String getWorkjust() {
		return workjust;
	}
	public void setWorkjust(String workjust) {
		this.workjust = workjust;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String[] getAttachments() {
		return attachments;
	}
	public void setAttachments(String[] attachments) {
		this.attachments = attachments;
	}
	
	
	
}
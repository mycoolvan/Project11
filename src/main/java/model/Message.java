package model;

public class Message {
	int msgid;
	int reqid;
	String touser;
	String fromuser;
	boolean unread;
	String msg;
	String msgdate;
	
	
	
	@Override
	public String toString() {
		return "Message [msgid=" + msgid + ", reqid=" + reqid + ", touser=" + touser + ", fromuser=" + fromuser
				+ ", unread=" + unread + ", msg=" + msg + ", msgdate=" + msgdate + "]";
	}
	public Message(int msgid, int reqid, String touser, String fromuser, boolean unread, String msg, String msgdate) {
		super();
		this.msgid = msgid;
		this.reqid = reqid;
		this.touser = touser;
		this.fromuser = fromuser;
		this.unread = unread;
		this.msg = msg;
		this.msgdate = msgdate;
	}
	
	
	public Message(int reqid, String touser, String fromuser, String msg) {
		super();
		this.reqid = reqid;
		this.touser = touser;
		this.fromuser = fromuser;
		this.msg = msg;
	}
	public int getMsgid() {
		return msgid;
	}
	public void setMsgid(int msgid) {
		this.msgid = msgid;
	}
	public int getReqid() {
		return reqid;
	}
	public void setReqid(int reqid) {
		this.reqid = reqid;
	}
	public String gettouser() {
		return touser;
	}
	public void settouser(String touser) {
		this.touser = touser;
	}
	public String getfromuser() {
		return fromuser;
	}
	public void setfromuser(String fromuser) {
		this.fromuser = fromuser;
	}
	public boolean isUnread() {
		return unread;
	}
	public void setUnread(boolean unread) {
		this.unread = unread;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getMsgdate() {
		return msgdate;
	}
	public void setMsgdate(String msgdate) {
		this.msgdate = msgdate;
	}
	
}

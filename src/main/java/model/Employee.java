package model;

public class Employee {
	
	int empid;
	String username;
	String fname;
	String lname;
	String password;
	String emptype;
	int superid;
	String depname;
	int dhid;
	public Employee(int empid, String username, String password, String fname, String lname, String emptype, int superid, String depname,
			int dhid) {
		super();
		this.empid = empid;
		this.username = username;
		this.password = password;
		this.fname = fname;
		this.lname = lname;
		this.emptype = emptype;
		this.superid = superid;
		this.depname = depname;
		this.dhid = dhid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDepname() {
		return depname;
	}
	public void setDepname(String depname) {
		this.depname = depname;
	}
	public int getDhid() {
		return dhid;
	}
	public void setDhid(int dhid) {
		this.dhid = dhid;
	}
	public int getEmpid() {
		return empid;
	}
	public void setEmpid(int empid) {
		this.empid = empid;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getEmptype() {
		return emptype;
	}
	public void setEmptype(String emptype) {
		this.emptype = emptype;
	}
	public int getSuperid() {
		return superid;
	}
	public void setSuperid(int superid) {
		this.superid = superid;
	}
	@Override
	public String toString() {
		return "Employee [empid=" + empid + ", username=" + username + ", fname=" + fname + ", lname=" + lname
				+ ", password=" + password + ", emptype=" + emptype + ", superid=" + superid + ", depname=" + depname
				+ ", dhid=" + dhid + "]";
	}
	
}

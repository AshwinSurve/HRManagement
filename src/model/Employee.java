package model;

public class Employee {
	int empid;
	String empname;
	String address;
	long mobileno;
	int depid;
	int roleid;
	String email;
	
	public Employee() {
		// TODO Auto-generated constructor stub
		
	}
	
	public Employee(int empid, String empname, String address, long mobileno, int depid, int roleid, String email) {
		super();
		this.empid = empid;
		this.empname = empname;
		this.address = address;
		this.mobileno = mobileno;
		this.depid = depid;
		this.roleid = roleid;
		this.email = email;
	}
	
	public int getEmpid() {
		return empid;
	}
	public void setEmpid(int empid) {
		this.empid = empid;
	}
	public String getEmpname() {
		return empname;
	}
	public void setEmpname(String empname) {
		this.empname = empname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public long getMobileno() {
		return mobileno;
	}
	public void setMobileno(long mobileno) {
		this.mobileno = mobileno;
	}
	public int getDepid() {
		return depid;
	}
	public void setDepid(int depid) {
		this.depid = depid;
	}
	public int getRoleid() {
		return roleid;
	}
	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}

package model;

public class Department {
	int depid;
	String depname;
	public Department(int depid,String depname) {
		// TODO Auto-generated constructor stub
		this.depid = depid;
		this.depname = depname;
	}
	public int getDepid() {
		return depid;
	}
	public void setDepid(int depid) {
		this.depid = depid;
	}
	public String getDepname() {
		return depname;
	}
	public void setDepname(String depname) {
		this.depname = depname;
	}
	
}

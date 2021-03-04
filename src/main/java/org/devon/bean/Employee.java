package org.devon.bean;

public class Employee {

	int id;
	String employeeName;
	long eid;

	public Employee() {
		super();
	}

	public Employee(int i, String employeeName, long eid) {
		super();
		this.id = i;
		this.employeeName = employeeName;
		this.eid = eid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public long getEid() {
		return eid;
	}

	public void setEid(long eid) {
		this.eid = eid;
	}

}
package org.devon.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.devon.bean.Employee;
import org.devon.exception.EmployeeNotFoundException;


public class EmployeeService {

	static HashMap<Integer,Employee> employeeIdMap=getemployeeIdMap();


	public EmployeeService() {
		super();

		if(employeeIdMap==null)
		{
			employeeIdMap=new HashMap<Integer,Employee>();
			// Creating some object of employees while initializing
			Employee e1=new Employee(1, "John",10000);
			Employee e2=new Employee(4, "Vicky",20000);
			Employee e3=new Employee(3, "Michel",8000);
			Employee e4=new Employee(2, "Nick",7000);


			employeeIdMap.put(1,e1);
			employeeIdMap.put(4,e2);
			employeeIdMap.put(3,e3);
			employeeIdMap.put(2,e4);
		}
	}

	public List<Employee> getAllEmployees()
	{
		List<Employee> employees = new ArrayList<Employee>(employeeIdMap.values());
		return employees;
	}

	public Employee getEmployee(int id)
	{
		Employee employee= employeeIdMap.get(id);

		if(employee == null)
		{
			throw new EmployeeNotFoundException("Employee with eid "+id+" not found");
		}
		return employee;
	}
	public Employee addEmployee(Employee employee)
	{
		employee.setId(getMaxId()+1);
		employeeIdMap.put(employee.getId(), employee);
		return employee;
	}

	public Employee updateEmployee(Employee employee)
	{
		if(employee.getId()<=0)
			return null;
		employeeIdMap.put(employee.getId(), employee);
		return employee;

	}
	public void deleteEmployee(int id)
	{
		employeeIdMap.remove(id);
	}

	public static HashMap<Integer, Employee> getemployeeIdMap() {
		return employeeIdMap;
	}

	// Utility method to get max id
	public static int getMaxId()
	{ 	 int max=0;
	for (int id:employeeIdMap.keySet()) {  
		if(max<=id)
			max=id;

	}  

	return max;


	}
}

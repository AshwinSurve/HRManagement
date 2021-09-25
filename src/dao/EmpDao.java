package dao;

import java.util.List;
import java.util.Map;

import model.Employee;

public interface EmpDao {
	
	 boolean NameValidation(String name);
	
	boolean addemployee(Employee ep);
	
	boolean deleteEmployee(int id);

	boolean updateEmployee(Employee upemp);
	
	public List<Employee> getAllEmployee();
	
	public List<Employee> searchEmployee(int searchId);
	
	public Map<String, Integer> getDepartmentwiseCount();
	
	public List<Employee> getEmpByDepartmentName(String department);
	
}

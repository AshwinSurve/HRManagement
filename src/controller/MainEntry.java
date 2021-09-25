package controller;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicStampedReference;

import dao.EmpDao;
import dao.EmpDaoImplements;
import dao.UserAunthenticate;
import model.Employee;

public class MainEntry {
	public static void main(String[] args) {
		EmpDao emp = new EmpDaoImplements();
		Scanner sc= new Scanner(System.in);
		boolean runSwitch = true;
		
		UserAunthenticate ua = new UserAunthenticate();
		boolean isAuthenticated = ua.authentication();
		
		if(isAuthenticated) {
			do {
			System.out.println("Press \n1 : add employee\n2 : update data\n3 : delete data\n4 : show employees\n5 : search emp\n6 : department wise data\n7 : Employee dept name\n8 : logout\n");
			
			int choice = sc.nextInt();
			switch(choice)
			{
			//To add new employee
			case 1: System.out.println("Enter Employee ID:");		
					int empId = sc.nextInt();
					
					//Name Validation---------------------------------------
					String empName = null;
					boolean nameflag=true;
					while(nameflag)
					{
						System.out.println("Enter Employee Name:");
						empName = sc.next();
					
						boolean isValid = emp.NameValidation(empName);
						if(isValid)
						{
							nameflag = false;
						}
						else
						{
							System.out.println("Number must contain Characters only");
							nameflag = true;
						}
					}
				
					System.out.println("Enter Address:");
					String empAdd = sc.next();
				
					//Email Validation
					//--------------------------------------------------------------
					String empMailId=null;                   
					boolean flag=true;
					while(flag)														
					{
						System.out.println("Enter Email ID:");
						empMailId=sc.next();
						if(empMailId.contains(".com"))
							{
								flag = false;
							}
							else
							{
								System.out.println("Please Enter valid Mail Id");
								flag=true;
							}
					}
				
				//Mobile number Validation
				//-----------------------------------------------------------
				
					System.out.println("Enter Mobile Number:");
					long empMob = sc.nextLong();
					int length = String.valueOf(empMob).length();			
							while(length!=10)
							{
								System.out.println("Mobile number must contain 10 digit");	
								System.out.println("Enter Mobile Number:");
								empMob = sc.nextLong();
								length = String.valueOf(empMob).length();
							}
					System.out.println("Enter Department ID");
					int empDeptId = sc.nextInt();
					System.out.println("Enter Role ID");
					int empRoleId = sc.nextInt();
					System.out.println();
					
					Employee employee = new Employee(empId, empName, empAdd, empMob, empDeptId, empRoleId, empMailId);
					boolean isinsert = emp.addemployee(employee);
					if(isinsert) 
						System.out.println("record added Succesfully");
					else
						System.out.println("Unsuccesfull insertoin!!");
					break;
					
			//To Update Existing Employee
			case 2: System.out.println("Enter Employye ID To Update:");			
					int updateEmpId = sc.nextInt();
					
					//---Name validation start
					String newEmpName = null;
					boolean nameflag2=true;
					while(nameflag2)
					{
						System.out.println("Enter Employye Name:");
						newEmpName = sc.next();
						boolean isValid = emp.NameValidation(newEmpName);
						if(isValid)
							{
								nameflag2 = false;
							}
						else
							{
								System.out.println("Number must contain Characters only");
								nameflag2 = true;
							}
					 }

					System.out.println("Enter New Address:");
					String newEmpAdd = sc.next();
					String newEmpMailId=null;                   
				
					//Email Validation
					boolean flag1 =true;
					while(flag1)														
					{
						System.out.println();
						System.out.println("LOG IN");
						System.out.println();
						System.out.println("Enter Email ID:");
						newEmpMailId=sc.next();
						if(newEmpMailId.contains(".com"))
						{
							flag1 = false;
						}
						else
						{
							System.out.println("Please Enter valid Mail Id");
							flag1=true;
						}
					}
				
					System.out.println("Enter New Mobile Number:");
					long newEmpMob = sc.nextLong();
					int length1 = String.valueOf(newEmpMob).length();		
					
					//mobile validation start
					while(length1!=10)												
					{
						System.out.println("Mobile number must contain 10 digit");	
						System.out.println("Enter Mobile Number:");
						newEmpMob = sc.nextLong();
						length1 = String.valueOf(newEmpMob).length();
					}

					System.out.println("Enter New Department ID");
					int newEmpDeptId = sc.nextInt();
					System.out.println("Enter New Role ID");
					int newEmpRoleId = sc.nextInt();
					
					Employee updateEmployee = new Employee(updateEmpId,newEmpName, newEmpAdd, newEmpMob, newEmpDeptId, newEmpRoleId, newEmpMailId);
					boolean isupdate = emp.updateEmployee(updateEmployee);
					if(isupdate) 
						System.out.println("record Updated Succesfully");
					else
						System.out.println("Unsuccesfull updation!!");
					break;
					
					
			//To delete record from employee table
			case 3:System.out.println("Enter Employee Id to Delete Employee Detail:");		
					int deleteEmpId =sc.nextInt();
					boolean isdelete = emp.deleteEmployee(deleteEmpId);
					if(isdelete)
						System.out.println("Record deleted Successfully..");
					else
						System.out.println("unsuccesful deletion.");
					break;
					
					//Display All Employee record
			case 4:System.out.println("Employee Detail");
					
					List<Employee> empList = emp.getAllEmployee();
					for(Employee emp1:empList)
					{
						
						System.out.println("	EmployeeID : "+emp1.getEmpid()+"\n	Employee Name : "+emp1.getEmpname()+"\n	Address        :"+emp1.getAddress()+"\n	Mobile        :"+emp1.getMobileno()+"\n	DepatrmentID   : "+emp1.getDepid()+" \n	RoleID        :"+emp1.getRoleid()+" \n	Email ID      : "+emp1.getEmail()+""	);
						
					}
					break;
					
					
			//To Search Employee detail by Department ID
			case 5: System.out.println("Enter  Id:");
					int dept_id = sc.nextInt();
					
					List<Employee> searchEmpList = emp.searchEmployee(dept_id);
					for(Employee searchEmp:searchEmpList)
					{
						System.out.println("	EmployeeID : "+searchEmp.getEmpid()+" \n	Employee Name : "+searchEmp.getEmpname()+"\n	Address        "+searchEmp.getAddress()+"\n	Mobile        :"+searchEmp.getMobileno()+" \n	Email ID      : "+searchEmp.getEmail()+"\n	DepatrmentID   : "+searchEmp.getDepid()+" \n	RoleID        : "+searchEmp.getRoleid()+" ");
						
					}
					
					break;
			
			case 6: Map<String,Integer> reportMap =emp.getDepartmentwiseCount();
						System.out.println("******Deprtment wise Employee Report******");
						System.out.println();
					for(String reportSet:reportMap.keySet())
					{
						System.out.println("     DEPARTMENT NAME : "+reportSet+"\n     TOTAL EMPLOYEE : "+reportMap.get(reportSet));
					}
					break;
						
			case 7:System.out.println("Enter Department Name");
					String dept = sc.next();
					List<Employee> empBydeptList = emp.getEmpByDepartmentName(dept);
					for(Employee empDept:empBydeptList)
					{
						System.out.println("	EmployeeID : "+empDept.getEmpid()+" \n	Employee Name : "+empDept.getEmpname()+"\n	Address        "+empDept.getAddress()+"\n	Mobile        :"+empDept.getMobileno()+" \n	Email ID      : "+empDept.getEmail()+"\n	DepatrmentID   : "+empDept.getDepid()+" \n	RoleID        : "+empDept.getRoleid()+" ");
					}
					break;
					
			case 8: System.out.println("Loged out....");
					runSwitch = false;
					break;
			}
			}while(runSwitch);
	}
	
	//Execute if Login Failed
	else
		{
			System.out.println("Authentication Failed");
		}
			
	}
}

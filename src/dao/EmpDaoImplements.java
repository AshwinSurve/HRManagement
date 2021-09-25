package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import configuration.DBConnect;
import model.Employee;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmpDaoImplements implements EmpDao {

	public boolean NameValidation(String name) {

		if (name == null || name.equals(""))
			return false;

		if (!name.matches("[a-zA-Z]*"))
			return false;

		return true;
	}

	public boolean addemployee(Employee ep) {
		// TODO Auto-generated method stub
		try (Connection con = DBConnect.getConnection();
				PreparedStatement ps = con.prepareStatement("insert into Employee values(?,?,?,?,?,?,?)")) {
			ps.setInt(1, ep.getEmpid());
			ps.setString(2, ep.getEmpname());
			ps.setString(3, ep.getAddress());
			ps.setLong(4, ep.getMobileno());
			ps.setInt(5, ep.getDepid());
			ps.setInt(6, ep.getRoleid());
			ps.setString(7, ep.getEmail());
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteEmployee(int id) { // Delete database record containing given ID
		try (Connection con = DBConnect.getConnection();
				PreparedStatement ps = con.prepareStatement("delete from Employee where empid=?")) {
			ps.setInt(1, id);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean updateEmployee(Employee upemp) {
		try (Connection con = DBConnect.getConnection();
				PreparedStatement ps = con.prepareStatement(
						"update Employee set empname=?,address=?,mobileno=?,depid=?,roleid=?,emailid=? where empid=?")) {

			ps.setString(1, upemp.getEmpname());
			ps.setString(2, upemp.getAddress());
			ps.setLong(3, upemp.getMobileno());
			ps.setInt(4, upemp.getDepid());
			ps.setInt(5, upemp.getRoleid());
			ps.setString(6, upemp.getEmail());
			ps.setInt(7, upemp.getEmpid());
			ps.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<Employee> getAllEmployee() {
		ArrayList<Employee> employeeList = new ArrayList<>();
		try (Connection con = DBConnect.getConnection();
				PreparedStatement ps = con.prepareStatement("select * from Employee")) {

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Employee emp = new Employee();
				emp.setEmpid(rs.getInt(1));
				emp.setEmpname(rs.getString(2));
				emp.setAddress(rs.getString(3));
				emp.setMobileno(rs.getLong(4));
				emp.setDepid(rs.getInt(5));
				emp.setDepid(rs.getInt(6));
				emp.setEmail(rs.getString(7));
				employeeList.add(emp);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employeeList;
	}

	public List<Employee> searchEmployee(int searchId) { // search
		ArrayList<Employee> searchList = new ArrayList<>();
		try (Connection con = DBConnect.getConnection();
				PreparedStatement ps = con.prepareStatement("select * from Employee where empid=?")) {
			ps.setInt(1, searchId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Employee employee = new Employee();
				employee.setEmpid(rs.getInt(1));
				employee.setEmpname(rs.getString(2));
				employee.setAddress(rs.getString(3));
				employee.setMobileno(rs.getLong(4));
				employee.setDepid(rs.getInt(5));
				employee.setRoleid(rs.getInt(6));
				employee.setEmail(rs.getString(7));
				searchList.add(employee);

			}
			return searchList;
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		return searchList;
	}

	public Map<String, Integer> getDepartmentwiseCount() {
		HashMap<String, Integer> reportMap = new HashMap<>();
		try (Connection con = DBConnect.getConnection(); Statement st = con.createStatement()) {
			ResultSet rs = st.executeQuery(
					"select d.depname ,count(*) from Department d inner join employee e on e.depid = d.depid group by depname");

			while (rs.next()) {
				reportMap.put(rs.getString(1), rs.getInt(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reportMap;
	}

	public List<Employee> getEmpByDepartmentName(String department) {
		ArrayList<Employee> empBydept = new ArrayList<>();
		try (Connection con = DBConnect.getConnection();
				PreparedStatement ps = con.prepareStatement(
						"select * from Employee where depid=(select depid from Department where depname=?)")) {
			ps.setString(1, department);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Employee emp = new Employee();
				emp.setEmpid(rs.getInt(1));
				emp.setEmpname(rs.getString(2));
				emp.setAddress(rs.getString(3));
				emp.setMobileno(rs.getLong(4));
				emp.setDepid(rs.getInt(5));
				emp.setDepid(rs.getInt(6));
				emp.setEmail(rs.getString(7));
				empBydept.add(emp);
			}
			return empBydept;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return empBydept;
	}

}

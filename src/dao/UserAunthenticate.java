package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import configuration.DBConnect;
import model.User;

public class UserAunthenticate {
	public boolean authentication()
	{
		User user = new User();
		Scanner sc = new Scanner(System.in);
		try (Connection con = DBConnect.getConnection();
				PreparedStatement pst = con.prepareStatement("select * from user"))
		{
		 ResultSet rs= pst.executeQuery(); 
		 while(rs.next())
		 {
			 user.setUsername(rs.getString(2));
			 user.setPassword(rs.getString(3));

		 }
		 	System.out.println();
		 	System.out.println("   +-------Login-------+");
		 	System.out.println();
			 System.out.println("   Enter User Name");
			 String s1 = sc.next();
			 System.out.println("   Enter Password");
			 String s2 = sc.next();
			 if(s1.equals(user.getUsername()) && s2.equals(user.getPassword()))
			 {
				 return true;
			 }
			 else
			 {
				 return false;
			 }
			 
		}
		catch (SQLException e) {
		// TODO: handle exception
	}
		return false;
	}
}

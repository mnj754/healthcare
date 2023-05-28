package com.assignment.healthcare;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class User {
	

	@PostMapping("/signup")
	public String signup(String userName,String userEmail,String userPassword,
			String userMobile,String userAge)
	{
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcare","root","#jaibalaji754#");
		PreparedStatement stmt=con.prepareStatement("insert into user(userName,userEmail,userPassword,userMobile,userAge)"
				+ "values(?,?,?,?,?)");
		stmt.setString(1,userName);
		stmt.setString(2,userEmail);
		stmt.setString(3,userPassword);
		stmt.setString(4,userMobile);
		stmt.setString(5,userAge);
		
		int i=stmt.executeUpdate();
		if(i>0)
		{
			return "successfully registered";
		}
		else {
			return "please try again";
		}
			
		}
	

        catch (Exception e) {
			// TODO: handle exception
        	e.printStackTrace();
		}
	
	return "";
}
	
	
	
	@PostMapping("/api/login")
	public String login(String email,String password)
	{
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcare","root","#jaibalaji754#");
		Statement stmt=con.createStatement();
		String query="select userPassword from user where userEmail='"+email+"'";
		ResultSet rs=stmt.executeQuery(query);
		if(rs.next())
		{
			if(rs.getString("userPassword").equals(password)) {
				return "valid user";
			}else {
				return "invalid user";
			}
		}else {
			return "pls signup first";
		}
	}
			
	catch(Exception e)
	{
	e.printStackTrace();	
	}
	return "";
	}
	

}

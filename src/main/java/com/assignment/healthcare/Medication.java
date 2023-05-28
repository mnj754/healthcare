package com.assignment.healthcare;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Medication {
	
	@PostMapping("/addMedication")
	public String addMedication(
			 int userId,
		        String medicationName,
		        String dosage,
		        String frequency,
		        String startDate,
		        String endDate )
	{
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcare","root","#jaibalaji754#");
		PreparedStatement stmt=con.prepareStatement("insert into medication(userId,medicationName, dosage, startDate, endDate)"
				+ "values(?,?,?,?,?)");
		
		
		stmt.setInt(1,userId);
		stmt.setString(2,medicationName);
		stmt.setString(3,dosage);
		stmt.setString(4,startDate);
		stmt.setString(5,endDate);
		
		
		int i=stmt.executeUpdate();
		if(i>0)
		{
			return "successfully saved";
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
	
	//api to get the medication of a particular user
	
			@GetMapping("/getMedication/{userid}")
			public Map getMedication(@PathVariable("userid")String userId) {
				System.out.println("checking");
				try
				{
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcare",
							"root","#jaibalaji754#");
				
				PreparedStatement stmt=con.prepareStatement("select * from medication where userId=?");
				stmt.setInt(1, Integer.parseInt(userId));
				ResultSet rs=stmt.executeQuery();
				ArrayList list=new ArrayList();
				while(rs.next())
				{
					Map map=new HashMap();
					map.put("id",rs.getInt("id"));
					map.put("medicationName",rs.getString("medicationName"));
					map.put("dosage",rs.getInt("dosage"));
					map.put("startDate",rs.getInt("startDate"));
					map.put("endDate",rs.getInt("endDate"));
			
					
			
					
					list.add(map);
				
					
				}
				Map newmap=new HashMap();
				newmap.put("Medication",list);
				
				return newmap;
				
				
				}
				catch(Exception e)
				{
					e.printStackTrace();
					
				}
				return null;
			}

}

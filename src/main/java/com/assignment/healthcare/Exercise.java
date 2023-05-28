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
public class Exercise {
	
	@PostMapping("/addExercise")
	public String addExercise(
			 int userId,
		        String exerciseName,
		        int duration,
		        int caloriesBurned
		        )

	{
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcare","root","#jaibalaji754#");
		PreparedStatement stmt=con.prepareStatement("insert into exercise(userId,exerciseName, duration, caloriesBurned)"
				+ "values(?,?,?,?)");
		stmt.setInt(1,userId);
		stmt.setString(2,exerciseName);
		stmt.setInt(3,duration);
		stmt.setInt(4,caloriesBurned);
		
		
		
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
	
	//api to get the exercise information of a particular user
	
			@GetMapping("/getExercise/{userid}")
			public Map getExercise(@PathVariable("userid")String userId) {
				System.out.println("checking");
				try
				{
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcare",
							"root","#jaibalaji754#");
				
				PreparedStatement stmt=con.prepareStatement("select * from exercise where userId=?");
				stmt.setInt(1, Integer.parseInt(userId));
				ResultSet rs=stmt.executeQuery();
				ArrayList list=new ArrayList();
				while(rs.next())
				{
					Map map=new HashMap();
					map.put("id",rs.getInt("id"));
					map.put("exerciseName",rs.getString("exerciseName"));
					map.put("duration",rs.getInt("duration"));
					map.put("caloriesBurned",rs.getInt("caloriesBurned"));
					
			
					
			
					
					list.add(map);
				
					
				}
				Map newmap=new HashMap();
				newmap.put("Exercise",list);
				
				return newmap;
				
				
				}
				catch(Exception e)
				{
					e.printStackTrace();
					
				}
				return null;
			}
	

}

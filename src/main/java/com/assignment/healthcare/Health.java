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
public class Health {
	
	@PostMapping("/healthParameters")
	public String healthParameter(
	 int userId,
    int weight,
    int bloodPressure,
    int heartRate,
    int bloodSugar,
    int exerciseDuration)

	{
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcare","root","#jaibalaji754#");
		PreparedStatement stmt=con.prepareStatement("insert into hparameters(userId,weight, bloodPressure, heartRate, bloodSugar,exerciseDuration)"
				+ "values(?,?,?,?,?,?)");
		stmt.setInt(1,userId);
		stmt.setInt(2,weight);
		stmt.setInt(3,bloodPressure);
		stmt.setInt(4,heartRate);
		stmt.setInt(5,bloodSugar);
		stmt.setInt(6,exerciseDuration);
		
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
	
	//api to get the health parameters of a particular user
	
			@GetMapping("/hParameters/{userid}")
			public Map hParameters(@PathVariable("userid")String userId) {
				System.out.println("checking");
				try
				{
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcare",
							"root","#jaibalaji754#");
				
				PreparedStatement stmt=con.prepareStatement("select * from hparameters where userId=?");
				stmt.setInt(1, Integer.parseInt(userId));
				ResultSet rs=stmt.executeQuery();
				ArrayList list=new ArrayList();
				while(rs.next())
				{
					Map map=new HashMap();
					map.put("id",rs.getInt("id"));
					map.put("weight",rs.getInt("weight"));
					map.put("bloodPressure",rs.getInt("bloodPressure"));
					map.put("heartRate",rs.getInt("heartRate"));
					map.put("bloodSugar",rs.getInt("bloodSugar"));
					map.put("exerciseDuration",rs.getInt("exerciseDuration"));
					
					map.put("userId",rs.getInt("userId"));
					
					list.add(map);
				
					
				}
				Map newmap=new HashMap();
				newmap.put("healthparameters",list);
				
				return newmap;
				
				
				}
				catch(Exception e)
				{
					e.printStackTrace();
					
				}
				return null;
			}
	
	
	

}

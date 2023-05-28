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
public class Nutrition {
	
	@PostMapping("/addNutrition")
	public String addNutrition(
			 int userId,
		        String mealName,
		        int calories,
		        int carbohydrates,
		        int proteins ,
		        int fats)

	{
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcare","root","#jaibalaji754#");
		PreparedStatement stmt=con.prepareStatement("insert into nutrition(userId,mealName, carbohydrates, proteins, fats)"
				+ "values(?,?,?,?,?)");
		stmt.setInt(1,userId);
		stmt.setString(2,mealName);
		stmt.setInt(3,carbohydrates);
		stmt.setInt(4,proteins);
		stmt.setInt(5,fats);
		
		
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
	
	//api to get the nutritions of a particular user
	
			@GetMapping("/get/{userid}")
			public Map getNutrition(@PathVariable("userid")String userId) {
				System.out.println("checking");
				try
				{
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcare",
							"root","#jaibalaji754#");
				
				PreparedStatement stmt=con.prepareStatement("select * from nutrition where userId=?");
				stmt.setInt(1, Integer.parseInt(userId));
				ResultSet rs=stmt.executeQuery();
				ArrayList list=new ArrayList();
				while(rs.next())
				{
					Map map=new HashMap();
					map.put("id",rs.getInt("id"));
					map.put("mealName",rs.getString("mealName"));
					map.put("bloodPressure",rs.getInt("carbohydrates"));
					map.put("heartRate",rs.getInt("proteins"));
					map.put("bloodSugar",rs.getInt("fats"));
			
					
			
					
					list.add(map);
				
					
				}
				Map newmap=new HashMap();
				newmap.put("Nutrition",list);
				
				return newmap;
				
				
				}
				catch(Exception e)
				{
					e.printStackTrace();
					
				}
				return null;
			}
	

}

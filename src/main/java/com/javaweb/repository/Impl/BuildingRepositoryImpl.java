package com.javaweb.repository.Impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;


@Repository
public class BuildingRepositoryImpl  implements BuildingRepository{
	static final String DB_URL = "jdbc:mysql://localhost:3306/estatebasic";
	static final String USER = "root";
	static final String PASS= "";
	@Override
	public List<BuildingEntity> findAll(String name , Long districtId) {
		StringBuilder sql = new StringBuilder("select * from building b where 1= 1   ");
		if(name!="" && name != null)
		{
			sql.append(" and b.name like '%"+name+"%'   ");
		}
		if(districtId!=null)
		{
			sql.append(" and b.districtid = "+ districtId +"   ");
		}
		List<BuildingEntity> kq = new ArrayList<BuildingEntity>();
		try(Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql.toString());
			){
			
			while (rs.next())
			{
				BuildingEntity building = new BuildingEntity();
				building.setName(rs.getString("name"));
				building.setNumberOfbasement(rs.getInt("numberOfBasement"));
				building.setStreet(rs.getString("street"));
				building.setWard(rs.getString("ward"));
				
				kq.add(building);
			}
			
			System.out.println("connected with database in mysql is successfully");
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			System.out.println("connected with database iss failed");
		}
		return kq;
	}

}

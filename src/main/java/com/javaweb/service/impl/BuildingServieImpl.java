package com.javaweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.model.BuildingDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.service.BuildingService;

@Service
public class BuildingServieImpl implements BuildingService{
	@Autowired
	private BuildingRepository buildingRepository;
	
	@Override
	public List<BuildingDTO> findAll(String name , Long districtId) {
		
		List<BuildingEntity> buldingEntities = buildingRepository.findAll(name , districtId);
		List<BuildingDTO> result = new ArrayList<BuildingDTO>();
		
		for(BuildingEntity item:buldingEntities)
		{
			BuildingDTO building = new BuildingDTO();
			building.setName(item.getName());
			building.setNumberOfbasement(item.getNumberOfbasement());
			building.setAddress(item.getStreet()+ " , "+ item.getWard());
			result.add(building);
		}
		return result;
	}

}

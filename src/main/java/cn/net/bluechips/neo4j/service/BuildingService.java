package cn.net.bluechips.neo4j.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.net.bluechips.neo4j.entity.Building;
import cn.net.bluechips.neo4j.repository.BuildingRepository;
@Service
public class BuildingService {
	@Autowired
	private BuildingRepository repo;

	public Building save(Building building) {
		return  repo.save(building);
	}

	public Optional<Building> findOne(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}
}

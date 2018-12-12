package cn.net.bluechips.neo4j.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.net.bluechips.neo4j.entity.Floor;
import cn.net.bluechips.neo4j.repository.FloorRepository;
@Service
public class FloorService {
	@Autowired
	private FloorRepository repo;

	public Floor save(Floor floor) {
		return  repo.save(floor);
	}

	public Optional<Floor> findOne(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}
}

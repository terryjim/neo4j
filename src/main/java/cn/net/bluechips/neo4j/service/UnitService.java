package cn.net.bluechips.neo4j.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.net.bluechips.neo4j.entity.Unit;
import cn.net.bluechips.neo4j.repository.UnitRepository;
@Service
public class UnitService {
	@Autowired
	private UnitRepository repo;

	public Unit save(Unit unit) {
		return  repo.save(unit);
	}

	public Optional<Unit> findOne(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}
}

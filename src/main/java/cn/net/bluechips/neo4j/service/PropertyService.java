package cn.net.bluechips.neo4j.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.net.bluechips.neo4j.entity.Property;
import cn.net.bluechips.neo4j.repository.PropertyRepository;
@Service
public class PropertyService {
	@Autowired
	private PropertyRepository repo;

	public Property save(Property property) {
		return  repo.save(property);
	}

	public Optional<Property> findOne(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}
}

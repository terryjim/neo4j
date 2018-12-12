package cn.net.bluechips.neo4j.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.net.bluechips.neo4j.entity.AccessControl;
import cn.net.bluechips.neo4j.repository.AccessControlRepository;
@Service
public class AccessControlService {
	@Autowired
	private AccessControlRepository repo;

	public AccessControl save(AccessControl accessControl) {
		return  repo.save(accessControl);
	}

	public Optional<AccessControl> findOne(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}
}

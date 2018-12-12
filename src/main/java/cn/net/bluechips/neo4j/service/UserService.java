package cn.net.bluechips.neo4j.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.net.bluechips.neo4j.entity.User;
import cn.net.bluechips.neo4j.repository.UserRepository;
@Service
public class UserService {
	@Autowired
	private UserRepository repo;

	public User save(User user) {
		return  repo.save(user);
	}

	public Optional<User> findOne(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}
}

package cn.net.bluechips.neo4j.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import cn.net.bluechips.neo4j.entity.User;

public interface UserRepository extends Neo4jRepository< User, Long> {
	/*Property save(Property property);

	Property findOne(Long id);
	Property findByName(String name);*/
}

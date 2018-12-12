package cn.net.bluechips.neo4j.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import cn.net.bluechips.neo4j.entity.AccessControl;

public interface AccessControlRepository extends Neo4jRepository< AccessControl, Long> {
	/*Property save(Property property);

	Property findOne(Long id);
	Property findByName(String name);*/
}

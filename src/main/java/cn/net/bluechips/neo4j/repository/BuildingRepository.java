package cn.net.bluechips.neo4j.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import cn.net.bluechips.neo4j.entity.Building;

public interface BuildingRepository extends Neo4jRepository< Building, Long> {
	/*Property save(Property property);

	Property findOne(Long id);
	Property findByName(String name);*/
}

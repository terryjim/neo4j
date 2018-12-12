package cn.net.bluechips.neo4j.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.net.bluechips.neo4j.entity.Room;
import cn.net.bluechips.neo4j.repository.RoomRepository;
@Service
public class RoomService {
	@Autowired
	private RoomRepository repo;

	public Room save(Room room) {
		return  repo.save(room);
	}

	public Optional<Room> findOne(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}
}

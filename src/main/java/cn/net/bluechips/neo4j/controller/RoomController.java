package cn.net.bluechips.neo4j.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.com.topit.base.RespCode;
import cn.com.topit.base.RespEntity;
import cn.net.bluechips.neo4j.entity.Building;
import cn.net.bluechips.neo4j.entity.Room;
import cn.net.bluechips.neo4j.service.BuildingService;
import cn.net.bluechips.neo4j.service.RoomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "房间基础信息!", tags = "room")
@RestController
@RequestMapping("/room")
@Slf4j
public class RoomController {
	@Autowired
	RoomService service;
	@Autowired
	BuildingService bService;

	@ApiOperation(value = "保存房号", notes = "")
	@PostMapping("save")
	public RespEntity save(@RequestBody(required = true) Room room) {
		System.out.println("---------------");
		try {
			service.save(room);
			return new RespEntity(RespCode.SUCCESS, room);
		} catch (Exception e) {
			System.out.println(e);
			return new RespEntity(RespCode.ERROR, e.toString());
		}
	}
/*
	@ApiOperation(value = "保存物业－楼栋关联信息", notes = "")
	@PostMapping("saveBuilding")
	public RespEntity saveBuilding(@RequestParam Long roomId, @RequestParam Long buildingId) {
		try {
			Optional<Room> room = service.findOne(roomId);
			Optional<Building> building = bService.findOne(buildingId);
			room.get().addBuilding(building.get());
			service.save(room.get());
			return new RespEntity(RespCode.SUCCESS, room);
		} catch (Exception e) {
			log.error("保存物业－楼栋关联信息:{}", e);
			return new RespEntity(RespCode.ERROR, e.toString());
		}
	}

	@ApiOperation(value = "批量保存物业－楼栋关联信息", notes = "")
	@PostMapping("saveBuildings")
	public RespEntity saveBuildings(@RequestParam Long roomId, @RequestParam String buildingIds) {
		try {
			Optional<Room> room = service.findOne(roomId);
			String[] buildings = buildingIds.split(",");
			for (int i = 0; i < buildings.length; i++) {
				String buildingId = buildings[i];
				Optional<Building> building = bService.findOne(Long.parseLong(buildingId));
				room.get().addBuilding(building.get());
			}
			service.save(room.get());
			return new RespEntity(RespCode.SUCCESS, room);
		} catch (Exception e) {
			log.error("保存物业－楼栋关联信息:{}", e);
			return new RespEntity(RespCode.ERROR, e.toString());
		}
	}*/
}

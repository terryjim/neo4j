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
import cn.net.bluechips.neo4j.entity.AccessControl;
import cn.net.bluechips.neo4j.entity.Floor;
import cn.net.bluechips.neo4j.entity.Room;
import cn.net.bluechips.neo4j.service.AccessControlService;
import cn.net.bluechips.neo4j.service.FloorService;
import cn.net.bluechips.neo4j.service.RoomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "楼层信息!", tags = "floor")
@RestController
@RequestMapping("/floor")
@Slf4j
public class FloorController {
	@Autowired
	FloorService service;
	@Autowired
	RoomService rService;
	@Autowired
	AccessControlService aService;

	@ApiOperation(value = "保存楼层", notes = "")
	@PostMapping("save")
	public RespEntity save(@RequestBody(required = true) Floor floor) {
		System.out.println("---------------");
		try {
			service.save(floor);
			return new RespEntity(RespCode.SUCCESS, floor);
		} catch (Exception e) {
			System.out.println(e);
			return new RespEntity(RespCode.ERROR, e.toString());
		}
	}

	@ApiOperation(value = "保存楼层－房号关联信息", notes = "")
	@PostMapping("saveRoom")
	public RespEntity saveRoom(@RequestParam Long floorId, @RequestParam Long roomId) {
		try {
			Optional<Floor> floor = service.findOne(floorId);
			Optional<Room> room = rService.findOne(roomId);
			floor.get().addRoom(room.get());
			service.save(floor.get());
			return new RespEntity(RespCode.SUCCESS, floor);
		} catch (Exception e) {
			log.error("保存楼层－房号关联信息:{}", e);
			return new RespEntity(RespCode.ERROR, e.toString());
		}
	}

	@ApiOperation(value = "批量保存楼层－房号关联信息", notes = "")
	@PostMapping("saveRooms")
	public RespEntity saveRooms(@RequestParam Long floorId, @RequestParam String roomIds) {
		try {
			Optional<Floor> floor = service.findOne(floorId);
			String[] rooms = roomIds.split(",");
			for (int i = 0; i < rooms.length; i++) {
				String roomId = rooms[i];
				Optional<Room> room = rService.findOne(Long.parseLong(roomId));
				floor.get().addRoom(room.get());
			}
			service.save(floor.get());
			return new RespEntity(RespCode.SUCCESS, floor);
		} catch (Exception e) {
			log.error("保存楼层－房号关联信息:{}", e);
			return new RespEntity(RespCode.ERROR, e.toString());
		}
	}
	
	@ApiOperation(value = "批量保存楼层－门禁设备关联信息", notes = "")
	@PostMapping("saveAccessControls")
	public RespEntity saveAccessControls(@RequestParam Long floorId, @RequestParam String accessControlIds) {
		try {
			Optional<Floor> floor = service.findOne(floorId);
			String[] accessControls = accessControlIds.split(",");
			for (int i = 0; i < accessControls.length; i++) {
				String accessControlId = accessControls[i];
				Optional<AccessControl> accessControl = aService.findOne(Long.parseLong(accessControlId));
				accessControl.get().addLocation(floor.get());
				aService.save(accessControl.get());
			}			
			return new RespEntity(RespCode.SUCCESS, floor);
		} catch (Exception e) {
			log.error("保存保存楼层－门禁设备关联信息出错:{}", e);
			return new RespEntity(RespCode.ERROR, e.toString());
		}
	}
}

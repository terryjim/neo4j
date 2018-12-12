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
import cn.net.bluechips.neo4j.entity.User;
import cn.net.bluechips.neo4j.service.RoomService;
import cn.net.bluechips.neo4j.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "用户信息!", tags = "user")
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
	@Autowired
	UserService service;
	@Autowired
	RoomService rService;

	@ApiOperation(value = "保存用户", notes = "")
	@PostMapping("save")
	public RespEntity save(@RequestBody(required = true) User user) {
		System.out.println("---------------");
		try {
			service.save(user);
			return new RespEntity(RespCode.SUCCESS, user);
		} catch (Exception e) {
			System.out.println(e);
			return new RespEntity(RespCode.ERROR, e.toString());
		}
	}

	@ApiOperation(value = "保存用户--位置关联信息", notes = "")
	@PostMapping("saveLocation")
	public RespEntity saveBuilding(@RequestParam Long userId, @RequestParam Long id, @RequestParam boolean isOwner) {
		try {
			Optional<User> user = service.findOne(userId);
			Optional<Room> room = rService.findOne(id);
			if (isOwner)
				user.get().addOwnerLocation(room.get());
			else
				user.get().addliveLocation(room.get());
			service.save(user.get());
			return new RespEntity(RespCode.SUCCESS, user);
		} catch (Exception e) {
			log.error("保存用户--位置关联信息出错:{}", e);
			return new RespEntity(RespCode.ERROR, e.toString());
		}
	}

	@ApiOperation(value = "批量保存用户--位置关联信息", notes = "")
	@PostMapping("saveBuildings")
	public RespEntity saveBuildings(@RequestParam Long userId, @RequestParam String ids,
			@RequestParam boolean isOwner) {
		try {
			Optional<User> user = service.findOne(userId);
			String[] locationIds = ids.split(",");
			for (int i = 0; i < locationIds.length; i++) {
				String roomId = locationIds[i];
				Optional<Room> room = rService.findOne(Long.parseLong(roomId));
				if (isOwner)
					user.get().addOwnerLocation(room.get());
				else
					user.get().addliveLocation(room.get());
			}
			service.save(user.get());
			return new RespEntity(RespCode.SUCCESS, user);
		} catch (Exception e) {
			log.error("保存用户--位置关联信息出错:{}", e);
			return new RespEntity(RespCode.ERROR, e.toString());
		}
	}
}

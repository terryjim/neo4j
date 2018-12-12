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
import cn.net.bluechips.neo4j.entity.Unit;
import cn.net.bluechips.neo4j.service.AccessControlService;
import cn.net.bluechips.neo4j.service.FloorService;
import cn.net.bluechips.neo4j.service.UnitService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(value = "单元信息!", tags = "unit")
@RestController
@RequestMapping("/unit")
public class UnitController {
	@Autowired	
	UnitService service;
	@Autowired
	FloorService fService;
	@Autowired
	AccessControlService aService;
	@ApiOperation(value = "保存单元信息", notes = "")
	@PostMapping("save")
	public RespEntity save(@RequestBody(required = true) Unit unit) {

		try {
			service.save(unit);
			return new RespEntity(RespCode.SUCCESS, unit);
		} catch (Exception e) {
			System.out.println(e);
			return new RespEntity(RespCode.ERROR, e.toString());
		}
	}

	@ApiOperation(value = "保存单元－楼层关联信息", notes = "")
	@PostMapping("saveFloor")
	public RespEntity saveFloor(@RequestParam Long unitId, @RequestParam Long floorId) {
		try {
			Optional<Unit> unit = service.findOne(unitId);
			Optional<Floor> floor = fService.findOne(floorId);
			unit.get().addFloor(floor.get());
			service.save(unit.get());
			return new RespEntity(RespCode.SUCCESS, unit);
		} catch (Exception e) {
			log.error("保存单元－楼层关联信息:{}", e);
			return new RespEntity(RespCode.ERROR, e.toString());
		}
	}

	@ApiOperation(value = "批量保存单元－楼层关联信息", notes = "")
	@PostMapping("saveFloors")
	public RespEntity saveFloors(@RequestParam Long unitId, @RequestParam String floorIds) {
		try {
			Optional<Unit> unit = service.findOne(unitId);
			String[] floors = floorIds.split(",");
			for (int i = 0; i < floors.length; i++) {
				String floorId = floors[i];
				Optional<Floor> floor = fService.findOne(Long.parseLong(floorId));
				unit.get().addFloor(floor.get());
			}
			service.save(unit.get());
			return new RespEntity(RespCode.SUCCESS, unit);
		} catch (Exception e) {
			log.error("保存单元－楼层关联信息:{}", e);
			return new RespEntity(RespCode.ERROR, e.toString());
		}
	}
	@ApiOperation(value = "批量保存楼层－门禁设备关联信息", notes = "")
	@PostMapping("saveAccessControls")
	public RespEntity saveAccessControls(@RequestParam Long unitId, @RequestParam String accessControlIds) {
		try {
			Optional<Unit> unit = service.findOne(unitId);
			String[] accessControls = accessControlIds.split(",");
			for (int i = 0; i < accessControls.length; i++) {
				String accessControlId = accessControls[i];
				Optional<AccessControl> accessControl = aService.findOne(Long.parseLong(accessControlId));
				accessControl.get().addLocation(unit.get());
				aService.save(accessControl.get());
			}			
			return new RespEntity(RespCode.SUCCESS, unit);
		} catch (Exception e) {
			log.error("保存保存单元－门禁设备关联信息出错:{}", e);
			return new RespEntity(RespCode.ERROR, e.toString());
		}
	}
}

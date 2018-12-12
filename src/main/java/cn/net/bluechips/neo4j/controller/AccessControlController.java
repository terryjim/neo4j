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
import cn.net.bluechips.neo4j.entity.AccessControl;
import cn.net.bluechips.neo4j.service.BuildingService;
import cn.net.bluechips.neo4j.service.AccessControlService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "门禁设备基础信息!", tags = "accessControl")
@RestController
@RequestMapping("/accessControl")
@Slf4j
public class AccessControlController {
	@Autowired
	AccessControlService service;
	@Autowired
	BuildingService bService;

	@ApiOperation(value = "保存门禁设备", notes = "")
	@PostMapping("save")
	public RespEntity save(@RequestBody(required = true) AccessControl accessControl) {
		System.out.println("---------------");
		try {
			service.save(accessControl);
			return new RespEntity(RespCode.SUCCESS, accessControl);
		} catch (Exception e) {
			System.out.println(e);
			return new RespEntity(RespCode.ERROR, e.toString());
		}
	}
/*
	@ApiOperation(value = "保存物业－楼栋关联信息", notes = "")
	@PostMapping("saveBuilding")
	public RespEntity saveBuilding(@RequestParam Long accessControlId, @RequestParam Long buildingId) {
		try {
			Optional<AccessControl> accessControl = service.findOne(accessControlId);
			Optional<Building> building = bService.findOne(buildingId);
			accessControl.get().addBuilding(building.get());
			service.save(accessControl.get());
			return new RespEntity(RespCode.SUCCESS, accessControl);
		} catch (Exception e) {
			log.error("保存物业－楼栋关联信息:{}", e);
			return new RespEntity(RespCode.ERROR, e.toString());
		}
	}

	@ApiOperation(value = "批量保存物业－楼栋关联信息", notes = "")
	@PostMapping("saveBuildings")
	public RespEntity saveBuildings(@RequestParam Long accessControlId, @RequestParam String buildingIds) {
		try {
			Optional<AccessControl> accessControl = service.findOne(accessControlId);
			String[] buildings = buildingIds.split(",");
			for (int i = 0; i < buildings.length; i++) {
				String buildingId = buildings[i];
				Optional<Building> building = bService.findOne(Long.parseLong(buildingId));
				accessControl.get().addBuilding(building.get());
			}
			service.save(accessControl.get());
			return new RespEntity(RespCode.SUCCESS, accessControl);
		} catch (Exception e) {
			log.error("保存物业－楼栋关联信息:{}", e);
			return new RespEntity(RespCode.ERROR, e.toString());
		}
	}*/
}

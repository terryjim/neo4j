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
import cn.net.bluechips.neo4j.entity.Building;
import cn.net.bluechips.neo4j.entity.Property;
import cn.net.bluechips.neo4j.service.AccessControlService;
import cn.net.bluechips.neo4j.service.BuildingService;
import cn.net.bluechips.neo4j.service.PropertyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "项目部基础信息!", tags = "property")
@RestController
@RequestMapping("/property")
@Slf4j
public class PropertyController {
	@Autowired
	PropertyService service;
	@Autowired
	BuildingService bService;
	@Autowired
	AccessControlService aService;
	@ApiOperation(value = "保存物业项目部", notes = "")
	@PostMapping("save")
	public RespEntity save(@RequestBody(required = true) Property property) {
		System.out.println("---------------");
		try {
			service.save(property);
			return new RespEntity(RespCode.SUCCESS, property);
		} catch (Exception e) {
			System.out.println(e);
			return new RespEntity(RespCode.ERROR, e.toString());
		}
	}

	@ApiOperation(value = "保存物业－楼栋关联信息", notes = "")
	@PostMapping("saveBuilding")
	public RespEntity saveBuilding(@RequestParam Long propertyId, @RequestParam Long buildingId) {
		try {
			Optional<Property> property = service.findOne(propertyId);
			Optional<Building> building = bService.findOne(buildingId);
			property.get().addBuilding(building.get());
			service.save(property.get());
			return new RespEntity(RespCode.SUCCESS, property);
		} catch (Exception e) {
			log.error("保存物业－楼栋关联信息:{}", e);
			return new RespEntity(RespCode.ERROR, e.toString());
		}
	}

	@ApiOperation(value = "批量保存物业－楼栋关联信息", notes = "")
	@PostMapping("saveBuildings")
	public RespEntity saveBuildings(@RequestParam Long propertyId, @RequestParam String buildingIds) {
		try {
			Optional<Property> property = service.findOne(propertyId);
			String[] buildings = buildingIds.split(",");
			for (int i = 0; i < buildings.length; i++) {
				String buildingId = buildings[i];
				Optional<Building> building = bService.findOne(Long.parseLong(buildingId));
				property.get().addBuilding(building.get());
			}
			service.save(property.get());
			return new RespEntity(RespCode.SUCCESS, property);
		} catch (Exception e) {
			log.error("保存物业－楼栋关联信息:{}", e);
			return new RespEntity(RespCode.ERROR, e.toString());
		}
	}
	
	@ApiOperation(value = "批量保存小区－门禁设备关联信息", notes = "")
	@PostMapping("saveAccessControls")
	public RespEntity saveAccessControls(@RequestParam Long propertyId, @RequestParam String accessControlIds) {
		try {
			Optional<Property> property = service.findOne(propertyId);
			String[] accessControls = accessControlIds.split(",");
			for (int i = 0; i < accessControls.length; i++) {
				String accessControlId = accessControls[i];
				Optional<AccessControl> accessControl = aService.findOne(Long.parseLong(accessControlId));
				accessControl.get().addLocation(property.get());
				aService.save(accessControl.get());
			}			
			return new RespEntity(RespCode.SUCCESS, property);
		} catch (Exception e) {
			log.error("保存保存楼层－门禁设备关联信息出错:{}", e);
			return new RespEntity(RespCode.ERROR, e.toString());
		}
	}
}

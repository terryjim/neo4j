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
import cn.net.bluechips.neo4j.entity.Property;
import cn.net.bluechips.neo4j.entity.Unit;
import cn.net.bluechips.neo4j.service.BuildingService;
import cn.net.bluechips.neo4j.service.UnitService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(value = "楼栋信息!", tags = "Building")
@RestController
@RequestMapping("/building")
public class BuildingController {
	@Autowired
	BuildingService service;
	@Autowired
	UnitService uService;

	@ApiOperation(value = "保存楼栋信息", notes = "")
	@PostMapping("save")
	public RespEntity save(@RequestBody(required = true) Building Building) {

		try {
			service.save(Building);
			return new RespEntity(RespCode.SUCCESS, Building);
		} catch (Exception e) {
			System.out.println(e);
			return new RespEntity(RespCode.ERROR, e.toString());
		}
	}

	@ApiOperation(value = "保存楼栋－单元关联信息", notes = "")
	@PostMapping("saveUnit")
	public RespEntity saveUnit(@RequestParam Long buildingId, @RequestParam Long unitId) {
		try {
			Optional<Building> building = service.findOne(buildingId);
			Optional<Unit> unit = uService.findOne(unitId);
			building.get().addUnit(unit.get());
			service.save(building.get());
			return new RespEntity(RespCode.SUCCESS, building);
		} catch (Exception e) {
			log.error("保存楼栋－单元关联信息:{}", e);
			return new RespEntity(RespCode.ERROR, e.toString());
		}
	}

	@ApiOperation(value = "批量保存楼栋－单元关联信息", notes = "")
	@PostMapping("saveUnits")
	public RespEntity saveUnits(@RequestParam Long buildingId, @RequestParam String unitIds) {
		try {
			Optional<Building> building = service.findOne(buildingId);
			String[] units = unitIds.split(",");
			for (int i = 0; i < units.length; i++) {
				String unitId = units[i];
				Optional<Unit> unit = uService.findOne(Long.parseLong(unitId));
				building.get().addUnit(unit.get());
			}
			service.save(building.get());
			return new RespEntity(RespCode.SUCCESS, building);
		} catch (Exception e) {
			log.error("保存楼栋－单元关联信息:{}", e);
			return new RespEntity(RespCode.ERROR, e.toString());
		}
	}
}

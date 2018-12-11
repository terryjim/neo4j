package cn.net.bluechips.neo4j.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.com.topit.base.RespCode;
import cn.com.topit.base.RespEntity;
import cn.net.bluechips.neo4j.entity.Building;
import cn.net.bluechips.neo4j.service.BuildingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "楼栋信息!", tags = "Building")
@RestController
@RequestMapping("/building") 
public class BuildingController {
@Autowired BuildingService service;
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
}

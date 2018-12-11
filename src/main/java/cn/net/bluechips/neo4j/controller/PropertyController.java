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
import cn.net.bluechips.neo4j.entity.Property;
import cn.net.bluechips.neo4j.service.PropertyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "项目部基础信息!", tags = "property")
@RestController
@RequestMapping("/property") 
public class PropertyController {
@Autowired PropertyService service;
@ApiOperation(value = "保存物业项目部", notes = "")
@PostMapping("save")
public RespEntity save(@RequestBody(required = true) Property property) {
	
	try {
		service.save(property);		
		return new RespEntity(RespCode.SUCCESS, property);
	} catch (Exception e) {
		System.out.println(e);
		return new RespEntity(RespCode.ERROR, e.toString());
	}
}
}

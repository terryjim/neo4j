package cn.com.topit.utils;

import java.io.IOException;

import org.neo4j.ogm.typeconversion.AttributeConverter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
//数据库json字段转换为对应类型
public class JpaConverterJson implements AttributeConverter<Object, String> {

	  private final static ObjectMapper objectMapper = new ObjectMapper();
/*
	  @Override
	  public String convertToDatabaseColumn(Object meta) {
	    try {
	      return objectMapper.writeValueAsString(meta);
	    } catch (JsonProcessingException ex) {
	    	System.out.println(ex);
	    	System.out.println(meta);
	      return null;
	      // or throw an error
	    }
	  }

	  @Override
	  public Object convertToEntityAttribute(String dbData) {
	    try {
	      return objectMapper.readValue(dbData, Object.class);
	    } catch (IOException ex) {
	      // logger.error("Unexpected IOEx decoding json from database: " + dbData);
	      return null;
	    }
	  }*/

	@Override
	public Object toEntityAttribute(String arg0) {
		 try {
		      return objectMapper.readValue(arg0, Object.class);
		    } catch (IOException ex) {
		      // logger.error("Unexpected IOEx decoding json from database: " + dbData);
		      return null;
		    }
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toGraphProperty(Object arg0) {
		// TODO Auto-generated method stub
		 try {
		      return objectMapper.writeValueAsString(arg0);
		    } catch (IOException ex) {
		      // logger.error("Unexpected IOEx decoding json from database: " + dbData);
		      return null;
		    }
	}

	}
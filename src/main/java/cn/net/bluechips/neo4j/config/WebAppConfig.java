package cn.net.bluechips.neo4j.config;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

@Configuration
@EnableAutoConfiguration
public class WebAppConfig extends WebMvcConfigurerAdapter {
	
	
	

	/*@Bean
	public MultipartConfigElement multipartConfigElement() {
		// 在配置文件里添加了就无需此类
		// http.multipart.max-request-size=1000Mb http.multipart.max-file-size=10Mb
		MultipartConfigFactory factory = new MultipartConfigFactory();
		// 文件最大KB,MB
		factory.setMaxFileSize("10MB");
		// 设置总上传数据总大小
		factory.setMaxRequestSize("100MB");
		return factory.createMultipartConfig();
	}*/

	/**
	 * 解决跨域问题
	 */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*").allowCredentials(false)
				.allowedMethods("GET", "POST", "DELETE", "PUT","OPTIONS").maxAge(3600);
	}
	
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		MappingJackson2HttpMessageConverter mjmc = new MappingJackson2HttpMessageConverter();
		ObjectMapper objectMapper = new ObjectMapper();
		DeserializationConfig dc = objectMapper.getDeserializationConfig();
		// 设置反序列化日期格式、忽略不存在get、set的属性,以避免拷贝属性时将没传值的属性自动修改为null
		objectMapper.setConfig(dc.with(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))
				.without(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES));
		mjmc.setObjectMapper(objectMapper);

		// 设置中文编码格式
		List<MediaType> list = new ArrayList<MediaType>();
		list.add(MediaType.APPLICATION_JSON_UTF8);
		mjmc.setSupportedMediaTypes(list);
		//converters.add(mjmc);		
		
		
		 /**
         * 序列换成json时,将所有的long变成string
         * 因为js中得数字类型不能包含所有的java long值
         */
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        objectMapper.registerModule(simpleModule);
        mjmc.setObjectMapper(objectMapper);
        converters.add(mjmc);
		
		
		
	}
	/*国际化!!!未实现*/
	 @Bean
	    public LocaleResolver localeResolver() {
	        SessionLocaleResolver slr = new SessionLocaleResolver();
	        // 默认语言
	        slr.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
	        return slr;
	    }

	    @Bean
	    public LocaleChangeInterceptor localeChangeInterceptor() {
	        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
	        // 参数名
	        lci.setParamName("lang");
	        return lci;
	    }

	    @Override
	    public void addInterceptors(InterceptorRegistry registry) {
	        registry.addInterceptor(localeChangeInterceptor());
	    }
}

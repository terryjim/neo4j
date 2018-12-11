package cn.net.bluechips.neo4j;


import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2 {

  /*  @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.net.bluechips.zsystem"))
                .paths(PathSelectors.any())
                .build();
    }*/
    @Bean  
    public Docket customDocket(){  
        ParameterBuilder ticketPar = new ParameterBuilder();  
        List<Parameter> pars = new ArrayList<Parameter>();    
        ticketPar.name("Authorization").description("jwt token")  
        .modelRef(new ModelRef("string")).parameterType("header")   
        .required(false).build(); //header中的ticket参数非必填，传空也可以  
        pars.add(ticketPar.build());    //根据每个方法名也知道当前方法在设置什么参数  
   
        return new Docket(DocumentationType.SWAGGER_2)  
                .select()  
                .apis(RequestHandlerSelectors.any())    
                .build()    
                .globalOperationParameters(pars)    
                .apiInfo(apiInfo());  
    }  
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Neo4j")
                .description("智生活系统平台")
                .termsOfServiceUrl("服务条款")
                .contact("terry")
                .version("1.0")
                .build();
    }

}

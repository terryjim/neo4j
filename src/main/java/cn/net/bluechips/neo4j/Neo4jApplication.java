package cn.net.bluechips.neo4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
@SpringBootApplication/*(exclude= {DataSourceAutoConfiguration.class})*/
@ComponentScan(basePackages= {"cn.com.topit.security","cn.com.topit.security.auth","cn.com.topit.util","cn.net.bluechips.neo4j"})
public class Neo4jApplication {

	public static void main(String[] args) {
		SpringApplication.run(Neo4jApplication.class, args);
	}
}

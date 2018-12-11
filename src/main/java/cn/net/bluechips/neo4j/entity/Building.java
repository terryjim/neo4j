package cn.net.bluechips.neo4j.entity;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@NodeEntity
@Data
/*@JsonInclude(value = Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true, includeFieldNames = true)*/
@ApiModel(value = "building", description = "楼栋")
public class Building {
	@Id 
	@GeneratedValue
	private Long id;
	private String name;
}

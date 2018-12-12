package cn.net.bluechips.neo4j.entity;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@NodeEntity
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
/*@JsonInclude(value = Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true, includeFieldNames = true)*/
@ApiModel(value = "unit", description = "单元门")
public class Unit implements Location{
	@Id
	@GeneratedValue
	private Long id;
	private String name;

	@Relationship(type = "of", direction = Relationship.INCOMING)
	private Set<Floor> floors;

	public boolean addFloor(Floor floor) {
		if (this.floors == null)
			this.floors = new HashSet<Floor>();
		return this.floors.add(floor);
	}
}

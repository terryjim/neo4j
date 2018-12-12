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
@ApiModel(value = "accessControl", description = "门禁设备")
public class AccessControl {
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	/*@Convert(JpaConverterJson.class)*/
	@Relationship(type = "has", direction = Relationship.INCOMING)
	private Set<Location> locations = new HashSet<Location>();

	public boolean addLocation(Location location) {
		if (this.locations == null)
			this.locations = new HashSet<Location>();
		return this.locations.add(location);
	}

	public boolean delLocation(Location location) {
		try {
			return this.locations.remove(location);
		} catch (Exception e) {
			return false;
		}

	}
}

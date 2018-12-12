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
@ApiModel(value = "user", description = "用户")
public class User {
	@Id
	@GeneratedValue
	private Long id;
	private String name;

	@Relationship(type = "live", direction = Relationship.OUTGOING)
	private Set<Location> liveLocations;
	@Relationship(type = "owner", direction = Relationship.OUTGOING)
	private Set<Location> ownerLocations;

	public boolean addliveLocation(Location location) {
		if (this.liveLocations == null)
			this.liveLocations = new HashSet<Location>();
		return this.liveLocations.add(location);
	}

	public boolean addOwnerLocation(Location location) {
		if (this.ownerLocations == null)
			this.ownerLocations = new HashSet<Location>();
		return this.ownerLocations.add(location);
	}
}

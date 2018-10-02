package com.k2.EntityMap;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EntityB {

	@Id
	public Integer id;
	public String name;
	
	public EntityB(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
}

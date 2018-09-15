package com.k2.EntityMap;

import javax.persistence.Id;

public class EntityB {

	@Id
	public Integer id;
	public String name;
	
	public EntityB(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
}

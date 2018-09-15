package com.k2.EntityMap;

import javax.persistence.Id;

public class EntityA {

	@Id
	public Integer id;
	public String name;
	
	public EntityA(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
}

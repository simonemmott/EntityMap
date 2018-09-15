package com.k2.EntityMap;

import javax.persistence.Id;

public class ExtendedA extends EntityA{

	public String description;
	
	public ExtendedA(Integer id, String name, String description) {
		super(id, name);
		this.description = description;
	}
}

package com.k2.EntityMap;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ExtendedA extends EntityA{

	public String description;
	
	public ExtendedA(Integer id, String name, String description) {
		super(id, name);
		this.description = description;
	}
}

package com.k2.EntityMap;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class EntityMapTests {
	
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
		
	@Test
	public void createEntityMapTest() {
		
		EntityMap<EntityA,Integer> entityMap = EntityMap.create(EntityA.class, Integer.class);
		
		assertNotNull(entityMap);
		assertEquals(EntityA.class, entityMap.getEntityClass());
		assertEquals(Integer.class, entityMap.getKeyClass());
		
	}
	
	@Test
	public void putEntityTest() {
		
		EntityA ea1 = new EntityA(1, "EA1");
		EntityA ea2 = new EntityA(2, "EA2");
		
		EntityMap<EntityA,Integer> entityMap = EntityMap.create(EntityA.class, Integer.class);

		entityMap.put(ea1);
		entityMap.put(ea2);
		
		assertEquals("EA1", entityMap.get(1).name);
		assertEquals("EA2", entityMap.get(2).name);
		
	}
	
	@Test
	public void putExtendedEntityTest() {

		ExtendedA ea3 = new ExtendedA(3, "EA3", "description");
		
		EntityMap<EntityA,Integer> entityMap = EntityMap.create(EntityA.class, Integer.class);

		entityMap.put(ea3);
		
		
		assertEquals("EA3", entityMap.get(3).name);
		
		assertEquals(ExtendedA.class, entityMap.get(3).getClass());
		

	}
	
	@Test
	public void entityMapSizeTest() {
		
		EntityA ea1 = new EntityA(1, "EA1");
		EntityA ea2 = new EntityA(2, "EA2");
		
		EntityMap<EntityA,Integer> entityMap = EntityMap.create(EntityA.class, Integer.class);

		assertEquals(0, entityMap.size());

		entityMap.put(ea1);
		
		assertEquals(1, entityMap.size());

		entityMap.put(ea2);
		
		assertEquals(2, entityMap.size());
		

		
	}
	
	@Test
	public void removeEntityTest() {
		
		EntityA ea1 = new EntityA(1, "EA1");
		EntityA ea2 = new EntityA(2, "EA2");
		
		EntityMap<EntityA,Integer> entityMap = EntityMap.create(EntityA.class, Integer.class);

		entityMap.put(ea1);
		entityMap.put(ea2);
		
		entityMap.remove(1);
		assertNull(entityMap.get(1));
		
		entityMap.remove(ea2);
		assertNull(entityMap.get(2));
		
		assertEquals(0, entityMap.size());

	}
	
	@Test
	public void listEntitiesTest() {
		
		EntityA ea1 = new EntityA(1, "EA1");
		EntityA ea2 = new EntityA(2, "EA2");
		ExtendedA ea3 = new ExtendedA(3, "EA3", "description");
		
		EntityMap<EntityA,Integer> entityMap = EntityMap.create(EntityA.class, Integer.class);

		entityMap.put(ea2);
		entityMap.put(ea3);
		entityMap.put(ea1);
		
		List<EntityA> entityList = entityMap.list();
		
		Comparator<EntityA> comparator = new Comparator<EntityA>() {
			@Override
			public int compare(EntityA e1, EntityA e2) {
				return e1.id - e2.id;
			}
		};
		
		Collections.sort(entityList, comparator);
		
		assertEquals(3, entityList.size());
		assertEquals(Integer.valueOf(1), entityList.get(0).id);
		assertEquals(Integer.valueOf(2), entityList.get(1).id);
		assertEquals(Integer.valueOf(3), entityList.get(2).id);

	}
	
	
	
	
	
	
}

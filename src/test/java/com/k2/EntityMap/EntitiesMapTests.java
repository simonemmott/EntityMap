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



public class EntitiesMapTests {
	
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
		

	
	@Test
	public void createEntitiesMapTest() {

		EntitiesMap em = EntitiesMap.create();
		
		assertNotNull(em);

	}
	
	@Test
	public void putEntitiesTest() {
		
		EntityA ea1 = new EntityA(1, "EA1");
		EntityA ea2 = new EntityA(2, "EA2");
		ExtendedA ea3 = new ExtendedA(3, "EA3", "description");
		EntityB eb1 = new EntityB(1, "EB1");
		EntityB eb2 = new EntityB(2, "EB2");

		
		EntitiesMap em = EntitiesMap.create();
		
		em.put(ea1);
		em.put(ea2);
		em.put(ea3);
		em.put(eb2);
		em.put(eb1);
		
		EntityA a1 = em.get(EntityA.class, 1);
		EntityA a2 = em.get(EntityA.class, 2);
		EntityA a3 = em.get(EntityA.class, 3);
		EntityB b1 = em.get(EntityB.class, 1);
		EntityB b2 = em.get(EntityB.class, 2);
		
		assertEquals(ea1, a1);
		assertEquals(ea2, a2);
		assertEquals(ea3, a3);
		assertEquals(eb1, b1);
		assertEquals(eb2, b2);
		
		assertNull(em.get(ExtendedA.class, 1));
		assertNotNull(em.get(ExtendedA.class, 3));
		
	}
	
	@Test
	public void removeEntitiesTest() {

		EntityA ea1 = new EntityA(1, "EA1");
		EntityA ea2 = new EntityA(2, "EA2");
		ExtendedA ea3 = new ExtendedA(3, "EA3", "description");
		EntityB eb1 = new EntityB(1, "EB1");
		EntityB eb2 = new EntityB(2, "EB2");

		
		EntitiesMap em = EntitiesMap.create();
		
		em.put(ea1);
		em.put(ea2);
		em.put(ea3);
		em.put(eb2);
		em.put(eb1);
		
		em.remove(EntityA.class, 1);
		assertNull(em.get(EntityA.class, 1));
		assertNotNull(em.get(EntityA.class, 2));
		assertNotNull(em.get(EntityA.class, 3));
		
		em.remove(EntityB.class, 2);
		assertNotNull(em.get(EntityB.class, 1));
		assertNull(em.get(EntityB.class, 2));
		
	}
	
	@Test
	public void listMappedClassesTest() {
		
		EntityA ea1 = new EntityA(1, "EA1");
		EntityA ea2 = new EntityA(2, "EA2");
		ExtendedA ea3 = new ExtendedA(3, "EA3", "description");
		EntityB eb1 = new EntityB(1, "EB1");
		EntityB eb2 = new EntityB(2, "EB2");

		
		EntitiesMap em = EntitiesMap.create();
		
		em.put(ea1);
		em.put(ea2);
		em.put(ea3);
		em.put(eb2);
		em.put(eb1);
		
		List<Class<?>> mappedClasses = em.getMappedClasses();
		
		assertEquals(2, mappedClasses.size());
		
		Comparator<Class<?>> comparator = new Comparator<Class<?>>() {
			@Override
			public int compare(Class<?> cls1, Class<?> cls2) {
				return cls1.getName().hashCode() - cls2.getName().hashCode();
			}
		};
			
		Collections.sort(mappedClasses, comparator);
		
		assertEquals(EntityA.class, mappedClasses.get(0));
		assertEquals(EntityB.class, mappedClasses.get(1));

	}
	
	@Test
	public void listsEntitiesTest() {
		
		EntityA ea1 = new EntityA(1, "EA1");
		EntityA ea2 = new EntityA(2, "EA2");
		ExtendedA ea3 = new ExtendedA(3, "EA3", "description");
		EntityB eb1 = new EntityB(1, "EB1");
		EntityB eb2 = new EntityB(2, "EB2");

		
		EntitiesMap em = EntitiesMap.create();
		
		em.put(ea1);
		em.put(ea2);
		em.put(ea3);
		em.put(eb2);
		em.put(eb1);
		
		List<EntityA> entityAs = em.list(EntityA.class);
		
		assertEquals(3, entityAs.size());
		
		Comparator<EntityA> compA = new Comparator<EntityA>() {
			@Override
			public int compare(EntityA a1, EntityA a2) {
				return a1.id = a2.id;
			}
		};
		
		Collections.sort(entityAs, compA);
		
		assertEquals(ea1, entityAs.get(0));
		assertEquals(ea2, entityAs.get(1));
		assertEquals(ea3, entityAs.get(2));

		List<EntityB> entityBs = em.list(EntityB.class);
		
		assertEquals(2, entityBs.size());
		
		Comparator<EntityB> compB = new Comparator<EntityB>() {
			@Override
			public int compare(EntityB b1, EntityB b2) {
				return b1.id = b2.id;
			}
		};
		
		Collections.sort(entityBs, compB);
		
		assertEquals(eb1, entityBs.get(0));
		assertEquals(eb2, entityBs.get(1));

	}
	
	
	
}

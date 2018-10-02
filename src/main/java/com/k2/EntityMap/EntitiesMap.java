package com.k2.EntityMap;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.k2.Util.Identity.IdentityUtil;

public class EntitiesMap {

	public static EntitiesMap create() {
		return new EntitiesMap();
	}
	
	private Map<Class<?>,EntityMap> map = new HashMap<Class<?>,EntityMap>();
	
	public <E> EntityMap<E,?> getClassMap(Class<E> entityClass) {
		EntityMap<E,?> em = map.get(IdentityUtil.getBaseEntityClass(entityClass));
		Class<?> keyClass = IdentityUtil.getKeyClass(entityClass);
		if (em == null) {
			em = EntityMap.create(entityClass, keyClass);
			map.put(entityClass, em);
		}
		return em;
	}

	private <E> void removeClassMap(Class<E> entityClass) {
		map.remove(entityClass);
	}
	
	public void put(Object entity) {
		Class<?> entityClass = IdentityUtil.getBaseEntityClass(entity.getClass());
		
		
		getClassMap(entityClass).put(entity);
		
	}

	@SuppressWarnings("unchecked")
	public <E> E get(Class<E> entityClass, Object key ) {
		Object obj = getClassMap(entityClass).get(key);
		if (obj == null)
			return null;
		if (entityClass.isAssignableFrom(obj.getClass())) {
			return (E) obj;
		}
		return null;
	}

	public <E> void remove(Class<E> entityClass, Object key) {
		EntityMap<E,?> entityMap = getClassMap(entityClass);
		entityMap.remove(key);
		if (entityMap.size() == 0) 
			removeClassMap(entityClass);
	}

	public List<Class<?>> getMappedClasses() {
		return Lists.newArrayList(map.keySet());
	}

	public <E> List<E> list(Class<E> entityClass) {
		if (map.containsKey(entityClass))
			return getClassMap(entityClass).list();
			
		return new ArrayList<E>();
	}
	
	
}

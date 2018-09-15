package com.k2.EntityMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.k2.Util.Identity.IdentityUtil;

public class EntityMap<E,K> {

	public static <E,K> EntityMap<E,K> create(Class<E> entityClass, Class<K> keyClass) {
		return new EntityMap<E,K>(entityClass, keyClass);
	}
	
	private EntityMap(Class<E> entityClass, Class<K> keyClass) {
		this.entityClass = entityClass;
		this.keyClass = keyClass;
	}
	
	private final Map<K,E> map = new HashMap<K,E>();
	
	private final Class<K> keyClass;
	public Class<K> getKeyClass() { return keyClass; }
	
	private final Class<E> entityClass;
	public Class<E> getEntityClass() { return entityClass; }

	@SuppressWarnings("unchecked")
	public void put(Object entity) {
		K key = (K) IdentityUtil.getId(entity);
		put(key, (E)entity);
	}
	
	public void put(K key, E entity) {
		map.put(key, entity);
	}

	public E get(Object key) {
		return map.get(key);
	}

	public int size() {
		return map.size();
	}

	@SuppressWarnings("unchecked")
	public E remove(Object obj) {
		if (obj.getClass() == keyClass) {
			return map.remove((K)obj);
		} else if (entityClass.isAssignableFrom(obj.getClass())) {
			
			K key = (K) IdentityUtil.getId(obj);
			return map.remove(key);
		} else {
			throw new EntityMapError("The object (class: {}) to remove is neither an instance of the entityClass or the keyClass", obj.getClass().getSimpleName()) ;
		}
		
	}

	public List<E> list() {
		return Lists.newArrayList(map.values());
	}



}

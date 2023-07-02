package br.com.uniacademia.enade.util;

import java.lang.reflect.Field;
import java.util.Hashtable;

import org.springframework.stereotype.Component;

@Component
public class Util {
	
	@SuppressWarnings("rawtypes")
	public static <E> E mesclarClasse(E oldEntity, E newEntity) {

	    Field[] newEntityFields = newEntity.getClass().getDeclaredFields();
	    Hashtable newHT = fieldsToHT(newEntityFields, newEntity);

	    Class oldEntityClass = oldEntity.getClass();
	    Field[] oldEntityFields = oldEntityClass.getDeclaredFields();

	    for (Field field : oldEntityFields){
	        field.setAccessible(true);
	        Object o = newHT.get(field.getName());
	        if (o != null){
	            try {
	                Field f = oldEntityClass.getDeclaredField(field.getName());
	                f.setAccessible(true);
	                f.set(oldEntity, o);
	            } catch (IllegalAccessException e) {
	                e.printStackTrace();
	            } catch (NoSuchFieldException e) {
	                e.printStackTrace();
	            }
	        }

	        }

	    return oldEntity;
	}
	
	private static Hashtable<String, Object> fieldsToHT(Field[] fields, Object obj){
	    Hashtable<String,Object> hashtable = new Hashtable<>();
	    for (Field field: fields){
	        field.setAccessible(true);
	        try {
	            Object retrievedObject = field.get(obj);
	            if (retrievedObject != null){
	                hashtable.put(field.getName(), field.get(obj));
	            }
	        } catch (IllegalAccessException e) {
	            e.printStackTrace();
	        }
	    }
	    return hashtable;
	}
	
}

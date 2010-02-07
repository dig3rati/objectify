package com.googlecode.objectify.impl.load;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Collection;

import com.googlecode.objectify.impl.TypeUtils;

/**
 * <p>This setter handles embedded collections similar to embedded arrays.  The special
 * consideration of collections follows the documentation for {@code TypeUtils.prepareCollection()}.</p>
 * 
 * @see TypeUtils#prepareCollection(Object, com.googlecode.objectify.impl.Wrapper)
 */
public class EmbeddedCollectionSetter extends EmbeddedMultivalueSetter
{
	/**
	 */
	Constructor<?> componentTypeCtor;

	/** */
	public EmbeddedCollectionSetter(Field field, String path, String collsionPath)
	{
		super(field, path, collsionPath);

		assert Collection.class.isAssignableFrom(field.getType());
		
		Class<?> componentType = TypeUtils.getComponentType(this.field.getType(), this.field.getGenericType());
		this.componentTypeCtor = TypeUtils.getNoArgConstructor(componentType);
	}

	/* (non-Javadoc)
	 * @see com.googlecode.objectify.impl.load.EmbeddedMultivalueSetter#getComponentTypeConstructor()
	 */
	@Override
	protected Constructor<?> getComponentConstructor()
	{
		return this.componentTypeCtor;
	}

	/* (non-Javadoc)
	 * @see com.googlecode.objectify.impl.load.EmbeddedMultivalueSetter#getOrCreateCollection(java.lang.Object, int)
	 */
	@Override
	protected Collection<Object> getOrCreateCollection(Object onPojo, int size)
	{
		return TypeUtils.prepareCollection(onPojo, this.field, size);
	}
}
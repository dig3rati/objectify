package com.googlecode.objectify.impl.translate;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.impl.Path;
import com.googlecode.objectify.impl.Property;


/**
 * Knows how to convert Ref<?> objects to datastore-native Key objects and vice-versa.
 *
 * @author Jeff Schnitzer <jeff@infohazard.org>
 */
public class RefTranslatorFactory extends ValueTranslatorFactory<Ref<?>, com.google.appengine.api.datastore.Key>
{
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public RefTranslatorFactory() {
		super((Class)Ref.class);
	}

	@Override
	protected ValueTranslator<Ref<?>, com.google.appengine.api.datastore.Key> createValueTranslator(Type type, final Annotation[] annotations, CreateContext ctx, Path path) {
		return new ValueTranslator<Ref<?>, com.google.appengine.api.datastore.Key>(com.google.appengine.api.datastore.Key.class) {

			@Override
			protected Ref<?> loadValue(com.google.appengine.api.datastore.Key value, LoadContext ctx, Path path) throws SkipException {
				//return ctx.makeRef(annotations, Key.create(value));
				// TODO implement me
				return Ref.create(Key.create(value));
			}

			@Override
			protected com.google.appengine.api.datastore.Key saveValue(Ref<?> value, boolean index, SaveContext ctx, Path path) throws SkipException {
				//ctx.registerReference(property, value);
				// TODO impelement me

				return value.key().getRaw();
			}
		};
	}
}
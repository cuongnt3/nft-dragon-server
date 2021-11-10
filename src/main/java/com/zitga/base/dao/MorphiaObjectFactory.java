package com.zitga.base.dao;

import com.mongodb.DBObject;
import org.mongodb.morphia.ObjectFactory;
import org.mongodb.morphia.annotations.ConstructorArgs;
import org.mongodb.morphia.mapping.MappedField;
import org.mongodb.morphia.mapping.Mapper;
import org.mongodb.morphia.mapping.MappingException;

import java.lang.reflect.Constructor;
import java.util.*;

public class MorphiaObjectFactory implements ObjectFactory {

    private static <T> Constructor<T> getNoArgsConstructor(final Class<T> type) {
        try {
            final Constructor<T> constructor = type.getDeclaredConstructor();
            constructor.setAccessible(true);
            return constructor;
        } catch (Throwable e) {
            throw new MappingException("No usable constructor for " + type.getName(), e);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T createInstance(final Class<T> clazz) {
        try {
            return getNoArgsConstructor(clazz).newInstance();
        } catch (Exception e) {
            if (Collection.class.isAssignableFrom(clazz)) {
                return (T) createList(null);
            } else if (Map.class.isAssignableFrom(clazz)) {
                return (T) createMap(null);
            } else if (Set.class.isAssignableFrom(clazz)) {
                return (T) createSet(null);
            }
            throw new MappingException("No usable constructor for " + clazz.getName(), e);
        }
    }

    @Override
    public <T> T createInstance(final Class<T> clazz, final DBObject dbObj) {
        Class<T> c = getClass(dbObj);
        if (c == null) {
            c = clazz;
        }
        return createInstance(c);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object createInstance(final Mapper mapper, final MappedField mappedField, final DBObject dbObj) {
        Class clazz = getClass(dbObj);
        if (clazz == null) {
            clazz = mappedField.isSingleValue() ? mappedField.getConcreteType() : mappedField.getSubClass();
            if (clazz.equals(Object.class)) {
                clazz = mappedField.getConcreteType();
            }
        }

        try {
            return createInstance(clazz, dbObj);
        } catch (RuntimeException e) {
            final ConstructorArgs argAnn = mappedField.getAnnotation(ConstructorArgs.class);
            if (argAnn == null) {
                throw e;
            }

            final Object[] args = new Object[argAnn.value().length];
            final Class[] argTypes = new Class[argAnn.value().length];
            for (int i = 0; i < argAnn.value().length; i++) {
                // using a fake MappedField to hold the value
                final Object val = dbObj.get(argAnn.value()[i]);
                args[i] = val;
                argTypes[i] = val.getClass();
            }

            try {
                final Constructor constructor = clazz.getDeclaredConstructor(argTypes);
                constructor.setAccessible(true);
                return constructor.newInstance(args);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List createList(final MappedField mappedField) {
        return newInstance(mappedField != null ? mappedField.getCTor() : null, ArrayList.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Map createMap(final MappedField mappedField) {
        return newInstance(mappedField != null ? mappedField.getCTor() : null, HashMap.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Set createSet(final MappedField mappedField) {
        return newInstance(mappedField != null ? mappedField.getCTor() : null, HashSet.class);
    }

    @SuppressWarnings("unchecked")
    private <T> Class<T> getClass(final DBObject dbObj) {
        Class clazz = null;

        return clazz;
    }

    /**
     * creates an instance of testType (if it isn't Object.class or null) or fallbackType
     */
    private <T> T newInstance(final Constructor<T> ctor, final Class<T> fallbackType) {
        if (ctor != null) {
            ctor.setAccessible(true);
            try {
                return ctor.newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return createInstance(fallbackType);
    }
}
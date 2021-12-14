package com.zitga.idle.publisher.annotation;

import com.zitga.idle.enumeration.observer.SubjectType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Listener {

    SubjectType[] value();

    boolean isListenAll() default false;
}

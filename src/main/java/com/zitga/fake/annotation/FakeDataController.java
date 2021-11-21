package com.zitga.fake.annotation;

import com.zitga.enumeration.fake.FakePlayerDataType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface FakeDataController {

    FakePlayerDataType[] value();
}

package com.zitga.idle.fake.annotation;


import com.zitga.idle.enumeration.fake.FakePlayerDataType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface FakeDataController {

    FakePlayerDataType[] value();
}

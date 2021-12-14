package com.zitga.idle.resource.annotation;


import com.zitga.idle.enumeration.resource.ResourceType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface RewardController {

    ResourceType[] value();
}

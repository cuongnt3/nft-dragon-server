package com.zitga.player.annotation;

import com.zitga.enumeration.player.PlayerDataType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface PlayerDataSerializer {

    PlayerDataType[] value();
}

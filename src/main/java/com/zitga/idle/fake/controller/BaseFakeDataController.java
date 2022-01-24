package com.zitga.idle.fake.controller;

import com.zitga.idle.enumeration.fake.FakePlayerDataType;
import com.zitga.idle.player.model.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseFakeDataController {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    public abstract int fakeData(Player player, FakePlayerDataType fakePlayerDataType, String fakeData);
}
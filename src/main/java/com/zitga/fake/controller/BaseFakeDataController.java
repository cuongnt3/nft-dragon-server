package com.zitga.fake.controller;

import com.zitga.enumeration.fake.FakePlayerDataType;
import com.zitga.player.model.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseFakeDataController {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    public abstract int fakeData(Player player, FakePlayerDataType fakePlayerDataType, String fakeData);
}
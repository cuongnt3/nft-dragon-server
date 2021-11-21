package com.zitga.fake.handler;

import com.zitga.base.constant.LogicCode;
import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanField;
import com.zitga.config.GameConfig;
import com.zitga.core.message.http.HttpResponse;
import com.zitga.enumeration.fake.FakePlayerDataType;
import com.zitga.fake.service.FakePlayerDataService;
import com.zitga.player.model.Player;
import io.netty.buffer.ByteBuf;

@BeanComponent
public class FakePlayerDataHandler {

    @BeanField
    private GameConfig gameConfig;

    @BeanField
    private FakePlayerDataService fakeDataService;

    public HttpResponse handle(Player player, int opCode, ByteBuf in) {
        if (gameConfig.getAllowFakeData()) {
            int resultCode = fakeDataService.fakeData(player, FakePlayerDataType.ADD_DRAGON.getValue(), "");
            if (resultCode == LogicCode.SUCCESS) {
                return HttpResponse.ok();
            } else {
                return HttpResponse.error(400, resultCode);
            }
        } else {
            return HttpResponse.error(403, LogicCode.FAKE_FORBIDDEN);
        }
    }
}
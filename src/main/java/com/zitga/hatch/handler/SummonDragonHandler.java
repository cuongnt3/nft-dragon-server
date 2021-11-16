package com.zitga.hatch.handler;

import com.zitga.bean.annotation.BeanField;
import com.zitga.core.annotation.socket.SocketHandler;
import com.zitga.core.utils.socket.SocketUtils;
import com.zitga.idle.base.constant.LogicCode;
import com.zitga.idle.base.constant.OpCode;
import com.zitga.idle.base.handler.AuthorizedHandler;
import com.zitga.idle.enumeration.summon.SummonType;
import com.zitga.idle.player.model.Player;
import com.zitga.idle.summon.model.message.SummonResult;
import com.zitga.idle.summon.service.SummonService;
import io.netty.buffer.ByteBuf;

@SocketHandler(OpCode.SUMMON_HERO)
public class SummonDragonHandler extends AuthorizedHandler {

    @BeanField
    private SummonService summonService;

    @Override
    public void handle(Player player, int opCode, ByteBuf in) {
        SummonType summonType = SummonType.toSummonType(in.readUnsignedByte());
        boolean isSingleSummon = in.readBoolean();

        SummonResult result;
        switch (summonType) {
            case SUMMON_BASIC:
                result = summonService.summonByBasicScroll(player, isSingleSummon);
                break;

            case SUMMON_PREMIUM:
                boolean isUseGem = in.readBoolean();
                result = summonService.summonByPremiumScroll(player, isSingleSummon, isUseGem);
                break;

            case SUMMON_FRIEND:
                result = summonService.summonByFriendPoint(player, isSingleSummon);
                break;

            case SUMMON_POINT:
                result = summonService.summonBySummonPoint(player);
                break;

            default:
                player.send(opCode, LogicCode.INVALID_INPUT_DATA);
                return;
        }

        if (result != null) {
            ByteBuf out = SocketUtils.createByteBuf(opCode);
            result.serialize(out);

            player.send(out);
        }
    }
}
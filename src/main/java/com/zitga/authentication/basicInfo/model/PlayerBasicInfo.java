package com.zitga.authentication.basicInfo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zitga.authentication.basicInfo.constant.BasicInfoConstant;
import com.zitga.authentication.basicInfo.constant.BasicInfoTag;
import com.zitga.authentication.model.PlayerAuthentication;
import com.zitga.base.constant.BasicTag;
import com.zitga.base.constant.CollectionName;
import com.zitga.base.model.BasePlayerComponent;
import com.zitga.player.model.Player;
import com.zitga.utils.RandomUtils;
import com.zitga.utils.TimeUtils;
import org.mongodb.morphia.annotations.*;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Entity(value = CollectionName.PLAYER_BASIC_INFO, noClassnameStored = true)
@Indexes({
        @Index(fields = {@Field(BasicInfoTag.PLAYER_LOWER_NAME_TAG)}),
        @Index(fields = {@Field(BasicTag.SLICE_TAG)})
})
public class PlayerBasicInfo extends BasePlayerComponent {

    @JsonIgnore
    @Version
    @Property(BasicTag.DOCUMENT_VERSION_TAG)
    private long documentVersion;

    @Property(BasicInfoTag.PLAYER_NAME_TAG)
    private String name;

    @Property(BasicInfoTag.PLAYER_LOWER_NAME_TAG)
    private String lowerName;

    @Property(BasicInfoTag.PLAYER_AVATAR_TAG)
    private int avatar;

    @Property(BasicTag.LEVEL_TAG)
    private int level;

    @Embedded(BasicInfoTag.MONEY_TAG)
    private Map<Integer, Long> money = new ConcurrentHashMap<>();

    @Property(BasicTag.CREATED_TIME_TAG)
    private Date createdTime;

    @Property(BasicInfoTag.LAST_LOGIN_TIME_TAG)
    private Date lastLoginTime;

    @Property(BasicInfoTag.LAST_ONLINE_TIME_TAG)
    private Date lastOnlineTime;

    @Property(BasicInfoTag.LAST_LOGOUT_TIME_TAG)
    private Date lastLogoutTime;

    @Property(BasicTag.SLICE_TAG)
    private final int slice;

    public PlayerBasicInfo() {
        // For serialize purpose
        this.slice = RandomUtils.nextInt(BasicInfoConstant.NUMBER_SLICES);
        this.lastOnlineTime = TimeUtils.getCurrentTimeInGMT();
    }

    public PlayerBasicInfo(Player player) {
        super(player);

        this.name = String.format("Player_%s", player.getPlayerId());
        this.lowerName = name.toLowerCase();

        this.level = BasicInfoConstant.DEFAULT_PLAYER_LEVEL;

        this.createdTime = TimeUtils.getCurrentTimeInGMT();
        this.lastLoginTime = TimeUtils.getCurrentTimeInGMT();
        this.lastOnlineTime = TimeUtils.getCurrentTimeInGMT();

        this.slice = RandomUtils.nextInt(BasicInfoConstant.NUMBER_SLICES);
    }

    // ---------------------------------------- Getters ----------------------------------------
}
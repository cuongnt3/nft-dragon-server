package com.zitga.idle.battleInfo.model;

import com.zitga.core.utils.socket.SerializeHelper;
import com.zitga.idle.base.constant.BasicTag;
import com.zitga.idle.battleInfo.constant.BattleInfoTag;
import com.zitga.idle.battleInfo.model.formation.DetailTeamFormation;
import io.netty.buffer.ByteBuf;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Property;

@Entity(value = "", noClassnameStored = true)
public class CompactPlayerInfo {

    @Property(BasicTag.PLAYER_ID_TAG)
    protected long playerId;

    @Property(BasicTag.SERVER_TAG)
    protected int server;

    @Property(BasicTag.PLAYER_NAME_TAG)
    protected String playerName;

    @Property(BasicTag.PLAYER_AVATAR_TAG)
    protected int playerAvatar;

    @Property(BasicTag.PLAYER_LEVEL_TAG)
    protected int playerLevel;

    @Property(BasicTag.PLAYER_GUILD_NAME_TAG)
    protected String guildName;

    @Embedded(BattleInfoTag.FORMATION_TAG)
    protected DetailTeamFormation detailTeamFormation = new DetailTeamFormation();

    public CompactPlayerInfo() {
        // For serialize purpose
    }

    // ---------------------------------------- Getters ----------------------------------------
    public long getPlayerId() {
        return playerId;
    }

    public int getServer() {
        return server;
    }

    public String getName() {
        return playerName;
    }

    public int getAvatar() {
        return playerAvatar;
    }

    public int getLevel() {
        return playerLevel;
    }

    public String getGuildName() {
        return guildName;
    }

    public DetailTeamFormation getDetailTeamFormation() {
        return detailTeamFormation;
    }

    public void serializeBasicInfo(ByteBuf out) {
        out.writeLongLE(playerId);
        SerializeHelper.writeString(out, playerName);
        out.writeIntLE(playerAvatar);
        out.writeShortLE(playerLevel);
    }

    // ---------------------------------------- Setters ----------------------------------------
    public void setGuildName(String guildName) {
        this.guildName = guildName;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    public void setServer(int server) {
        this.server = server;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setPlayerAvatar(int playerAvatar) {
        this.playerAvatar = playerAvatar;
    }

    public void setPlayerLevel(int playerLevel) {
        this.playerLevel = playerLevel;
    }

    public void setDetailTeamFormation(DetailTeamFormation detailTeamFormation) {
        this.detailTeamFormation = detailTeamFormation;
    }

    public void deserializeBasicInfo(ByteBuf in) {
        playerId = in.readLongLE();
        playerName = SerializeHelper.readString(in);
        playerAvatar = in.readIntLE();
        playerLevel = in.readShortLE();
    }
}
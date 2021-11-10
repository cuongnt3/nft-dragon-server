package com.nft.authentication.model;

import com.nft.base.constant.CollectionName;
import com.nft.battle.constant.BattleConstant;
import com.zitga.core.authentication.IAuthorizedEntity;
import com.zitga.core.authentication.socket.IPeerAuthentication;
import com.zitga.core.handler.socket.support.context.HandlerContext;
import com.zitga.core.handler.socket.support.context.Peer;
import com.zitga.core.message.socket.IDeserializable;
import com.zitga.core.message.socket.ISerializable;
import com.zitga.core.utils.socket.SerializeHelper;
import com.nft.authentication.constant.AdminAuthTag;
import com.nft.authentication.model.endPoint.AdminEndpoint;
import com.nft.authentication.utils.AuthUtils;
import io.netty.buffer.ByteBuf;
import org.mongodb.morphia.annotations.*;

@Entity(value = CollectionName.ADMIN_AUTHENTICATION, noClassnameStored = true)
@Indexes({
        @Index(fields = {@Field(AdminAuthTag.USER_NAME_TAG)}, options = @IndexOptions(unique = true)),
})
public class AdminAuthentication implements IPeerAuthentication, ISerializable, IDeserializable, IAuthorizedEntity {

    @Id
    private long id;

    @Transient
    @NotSaved
    private AdminEndpoint adminEndpoint;

    @Property(AdminAuthTag.USER_NAME_TAG)
    private String userName;

    @Property(AdminAuthTag.PASSWORD_TAG)
    // password is hashed and salted
    private String password;

    @Property(AdminAuthTag.ROLE_TAG)
    private int role;

    @Transient
    @NotSaved
    private Peer peer;

    @Transient
    @NotSaved
    private boolean isAuth;

    @Transient
    @NotSaved
    private int battleId = BattleConstant.NO_BATTLE;

    public AdminAuthentication() {
        // for serialize purpose
    }

    public AdminAuthentication(String userName, String password) {
        this.userName = userName;
        this.password = AuthUtils.calculateSaltedPassword(password);
    }

    // ---------------------------------------- Getters ----------------------------------------
    public long getId() {
        return id;
    }

    public AdminEndpoint getEndpoint() {
        return adminEndpoint;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public boolean comparePassword(String hashedPassword) {
        String saltedPassword = AuthUtils.calculateSaltedPassword(hashedPassword);
        return password.equals(saltedPassword);
    }

    public int getRole() {
        return role;
    }

    public void send(int opCode, int returnCode) {
        peer.send(opCode, returnCode);
    }

    public void send(ByteBuf out) {
        peer.send(out);
    }

    public boolean isInBattle(int battleId) {
        if (battleId == BattleConstant.NO_BATTLE) {
            return false;
        }

        return this.battleId == battleId;
    }

    public int getBattleId() {
        return battleId;
    }

    @Override
    public Peer getPeer() {
        return peer;
    }

    @Override
    public boolean isPeerAuthorized() {
        return isAuth;
    }

    @Override
    public void serialize(ByteBuf out) {
        out.writeLongLE(id);
        SerializeHelper.writeString(out, userName);
        out.writeByte(role);
    }

    // ---------------------------------------- Setters ----------------------------------------
    public void setAdminEndpoint(HandlerContext context) {
        this.adminEndpoint = new AdminEndpoint(this, context);
    }

    public void setPassword(String password) {
        this.password = AuthUtils.calculateSaltedPassword(password);
    }

    /**
     * only a superUser can set other non super user's role.
     */
    public void setRole(int role) {
        this.role = role;
    }

    public void setBattleId(int battleId) {
        this.battleId = battleId;
    }

    @Override
    public void setPeer(Peer peer) {
        this.peer = peer;
    }

    @Override
    public void setPeerAuthorized(boolean isAuth) {
        this.isAuth = isAuth;
    }

    @Override
    public void deserialize(ByteBuf in) {
        id = in.readLongLE();
        userName = SerializeHelper.readString(in);
        role = in.readUnsignedByte();
    }

    @Override
    public String getAuthToken(int authProvider) {
        return userName;
    }

    @Override
    public void setAuthToken(int authProvider, String authToken) {
    }
}
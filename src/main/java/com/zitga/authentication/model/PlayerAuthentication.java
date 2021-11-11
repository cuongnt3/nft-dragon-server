package com.zitga.authentication.model;

import com.zitga.authentication.constant.AuthTag;
import com.zitga.authentication.model.endPoint.PlayerEndpoint;
import com.zitga.authentication.utils.AuthUtils;
import com.zitga.base.constant.CollectionName;
import com.zitga.core.authentication.IAuthorizedEntity;
import com.zitga.core.handler.socket.support.context.HandlerContext;
import org.mongodb.morphia.annotations.*;

@Entity(value = CollectionName.PLAYER_AUTHENTICATION, noClassnameStored = true)
@Indexes({
        @Index(fields = {@Field(AuthTag.USER_NAME_TAG)}, options = @IndexOptions(unique = true)),
})
public class PlayerAuthentication implements IAuthorizedEntity {

    @Id
    private long id;

    @Transient
    @NotSaved
    private PlayerEndpoint playerEndpoint;

    @Property(AuthTag.USER_NAME_TAG)
    private String userName;

    @Property(AuthTag.PASSWORD_TAG)
    // password is hashed and salted
    private String password;

    @Transient
    @NotSaved
    private String cachedHashPassword;

    public PlayerAuthentication() {
        // for serialize purpose
    }

    public PlayerAuthentication(String userName, String password, long playerId) {
        this.id = playerId;
        this.userName = userName;
        this.password = AuthUtils.calculateSaltedPassword(password);
    }

    // ---------------------------------------- Getters ----------------------------------------
    public long getId() {
        return id;
    }

    public PlayerEndpoint getEndpoint() {
        return playerEndpoint;
    }

    public String getUserName() {
        return userName;
    }

    public String getCachedHashPassword() {
        return cachedHashPassword;
    }

    public boolean checkPassword() {
        String saltedPassword = AuthUtils.calculateSaltedPassword(cachedHashPassword);
        return password.equals(saltedPassword);
    }

    public void setCachedHashPassword(String cachedHashPassword) {
        this.cachedHashPassword = cachedHashPassword;
    }

    // ---------------------------------------- Setters ----------------------------------------
    public void setAdminEndpoint(HandlerContext context) {
        this.playerEndpoint = new PlayerEndpoint(this, context);
    }

    public void setPassword(String password) {
        this.password = AuthUtils.calculateSaltedPassword(password);
    }

    @Override
    public String getAuthToken(int authProvider) {
        return userName;
    }

    @Override
    public void setAuthToken(int authProvider, String authToken) {
    }
}
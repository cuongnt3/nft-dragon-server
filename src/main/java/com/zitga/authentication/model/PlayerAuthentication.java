package com.zitga.authentication.model;

import com.zitga.authentication.constant.AuthConstant;
import com.zitga.authentication.constant.AuthTag;
import com.zitga.authentication.utils.AuthUtils;
import com.zitga.base.constant.CollectionName;
import com.zitga.core.authentication.IAuthorizedEntity;
import com.zitga.utils.StringUtils;
import com.zitga.utils.TimeUtils;
import org.mongodb.morphia.annotations.*;

import java.util.Date;

@Entity(value = CollectionName.PLAYER_AUTHENTICATION, noClassnameStored = true)
@Indexes({
        @Index(fields = {@Field(AuthTag.USER_NAME_TAG)}, options = @IndexOptions(unique = true)),
})
public class PlayerAuthentication implements IAuthorizedEntity {

    @Id
    private long id;

    @Property(AuthTag.USER_NAME_TAG)
    private String userName;

    @Property(AuthTag.PASSWORD_TAG)
    // password is hashed and salted
    private String password;

    @Property(AuthTag.LAST_DEVICE_PLAYED_TAG)
    private String lastDevicePlayed;

    @Transient
    @NotSaved
    private String cachedHashPassword;

    @Transient
    @NotSaved
    private String cachedDeviceId;

    @Transient
    @NotSaved
    private Date lastOnlineTime = TimeUtils.addDays(TimeUtils.getCurrentTimeInGMT(), -1);

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

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getCachedHashPassword() {
        return cachedHashPassword;
    }

    public String getCachedDeviceId() {
        return cachedDeviceId;
    }

    public boolean checkPassword() {
        String saltedPassword = AuthUtils.calculateSaltedPassword(cachedHashPassword);
        return password.equals(saltedPassword);
    }

    public boolean checkValidDevice() {
        if (StringUtils.isNullOrEmpty(lastDevicePlayed)) {
            return true;
        }

        if (lastDevicePlayed.equals(cachedDeviceId)) {
            return true;
        }

        Date expiredTime = TimeUtils.addSeconds(lastOnlineTime, AuthConstant.CHANGE_DEVICE_INTERVAL);
        return TimeUtils.getCurrentTimeInGMT().after(expiredTime);
    }

    public boolean isHaveUserName() {
        return !StringUtils.isNullOrEmpty(userName) && !StringUtils.isNullOrEmpty(password);
    }

    // ---------------------------------------- Setters ----------------------------------------
    public void setPassword(String password) {
        this.password = AuthUtils.calculateSaltedPassword(password);
    }

    public void setCachedHashPassword(String cachedHashPassword) {
        this.cachedHashPassword = cachedHashPassword;
    }

    public void setCachedDeviceId(String cachedDeviceId) {
        this.cachedDeviceId = cachedDeviceId;
    }

    public void updateLastDevicePlayed() {
        this.lastDevicePlayed = cachedDeviceId;
        lastOnlineTime = TimeUtils.getCurrentTimeInGMT();
    }

    @Override
    public String getAuthToken(int authProvider) {
        return userName;
    }

    @Override
    public void setAuthToken(int authProvider, String authToken) {
    }
}
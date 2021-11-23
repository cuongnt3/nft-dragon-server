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
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
    private Date lastOnlineTime = TimeUtils.addDays(TimeUtils.getCurrentTimeInGMT(), -1);

    @Transient
    @NotSaved
    private Date recentLoginTime;

    @Transient
    @NotSaved
    private Map<Integer, String> authorizedDataMap = new ConcurrentHashMap<>();

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

    public Date getRecentLoginTime() {
        return recentLoginTime;
    }

    public boolean checkPassword() {
        String saltedPassword = AuthUtils.calculateSaltedPassword(getAuthToken(AuthConstant.HASH_PASSWORD));
        return password.equals(saltedPassword);
    }

    public boolean checkValidDevice() {
        if (StringUtils.isNullOrEmpty(lastDevicePlayed)) {
            return true;
        }

        String cacheDeviceId = getAuthToken(AuthConstant.DEVICE_ID);
        if (StringUtils.isNullOrEmpty(cacheDeviceId)){
            return false;
        }

        if (lastDevicePlayed.equals(cacheDeviceId)) {
            return true;
        }

        Date expiredTime = TimeUtils.addSeconds(lastOnlineTime, AuthConstant.CHANGE_DEVICE_INTERVAL);
        return TimeUtils.getCurrentTimeInGMT().after(expiredTime);
    }

    public Date getLastOnlineTime() {
        return lastOnlineTime;
    }

    public boolean isHaveUserName() {
        return !StringUtils.isNullOrEmpty(userName) && !StringUtils.isNullOrEmpty(password);
    }

    // ---------------------------------------- Setters ----------------------------------------
    public void setPassword(String password) {
        this.password = AuthUtils.calculateSaltedPassword(password);
    }

    public void updateLastDevicePlayed() {
        this.lastDevicePlayed = getAuthToken(AuthConstant.DEVICE_ID);
        lastOnlineTime = TimeUtils.getCurrentTimeInGMT();
        recentLoginTime = TimeUtils.getCurrentTimeInGMT();
    }

    @Override
    public String getAuthToken(int key) {
        return authorizedDataMap.get(key);
    }

    @Override
    public void setAuthToken(int key, String data) {
        authorizedDataMap.put(key, data);
    }
}
package com.zitga.hatch.model.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zitga.player.model.Player;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.NotSaved;
import org.mongodb.morphia.annotations.Transient;

public abstract class BasePlayerComponent {

    @JsonIgnore
    @Id
    private long playerId;

    @JsonIgnore
    @Transient
    @NotSaved
    private Player player;

    public BasePlayerComponent() {
        // For serialize purpose
    }

    public BasePlayerComponent(Player player) {
        this.playerId = player.getPlayerId();
        this.player = player;
    }

    @JsonIgnore
    public void bindingWithPlayer(Player player) {
        this.player = player;
    }

    @JsonIgnore
    public long getPlayerId() {
        return playerId;
    }

    @JsonIgnore
    public Player getPlayer() {
        return player;
    }
}
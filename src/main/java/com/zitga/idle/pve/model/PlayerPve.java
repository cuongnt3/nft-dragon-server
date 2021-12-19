package com.zitga.idle.pve.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zitga.idle.base.constant.BasicTag;
import com.zitga.idle.base.constant.CollectionName;
import com.zitga.idle.base.model.BasePlayerComponent;
import com.zitga.idle.player.model.Player;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Version;

@Entity(CollectionName.PLAYER_PVE_COLLECTION)
public class PlayerPve extends BasePlayerComponent {

    @JsonIgnore
    @Version
    @Property(BasicTag.DOCUMENT_VERSION_TAG)
    private long documentVersion;

    public PlayerPve() {
        // For serialize purpose
    }

    public PlayerPve(Player player) {
        super(player);
    }
}

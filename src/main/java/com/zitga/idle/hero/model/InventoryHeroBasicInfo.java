package com.zitga.idle.hero.model;

import com.zitga.core.message.socket.IDeserializable;
import com.zitga.core.message.socket.ISerializable;
import com.zitga.idle.base.constant.BasicTag;
import com.zitga.idle.hero.constant.HeroTag;
import io.netty.buffer.ByteBuf;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Property;

@Entity(value = "", noClassnameStored = true)
public class InventoryHeroBasicInfo implements ISerializable, IDeserializable {

    @Property(HeroTag.HERO_ID_TAG)
    private int heroId;

    @Property(BasicTag.LEVEL_TAG)
    private int heroLevel;

    @Property(BasicTag.STAR_TAG)
    private int heroStar;

    public InventoryHeroBasicInfo() {
    }

    public InventoryHeroBasicInfo(InventoryHero inventoryHero) {
        this.heroId = inventoryHero.getHeroId();
        this.heroLevel = inventoryHero.getLevel();
        this.heroStar = inventoryHero.getStar();
    }

    // ---------------------------------------- Getters ----------------------------------------
    @Override
    public void serialize(ByteBuf out) {
        out.writeIntLE(heroId);
        out.writeByte(heroStar);
        out.writeShortLE(heroLevel);
    }

    // ---------------------------------------- Setters ----------------------------------------
    @Override
    public void deserialize(ByteBuf in) {
        heroId = in.readIntLE();
        heroStar = in.readUnsignedByte();
        heroLevel = in.readShortLE();
    }
}

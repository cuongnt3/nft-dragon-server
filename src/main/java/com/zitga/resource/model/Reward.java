package com.zitga.resource.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.zitga.ServiceController;
import com.zitga.enumeration.resource.ResourceType;
import com.zitga.resource.constant.ResourceTag;
import com.zitga.utils.StringUtils;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Property;

import java.util.Map;

@Entity(value = "", noClassnameStored = true)
public class Reward {

    @JsonProperty("0")
    @Property(ResourceTag.RESOURCE_TYPE_TAG)
    private int type;

    @JsonProperty("1")
    @Property(ResourceTag.RESOURCE_ID_TAG)
    private int id;

    @JsonProperty("2")
    @Property(ResourceTag.RESOURCE_NUMBER_TAG)
    private int number;

    @JsonProperty("3")
    @Property(ResourceTag.RESOURCE_DATA_TAG)
    private int data;

    public Reward() {
        // For serialize purpose
    }

    public Reward(Map<String, String> csv) {
        this.type = Integer.parseInt(csv.get(ResourceTag.RESOURCE_TYPE_TAG));
        this.id = Integer.parseInt(csv.get(ResourceTag.RESOURCE_ID_TAG));
        this.number = Integer.parseInt(csv.get(ResourceTag.RESOURCE_NUMBER_TAG));

        String heroStarString = csv.get(ResourceTag.RESOURCE_DATA_TAG);
        if (!StringUtils.isNullOrEmpty(heroStarString)) {
            this.data = Integer.parseInt(heroStarString);
        }

        ServiceController.instance().getRewardValidatorService().addRewardToValidate(this);
    }

    public Reward(ResourceType type, int resourceId) {
        this.type = type.getValue();
        this.id = resourceId;
        this.number = 1;
    }

    public Reward(ResourceType type, int resourceId, int number) {
        this.type = type.getValue();
        this.id = resourceId;
        this.number = number;
    }

    public Reward(ResourceType type, int resourceId, int number, int data) {
        this.type = type.getValue();
        this.id = resourceId;
        this.number = number;
        this.data = data;
    }

    // ---------------------------------------- Getters ----------------------------------------
    @JsonIgnore
    public ResourceType getType() {
        return ResourceType.toResourceType(type);
    }

    @JsonIgnore
    public int getId() {
        return id;
    }

    @JsonIgnore
    public int getNumber() {
        return number;
    }

    @JsonIgnore
    public int getData() {
        return data;
    }

    @JsonIgnore
    public boolean isValid() {
        if (type < 0) {
            return false;
        }

        if (number <= 0) {
            return false;
        }

        if (data < 0) {
            return false;
        }

        return true;
    }

    @JsonIgnore
    @Override
    public Reward clone() {
        return new Reward(ResourceType.toResourceType(type), id, number, data);
    }

    @JsonIgnore
    @Override
    public String toString() {
        return String.format("Reward{type = %s, id = %s, number = %s, data = %s}",
                type, id, number, data);
    }

    // ---------------------------------------- Setters ----------------------------------------
    public void addNumber(int valueAdd) {
        if (valueAdd > 0) {
            number += valueAdd;
        }
    }
}

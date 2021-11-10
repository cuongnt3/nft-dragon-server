package com.zitga.authentication.model.data;

import com.zitga.base.constant.CollectionName;
import com.zitga.authentication.constant.AdminAuthTag;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

@Entity(value = CollectionName.ADMIN_OPCODE_ACTION, noClassnameStored = true)
public class AdminOpCodeAction {

    @Id
    private int opcode;

    @Property(AdminAuthTag.ACTION_TYPE_TAG)
    private int actionType;

    public int getOpcode() {
        return opcode;
    }

    public int getActionType() {
        return actionType;
    }
}
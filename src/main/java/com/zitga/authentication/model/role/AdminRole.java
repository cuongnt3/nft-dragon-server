package com.zitga.authentication.model.role;

import com.zitga.base.constant.CollectionName;
import com.zitga.core.message.socket.IDeserializable;
import com.zitga.core.message.socket.ISerializable;
import com.zitga.authentication.constant.AdminAuthTag;
import io.netty.buffer.ByteBuf;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

import java.util.HashSet;
import java.util.Set;

@Entity(value = CollectionName.ADMIN_ROLE, noClassnameStored = true)
public class AdminRole implements ISerializable, IDeserializable {

    @Id
    @Property(AdminAuthTag.ROLE_TAG)
    private int role;

    @Embedded(AdminAuthTag.ALLOWED_ACTIONS_TAG)
    private Set<Integer> allowedActions; //enum AdminPermission

    public AdminRole() {
        allowedActions = new HashSet<>();
        // For serialize purpose
    }

    public AdminRole(int role, Set<Integer> allowedActions) {
        this.role = role;
        this.allowedActions = allowedActions;
    }

    // ---------------------------------------- Getters ----------------------------------------
    public int getRole() {
        return role;
    }

    public Set<Integer> getAllowedActions() {
        return allowedActions;
    }

    @Override
    public void serialize(ByteBuf out) {
        out.writeByte(role);
        out.writeByte(allowedActions.size());
        for (int adminAction : allowedActions) {
            out.writeShortLE(adminAction);
        }
    }

    // ---------------------------------------- Setters ----------------------------------------
    @Override
    public void deserialize(ByteBuf in) {
        role = in.readUnsignedByte();
        int size = in.readUnsignedByte();
        for (int i = 0; i < size; i++) {
            int adminAction = in.readShortLE();
            allowedActions.add(adminAction);
        }
    }
}
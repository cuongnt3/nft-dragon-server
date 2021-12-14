package com.zitga.idle.enumeration.battle;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum ActionResultType {
    ATTACK(1),
    USE_ACTIVE_DAMAGE_SKILL(2),
    COUNTER_ATTACK(3),
    SUB_ACTIVE_DAMAGE_SKILL(4),
    MAIN_SUB_ACTIVE_DAMAGE_SKILL(5),

    BONUS_ATTACK(11),

    TRIGGER_SUB_ACTIVE(21),

    CHANGE_EFFECT(91),
    CHANGE_POWER(92),

    DEAD(101),
    DEAD_FOR_DISPLAY(102),

    HEAL_EFFECT(201),
    DRYAD_EFFECT(202),

    BURNING_EFFECT(211),
    POISON_EFFECT(212),
    BLEED_EFFECT(213),
    CC_DOT_EFFECT(214),

    BURNING_MARK(214),

    VENOM_STACK(221),

    BOND_EFFECT(301),
    BOND_SHARE_DAMAGE(302),

    FREEZE_BREAK(501),
    INSTANT_KILL(502),
    RESIST_EFFECT(503),

    REFLECT_DAMAGE(601),
    SPLASH_DAMAGE(602),
    BOUNCING_DAMAGE(603),

    REBORN(701),
    REVIVE(702),
    REGENERATE(703),

    DIVINE_SHIELD(711),
    MAGIC_SHIELD(712),

    STEAL_STAT(801),
    ADD_STOLEN_STAT(802),
    DROWNING_MARK(803),
    ;

    private static final Map<Integer, ActionResultType> actionResultTypeMap = new ConcurrentHashMap<>();
    private final int value;

    ActionResultType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static ActionResultType toType(int type) {
        return actionResultTypeMap.get(type);
    }

    static {
        ActionResultType[] actionResultTypes = ActionResultType.values();
        for (ActionResultType actionResultType : actionResultTypes) {
            actionResultTypeMap.put(actionResultType.getValue(), actionResultType);
        }
    }
}

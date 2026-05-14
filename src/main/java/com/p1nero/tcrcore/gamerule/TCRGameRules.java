package com.p1nero.tcrcore.gamerule;

import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;

public class TCRGameRules {

    public static final GameRules.Key<GameRules.IntegerValue> MAX_CLOUDLAND_PLAYER_COUNT =
            GameRules.register("maxCloudlandPlayerCount", GameRules.Category.PLAYER, GameRules.IntegerValue.create(4));

    public static final GameRules.Key<GameRules.IntegerValue> MAX_INFINITE_SAMSARA_PLAYER_COUNT =
            GameRules.register("maxInfiniteSamsaraPlayerCount", GameRules.Category.PLAYER, GameRules.IntegerValue.create(1));

    public static int getMaxCloudlandPlayerCount(Level level) {
        return level.getGameRules().getInt(MAX_CLOUDLAND_PLAYER_COUNT);
    }

    public static int getMaxInfiniteSamsaraPlayerCount(Level level) {
        return level.getGameRules().getInt(MAX_INFINITE_SAMSARA_PLAYER_COUNT);
    }

    public static void register() {

    }

}

package timetrap.timetrap.config;

import org.bukkit.configuration.file.FileConfiguration;
import timetrap.timetrap.TimeTrap;



import java.util.HashMap;
import java.util.Map;


public class Config {
    private static FileConfiguration config;
    private static Map<String, Integer> time_block =new HashMap<>();

    static int IRON_BLOCK;
    static int RAW_IRON_BLOCK;
    static int GOLD_BLOCK;
    static int RAW_GOLD_BLOCK;
    static int DIAMOND_BLOCK;
    static int EMERALD_BLOCK;
    static int ANCIENT_DEBRIS;
    static int NETHERITE_BLOCK;
    static int Time;


    static {
        config = TimeTrap.getInstance().getConfig();
        getTimeBlock();
    }

    public Config() {
    }
    public static Boolean getSuspend(){
        return TimeTrap.getInstance().getConfig().getBoolean("config.suspend");
    }
    public static Boolean setSuspend(Boolean state){
        TimeTrap.getInstance().getConfig().set("config.suspend",state);
        TimeTrap.getInstance().saveConfig();
        return true;
    }
    public static Boolean getStart(){
        return TimeTrap.getInstance().getConfig().getBoolean("config.start");
    }
    public static Boolean setStart(Boolean state){
        TimeTrap.getInstance().getConfig().set("config.start",state);
        TimeTrap.getInstance().saveConfig();
        return true;
    }

    public static void reloadConfig() {
        TimeTrap.getInstance().reloadConfig();
        config = TimeTrap.getInstance().getConfig();
    }
    public static void getTimeBlock() {
        IRON_BLOCK=TimeTrap.getInstance().getConfig().getInt("config.block_time.IRON_BLOCK");
         RAW_IRON_BLOCK=TimeTrap.getInstance().getConfig().getInt("config.block_time.RAW_IRON_BLOCK");
         GOLD_BLOCK=TimeTrap.getInstance().getConfig().getInt("config.block_time.GOLD_BLOCK");
         RAW_GOLD_BLOCK=TimeTrap.getInstance().getConfig().getInt("config.block_time.RAW_GOLD_BLOCK");
         DIAMOND_BLOCK=TimeTrap.getInstance().getConfig().getInt("config.block_time.DIAMOND_BLOCK");
         EMERALD_BLOCK=TimeTrap.getInstance().getConfig().getInt("config.block_time.EMERALD_BLOCK");
         ANCIENT_DEBRIS=TimeTrap.getInstance().getConfig().getInt("config.block_time.ANCIENT_DEBRIS");
         NETHERITE_BLOCK=TimeTrap.getInstance().getConfig().getInt("config.block_time.NETHERITE_BLOCK");
    }
    public static int getTimeDeath() {
        return TimeTrap.getInstance().getConfig().getInt("config.death");
    }
    public static int getTimeKill() {
        return TimeTrap.getInstance().getConfig().getInt("config.kill");
    }
    public static int getIronBlock() {
        return IRON_BLOCK;
    }
    public static int getRawIronBlock() {
        return RAW_IRON_BLOCK;
    }
    public static int getGoldBlock() {
        return GOLD_BLOCK;
    }
    public static int getRawGoldBlock() {
        return RAW_GOLD_BLOCK;
    }
    public static int getDiamondBlock() {
        return DIAMOND_BLOCK;
    }
    public static int getEmeraldBlock() {
        return EMERALD_BLOCK;
    }
    public static int getAncientDebris() {
        return ANCIENT_DEBRIS;
    }
    public static int getNetheriteBlock() {
        return NETHERITE_BLOCK;
    }

    public static int getTime() {
        Time=TimeTrap.getInstance().getConfig().getInt("config.time")*60;
        return Time;
    }

}


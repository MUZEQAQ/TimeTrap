package timetrap.timetrap;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import timetrap.timetrap.commands.TimeTrapCon;
import timetrap.timetrap.config.Config;
import timetrap.timetrap.holder.PlayerTimeHolder;

public class Papi extends PlaceholderExpansion {

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public String getAuthor() {
        return "沐泽";
    }

    @Override
    public String getIdentifier() {
        return "TimeTrap";
    }

    @Override
    public String getVersion() {
        return "1.2";
    }


    @Override
    public String onRequest(OfflinePlayer player, String identifier) {
        if (identifier.equals("IRON_BLOCK")) {
            return "" + Config.getIronBlock();
        }
        if (identifier.equals("RAW_IRON_BLOCK")) {
            return "" + Config.getRawIronBlock();
        }
        if (identifier.equals("GOLD_BLOCK")) {
            return "" + Config.getGoldBlock();
        }
        if (identifier.equals("RAW_GOLD_BLOCK")) {
            return "" + Config.getRawGoldBlock();
        }
        if (identifier.equals("EMERALD_BLOCK")) {
            return "" + Config.getEmeraldBlock();
        }
        if (identifier.equals("DIAMOND_BLOCK")) {
            return "" + Config.getDiamondBlock();
        }
        if (identifier.equals("ANCIENT_DEBRIS")) {
            return "" + Config.getAncientDebris();
        }
        if (identifier.equals("NETHERITE_BLOCK")) {
            return "" + Config.getNetheriteBlock();
        }
        if (identifier.equals("kill")) {
            return Config.getTimeKill() + "%";
        }
        if (identifier.equals("death")) {
            return Config.getTimeDeath() + "%";
        }
        if (identifier.equals("top1")) {
            return TimeTrapCon.top1;
        }
        if (identifier.equals("top2")) {
            return TimeTrapCon.top2;
        }
        if (identifier.equals("top3")) {
            return TimeTrapCon.top3;
        }
        if (identifier.equals("top4")) {
            return TimeTrapCon.top4;
        }
        if (identifier.equals("top5")) {
            return TimeTrapCon.top5;
        }
        if (identifier.equals("top6")) {
            return TimeTrapCon.top6;
        }
        if (identifier.equals("top7")) {
            return TimeTrapCon.top7;
        }
        if (identifier.equals("top8")) {
            return TimeTrapCon.top8;
        }
        if (identifier.equals("top9")) {
            return TimeTrapCon.top9;
        }
        if (identifier.equals("top10")) {
            return TimeTrapCon.top10;
        }
        if (identifier.equals("time")) {
            int TimeTime=Config.getTime();
            int sec = TimeTime % 60;
            int minutes = TimeTime / 60;
            int min = minutes % 60;
            int hjh = minutes / 60;
            int hou = hjh % 24;
            int day = hjh / 24;
            return day + "天" + hou + "时" + min + "分" + sec + "秒(" + TimeTime + "秒)";
        }
        return null;
    }




}
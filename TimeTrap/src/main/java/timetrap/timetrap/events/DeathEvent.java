package timetrap.timetrap.events;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import timetrap.timetrap.TimeTrap;
import timetrap.timetrap.config.Config;
import timetrap.timetrap.holder.PlayerTimeHolder;



public class DeathEvent implements Listener {
    //玩家死亡
    @EventHandler
    public void Event(PlayerDeathEvent e) {
        if (TimeTrap.isEventTorF()) {
            String mes = e.getDeathMessage();
            String player2Name = e.getEntity().getName();
            String afterValue = mes.replace("" + player2Name, "");
            String player1Name = afterValue.replace(" was slain by ", "");
            int time = PlayerTimeHolder.getPlayerTime(e.getEntity().getUniqueId());
            int time2=(time * Config.getTimeDeath())/100;
            PlayerTimeHolder.modifyPlayerTime(e.getEntity().getUniqueId(), time-time2);
            int sec = time2 / 2 % 60;
            int minutes = time2 / 2 / 60;
            int min = minutes % 60;
            int hjh = minutes / 60;
            int hou = hjh % 24;
            int day = hjh / 24;
            e.getEntity().sendMessage(TimeTrap.getTimeTrapName() + e.getEntity().getName() + " 死亡损了时间" + day + "天" + hou + "时" + min + "分" + sec + "秒(" + time2 + "秒)");
            Bukkit.getOnlinePlayers().forEach(online -> {
                if (online.getName().equals(player1Name)) {
                    int time1 = PlayerTimeHolder.getPlayerTime(online.getUniqueId());
                    int time3 = (time*Config.getTimeKill())/100;
                    PlayerTimeHolder.modifyPlayerTime(online.getUniqueId(), time1 + time3);
                    int sec1 = time3 / 2 % 60;
                    int minutes1 = time3 / 2 / 60;
                    int min1 = minutes1 % 60;
                    int hjh1 = minutes1 / 60;
                    int hou1 = hjh1 % 24;
                    int day1 = hjh1 / 24;
                    online.sendMessage(TimeTrap.getTimeTrapName() + "击杀玩家 " + e.getEntity().getName() + " 获得了时间" + day1 + "天" + hou1 + "时" + min1 + "分" + sec1 + "秒(" + time3 + "秒)");
                }
            });
        }
    }
}
package timetrap.timetrap.events;

import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import timetrap.timetrap.TimeTrap;
import timetrap.timetrap.holder.PlayerTimeHolder;
import timetrap.timetrap.sql.SQLite;
import java.sql.SQLException;


public class QuitEvent implements Listener {
    @EventHandler
    public void Event(PlayerQuitEvent e) throws SQLException {
        if (TimeTrap.isEventTorF()) {
            HumanEntity player = e.getPlayer();

            // 保存时间
            int time1 = PlayerTimeHolder.getPlayerTime(player.getUniqueId());
            int i = SQLite.queryData(TimeTrap.getCon(), player.getUniqueId());
            if (i == -1) {
                SQLite.addData(TimeTrap.getCon(),player.getName(),player.getUniqueId(), time1);
            } else {
                SQLite.modifyData(TimeTrap.getCon(), player.getUniqueId(), time1);
            }
            PlayerTimeHolder.removePlayerTime(player.getUniqueId());
        }
    }
}

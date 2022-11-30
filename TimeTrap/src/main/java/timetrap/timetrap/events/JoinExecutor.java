package timetrap.timetrap.events;

import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import timetrap.timetrap.TimeTrap;
import timetrap.timetrap.config.Config;
import timetrap.timetrap.holder.PlayerTimeHolder;
import timetrap.timetrap.sql.SQLite;

import java.sql.SQLException;


public class JoinExecutor implements Listener {
    @EventHandler
    public void Event(PlayerJoinEvent e) throws SQLException {
        if (TimeTrap.isEventTorF()) {
            HumanEntity player = e.getPlayer();
            // 连接数据库
            // 查询玩家数据
            int time = SQLite.queryData(TimeTrap.getCon(), player.getUniqueId());
            // 给玩家时间
            if (time == -1) {
                PlayerTimeHolder.modifyPlayerTime(player.getUniqueId(), Config.getTime());
            } else {
                PlayerTimeHolder.modifyPlayerTime(player.getUniqueId(), time);
            }
            //断开连接
        }
    }
}

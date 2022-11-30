package timetrap.timetrap.events;

import org.bukkit.Location;
import org.bukkit.block.Block;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDropItemEvent;
import timetrap.timetrap.TimeTrap;

public class DropItemEvent implements Listener {
    @EventHandler
    public void Event(BlockDropItemEvent e) {
        if (TimeTrap.isEventTorF()) {
            Location location1 = e.getBlock().getLocation();
            int x = (int) location1.getX();
            int y = (int) location1.getY();
            int z = (int) location1.getZ();
            String str2 = "" + x +""+ y+"" + z;
            Block shower = e.getBlock();
            if (ToggleSneakEvent.getPlayerUUIDLocation().containsKey(str2)) {
                shower.setType(ToggleSneakEvent.getPlayerUUIDLocation().get(str2));
                e.setCancelled(true);
            }
        }
    }
}

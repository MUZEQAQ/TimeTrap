package timetrap.timetrap.events;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageEvent;
import timetrap.timetrap.TimeTrap;

import java.util.Map;


public class DamageEvent implements Listener {
    @EventHandler
    public void Event(BlockDamageEvent e) {

        if (TimeTrap.isEventTorF()) {
            Location location1 = e.getBlock().getLocation();
            int x = (int) location1.getX();
            int y = (int) location1.getY();
            int z = (int) location1.getZ();
            String str1 = "" + x +""+ y+"" + z;
            Map<String, Material> a =ToggleSneakEvent.getPlayerUUIDLocation();
            if (a.containsKey(str1)) {
                e.setCancelled(true);
            }
        }
    }

}

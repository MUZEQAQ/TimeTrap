package timetrap.timetrap.events;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import timetrap.timetrap.TimeTrap;
import timetrap.timetrap.config.Config;
import timetrap.timetrap.holder.PlayerTimeHolder;
import java.util.*;


public class ToggleSneakEvent implements Listener {
    static World world;
    static Location location;

    public static Map<String, Material> getPlayerUUIDLocation() {
        return playerUUIDLocation;
    }

    private static Map<String, Material> playerUUIDLocation = new HashMap<>();

    @EventHandler
    public void Event(PlayerToggleSneakEvent e) {
        if (TimeTrap.isEventTorF()) {
            //如果潜行
            Player player = e.getPlayer();
            //获得方块坐标
            location = player.getLocation();
            location.setY(location.getY() - 1);
            world = location.getWorld();
            Block blockType = location.getBlock();


            switch (String.valueOf(blockType.getType())) {
                case "RAW_IRON_BLOCK": {
                    //粗铁块
                    addTime(Config.getRawIronBlock(), e.getPlayer(), blockType, "§7粗铁块§r§b", String.valueOf(blockType.getType()));
                    break;
                }
                case "RAW_GOLD_BLOCK": {
                    //粗金块
                    addTime(Config.getRawGoldBlock(), e.getPlayer(), blockType, "§6粗金块§r§b", String.valueOf(blockType.getType()));
                    break;
                }
                case "ANCIENT_DEBRIS": {
                    //残骸
                    addTime(Config.getAncientDebris(), e.getPlayer(), blockType, "§d远古残骸§r§b", String.valueOf(blockType.getType()));
                    break;
                }
                case "IRON_BLOCK": {
                    //铁块
                    addTime(Config.getIronBlock(), e.getPlayer(), blockType, "§f铁块§r§b", String.valueOf(blockType.getType()));
                    break;
                }
                case "GOLD_BLOCK": {
                    //金块
                    addTime(Config.getGoldBlock(), e.getPlayer(), blockType, "§e金块§r§b", String.valueOf(blockType.getType()));
                    break;
                }
                case "DIAMOND_BLOCK": {
                    //钻块
                    addTime(Config.getDiamondBlock(), e.getPlayer(), blockType, "§9钻石块§r§b", String.valueOf(blockType.getType()));
                    break;
                }
                case "EMERALD_BLOCK": {
                    //绿宝石块
                    addTime(Config.getEmeraldBlock(), e.getPlayer(), blockType, "§a绿宝石块§r§b", String.valueOf(blockType.getType()));
                    break;
                }
                case "NETHERITE_BLOCK": {
                    //合金块
                    addTime(Config.getNetheriteBlock(), e.getPlayer(), blockType, "§5下界合金块§r§b", String.valueOf(blockType.getType()));
                    break;
                }
                default: {
                    break;
                }

            }
        }

    }


    public void addTime(int time1, Player player, Block blockType, String blockName, String name) {
        Block shower = blockType.getRelative(BlockFace.UP, 0);
        int x = (int) shower.getX();
        int y = (int) shower.getY();
        int z = (int) shower.getZ();
        String str = "" + x + "" + y + "" + z;

        if (!playerUUIDLocation.containsKey(str)) {
            playerUUIDLocation.put(str, Material.getMaterial(name, true));
            new BukkitRunnable() {
                int i = 11;
                public void run() {
                    if (i == 11) {
                        world.spawnParticle(Particle.PORTAL, blockType.getX() + 0.5, blockType.getY() + 0.5, blockType.getZ() + 0.5, 150, 0.5, 0.5, 0.5, 0.1);
                        world.playSound(location, Sound.BLOCK_PORTAL_TRIGGER, 10F, 5F);
                    }
                    if (i == 10) {
                        world.spawnParticle(Particle.PORTAL, blockType.getX() + 0.5, blockType.getY() + 0.5, blockType.getZ() + 0.5, 160, 0.5, 0.5, 0.5, 0.1);
                    }
                    if (i == 9) {
                        world.spawnParticle(Particle.PORTAL, blockType.getX() + 0.5, blockType.getY() + 0.5, blockType.getZ() + 0.5, 170, 0.5, 0.5, 0.5, 0.1);
                    }
                    if (i == 8) {
                        world.spawnParticle(Particle.PORTAL, blockType.getX() + 0.5, blockType.getY() + 0.5, blockType.getZ() + 0.5, 180, 0.5, 0.5, 0.5, 0.1);
                    }

                    if (i == 7) {
                        world.spawnParticle(Particle.PORTAL, blockType.getX() + 0.5, blockType.getY() + 0.5, blockType.getZ() + 0.5, 190, 0.5, 0.5, 0.5, 0.1);
                    }
                    if (i == 6) {
                        world.spawnParticle(Particle.PORTAL, blockType.getX() + 0.5, blockType.getY() + 0.5, blockType.getZ() + 0.5, 200, 0.5, 0.5, 0.5, 0.1);
                    }
                    if (i == 5) {
                        world.spawnParticle(Particle.PORTAL, blockType.getX() + 0.5, blockType.getY() + 0.5, blockType.getZ() + 0.5, 220, 0.5, 0.5, 0.5, 0.1);
                    }
                    if (i == 4) {
                        world.spawnParticle(Particle.PORTAL, blockType.getX() + 0.5, blockType.getY() + 0.5, blockType.getZ() + 0.5, 240, 0.5, 0.5, 0.5, 0.1);
                    }
                    if (i == 3) {
                        world.playSound(location, Sound.ENTITY_ZOMBIE_VILLAGER_CONVERTED, 3F, 3F);
                    }
                    if (i == 3) {
                        world.spawnParticle(Particle.PORTAL, blockType.getX() + 0.5, blockType.getY() + 0.5, blockType.getZ() + 0.5, 260, 0.5, 0.5, 0.5, 0.1);
                    }
                    if (i == 2) {
                        world.spawnParticle(Particle.PORTAL, blockType.getX() + 0.5, blockType.getY() + 0.5, blockType.getZ() + 0.5, 280, 0.5, 0.5, 0.5, 0.1);
                    }
                    if (i == 1) {
                        world.spawnParticle(Particle.PORTAL, blockType.getX() + 0.5, blockType.getY() + 0.5, blockType.getZ() + 0.5, 300, 0.5, 0.5, 0.5, 0.1);
                        world.spawnParticle(Particle.SPELL_WITCH, blockType.getX() + 0.5, blockType.getY() + 0.5, blockType.getZ() + 0.5, 1000, 0, 0, 0, 0.01);
                    }
                    if (i < 0) {
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                playerUUIDLocation.remove(str);
                                world.spawnParticle(Particle.SPELL_WITCH, blockType.getX() + 0.5, blockType.getY() + 0.5, blockType.getZ() + 0.5, 1000, 0, 0, 0, 0.01);
                                shower.setType(Material.AIR);
                                // 给玩家抗性提升5 2s
                                player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 40, 5, false, false, true));
                                // 将闪电传送到玩家
                                world.spawnEntity(shower.getLocation(), EntityType.LIGHTNING);
                                // 闪电声音
                                world.playSound(location, Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 4F, 4F);
                                // 不死图腾声音
                                world.playSound(location, Sound.ITEM_TOTEM_USE, 4F, 4F);
                                int time = PlayerTimeHolder.getPlayerTime(player.getUniqueId());
                                PlayerTimeHolder.modifyPlayerTime(player.getUniqueId(), time + time1);
                                int sec = time1 % 60;
                                int minutes = time1 / 60;
                                int min = minutes % 60;
                                int hjh = minutes / 60;
                                int hou = hjh % 24;
                                int day = hjh / 24;
                                player.sendMessage(TimeTrap.getTimeTrapName() + blockName + " 提供了时间" + day + "天" + hou + "时" + min + "分" + sec + "秒(" + time1 + "秒)");
                                // 升级声音
                                world.playSound(location, Sound.ENTITY_PLAYER_LEVELUP, 4F, 4F);
                                this.cancel();
                            }
                        }.runTaskLater(TimeTrap.getInstance(),0);
                        this.cancel();
                    }
                    i--;
                }
            }.runTaskTimerAsynchronously(TimeTrap.getInstance(), 0, 4);
        }
    }

}

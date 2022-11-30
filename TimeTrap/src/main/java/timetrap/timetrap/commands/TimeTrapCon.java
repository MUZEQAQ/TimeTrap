package timetrap.timetrap.commands;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import timetrap.timetrap.TimeTrap;
import timetrap.timetrap.config.Config;
import timetrap.timetrap.holder.PlayerTimeHolder;
import timetrap.timetrap.sql.SQLite;

public class TimeTrapCon implements CommandExecutor {

    public static String top1 = "暂无玩家";
    public static String top2 = "暂无玩家";
    public static String top3 = "暂无玩家";
    public static String top4 = "暂无玩家";
    public static String top5 = "暂无玩家";
    public static String top6 = "暂无玩家";
    public static String top7 = "暂无玩家";
    public static String top8 = "暂无玩家";
    public static String top9 = "暂无玩家";
    public static String top10 = "暂无玩家";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            return false;
        } else if (args.length == 1) {
            switch (args[0]) {
                case "开始": {
                    if (sender.hasPermission("timetrap.op")) {
                        if (Config.getSuspend()) {
                            sender.sendMessage(ChatColor.AQUA + "[TimeTrap] " + "已经有一个游戏记录 是否确定重开 请输入 /tt 清除记录");
                            break;
                        }
                        if (Config.getStart()) {
                            sender.sendMessage(ChatColor.AQUA + "[TimeTrap] " + "游戏已经开始了");
                        } else {
                            //重新给全部玩家时间和模式
                            Bukkit.getOnlinePlayers().forEach(online -> {
                                online.setGameMode(GameMode.SURVIVAL);
                                online.sendTitle(ChatColor.GREEN + "游戏开始", "", 20, 40, 20);
                                PlayerTimeHolder.modifyPlayerTime(online.getUniqueId(), Config.getTime());
                            });

                            new BukkitRunnable() {
                                public void run() {
                                    StarEvent();
                                }
                            }.runTaskLater(TimeTrap.getInstance(), 20);
                        }
                        break;

                    }
                }
                case "结束": {
                    if (sender.hasPermission("timetrap.op")) {
                        if (!Config.getStart()) {
                            sender.sendMessage(ChatColor.AQUA + "[TimeTrap] " + "已经结束了");
                            break;
                        }
                        top1 = "暂无玩家";
                        top2 = "暂无玩家";
                        top3 = "暂无玩家";
                        top4 = "暂无玩家";
                        top5 = "暂无玩家";
                        top6 = "暂无玩家";
                        top7 = "暂无玩家";
                        top8 = "暂无玩家";
                        top9 = "暂无玩家";
                        top10 = "暂无玩家";
                        new BukkitRunnable() {
                            public void run() {
                                try {
                                    SQLite.modifyALLData(TimeTrap.getCon());
                                    PlayerTimeHolder.getPlayerTimeMap().clear();
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            }
                        }.runTaskLater(TimeTrap.getInstance(), 20);
                        Config.setSuspend(false);
                        Config.setStart(false);
                        Bukkit.getOnlinePlayers().forEach(online -> {
                            online.sendTitle(ChatColor.RED + "游戏结束", "", 20, 40, 20);
                        });
                        break;
                    }
                }
                case "继续": {
                    if (sender.hasPermission("timetrap.op")) {
                        if (Config.getStart()) {
                            sender.sendMessage(ChatColor.AQUA + "[TimeTrap] " + "已经开始了");
                            break;
                        } else {
                            try {
                                SQLite.queryAllData(TimeTrap.getCon());
                                // 读取时间
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                        Bukkit.getOnlinePlayers().forEach(online -> {
                            online.setGameMode(GameMode.SURVIVAL);
                            online.sendTitle(ChatColor.GREEN + "游戏继续", "", 20, 40, 20);
                            PlayerTimeHolder.modifyPlayerTime(online.getUniqueId(), Config.getTime());
                        });
                        //开始
                        new BukkitRunnable() {
                            public void run() {
                                StarEvent();
                            }
                        }.runTaskLater(TimeTrap.getInstance(), 20);
                        break;
                    }
                }
                case "暂停": {
                    if (sender.hasPermission("timetrap.op")) {
                        if (!Config.getStart()) {
                            sender.sendMessage(ChatColor.AQUA + "[TimeTrap] " + "已经结束了");
                            break;
                        }
                        Config.setSuspend(true);
                        Config.setStart(false);
                        new BukkitRunnable() {
                            public void run() {
                                Bukkit.getOnlinePlayers().forEach(online -> {
                                    online.sendTitle(ChatColor.RED + "游戏暂停", "", 20, 40, 20);
                                    int time = PlayerTimeHolder.getPlayerTime(online.getUniqueId());
                                    try {
                                        SQLite.modifyData(TimeTrap.getCon(), online.getUniqueId(), time);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                });
                            }
                        }.runTaskLater(TimeTrap.getInstance(), 20);
                        try {
                            String sql = "SELECT * FROM DATA ORDER BY S DESC";
                            Statement stat = TimeTrap.getCon().createStatement();
                            ResultSet rs = stat.executeQuery(sql);
                            int a = 0;
                            while (rs.next()) {
                                String id = rs.getString("UUID");
                                String name = rs.getString("Name");
                                int i1 = rs.getInt("S");
                                int sec1 = i1 % 60;
                                int minutes1 = i1 / 60;
                                int min1 = minutes1 % 60;
                                int hjh1 = minutes1 / 60;
                                int hou1 = hjh1 % 24;
                                int day1 = hjh1 / 24;
                                String Name = name + ": " + day1 + "天" + hou1 + "时" + min1 + "分" + sec1 + "秒(" + i1 + ")";
                                switch (a) {
                                    case 0: {
                                        top1 = Name;
                                        break;
                                    }
                                    case 1: {
                                        top2 = Name;
                                        break;
                                    }
                                    case 2: {
                                        top3 = Name;
                                        break;
                                    }
                                    case 3: {
                                        top4 = Name;
                                        break;
                                    }
                                    case 4: {
                                        top5 = Name;
                                        break;
                                    }
                                    case 5: {
                                        top6 = Name;
                                        break;
                                    }
                                    case 6: {
                                        top7 = Name;
                                        break;
                                    }
                                    case 7: {
                                        top8 = Name;
                                        break;
                                    }
                                    case 8: {
                                        top9 = Name;
                                    }
                                    case 9: {
                                        top10 = Name;
                                    }
                                }
                                a++;
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }
                case "帮助": {
                    sender.sendMessage(
                            ChatColor.AQUA + " [TimeTrap] \n" +
                                    "/timeTrap 帮助 插件命令\n"
                    );
                    if (sender.hasPermission("timetrap.op")) {
                        sender.sendMessage(
                                ChatColor.AQUA +
                                        "/timeTrap 开始 开始计时\n" +
                                        "/timeTrap 继续 继续计时\n" +
                                        "/timeTrap 暂停 暂停计时\n" +
                                        "/timeTrap 结束 结束并清空数据\n" +
                                        "/timeTrap 设置 () [] 设置玩家时间 ()为玩家名称 []为时间\n" +
                                        "/timeTrap 给予 () [] 给予玩家时间 ()为玩家名称 []为时间\n");
                    }
                    if (sender.hasPermission("timetrap.pay")) {
                        sender.sendMessage(
                                ChatColor.AQUA + "/timeTrap 转账 () [] 转账给玩家时间 ()为玩家名称 []为时间\n");
                    }
                    if (sender.hasPermission("timetrap.list")) {
                        sender.sendMessage(
                                ChatColor.AQUA + "/timeTrap 排行 时间排行 1分钟刷新一次\n");
                    }
                    break;
                }
                case "排行": {
                    if (sender.hasPermission("timetrap.list")) {
                        new BukkitRunnable() {
                            public void run() {
                                try {

                                    String sql = "SELECT * FROM DATA ORDER BY DATA.S DESC LIMIT 10";
                                    Statement stat = TimeTrap.getCon().createStatement();
                                    ResultSet rs = stat.executeQuery(sql);
                                    sender.sendMessage("§b========= §3[Time Trap Top]§r§b =========\n");
                                    int i = 0;
                                    while (rs.next()) {
                                        i++;
                                        String name = rs.getString("Name");
                                        int time = rs.getInt("S");
                                        int sec = time % 60;
                                        int minutes = time / 60;
                                        int min = minutes % 60;
                                        int hjh = minutes / 60;
                                        int hou = hjh % 24;
                                        int day = hjh / 24;
                                        sender.sendMessage("§3" + i + " §2" + name + " §3" + day + "天" + hou + "时" + min + "分" + sec + "秒(" + time + "秒)");
                                    }
                                    sender.sendMessage("§b======== §3每5分钟刷新一次§r§b =======\n");
                                    this.cancel();
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                    this.cancel();
                                }
                            }
                        }.runTaskTimerAsynchronously(TimeTrap.getInstance(), 0, 20);
                        break;
                    }
                }
                case "重载": {
                    if (sender.hasPermission("timetrap.op")) {
                        try {
                            Config.reloadConfig();
                            if (!(sender instanceof Player)) {
                                Bukkit.getConsoleSender().sendMessage(TimeTrap.getTimeTrapName() + ChatColor.GREEN + "配置文件重载成功");
                            } else {
                                sender.sendMessage(TimeTrap.getTimeTrapName() + ChatColor.GREEN + "配置文件重载成功");
                            }
                            return false;
                        } catch (Exception var9) {
                            if (!(sender instanceof Player)) {
                                Bukkit.getConsoleSender().sendMessage(TimeTrap.getTimeTrapName() + ChatColor.RED + "配置文件重载失败");
                            } else {
                                sender.sendMessage(TimeTrap.getTimeTrapName() + ChatColor.AQUA + ChatColor.GREEN + "配置文件重载失败");
                            }
                            return false;
                        }
                    }

                }
                case "清除记录": {
                    if (sender.hasPermission("timetrap.op")) {
                        if (!Config.getStart()) {
                            Config.setStart(false);
                            Config.setSuspend(false);
                            sender.sendMessage(ChatColor.AQUA + "[TimeTrap] " + ChatColor.GREEN + "清除记录成功");
                        } else {
                            sender.sendMessage(ChatColor.AQUA + "[TimeTrap] " + "§c要想清除记录 请先将游戏暂停");
                        }
                        break;
                    }
                    break;
                }
                default: {
                    sender.sendMessage(ChatColor.AQUA + "[TimeTrap] " + "§c您输入的指令不存在");
                    return false;
                }
            }


        } else if (args.length == 3) {
            switch (args[0]) {
                case "给予": {
                    if (sender.hasPermission("timetrap.op")) {
                        if (!(sender instanceof Player)) {
                            return false;
                        }
                        Player player = (Player) sender;
                        // 获取玩家时间
                        int time = PlayerTimeHolder.getPlayerTime(player.getUniqueId());
                        //判断是否为空
                        if (time == -1) {
                            PlayerTimeHolder.addPlayerTime(player.getUniqueId(), Config.getTime());
                        }
                        //获取最新玩家时间
                        int time1 = PlayerTimeHolder.getPlayerTime(player.getUniqueId());
                        try {
                            String playerName = args[1];
                            for (Player o : Bukkit.getOnlinePlayers()) {
                                if (playerName.equals(o.getName())) {
                                    try {
                                        int time2 = Integer.parseInt(args[2]);
                                        PlayerTimeHolder.modifyPlayerTime(o.getUniqueId(), time1 + time2);
                                        int sec = time2 % 60;
                                        int minutes = time2 / 60;
                                        int min = minutes % 60;
                                        int hjh = minutes / 60;
                                        int hou = hjh % 24;
                                        int day = hjh / 24;
                                        o.sendMessage(TimeTrap.getTimeTrapName() + player.getName() + "§r§b" + " 给予了时间" + day + "天" + hou + "时" + min + "分" + sec + "秒(" + time2 + "秒)");
                                    } catch (Exception e) {
                                        sender.sendMessage(ChatColor.AQUA + "[TimeTrap] " + "§c请填正负整数");
                                        return false;
                                    }
                                }
                            }
                        } catch (Exception e) {
                            sender.sendMessage(ChatColor.AQUA + "[TimeTrap] " + "§c使用的命令不正确");
                            return false;
                        }
                    } else {
                        sender.sendMessage(ChatColor.AQUA + "[TimeTrap] " + "§c您没有该指令权限");
                        return false;
                    }
                    break;
                }
                case "设置": {
                    if (sender.hasPermission("timetrap.op")) {
                        if (!(sender instanceof Player)) {
                            return false;
                        }
                        Player player = (Player) sender;
                        // 获取玩家时间
                        int time = PlayerTimeHolder.getPlayerTime(player.getUniqueId());
                        //判断是否为空
                        if (time == -1) {
                            PlayerTimeHolder.addPlayerTime(player.getUniqueId(), Config.getTime());
                        }
                        //获取最新玩家时间
                        //获取添加时间
                        try {
                            String playerName = args[1];
                            for (Player o : Bukkit.getOnlinePlayers()) {
                                if (playerName.equals(o.getName())) {
                                    try {
                                        int time2 = Integer.parseInt(args[2]);
                                        PlayerTimeHolder.modifyPlayerTime(o.getUniqueId(), time2);
                                        int sec = time2 % 60;
                                        int minutes = time2 / 60;
                                        int min = minutes % 60;
                                        int hjh = minutes / 60;
                                        int hou = hjh % 24;
                                        int day = hjh / 24;
                                        o.sendMessage(TimeTrap.getTimeTrapName() + player.getName() + "§r§b" + " 设置了您的时间" + day + "天" + hou + "时" + min + "分" + sec + "秒(" + time2 + "秒)");
                                    } catch (Exception e) {
                                        sender.sendMessage(ChatColor.AQUA + "[TimeTrap] " + "§c请填正负整数");
                                        return false;
                                    }
                                }
                            }
                        } catch (Exception e) {
                            sender.sendMessage(ChatColor.AQUA + "[TimeTrap] " + "§c请输入正确的玩家名称");
                            return false;
                        }
                    } else {
                        sender.sendMessage(ChatColor.AQUA + "[TimeTrap] " + "§c您没有该指令权限");
                        return false;
                    }
                    break;
                }
                case "转账": {
                    if (!(sender instanceof Player)) {
                        return false;
                    }
                    if (sender.hasPermission("timetrap.pay")) {
                        Player player = (Player) sender;
                        //判断是否为空
                        if (!PlayerTimeHolder.contains(player.getUniqueId())) {
                            PlayerTimeHolder.addPlayerTime(player.getUniqueId(), Config.getTime());
                        }
                        //获取添加时间
                        try {
                            String playerName = args[1];
                            for (Player o : Bukkit.getOnlinePlayers()) {
                                if (playerName.equals(o.getName())) {
                                    try {
                                        int time = PlayerTimeHolder.getPlayerTime(player.getUniqueId());
                                        int time2 = Integer.parseInt(args[2]);
                                        if (time < time2) {
                                            player.sendMessage(TimeTrap.getTimeTrapName() + player.getName() + " 您的时间不足");
                                        } else {
                                            PlayerTimeHolder.modifyPlayerTime(player.getUniqueId(), time - time2);
                                            int time1 = PlayerTimeHolder.getPlayerTime(o.getUniqueId());
                                            PlayerTimeHolder.modifyPlayerTime(o.getUniqueId(), time1 + time2);
                                            int sec = time2 % 60;
                                            int minutes = time2 / 60;
                                            int min = minutes % 60;
                                            int hjh = minutes / 60;
                                            int hou = hjh % 24;
                                            int day = hjh / 24;
                                            o.sendMessage(TimeTrap.getTimeTrapName() + player.getName() + "§r§b" + " 给予了您时间" + day + "天" + hou + "时" + min + "分" + sec + "秒(" + time2 + "秒)");
                                            player.sendMessage(TimeTrap.getTimeTrapName() + "您给予了 " + o.getName() + "§r§b 时间" + day + "天" + hou + "时" + min + "分" + sec + "秒(" + time2 + "秒)");
                                        }
                                        return false;
                                    } catch (Exception e) {
                                        sender.sendMessage(ChatColor.AQUA + "[TimeTrap] " + "§c使用的命令不正确");
                                        return false;
                                    }
                                }
                            }
                            sender.sendMessage(ChatColor.AQUA + "[TimeTrap] " + "§c请输入正确的玩家名称");
                        } catch (Exception e) {
                            sender.sendMessage(ChatColor.AQUA + "[TimeTrap] " + "§c使用的命令不正确");
                            return false;
                        }
                    } else {
                        sender.sendMessage(ChatColor.AQUA + "[TimeTrap] " + "§c您没有该指令权限");
                        return false;
                    }
                }
                default: {
                    sender.sendMessage(ChatColor.AQUA + "[TimeTrap] " + "§c您输入的指令不存在");
                    return false;
                }
            }
        } else {
            sender.sendMessage(ChatColor.AQUA + "[TimeTrap] " + "§c您输入的指令不存在");
        }
        return false;
    }


    public static void StarEvent() {
        Config.setStart(true);
        Config.setSuspend(false);
        TimeTrap.setEventTorF(true);
        new BukkitRunnable() {
            public void run() {
                Bukkit.getOnlinePlayers().forEach(online -> {
                    if (!PlayerTimeHolder.contains(online.getUniqueId())) {
                        PlayerTimeHolder.addPlayerTime(online.getUniqueId(), Config.getTime());
                    }
                    int i = PlayerTimeHolder.getPlayerTime(online.getUniqueId());
                    try {
                        int ai = SQLite.queryData(TimeTrap.getCon(), online.getUniqueId());
                        if (ai == -1) {
                            SQLite.addData(TimeTrap.getCon(), online.getName(), online.getUniqueId(), i);
                        } else {
                            SQLite.modifyData(TimeTrap.getCon(), online.getUniqueId(), i);
                        }
                    } catch (SQLException e) {

                        Bukkit.getConsoleSender().sendMessage(TimeTrap.getTimeTrapName() + ChatColor.AQUA + "[ 时间陷阱 ]" + ChatColor.GREEN + "自动保存失败");
                        Bukkit.getConsoleSender().sendMessage(TimeTrap.getTimeTrapName() + ChatColor.AQUA + "[ 时间陷阱 ]" + ChatColor.GREEN + e);
                    }
                    int sec = i % 60;
                    int minutes = i / 60;
                    int min = minutes % 60;
                    int hjh = minutes / 60;
                    int hou = hjh % 24;
                    int day = hjh / 24;
                    if (!Config.getStart()) {
                        TimeTrap.setEventTorF(false);
                        this.cancel();
                    }
                    if (i > 0) {
                        online.sendTitle("", day + ":" + hou + ":" + min + ":" + sec, 0, 22, 0);
                        i--;
                        //修改玩家时间
                        PlayerTimeHolder.modifyPlayerTime(online.getUniqueId(), i);
                        if (online.getGameMode().equals(GameMode.SPECTATOR)) {
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    online.setGameMode(GameMode.SURVIVAL);
                                    online.sendMessage(ChatColor.AQUA + "[TimeTrap] " + ChatColor.GREEN + "您已被复活");
                                }
                            }.runTask(TimeTrap.getInstance());
                        }
                    } else {
                        if (!online.getGameMode().equals(GameMode.SPECTATOR)) {
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    online.sendTitle("您的游戏结束", "", 20, 40, 20);
                                    online.setGameMode(GameMode.SPECTATOR);
                                }
                            }.runTask(TimeTrap.getInstance());
                        }
                    }


                });
                try {
                    String sql = "SELECT * FROM DATA ORDER BY S DESC";
                    Statement stat = TimeTrap.getCon().createStatement();
                    ResultSet rs = stat.executeQuery(sql);
                    int a = 0;
                    while (rs.next()) {
                        String id = rs.getString("UUID");
                        String name = rs.getString("Name");
                        int i1 = rs.getInt("S");
                        int sec1 = i1 % 60;
                        int minutes1 = i1 / 60;
                        int min1 = minutes1 % 60;
                        int hjh1 = minutes1 / 60;
                        int hou1 = hjh1 % 24;
                        int day1 = hjh1 / 24;
                        String Name = name + ": " + day1 + "天" + hou1 + "时" + min1 + "分" + sec1 + "秒(" + i1 + ")";
                        switch (a) {
                            case 0: {
                                top1 = Name;
                                break;
                            }
                            case 1: {
                                top2 = Name;
                                break;
                            }
                            case 2: {
                                top3 = Name;
                                break;
                            }
                            case 3: {
                                top4 = Name;
                                break;
                            }
                            case 4: {
                                top5 = Name;
                                break;
                            }
                            case 5: {
                                top6 = Name;
                                break;
                            }
                            case 6: {
                                top7 = Name;
                                break;
                            }
                            case 7: {
                                top8 = Name;
                                break;
                            }
                            case 8: {
                                top9 = Name;
                            }
                            case 9: {
                                top10 = Name;
                            }
                        }
                        a++;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }.runTaskTimerAsynchronously(TimeTrap.getInstance(), 0, 20);
    }


}

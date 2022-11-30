package timetrap.timetrap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import timetrap.timetrap.commands.TimeTrapCon;
import timetrap.timetrap.config.Config;
import timetrap.timetrap.events.*;
import timetrap.timetrap.sql.SQLite;
import timetrap.timetrap.tab.Tab;
import java.sql.Connection;
import java.sql.SQLException;



public final class TimeTrap extends JavaPlugin {
    private static String TimeTrapName = ChatColor.AQUA + "[TimeTrap] ";
    private static boolean EventTorF;
    private static Connection con;
    private static TimeTrap instance;


    public static TimeTrap getInstance() {
        return instance;
    }



    public TimeTrap() {
    }

    public static Connection getCon() {
        return con;
    }

    public static boolean isEventTorF() {
        return EventTorF;
    }

    public static void setEventTorF(boolean eventTorF) {
        EventTorF = eventTorF;
    }

    public static String getTimeTrapName() {
        return TimeTrapName;
    }



    @Override
    public void onEnable() {
        instance = this;
        setEventTorF(false);
        Bukkit.getConsoleSender().sendMessage(TimeTrapName + ChatColor.AQUA + "[ 时间陷阱 ]" + ChatColor.GREEN + "插件加载成功");
        Bukkit.getConsoleSender().sendMessage(TimeTrapName + ChatColor.GREEN + "作者: " + ChatColor.AQUA + this.getDescription().getAuthors());
        Bukkit.getConsoleSender().sendMessage(TimeTrapName + ChatColor.GREEN + "版本: " + ChatColor.AQUA + this.getDescription().getVersion());
        this.regConfig();
        this.regCommands();
        this.regEvent();
        this.regAPI();
        new Papi().register();
        try {
            con = SQLite.getConnection();
            SQLite.createTable(con);
            this.getLogger().warning(TimeTrapName+"导入成功");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (Config.getStart()){
            try {
                SQLite.queryAllData(getCon());
                // 读取时间
            } catch (SQLException e) {
                e.printStackTrace();
            }
            TimeTrapCon.StarEvent();
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Bukkit.getConsoleSender().sendMessage(TimeTrapName + ChatColor.AQUA + "[ 时间陷阱 ]" + ChatColor.RED + "插件卸载成功");
    }


    private void regAPI() {
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") == null) {
            this.getLogger().warning("没有找到PlaceholderAPI，变量将无法使用！！！");
        }

    }


    private void regConfig() {
        //保存配置文件
        this.saveDefaultConfig();
    }

    private void regCommands() {
        this.getCommand("timeTrap").setExecutor(new TimeTrapCon());
        this.getCommand("timeTrap").setTabCompleter(new Tab());
    }

    private void regEvent() {
        this.getServer().getPluginManager().registerEvents(new JoinExecutor(), this);
        this.getServer().getPluginManager().registerEvents(new QuitEvent(), this);
        this.getServer().getPluginManager().registerEvents(new DeathEvent(), this);
        this.getServer().getPluginManager().registerEvents(new ToggleSneakEvent(), this);
        this.getServer().getPluginManager().registerEvents(new DropItemEvent(), this);
        this.getServer().getPluginManager().registerEvents(new DamageEvent(), this);
    }


}

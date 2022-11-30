package timetrap.timetrap.tab;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import java.util.ArrayList;
import java.util.List;

public class Tab implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            List list = new ArrayList<>();
            if (sender.hasPermission("timetrap.op")) {
                list.add("帮助");
                list.add("开始");
                list.add("继续");
                list.add("暂停");
                list.add("结束");
                list.add("重载");
                list.add("设置");
                list.add("给予");
                list.add("清除记录");
            }
            if (sender.hasPermission("timetrap.pay")) {
                list.add("转账");
            }
            if (sender.hasPermission("timetrap.list")) {
                list.add("排行");
            }
            return list;
        }
        if (args.length == 2) {
            if (args[1].equals("转账") | args[1].equals("设置") | args[1].equals("给予")) {
                List list = new ArrayList<>();
                Bukkit.getOnlinePlayers().forEach(o -> {
                    list.add(o.getName());
                });
                return list;
            }
        }
        if (args.length == 3) {
            if (args[1].equals("转账") | args[1].equals("设置") | args[1].equals("给予")) {
                List list = new ArrayList<>();
                list.add("填正负整数");
                return list;
            }
        }
        return null;
    }
}

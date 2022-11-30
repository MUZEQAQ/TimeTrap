package timetrap.timetrap.holder;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerTimeHolder {
    private static Map<UUID, Integer> playerTimeMap =new HashMap<>();

    public static Map<UUID, Integer> getPlayerTimeMap() {
        return playerTimeMap;
    }

    public static boolean contains(UUID playerName){
        return playerTimeMap.containsKey(playerName);
    }
    //获取时间
    public static Integer getPlayerTime(UUID playerName){
        if(contains(playerName)){
            return playerTimeMap.get(playerName);
        }else{
            return -1;
        }
    }
    //添加数据
    public static Integer addPlayerTime (UUID playerName, int time){
        return playerTimeMap.put(playerName,time);
    }
    //删除数据
    public static Integer removePlayerTime (UUID playerName){
        return playerTimeMap.remove(playerName);
    }
    //修改数据
    public static void modifyPlayerTime (UUID playerName,int time){
        if (PlayerTimeHolder.contains(playerName)) {
            PlayerTimeHolder.removePlayerTime(playerName);
        }
        PlayerTimeHolder.addPlayerTime(playerName, time);
    }

}

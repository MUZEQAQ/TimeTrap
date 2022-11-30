package timetrap.timetrap.sql;

import org.sqlite.SQLiteConfig;
import org.sqlite.SQLiteDataSource;
import timetrap.timetrap.holder.PlayerTimeHolder;

import java.sql.*;
import java.util.UUID;

public class SQLite {


    //创建+链接数据库 CONNECT
    public static Connection getConnection() throws SQLException {
        SQLiteConfig config = new SQLiteConfig();
        config.setSharedCache(true);
        config.enableRecursiveTriggers(true);
        SQLiteDataSource ds = new SQLiteDataSource(config);
        //⭐你可以命名"jdbc:sqlite:"后面的数据库文件名称，程序运行时若无此文件，会自动创建
        String url = System.getProperty("user.dir"); // 获取工作目录
        ds.setUrl("jdbc:sqlite:" + url + "/plugins/TimeTrap/Data.db");
        return ds.getConnection();
    }

    //创建表操作 CREATE TABLE
    public static void createTable(Connection con) throws SQLException {
        //⭐这里需要自定义数据类型和数据数量
        String sql = "create TABLE IF not EXISTS 'DATA'('Name' text ,'UUID' text , 'S' integer);";
        Statement stat = null;
        stat = con.createStatement();
        stat.executeUpdate(sql);

    }

    //查询是否存在
    public static int queryData(Connection con, UUID playerUUID) throws SQLException {
        String sql = "SELECT * FROM DATA WHERE UUID='" + playerUUID + "'";
        Statement stat = con.createStatement();
        ResultSet rs = stat.executeQuery(sql);
        int time = -1;
        while (rs.next()) {
            String id = rs.getString("UUID");
            String name = rs.getString("Name");
            time = rs.getInt("S");

        }
        return time;
    }

    //新增玩家数据
    public static void addData(Connection con, String playerName, UUID playerUUID, int time) throws SQLException {
        String sql = "insert into DATA (Name,UUID, S) values('" + playerName + "','" + playerUUID + "'," + time + ")";
        Statement stat = null;
        stat = con.createStatement();
        stat.executeUpdate(sql);
    }

    //修改玩家数据
    public static int modifyData(Connection con, UUID playerUUID, int time) {
        String sql = "UPDATE DATA SET S = " + time + " WHERE UUID = '" + playerUUID + "'";
        try {
            Statement stat = null;
            stat = con.createStatement();
            stat.executeUpdate(sql);
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    //删除表
    public static void modifyALLData(Connection con) throws SQLException {
        String sql = "DROP TABLE DATA";
        Statement stat = null;
        stat = con.createStatement();
        stat.executeUpdate(sql);
        createTable(con);
    }
    //查询全部数据
    public static void queryAllData(Connection con) throws SQLException {
        String sql = "SELECT * FROM DATA";
        Statement stat = con.createStatement();
        ResultSet rs = stat.executeQuery(sql);
        while (rs.next()) {
            String id = rs.getString("UUID");
            String name = rs.getString("Name");
            int time = rs.getInt("S");
            PlayerTimeHolder.modifyPlayerTime(UUID.fromString(id), time);
        }
    }




}
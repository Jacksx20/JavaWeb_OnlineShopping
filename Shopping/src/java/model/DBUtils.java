package model;

import java.sql.*;
import java.util.*;

public class DBUtils {
    private static String url;
    private static List<Type> types;
    
    public static void setUrl(String url) {
        DBUtils.url= url;
    }
    
    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(DBUtils.url);
    }
    
    public static boolean update(String sql, Object...args) {
        try(Connection cn= connect();
            CallableStatement cs= cn.prepareCall(sql)) {
            for(int i=0; i<args.length; i++) cs.setObject(i+1, args[i]);
            cs.executeUpdate();
            return true;
        }catch(SQLException se) {
            se.printStackTrace();
            return false;
        }
    }
    
    public static List<Type> queryTypes() {
        types= new ArrayList<>();
        try(Connection cn= DBUtils.connect();
            PreparedStatement ps= cn.prepareStatement("select * from types");
            ResultSet rs= ps.executeQuery()) {
            while(rs.next()) {
                types.add(new Type(rs.getInt("code"),rs.getString("title")));
            }
        }catch(SQLException se) {
            se.printStackTrace();
        }
        return types;
    }

    public static HashMap<String,Ware> queryWaresByType(int type) {
        HashMap<String,Ware> map= new HashMap<>();
        try(Connection cn= DBUtils.connect();
            PreparedStatement ps= cn.prepareStatement("select * from wares where type="+type);
            ResultSet rs= ps.executeQuery()) {
            for(String id; rs.next();) {
                Ware ware= new Ware(id=rs.getString("code"), 
                                    rs.getString("title"), 
                                    rs.getString("model"), 
                                    rs.getString("depict"), 
                                    rs.getString("photo"),
                                    rs.getDouble("price"),
                                    rs.getInt("amount"),
                                    rs.getInt("type"));
                map.put(id, ware);
            }
        }catch(SQLException se) {
            se.printStackTrace();
        }
        return map;
    }
}

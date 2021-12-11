package cn.test;



import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import cn.DButil.DatabaseDAO;
//
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataDAO<T> {

    public int executeSql(String sql,Object...args){
        Connection conn = DatabaseDAO.getConn();
        PreparedStatement ps = null;
        int flag = 0;

        try {
            ps = conn.prepareStatement(sql);
            if(args!=null&&args.length>0){
                for(int i = 0;i < args.length;i++){
                    ps.setObject(i+1,args[i]);
                }
            }
            System.out.println(sql);
            flag = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return flag;
    }

    public List<T> getData(String sql,Class<T> clazz,Object...args){
        List<T> list = new ArrayList<T>();
        Connection conn = DatabaseDAO.getConn();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql);
            if(args!=null&&args.length>0){
                for(int i = 0;i < args.length;i++){
                    ps.setObject(i+1,args[i]);
                }
            }
            System.out.println(sql);
            rs = ps.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            while (rs.next()){
                T t = clazz.newInstance();
                for(int i = 1;i <= metaData.getColumnCount();i++){
                    String columnName = metaData.getColumnName(i);
                    Object value = rs.getObject(i);
                    //BeanUtils.setProperty(t,columnName,value);
                    BeanUtils.setProperty(t, columnName, value);
                }
                list.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return list;
    }
}

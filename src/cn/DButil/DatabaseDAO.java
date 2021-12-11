package cn.DButil;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;

public class DatabaseDAO {
    
	 public static Connection getConn() {
//	        String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";//数据库驱动程序
  String url="jdbc:mysql://localhost:3307/bookshop?&useSSL=false&serverTimezone=UTC";//URL
  String user="root";//用户名
  String password="root";//密码
  Connection conn = null;
  //加载数据库驱动
  try {
	Class.forName("com.mysql.cj.jdbc.Driver");
	
	conn = DriverManager.getConnection(url, user, password);
} catch (ClassNotFoundException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

  //连接数据库并验证数据
  
 // System.out.println("Connection Created");
  return conn;

}

	
	
    /* public static Connection getConn(){
    	 Connection conn = null;
    	 InputStream ins = DatabaseDAO.class.getResourceAsStream("config.properties");
    	 
    	 //InputStream ins = ClassLoader.getSystemResourceAsStream("edu/xzit/java/day1226/config.properties");
    	 Properties p = new Properties();
    	 try {
			p.load(ins);
		    //BasicDataSource
			DataSource dataSource = (DataSource)BasicDataSourceFactory.createDataSource(p);
			conn = dataSource.getConnection();	
			
    	 } catch (Exception e) {
			throw new RuntimeException(e);
		}
    	 return conn;
     } 
     */
   public static void main(String[] args) {
		Connection a=new DatabaseDAO().getConn();
		System.out.println("aa");
	} 
     
    public static void closeAll(ResultSet rs,Statement ps,Connection conn){
    	try {
    		if(rs!=null)
			rs.close();
    		if(ps!=null)
			ps.close();
    		if(conn!=null)
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    } 
}

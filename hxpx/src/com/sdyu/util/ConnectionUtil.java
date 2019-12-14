package com.sdyu.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mysql.jdbc.ResultSet;

public class ConnectionUtil {
	static Connection conn=null;
  public  static Connection getConnection() throws Exception{
	  Class.forName("com.mysql.jdbc.Driver");
	  String url="jdbc:mysql://localhost:3307/stu";
	  String user="root";
	  String password="74123";
	  conn=DriverManager.getConnection(url,user,password);
	  return conn;
  }
  
  public static void closeConn(){
	  try {
		conn.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
  
  public List<Map<String, Object>> convertToList(ResultSet rs) throws SQLException {
	  List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
	  try{
	  ResultSetMetaData md = rs.getMetaData(); //获得结果集结构信息,元数据
		int columnCount = md.getColumnCount();   //获得列数 
		while (rs.next()) {
			Map<String,Object> rowData = new HashMap<String,Object>();
			for (int i = 1; i <= columnCount; i++) {
				rowData.put(md.getColumnName(i), rs.getObject(i));
			}
			list.add(rowData);

		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
		return list;
	}
  public static void main(String[] args) throws Exception{
	  System.out.println(getConnection());
  }
}
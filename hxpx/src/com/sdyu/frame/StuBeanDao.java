package com.sdyu.frame;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.sdyu.util.ConnectionUtil;

public class StuBeanDao {


	//��ѯ��Ϣ���

	Connection con = null;
	Statement st = null;
	ResultSet rs = null;
	String sql = null;
	PreparedStatement ps = null;

	/*
	 * ������Ϣ
	 */
	public int insertStu(StuBean stu) {
		String number = stu.getNo();
		String name = stu.getName();
		String phonenumber = stu.getPhoneNumber();
		String bumen = stu.getBumen();
		int result = 0;
		sql = "insert into Student(no,name,phonenumber,bumen) values('"
				+ number
				+ "','"
				+ name
				+ "','"
				+ phonenumber
				+ "','"
				+ bumen
				+ "');";
		try {
			con = ConnectionUtil.getConnection();
			st = con.createStatement();
			result = st.executeUpdate(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionUtil.closeConn();
		}

		return result;

	}

	/*
	 * ɾ����Ϣ
	 */
	public int deleteStu(String stuno) {

		int result = 0;

		try {
			con = ConnectionUtil.getConnection();
			st = con.createStatement();
			String sql = "select * from Student where no='" + stuno + "'";
			rs = st.executeQuery(sql);

			while (rs.next()) {
				sql = "delete from Student where no=?";
				ps = con.prepareStatement(sql);
				ps.setString(1, stuno);
				result = ps.executeUpdate();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionUtil.closeConn();
		}

		return result;
	}

	/*
	 * �޸���Ϣ
	 */
	public int updateStu(StuBean stu) {
		String number = stu.getNo();
		String name = stu.getName();
		String phonenumber = stu.getPhoneNumber();
		String bumen = stu.getBumen();
		int result = 0;
		sql = "update Student set name='"
				+ name
				+ "',"
				+ "phonenumber='"
				+ phonenumber
				+ "',"
				+ "bumen='"
				+ bumen
				+ "'  where no='"+number+"';";
		
		try {
			con = ConnectionUtil.getConnection();
			st = con.createStatement();
			result = st.executeUpdate(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionUtil.closeConn();
		}

		return result;
	}

	/*
	 * ��ѯ��������Ϣ
	 */
	
	public String[][] searchAll(){
		String[][] result=null;
		int row=0;
		int i=0;
		sql="select * from student order by no";
		try {
			con = ConnectionUtil.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(sql);
			if(rs.last()){
				row=rs.getRow();
				
			}
			if(row==0){
				result=null;
			}else{
				result=new String[row][6];
				rs.first();
				rs.previous();
				while(rs.next()){
					result[i][0]=rs.getString("no");
					result[i][1]=rs.getString("name");
					result[i][2]=rs.getString("phonenumber");
					result[i][3]=rs.getString("bumen");
					i++;

				}
				
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionUtil.closeConn();
		}
		
		return result;
	}
	
	
    
	/*
	 * ���ݱ�Ų�ѯ��Ϣ
	 */
	
	public String[] searchById(String stuno){
		String[] result=null;		
		
		sql="select * from student where no='"+stuno+"'";
		try {
			con = ConnectionUtil.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(sql);
			result=new String[4];
			if(rs.next()){
					result[0]=rs.getString("no");
					result[1]=rs.getString("name");
					result[2]=rs.getString("phonenumber");
					result[3]=rs.getString("bumen");
			}
			} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionUtil.closeConn();
		}
		
		return result;
	}
	
	/*
	 * �����ֻ��Ų�ѯ��Ϣ
	 */
	
	public String[] searchByPN(String stuno){
		String[] result=null;		
		
		sql="select * from student where phonenumber='"+stuno+"'";
		try {
			con = ConnectionUtil.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(sql);
			result=new String[4];
			if(rs.next()){
					result[0]=rs.getString("no");
					result[1]=rs.getString("name");
					result[2]=rs.getString("phonenumber");
					result[3]=rs.getString("bumen");
			}
			} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionUtil.closeConn();
		}
		
		return result;
	}
	
	/*
	 * ����������ѯѧ����Ϣ
	 */
	
	public String[] searchByName(String stuname){
		String[] result=null;
		int row=0;
		
		sql="select * from student where name='"+stuname+"'";
		try {
			con = ConnectionUtil.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(sql);
			result=new String[4];
			if(rs.next()){
					result[0]=rs.getString("no");
					result[1]=rs.getString("name");
					result[2]=rs.getString("phonenumber");
					result[3]=rs.getString("bumen");
			}
			} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionUtil.closeConn();
		}
		
		return result;
	}
	/*
	 * ���ݲ��Ų�ѯ��Ϣ
	 */
	
	public String[][] searchByBumen(String bumen){
		
		String[][] result=null;
		int row=0;
		int i=0;
		sql="select * from student where bumen='"+bumen+"'";
		try {
			con = ConnectionUtil.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(sql);
			if(rs.last()){
				row=rs.getRow();
				
			}
			if(row==0){
				result=null;
			}else{
				result=new String[row][4];
				rs.first();
				rs.previous();
				while(rs.next()){
					result[i][0]=rs.getString("no");
					result[i][1]=rs.getString("name");
					result[i][2]=rs.getString("phonenumber");
					result[i][3]=rs.getString("bumen");
					i++;

				}
				
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionUtil.closeConn();
		}
		
		return result;
	}
	

	

}

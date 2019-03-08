package Homework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Drug1 {
	
	static Connection conn=null;
	static PreparedStatement ps=null;
    static ResultSet rs=null;
	private static String raven;
	public static Connection getMysqlConn(){
		try {
			/**
			 * ����JDBC��������
			 */
			Class.forName("com.mysql.jdbc.Driver");
			//��ȡ���ݿ���Ϣ
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/raven?useUnicode=true&characterEncoding=gbk","root","raven");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	

	public static void close(ResultSet rs,Statement ps,Connection conn){
		try {
			if(rs!=null){
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(ps!=null){
				ps.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(conn!=null){
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Statement ps,Connection conn){
		try {
			if(ps!=null){
				ps.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(conn!=null){
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @param conn
	 */
	public static void close(Connection conn){
		try {
			if(conn!=null){
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/*
	 * ��ѯ����  Ȼ���ý����������ӡ ��ѭ�ȿ���صĹ���Ѷ���ȫ���ر�
	 */
	public static void SeleceDate(String user_name){
		conn=Drug1.getMysqlConn();
		try {
			String sql="select * from Drug_01 where ҩƷ����=?";
			ps=conn.prepareStatement(sql);
			ps.setObject(1, user_name);
			//ps.setObject(2, user_name);
			 rs=ps.executeQuery();
				while(rs.next()){
					System.out.println(rs.getInt(1)+"------"+rs.getString(2)+"------"+rs.getString(3));}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			Drug1.close(ps, conn);
		}
	}
	public static void SeleceDate1(){
		conn=Drug1.getMysqlConn();
		try {
			String sql="select * from Drug_01";
			ps=conn.prepareStatement(sql);
			//ps.setObject(1, user_name);
			//ps.setObject(2, user_name);
			 rs=ps.executeQuery();
				while(rs.next()){
					System.out.println(rs.getInt(1)+"------"+rs.getString(2)+"------"+rs.getString(3));}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			Drug1.close(ps, conn);
		}
	}
	
	/**
	 * ��������
	 * @param user_name
	 * @param pwbs
	 */
		public static void InsertDate8(String A,String B,String C,String D,String E ) {
			conn=Drug1.getMysqlConn();
			try {
				String sql="insert into drug_01 (ҩƷ����,ҩƷ����,��λ,��װ,����) values (?,?,?,?,?) ";
				ps=conn.prepareStatement(sql);
				ps.setObject(1, A);
				ps.setObject(2,B);
				ps.setObject(3,C);
				ps.setObject(4,D);
				ps.setObject(5,E);
               ps.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				Drug1.close(ps, conn);
			}
		}
		/**
		 * 
		 */
		public static void InsertDate10(String A,String B,String C,String D,String E ) {
			conn=Drug1.getMysqlConn();
			try {
				String sql="insert into drug_02 (��Ӧ������,��ϵ��,��ϵ�绰,��ַ,������) values (?,?,?,?,?) ";
				ps=conn.prepareStatement(sql);
				ps.setObject(1, A);
				ps.setObject(2,B);
				ps.setObject(3,C);
				ps.setObject(4,D);
				ps.setObject(5,E);
               ps.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				Drug1.close(ps, conn);
			}
		}
		/**
		 * 
		 */
		public static void InsertDate11(String A,String B,String C,String D,String E) {
			conn=Drug1.getMysqlConn();
			try {
				String sql="insert into drug_03 (��ⵥ���,�������,�����Ա,�ܽ��,�����) values (?,?,?,?,?) ";
				ps=conn.prepareStatement(sql);
				ps.setObject(1, A);
				ps.setObject(2,B);
				ps.setObject(3,C);
				ps.setObject(4,D);
				ps.setObject(5,E);
				//ps.setObject(6, F);
               ps.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				Drug1.close(ps, conn);
			}
		}
		public static void InsertDate18(String A,String B,String C,String D,String E) {
			conn=Drug1.getMysqlConn();
			try {
				String sql="insert into drug_04 (���,����,����,��װ,��Ч����) values (?,?,?,?,?) ";
				ps=conn.prepareStatement(sql);
				ps.setObject(1, A);
				ps.setObject(2,B);
				ps.setObject(3,C);
				ps.setObject(4,D);
				ps.setObject(5,E);
				//ps.setObject(6, F);
               ps.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				Drug1.close(ps, conn);
			}
		}
		/**
		 * ɾ������
		 * @param s
		 */
		public static void DelectDate(String s) {
			conn=Drug1.getMysqlConn();
			try {
				String sql="delete from drug_01 where ҩƷ����=? ";
				ps=conn.prepareStatement(sql);
				ps.setObject(1, s);
               ps.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				Drug1.close(ps, conn);
			}
		}
		/**
		 * ɾ��֪ͨ
		 * @param s
		 */
		public static void DelectTongZhi(String s) {
			conn=Drug1.getMysqlConn();
			try {
				String sql="delete from data where text=? ";
				ps=conn.prepareStatement(sql);
				ps.setObject(1, s);
               ps.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				Drug1.close(ps, conn);
			}
		}
		///////////////////////////
		public static void DelectDates(String s) {
			conn=Drug1.getMysqlConn();
			try {
				String sql="delete from drug_02 where ��Ӧ������=? ";
				ps=conn.prepareStatement(sql);
				ps.setObject(1, s);
               ps.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				Drug1.close(ps, conn);
			}
		}
		
		/**
		 * 
		 * @param a
		 * @param b
		 * @param c
		 * @param d
		 * @param eg
		 */
		public static void DelectDate1(String s) {
			conn=Drug1.getMysqlConn();
			try {
				String sql="delete from drug_03 where ��ⵥ���=? ";
				ps=conn.prepareStatement(sql);
				ps.setObject(1, s);
               ps.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				Drug1.close(ps, conn);
			}
		}
		/**
		 * 
		 * @param a
		 * @param b
		 * @param c
		 * @param d
		 * @param eg
		 */
		public static void DelectDate2(String s) {
			conn=Drug1.getMysqlConn();
			try {
				String sql="delete from drug_04 where ���=? ";
				ps=conn.prepareStatement(sql);
				ps.setObject(1, s);
               ps.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				Drug1.close(ps, conn);
			}
		} 
		static void UpdateDate1(String a,String b,String c,String d,String eg) {
			conn=Drug1.getMysqlConn();
			try {
				String sql="update drug_01 set ҩƷ����=?,��ⵥ���=?,��λ=?,��װ=? where ҩƷ����=?";
				ps=conn.prepareStatement(sql);
				ps.setObject(1, a);
				ps.setObject(2, b);
				ps.setObject(3, c);
				ps.setObject(4, d);
				ps.setObject(5, eg);
               //ps.execute();
				ps.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				Drug1.close(ps, conn);
			}
		}
			/**
			 * ��¼��������
			 * 
			 */
			public static void InsertLog(String A,String B,String C,String D,String E) {
				conn=Drug1.getMysqlConn();
				try {
					String sql="insert into loging (account,password,name,sex,phone) values (?,?,?,?,?) ";
					ps=conn.prepareStatement(sql);
					ps.setObject(1, A);
					ps.setObject(2,B);
					ps.setObject(3,C);
					ps.setObject(4,D);
					ps.setObject(5,E);
	               ps.execute();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					Drug1.close(ps, conn);
				}
			}
		}


		








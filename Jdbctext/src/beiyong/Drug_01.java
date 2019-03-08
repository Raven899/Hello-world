package beiyong;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Drug_01 {
	
	static Connection conn=null;
	static PreparedStatement ps=null;
    static ResultSet rs=null;
	private static String raven;
	public static Connection getMysqlConn(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
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
	 * 查询数据  然后用结果集遍历打印 遵循先开后关的规则把对象全都关闭
	 */
	public static void SeleceDate(String user_name){
		conn=Drug1.getMysqlConn();
		try {
			String sql="select * from Drug_01 where 药品名称=?";
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
	public static String SeleceDate1(String drug_name){
		conn=Drug1.getMysqlConn();
		try {
			String sql="select * from Drug_01 where 药品名称=?";
			ps=conn.prepareStatement(sql);
			ps.setObject(1, drug_name);
			//ps.setObject(2, user_name);
			 rs=ps.executeQuery();
				while(rs.next()){
					String s;
					 s = rs.getString(1);
					String m;
					 m = rs.getString(2);
					String h;
					h = rs.getString(3);
					String ss;
					  ss = rs.getString(4);
					String sm;
					 sm = rs.getString(15);
					
							}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			Drug1.close(ps, conn);
		}
		return drug_name;
	
	}
	/**
	 * 插入数据
	 * @param user_name
	 * @param pwbs
	 */
		public static void InsertDate8(String A,String B,String C,String D,String E ) {
			conn=Drug1.getMysqlConn();
			try {
				String sql="insert into drug_01 (药品编码,药品名称,单位,包装,入库单编号) values (?,?,?,?,?) ";
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
				String sql="insert into drug_02 (供应商名称,联系人,联系电话,地址,开户行) values (?,?,?,?,?) ";
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
		 * 删除数据
		 * @param s
		 */
		public static void DelectDate(String s) {
			conn=Drug1.getMysqlConn();
			try {
				String sql="delete from drug_01 where 药品编码=? ";
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
				String sql="delete from drug_02 where 供应商名称=? ";
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

		public static void UpdateDate1(String a,String b,String c,String d,String eg) {
			conn=Drug1.getMysqlConn();
			try {
				String sql="update drug_01 set 药品名称=?,入库单编号=?,单位=?,包装=? where 药品编码=?";
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

}
		
//	public static void main(String[] args) {
//		Drug1 f=new Drug1();
//		Drug1.SeleceDate(1, raven);
//		String S="xuekai";
//		String M="88888";
//		String g="88888";
//		String d="88888";
//		String z="88888";
//		
		//int a=2;
		//f.InsertDate8(S,M);
		//f.DelectDate(6);
		//f.UpdateDate(S, 2);
//		//f.InsertDate8(S,M, g, d, z);


	







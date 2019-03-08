package Homework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JTable;


/**
 * 批处理
 * @author
 *
 */
public class Index {
	 private static JTable table = null;
	public static void Insert() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
	   

		try {
			//加载驱动类
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/raven?useUnicode=true&characterEncoding=gbk","root","raven");
			
			conn.setAutoCommit(false);  //设为手动提交
			long start = System.currentTimeMillis();
			stmt = conn.createStatement();
			for(int i=1;i<1500;i++){
				String s=Names.build(3);
				String m=Names.randomDate();
				String w=Names.nation();
				String t=Names.city();

				String sql="insert into test2(ID,name,nation,city,birthday) values ('"+i+"','"+s+"','"+w+"','"+t+"','"+m+"')";
				stmt.addBatch(sql);
			}
			stmt.executeBatch();
			conn.commit();  //提交事务
			long end = System.currentTimeMillis();
			JOptionPane.showMessageDialog(table,"插入成功！"+"\n"+(end-start)+"毫秒");	
			System.out.println("插入150万条数据，耗时(毫秒)："+(end-start));
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			//遵循：resultset-->statment-->connection这样的关闭顺序
			try {
				if(rs!=null){
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(stmt!=null){
					stmt.close();
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
	}

}

package Jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Random;
/**
 * 测试事务
 * @author 瑞文
 *
 */

public class Test7{
public static void main(String[] args) {
	Connection conn=null;
	PreparedStatement ps1=null;
	try {
		//加载驱动类
		Class.forName("com.mysql.jdbc.Driver");
		
		try {
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/raven?useUnicode=true&characterEncoding=gbk","root","raven");
		conn.setAutoCommit(false);//JDBC中默认自动提交事务
		for(int i=0;i<1000;i++) {
			String sql=("insert into t_user(username,pwd,regTime,lastlogoinTime) values(?,?,?,?)");
			ps1=conn.prepareStatement(sql);
				ps1.setObject(1, "县城"+i);
				ps1.setObject(2, 888888888);
				
				int rand=10000000+new Random().nextInt(1000000000);
				
		java.sql.Date date=new java.sql.Date(System.currentTimeMillis()-rand);
		Timestamp stamp=new Timestamp(System.currentTimeMillis()-rand);
		ps1.setDate(3, date);
		ps1.setTimestamp(4, stamp);
		ps1.execute();
		conn.commit();
		
		}
		System.out.println("插入数据");
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback();
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
			if(ps1!=null) {
				try {
					ps1.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				}
			}
		}
	}



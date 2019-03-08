package Jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test5 {
public static void main(String[] args) {
	Connection conn=null;
	Statement stmt=null;
	ResultSet rs=null;
	try {
		//加载驱动类
		Class.forName("com.mysql.jdbc.Driver");
		
		try {
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/raven?useUnicode=true&characterEncoding=gbk","root","raven");
		conn.setAutoCommit(false);//事务设为手动提交
		
		long start=System.currentTimeMillis();
		stmt=conn.createStatement();//创建对象
		
		for(int i=0;i<200;i++) {
			stmt.addBatch("insert into t_user(username,pwd,regTime) values ('raven@"+i+"',960828715,now())");
			}
		
		stmt.executeBatch();//执行
		conn.commit();// 手动提交事务
		long end=System.currentTimeMillis();
		System.out.println("插入两万条数据耗时："+(end-start));
		
			
			
		} catch (SQLException e) { }
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		if(stmt!=null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(rs!=null) {
					try {
						rs.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
}
}

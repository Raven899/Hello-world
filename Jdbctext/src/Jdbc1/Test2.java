package Jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Test2 {
	public static void main(String[] args) {
		
		try {
			//加载驱动类
			Class.forName("com.mysql.jdbc.Driver");
			long start = System.currentTimeMillis();
			try {
				Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/raven?useUnicode=true&characterEncoding=gbk","root","raven");
			Statement stmt=conn.createStatement();
			String name="龟孙";
			//String sql="insert INTO t_user(username,pwd,regTime) VALUES('"+name+"',96082871,NOW())";
				
			//stmt.execute(sql);	
			//测试sql注入
			String sql="delete from t_user where username='三狗'";
			stmt.execute(sql);	
			} catch (SQLException e) { }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

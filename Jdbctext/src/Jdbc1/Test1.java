package Jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Test1 {
public static void main(String[] args) {
	Connection conn=null;
		try {
			//加载驱动类
			Class.forName("com.mysql.jdbc.Driver");
			long start = System.currentTimeMillis();
			try {
				conn=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/raven?useUnicode=true&characterEncoding=gbk","root","raven");
				long end = System.currentTimeMillis();
				System.out.println(conn);
				System.out.println("建立连接，耗时："+(end-start)+"ms毫秒");
			} catch (SQLException e) { }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
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

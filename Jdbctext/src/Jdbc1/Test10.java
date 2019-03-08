package Jdbc1;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 将字符串代表的日期转为long数字(格式：yyyy-MM-dd hh:mm:ss)
 * 测试事务
 * @author 瑞文
 *
 */

public class Test10{
	
public static void main(String[] args) {
	Connection conn=null;
	PreparedStatement ps1=null;

	try {
		//加载驱动类
		Class.forName("com.mysql.jdbc.Driver");
		
		try {
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/raven?useUnicode=true&characterEncoding=gbk","root","raven");
		//ps1=conn.prepareStatement("insert into t_user(username,headImag) values(?,?)");
		ps1 = conn.prepareStatement("insert into t_user (username,headImg) values (?,?) ");
		ps1.setString(1, "高淇");
		ps1.setBlob(2, new FileInputStream("e:/google/100.jpg"));
				
				ps1.execute();
		

//		
		System.out.println("插入数据");
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback();
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
//		if(r!=null) {
//			try {
//				r.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		if(rs!=null) {
//			try {
//				rs.close();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		
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



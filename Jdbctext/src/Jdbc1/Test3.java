package Jdbc1;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Test3 {
	public static void main(String[] args) {
		Connection conn=null;
		PreparedStatement ps=null;
		try {
			//����������
			Class.forName("com.mysql.jdbc.Driver");
			
			try {
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/raven?useUnicode=true&characterEncoding=gbk","root","raven");
			String sql="insert INTO t_user(username,pwd,regTime) VALUES(?,?,?)";
			ps=conn.prepareStatement(sql);
				ps.setObject(1, "����");
				ps.setString(2, "66666");
				ps.setDate(3, new Date(System.currentTimeMillis()));
				System.out.println("����һ�м�¼");
				//ps.execute();
			int count=ps.executeUpdate();
			System.out.println(count);
				
			} catch (SQLException e) { }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(ps!=null) {
				try {
					ps.close();
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
				}
			}
		}
	}
	
}

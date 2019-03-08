package Jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 * ��������
 * @author ����
 *
 */

public class Test6 {
public static void main(String[] args) {
	Connection conn=null;
	PreparedStatement ps1=null;
	PreparedStatement ps2=null;
	try {
		//����������
		Class.forName("com.mysql.jdbc.Driver");
		
		try {
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/raven?useUnicode=true&characterEncoding=gbk","root","raven");
		conn.setAutoCommit(false);//JDBC��Ĭ���Զ��ύ����
		
		String sql=("insert into t_user(username,pwd) values(?,?)");
		ps1=conn.prepareStatement(sql);
			ps1.setObject(1, "�س�");
			ps1.setObject(2, 888888888);
			ps1.execute();
			System.out.println("����һ���û�");
			
			Thread.sleep(6000);
			
			ps2=conn.prepareStatement("insert into t_user(username,pwd) values(?,?,?)");
				ps2.setObject(1, "����");
				ps2.setObject(2, 888888888);
				ps2.execute();
				System.out.println("����һ���û�:����");
			
			
			conn.commit();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback();
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		if(ps2!=null) {
			try {
				ps2.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
}


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
		//����������
		Class.forName("com.mysql.jdbc.Driver");
		
		try {
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/raven?useUnicode=true&characterEncoding=gbk","root","raven");
		conn.setAutoCommit(false);//������Ϊ�ֶ��ύ
		
		long start=System.currentTimeMillis();
		stmt=conn.createStatement();//��������
		
		for(int i=0;i<200;i++) {
			stmt.addBatch("insert into t_user(username,pwd,regTime) values ('raven@"+i+"',960828715,now())");
			}
		
		stmt.executeBatch();//ִ��
		conn.commit();// �ֶ��ύ����
		long end=System.currentTimeMillis();
		System.out.println("�������������ݺ�ʱ��"+(end-start));
		
			
			
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

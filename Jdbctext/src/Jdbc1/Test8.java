package Jdbc1;

import java.sql.Connection;
//import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

//import java.sql.Timestamp;
//import java.util.Random;
/**
 * ���ַ������������תΪlong����(��ʽ��yyyy-MM-dd hh:mm:ss)
 * ��������
 * @author ����
 *
 */

public class Test8{
	public static long str2Date(String dateStr) {
		//���ַ������������תΪlong����
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	    try {
			return format.parse(dateStr).getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
public static void main(String[] args) {
	Connection conn=null;
	PreparedStatement ps1=null;
    ResultSet rs=null;
	try {
		//����������
		Class.forName("com.mysql.jdbc.Driver");
		
		try {
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/raven?useUnicode=true&characterEncoding=gbk","root","raven");
		//conn.setAutoCommit(false);//JDBC��Ĭ���Զ��ύ����

		ps1 = conn.prepareStatement("select * from t_user where regTime>? and regTime<?");
		java.sql.Date start = new java.sql.Date(str2Date("2018-9-10 10:23:45"));
		java.sql.Date end = new java.sql.Date(str2Date("2018-10-13 10:23:45"));
		ps1.setObject(1, start);
		ps1.setObject(2, end);
		
		rs = ps1.executeQuery();
		while(rs.next()){
			System.out.println(rs.getInt("id")+"--"+rs.getString("username")+"--"+rs.getDate("regTime"));
		}
        
		System.out.println("��������");
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback();
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		if(rs!= null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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



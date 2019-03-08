package Jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test4 {
public static void main(String[] args) {
	Connection conn=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	try {
		//¼ÓÔØÇý¶¯Àà
		Class.forName("com.mysql.jdbc.Driver");
		
		try {
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/raven?useUnicode=true&characterEncoding=gbk","root","raven");
		String sql="select id,username,pwd from t_user where id>?";
		ps=conn.prepareStatement(sql);
			ps.setObject(1, 1);
		   rs=ps.executeQuery();
			while(rs.next()){
				System.out.println(rs.getInt(1)+"------"+rs.getString(2)+"------"+rs.getString(3));}
			
			
			
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

package Jdbc2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ����ʹ��object[]����װһ���¼
 * @author ����
 *
 */

public class Fuck1 {
	public static void main(String[] args) {
		Connection conn=JDBCUtil.getMysqlConn();
		PreparedStatement ps = null;
		ResultSet rs=null;
		try {
			ps = conn.prepareStatement("select empname,salary,age from emp where id=?");
			ps.setObject(1, 1);
			rs=ps.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getString(1));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		
		}finally {
			JDBCUtil.close(rs, ps, conn);
		}
	}

}

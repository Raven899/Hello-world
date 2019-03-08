package Jdbc1;
import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Timestamp;
//import java.util.Random;
/**
 * ���ַ������������תΪlong����(��ʽ��yyyy-MM-dd hh:mm:ss)
 * ��������
 * @author ����
 *
 */

public class Test9{
	
public static void main(String[] args) {
	Connection conn=null;
	PreparedStatement ps1=null;
	ResultSet rs=null;
	Reader r=null;
	try {
		//����������
		Class.forName("com.mysql.jdbc.Driver");
		
		try {
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/raven?useUnicode=true&characterEncoding=gbk","root","raven");
		//conn.setAutoCommit(false);//JDBC��Ĭ���Զ��ύ����

		ps1 = conn.prepareStatement("select *from t_user where id=?");
		ps1.setObject(1, 5223);
		//ps1.setClob(2, new FileReader(new File("e:/google/123.txt")));
	     rs=ps1.executeQuery();	
	     while(rs.next()) {
	    	 Clob c=rs.getClob("myInfo");
	    	r=c.getCharacterStream();
	    	 int temp=0;
	    	 while((temp=r.read())!=-1) {
	    		 System.out.print((char)temp);
	    	 }
	    	 
	     }
		//ps1.executeUpdate();
        
		System.out.println("��������");
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback();
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		if(r!=null) {
			try {
				r.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(rs!=null) {
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



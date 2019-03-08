package Homework;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;


public class Indexone {

	private JFrame frame;
	private JTextField textField;
    private JTable table = null;
	static String[][] ss=new String[1024][5] ; //从文件中读取内容到此数组中
	boolean flag=true;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Indexone window = new Indexone();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Indexone() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 736, 554);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(new Color(238, 232, 170));
		frame.setBackground(Color.PINK);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setFont(new Font("宋体", Font.BOLD, 20));
		textField.setBounds(305, 156, 165, 42);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("请输入姓名");
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 23));
		lblNewLabel.setBounds(176, 159, 135, 32);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("常规查询");
		btnNewButton.setForeground(new Color(0, 128, 128));
		btnNewButton.setFont(new Font("宋体", Font.BOLD, 15));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flag=false;
				String s=textField.getText();
				select1( s);
			}
		});
		btnNewButton.setBounds(305, 260, 145, 42);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("创建索引查询");
		btnNewButton_1.setForeground(new Color(0, 128, 128));
		btnNewButton_1.setFont(new Font("宋体", Font.BOLD, 15));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flag=true;
				String s=textField.getText();
				select1(s);
			}
		});
		btnNewButton_1.setBounds(305, 338, 145, 42);
		frame.getContentPane().add(btnNewButton_1);
	
	JButton btnNewButton_2 = new JButton("插入150万数据");
	btnNewButton_2.setForeground(new Color(0, 128, 128));
	btnNewButton_2.setFont(new Font("宋体", Font.BOLD, 15));
	btnNewButton_2.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			new Index().Insert();
		}
	});
	btnNewButton_2.setBounds(305, 416, 145, 42);
	frame.getContentPane().add(btnNewButton_2);
}
	/**
	 * 
	 * @param id
	 */
	void select1(String name) {
		String sql=null;
		 int i = 0;
		 Connection conn = Drug1.getMysqlConn();
			PreparedStatement ps = null;
		  long start = System.currentTimeMillis();
		   try {
			   if(flag=true) {
				   sql="select * from test where name=?"; 
			   }else {
				   sql="select * from test1 where name=?"; 
			   }
			ps=conn.prepareStatement(sql);
				ps.setObject(1, name);
				 ResultSet rs = ps.executeQuery();
					while(rs.next()){
					
						ss[i][0]=rs.getString(1);
						   ss[i][1]=rs.getString(2);
						   ss[i][2]=rs.getString(3);
						   ss[i][3]=rs.getString(4);
						   ss[i][4]=rs.getString(5);		
					}
			long end = System.currentTimeMillis();
	JOptionPane.showMessageDialog(table,"该人员信息如下：\n"+"姓名："+ss[i][1]+"\n"+"民族："+ss[i][2]+"\n"+"籍贯："+ss[i][3]+"\n"+"出生日期："+ss[i][4]+"\n"+"花费时间:"+(end-start)+"毫秒");	
			} catch (SQLException e1) { 
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally {
				Drug1.close(ps, conn);
			}
		   
  
}
}

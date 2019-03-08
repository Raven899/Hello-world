package Homework;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Loging extends JFrame implements ActionListener{
	  private JLabel label_1; 
	  private JLabel label_2;
	  private JLabel label;
	  private JTextField numtext; 
	  private JTextField logtext; 
	  private JButton zhuce; 
	  private JButton loging; 
	  private static DefaultTableModel model = null;
	  private JTable table = null;
	  String ss[][];
	  String p1,p2,p3,p4,p5;
	  SimpleDateFormat df;
		String abc;
	int p6;
	private Object w;
	public Loging() {
		
		JLabel label = new JLabel("药品管理系统");
		label.setFont(new Font("华文行楷", Font.BOLD, 36));
		label.setBounds(231, 50, 302, 60);
		getContentPane().add(label);
		
		numtext = new JTextField();
		numtext.setBounds(283, 164, 185, 36);
		numtext.setFont(new Font("楷体", Font.PLAIN, 20));
		getContentPane().add(numtext);
		numtext.setColumns(10);
		
		logtext = new JTextField();
		logtext .setBounds(283, 231, 185, 36);
		logtext .setFont(new Font("楷体", Font.PLAIN, 20));
		getContentPane().add(logtext );
		logtext .setColumns(10);
		
		JLabel number = new JLabel("账号");
		number.setFont(new Font("楷体", Font.BOLD, 23));
		number.setBounds(231, 173, 72, 31);
		getContentPane().add(number);
		
		JLabel password = new JLabel("密码");
		password.setFont(new Font("楷体", Font.BOLD, 23));
		password.setBounds(231, 236, 72, 31);
		getContentPane().add(password);
		
		loging = new JButton("登录");
		loging.setFont(new Font("楷体", Font.PLAIN, 20));
		loging.setBounds(283, 300, 80, 27);
		getContentPane().add(loging);
		
		zhuce = new JButton("注册");
		zhuce.setBounds(388, 300, 80, 27);
		zhuce.setFont(new Font("楷体", Font.PLAIN, 20));

		getContentPane().add(zhuce);
		
		setBounds(350, 150, 792, 528);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setVisible(true);
		loging.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String s=numtext.getText();
				try {
					raven(s);
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		zhuce.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Zhuce();
			}
		});
		
	}

	public void actionPerformed(ActionEvent arg0) {
	
		
	}
	public void Zhuce() {
		JFrame f=new JFrame("");
		JPanel panel1,panel2,panel1_1,panel1_2,panel1_3,panel1_4,panel1_5;
		JPanel panel=new JPanel();
		
		panel.setLayout(new BorderLayout(10,0)); 
		panel1=new JPanel();panel2=new JPanel();
		panel1_1=new JPanel();panel1_2=new JPanel();panel1_3=new JPanel();panel1_4=new JPanel();
		panel1_5=new JPanel();
		panel1.setLayout(new GridLayout(0, 1, 10, 20));//多行一列
		
		JLabel sex=new JLabel("账号:"); 
		JLabel  min_agelabel=new JLabel("密码:"); 
		JLabel  max_agelabel=new JLabel("姓名:"); 
		JLabel  majorl_abel=new JLabel("性别:"); 
		JLabel  bookPriceLabel=new JLabel("电话:"); 
	   
	      
	    JTextField number_=new JTextField(15); 
	    JTextField name_=new JTextField(15); 
	    JTextField sex_=new JTextField(15); 
	    JTextField age_=new JTextField(15); 
	    JTextField major_=new JTextField(15); 
	   panel1_1.add(sex); panel1_1.add(number_);
	   panel1_2.add(min_agelabel);  panel1_2.add(name_);
	   panel1_3.add(max_agelabel); panel1_3.add(sex_);
	   panel1_4.add(majorl_abel); panel1_4.add(age_);
	   panel1_5.add(bookPriceLabel); panel1_5.add(major_);
	   panel1.add(panel1_1);
	   panel1.add(panel1_2);
	   panel1.add(panel1_3);
	   panel1.add(panel1_4);
	   panel1.add(panel1_5);
	   JButton submitBut=new JButton("确认"); 
	   JButton cancelBut=new JButton("取消"); 
	    
	    panel2.add(submitBut);panel2.add(cancelBut);
		panel.add(panel1,BorderLayout.CENTER);
		panel.add(panel2,BorderLayout.SOUTH);
		f.getContentPane().add(panel);  //将面板添加到容器上
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		f.setBounds(100,100,100,400);//设置窗口出现的位置
		f.setSize(400,300);//窗口尺寸
		f.setVisible(true);
		submitBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				String s=numtext.getText();
				String m=logtext.getText();
				Drug1.InsertLog(number_.getText(),name_.getText(),sex_.getText(),age_.getText(),major_.getText());
				JOptionPane.showMessageDialog(table,"注册成功");
				f.setVisible(false);
				   
				   
				   }			     
		 });
		
		cancelBut.addActionListener(new ActionListener() {
			   public void actionPerformed(ActionEvent e){
				   f.setVisible(false);
			   }
			        
		 });
	}
	void raven(String id) throws UnknownHostException {
		int i=0;
			 Connection conn = Drug1.getMysqlConn();
				PreparedStatement ps = null;
			   try {
					String sql="select * from loging where account=?";
					ps=conn.prepareStatement(sql);
					ps.setObject(1, id);
					 ResultSet rs = ps.executeQuery();
						while(rs.next()){
							System.out.println("账号:"+rs.getString(1));
							System.out.println("密码:"+rs.getString(2));
							p1=rs.getString(1);
							   p2=rs.getString(2);
							  p3=rs.getString(3);
							  p4=rs.getString(4);
							   p5=rs.getString(5);
							   p6=rs.getInt(6);
							//System.out.println("ID————>"+p6);
							//System.out.println("密码————>"+p5);

						}
						System.out.println(p1+p2+p3+p4+p5);
						if(p2.equals(logtext.getText())&&p6!=1) {
							 Drug_00 wang =new Drug_00();
							  wang.update1();
							  log(p1,"用户");
							  setVisible(false);
						}else if(p2.equals(logtext.getText())&&p6==1) {
							Admin ruiwwen=new Admin();
							ruiwwen.update1();
							 log(p1, "管理员");
							setVisible(false);
							
						}else {
							new Drug_00().setVisible(false);
							JOptionPane.showMessageDialog(table,"密码错误");
						}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}finally {
					Drug1.close(ps, conn);
				}
	}
	/**
	 * 插入
	 * @param A
	 * @param B
	 * @throws UnknownHostException
	 */
	void log(String A,String B) throws UnknownHostException {
		InetAddress addr=InetAddress.getLocalHost();
		//返回192.168.43.236
		String s=addr.getHostAddress();
		String m=addr.getHostName();
		//System.out.println(s);
		//获取登录时间
		df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String abc=df.format(new Date());
		//链接数据库
		Connection conn = Drug1.getMysqlConn();
		PreparedStatement ps = null;
	   try {
			String sql="insert into Logdate(username,type,ip,logdate,dropdate) values (?,?,?,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setObject(1, A);
			ps.setObject(2,B);
			ps.setObject(3,s);
			ps.setObject(4,abc);
			ps.setObject(5,m);
			//ps.setObject(6, F);
           ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			Drug1.close(ps, conn);
		}
	}
	public static void main(String[] args) {
		new Loging();
	}
	
	}
	


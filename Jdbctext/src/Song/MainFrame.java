package Song;

import java.awt.*;
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

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Homework.Admin;
import Homework.Drug1;
import Homework.Drug_00;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JPanel contentPane;
	private static CardLayout cardLayout;

	private JTextField userNameField;
	private JPasswordField passwordField;
	
	static String[][] ss=new String[1024][5] ;
	 private JTable table = null;
	 String p1,p2,p3,p4,p5;
	 int p6;

	public static void main(String[] args) {
		MainFrame f=new MainFrame();
		f.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		super("");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 482, 485);

		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		cardLayout = new CardLayout();
		contentPane.setLayout(cardLayout);

		JPanel loginPanel = new JPanel();
		contentPane.add(loginPanel, "loginPanel");
		loginPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("药品管理系统\n" + "\n" + "");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 24, 436, 34);
		loginPanel.add(lblNewLabel);

		userNameField = new JTextField();
		userNameField.setBounds(114, 134, 237, 34);
		loginPanel.add(userNameField);
		userNameField.setColumns(10);

		JLabel lblUsername = new JLabel("账号");
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setFont(new Font("宋体", Font.PLAIN, 20));
		lblUsername.setBounds(114, 89, 237, 34);
		loginPanel.add(lblUsername);

		JLabel lblPasword = new JLabel("密码");
		lblPasword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPasword.setFont(new Font("宋体", Font.PLAIN, 20));
		lblPasword.setBounds(114, 179, 237, 34);
		loginPanel.add(lblPasword);

		passwordField = new JPasswordField();
		passwordField.setBounds(114, 224, 237, 34);
		loginPanel.add(passwordField);
		JButton btnNewButton = new JButton("登录");
		/**
		 * 登录的监听事件
		 */
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String userName = userNameField.getText();
				try {
					raven(userName);
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(114, 269, 237, 34);
		loginPanel.add(btnNewButton);

		JPanel allPanel = new JPanel();
		contentPane.add(allPanel, "allPanel");
		allPanel.setLayout(new GridLayout(1, 0, 0, 0));

		JTabbedPane allOptionPane = new JTabbedPane(JTabbedPane.TOP);
		allPanel.add(allOptionPane);
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
							p1=rs.getString(1);
							   p2=rs.getString(2);
							  p3=rs.getString(3);
							  p4=rs.getString(4);
							  p5=rs.getString(5);
							  p6=rs.getInt(6);
						}
						String pass = new String(passwordField.getPassword());
						//System.out.println(pass);
						if(p2.equals(pass)&&p6!=1) {
							log(p1,"用户");
							 Song wang =new Song();
							  setVisible(false);
						}else if(p2.equals(pass)&&p6==1){
							log(p1,"管理员");
							Loged f=new Loged();
							Loged.update1();
							setVisible(false);
							
							
							
						}else {
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
		//System.out.println(s);
		//获取登录时间
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String abc=df.format(new Date());
		//链接数据库
		Connection conn = Drug1.getMysqlConn();
		PreparedStatement ps = null;
	   try {
			String sql="insert into Logdate(username,type,ip,logdate) values (?,?,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setObject(1, A);
			ps.setObject(2,B);
			ps.setObject(3,s);
			ps.setObject(4,abc);
			//ps.setObject(5,E);
			//ps.setObject(6, F);
           ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			Drug1.close(ps, conn);
		}
	}
	
        	
	}

		
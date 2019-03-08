package Jdbc2;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import java.awt.GridLayout;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Test111 extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static StudentManager sm;
	private static JPanel contentPane;
	private static CardLayout cardLayout;

	private JTextField userNameField;
	private JPasswordField passwordField;
	private JTextField t1;
	private JTextField t2;
	private JTextField t4;
	private JTextField t5;
	private JTextField td;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	private JTextField textField_15;
	private JTextField tu0;

	private static String 账号 = "";
	private static String 密码 = "";
	private JTextField t3;
	private static JTable table;
	String[][] ss=new String[1024][5] ; 

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test111 frame = new Test111();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Test111() {
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

		JLabel lblNewLabel = new JLabel("学生管理系统\n" + "\n" + "");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 24, 436, 34);
		loginPanel.add(lblNewLabel);

		userNameField = new JTextField();
		userNameField.setBounds(114, 134, 237, 34);
		loginPanel.add(userNameField);
		userNameField.setColumns(10);

		JLabel lblUsername = new JLabel("账号（无）");
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setFont(new Font("宋体", Font.PLAIN, 20));
		lblUsername.setBounds(114, 89, 237, 34);
		loginPanel.add(lblUsername);

		JLabel lblPasword = new JLabel("密码（无）");
		lblPasword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPasword.setFont(new Font("宋体", Font.PLAIN, 20));
		lblPasword.setBounds(114, 179, 237, 34);
		loginPanel.add(lblPasword);

		passwordField = new JPasswordField();
		passwordField.setBounds(114, 224, 237, 34);
		loginPanel.add(passwordField);

		JButton btnNewButton = new JButton("登录");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String userName = userNameField.getText();
				String pass = new String(passwordField.getPassword());
				if (userName.equals(账号) && pass.equals(密码)) {
					showPanel("allPanel");
					return;
				}
				JOptionPane.showMessageDialog(null, "UserName & Password MisMatch!");

			}
		});
		btnNewButton.setBounds(114, 269, 237, 34);
		loginPanel.add(btnNewButton);

		JPanel allPanel = new JPanel();
		contentPane.add(allPanel, "allPanel");
		allPanel.setLayout(new GridLayout(1, 0, 0, 0));

		JTabbedPane allOptionPane = new JTabbedPane(JTabbedPane.TOP);
		allPanel.add(allOptionPane);

		JPanel showPanel = new JPanel();
		allOptionPane.addTab("所有学生信息", null, showPanel, null);
		showPanel.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		showPanel.add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		scrollPane.setViewportView(table);

		JPanel addStudentPanel = new JPanel();
		allOptionPane.addTab("添加学生信息", null, addStudentPanel, null);
		addStudentPanel.setLayout(null);

		t1 = new JTextField();
		t1.setBounds(182, 44, 259, 31);
		addStudentPanel.add(t1);
		t1.setColumns(10);

		t2 = new JTextField();
		t2.setColumns(10);
		t2.setBounds(182, 86, 259, 31);
		addStudentPanel.add(t2);

		t3 = new JTextField();
		t3.setColumns(10);
		t3.setBounds(182, 128, 259, 31);
		addStudentPanel.add(t3);

		t4 = new JTextField();
		t4.setColumns(10);
		t4.setBounds(182, 170, 259, 31);
		addStudentPanel.add(t4);

		t5 = new JTextField();
		t5.setColumns(10);
		t5.setBounds(182, 212, 259, 31);
		addStudentPanel.add(t5);

		JLabel lblNewLabel_1 = new JLabel("学号");
		lblNewLabel_1.setBounds(20, 44, 152, 31);
		addStudentPanel.add(lblNewLabel_1);

		JLabel lblName = new JLabel("姓名");
		lblName.setBounds(20, 86, 152, 31);
		addStudentPanel.add(lblName);

		JLabel lblFatherName = new JLabel("学院");
		lblFatherName.setBounds(20, 128, 152, 31);
		addStudentPanel.add(lblFatherName);

		JLabel lblCollege = new JLabel("专业");
		lblCollege.setBounds(20, 170, 152, 31);
		addStudentPanel.add(lblCollege);

		JLabel lblPhoneNumber = new JLabel("电话号码");
		lblPhoneNumber.setBounds(20, 212, 152, 31);
		addStudentPanel.add(lblPhoneNumber);

		JButton btnNewButton_3 = new JButton("提交");
		
		////////////////////////////////////////////////////////////////////////////
		////添加学生信息按钮监听事件
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int s1 = Integer.parseInt(t1.getText());
				String s2 = t2.getText();
				String s3 = t3.getText();
				String s4 = t4.getText();
				String s5 = t5.getText();

				Student student = new Student(s1, s2, s3, s4, s5);
				sm.addStudent(student);
				sm.updateDatabase();

				JOptionPane.showMessageDialog(null, "添加成功！");

			}
		});
		
		
		btnNewButton_3.setBounds(310, 254, 131, 31);
		addStudentPanel.add(btnNewButton_3);

		JPanel deleteStudentPane = new JPanel();
		allOptionPane.addTab("删除", null, deleteStudentPane, null);
		deleteStudentPane.setLayout(null);

		td = new JTextField();
		td.setColumns(10);
		td.setBounds(68, 149, 298, 31);
		deleteStudentPane.add(td);

		JLabel lblEnterId_1 = new JLabel("输入学号");
		lblEnterId_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterId_1.setFont(new Font("宋体", Font.PLAIN, 18));
		lblEnterId_1.setBounds(68, 107, 298, 31);
		deleteStudentPane.add(lblEnterId_1);
		
/////////////////////////////////////////////////////////////////
		/*
		 * 删除按钮监听事件
		 */
		JButton btnNewButton_2 = new JButton("删除");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int x = Integer.parseInt(td.getText());
				sm.deleteStudent(new Student(x));
				sm.updateDatabase();

				JOptionPane.showMessageDialog(null, "删除成功");

			}
		});
		
		
		
		
		
		
		
		btnNewButton_2.setBounds(68, 191, 298, 38);
		deleteStudentPane.add(btnNewButton_2);

		JPanel updateStudent = new JPanel();
		allOptionPane.addTab("修改信息", null, updateStudent, null);
		updateStudent.setLayout(null);

		textField_11 = new JTextField();
		textField_11.setColumns(10);
		textField_11.setBounds(182, 132, 259, 31);
		updateStudent.add(textField_11);

		textField_12 = new JTextField();
		textField_12.setColumns(10);
		textField_12.setBounds(182, 174, 259, 31);
		updateStudent.add(textField_12);

		textField_13 = new JTextField();
		textField_13.setColumns(10);
		textField_13.setBounds(182, 216, 259, 31);
		updateStudent.add(textField_13);

		textField_14 = new JTextField();
		textField_14.setColumns(10);
		textField_14.setBounds(182, 258, 259, 31);
		updateStudent.add(textField_14);

		textField_15 = new JTextField();
		textField_15.setColumns(10);
		textField_15.setBounds(182, 300, 259, 31);
		updateStudent.add(textField_15);

		JLabel label_5 = new JLabel("学号");
		label_5.setBounds(20, 132, 152, 31);
		updateStudent.add(label_5);

		JLabel label_6 = new JLabel("姓名");
		label_6.setBounds(20, 174, 152, 31);
		updateStudent.add(label_6);

		JLabel label_7 = new JLabel("学院");
		label_7.setBounds(20, 216, 152, 31);
		updateStudent.add(label_7);

		JLabel label_8 = new JLabel("专业");
		label_8.setBounds(20, 258, 152, 31);
		updateStudent.add(label_8);

		JLabel label_9 = new JLabel("电话号码");
		label_9.setBounds(20, 300, 152, 31);
		updateStudent.add(label_9);

		JLabel lblEnterId = new JLabel("输入学号");
		lblEnterId.setBounds(20, 50, 152, 31);
		updateStudent.add(lblEnterId);

		tu0 = new JTextField();
		tu0.setColumns(10);
		tu0.setBounds(182, 50, 259, 31);
		updateStudent.add(tu0);
//////////////////////////////////////////////////////////////
		/**
		 * 修改按钮监听事件
		 */
		JButton btnNewButton_1 = new JButton("提交");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int id = Integer.parseInt(tu0.getText());
				Student before = new Student(id);

				int s1 = Integer.parseInt(textField_11.getText());
				String s2 = textField_12.getText();
				String s3 = textField_13.getText();
				String s4 = textField_14.getText();
				String s5 = textField_15.getText();
				Student after = new Student(s1, s2, s3, s4, s5);

				sm.updateStudent(before, after);
				sm.updateDatabase();

				JOptionPane.showMessageDialog(null, "更改成功");

			}
		});
		
		
		btnNewButton_1.setBounds(304, 342, 137, 38);
		updateStudent.add(btnNewButton_1);
		
		JPanel deleteStudentPane1 = new JPanel();
		allOptionPane.addTab("查询", null, deleteStudentPane1, null);
		deleteStudentPane1.setLayout(null);

		td = new JTextField();
		td.setColumns(10);
		td.setBounds(68, 149, 298, 31);
		deleteStudentPane1.add(td);

		JLabel lblEnterId_4 = new JLabel("输入学号");
		lblEnterId_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterId_1.setFont(new Font("宋体", Font.PLAIN, 18));
		lblEnterId_1.setBounds(68, 107, 298, 31);
		deleteStudentPane.add(lblEnterId_1);

		JButton btnNewButton_4 = new JButton("查询");
		
		
		//////////////////////////////////////////////////
		/**
		 * 查询按钮监听事件
		 */
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int x = Integer.parseInt(td.getText());
				sm.sarchStudent(new Student(x));

				sm.updateDatabase();

			}
		});
		btnNewButton_4.setBounds(68, 191, 298, 38);
		deleteStudentPane1.add(btnNewButton_4);

		JLabel lblNewLabel_2 = new JLabel("谁的信息将被更新？");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(20, 11, 421, 28);
		updateStudent.add(lblNewLabel_2);

		JLabel lblEnterUpdatedInformation = new JLabel("输入更新信息\n" + 
				"\n" + 
				"");
		lblEnterUpdatedInformation.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterUpdatedInformation.setBounds(20, 92, 421, 28);
		updateStudent.add(lblEnterUpdatedInformation);

		sm = new StudentManager();
		sm.initialize();

		refreshTableModel();
		
	}
	

	public static void showPanel(String panelName) {
		cardLayout.show(contentPane, panelName);
	}

	public static void refreshTableModel() {
		table.setModel(sm.getTableModel());
	}
}

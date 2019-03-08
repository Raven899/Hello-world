package Homework;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javafx.scene.control.ComboBox;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class City {

	private JFrame frame;
	private JTextField names;
	private JTextField sex;
	private JTextField birthdays;
	private JTextField IDcard;
	private JComboBox comboBox;
	static Connection conn=null;
	static PreparedStatement ps=null;
    static ResultSet rs=null;
	 static String[][] ss=new String[1024][6] ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					City window = new City();
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
	public City() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 736, 554);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel name = new JLabel("����");
		name.setFont(new Font("����", Font.PLAIN, 25));
		name.setBounds(235, 52, 72, 27);
		frame.getContentPane().add(name);
		
		names = new JTextField();
		names.setBounds(321, 49, 113, 30);
		frame.getContentPane().add(names);
		names.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("�Ա�");
		lblNewLabel_1.setFont(new Font("����", Font.PLAIN, 25));
		lblNewLabel_1.setBounds(235, 101, 72, 37);
		frame.getContentPane().add(lblNewLabel_1);
		
		sex = new JTextField();
		sex.setBounds(321, 110, 113, 30);
		frame.getContentPane().add(sex);
		sex.setColumns(10);
		
		//******
		 Chooser ser = Chooser.getInstance();
	        javax.swing.JTextField text = new JTextField();
	        text.setBounds(321, 298, 148, 30);
	        text.setText("");
	        ser.register(text);
	        frame.getContentPane().add(text);
		//*****
//		birthdays = new JTextField();
//		birthdays.setBounds(321, 298, 148, 30);
		//frame.getContentPane().add(birthdays);
		
		JLabel lblNewLabel_2 = new JLabel("��������");
		lblNewLabel_2.setFont(new Font("����", Font.PLAIN, 25));
		lblNewLabel_2.setBounds(187, 296, 120, 27);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("����");
		lblNewLabel_3.setFont(new Font("����", Font.PLAIN, 25));
		lblNewLabel_3.setBounds(235, 172, 50, 27);
		frame.getContentPane().add(lblNewLabel_3);
		
		JButton btnNewButton = new JButton("¼��");
		btnNewButton.setFont(new Font("����", Font.PLAIN, 25));
		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String A=names.getText();
				String B=sex.getText();
				//String C=(String) comboBox.getSelectedItem();
				String C =  String.valueOf(comboBox.getSelectedItem());
				String D=IDcard.getText();
				String E=text.getText();
				InsertData(A, B, C, D, E);
				
			}
		});
		btnNewButton.setBounds(194, 372, 89, 37);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("���");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				names.setText(null);
				sex.setText(null);
				((JTextField) comboBox.getEditor().getEditorComponent()).setText(null);
				IDcard.setText(null);
				text.setText(null);
				
				
			}
		});
		btnNewButton_1.setFont(new Font("����", Font.PLAIN, 25));
		btnNewButton_1.setBounds(356, 372, 95, 37);
		frame.getContentPane().add(btnNewButton_1);
		
		comboBox = new JComboBox();
		
		//*****************************************************************/
		/**
		 * getEditor() ������ JComboBox�ֶ������ڻ��ƺͱ༭��ѡ��Ŀ�ı༭����
		 * getEditorComponent() ����Ӧ��ӵ��˱༭��������νṹ�е����
		 */
		comboBox.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String str = ((JTextField) comboBox.getEditor().getEditorComponent()).getText();
				int i = comboBox.getSelectedIndex();
			    	 Search01(str);
					((JTextField) comboBox.getEditor().getEditorComponent()).setText(str);
				
			}
		});
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				int i = comboBox.getSelectedIndex();
				if(-1!=i) {
					Sheet1 selected = (Sheet1) comboBox.getSelectedItem();				
				}
			}
		});	
		//////////////////////////////////////////////////////////////////////////////////
		comboBox.setEditable(true);
		comboBox.setBounds(321, 174, 148, 30);
		frame.getContentPane().add(comboBox);
		
		JLabel lblNewLabel_4 = new JLabel("���֤��");
		lblNewLabel_4.setFont(new Font("����", Font.PLAIN, 25));
		lblNewLabel_4.setBounds(187, 229, 120, 37);
		frame.getContentPane().add(lblNewLabel_4);
		IDcard = new JTextField();
		IDcard.setBounds(321, 236, 148, 30);
		frame.getContentPane().add(IDcard);
	}
	
	
	
	public void Search01(String str) {
		DefaultComboBoxModel<Object> model = (DefaultComboBoxModel<Object>) comboBox.getModel();
		int items = comboBox.getItemCount();
		if (items > 0) {
			comboBox.removeAllItems();
			}
		 Connection conn = Drug1.getMysqlConn();
			PreparedStatement ps = null;
			
		   try {
				String sql1="select MergerName from sheet1 where ID like ?";
				String sql2="select MergerName from sheet1 where Pinyin like ?";
				
				//ʹ��String.charAt(index)������ֵΪchar��
				//���Եõ�String��ĳһָ��λ�õ�char
				
				char index=str.charAt(0);
				/**
				 * Character.isDigit( char ch )
				 * �ж�ch�Ƿ��������ַ�����'1'��'2����
				 * �Ƿ���true�����򷵻�false
				 */
				if(Character.isDigit(index)) {    //Character.isDigit( char ch )
					ps=conn.prepareStatement(sql1);
				}else {
					ps=conn.prepareStatement(sql2);
				}

				ps.setObject(1, "%"+str+"%");
				 ResultSet rs = ps.executeQuery();
				 
					while(rs.next()){
						
						Sheet1 sh = new Sheet1();
						sh.setMergerName(rs.getString(1));
						model.addElement(sh);	   						
					}
					comboBox.getSelectedIndex();
					comboBox.showPopup();//������Ͽ���ʾ�䵯������
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally {
				Drug1.close(ps, conn);
			}
	}
	/**
	 * ¼����Ϣ�����õķ���
	 * �����ݿ������Ϣ
	 */
	public static void InsertData(String A,String B,String C,String D,String E) {
		conn=Drug1.getMysqlConn();
		try {
			String sql="insert into sheet (name,sex,birthplace,idcard,birthday) values (?,?,?,?,?) ";
			ps=conn.prepareStatement(sql);
			ps.setObject(1, A);
			ps.setObject(2,B);
			ps.setObject(3,C);
			ps.setObject(4,D);
			ps.setObject(5,E);
           ps.execute();
			JOptionPane.showMessageDialog(null, "¼��ɹ�");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			Drug1.close(ps, conn);
		}
	}
	/**
	 * *�����ַ���unicode����ķ�Χ��
     * ���֣�[0x4e00,0x9fa5]����ʮ����[19968,40869]��
     * ���֣�[0x30,0x39]����ʮ����[48, 57]��
     *Сд��ĸ��[0x61,0x7a]����ʮ����[97, 122]��
     * ��д��ĸ��[0x41,0x5a]����ʮ����[65, 90]��
	 * @param str
	 * @return
	 */
	public static boolean isLetterDigitOrChinese(String str) {
		  String regex = "^[a-z0-9A-Z\u4e00-\u9fa5]+$";//������Ҫ��ֱ���޸�������ʽ�ͺ�
		  return str.matches(regex);
		 }
}

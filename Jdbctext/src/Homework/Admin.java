package Homework;
import java.awt.*;  
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Homework.Drug1;

import java.awt.event.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
public class Admin extends JFrame implements ActionListener{  
  private JLabel numberlabel; 
  private JTextField numbertext,sextext; 
  private JButton numbersearch; 
  private JButton addBut; 
  private JButton modifyBut; 
  private JButton deleteBut; 
  private JButton otherBut; 
  private JButton refresh;
  private static DefaultTableModel model = null;
  private JTable table = null;
  static Connection conn=null;
	static PreparedStatement ps=null;
  static ResultSet rs=null;
   static int j=0;
   int k=-1;
   //static String drug_01 = null;
   //j�Ĺ����Ǽ�¼�ļ��е������ܸ�����Ϊȫ�ֱ���
   //k����������Ӻ��޸Ĺ�������ͬһ����壬k=-1Ϊ��ӹ��ܡ�k=index�����޸ĵ��кţ���Ϊ�޸Ĺ���
   static String[][] ss=new String[1024][5] ; //���ļ��ж�ȡ���ݵ���������
  
  public static void main(String[] args)  { 
	  
	  Admin wang =new Admin();
	 
	  wang.update1();
  } 

  public Admin() { 
    //�����������ϵĿؼ� 
	super("ҩƷ��Ϣ����ϵͳ");
  
    numbersearch=new JButton("���֪ͨ"); 
    modifyBut=new JButton("ɾ��֪ͨ"); 
    deleteBut=new JButton("��ӹ���Ա"); 
    addBut = new JButton("�޸�֪ͨ");
  refresh = new JButton("�˳�ϵͳ");
 
    numbersearch.addActionListener(this); 
    addBut.addActionListener(this); 
   // otherBut.addActionListener(this); 
    modifyBut.addActionListener(this); 
    deleteBut.addActionListener(this); 
    refresh.addActionListener(this);
	//����jtable�б�
	String[][] datas = {};
	String[] titles = {"�û���", "�˻����", "IP��ַ", "��¼ʱ��", "������" };
	model = new DefaultTableModel(datas, titles);
	table = new JTable(model);
	table.setBackground(Color.ORANGE);
     
/////////////////////////////////////////////////////////////////////////////////////////////////////////////	     
	JScrollPane  bookScroll=new JScrollPane(table);	      
    Container con1=new Container(); 
    con1.setLayout(new FlowLayout()); 

    Container con2=new Container(); 
    con2.setLayout(new FlowLayout());
    con2.add(numbersearch); 
    con2.add(addBut); 
    con2.add(modifyBut); 
    con2.add(deleteBut); 
    con2.add(refresh); 
  
   this.setLayout(new BorderLayout()); 
   this.add(con1,BorderLayout.NORTH); 
   this.add(bookScroll,BorderLayout.CENTER); 
   this.add(con2,BorderLayout.SOUTH); 
   
/////////////////////////////////////////////////////////////////////////////////////////////////////////////  
   
//�˳��¼�����
   refresh.addActionListener(new ActionListener() {
	   public void actionPerformed(ActionEvent e){
		   new Loging();
		   setVisible(false);
		   dropdate();
	   }   	   
   });
		/////////////////////////////////////////////////////////////////////////////////////////////////
		
	
		////////////////////////////////////////////////////////////////////////
		
  
 
//���֪ͨ�¼�����
	numbersearch.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e){	
//			
		fbtext();
		}    
	});
//�޸�֪ͨ��ť�¼�����			     
	addBut.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e){	
			updatefbtext();
			
		}
	});	 	
//ɾ��֪ͨ��ť�����¼�
	modifyBut.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e){	
			//int index = table.getSelectedRow();
			Delect();
			
		}
	});	
	
	
//��ӹ���Ա��ť�����¼�	
	deleteBut.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e){	 
			addPeople();

			
			
		}		   
	 }); 	

	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	this.setBounds(350, 150, 900, 500);    
	this.validate(); 
	this.setVisible(true);   
  } 
  	
	protected void MainFrame() {
	// TODO Auto-generated method stub
	
}

	@Override
	public void actionPerformed(ActionEvent arg0) {
	
		
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////		
		/**
		 * ˢ���б�
		 */
		static void update1() {
			 
				   Connection conn = Drug1.getMysqlConn();
					PreparedStatement ps = null;
				   try {
						String sql="select * from logdate";
						ps=conn.prepareStatement(sql);
	
						//ps.setObject(1, user_name);
						 ResultSet rs = ps.executeQuery();
							while(rs.next()){
								 ss[j][0]=rs.getString(1);
								   ss[j][1]=rs.getString(2);
								   ss[j][2]=rs.getString(3);
								   ss[j][3]=rs.getString(4);
								   ss[j][4]=rs.getString(5);
								   //ss[j][5]=rs.getString(6);
								   model.addRow(new String[] { ss[j][0],  ss[j][1], ss[j][2] , ss[j][3], ss[j][4] });

								   j++;	
								
								
							}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}finally {
						Drug1.close(ps, conn);
					}
//				  
		}
		
		////////////////////////////////////////////////////////////////////////////////////////
		//�����˳�ϵͳʱ��
		void dropdate() {
			//��ȡ�˳�ʱ��
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String abc=df.format(new Date());
			//�������ݿ�
			Connection conn = Drug1.getMysqlConn();
			PreparedStatement ps = null;
		   try {
				String sql="update logdate set dropdate=? where logdate=?";
				ps=conn.prepareStatement(sql);
				ps.setObject(1, abc);
				GetID f=new GetID(new Loging().abc);
				String s=f.getID();
				System.out.println("ID:"+s);
				ps.setObject(2,s);
	           ps.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				Drug1.close(ps, conn);
			}
		}
		/**
		 * ������Ϣ
		 */
		void fbtext() {
			JFrame frame;
			JTextField textField;
			JTextField textField_1;
			frame = new JFrame();
			frame.getContentPane().setBackground(Color.LIGHT_GRAY);
			frame.setBounds(100, 100, 605, 608);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().setLayout(null);
			
			textField = new JTextField();
			textField.setBounds(195, 92, 179, 37);
			frame.getContentPane().add(textField);
			textField.setColumns(10);
			
			JLabel lblNewLabel = new JLabel("\u4E3B\u9898");
			lblNewLabel.setFont(new Font("����", Font.PLAIN, 29));
			lblNewLabel.setBounds(123, 89, 72, 40);
			frame.getContentPane().add(lblNewLabel);
			
			JLabel lblNewLabel_1 = new JLabel("\u53D1\u5E03\u8005");
			lblNewLabel_1.setFont(new Font("����", Font.PLAIN, 28));
			lblNewLabel_1.setBounds(103, 148, 138, 42);
			frame.getContentPane().add(lblNewLabel_1);
			
			textField_1 = new JTextField();
			textField_1.setBounds(195, 156, 179, 37);
			frame.getContentPane().add(textField_1);
			textField_1.setColumns(10);
			
			JButton button = new JButton("\u63D0\u4EA4");
			button.setBounds(459, 440, 87, 27);
			frame.getContentPane().add(button);
			
			JButton btnNewButton = new JButton("\u53D6\u6D88");
			btnNewButton.setBounds(459, 480, 87, 27);
			frame.getContentPane().add(btnNewButton);
			
			JTextArea textArea = new JTextArea();
			textArea.setRows(10);
			textArea.setLineWrap(true);

			textArea.setBounds(88, 225, 349, 290);
			frame.getContentPane().add(textArea);
			frame.setVisible(true);
			textField_1.setText("����Ա");
			button.addActionListener(new ActionListener() {
				   public void actionPerformed(ActionEvent e){
					
					   conn=Drug1.getMysqlConn();
						try {
							String sql="insert into data (text,username,data) values (?,?,?) ";
							ps=conn.prepareStatement(sql);
							ps.setObject(1, textField.getText());
							ps.setObject(2,textField_1.getText());
							ps.setObject(3,textArea.getText());
							
			               ps.execute();
			               JOptionPane.showMessageDialog(table,"�޸ĳɹ�");
						   frame.setVisible(false);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}finally {
							Drug1.close(ps, conn);
						}
					}
				        
			 });
			btnNewButton.addActionListener(new ActionListener() {
				   public void actionPerformed(ActionEvent e){
					   frame.setVisible(false);
					   
				   }
				   });
			
		}
		/**
		 * ��ӹ���Ա
		 */
		void addPeople() {
			JFrame f=new JFrame("");
			JPanel panel1,panel2,panel1_1,panel1_2;

			JPanel panel=new JPanel();
			
			panel.setLayout(new BorderLayout(10,0)); 
			panel1=new JPanel();panel2=new JPanel();
			panel1_1=new JPanel();panel1_2=new JPanel();//panel1_3=new JPanel();panel1_4=new JPanel();
			panel1.setLayout(new GridLayout(0, 1, 10, 20));//����һ��
			
			JLabel sex=new JLabel("�˺�:");		      
		    JTextField number_=new JTextField(15); 

		   panel1_1.add(sex); 
		   panel1_1.add(number_);

		   panel1.add(panel1_1);

		   JButton submitBut=new JButton("ȷ��"); 
		   JButton cancelBut=new JButton("ȡ��"); 
		    
		    panel2.add(submitBut);panel2.add(cancelBut);
			panel.add(panel1,BorderLayout.CENTER);
			panel.add(panel2,BorderLayout.SOUTH);
			f.getContentPane().add(panel);  //�������ӵ�������
			f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
			f.setBounds(100,100,100,400);//���ô��ڳ��ֵ�λ��
			f.setSize(200,200);//���ڳߴ�
			f.setVisible(true);
			submitBut.addActionListener(new ActionListener() {
				   public void actionPerformed(ActionEvent e){
					   String s=number_.getText();
					   Connection conn = Drug1.getMysqlConn();
						PreparedStatement ps = null;
					   try {
							String sql="update loging set number=? where account=?";
							ps=conn.prepareStatement(sql);
							ps.setObject(1,1);
							ps.setObject(2,s);
							ps.execute();
							model.setRowCount(0);
							update1();
							JOptionPane.showMessageDialog(table,"�޸ĳɹ�");
							f.setVisible(false);

							
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}finally {
							Drug1.close(ps, conn);
						}
				   }
				        
			 });
			cancelBut.addActionListener(new ActionListener() {
				   public void actionPerformed(ActionEvent e){
					   f.setVisible(false);
				   }
				        
			 });
		}
		/**
		 * ɾ��֪ͨ
		 */
		private void Delect(){
			JFrame f=new JFrame("");
			JPanel panel1,panel2,panel1_1,panel1_2;

			JPanel panel=new JPanel();
			
			panel.setLayout(new BorderLayout(10,0)); 
			panel1=new JPanel();panel2=new JPanel();
			panel1_1=new JPanel();panel1_2=new JPanel();//panel1_3=new JPanel();panel1_4=new JPanel();
			panel1.setLayout(new GridLayout(0, 1, 10, 20));//����һ��
			
			JLabel sex=new JLabel("֪ͨ����:");		      
		    JTextField number_=new JTextField(15); 

		   panel1_1.add(sex); 
		   panel1_1.add(number_);

		   panel1.add(panel1_1);

		   JButton submitBut=new JButton("ȷ��"); 
		   JButton cancelBut=new JButton("ȡ��"); 
		    
		    panel2.add(submitBut);panel2.add(cancelBut);
			panel.add(panel1,BorderLayout.CENTER);
			panel.add(panel2,BorderLayout.SOUTH);
			f.getContentPane().add(panel);  //�������ӵ�������
			f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
			f.setBounds(100,100,100,400);//���ô��ڳ��ֵ�λ��
			f.setSize(200,200);//���ڳߴ�
			f.setVisible(true);
			submitBut.addActionListener(new ActionListener() {
				   public void actionPerformed(ActionEvent e){
					   String s=number_.getText();
						
						Drug1.DelectTongZhi(s);
						model.setRowCount(0);
						String drug_01 = null;
						update1();
						JOptionPane.showMessageDialog(table,"ɾ���ɹ�");
						f.setVisible(false);
				   }
				        
			 });
			cancelBut.addActionListener(new ActionListener() {
				   public void actionPerformed(ActionEvent e){
					   f.setVisible(false);
				   }
				        
			 });
			
		}
		/**
		 * �޸�֪ͨ
		 */
		void updatefbtext() {
			JFrame frame;
			JTextField textField;
			JTextField textField_1;
			frame = new JFrame();
			frame.getContentPane().setBackground(Color.LIGHT_GRAY);
			frame.setBounds(100, 100, 605, 608);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().setLayout(null);
			
			textField = new JTextField();
			textField.setBounds(195, 92, 179, 37);
			frame.getContentPane().add(textField);
			textField.setColumns(10);
			
			JLabel lblNewLabel = new JLabel("\u4E3B\u9898");
			lblNewLabel.setFont(new Font("����", Font.PLAIN, 29));
			lblNewLabel.setBounds(123, 89, 72, 40);
			frame.getContentPane().add(lblNewLabel);
			
			JLabel lblNewLabel_1 = new JLabel("\u53D1\u5E03\u8005");
			lblNewLabel_1.setFont(new Font("����", Font.PLAIN, 28));
			lblNewLabel_1.setBounds(103, 148, 138, 42);
			frame.getContentPane().add(lblNewLabel_1);
			
			textField_1 = new JTextField();
			textField_1.setBounds(195, 156, 179, 37);
			frame.getContentPane().add(textField_1);
			textField_1.setColumns(10);
			
			JButton button = new JButton("\u63D0\u4EA4");
			button.setBounds(459, 440, 87, 27);
			frame.getContentPane().add(button);
			
			JButton btnNewButton = new JButton("\u53D6\u6D88");
			btnNewButton.setBounds(459, 480, 87, 27);
			frame.getContentPane().add(btnNewButton);
			
			JTextArea textArea = new JTextArea();
			textArea.setRows(10);
			textArea.setLineWrap(true);

			textArea.setBounds(88, 225, 349, 290);
			frame.getContentPane().add(textArea);
			frame.setVisible(true);
			textField_1.setText("����Ա");
			button.addActionListener(new ActionListener() {
				   public void actionPerformed(ActionEvent e){
						try {
							Connection conn = Drug1.getMysqlConn();
							PreparedStatement ps = null;
						String sql="update data set data=? where text=?";
					   ps=conn.prepareStatement(sql);
					   ps.setObject(1,textArea.getText());
						ps.setObject(2,textField.getText());
						ps.execute();
			               JOptionPane.showMessageDialog(table,"�޸ĳɹ�");
						   frame.setVisible(false);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}finally {
							Drug1.close(ps, conn);
						}
					}
				        
			 });
			btnNewButton.addActionListener(new ActionListener() {
				   public void actionPerformed(ActionEvent e){
					   frame.setVisible(false);
				   }
			});
			
		}
		}
	
		


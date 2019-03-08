package Song;
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
public class Loged extends JFrame implements ActionListener{  
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
	  
	  Loged wang =new Loged();
	 
	  wang.update1();
  } 

  public Loged() { 
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
	String[] titles = { "�û���", "�˻����", "IP��ַ", "��¼ʱ��", "�˳�ʱ��" };
	model = new DefaultTableModel(datas, titles);
	table = new JTable(model);
	table.setBackground(Color.pink);
     
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
		   MainFrame f=new MainFrame();
		   setVisible(false);
		   dropdate();
	   }   	   
   });
		/////////////////////////////////////////////////////////////////////////////////////////////////
		
	
		////////////////////////////////////////////////////////////////////////
		
  
 
//�����¼�����
	numbersearch.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e){	
//			
		fbtext();
		}    
	});
//�޸�֪ͨ��ť�¼�����			     
	addBut.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e){	
			
		}
	});	 	
//ɾ��֪ͨ��ť�����¼�
	modifyBut.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e){	
			int index = table.getSelectedRow();
			
		}
	});	
	
	
//��ӹ���Ա��ť�����¼�	
	deleteBut.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e){	   

			
			
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
								   model.addRow(new String[] { ss[j][0],  ss[j][1], ss[j][2] , ss[j][3], ss[j][4]  });

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
				String sql="insert into Logdate(dropdate) values (?)";
				ps=conn.prepareStatement(sql);
				ps.setObject(1, abc);
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
			frame.getContentPane().setBackground(Color.PINK);
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
			button.setBounds(451, 506, 87, 27);
			frame.getContentPane().add(button);
			
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
			
		}

		}
	
		


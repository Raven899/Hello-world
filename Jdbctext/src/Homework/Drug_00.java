package Homework;
import java.awt.*;  
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Homework.Drug1;

import java.awt.event.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class Drug_00 extends JFrame implements ActionListener{  
  private JLabel numberlabel; 
  private JTextField numbertext,sextext; 
  private JButton numbersearch; 
  private JButton addBut; 
  private JButton modifyBut; 
  private JButton deleteBut; 
  private JButton otherBut; 
  private JButton refresh;
  private JButton accept;
  private JButton drug;
  private JButton drug01;
  private JButton drug02;
  private static DefaultTableModel model = null;
  private JTable table = null;
   static int j=0;
int k=-1;
   //static String drug_01 = null;
   //j�Ĺ����Ǽ�¼�ļ��е������ܸ�����Ϊȫ�ֱ���
   //k����������Ӻ��޸Ĺ�������ͬһ����壬k=-1Ϊ��ӹ��ܡ�k=index�����޸ĵ��кţ���Ϊ�޸Ĺ���
   static String[][] ss=new String[1024][5] ; //���ļ��ж�ȡ���ݵ���������
  
  public static void main(String[] args)  { 
	  
	  Drug_00 wang =new Drug_00();
	 
	  wang.update1();
  } 

  public Drug_00() { 
    //�����������ϵĿؼ� 
	super("ҩƷ��Ϣ����ϵͳ");
    numberlabel=new JLabel("������ҩƷ���:"); 
    numbertext=new JTextField(10); 
  
    numbersearch=new JButton("����Ų�ѯ"); 
    addBut=new JButton("���"); 
    modifyBut=new JButton("�޸�"); 
    deleteBut=new JButton("ɾ��"); 
    otherBut=new JButton("������ѯ��ʽ"); 
    addBut = new JButton("���");
  refresh = new JButton("�鿴֪ͨ");
  drug=new JButton("ҩƷ����");//
  accept=new JButton("��Ӧ�̹���");
  drug01=new JButton("��ⵥ��ͷ");
  drug02=new JButton("��ⵥ��Ŀ");

 
    numbersearch.addActionListener(this); 
    addBut.addActionListener(this); 
    otherBut.addActionListener(this); 
    modifyBut.addActionListener(this); 
    deleteBut.addActionListener(this); 
    refresh.addActionListener(this);
    drug.addActionListener(this);
    accept.addActionListener(this);
    drug01.addActionListener(this);
    drug02.addActionListener(this);
	//����jtable�б�
	String[][] datas = {};
	String[] titles = { "ҩƷ���", "ҩƷ����", "��λ", "��װ", "����" };
	model = new DefaultTableModel(datas, titles);
	table = new JTable(model);
	table.setBackground(Color.pink);
     
/////////////////////////////////////////////////////////////////////////////////////////////////////////////	     
	JScrollPane  bookScroll=new JScrollPane(table);	      
    Container con1=new Container(); 
    con1.setLayout(new FlowLayout()); 

    con1.add(drug);
    con1.add(accept);
    con1.add(drug01);
    con1.add(drug02);
    Container con2=new Container(); 
    con2.setLayout(new FlowLayout());
    con2.add(numberlabel); 
    con2.add(numbertext); 
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
   
//ˢ���б��¼�����
   refresh.addActionListener(new ActionListener() {
	   public void actionPerformed(ActionEvent e){
//		   j=0;//����j=0
//		   model.setRowCount(0);//����б�����
//		   update1();//���÷���
		   watch_fbtext();
	   }   	   
   });
		/////////////////////////////////////////////////////////////////////////////////////////////////
		drug01.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		Drug_03 Drug1=new Drug_03();
		Drug1.update2(); 
		setVisible(false);
		
		
		}
		
		});
		////////////////////////////////////////////////////////////////////////
		//��ⵥ��Ŀ
		drug02.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		Drug_04 Drug=new Drug_04();
		Drug_04.update4();
		setVisible(false);
		
		
		}
		
		});
  
 
//�����¼�����
	numbersearch.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e){	
//			for(int i=0;i<j;i++){ 
//				if(numbertext.getText().equals(ss[i][0])){
//					JOptionPane.showMessageDialog(table,"������Ϣ���£�\n"+"ѧ�ţ�"+ss[i][0]+"\n"+"������"+ss[i][1]+"\n"+"�Ա�"+ss[i][2]+"\n"+"���䣺"+ss[i][3]+"\n"+"רҵ��"+ss[i][4]);
//					 break;
//				}
	//		}
			select1(numbertext.getText());
		}    
	});
//���ѧ����ť�¼�����			     
	addBut.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e){	
			k=-1;//k�Ĺ����Ѿ�������
			q();//����q����
		}
	});	 	
//�޸�ѧ����ť�����¼�
	modifyBut.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e){	
			int index = table.getSelectedRow();
			//k=index;
			q2();
		}
	});	
	//////ҩƷ����ť�����¼�
	drug.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			 Drug_00 wang =new Drug_00();
			 update1();
			 setVisible(false);
			
		}
		
	});
	////////��Ӧ�̹���ť�����¼�
	accept.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			 Drug_02 Drug=new Drug_02();
			 setVisible(false);
			 
			
		}
		
	});
	
//ɾ����ť�����¼�	
	deleteBut.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e){	   

			q1();
			
		}		   
	 }); 	

	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	this.setBounds(350, 150, 900, 500);    
	this.validate(); 
	this.setVisible(true);   
  } 
  	
	@Override
	public void actionPerformed(ActionEvent arg0) {
	
		
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////	

/////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	private void q(){
		JFrame f=new JFrame("");
		JPanel panel1,panel2,panel1_1,panel1_2,panel1_3,panel1_4,panel1_5;
		JPanel panel=new JPanel();
		
		panel.setLayout(new BorderLayout(10,0)); 
		panel1=new JPanel();panel2=new JPanel();
		panel1_1=new JPanel();panel1_2=new JPanel();panel1_3=new JPanel();panel1_4=new JPanel();
		panel1_5=new JPanel();
		panel1.setLayout(new GridLayout(0, 1, 10, 20));//����һ��
		
		JLabel sex=new JLabel("���:"); 
		JLabel  min_agelabel=new JLabel("����:"); 
		JLabel  max_agelabel=new JLabel("��λ:"); 
		JLabel  majorl_abel=new JLabel("��װ:"); 
		JLabel  bookPriceLabel=new JLabel("����:"); 
	   
	      
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
	   JButton submitBut=new JButton("ȷ��"); 
	   JButton cancelBut=new JButton("ȡ��"); 
	    
	    panel2.add(submitBut);panel2.add(cancelBut);
		panel.add(panel1,BorderLayout.CENTER);
		panel.add(panel2,BorderLayout.SOUTH);
		f.getContentPane().add(panel);  //�������ӵ�������
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		f.setBounds(100,100,100,400);//���ô��ڳ��ֵ�λ��
		f.setSize(400,300);//���ڳߴ�
		f.setVisible(true);
		
		
		submitBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){

				   if(k==-1){
					   ss[j][0]=number_.getText();
					   ss[j][1]=name_.getText();
					   ss[j][2]=sex_.getText();
					   ss[j][3]=age_.getText();
					   ss[j][4]=major_.getText();
					   j++;
					   sex.setText(number_.getText());
					 
						 String abc = name_.getText();
						  
					   Drug1.InsertDate8(number_.getText(),name_.getText(),sex_.getText(),age_.getText(),major_.getText());
					   model.setRowCount(0);
					   update1();
					   JOptionPane.showMessageDialog(table,"��ӳɹ���");
					   f.setVisible(false);
				   }else{//����indexΪ�޸Ĺ���
					   ss[k][0]=number_.getText();
					   ss[k][1]=name_.getText();
					   ss[k][2]=sex_.getText();
					   ss[k][3]=age_.getText();
					   ss[k][4]=major_.getText();
					  
					 
					   model.setRowCount(0);
					   JOptionPane.showMessageDialog(table,"�޸ĳɹ�");
					   f.setVisible(false);
				   }
				   
				   }			     
		 });
		
		cancelBut.addActionListener(new ActionListener() {
			   public void actionPerformed(ActionEvent e){
				   f.setVisible(false);
			   }
			        
		 });
	}
	////////////////////////////////////////////////////////////////////////////
	//						String sql="update drug_01 set ҩƷ����=?,��ⵥ���=?,��λ=?,��װ=? where ҩƷ����=?";

	private void q2(){
		JFrame f=new JFrame("");
		JPanel panel1,panel2,panel1_1,panel1_2,panel1_3,panel1_4,panel1_5;
		JPanel panel=new JPanel();
		
		panel.setLayout(new BorderLayout(10,0)); 
		panel1=new JPanel();panel2=new JPanel();
		panel1_1=new JPanel();panel1_2=new JPanel();panel1_3=new JPanel();panel1_4=new JPanel();
		panel1_5=new JPanel();
		panel1.setLayout(new GridLayout(0, 1, 10, 20));//����һ��
		
		JLabel sex=new JLabel("���:"); 
		JLabel  min_agelabel=new JLabel("����:"); 
		JLabel  max_agelabel=new JLabel("��λ:"); 
		JLabel  majorl_abel=new JLabel("��װ:"); 
		JLabel  bookPriceLabel=new JLabel("����:"); 
	   
	      
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
	   JButton submitBut1=new JButton("ȷ��"); 
	   JButton cancelBut=new JButton("ȡ��"); 
	    
	    panel2.add(submitBut1);panel2.add(cancelBut);
		panel.add(panel1,BorderLayout.CENTER);
		panel.add(panel2,BorderLayout.SOUTH);
		f.getContentPane().add(panel);  //�������ӵ�������
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		f.setBounds(100,100,100,400);//���ô��ڳ��ֵ�λ��
		f.setSize(400,300);//���ڳߴ�
		f.setVisible(true);
//		
//		
		submitBut1.addActionListener(new ActionListener() {
			   public void actionPerformed(ActionEvent e){
				
				   Connection conn = Drug1.getMysqlConn();
					PreparedStatement ps = null;
				   try {
						String sql="update drug_01 set ҩƷ����=?,��λ=?,��װ=?,����=? where ҩƷ����=?";
						ps=conn.prepareStatement(sql);
						ps=conn.prepareStatement(sql);
						ps.setObject(1, name_.getText());
						
						ps.setObject(2, sex_.getText());
						ps.setObject(3, age_.getText());
						ps.setObject(4, major_.getText());
						ps.setObject(5, number_.getText());
		               //ps.execute();
						ps.execute();
						model.setRowCount(0);
						update1();
						JOptionPane.showMessageDialog(table,"�޸ĳɹ�");
						f.setVisible(false);

						
								//System.out.println(rs.getInt(1)+"------"+rs.getString(2)+"------"+rs.getString(3));}
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
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////	
		
		//////////////////////////////////////////////////////////////////////////////////////////////
		private void q1(){
			JFrame f=new JFrame("");
			JPanel panel1,panel2,panel1_1,panel1_2;

			JPanel panel=new JPanel();
			
			panel.setLayout(new BorderLayout(10,0)); 
			panel1=new JPanel();panel2=new JPanel();
			panel1_1=new JPanel();panel1_2=new JPanel();//panel1_3=new JPanel();panel1_4=new JPanel();
			panel1.setLayout(new GridLayout(0, 1, 10, 20));//����һ��
			
			JLabel sex=new JLabel("ҩƷ���:");		      
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
						
						Drug1.DelectDate(s);
						//rewrite();
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
/////////////////////////////////////////////////////////////////////////////////////////////////////////////		
		
		static void update1() {
			 
				   Connection conn = Drug1.getMysqlConn();
					PreparedStatement ps = null;
				   try {
						String sql="select * from drug_01";
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
								//System.out.println(rs.getInt(1)+"------"+rs.getString(2)+"------"+rs.getString(3));}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}finally {
						Drug1.close(ps, conn);
					}
//				  
		}
		void select1(String id) {
			 Connection conn = Drug1.getMysqlConn();
				PreparedStatement ps = null;
			   try {
					String sql="select * from Drug_01 where ҩƷ����=?";
					ps=conn.prepareStatement(sql);

					ps.setObject(1, id);
					 ResultSet rs = ps.executeQuery();
						while(rs.next()){
							 int i = 0;
							ss[i][0]=rs.getString(1);
							   ss[i][1]=rs.getString(2);
							   ss[i][2]=rs.getString(3);
							   ss[i][3]=rs.getString(4);
							   ss[i][4]=rs.getString(5);
		JOptionPane.showMessageDialog(table,"��ҩƷ��Ϣ���£�\n"+"��ţ�"+ss[i][0]+"\n"+"���ƣ�"+ss[i][1]+"\n"+"��λ��"+ss[i][2]+"\n"+"��װ��"+ss[i][3]+"\n"+"���ࣺ"+ss[i][4]);	
							
						}
							//System.out.println(rs.getInt(1)+"------"+rs.getString(2)+"------"+rs.getString(3));}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}finally {
					Drug1.close(ps, conn);
				}
//			  
	}
		////////////////////////////////////////////////////////////////////////////////////////	
		/**
		 * �鿴֪ͨ
		 */
		void watch_fbtext() {
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
			
			JTextArea textArea = new JTextArea();
			textArea.setRows(10);
			textArea.setLineWrap(true);
			
			JButton button = new JButton("ȷ��");
			button.setBounds(451, 506, 87, 27);
			frame.getContentPane().add(button);

			textArea.setBounds(88, 225, 349, 290);
			frame.getContentPane().add(textArea);
			frame.setVisible(true);
			String id="����Ա";
			   Connection conn = Drug1.getMysqlConn();
				PreparedStatement ps = null;
			   try {
					String sql="select * from data";
					ps=conn.prepareStatement(sql);
					 ResultSet rs = ps.executeQuery();
						while(rs.next()){
							 int i = 0;
							ss[i][0]=rs.getString(1);
							   ss[i][1]=rs.getString(2);
							   ss[i][2]=rs.getString(3);
							   System.out.println(ss[i][0]);
							   textField.setText(ss[i][0]);
							   textField_1.setText(ss[i][1]);
							   textArea.setText(ss[i][2]);
							
						}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}finally {
					Drug1.close(ps, conn);
				}
		
		button.addActionListener(new ActionListener() {
			   public void actionPerformed(ActionEvent e){	 
				   frame.setVisible(false);
			   }
			   

	});
		
		} 


}
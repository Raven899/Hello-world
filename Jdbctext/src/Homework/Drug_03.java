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
import java.text.SimpleDateFormat;
import java.util.Date;
public class Drug_03 extends JFrame implements ActionListener{  
  private JLabel numberlabel,sexlabel; 
  private JTextField numbertext,sextext; 
  private JButton numbersearch; 
  private JButton addBut; 
  private JButton modifyBut; 
  private JButton deleteBut; 
  private JButton otherBut; 
  private JButton sexbutton;
  private JButton multi_search;
  private JButton refresh;
  private JButton accept;
  private JButton drug;
  private JButton drug01;
  private JButton drug02;
  private static DefaultTableModel model = null;
  private JTable table = null;
   static int j=0;
int k=-1;
   //j�Ĺ����Ǽ�¼�ļ��е������ܸ�����Ϊȫ�ֱ���
   //k����������Ӻ��޸Ĺ�������ͬһ����壬k=-1Ϊ��ӹ��ܡ�k=index�����޸ĵ��кţ���Ϊ�޸Ĺ���
   static String[][] ss=new String[1024][7] ; //���ļ��ж�ȡ���ݵ���������

  public Drug_03() { 
    //�����������ϵĿؼ� 
	super("");
    numberlabel=new JLabel("��������ⵥ���:"); 
    numbertext=new JTextField(10); 
    sexlabel=new JLabel("������:"); 
    sextext=new JTextField(10); 
    sexbutton=new JButton("��ѯ");
    numbersearch=new JButton("����ⵥ��Ų�ѯ"); 
    multi_search=new JButton("��������ѯ"); 
    addBut=new JButton("���"); 
    modifyBut=new JButton("�޸�"); 
    deleteBut=new JButton("ɾ��"); 
    otherBut=new JButton("������ѯ��ʽ"); 
    addBut = new JButton("���");
    drug=new JButton("ҩƷ����");
    accept=new JButton("��Ӧ�̹���");
  refresh = new JButton("ˢ���б�");
  drug01=new JButton("��ⵥ��ͷ");
  drug02=new JButton("��ⵥ��Ŀ");

    //.addActionListener(this); 
    multi_search.addActionListener(this); 
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
	String[] titles = { "��ⵥ���", "�������", "�����Ա", "�ܽ��", "�����"};
	model = new DefaultTableModel(datas, titles);
	table = new JTable(model);
	table.setBackground(Color.gray);
     //update1();
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
    con2.add(sexbutton); 
   
    con2.add(addBut); 
    con2.add(modifyBut); 
    con2.add(deleteBut); 
    con2.add(refresh); 
    
   this.setLayout(new BorderLayout()); 
   this.add(con1,BorderLayout.NORTH); 
   this.add(bookScroll,BorderLayout.CENTER); 
   this.add(con2,BorderLayout.SOUTH); 
   //update2();
   
/////////////////////////////////////////////////////////////////////////////////////////////////////////////  
   
//ˢ���б��¼�����
   refresh.addActionListener(new ActionListener() {
	   public void actionPerformed(ActionEvent e){
		   j=0;//����j=0
		   model.setRowCount(0);//����б�����
		   update2();//���÷���
	   }   	   
   });

//////ҩƷ����ť�����¼�
	drug.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			Drug_00 wang =new Drug_00();
			Drug_00.update1();
			 setVisible(false);
			
		}
		
	});
	////////��Ӧ�̹���ť�����¼�
	accept.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			 Drug_02 Drug=new Drug_02();
			 Drug.update2();
			 setVisible(false);
			 
			
		}
		
	});
/////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * ��ⵥ��ͷ
	 */
	drug01.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			 Drug_03 s=new Drug_03();
			 setVisible(false);
			
		}
		
	});
	/**��ⵥ��Ŀ
	 * 
	 */
	drug02.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			 Drug_04 s=new Drug_04();
			 Drug_04.update4();
			 setVisible(false);
			
		}
		
	});
//////////////////////////////////////////////////////////////////

 //�����¼�����
 	numbersearch.addActionListener(new ActionListener() {
 		public void actionPerformed(ActionEvent e){	

 			select2(numbertext.getText());
 		}    
 	});
 //��Ӱ�ť�¼�����			     
 	addBut.addActionListener(new ActionListener() {
 		public void actionPerformed(ActionEvent e){	
 			k=-1;//k�Ĺ����Ѿ�������
 			adddata();//����q����
 		}
 	});	 	
//�޸İ�ť�����¼�
	modifyBut.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e){	
			int index = table.getSelectedRow();
			//k=index;
			updatenumber();		}
	});	
//ɾ����ť�����¼�	
	deleteBut.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e){	   
//			
//		
			delectable2();
			
		}		   
	 }); 	

	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	this.setBounds(350, 150, 900, 500);    
	this.validate(); 
	this.setVisible(true);   
  } 
  	
	public void actionPerformed1(ActionEvent arg0) {
	
		
	}
	private void updatenumber(){
		JFrame f=new JFrame("");
		JPanel panel1,panel2,panel1_1,panel1_2,panel1_3,panel1_4,panel1_5;
		JPanel panel=new JPanel();
		
		panel.setLayout(new BorderLayout(10,0)); 
		panel1=new JPanel();panel2=new JPanel();
		panel1_1=new JPanel();panel1_2=new JPanel();panel1_3=new JPanel();panel1_4=new JPanel();
		panel1_5=new JPanel();
		panel1.setLayout(new GridLayout(0, 1, 10, 20));//����һ��

		JLabel sex=new JLabel("��ⵥ���:"); 
		JLabel  min_agelabel=new JLabel("�������:"); 
		JLabel  max_agelabel=new JLabel("�����Ա:"); 
		JLabel  majorl_abel=new JLabel("�ܽ��:"); 
		JLabel  bookPriceLabel=new JLabel("���������:"); 
	   
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
//		
//		
		submitBut.addActionListener(new ActionListener() {
			   public void actionPerformed(ActionEvent e){
		
				   Connection conn = Drug1.getMysqlConn();
					PreparedStatement ps = null;
				   try {
						String sql="update drug_03 set �������=?,�����Ա=?,�ܽ��=?,�����=?where ��ⵥ���=?";
						ps=conn.prepareStatement(sql);
	
						//ps.setObject(2, user_name);
						ps=conn.prepareStatement(sql);
						ps.setObject(1, name_.getText());
						ps.setObject(2, sex_.getText());
						ps.setObject(3, age_.getText());
						ps.setObject(4, major_.getText());
						ps.setObject(5, number_.getText());
					
		               //ps.execute();
						ps.execute();
						model.setRowCount(0);
						update2();
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
	/////////////////////////////////////////////////////////////////////////////////////////
	static void update2() {
		 
		   Connection conn = Drug1.getMysqlConn();
			PreparedStatement ps = null;
		   try {
				String sql="select * from drug_03";
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
						   model.addRow(new String[] { ss[j][0],  ss[j][1], ss[j][2] , ss[j][3], ss[j][4] ,ss[j][5] });

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
	////////////////////////////////////////////////////////////////////////
	private void adddata(){
		JFrame f=new JFrame("");
		JPanel panel1,panel2,panel1_1,panel1_2,panel1_3,panel1_4,panel1_5,panel1_6;
		JPanel panel=new JPanel();
		
		panel.setLayout(new BorderLayout(10,0)); 
		panel1=new JPanel();panel2=new JPanel();
		panel1_1=new JPanel();panel1_2=new JPanel();panel1_3=new JPanel();panel1_4=new JPanel();
		panel1_5=new JPanel();panel1_6=new JPanel();
		panel1.setLayout(new GridLayout(0, 1, 10, 20));//����һ��
		//��ⵥ
		JLabel sex=new JLabel("��ⵥ���:"); 
		JLabel  min_agelabel=new JLabel("�������:"); 
		JLabel  max_agelabel=new JLabel("�����Ա:"); 
		JLabel  majorl_abel=new JLabel("�ܽ��:"); 
		JLabel  bookPriceLabel=new JLabel("�����:"); 
		//JLabel  bookPriceLabel1=new JLabel("�������:"); 
	   
	      
	    JTextField number_=new JTextField(15); 
	    JTextField name_=new JTextField(15); 
	    JTextField sex_=new JTextField(15); 
	    JTextField age_=new JTextField(15); 
	    JTextField major_=new JTextField(15); 
	    JTextField major01_=new JTextField(15);
	   panel1_1.add(sex); panel1_1.add(number_);
	   panel1_2.add(min_agelabel);  panel1_2.add(name_);
	   panel1_3.add(max_agelabel); panel1_3.add(sex_);
	   panel1_4.add(majorl_abel); panel1_4.add(age_);
	   panel1_5.add(bookPriceLabel); panel1_5.add(major_);
	  // panel1_6.add(bookPriceLabel1); panel1_5.add(major01_);
	   panel1.add(panel1_1);
	   panel1.add(panel1_2);
	   panel1.add(panel1_3);
	   panel1.add(panel1_4);
	   panel1.add(panel1_5);
	   panel1.add(panel1_6);
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
					   ss[j][0]=number_.getText();
					   ss[j][1]=name_.getText();
					   ss[j][2]=sex_.getText();
					   ss[j][3]=age_.getText();
					   ss[j][4]=major_.getText();
					   ss[j][5]=major01_.getText();
					   j++;
					   SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					   String abc=df.format(new Date());
					  
					   Drug1.InsertDate11(number_.getText(),abc,sex_.getText(),age_.getText(),major_.getText());
					   model.setRowCount(0);
					   update2();
					   JOptionPane.showMessageDialog(table,"��ӳɹ���");
					   f.setVisible(false);
				   
				   }			     
		 });
		
		cancelBut.addActionListener(new ActionListener() {
			   public void actionPerformed(ActionEvent e){
				   f.setVisible(false);
			   }
			        
		 });
	}
	//////////////////////////////////////////////
	private void delectable2(){
		JFrame f=new JFrame("");
		JPanel panel1,panel2,panel1_1,panel1_2;

		JPanel panel=new JPanel();
		
		panel.setLayout(new BorderLayout(10,0)); 
		panel1=new JPanel();panel2=new JPanel();
		panel1_1=new JPanel();panel1_2=new JPanel();//panel1_3=new JPanel();panel1_4=new JPanel();
		panel1.setLayout(new GridLayout(0, 1, 10, 20));//����һ��
		
		JLabel sex=new JLabel("��ⵥ���:");		      
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
					
					Drug1.DelectDate1(s);
					//rewrite();
					model.setRowCount(0);
					update2();
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
	 * 
	 */
	void select2(String id) {
		 Connection conn = Drug1.getMysqlConn();
			PreparedStatement ps = null;
		   try {
				String sql="select * from Drug_04 where ���=?";
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
						  // ss[i][5]=rs.getString(6);
	JOptionPane.showMessageDialog(table,"����ⵥ��Ϣ���£�\n"+"��ⵥ��ţ�"+ss[i][0]+"\n"+"������ڣ�"+ss[i][1]+"\n"+"�����Ա��ϵ�绰��"+ss[i][2]+"\n"+"�ܽ�"+ss[i][3]+"\n"+"����ˣ�"+ss[i][4]);
	//+"\n"+"��Ӧ��״̬��"+ss[i][5]
						
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

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
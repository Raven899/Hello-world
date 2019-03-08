package beiyong;
import java.awt.*;  
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Homework.Drug1;

import java.awt.event.*;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class Drug02 extends JFrame implements ActionListener{  
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
  private DefaultTableModel model = null;
  private JTable table = null;
   int j=0,k=-1;
   //j�Ĺ����Ǽ�¼�ļ��е������ܸ�����Ϊȫ�ֱ���
   //k����������Ӻ��޸Ĺ�������ͬһ����壬k=-1Ϊ��ӹ��ܡ�k=index�����޸ĵ��кţ���Ϊ�޸Ĺ���
   String[][] ss=new String[1024][5] ; //���ļ��ж�ȡ���ݵ���������

  public Drug02() { 
    //�����������ϵĿؼ� 
	super("ѧ����Ϣ����ϵͳ");
    numberlabel=new JLabel("������ѧ��:"); 
    numbertext=new JTextField(10); 
    sexlabel=new JLabel("�������Ա�:"); 
    sextext=new JTextField(10); 
    sexbutton=new JButton("���Ա��ѯ");
    numbersearch=new JButton("��ѧ�Ų�ѯ"); 
    multi_search=new JButton("��������ѯ"); 
    addBut=new JButton("���ѧ��"); 
    modifyBut=new JButton("�޸�ѡ����"); 
    deleteBut=new JButton("ɾ��ѡ����"); 
    otherBut=new JButton("������ѯ��ʽ"); 
    addBut = new JButton("���ѧ��");
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
	String[] titles = { "��Ӧ������", "��ϵ��", "��ϵ�绰", "��ַ", "�������˺�" ,"��Ӧ��״̬"};
	model = new DefaultTableModel(datas, titles);
	table = new JTable(model);
     //update1();
/////////////////////////////////////////////////////////////////////////////////////////////////////////////	     
	JScrollPane  bookScroll=new JScrollPane(table);	      
    Container con1=new Container(); 
    con1.setLayout(new FlowLayout()); 
    con1.add(numberlabel); 
    con1.add(numbertext); 
    con1.add(numbersearch); 
    con1.add(sexlabel); 
    con1.add(sextext); 
    con1.add(sexbutton); 
    con1.add(multi_search);
    Container con2=new Container(); 
    con2.setLayout(new FlowLayout()); 
    con2.add(addBut); 
    con2.add(modifyBut); 
    con2.add(deleteBut); 
    con2.add(refresh); 
    con2.add(drug);
    con2.add(accept);
    con2.add(drug01);
    con2.add(drug02);
   this.setLayout(new BorderLayout()); 
   this.add(con1,BorderLayout.NORTH); 
   this.add(bookScroll,BorderLayout.CENTER); 
   this.add(con2,BorderLayout.SOUTH); 
   update2();
   
/////////////////////////////////////////////////////////////////////////////////////////////////////////////  
   
//ˢ���б��¼�����
   refresh.addActionListener(new ActionListener() {
	   public void actionPerformed(ActionEvent e){
		   j=0;//����j=0
		   model.setRowCount(0);//����б�����
		   update2();//���÷���
	   }   	   
   });
//���Ա��ѯ��ť�����¼�  
   sexbutton.addActionListener(new ActionListener() {
	   public void actionPerformed(ActionEvent e){
		   String[][] temp1=new String[1024][5] ; //������ʱ���飬�ŷ��ϲ�ѯ������Ԫ��	
		   int z=0;	   //z��¼��ʱ������Ԫ�ظ���
		   for(int ii=0;ii<j;ii++){//ѭ��j��
			   if(sextext.getText().equals(ss[ii][2])){//�����ȸ�ֵ
				   temp1[z++]=ss[ii];
			   }
			   model.setRowCount(0); 
			 	for(int i=0;i<z;i++)//��дjtable�б�
		          model.addRow(new String[] { temp1[i][0], temp1[i][1], temp1[i][2] , temp1[i][3], temp1[i][4]  });			 
		   }		   
	   }	   
   });
//////ҩƷ����ť�����¼�
	drug.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			 Wangxiong wang =new Wangxiong();
			 Wangxiong.update1();
			 setVisible(false);
			
		}
		
	});
	////////��Ӧ�̹���ť�����¼�
	accept.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			 Drug02 Drug=new Drug02();
			 setVisible(false);
			 
			
		}
		
	});
/////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 
	 */
	drug01.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			 Drug_03 s=new Drug_03();
			 Drug_03.update2();
			 setVisible(false);
			
		}
		
	});
	/**
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
 //��������ѯ�¼�����  
   multi_search.addActionListener(new ActionListener() {
	   public void actionPerformed(ActionEvent e){
		   //mulit_search();//���ú���
	   }   	   
   });
//ѧ�Ų����¼�����
	numbersearch.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e){	
//			for(int i=0;i<j;i++){ 
//				if(numbertext.getText().equals(ss[i][0])){
//					JOptionPane.showMessageDialog(table,"������Ϣ���£�\n"+"ѧ�ţ�"+ss[i][0]+"\n"+"������"+ss[i][1]+"\n"+"�Ա�"+ss[i][2]+"\n"+"���䣺"+ss[i][3]+"\n"+"רҵ��"+ss[i][4]);
//					 break;
//				}
	//		}
			select2(numbertext.getText());
		}    
	});
//���ѧ����ť�¼�����			     
	addBut.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e){	
			k=-1;//k�Ĺ����Ѿ�������
			adddata();//����q����
		}
	});	 	
//�޸�ѧ����ť�����¼�
	modifyBut.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e){	
			int index = table.getSelectedRow();
			//k=index;
			//q2();
		}
	});	
//ɾ��ѧ����ť�����¼�	
	deleteBut.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e){	   
//			int index = table.getSelectedRow();
//			for(int i=index;i<j-1;i++){//ɾ��ѧ�����Ƿ���ѡ�еĵ�i��Ԫ�أ��ȶ��ڴ��е������������������i֮���Ԫ��һ��ǰ��һλ�����ǵ�i����д���ļ���
//				ss[i]=ss[i+1];		
//			}
//			j--;
//			rewrite();
//			model.setRowCount(0);
//			reload();
//			JOptionPane.showMessageDialog(table,"ɾ���ɹ�");
			//q1();
			delectables();
			
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
	/////////////////////////////////////////////////////////////////////////////////////////
	void update2() {
		 
		   Connection conn = Drug1.getMysqlConn();
			PreparedStatement ps = null;
		   try {
				String sql="select * from drug_02";
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
	////////////////////////////////////////////////////////////////////////
	private void adddata(){
		JFrame f=new JFrame("");
		JPanel panel1,panel2,panel1_1,panel1_2,panel1_3,panel1_4,panel1_5;
		JPanel panel=new JPanel();
		
		panel.setLayout(new BorderLayout(10,0)); 
		panel1=new JPanel();panel2=new JPanel();
		panel1_1=new JPanel();panel1_2=new JPanel();panel1_3=new JPanel();panel1_4=new JPanel();
		panel1_5=new JPanel();
		panel1.setLayout(new GridLayout(0, 1, 10, 20));//����һ��
		
		JLabel sex=new JLabel("ѧ ��:"); 
		JLabel  min_agelabel=new JLabel("�� ��:"); 
		JLabel  max_agelabel=new JLabel("�� ��:"); 
		JLabel  majorl_abel=new JLabel("�� ��:"); 
		JLabel  bookPriceLabel=new JLabel("רҵ:"); 
	   
	      
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
					   ss[j][0]=number_.getText();
					   ss[j][1]=name_.getText();
					   ss[j][2]=sex_.getText();
					   ss[j][3]=age_.getText();
					   ss[j][4]=major_.getText();
					   j++;
					   sex.setText(number_.getText());
					 
						 String abc = name_.getText();
						  
					   Drug1.InsertDate10(number_.getText(),name_.getText(),sex_.getText(),age_.getText(),major_.getText());
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
	private void delectables(){
		JFrame f=new JFrame("");
		JPanel panel1,panel2,panel1_1,panel1_2;

		JPanel panel=new JPanel();
		
		panel.setLayout(new BorderLayout(10,0)); 
		panel1=new JPanel();panel2=new JPanel();
		panel1_1=new JPanel();panel1_2=new JPanel();//panel1_3=new JPanel();panel1_4=new JPanel();
		panel1.setLayout(new GridLayout(0, 1, 10, 20));//����һ��
		
		JLabel sex=new JLabel("��Ӧ������:");		      
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
					
					Drug1.DelectDates(s);
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
				String sql="select * from Drug_02 where ��Ӧ������=?";
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
	JOptionPane.showMessageDialog(table,"�ù�Ӧ����Ϣ���£�\n"+"��Ӧ�����ƣ�"+ss[i][0]+"\n"+"��ϵ�ˣ�"+ss[i][1]+"\n"+"��ϵ�绰��"+ss[i][2]+"\n"+"��ַ��"+ss[i][3]+"\n"+"�������˺ţ�"+ss[i][4]);
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
}
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
public class Wangxiong extends JFrame implements ActionListener{  
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
   //static String drug_01 = null;
   //j�Ĺ����Ǽ�¼�ļ��е������ܸ�����Ϊȫ�ֱ���
   //k����������Ӻ��޸Ĺ�������ͬһ����壬k=-1Ϊ��ӹ��ܡ�k=index�����޸ĵ��кţ���Ϊ�޸Ĺ���
   static String[][] ss=new String[1024][5] ; //���ļ��ж�ȡ���ݵ���������
  
  public static void main(String[] args)  { 
	  
	  Wangxiong wang =new Wangxiong();
	 
	  wang.update1();
  } 

  public Wangxiong() { 
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
  refresh = new JButton("ˢ���б�");
  drug=new JButton("ҩƷ����");
  accept=new JButton("��Ӧ�̹���");
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
	String[] titles = { "ѧ��", "����", "�Ա�", "����", "רҵ" };
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
   
/////////////////////////////////////////////////////////////////////////////////////////////////////////////  
   
//ˢ���б��¼�����
   refresh.addActionListener(new ActionListener() {
	   public void actionPerformed(ActionEvent e){
		   j=0;//����j=0
		   model.setRowCount(0);//����б�����
		   update1();//���÷���
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

 //��������ѯ�¼�����  
   multi_search.addActionListener(new ActionListener() {
	   public void actionPerformed(ActionEvent e){
		   mulit_search();//���ú���
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
			// TODO Auto-generated method stub
			 Wangxiong wang =new Wangxiong();
			 update1();
			 setVisible(false);
			
		}
		
	});
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
	////////��Ӧ�̹���ť�����¼�
	accept.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			 Drug02 Drug=new Drug02();
			 setVisible(false);
			 
			
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
//��������ѯ��ť		
	void mulit_search(){
		JFrame f=new JFrame("��������ѯ����");
		JPanel panel1,panel2,panel1_1,panel1_2,panel1_3,panel1_4;
		JPanel panel=new JPanel();
		
		panel.setLayout(new BorderLayout(10,0)); 
		panel1=new JPanel();panel2=new JPanel();
		panel1_1=new JPanel();panel1_2=new JPanel();panel1_3=new JPanel();panel1_4=new JPanel();
		panel1.setLayout(new GridLayout(0, 1, 10, 20));//����һ��
		
		JLabel sex=new JLabel("��        ��:"); 
		JLabel  min_agelabel=new JLabel("��С����:"); 
		JLabel  max_agelabel=new JLabel("�������:"); 
		JLabel  majorl_abel=new JLabel("ר       ҵ:"); 
	   
	      
	    JTextField sex_text=new JTextField(15); 
	    JTextField min_age_text=new JTextField(15); 
	    JTextField max_age_text=new JTextField(15); 
	    JTextField major_text=new JTextField(15); 
	   panel1_1.add(sex); panel1_1.add(sex_text);
	   panel1_2.add(min_agelabel);  panel1_2.add(min_age_text);
	   panel1_3.add(max_agelabel); panel1_3.add(max_age_text);
	   panel1_4.add(majorl_abel); panel1_4.add(major_text);
	   panel1.add(panel1_1);
	   panel1.add(panel1_2);
	   panel1.add(panel1_3);
	   panel1.add(panel1_4);
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
				String[][] temp2 = null; //������ʱ���飬�ŷ���������Ԫ�� 
				temp2=ss;
//				 System.arraycopy(ss, 0, temp2, 0, ss.length);
				int n,n1,n2,m=0,m1=0,m2=0,x1=j;//n��n1��n2Ϊѭ��������k m m1  m2 �ֱ�Ϊִ�и�����ѯ�����������������Ԫ�ظ��������ֵ��x1
				//�Ȱ��Ա��ѯ����������Ԫ��
				if(sex_text.getText().equals("")==false){
					int k=0;	   
					for(int iii=0;iii<j;iii++){
						if(sex_text.getText().equals(ss[iii][2]))temp2[k++]=ss[iii];
						 x1=k; 
					}
					
				 }
				//��ѯ����С������Ԫ��  
				if(min_age_text.getText().equals("")==false) {
				    for(n=0;n<x1;n++){
				    	if(temp2[n][3].compareTo(min_age_text.getText())>=0){
				    		temp2[m]=temp2[n];//���ݽṹ��ѧ���ķ�������ͬһ������ֱ�ӽ��в���
				    		m++;	
				    		 
				    	}
				    } 
				    
				    x1=m;
				 } 		
				//��ѯ���������С��
				 if(max_age_text.getText().equals("")==false) {
				    for(n1=0;n1<x1;n1++){
				    	if(temp2[n1][3].compareTo(max_age_text.getText())<=0){
				    		temp2[m1]=temp2[n1];
				    		m1++;
				    	}
				    }  
				    x1=m1;
				}
				 //��ѯרҵ
				if(major_text.getText().equals("")==false) {				    				    	
				    for(n2=0;n2<x1;n2++){
				    	if(major_text.getText().equals(temp2[n2][4])){
				    		temp2[m2]=temp2[n2];
				    		m2++;
				    	}	
				    } 
				    x1=m2;	    	
				}
				//���x1������0��˵���з���������ѧ��Ԫ��
			if(x1!=0){
				 model.setRowCount(0);   	
				 for(int i=0;i<x1;i++)
			        model.addRow(new String[] { temp2[i][0], temp2[i][1], temp2[i][2] , temp2[i][3], temp2[i][4]  });
				    temp2=null;
				  f.dispose();
				 	}
				    else{
				    	JOptionPane.showMessageDialog(table,"û�в�ѯ������������ѧ��");
				    }
				 	 f.dispose();	
				   } 
		 });   

		cancelBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				f.setVisible(false);
			   }
			        
		 });
		
	}
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

				   if(k==-1){//����-1Ϊ��ӹ���
//				   ss[j][0]=number_.getText();
//				   ss[j][1]=name_.getText();
//				   ss[j][2]=sex_.getText();
//				   ss[j][3]=age_.getText();
//				   ss[j][4]=major_.getText();
//				   j++;
//				   rewrite();
//				   model.setRowCount(0);
//				   reload();
//				   JOptionPane.showMessageDialog(table,"��ӳɹ�");
//				   f.setVisible(false);
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
	private void q2(){
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
//		
//		
		submitBut.addActionListener(new ActionListener() {
			   public void actionPerformed(ActionEvent e){
				  // String s=number_.getText();
				  // ss[k][0]=number_.getText();
////			   ss[k][1]=name_.getText();
////			   ss[k][2]=sex_.getText();
////			   ss[k][3]=age_.getText();
////			   ss[k][4]=major_.getText();
					
//					Drug1.UpdateDate1(number_.getText(),name_.getText(),sex_.getText(),age_.getText(),major_.getText());
//					//rewrite();
//					model.setRowCount(0);
//					update1();
//					JOptionPane.showMessageDialog(table,"�޸ĳɹ�");
//					f.setVisible(false);
				   Connection conn = Drug1.getMysqlConn();
					PreparedStatement ps = null;
				   try {
						String sql="update drug_01 set ҩƷ����=?,��ⵥ���=?,��λ=?,��װ=? where ҩƷ����=?";
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
			
			JLabel sex=new JLabel("ѧ ��:");		      
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
		JOptionPane.showMessageDialog(table,"������Ϣ���£�\n"+"ѧ�ţ�"+ss[i][0]+"\n"+"������"+ss[i][1]+"\n"+"�Ա�"+ss[i][2]+"\n"+"���䣺"+ss[i][3]+"\n"+"רҵ��"+ss[i][4]);	
							
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
		
		
}
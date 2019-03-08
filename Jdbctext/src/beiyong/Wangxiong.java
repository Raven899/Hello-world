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
   //j的功能是记录文件中的数据总个数，为全局变量
   //k的作用是添加和修改功能用了同一个面板，k=-1为添加功能。k=index（即修改的行号）则为修改功能
   static String[][] ss=new String[1024][5] ; //从文件中读取内容到此数组中
  
  public static void main(String[] args)  { 
	  
	  Wangxiong wang =new Wangxiong();
	 
	  wang.update1();
  } 

  public Wangxiong() { 
    //创建主界面上的控件 
	super("学生信息管理系统");
    numberlabel=new JLabel("请输入学号:"); 
    numbertext=new JTextField(10); 
    sexlabel=new JLabel("请输入性别:"); 
    sextext=new JTextField(10); 
    sexbutton=new JButton("按性别查询");
    numbersearch=new JButton("按学号查询"); 
    multi_search=new JButton("多条件查询"); 
    addBut=new JButton("添加学生"); 
    modifyBut=new JButton("修改选中项"); 
    deleteBut=new JButton("删除选中项"); 
    otherBut=new JButton("其它查询方式"); 
    addBut = new JButton("添加学生");
  refresh = new JButton("刷新列表");
  drug=new JButton("药品管理");
  accept=new JButton("供应商管理");
  drug01=new JButton("入库单单头");
  drug02=new JButton("入库单单目");


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
	//创建jtable列表
	String[][] datas = {};
	String[] titles = { "学号", "姓名", "性别", "年龄", "专业" };
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
   
//刷新列表事件监听
   refresh.addActionListener(new ActionListener() {
	   public void actionPerformed(ActionEvent e){
		   j=0;//先让j=0
		   model.setRowCount(0);//清除列表内容
		   update1();//调用方法
	   }   	   
   });
//按性别查询按钮监听事件  
   sexbutton.addActionListener(new ActionListener() {
	   public void actionPerformed(ActionEvent e){
		   String[][] temp1=new String[1024][5] ; //创建临时数组，放符合查询条件的元素	
		   int z=0;	   //z记录临时数组中元素个数
		   for(int ii=0;ii<j;ii++){//循环j次
			   if(sextext.getText().equals(ss[ii][2])){//如果相等赋值
				   temp1[z++]=ss[ii];
			   }
			   model.setRowCount(0); 
			 	for(int i=0;i<z;i++)//重写jtable列表
		          model.addRow(new String[] { temp1[i][0], temp1[i][1], temp1[i][2] , temp1[i][3], temp1[i][4]  });			 
		   }		   
	   }	   
   });

 //多条件查询事件监听  
   multi_search.addActionListener(new ActionListener() {
	   public void actionPerformed(ActionEvent e){
		   mulit_search();//调用函数
	   }   	   
   });
//学号查找事件监听
	numbersearch.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e){	
//			for(int i=0;i<j;i++){ 
//				if(numbertext.getText().equals(ss[i][0])){
//					JOptionPane.showMessageDialog(table,"该生信息如下：\n"+"学号："+ss[i][0]+"\n"+"姓名："+ss[i][1]+"\n"+"性别："+ss[i][2]+"\n"+"年龄："+ss[i][3]+"\n"+"专业："+ss[i][4]);
//					 break;
//				}
	//		}
			select1(numbertext.getText());
		}    
	});
//添加学生按钮事件监听			     
	addBut.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e){	
			k=-1;//k的功能已经叙述过
			q();//调用q方法
		}
	});	 	
//修改学生按钮监听事件
	modifyBut.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e){	
			int index = table.getSelectedRow();
			//k=index;
			q2();
		}
	});	
	//////药品管理按钮监听事件
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
	////////供应商管理按钮监听事件
	accept.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			 Drug02 Drug=new Drug02();
			 setVisible(false);
			 
			
		}
		
	});
	
//删除学生按钮监听事件	
	deleteBut.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e){	   
//			int index = table.getSelectedRow();
//			for(int i=index;i<j-1;i++){//删除学生就是返回选中的第i个元素，先对内存中的数组操作。将数组中i之后的元素一次前移一位，覆盖掉i，在写入文件中
//				ss[i]=ss[i+1];		
//			}
//			j--;
//			rewrite();
//			model.setRowCount(0);
//			reload();
//			JOptionPane.showMessageDialog(table,"删除成功");
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
//多条件查询按钮		
	void mulit_search(){
		JFrame f=new JFrame("多条件查询窗口");
		JPanel panel1,panel2,panel1_1,panel1_2,panel1_3,panel1_4;
		JPanel panel=new JPanel();
		
		panel.setLayout(new BorderLayout(10,0)); 
		panel1=new JPanel();panel2=new JPanel();
		panel1_1=new JPanel();panel1_2=new JPanel();panel1_3=new JPanel();panel1_4=new JPanel();
		panel1.setLayout(new GridLayout(0, 1, 10, 20));//多行一列
		
		JLabel sex=new JLabel("性        别:"); 
		JLabel  min_agelabel=new JLabel("最小年龄:"); 
		JLabel  max_agelabel=new JLabel("最大年龄:"); 
		JLabel  majorl_abel=new JLabel("专       业:"); 
	   
	      
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
				String[][] temp2 = null; //创建临时数组，放符合条件的元素 
				temp2=ss;
//				 System.arraycopy(ss, 0, temp2, 0, ss.length);
				int n,n1,n2,m=0,m1=0,m2=0,x1=j;//n，n1，n2为循环变量，k m m1  m2 分别为执行各个查询后，数组里符合条件的元素个数，最后赋值给x1
				//先按性别查询符合条件的元素
				if(sex_text.getText().equals("")==false){
					int k=0;	   
					for(int iii=0;iii<j;iii++){
						if(sex_text.getText().equals(ss[iii][2]))temp2[k++]=ss[iii];
						 x1=k; 
					}
					
				 }
				//查询比最小年龄大的元素  
				if(min_age_text.getText().equals("")==false) {
				    for(n=0;n<x1;n++){
				    	if(temp2[n][3].compareTo(min_age_text.getText())>=0){
				    		temp2[m]=temp2[n];//数据结构中学到的方法，对同一个数组直接进行操作
				    		m++;	
				    		 
				    	}
				    } 
				    
				    x1=m;
				 } 		
				//查询比最大年龄小的
				 if(max_age_text.getText().equals("")==false) {
				    for(n1=0;n1<x1;n1++){
				    	if(temp2[n1][3].compareTo(max_age_text.getText())<=0){
				    		temp2[m1]=temp2[n1];
				    		m1++;
				    	}
				    }  
				    x1=m1;
				}
				 //查询专业
				if(major_text.getText().equals("")==false) {				    				    	
				    for(n2=0;n2<x1;n2++){
				    	if(major_text.getText().equals(temp2[n2][4])){
				    		temp2[m2]=temp2[n2];
				    		m2++;
				    	}	
				    } 
				    x1=m2;	    	
				}
				//如果x1不等于0，说明有符合条件的学生元素
			if(x1!=0){
				 model.setRowCount(0);   	
				 for(int i=0;i<x1;i++)
			        model.addRow(new String[] { temp2[i][0], temp2[i][1], temp2[i][2] , temp2[i][3], temp2[i][4]  });
				    temp2=null;
				  f.dispose();
				 	}
				    else{
				    	JOptionPane.showMessageDialog(table,"没有查询到符合条件的学生");
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
		panel1.setLayout(new GridLayout(0, 1, 10, 20));//多行一列
		
		JLabel sex=new JLabel("学 号:"); 
		JLabel  min_agelabel=new JLabel("姓 名:"); 
		JLabel  max_agelabel=new JLabel("性 别:"); 
		JLabel  majorl_abel=new JLabel("年 龄:"); 
		JLabel  bookPriceLabel=new JLabel("专业:"); 
	   
	      
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

				   if(k==-1){//等于-1为添加功能
//				   ss[j][0]=number_.getText();
//				   ss[j][1]=name_.getText();
//				   ss[j][2]=sex_.getText();
//				   ss[j][3]=age_.getText();
//				   ss[j][4]=major_.getText();
//				   j++;
//				   rewrite();
//				   model.setRowCount(0);
//				   reload();
//				   JOptionPane.showMessageDialog(table,"添加成功");
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
					   JOptionPane.showMessageDialog(table,"添加成功！");
					   f.setVisible(false);
				   }else{//等于index为修改功能
					   ss[k][0]=number_.getText();
					   ss[k][1]=name_.getText();
					   ss[k][2]=sex_.getText();
					   ss[k][3]=age_.getText();
					   ss[k][4]=major_.getText();
					  
					 
					   model.setRowCount(0);
					   JOptionPane.showMessageDialog(table,"修改成功");
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
		panel1.setLayout(new GridLayout(0, 1, 10, 20));//多行一列
		
		JLabel sex=new JLabel("学 号:"); 
		JLabel  min_agelabel=new JLabel("姓 名:"); 
		JLabel  max_agelabel=new JLabel("性 别:"); 
		JLabel  majorl_abel=new JLabel("年 龄:"); 
		JLabel  bookPriceLabel=new JLabel("专业:"); 
	   
	      
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
//					JOptionPane.showMessageDialog(table,"修改成功");
//					f.setVisible(false);
				   Connection conn = Drug1.getMysqlConn();
					PreparedStatement ps = null;
				   try {
						String sql="update drug_01 set 药品名称=?,入库单编号=?,单位=?,包装=? where 药品编码=?";
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
						JOptionPane.showMessageDialog(table,"修改成功");
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
			panel1.setLayout(new GridLayout(0, 1, 10, 20));//多行一列
			
			JLabel sex=new JLabel("学 号:");		      
		    JTextField number_=new JTextField(15); 

		   panel1_1.add(sex); 
		   panel1_1.add(number_);

		   panel1.add(panel1_1);

		   JButton submitBut=new JButton("确认"); 
		   JButton cancelBut=new JButton("取消"); 
		    
		    panel2.add(submitBut);panel2.add(cancelBut);
			panel.add(panel1,BorderLayout.CENTER);
			panel.add(panel2,BorderLayout.SOUTH);
			f.getContentPane().add(panel);  //将面板添加到容器上
			f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
			f.setBounds(100,100,100,400);//设置窗口出现的位置
			f.setSize(200,200);//窗口尺寸
			f.setVisible(true);
			submitBut.addActionListener(new ActionListener() {
				   public void actionPerformed(ActionEvent e){
					   String s=number_.getText();
						
						Drug1.DelectDate(s);
						//rewrite();
						model.setRowCount(0);
						String drug_01 = null;
						update1();
						JOptionPane.showMessageDialog(table,"删除成功");
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
					String sql="select * from Drug_01 where 药品编码=?";
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
		JOptionPane.showMessageDialog(table,"该生信息如下：\n"+"学号："+ss[i][0]+"\n"+"姓名："+ss[i][1]+"\n"+"性别："+ss[i][2]+"\n"+"年龄："+ss[i][3]+"\n"+"专业："+ss[i][4]);	
							
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
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
   //j的功能是记录文件中的数据总个数，为全局变量
   //k的作用是添加和修改功能用了同一个面板，k=-1为添加功能。k=index（即修改的行号）则为修改功能
   String[][] ss=new String[1024][5] ; //从文件中读取内容到此数组中

  public Drug02() { 
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
    drug=new JButton("药品管理");
    accept=new JButton("供应商管理");
  refresh = new JButton("刷新列表");
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
	String[] titles = { "供应商名称", "联系人", "联系电话", "地址", "开户行账号" ,"供应商状态"};
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
   
//刷新列表事件监听
   refresh.addActionListener(new ActionListener() {
	   public void actionPerformed(ActionEvent e){
		   j=0;//先让j=0
		   model.setRowCount(0);//清除列表内容
		   update2();//调用方法
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
//////药品管理按钮监听事件
	drug.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			 Wangxiong wang =new Wangxiong();
			 Wangxiong.update1();
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
 //多条件查询事件监听  
   multi_search.addActionListener(new ActionListener() {
	   public void actionPerformed(ActionEvent e){
		   //mulit_search();//调用函数
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
			select2(numbertext.getText());
		}    
	});
//添加学生按钮事件监听			     
	addBut.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e){	
			k=-1;//k的功能已经叙述过
			adddata();//调用q方法
		}
	});	 	
//修改学生按钮监听事件
	modifyBut.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e){	
			int index = table.getSelectedRow();
			//k=index;
			//q2();
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
					   JOptionPane.showMessageDialog(table,"添加成功！");
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
		panel1.setLayout(new GridLayout(0, 1, 10, 20));//多行一列
		
		JLabel sex=new JLabel("供应商名称:");		      
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
					
					Drug1.DelectDates(s);
					//rewrite();
					model.setRowCount(0);
					update2();
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
	/**
	 * 
	 */
	void select2(String id) {
		 Connection conn = Drug1.getMysqlConn();
			PreparedStatement ps = null;
		   try {
				String sql="select * from Drug_02 where 供应商名称=?";
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
	JOptionPane.showMessageDialog(table,"该供应商信息如下：\n"+"供应商名称："+ss[i][0]+"\n"+"联系人："+ss[i][1]+"\n"+"联系电话："+ss[i][2]+"\n"+"地址："+ss[i][3]+"\n"+"开户行账号："+ss[i][4]);
	//+"\n"+"供应商状态："+ss[i][5]
						
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
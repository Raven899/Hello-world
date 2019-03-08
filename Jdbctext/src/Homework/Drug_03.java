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
   //j的功能是记录文件中的数据总个数，为全局变量
   //k的作用是添加和修改功能用了同一个面板，k=-1为添加功能。k=index（即修改的行号）则为修改功能
   static String[][] ss=new String[1024][7] ; //从文件中读取内容到此数组中

  public Drug_03() { 
    //创建主界面上的控件 
	super("");
    numberlabel=new JLabel("请输入入库单编号:"); 
    numbertext=new JTextField(10); 
    sexlabel=new JLabel("请输入:"); 
    sextext=new JTextField(10); 
    sexbutton=new JButton("查询");
    numbersearch=new JButton("按入库单编号查询"); 
    multi_search=new JButton("多条件查询"); 
    addBut=new JButton("添加"); 
    modifyBut=new JButton("修改"); 
    deleteBut=new JButton("删除"); 
    otherBut=new JButton("其它查询方式"); 
    addBut = new JButton("添加");
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
	String[] titles = { "入库单编号", "入库日期", "入库人员", "总金额", "审核人"};
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
   
//刷新列表事件监听
   refresh.addActionListener(new ActionListener() {
	   public void actionPerformed(ActionEvent e){
		   j=0;//先让j=0
		   model.setRowCount(0);//清除列表内容
		   update2();//调用方法
	   }   	   
   });

//////药品管理按钮监听事件
	drug.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			Drug_00 wang =new Drug_00();
			Drug_00.update1();
			 setVisible(false);
			
		}
		
	});
	////////供应商管理按钮监听事件
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
	 * 入库单单头
	 */
	drug01.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			 Drug_03 s=new Drug_03();
			 setVisible(false);
			
		}
		
	});
	/**入库单单目
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

 //查找事件监听
 	numbersearch.addActionListener(new ActionListener() {
 		public void actionPerformed(ActionEvent e){	

 			select2(numbertext.getText());
 		}    
 	});
 //添加按钮事件监听			     
 	addBut.addActionListener(new ActionListener() {
 		public void actionPerformed(ActionEvent e){	
 			k=-1;//k的功能已经叙述过
 			adddata();//调用q方法
 		}
 	});	 	
//修改按钮监听事件
	modifyBut.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e){	
			int index = table.getSelectedRow();
			//k=index;
			updatenumber();		}
	});	
//删除按钮监听事件	
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
		panel1.setLayout(new GridLayout(0, 1, 10, 20));//多行一列

		JLabel sex=new JLabel("入库单编号:"); 
		JLabel  min_agelabel=new JLabel("入库日期:"); 
		JLabel  max_agelabel=new JLabel("入库人员:"); 
		JLabel  majorl_abel=new JLabel("总金额:"); 
		JLabel  bookPriceLabel=new JLabel("审核人种类:"); 
	   
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
		
				   Connection conn = Drug1.getMysqlConn();
					PreparedStatement ps = null;
				   try {
						String sql="update drug_03 set 入库日期=?,入库人员=?,总金额=?,审核人=?where 入库单编号=?";
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
		panel1.setLayout(new GridLayout(0, 1, 10, 20));//多行一列
		//入库单
		JLabel sex=new JLabel("入库单编号:"); 
		JLabel  min_agelabel=new JLabel("入库日期:"); 
		JLabel  max_agelabel=new JLabel("入库人员:"); 
		JLabel  majorl_abel=new JLabel("总金额:"); 
		JLabel  bookPriceLabel=new JLabel("审核人:"); 
		//JLabel  bookPriceLabel1=new JLabel("审核日期:"); 
	   
	      
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
					   ss[j][5]=major01_.getText();
					   j++;
					   SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					   String abc=df.format(new Date());
					  
					   Drug1.InsertDate11(number_.getText(),abc,sex_.getText(),age_.getText(),major_.getText());
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
	private void delectable2(){
		JFrame f=new JFrame("");
		JPanel panel1,panel2,panel1_1,panel1_2;

		JPanel panel=new JPanel();
		
		panel.setLayout(new BorderLayout(10,0)); 
		panel1=new JPanel();panel2=new JPanel();
		panel1_1=new JPanel();panel1_2=new JPanel();//panel1_3=new JPanel();panel1_4=new JPanel();
		panel1.setLayout(new GridLayout(0, 1, 10, 20));//多行一列
		
		JLabel sex=new JLabel("入库单编号:");		      
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
					
					Drug1.DelectDate1(s);
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
				String sql="select * from Drug_04 where 序号=?";
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
	JOptionPane.showMessageDialog(table,"该入库单信息如下：\n"+"入库单编号："+ss[i][0]+"\n"+"入库日期："+ss[i][1]+"\n"+"入库人员联系电话："+ss[i][2]+"\n"+"总金额："+ss[i][3]+"\n"+"审核人："+ss[i][4]);
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

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
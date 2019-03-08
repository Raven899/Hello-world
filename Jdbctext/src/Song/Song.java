package Song;
import java.awt.*;  
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import Homework.Drug1;
import Jdbc2.StudentManager;

import java.awt.event.*;
import java.sql.*;
public class Song extends JFrame implements ActionListener{  
 
  private JButton addBut; 
  private JButton modifyBut; 
  private JButton deleteBut; 
  private JButton searchBut; 
  private JButton refresh;
  private JButton watch;
  private DefaultTableModel model = null;
  private JTable table = null;
   int j=0,k=-1;
   ///////////////////////////////////////////////////////////////////////
   private static final long serialVersionUID = 1L;
	
	String[][] ss=new String[1024][5] ; 
   //j的功能是记录文件中的数据总个数，为全局变量
   //k的作用是添加和修改功能用了同一个面板，k=-1为添加功能。k=index（即修改的行号）则为修改功能
  
//  public static void main(String[] args)  { 
//	  
//	  Song wang =new Song(); 
//	 // wang.update1();
//  } 
  ////////////////////////////////////////////////////////////////////




  public Song() { 
    //创建主界面上的控件 
	super("药品信息管理系统");
	///////////////////////////////////////////////////////////////

	///////////////////////////////////////////////////////////////////////
	watch=new JButton("查看通知");
    searchBut=new JButton("查询"); 
    addBut=new JButton("添加"); 
    modifyBut=new JButton("修改"); 
    deleteBut=new JButton("删除"); 
    addBut = new JButton("添加");
  refresh = new JButton("刷新列表");
  	watch.addActionListener(this);
    addBut.addActionListener(this); 
    modifyBut.addActionListener(this); 
    deleteBut.addActionListener(this); 
    refresh.addActionListener(this);
	//创建jtable列表
	String[][] datas = {};
	String[] titles = { "药品编号", "名称", "单位", "包装", "种类" };
	model = new DefaultTableModel(datas, titles);
	table = new JTable(model);
 
/////////////////////////////////////////////////////////////////////////////////////////////////////////////	     
	JScrollPane  bookScroll=new JScrollPane(table);	      
    Container con1=new Container(); 
    con1.setLayout(new FlowLayout()); 
   
    Container con2=new Container(); 
    con2.setLayout(new FlowLayout());
    con2.add(watch);
    con2.add(searchBut);
    con2.add(addBut); 
    con2.add(modifyBut); 
    con2.add(deleteBut); 
    con2.add(refresh); 
   this.setLayout(new BorderLayout()); 
   this.add(con1,BorderLayout.NORTH); 
   this.add(bookScroll,BorderLayout.CENTER); 
   this.add(con2,BorderLayout.SOUTH); 
   
/////////////////////////////////////////////////////////////////////////////////////////////////////////////  
   /**
    * 查询按钮监听事件
    */
searchBut.addActionListener(new ActionListener() {
	   public void actionPerformed(ActionEvent e){
//		   j=0;//先让j=0
//		   model.setRowCount(0);//清除列表内容
//		  search();//调用方法
//		  update1();
	   }   	   
   });
   ///////////////////////////////////////////////
   
//刷新列表事件监听
   refresh.addActionListener(new ActionListener() {
	   public void actionPerformed(ActionEvent e){
//		   j=0;//先让j=0
//		   model.setRowCount(0);//清除列表内容
//		   update1();//调用方法
	   }   	   
   });

//添加学生按钮事件监听			     
	addBut.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e){	
//			k=-1;//k的功能已经叙述过
//			q();//调用q方法
		}
	});	 	
//修改学生按钮监听事件
	modifyBut.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e){	
//			int index = table.getSelectedRow();
//			//k=index;
//			q2();
		}
	});	
//删除学生按钮监听事件	
	deleteBut.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e){	   

			//q1();
			
		}		   
	 }); 
	watch.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e){	
			fbtext();

		
			
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
	/**
	 * 添加函数
	 */
	private void q(){
		JFrame f=new JFrame("");
		JPanel panel1,panel2,panel1_1,panel1_2,panel1_3,panel1_4,panel1_5;
		JPanel panel=new JPanel();
		
		panel.setLayout(new BorderLayout(10,0)); 
		panel1=new JPanel();panel2=new JPanel();
		panel1_1=new JPanel();panel1_2=new JPanel();panel1_3=new JPanel();panel1_4=new JPanel();
		panel1_5=new JPanel();
		panel1.setLayout(new GridLayout(0, 1, 10, 20));//多行一列
		
		JLabel sex=new JLabel("编号:"); 
		JLabel  min_agelabel=new JLabel("名称:"); 
		JLabel  max_agelabel=new JLabel("单位:"); 
		JLabel  majorl_abel=new JLabel("包装:"); 
		JLabel  bookPriceLabel=new JLabel("种类:"); 
	   
	      
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
						  
					   Drug1.InsertDate8(number_.getText(),name_.getText(),sex_.getText(),age_.getText(),major_.getText());
					   model.setRowCount(0);
					   update1();
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
	////////////////////////////////////////////////////////////////////////////
	private void q2(){
		/**
		 * 修改函数
		 */
		JFrame f=new JFrame("");
		JPanel panel1,panel2,panel1_1,panel1_2,panel1_3,panel1_4,panel1_5;
		JPanel panel=new JPanel();
		
		panel.setLayout(new BorderLayout(10,0)); 
		panel1=new JPanel();panel2=new JPanel();
		panel1_1=new JPanel();panel1_2=new JPanel();panel1_3=new JPanel();panel1_4=new JPanel();
		panel1_5=new JPanel();
		panel1.setLayout(new GridLayout(0, 1, 10, 20));//多行一列
		JLabel sex=new JLabel("编号:"); 
		JLabel  min_agelabel=new JLabel("名称:"); 
		JLabel  max_agelabel=new JLabel("单位:"); 
		JLabel  majorl_abel=new JLabel("包装:"); 
		JLabel  bookPriceLabel=new JLabel("种类:"); 
	   
	      
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
						String sql="update drug_0 set 药品名称=?,单位=?,包装=?,种类=? where 药品编号=?";
						ps=conn.prepareStatement(sql);
	
						ps=conn.prepareStatement(sql);
						ps.setObject(1, name_.getText());
						ps.setObject(2, sex_.getText());
						ps.setObject(3, age_.getText());
						ps.setObject(4, major_.getText());
						ps.setObject(5, number_.getText());
						ps.execute();
						model.setRowCount(0);
						update1();
						JOptionPane.showMessageDialog(table,"修改成功");
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
/////////////////////////////////////////////////////////////////////////////////////////////////////////////	
		
		//////////////////////////////////////////////////////////////////////////////////////////////
		//删除函数
	private void q1(){
			JFrame f=new JFrame("");
			JPanel panel1,panel2,panel1_1,panel1_2;

			JPanel panel=new JPanel();
			
			panel.setLayout(new BorderLayout(10,0)); 
			panel1=new JPanel();panel2=new JPanel();
			panel1_1=new JPanel();panel1_2=new JPanel();//panel1_3=new JPanel();panel1_4=new JPanel();
			panel1.setLayout(new GridLayout(0, 1, 10, 20));//多行一列
			
			JLabel sex=new JLabel("编号:");		      
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
		/**
		 * 刷新函数
		 */
		void update1() {
			 
				   Connection conn = Drug1.getMysqlConn();
					PreparedStatement ps = null;
				   try {
						String sql="select * from drug_0";
						ps=conn.prepareStatement(sql);
	
						//ps.setObject(2, user_name);
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
		void select1(String id) {
			 Connection conn = Drug1.getMysqlConn();
				PreparedStatement ps = null;
			   try {
					String sql="select * from Drug_0 where 药品编号=?";
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
		JOptionPane.showMessageDialog(table,"该药品信息如下：\n"+"编号："+ss[i][0]+"\n"+"名称："+ss[i][1]+"\n"+"单位："+ss[i][2]+"\n"+"包装："+ss[i][3]+"\n"+"种类："+ss[i][4]);	
							
						}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}finally {
					Drug1.close(ps, conn);
				}
//			  
	}
		/**
		 * 查找函数
		 */
		private void search(){
			JFrame f=new JFrame("");
			JPanel panel1,panel2,panel1_1,panel1_2;

			JPanel panel=new JPanel();
			
			panel.setLayout(new BorderLayout(10,0)); 
			panel1=new JPanel();panel2=new JPanel();
			panel1_1=new JPanel();panel1_2=new JPanel();//panel1_3=new JPanel();panel1_4=new JPanel();
			panel1.setLayout(new GridLayout(0, 1, 10, 20));//多行一列
			
			JLabel sex=new JLabel("编号:");		      
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
						
						select1(s);
						model.setRowCount(0);
						update1();
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
		 * 查看通知
		 * 
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
			lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 29));
			lblNewLabel.setBounds(123, 89, 72, 40);
			frame.getContentPane().add(lblNewLabel);
			
			JLabel lblNewLabel_1 = new JLabel("\u53D1\u5E03\u8005");
			lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 28));
			lblNewLabel_1.setBounds(103, 148, 138, 42);
			frame.getContentPane().add(lblNewLabel_1);
			
			textField_1 = new JTextField();
			textField_1.setBounds(195, 156, 179, 37);
			frame.getContentPane().add(textField_1);
			textField_1.setColumns(10);
			
			JTextArea textArea = new JTextArea();
			textArea.setRows(10);
			textArea.setLineWrap(true);
			
			JButton button = new JButton("确定");
			button.setBounds(451, 506, 87, 27);
			frame.getContentPane().add(button);

			textArea.setBounds(88, 225, 349, 290);
			frame.getContentPane().add(textArea);
			frame.setVisible(true);
			String id="管理员";
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
		
		

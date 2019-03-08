package Jdbc1;
import java.awt.*;  
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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
  private DefaultTableModel model = null;
  private JTable table = null;
   int j=0,k=-1;
   //j�Ĺ����Ǽ�¼�ļ��е������ܸ�����Ϊȫ�ֱ���
   //k����������Ӻ��޸Ĺ�������ͬһ����壬k=-1Ϊ��ӹ��ܡ�k=index�����޸ĵ��кţ���Ϊ�޸Ĺ���
   String[][] ss=new String[1024][5] ; //���ļ��ж�ȡ���ݵ���������
  
  public static void main(String[] args)  { 
	  Wangxiong wang =new Wangxiong(); 
  } 

  public Wangxiong() { 
    //�����������ϵĿؼ� 
	super("ҩƷ��ⵥ����ϵ�?");
    numberlabel=new JLabel("��������:"); 
    numbertext=new JTextField(10); 
    sexlabel=new JLabel("����������:"); 
    sextext=new JTextField(10); 
    sexbutton=new JButton("�����Ʋ�ѯ");
    numbersearch=new JButton("����Ų��?"); 
    multi_search=new JButton("��������ѯ"); 
    addBut=new JButton("���ѧ��?"); 
    modifyBut=new JButton("�޸�ѡ����"); 
    deleteBut=new JButton("ɾ��ѡ����"); 
    otherBut=new JButton("������ѯ��ʽ"); 
    addBut = new JButton("���ҩ�?");
  refresh = new JButton("ˢ���б�");

    //.addActionListener(this); 
    multi_search.addActionListener(this); 
    numbersearch.addActionListener(this); 
    addBut.addActionListener(this); 
    otherBut.addActionListener(this); 
    modifyBut.addActionListener(this); 
    deleteBut.addActionListener(this); 
    refresh.addActionListener(this);
	//����jtable�б�
	String[][] datas = {};
	String[] titles = { "ҩƷ����", "��ⵥ���", "ҩƷ����", "��λ", "��װ" };
	model = new DefaultTableModel(datas, titles);
	table = new JTable(model);
	//reload();
/////////////////////////////////////////////////////////////////////////////////////////////////////////////	     
	JScrollPane  bookScroll=new JScrollPane(table);	      
    Container con1=new Container(); 
    con1.setLayout(new FlowLayout()); 
//    con1.add(numberlabel); 
//    con1.add(numbertext); 
//    con1.add(numbersearch); 
//    con1.add(sexlabel); 
//    con1.add(sextext); 
//    con1.add(sexbutton); 
//    con1.add(multi_search);
    Container con2=new Container(); 
    con2.setLayout(new FlowLayout()); 
//    con2.add(addBut); 
//    con2.add(modifyBut); 
//    con2.add(deleteBut); 
//    con2.add(refresh); 
   this.setLayout(new BorderLayout()); 
   this.add(con1,BorderLayout.NORTH); 
   this.add(bookScroll,BorderLayout.CENTER); 
   this.add(con2,BorderLayout.SOUTH); 
   
/////////////////////////////////////////////////////////////////////////////////////////////////////////////  
   
//ˢ���б��¼�����
   refresh.addActionListener(new ActionListener() {
	   public void actionPerformed(ActionEvent e){
		   j=0;//����j=0
		   model.setRowCount(0);//����б�����?
		 //  reload();//���÷���
	   }   	   
   });
//���Ա��ѯ��ť�����¼�?  
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
		   //mulit_search();//���ú���
	   }   	   
   });
//ѧ�Ų����¼�����
	numbersearch.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e){	
			for(int i=0;i<j;i++){ 
				if(numbertext.getText().equals(ss[i][0])){
					JOptionPane.showMessageDialog(table,"������Ϣ���£�\n"+"ѧ�ţ�"+ss[i][0]+"\n"+"������"+ss[i][1]+"\n"+"�Ա�"+ss[i][2]+"\n"+"���䣺"+ss[i][3]+"\n"+"רҵ��"+ss[i][4]);
					 break;
				}
			}
		}    
	});
//���ѧ����ť�¼�����?			     
	addBut.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e){	
			k=-1;//k�Ĺ����Ѿ�������
			//q();//����q����
		}
	});	 	
//�޸�ѧ����ť�����¼�
	modifyBut.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e){	
			int index = table.getSelectedRow();
			k=index;
			//q();
		}
	});	
//ɾ��ѧ����ť�����¼�	
	deleteBut.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e){	   
			int index = table.getSelectedRow();
			for(int i=index;i<j-1;i++){//ɾ��ѧ�����Ƿ���ѡ�еĵ�i��Ԫ�أ��ȶ��ڴ��е������������������i֮���Ԫ��һ��ǰ��һλ�����ǵ�i����д���ļ���
				ss[i]=ss[i+1];		
			}
			j--;
			//rewrite();
			model.setRowCount(0);
			//reload();
			JOptionPane.showMessageDialog(table,"ɾ���ɹ�");
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
//////////////////////////////////////////////////////////////////////////////////////////		
		
	}
		
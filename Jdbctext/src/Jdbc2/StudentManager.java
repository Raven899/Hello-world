package Jdbc2;

import java.awt.Component;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import Homework.Drug1;


public class StudentManager {
	private ArrayList<Student> students = new ArrayList<>();

	public void addStudent(Student s) {
		students.add(s);
		updateDatabase();
		MainFrame.refreshTableModel();
	}
	

	public void deleteStudent(Student s) {

		int len = students.size();
		for (int i = 0; i < len; i++) {
			if (s.isEqual(students.get(i))) {
				students.remove(i);
				break;
			}
		}
		updateDatabase();
		MainFrame.refreshTableModel();
	}///////////////////////////////////////////////////////////////////
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
						String[][] ss;
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
	public void sarchStudent(Student s1) {
	
		int len = students.size();
		for (int i = 0; i < len; i++) {
			if (s1.isEqual(students.get(i))) {
				JOptionPane.showMessageDialog(null, "该生信息如下：\n"+"学号："+students.get(i).getId()+"\n"+"姓名："+students.get(i).getName()+"\n"+"学院："+students.get(i).getfName()+"\n"+"专业："+students.get(i).getCollege()+"\n"+"电话号码："+students.get(i).getPhnNumber(), null, i);
				 break;
			}
		}
		updateDatabase();
		MainFrame.refreshTableModel();
	}
	public void updateStudent(Student before, Student after) {
		int len = students.size();
		for (int i = 0; i < len; i++) {
			if (before.isEqual(students.get(i))) {
				students.get(i).setId(after.getId());
				students.get(i).setName(after.getName());
				students.get(i).setfName(after.getfName());
				students.get(i).setCollege(after.getCollege());
				students.get(i).setPhnNumber(after.getPhnNumber());
				break;
			}
		}

		updateDatabase();
		MainFrame.refreshTableModel();
	}

	public void updateDatabase() {

		File file = new File("file.txt");
		int len = students.size();
		try {
			Formatter ff = new Formatter(file);
			for (int i = 0; i < len; i++) {
				ff.format("%s\n", students.get(i).getId());
				ff.format("%s\n", students.get(i).getName());
				ff.format("%s\n", students.get(i).getfName());
				ff.format("%s\n", students.get(i).getCollege());
				ff.format("%s\n", students.get(i).getPhnNumber());
			}
			ff.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void initialize() {

		try {
			File file = new File("file.txt");
			Scanner scanner = new Scanner(file);
			students = new ArrayList<Student>();
			while (scanner.hasNextLine()) {
				students.add(new Student(Integer.parseInt(scanner.nextLine()), scanner.nextLine(), scanner.nextLine(),
						scanner.nextLine(), scanner.nextLine()));
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public DefaultTableModel getTableModel() {
		String[] str = { "学号", "姓名", "学院", "专业", "电话" };
		DefaultTableModel dtm = new DefaultTableModel(str, 0);

		int len = students.size();
		for (int i = 0; i < len; i++) {
			String[] strings = new String[5];
			strings[0] = String.valueOf(students.get(i).getId());
			strings[1] = students.get(i).getName();
			strings[2] = students.get(i).getfName();
			strings[3] = students.get(i).getCollege();
			strings[4] = students.get(i).getPhnNumber();
			dtm.addRow(strings);
		}
		return dtm;
	}

}

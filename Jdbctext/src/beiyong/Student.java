package beiyong;

import java.io.Serializable;

public class Student implements Serializable{
		long number;
		String name;
		String sex;
		int age;
		String major;
		
		public  Student(long id, String name,String sex, int age, String major) {
		this.number = id;
		this.name=name;
		this.sex=sex;
		this.age = age;
		this.major = major;
		
		}
		}
package Jdbc2;

import java.sql.Date;

/**
 * @author ÈðÎÄ
 *
 */
public class Emp {
	private Integer id;
	private  String empname;
	private Integer age;
	private double salary;
	private Date birthday;
	private Integer deptId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEmpname() {
		return empname;
	}
	public void setEmpname(String empname) {
		this.empname = empname;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	public Emp(Integer id, String empname, Integer age, double salary, Date birthday, Integer deptId) {
		super();
		this.id = id;
		this.empname = empname;
		this.age = age;
		this.salary = salary;
		this.birthday = birthday;
		this.deptId = deptId;
	}
	public Emp(String empname, Integer age, double salary, Date birthday, Integer deptId) {
		super();
		this.empname = empname;
		this.age = age;
		this.salary = salary;
		this.birthday = birthday;
		this.deptId = deptId;
	}
	
	public Emp() {
		
		
	}

	

}

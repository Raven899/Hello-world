package Jdbc2;


public class Student {

	int id;
	String name;
	private String fName;
	private String college;
	private String phnNumber;
	public String major;
	public int age;
	public String sex;
	public long number;
	public Student(int id, String name, String fName, String college, String phnNumber) {
		this.id = id;
		this.name = name;
		this.fName = fName;
		this.college = college;
		this.phnNumber = phnNumber;
	}

	public Student(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getPhnNumber() {
		return phnNumber;
	}

	public void setPhnNumber(String phnNumber) {
		this.phnNumber = phnNumber;
	}

	public boolean isEqual(Student s) {
		if (this.id == s.id)
			return true;
		return false;
	}

}

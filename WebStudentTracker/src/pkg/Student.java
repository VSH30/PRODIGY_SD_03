package pkg;

public class Student {
	private String first;
	private String last;
	private String email;
	private int id;
	private String mob;
	
	public Student(String first, String last, String email, int id, String mob) {
		super();
		this.first = first;
		this.last = last;
		this.email = email;
		this.id = id;
		this.mob = mob;
	}
	public Student(String first, String last, String email, String mob) {
		super();
		this.first = first;
		this.last = last;
		this.email = email;
		this.mob = mob;
	}
	public String getFirst() {
		return first;
	}
	public void setFirst(String first) {
		this.first = first;
	}
	public String getLast() {
		return last;
	}
	public void setLast(String last) {
		this.last = last;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getMob() {
		return mob;
	}
	public void setMob(String mob) {
		this.mob = mob;
	}
	@Override
	public String toString() {
		return "Student [first=" + first + ", last=" + last + ", email=" + email + "]";
	}
}

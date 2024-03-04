package pkg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class StudentDBUtil {
	private DataSource dataSource;
	
	public StudentDBUtil(DataSource theDataSource){
		dataSource = theDataSource;
	}
	
	public List<Student> getStudents() throws Exception{
		List<Student> students = new ArrayList<>();
		
		Connection conn = null;
		Statement stm = null;
		ResultSet rs = null;
		
		try {
			//get a conn
			conn = dataSource.getConnection();
			
			//create sql statemetn
			String sql = "SELECT * FROM student";
			stm = conn.createStatement();
			
			//execute query
			rs = stm.executeQuery(sql);
			
			//process result set
			while(rs.next()) {
				//get data from rs
				String first = rs.getString("fn");
				String last = rs.getString("ln");
				String email = rs.getString("email");
				int id = rs.getInt("id");
				String mob = rs.getString("mob");
				
				//create stud objs
				Student tempStud = new Student(first,last,email,id,mob);
				
				//add to list
				students.add(tempStud);
			}
				
			return students;
		}finally {
			//close JDBC objs
			close(rs,stm,conn);
		}
		
	}

	private void close(ResultSet rs, Statement stm, Connection conn) throws Exception{
		if(rs!=null)
			rs.close();
		if(stm!=null)
			stm.close();
		if(conn!=null)
			conn.close();
	}

	public void addStudent(Student newStud) throws Exception{
		
		Connection conn = null;
		PreparedStatement stm = null;
		
		conn = dataSource.getConnection();
		
		//create sql to ins
		String sql = "INSERT INTO student "
					+ "(fn, ln, email, mob) "
					+ "values(?, ?, ?, ?)";
		
		stm = conn.prepareStatement(sql);
		
		//set param values for stud
		stm.setString(1, newStud.getFirst());
		stm.setString(2, newStud.getLast());
		stm.setString(3, newStud.getEmail());
		stm.setString(4, newStud.getMob());
		//execute sql
		stm.execute();
		
		//close jdbc objs
		close(null,stm,conn);
	}

	public Student getStudent(String id) throws Exception{
		Student stud = null;
		
		Connection conn = dataSource.getConnection();
		String sql = "SELECT * FROM STUDENT WHERE id=?";
		PreparedStatement stm = conn.prepareStatement(sql);
		
		int ID = Integer.parseInt(id);
		stm.setInt(1, ID);
		
		ResultSet rs = stm.executeQuery();
		
		if(rs.next())
			stud = new Student(rs.getString("fn"),rs.getString("ln"),rs.getString("email"),ID,rs.getString("mob"));
		
		close(rs,stm,conn);
		
		return stud;
	}

	public void updateStudent(Student stud) throws Exception{
		Connection conn = dataSource.getConnection();
		String sql = "UPDATE student SET fn=?, ln=?, email=?, mob=? WHERE id=?";
		
		PreparedStatement stm = conn.prepareStatement(sql);
		
		stm.setString(1, stud.getFirst());
		stm.setString(2, stud.getLast());
		stm.setString(3, stud.getEmail());
		stm.setString(4, stud.getMob());
		stm.setInt(5, stud.getId());
		
		stm.execute();
		
		close(null,stm,conn);
	}

	public void deleteStudent(int id) throws Exception{
		Connection conn = dataSource.getConnection();
		String sql = "DELETE FROM student WHERE id=?";
		
		PreparedStatement stm = conn.prepareStatement(sql);
		
		stm.setInt(1, id);
		
		stm.execute();
		
		close(null,stm,conn);
	}
}

package pkg;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


@WebServlet("/StudentControllerServlet")
public class StudentControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private StudentDBUtil studentDbUtil;
	
	@Resource(name="jdbc/web_student_tracker")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		try {
			studentDbUtil = new StudentDBUtil(dataSource);
		}catch(Exception e) {
			throw new ServletException(e);
		}
	}

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			//read command param
			String theCommand = request.getParameter("command");
			if(theCommand == null) {
				theCommand = "LIST";
			}
			
			//route to appropriate method
			switch(theCommand) {
			case "LIST":
				list(request,response);
				break;
			case "ADD":
				addStudent(request,response);
				break;
			case "LOAD":
				loadStudent(request,response);
				break;
			case "UPDATE":
				updateStudent(request,response);
				break;
			case "DELETE":
				deleteStudent(request,response);
				break;
			default:
				list(request,response);

			}
		}catch(Exception e) {
			throw new ServletException(e);
		}
	}

	private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//read stud id
		int id = Integer.parseInt(request.getParameter("studentId"));
		
		//perform delete
		studentDbUtil.deleteStudent(id);
		
		//list or view
		list(request,response);
	}

	private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//read data from form
		int id = Integer.parseInt(request.getParameter("studentId"));
		String fn = request.getParameter("fn");
		String ln = request.getParameter("ln");
		String email = request.getParameter("email");
		String mob = request.getParameter("mob");
		
		//create stud obj
		Student stud = new Student(fn,ln,email,id,mob);
		
		//perform update
		studentDbUtil.updateStudent(stud);
		
		//send to jsp or display
		list(request,response);
	}

	private void loadStudent(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//read stud id from form
		String id = request.getParameter("studentId");
		
		//get stud from db
		Student stud_data = studentDbUtil.getStudent(id);
		
		//set stud attribute
		request.setAttribute("student_data", stud_data);
		
		//send to jsp
		request.getRequestDispatcher("/update-student-form.jsp").forward(request, response);
	}

	private void addStudent(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//read student info from form
		String fn = request.getParameter("fn");
		String ln = request.getParameter("ln");
		String email = request.getParameter("email");
		String mob = request.getParameter("mob");
		//create new obj
		Student newStud = new Student(fn,ln,email,mob);
		
		//add to db
		studentDbUtil.addStudent(newStud);
		
		
		//send to view
		list(request,response);
	}

	private void list(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//Display or send to jsp
		List<Student> students;
		students = studentDbUtil.getStudents();
		request.setAttribute("STUDENT_LIST", students);
		request.getRequestDispatcher("/list-students.jsp").forward(request, response);
	}
}

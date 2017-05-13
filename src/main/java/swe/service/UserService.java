package swe.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import swe.model.Student;
import swe.model.Teacher;
import swe.model.User;

@Service
public class UserService {

	static User loggedin;
	static String type;
	
	public int getUserType(User u) {
		int type = 0; // 1 = teacher, 2= student 0 = invalid
		if (TeacherService.getTeacher(u.getUsername()) != null) {
			type = 1;
		}
		else if (StudentService.getStudent(u.getUsername()) != null) {
			type = 2;
		}
		else type = 0;
		//System.out.println(ss.getLoggedin().getUsername() + " uff bgd");
		return type;
	}
	public static User getLoggedin() {
		return loggedin;
	}
	
	public static int validate(User u) {
		ArrayList<Teacher> teachers = TeacherService.getAllTeachers();
		
		for (int i = 0; i < teachers.size(); i++) {
			if (teachers.get(i).getUsername().equals(u.getUsername()) &&
					teachers.get(i).getPassword().equals(u.getPassword())) {
				loggedin = teachers.get(i);
				type = "teacher";
				return 1;
			}
		}
		ArrayList<Student> students = StudentService.getAllStudents();
		for (int i = 0; i < students.size(); i++) {
			if (students.get(i).getUsername().equals(u.getUsername()) &&
					students.get(i).getPassword().equals(u.getPassword())) {
				loggedin = students.get(i);
				type = "student";
				return 2;
			}
		}
		return 0;
		
	}
	
	public static String getUserType() {
		return type;
	}
	
	public Teacher getTeacher(User user) {
		return TeacherService.getTeacher(user.getUsername());
	}
	public Student getStudent(User user) {
		return StudentService.getStudent(user.getUsername());
	}
		

}

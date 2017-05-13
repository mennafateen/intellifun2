package swe.service;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.stereotype.Service;

import swe.model.Student;

@Service
public class StudentService {
		
	private static ArrayList<Student> students = new ArrayList<>(Arrays.asList (
			 new Student("Hinata Shouyou", "mini-spiderman", "male", "littlegiant@gmail.com", "123", "h.jpeg", 1),
			 new Student("Kageyama Tobio", "king", "male", "illsurpassoikawa@gmail.com", "123", "k.jpg", 2),
			 new Student("Tsukkishima Kei","tsukki", "male", "sarcasticaf@gmail.com", "123", "t.jpg", 3),
			 new Student("Yamaguchi Tadashi", "tadashi", "male", "imnotgoodenough@gmail.com", "123", "y.png", 4)
			));	
		
	public static ArrayList<Student> getAllStudents() {
		return students; 
	}

	public static void addStudent(Student s) {
		students.add(s);
		System.out.println("new student: " + students.get(students.size() - 1).getUsername());
		//loggedin = s;
	}	
	
	public static Student getStudent(String username) {
		for (int i = 0; i < students.size(); i++) {
			if (students.get(i).getUsername().equals(username)) {
				//loggedin = students.get(i);
				System.out.println(" student: " + students.get(students.size() - 1).getUsername());

				return students.get(i); 
			}
		}
		return null;
	}
	
	// that's redundant now
	public static Student findStudent(String username) {
		return students.stream().filter(s -> s.getUsername().equals(username)).findFirst().get();
	}
	
	
		
}

package swe.service;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.stereotype.Service;

import swe.model.Game;
import swe.model.QuestionMCQ;
import swe.model.Teacher;

@Service
public class TeacherService {
		
	private static ArrayList<QuestionMCQ> qs = new ArrayList<>(3);
//	Arrays.asList( new QuestionMCQ("question1", "a1", "a2", "a3", "a4", "a1"),
//				   new QuestionMCQ("question2", "a1", "a2", "a3", "a4", "a1"),
//			       new QuestionMCQ("question3", "a1", "a2", "a3", "a4", "a1")
//			    ));
//	
	
	private static ArrayList<Game> games = new ArrayList<>(Arrays.asList (
			new Game(1, "TF", "game1", qs),
			new Game(2, "MCQ", "game2", qs),
			new Game(3, "TF", "game3", qs)
			));
	
	private static ArrayList<Teacher> teachers = new ArrayList<>(Arrays.asList (
			 new Teacher("Takeda Sensei", "sensei", "male", "sensei@gmail.com", "123", "s.jpeg", games),
			 new Teacher("Ukai Coach", "coach", "male", "coach@gmail.com", "123", "c.jpg"),
			 new Teacher("Teacher3","teach3", "male", "teach3@gmail.com", "123", "default-user.png", games),
			 new Teacher("Teacher4", "teach4", "male", "teach4@gmail.com", "123", "default-user.png", games)
			));	
		
	public static ArrayList<Teacher> getAllTeachers() {
		return teachers; 
	}
	
	public static void addTeacher(Teacher t) {
		teachers.add(t);
	//	loggedin = t;
	}
	
	// thats redundant now
	public static Teacher findTeacher(String username) {
		return teachers.stream().filter(t -> t.getUsername().equals(username)).findFirst().get();
	}
	
	public static Teacher getTeacher(String username) {
		for (int i = 0; i < teachers.size(); i++) {
			if (teachers.get(i).getUsername().equals(username)) {
			//	loggedin = teachers.get(i);
				return teachers.get(i); 
			}
		}
		return null;
	}
	
}

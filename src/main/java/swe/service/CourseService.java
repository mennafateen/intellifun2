package swe.service;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.stereotype.Service;

import swe.model.Course;
import swe.model.Game;
import swe.model.QuestionMCQ;

@Service
public class CourseService {

	private static  ArrayList<QuestionMCQ> qs = new ArrayList<>(Arrays.asList(
			new QuestionMCQ("question1", "a1", "a2", "a3", "a4", "a1"),
			new QuestionMCQ("question2", "a1", "a2", "a3", "a4", "a1"),
			new QuestionMCQ("question3", "a1", "a2", "a3", "a4", "a1")
			));
	
	private static  ArrayList<Game> games = new ArrayList<>(Arrays.asList (
			new Game(1, "TF", "game1", qs, false),
			new Game(2, "MCQ", "game2", qs),
			new Game(3, "TF", "game3", qs)
			));
	
	private static  ArrayList<Course> courses = new ArrayList<>(Arrays.asList (
			 new Course(1, "Math 101", "Math 101 desription", games),
			 new Course(2, "Math 102", "Math 102 description", games)
			 ));
	
	
	public static void addCourse(Course c) {
		courses.add(c); // add game to database 
	}
	
	public static Course getCourse (int id) {
		for (int i = 0; i < courses.size(); i++) {
			if (courses.get(i).getId() == id) {
				return courses.get(i); 
			}
		}
		return null;
	}
	
	public static void createCourse (String name, String description) {
		Course newCourse = new Course(courses.size() + 1, name, description);
		addCourse(newCourse);
	}
	
	
	public static ArrayList<Course> viewCourses() {
		return courses;
	}
	
	
	
}

package swe.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import swe.model.Course;
import swe.model.Game;
import swe.service.CourseService;
import swe.service.UserService;

@Controller
@RequestMapping("/")
public class CourseController {
	
	// create course stuff
	@RequestMapping(value = "createCourseForm", method = RequestMethod.GET)
	public String createCourse (Model model) {
		Course course = new Course();
		model.addAttribute("course", course);
		return "/createCourseForm";
	}
		
	@RequestMapping(value = "createCourseForm", method = RequestMethod.POST)
	public String createCourse (@ModelAttribute(value = "course") Course c) {		
		
		CourseService.createCourse(c.getName(), c.getDescription()); 
		return "/courseInfo";
	}
	
	// view courses
	@RequestMapping(value = "viewCourses", method = RequestMethod.GET)
	public String viewCourses (Model model) {
		ArrayList<Course> courses = CourseService.viewCourses();
		model.addAttribute("courses", courses);
		model.addAttribute("type", UserService.getUserType());
		return "/viewCourses";
	}
	
	@RequestMapping(value = "course/{id}")
    public String showCourse (@PathVariable int id, Model model) {
		Course course = CourseService.getCourse(id);
        ArrayList<Game> games = course.getGames();
        model.addAttribute("course", course);
        //model.addAttribute("user", userService.getLoggedin()); // for different teachers
        model.addAttribute("games", games);
        model.addAttribute("type", UserService.getUserType());
        return "courseInfo";
    }
	
}








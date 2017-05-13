package swe.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import swe.model.Comment;
import swe.model.Course;
import swe.model.Game;
import swe.model.QuestionMCQ;
import swe.model.Teacher;
import swe.service.CourseService;
import swe.service.GameService;
import swe.service.TeacherService;
import swe.service.UserService;

@Controller
@RequestMapping("/")
public class GameController {
	
	static Game game = new Game();
	QuestionMCQ question = new QuestionMCQ();
	
	// create game stuff
	@RequestMapping(value = "createGameForm/{id}", method = RequestMethod.GET)
	public String create (Model model, @PathVariable int id) {
		System.out.println("create game form");
		model.addAttribute("game", game);
		model.addAttribute("course", CourseService.getCourse(id));
		System.out.println(CourseService.getCourse(id).getName());
		
		return "/createGameForm";
	}
	
	@RequestMapping(value = "createGameForm/{id}", method = RequestMethod.POST)
	public String createGame (@ModelAttribute(value = "game") Game game, @ModelAttribute(value = "course") Course course, Model model) {
		System.out.println(game.getName() + " hmm");
		GameService.addGame(game);
		
		System.out.println(course.getName() + " naaame");
		course.addGameToCourse(game);
		Teacher teacher = (Teacher) UserService.getLoggedin();
		teacher.addGame(game);
		return "redirect:/newQuestion/" + game.getId();
	}
	
	@RequestMapping(value = "newQuestion/{id}", method = RequestMethod.GET)
	public String createGame (Model model, @PathVariable int id, @ModelAttribute(value = "question") QuestionMCQ question) {
		
		game = GameService.findGame(id);
		model.addAttribute("question", question);
		model.addAttribute("game", game);
		System.out.println("the game we're modifying: " + game.getName());
		game.addQuestion(question);
		System.out.println(question.getQuestionhead());
		if (game.getQuestions() != null) {
			for (int i = 0; i < game.getQuestions().size(); i++) {
				if (game.getQuestions().get(i) != null) 
					System.out.println(game.getQuestions().get(i).getQuestionhead() + " dafsdfsd");
				else System.out.println("uf");
				}
			}
		else System.out.println("mafish haga");
		
		return "/newQuestion";
		
	}
	
	@RequestMapping(value = "gameInf/{id}")
	public String gameInfo (Model model, @PathVariable int id) {		
		game = GameService.findGame(id);
		model.addAttribute("game", game);
		System.out.println(game.getName());
		ArrayList<QuestionMCQ> questions = game.getQuestions();
		model.addAttribute("questions", questions);
		return "/gameInfo";
	}
		
	
	// play game stuff
	@RequestMapping(value = "playGame/{id}", method = RequestMethod.GET)
	public String play (@PathVariable int id, Model model) {
		game = GameService.findGame(id);
		System.out.println(game.getQuestions().size());
		
		System.out.println(game.getName());
		System.out.println(game.getQuestions().get(0).getChoice1() + " fe eh");
		
		ArrayList<String> userAnswers = new ArrayList<>(10);
		model.addAttribute("userAnswers", userAnswers);
		model.addAttribute("game", game);

		return "/playGame";
	}
	
	// fix meee!
	@RequestMapping(value = "playGame/{id}", method = RequestMethod.POST)
	public String getGame (@PathVariable int id, Model model, @ModelAttribute(value = "game") Game game) {		
			
		//System.out.println("hellooooo" + score); // doesnt get printed
		//int score = gameService.checkAnswers(game, usergame);

		return "/myerror";
		//return "redirect:/gameInfo/" + usergame.getId() + ".html";
	}
	
	// view game stuff
	@RequestMapping(value = "game/{id}", method = RequestMethod.GET)
	public String viewGameHome (@PathVariable int id, Model model) {
		game = GameService.findGame(id);
		
		model.addAttribute("user", UserService.getLoggedin());
		model.addAttribute("game", game);
		model.addAttribute("type", UserService.getUserType());
		String username = "";
		model.addAttribute("username", username);
		model.addAttribute("id", id);
		
		System.out.println(game.isActive());
		System.out.println("comments size: " + game.getComments().size());
		
		return "/gameHome";
	}
	
	// add collab stuff
	@RequestMapping(value = "addCollab/{id}", method = RequestMethod.GET)
	public String addCollab (@PathVariable int id, Model model) {
		game = GameService.findGame(id);
		Teacher teacher = new Teacher();
		model.addAttribute("game", game);
		model.addAttribute("teacher", teacher);

		return "/addCollab";
	}
	
	@RequestMapping(value = "addCollab/{id}", method = RequestMethod.POST)
	public String addCollab (@PathVariable int id, @ModelAttribute(value = "teacher") Teacher teacher, Model model) {
		String username = teacher.getUsername();
		Teacher collab = TeacherService.findTeacher(username);
		System.out.println(game.getName());
		collab.addGame(game);
		model.addAttribute(game);
		return "redirect:/game/" + id;
	}
	
	@RequestMapping(value = "viewGames", method = RequestMethod.GET)
	public String viewGames (Model model) {
		
		Teacher teacher = (Teacher) UserService.getLoggedin();
		model.addAttribute("user", UserService.getLoggedin());
		model.addAttribute("listOfGames", teacher.getListOfGames());
		model.addAttribute("type", UserService.getUserType());

		return "/viewGames";
	}
	
	// copy game stuff
	@RequestMapping(value = "copyGame/{id}", method = RequestMethod.GET)
	public String copyGame (@PathVariable int id, Model model) {
		game = GameService.findGame(id);
		Teacher teacher = (Teacher) UserService.getLoggedin();
		model.addAttribute("user", UserService.getLoggedin());
		System.out.println("teacher: " + teacher.getUsername());
		//System.out.println("size of teacher games: " + teacher.getListOfGames().size());
		model.addAttribute("game", game);
		model.addAttribute("type", UserService.getUserType());
		teacher.addGame(game);

		return "redirect:/viewGames";
	}
	
	// activate game stuff
	@RequestMapping(value = "activateGame/{id}", method = RequestMethod.GET)
	public String activateGame (@PathVariable int id, Model model) {
		game = GameService.findGame(id);
		game.setActive(true);
		Teacher teacher = (Teacher) UserService.getLoggedin();
		
		for (int i = 0; i < teacher.getListOfGames().size(); i++)
			if (teacher.getListOfGames().get(i).getId() == id)
				teacher.getListOfGames().get(i).setActive(true);
		
		model.addAttribute("game", game);
		model.addAttribute("type", UserService.getUserType());
		
		return "redirect:/game/" + game.getId();
	}
	
	// deactivate game stuff
	@RequestMapping(value = "deactivateGame/{id}", method = RequestMethod.GET)
	public String deactivateGame (@PathVariable int id, Model model) {
		game = GameService.findGame(id);
		game.setActive(false);
		Teacher teacher = (Teacher) UserService.getLoggedin();
			
		for (int i = 0; i < teacher.getListOfGames().size(); i++)
			if (teacher.getListOfGames().get(i).getId() == id)
				teacher.getListOfGames().get(i).setActive(false);
			
		model.addAttribute("game", game);
		model.addAttribute("type", UserService.getUserType());
			
		return "redirect:/game/" + game.getId();
	}
	
	// add comment stuff
	@RequestMapping(value = "commentForm/{id}", method = RequestMethod.GET)
	public String addComment (@PathVariable int id, Model model) {
		game = GameService.findGame(id);
		Comment comment = new Comment();
		model.addAttribute("game", game);
		model.addAttribute("comment", comment);
		model.addAttribute("user", UserService.getLoggedin());

		return "/commentForm";
	}
		
	@RequestMapping(value = "commentForm/{id}", method = RequestMethod.POST)
	public String addComment (@PathVariable int id, @ModelAttribute(value = "comment") Comment comment, @ModelAttribute(value = "game") Game game, Model model) {
		comment.setUser(UserService.getLoggedin());
		GameService.findGame(id).addComment(comment);
		System.out.println("comment username: " + comment.getUser().getUsername());
		System.out.println("size of comments hena tayeb? " + game.getComments().size());
		System.out.println(comment.getThecomment());
		System.out.println(game.getName());
		model.addAttribute(game);
		
		return "redirect:/game/" + id;
	}
		
	
	
	@RequestMapping(value = "gameInfo/{id}" , method = RequestMethod.GET)
	public String gameInfo (@PathVariable int id, ModelMap modelMap ) {		
		Game game = GameService.findGame(id);
		modelMap.put("game", game);
		return "/gameInfo";
	}

	
	
}







package swe.model;

import java.util.ArrayList;
import swe.model.Game;

public class Teacher extends User {
	
	private ArrayList<Game> listOfGames = new ArrayList<Game>();
	
	public Teacher() {
		super();
	}
	
	public Teacher(String name, String username, String gender, String email, String password, String pic) {
		super(name, username, gender, email, password, pic);
	}
	
	public Teacher(String name, String username, String gender, String email, String password, String pic, ArrayList<Game> games) {
		super(name, username, gender, email, password, pic);
		this.listOfGames = games;
	}

	public ArrayList<Game> getListOfGames() {
		return listOfGames;
	}

	public void setListOfGames(ArrayList<Game> listOfGames) {
		this.listOfGames = listOfGames;
	}
	
	public void addGame(Game g) {
		listOfGames.add(g);
	}
	
}

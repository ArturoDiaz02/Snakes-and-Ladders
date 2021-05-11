package model;

import java.io.Serializable;

public class Players implements Serializable{

	private static final long serialVersionUID = 3L;
	private String name;
	private Character token;
	private int movement;
	private int score;
	private int currentBox;

	public Players(Character token) {
		this.name = null;
		this.token = token;
		this.movement = 0;
		this.currentBox = 1;
		this.score = 0;
	}

	public int getScore() {
		return this.score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Character getToken() {
		return token;
	}

	public void setToken(Character token) {
		this.token = token;
	}

	public int getMovement() {
		return movement;
	}

	public void setMovement(int movement) {
		this.movement = movement;
	}

	public int getCurrentBox() {
		return currentBox;
	}

	public void setCurrentBox(int currentBox) {
		this.currentBox = currentBox;
	}
	
	
	
	
}

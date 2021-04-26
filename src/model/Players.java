package model;

import java.io.Serializable;

public class Players implements Serializable{

	private static final long serialVersionUID = 3L;
	private String name;
	private Character token;
	private int movement;
	private int missingBoxes;
	private int currentBox;
	
	public Players(Character token, int missingBoxes) {
		this.name = null;
		this.token = token;
		this.movement = 0;
		this.missingBoxes = missingBoxes;
		this.currentBox = 0;
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

	public int getMissingBoxes() {
		return missingBoxes;
	}

	public void setMissingBoxes(int missingBoxes) {
		this.missingBoxes = missingBoxes;
	}

	public int getCurrentBox() {
		return currentBox;
	}

	public void setCurrentBox(int currentBox) {
		this.currentBox = currentBox;
	}
	
	
	
	
}

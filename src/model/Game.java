package model;

import java.io.Serializable;

import model.LinkedList.MeLinkedLists;

public class Game implements Serializable{

	private static final long serialVersionUID = 1L;
	private MeLinkedLists<Box> boxs;
	private MeLinkedLists<Players> leaderBoard;
	private int colums;
	private int rows;

	public Game() {
		boxs = new MeLinkedLists<>();
		leaderBoard =  new MeLinkedLists<>();
		colums = 0;
		rows = 0;		
	}
	

	public int getColums() {
		return this.colums;
	}

	public int getRows() {
		return this.rows;
	}

	public void setColums(int colums) {
		this.colums = colums;
	}
	
	public void setRows(int rows) {
		this.rows = rows;
	}

	public MeLinkedLists<Players> getLeaderBoard() {
		return leaderBoard;
	}

	public void setLeaderBoard(MeLinkedLists<Players> leaderBoard) {
		this.leaderBoard = leaderBoard;
	}

	public MeLinkedLists<Box> getBoxs() {
		return boxs;
	}

	public void setBoxs(MeLinkedLists<Box> boxs) {
		this.boxs = boxs;
	}
	
	
	
	
}

package model;

import java.io.Serializable;

import model.LinkedList.*;

public class Game implements Serializable{

	private static final long serialVersionUID = 1L;
	/**
	 * boxes List
	 */
	private MeLinkedLists<Box> boxs;
	/**
	 * winners list
	 */
	private BinaryTree leaderBoard;
	/**
	 * players list
	 */
	private MeLinkedLists <Players> players;
	/**
	 * colums
	 */
	private int colums;
	/**
	 * rows
	 */
	private int rows;

	/**
	 * Game constructor
	 */
	public Game() {
		boxs = new MeLinkedLists<>();
		leaderBoard =  new BinaryTree();
		players = new MeLinkedLists<>();
		colums = 0;
		rows = 0;		
	}

	/**
	 * Get MeLinkedLists Players
	 * @return Players
	 */
	public MeLinkedLists<Players> getPlayers() {
		return this.players;
	}

	/**
	 * Set MeLinkedLists Players
	 * @param players
	 */
	public void setPlayers(MeLinkedLists<Players> players) {
		this.players = players;
	}
	
	/**
	 * Get colums
	 * @return colums
	 */
	public int getColums() {
		return this.colums;
	}

	/**
	 * Get rows
	 * @return rows
	 */
	public int getRows() {
		return this.rows;
	}

	/**
	 * Set colums
	 * @param colums
	 */
	public void setColums(int colums) {
		this.colums = colums;
	}

	/**
	 * Set rows
	 * @param rows
	 */
	public void setRows(int rows) {
		this.rows = rows;
	}

	/**
	 * Get leaderBoard
	 * @return leaderBoard
	 */
	public BinaryTree getLeaderBoard() {
		return leaderBoard;
	}

	/**
	 * Set leaderBoard
	 * @param leaderBoard
	 */
	public void setLeaderBoard(BinaryTree leaderBoard) {
		this.leaderBoard = leaderBoard;
	}

	/**
	 * Get boxs
	 * @return boxs
	 */
	public MeLinkedLists<Box> getBoxs() {
		return boxs;
	}

	/**
	 * Set boxs
	 * @param boxs
	 */
	public void setBoxs(MeLinkedLists<Box> boxs) {
		this.boxs = boxs;
	}

	/**
	 * moves players according to the data and only allows you to win if you land directly on the last square
	 * @param token player token
	 * @param dice die value
	 * @return return according to whether the no was won
	 */

	public boolean playerMove(Character token, int dice){

		Players player = searchBox(token, 0, 0, 0).getPlayers().get(0);
		player.setCurrentBox(player.getCurrentBox() + dice);
		
		if(player.getCurrentBox() == colums*rows){
			player.setMovement(player.getMovement() + 1);
			return true;

		}else if(player.getCurrentBox() > colums*rows){
			int difference = colums*rows - (player.getCurrentBox() - dice);
			getBoxs().get(searchPosition(player.getCurrentBox() - dice, 0)).getPlayers().remove(0);
			player.setCurrentBox(colums*rows);
			getBoxs().get(colums*rows - colums).getPlayers().add(player);

			if(dice - difference == colums*rows){
				return playerMove(token, (colums*rows - 1) * (-1));

			}else{
				return playerMove(token, (dice - difference) * (-1));
			}
			
			
		}else{

			int index = searchPosition(player.getCurrentBox(), 0);
			searchBox(token, 0, 0, 0).getPlayers().remove(0);
			player.setMovement(player.getMovement() + 1);

			if(getBoxs().get(index).getTypeAction()){
				player.setCurrentBox(getBoxs().get(getBoxs().get(index).getSendTo()).getNumBoxInt());
				getBoxs().get(getBoxs().get(index).getSendTo()).getPlayers().add(player);
				System.out.println("*. El jugador cayo en una casilla especial por ende su ficha queda en: " + player.getCurrentBox());
			}else{
				getBoxs().get(index).getPlayers().add(player);
			
			}

			return false;
		}
		

	}

	/**
	 * search in which box a player is located by his token
	 * @param token player token
	 * @param indexBox 
	 * @param indexPlayer
	 * @param contador
	 * @return return the box where the player is
	 */
	public Box searchBox(Character token, int indexBox, int indexPlayer, int contador){

		if (getBoxs().get(indexBox).getPlayers().size() > contador) {

			if(getBoxs().get(indexBox).getPlayerToken(indexPlayer).equals(token)){
				return getBoxs().get(indexBox);

			}else if(indexPlayer == getBoxs().get(indexBox).getPlayers().size() - 1){
				return searchBox(token, indexBox + 1, 0, 0);

			}else{
				return searchBox(token, indexBox , indexPlayer + 1, contador + 1);

			}
	
		
		}else{
			return searchBox(token, indexBox + 1 , 0, 0);
		}
		
		
	}

	/**
	 * finds the index of a cell based on its value
	 * @param num value
	 * @param index cell
	 * @return
	 */

	private int searchPosition(int num, int index){

		if(getBoxs().get(index).getNumBoxInt() == num){
            return getBoxs().indexOf(getBoxs().get(index));

        }else{
            return searchPosition(num, index + 1);

        }
			
		
	}
	
	
	
}

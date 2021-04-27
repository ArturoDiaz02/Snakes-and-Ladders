package model;

import java.io.Serializable;

import model.LinkedList.MeLinkedLists;

public class Game implements Serializable{

	private static final long serialVersionUID = 1L;
	private MeLinkedLists<Box> boxs;
	private MeLinkedLists<Players> leaderBoard;
	private MeLinkedLists <Players> players;
	private int colums;
	private int rows;

	public Game() {
		boxs = new MeLinkedLists<>();
		leaderBoard =  new MeLinkedLists<>();
		players = new MeLinkedLists<>();
		colums = 0;
		rows = 0;		
	}

	public MeLinkedLists<Players> getPlayers() {
		return this.players;
	}

	public void setPlayers(MeLinkedLists<Players> players) {
		this.players = players;
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

	public boolean playerMove(Character token, int dice){

		Players player = searchBox(token, 0, 0, 0).getPlayers().get(0);
		player.setCurrentBox(player.getCurrentBox() + dice);
		int index = searchPosition(player.getCurrentBox(), 0);
		searchBox(token, 0, 0, 0).getPlayers().remove(0);
		player.setMovement(player.getMovement() + 1);

		if(player.getCurrentBox() == colums*rows){

			return true;

		}else if(player.getCurrentBox() > colums*rows){
			int difference = player.getCurrentBox() - colums*rows;
			player.setCurrentBox(colums*rows);
			getBoxs().get(colums*rows - colums).getPlayers().add(player);
			return playerMove(token, difference * (-1));
			
		}else{

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


	private Box searchBox(Character token, int indexBox, int indexPlayer, int contador){

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

	private int searchPosition(int num, int index){

		if(getBoxs().get(index).getNumBoxInt() == num){
            return getBoxs().indexOf(getBoxs().get(index));

        }else{
            return searchPosition(num, index + 1);

        }
			
		
	}
	
	
	
}

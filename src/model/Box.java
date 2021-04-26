package model;

import java.io.Serializable;

import model.LinkedList.MeLinkedLists;

public class Box implements Serializable{
	
	private static final long serialVersionUID = 2L;

	private String numBox;
	private boolean action;
	private boolean typeAction;
	private String idAction;
	private int sendTo;
	private MeLinkedLists<Players> players;
	
	public Box(String num) {
		this.numBox = num;
		this.action = false;
		this.typeAction = false;
		this.players = new MeLinkedLists<>();
		this.idAction = "";
		
	}

	public boolean getAction() {
		return this.action;
	}


	public boolean getTypeAction() {
		return this.typeAction;
	}


	public int getSendTo() {
		return this.sendTo;
	}

	public void setSendTo(int sendTo) {
		this.sendTo = sendTo;
	}
	

	public String getIdAction() {
		return idAction;
		
	}

	public void setIdAction(String idAction) {
		this.idAction = idAction;
	}

	public String getNumBox() {
		return numBox;
	}

	public void setNumBox(String numBox) {
		this.numBox = numBox;
	}

	public boolean isAction() {
		return action;
	}

	public void setAction(boolean action) {
		this.action = action;
	}

	public boolean isTypeAction() {
		return typeAction;
	}

	public void setTypeAction(boolean typeAction) {
		this.typeAction = typeAction;
	}

	public MeLinkedLists<Players> getPlayers() {
		return players;
	}

	public void setPlayers(MeLinkedLists<Players> players) {
		this.players = players;
	}
	
	public void setter() {
		setAction(action);
		setTypeAction(typeAction);
		setIdAction(idAction);
		
	}
	
	public String playerIn(){
		return playerIn(0, "");

	}

	private String playerIn(int contador, String players){

		if(contador < getPlayers().size()){
			players = players + getPlayers().get(contador).getToken();
			playerIn(contador + 1, players);
		}

		return players;
	}
	
	
	
}

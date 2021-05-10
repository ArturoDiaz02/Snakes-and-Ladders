package model.LinkedList;

import java.io.Serializable;
import model.*;

public class BinaryTree implements Serializable {

	private static final long serialVersionUID = 7L;
	private Node<Players> head;
	private int size;

	public BinaryTree() {
        head = null;
       	size = 0;
    }
    
	public int getSize() {
		return this.size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Node<Players> getHead() {
		return this.head;
	}

	public void setHead(Node<Players> head) {
		this.head = head;
	}

	public void add(Players e){
		if(head == null){
			head = new Node<Players>(e);

		}else{
			add(e, head);
		}
		
	}
	
   	private void add(Players e, Node<Players> temp){//inversamente

		if(temp.getItem().getScore() <= e.getScore()){
			if(temp.getPrevious() == null){
				temp.setPrevious(new Node<Players>(e));

			}else{
				add(e, temp.getPrevious());
			}

		}else {
			if(temp.getNext() == null){
				temp.setNext(new Node<Players>(e));

			}else{
				add(e, temp.getNext());
			}
		}
		
	}

	public void inOrden(){
		inOrden(head);
	}


	private void inOrden(Node<Players> player){
		if(player != null){
			inOrden(player.getPrevious());
			System.out.println("Nombre: " + player.getItem().getName() + ", Token: " + player.getItem().getToken() + ", Score: " + player.getItem().getScore());
			inOrden(player.getNext());
		}
	}
  		
 
   
     
}



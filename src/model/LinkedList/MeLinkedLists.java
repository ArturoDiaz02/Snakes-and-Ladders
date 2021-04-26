package model.LinkedList;

import java.io.Serializable;

public class MeLinkedLists<E> implements Serializable {

	private static final long serialVersionUID = 4L;
	private Node<E> head;
    private Node<E> tail;
    private int colums;
    private int rows;
    private int size;

    public MeLinkedLists() {
        head = null;
        size = 0;
        tail = null;
        colums = 0;
        size = 0;
    }
    
    public int getColums() {
		return colums;
	}

	public void setColums(int colums) {
		this.colums = colums;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public Node<E> getHead() {
        return head;
    }

    public Node<E> getTail() {
        return tail;
    }

    public boolean isEmpty() {
        return (head == null)? true: false;
    }

    public int size() {
        return size;
    }

   /* public void add(E e) {
        
        if (head == null) {
            head = new Node<E>(e);
            tail = head;

        }else{
          add(e, tail, 0);

        }

        size += 1;
    }
    
    private void add(E e, Node<E> temp, int vali) {
    
        if(vali == 0){
            temp.setNext(new Node<E>(e));
            temp.getNext().setPrevious(temp);
            tail = temp.getNext();
            vali += 1;
        }
    	
        if(temp.getPrevious() != null && vali == 1){
            add(e, temp.getPrevious(), vali);
           
        }else{
            head = temp;
        }

    }*/

    public void add(E e) {
        
        if (head == null) {
            head = new Node<E>(e);
            tail = head;

        }else{
            Node<E> temp = head;

            for(int i = 0; i < size; i++){

                if(temp.getNext() != null){
                    temp = temp.getNext();

                }else{
                    temp.setNext(new Node<E>(e));
                    temp.getNext().setPrevious(temp);
                    tail = temp.getNext();

                }
            }

            for(int i = 0; i < size; i++){

                if(temp.getPrevious() != null){
                    temp = temp.getPrevious();

                }else{
                    head = temp;
                }

            }


        }

        size++;
    }

   /* public E get(int index){
       
        return get(index, head, 0);

    }
    
    private E get(int index, Node<E> temp, int contador){

    	
        if(contador < index && index < size) {
        	get(index, temp.getNext(), contador + 1);
        }
        
        return temp.getItem();

    }*/

    public E get(int index){
        int contador = 0;
        Node<E> temp = head;

        while(contador < index && index < size){

            temp = temp.getNext();
            contador++;

        }

        return temp.getItem();

    }

    public Node<E> getNode(int index){
       
        return getNode(index, head, 0);

    }
    
    private Node<E> getNode(int index, Node<E> temp, int contador){

    	
        if(contador < index && index < size) {
        	//get(index, temp.getNext(), contador + 1);
        }
        
        return temp;

    }

    public void remove(int index){
        if(index == 0){
            head = head.getNext();
        }else{
            getNode(index - 1).setNext(getNode(index).getNext());
        }

    }
    
    public E getAndRemove(int index){
        E item = get(index);
        remove(index);

        return item;
    }
    
}

class Node<E> implements Serializable{

	private static final long serialVersionUID = 5L;
	private E item;
    private Node<E> next;
    private Node<E> previous;
    private Node<E> root;

    public Node(E element) {
        this.item = element;
        this.next = null;
        this.previous = null;

    }

	public Node<E> getRoot() {
		return root;
	}

	public void setRoot(Node<E> root) {
		this.root = root;
	}

	public void setItem(E item) {
		this.item = item;
	}

	public E getItem() {
        return item;
    }

    public Node<E> getNext() {
        return next;
    }

    public void setNext(Node<E> next) {
        this.next = next;
    }

    public Node<E> getPrevious() {
        return previous;
    }

    public void setPrevious(Node<E> previous) {
        this.previous = previous;
    }
}

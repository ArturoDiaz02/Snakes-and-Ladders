package model.LinkedList;

import java.io.Serializable;

public class MeLinkedLists<E> implements Serializable {

	private static final long serialVersionUID = 4L;
	private Node<E> head;
    private Node<E> tail;
    private int size;

    public MeLinkedLists() {
        head = null;
        size = 0;
        tail = null;
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

    public void add(E e) {
        
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

    }

    public int indexOf(E e){
        return indexOf(e, head, 0);
    }

    private int indexOf(E e, Node<E> temp, int contador){
        if(e.equals(temp.getItem())){
            return contador;

        }else{
            return indexOf(e, temp.getNext(), contador + 1);

        }
    }

    public E get(int index){
       
        return get(index, head);

    }
    
    private E get(int index, Node<E> temp){

        if(index == 0){
            return temp.getItem();

        }else{
            return get(index - 1, temp.getNext());

        }
       
    }

    public Node<E> getNode(int index){
       
        return getNode(index, head);

    }
    
    private Node<E> getNode(int index, Node<E> temp){

        if(index == 0){
            return temp;

        }else{
            return getNode(index - 1, temp.getNext());

        }

    }

    public void remove(int index){
        if(index == 0){
            head = head.getNext();
        }else{
            getNode(index).getPrevious().setNext(getNode(index).getNext());
            getNode(index).getNext().setPrevious(getNode(index).getPrevious());
        }

        size -= 1;
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

package ui;

import java.util.ArrayList;
import java.util.Scanner;

import model.*;
import model.LinkedList.MeLinkedLists;

public class SnakesAndLaddersGUI {

	private Game game;
	
	public SnakesAndLaddersGUI() {}
	
	public Game getGame() {	return game;}

    public int menus (Scanner scanner){
        int control = 0;
        
		limpiarPantalla();

        System.out.print("*********************************************************************************************\n");
        System.out.print("Snakes And Ladders\n\n");

        System.out.print("Bienvenido al juego.\n");
        System.out.print("Para ingresar a una de ellas escriba su indice\n\n");
   
        System.out.print("1. JUGAR\n\n");
        System.out.print("2. TABLA clasificatoria\n\n");
        System.out.print("3. SALIR\n\n");
        System.out.print("*********************************************************************************************");

      
        System.out.println();
        System.out.println("Escriba el indice:");
        System.out.print(">");
        control = scanner.nextInt();
        
        if(control <= 0 && control >= 4) {
        	limpiarPantalla();
        	menus(scanner);
        }


        return control;

    }
	
    
	public void format(Scanner scanner) {

		limpiarPantalla();

	    System.out.print("*********************************************************************************************\n");
	    System.out.print("Snakes And Ladders\n\n");

	    System.out.print("Para empezar el juego digite el formato del mismo de la siguiente manera (F C S E SP)\n\n");

	    System.out.print("*. F son la filas\n\n");
	    System.out.print("*. C son las columnas\n\n");
	    System.out.print("*. S serpientes en el tablero\n\n");
	    System.out.print("*. E escaleras en el tablero\n\n");
	    System.out.print("*. SP simbolos de los jugadores 2 como minimo y 9 como maximo (* ! O X % $ # + &)\n\n");
	    System.out.print("*********************************************************************************************");

	      
	    System.out.println();
	    System.out.println("Escriba el formato:");
	    System.out.print(">");
	    creator(scanner.nextLine());
		
		
	}
	

    public void creator(String next) {
		ArrayList<String> format = spliter(next, new ArrayList<>(), 0);

		
		int rows = Integer.parseInt(format.get(0));
		int colums = Integer.parseInt(format.get(1));
		int snakes = Integer.parseInt(format.get(2));
		int ladders = Integer.parseInt(format.get(3));
		String tokens = format.get(4);

		game.setColums(colums);
		game.setRows(rows);

		if((rows * colums) % 2 != 0){ 
			if((rows * colums) - 1 < snakes*2 + colums*2){
				if(snakes > ladders){

					if(((rows * colums) - 1) < ladders*2){
						ladders = ((rows * colums) - 1) / 2;
					}

					snakes = (((rows * colums) - 1) - ladders*2) / 2;

					if(snakes > 26){
						snakes = 26;
					}

				}else if(snakes < ladders){

					if(((rows * colums) - 1) < snakes*2){
						snakes = ((rows * colums) - 1) / 2;
					}

					ladders = (((rows * colums) - 1) - snakes*2) / 2;
					
					if(snakes > 26){
						snakes = 26;
					}

				}else{

					snakes = ((rows * colums) - 1) / 4;
					ladders = snakes;

					if(snakes > 26){
						snakes = 26;
					}
				}
			}
		}

		if((rows * colums) % 2 == 0){ 
			if((rows * colums) - 2 < snakes*2 + colums*2){
				if(snakes > ladders){

					if(((rows * colums) - 2) < ladders*2){
						ladders = ((rows * colums) - 2) / 2;
					}

					snakes = (((rows * colums) - 2) - ladders*2) / 2;

					if(snakes > 26){
						snakes = 26;
					}

				}else if(snakes < ladders){

					if(((rows * colums) - 2) < snakes*2){
						snakes = ((rows * colums) - 2) / 2;
					}

					ladders = (((rows * colums) - 2) - snakes*2) / 2;

					if(snakes > 26){
						snakes = 26;
					}

				}else{

					snakes = ((rows * colums) - 2) / 4;
					ladders = snakes;

					if(snakes > 26){
						snakes = 26;
					}
				}
			}
		}
		
		createBox(rows * colums, 0, createPlayers(tokens, rows * colums, 0, new MeLinkedLists<>()), colums, false, 0);

		//setEspecialBoxs((rows * colums), snakes, ladders, 0);
		
	}


    public MeLinkedLists<Players> createPlayers(String tokens, int missingBoxes, int contador, MeLinkedLists<Players> player) {
		
		if(contador < tokens.length()){
			player.add(new Players(tokens.charAt(contador), missingBoxes));
			createPlayers(tokens, missingBoxes, contador + 1, player);
		}
		
		return player;
		
	}


	public void setEspecialBoxs(int amountBoxs, int snakes, int ladders, int contador) {
		if(contador < amountBoxs){

			int head = (int) (Math.random() * (amountBoxs - 1) + 1);
			int tail = (int) (Math.random() * (amountBoxs - 1) + 1);
			int action = (int) (Math.random() * 2);

			System.out.println(head + " "+  tail + " " + action);

			if (head == tail || head <= tail || game.getBoxs().get(head).getAction() || game.getBoxs().get(tail).getAction()) {
				setEspecialBoxs(amountBoxs, snakes, ladders, contador);
			}

			if(action == 0 && snakes != 0){ //0 es serpiente

				game.getBoxs().get(head).setAction(true);
				game.getBoxs().get(head).setTypeAction(true); //true es serpiente y false escalera
				game.getBoxs().get(head).setSendTo(tail);
				game.getBoxs().get(head).setIdAction(game.getAlphabet().get(contador));

				game.getBoxs().get(tail).setAction(true);
				game.getBoxs().get(tail).setTypeAction(true); 
				game.getBoxs().get(tail).setIdAction(game.getAlphabet().getAndRemove(contador));
				snakes -= 1;
			}

			if(action == 1 && ladders != 0){ //1 es escalera

				game.getBoxs().get(tail).setAction(true);
				game.getBoxs().get(tail).setTypeAction(false); //true es serpiente y false escalera
				game.getBoxs().get(tail).setSendTo(head);
				game.getBoxs().get(tail).setIdAction(String.valueOf(contador + 1));

				game.getBoxs().get(head).setAction(true);
				game.getBoxs().get(head).setTypeAction(true); 
				game.getBoxs().get(head).setIdAction(String.valueOf(contador + 1));

				ladders -= 1;
			}

		}

		setEspecialBoxs(amountBoxs, snakes, ladders, contador + 1);

	}


	public void createBox(int amountBoxs, int contador, MeLinkedLists<Players> players, int jump, boolean vali, int index) {

    	if(contador < amountBoxs) {

			if(contador == jump){
				jump += game.getColums();

				if(!(vali)){
					vali = true;
					index = jump;

				}else{
					vali = false;
					
				}

			}

			if(!vali){
				game.getBoxs().add(new Box(contador + 1));

				if(contador == 0){
					game.getBoxs().get(contador).setPlayers(players);
				}
			}

			if(vali){
				game.getBoxs().add(new Box(index));
				index -= 1;

			}
		
			System.out.println(contador);
			System.out.println(game.getBoxs().get(contador).getNumBox());
			System.out.println("____");

    		createBox(amountBoxs, contador + 1, players, jump, vali, index);
    		
    	}
		
	}
    
 
	public ArrayList<String> spliter(String next, ArrayList<String> format, int contador) {

    	if(contador < 5) {
    		format.add(next.split(" ")[contador]);
    		spliter(next, format, contador + 1);
    	}
    	
		return format;
    
    }


	public void play(Scanner scanner) {

		showBoardWithEspecials(0, game.getColums() * game.getRows(), 0, game.getColums() * game.getRows() - game.getColums());

		//limpiarPantalla();
		String vali = scanner.nextLine();

		if(vali.equals("1")){
			inGame("main");
		}

	}


	private void inGame(String index) {

		switch (index) {
			case "main":
				showBoard(0, (game.getColums() * game.getRows()), game.getColums());

				break;
		
			default:
				System.out.println("error");
				break;
		}

	}

	public void showBoardWithEspecials(int contador, int sizeBoard, int jump, int index){//quitar 1 de size
		if(contador < sizeBoard){

			if(jump == game.getColums() - 1){
				index -= game.getColums();
				jump = 0;

				System.out.println();

			}

			if(game.getBoxs().get(index + jump).getAction()){
				System.out.print("[" + game.getBoxs().get(index + jump).getNumBox() + " " + game.getBoxs().get(index + jump).getIdAction() + "]");
	
			}else{
				System.out.print("[" + game.getBoxs().get(index + jump).getNumBox() + "  ]");
	
			}
			

			if(contador == sizeBoard - 1){
				System.out.println();
			}

			showBoardWithEspecials(contador + 1, sizeBoard, jump + 1, index);
			
		}

	}


	public void showBoard(int contador, int sizeBoard, int jump){
		if(contador < sizeBoard){

			if(contador == jump){
				System.out.println();
				jump += game.getColums();

			}

			if(game.getBoxs().get(sizeBoard - contador).getAction()){
				System.out.print("[" + game.getBoxs().get(sizeBoard - contador).getIdAction() + " " + game.getBoxs().get(sizeBoard - contador).playerIn() + "]");

			}else{
				System.out.print("[" + game.getBoxs().get(sizeBoard - contador).playerIn() + "]");

			}

			if(contador + 1 == sizeBoard){
				System.out.println();
			}

			showBoard(contador + 1, sizeBoard, jump);
			
		}

	}


	public void init(Game data) {
		game = new Game(data);
	}


	public static void limpiarPantalla() {
        try {
            new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
        }
        catch (Exception e) {
            System.out.println(e);

        }
    }
	
}

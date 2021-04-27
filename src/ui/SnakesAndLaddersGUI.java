package ui;

import java.util.Scanner;
import model.*;

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
		String[] format = spliter(next, new String[5], 0);

		int rows = Integer.parseInt(format[0]);
		int colums = Integer.parseInt(format[1]);
		int snakes = Integer.parseInt(format[2]);
		int ladders = Integer.parseInt(format[3]);
		String tokens = format[4];

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
		
		createBox(rows * colums, 0, colums, false, 0);
		createPlayers(tokens, 0);
		
		setLadders(rows * colums, ladders, 0);
		setSnakes(rows * colums, snakes, 0);
		
	}


    public void createPlayers(String tokens, int contador) {
		
		if(contador < tokens.length()){
			game.getBoxs().get(0).getPlayers().add(new Players(tokens.charAt(contador)));
			game.getPlayers().add(new Players(tokens.charAt(contador)));
			createPlayers(tokens, contador + 1);
			
		}
	
	}


	public void setLadders(int amountBoxs, int ladders, int contador) {

		if(contador < amountBoxs && ladders != 0){

			int head = (int) (Math.random() * (amountBoxs - 2) + 1);
			int tail = (int) (Math.random() * (head - 2)  + 1);
			

			if (head >= amountBoxs - 2 || tail == 0 || head <= tail || game.getBoxs().get(head).getAction() || game.getBoxs().get(tail).getAction()) {
				setLadders(amountBoxs, ladders, contador);

			}else{
		
				game.getBoxs().get(tail).setAction(true);
				game.getBoxs().get(tail).setTypeAction(true);
				game.getBoxs().get(tail).setSendTo(head);
				game.getBoxs().get(tail).setIdAction(String.valueOf(contador + 1));
	
				game.getBoxs().get(head).setTypeAction(true); 
				game.getBoxs().get(head).setIdAction(game.getBoxs().get(tail).getIdAction());
				setLadders(amountBoxs, ladders - 1, contador + 1);
			}

		}

		
	}


	public void setSnakes(int amountBoxs, int snakes, int contador) {

		if(contador < amountBoxs && snakes != 0){

			int head = (int) (Math.random() * (amountBoxs - 2) + 1);
			int tail = (int) (Math.random() * (head - 2) + 1);

			if (head >= amountBoxs - 2 || tail == 0 || head <= tail || game.getBoxs().get(head).getAction() || game.getBoxs().get(tail).getAction()) {
				setSnakes(amountBoxs, snakes, contador);
				
			}else{

				game.getBoxs().get(head).setAction(true);
				game.getBoxs().get(head).setTypeAction(true); //true es serpiente y false escalera
				game.getBoxs().get(head).setSendTo(tail);
				game.getBoxs().get(head).setIdAction(String.valueOf(Alphabet.letter(contador)));
			
				game.getBoxs().get(tail).setTypeAction(true); 
				game.getBoxs().get(tail).setIdAction(game.getBoxs().get(head).getIdAction());
				setSnakes(amountBoxs, snakes - 1, contador + 1);
			}


		}

	}


	public void createBox(int amountBoxs, int contador, int jump, boolean vali, int index) {

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
				String  num;

				if(contador + 1 > 9){
					num = String.valueOf(contador + 1);
				}else{
					num = "0" + (contador + 1);
				}

				game.getBoxs().add(new Box(num, contador + 1));

			}

			if(vali){

				String  num;
				
				if(index > 9){
					num = String.valueOf(index);
				}else{
					num = "0" + (index);
				}

				game.getBoxs().add(new Box(num, index));
				index -= 1;

			}

    		createBox(amountBoxs, contador + 1, jump, vali, index);
    		
    	}
		
	}
    
 
	public String[] spliter(String next, String[] format, int contador) {

    	if(contador < 5) {
    		format[contador] = (next.split(" ")[contador]);
    		spliter(next, format, contador + 1);
    	}
    	
		return format;
    
    }


	public void play(Scanner scanner, int player) {

	    System.out.print("*********************************************************************************************\n");
		System.out.print("Tablero de Juego\n\n");
		showBoard(0, game.getColums() * game.getRows(), 0, game.getColums() * game.getRows() - game.getColums());
	    System.out.print("\n*********************************************************************************************\n");
	    System.out.println("*. Precione enter para tirar el dado (" + game.getPlayers().get(player).getToken() + "):");
		System.out.println("*. Escriba (num) para ir al Tablero de Comodines:");
		System.out.println("*. Escriba (simul) para generar una simulacion del juego:");
		System.out.println("*. Escriba (menu) para terminar el juego y ir al menu:");
	    System.out.print(">");

		switch(scanner.nextLine()){
				case "":
				rollDice(player, scanner);
				break;

				case "num":
				inGame("num", scanner);
				break;

				case "simul":
				inGame("simul", scanner);
				break;

				case "menu":
				inGame("menu", scanner);
				break;

		}

		limpiarPantalla();


	}

	private void rollDice(int player, Scanner scanner){
		int dice = (int) (Math.random() * 6 + 1);
		System.out.println("\nEl jugador " + game.getPlayers().get(player).getToken() + " ha lanzado el dado y obtuvo el puntaje " + dice + ".\n");
		game.playerMove(game.getPlayers().get(player).getToken(), dice);

		if(player ==  game.getPlayers().size() - 1){
			play(scanner, 0);

		}else{
			play(scanner, player + 1);
		}

		

	}

	public void inGame(String index, Scanner scanner) {

		switch (index) {
			case "main":
				limpiarPantalla();
				System.out.print("*********************************************************************************************\n");
				System.out.print("Tablero de Comodines\n\n");
				showBoardWithEspecials(0, game.getColums() * game.getRows(), 0, game.getColums() * game.getRows() - game.getColums());
				System.out.print("\n\n*********************************************************************************************\n");
				System.out.println("Precione Enter para iniciar:");
	    		System.out.print(">");

				if(scanner.nextLine().equals("")){
					limpiarPantalla();
					play(scanner, 0);
				}

				break;
		
			default:
				System.out.println("error");
				break;
		}

	}


	public void showBoardWithEspecials(int contador, int sizeBoard, int jump, int index){//quitar 1 de size
		if(contador < sizeBoard){

			if(game.getBoxs().get(index + jump).getTypeAction()){
				System.out.print("[" + game.getBoxs().get(index + jump).getNumBox() + " " + game.getBoxs().get(index + jump).getIdAction() + "]");
	
			}else{
				System.out.print("[" + game.getBoxs().get(index + jump).getNumBox() + "  ]");
	
			}

			if(jump == game.getColums() - 1){
				index -= game.getColums();
				jump = 0;

				System.out.println();

				showBoardWithEspecials(contador + 1, sizeBoard, jump, index);

			}else{

				showBoardWithEspecials(contador + 1, sizeBoard, jump + 1, index);
			}

		
		}

	}


	public void showBoard(int contador, int sizeBoard, int jump, int index){

		if(contador < sizeBoard){

			if(game.getBoxs().get(index + jump).getTypeAction()){
				System.out.print("[" + game.getBoxs().get(index + jump).getIdAction() + " " + game.getBoxs().get(index + jump).playerIn() + "]");

			}else{
				System.out.print("[" + game.getBoxs().get(index + jump).playerIn() + "  ]");

			}

			if(jump == game.getColums() - 1){
				index -= game.getColums();
				jump = 0;

				System.out.println();

				showBoard(contador + 1, sizeBoard, jump, index);

			}else{

				showBoard(contador + 1, sizeBoard, jump + 1, index);
			}

		
		}

	}


	public void init(Game data) {
		game = new Game();
		game = data;
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

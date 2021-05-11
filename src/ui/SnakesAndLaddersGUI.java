package ui;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import model.*;
import model.LinkedList.MeLinkedLists;

public class SnakesAndLaddersGUI {

	/**
	 * Game object
	 */
	private Game game;

	/**
	 * SnakesAndLaddersGUI constructor
	 */
	public SnakesAndLaddersGUI() {}

	/**
	 * Get Game
	 * @return game
	 */
	public Game getGame() {	return game;}


	/**
	 * Main menu, shows available options 
	 * @param scanner
	 * @return control, control variable
	 */
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
	
    /**
	 * Format menu, shows available options and ask for the game format that it send to creator method
	 * @param scanner
	 */
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
	
	/**
	 * This method generates the board, first it slips the format, second it filters the snakes and ladders and finally it creates the board and its special squares
	 * @param next format
	 */
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
			if((rows * colums) - 3 < snakes*2 + ladders*2){

				if(((rows * colums - 3) / 2) % 2 != 0){
					snakes = (rows * colums - 3) / 4;
					ladders = (rows * colums - 3) / 2 - snakes;

				}else{
					snakes = (rows * colums - 3) / 4;
					ladders = snakes;

				}

			}
		}

		if((rows * colums) % 2 == 0){ 
			if((rows * colums) - 2 < snakes*2 + ladders*2){

				snakes = (rows * colums - 2) / 4;
				ladders = snakes;	

			}
		}
		
		createBox(rows * colums, 0, colums, false, 0);
		createPlayers(tokens, 0);

		setLadders(rows * colums, ladders, 0);
		setSnakes(rows * colums, snakes, 0);
		
	}

	/**
	 * create the players to suit the user and save them in the squares and in the game object
	 * @param tokens format of players
	 * @param contador
	 */
    public void createPlayers(String tokens, int contador) {
		
		if(contador < tokens.length()){
			game.getBoxs().get(0).getPlayers().add(new Players(tokens.charAt(contador)));
			game.getPlayers().add(new Players(tokens.charAt(contador)));
			createPlayers(tokens, contador + 1);
			
		}
	
	}

	/**
	 * this method randomly creates the ladders on the board
	 * @param amountBoxs board size
	 * @param ladders amount ladders
	 * @param contador
	 */

	public void setLadders(int amountBoxs, int ladders, int contador) {

		if(contador < amountBoxs && ladders != 0){

			int head = (int) (Math.random() * (amountBoxs - 2) + 1);
			int tail = (int) (Math.random() * (head - 1) + 1);

			if (head == tail || game.getBoxs().get(head).getNumBoxInt() == game.getColums()*game.getRows() || game.getBoxs().get(head).getAction() || game.getBoxs().get(tail).getAction()) {
				setLadders(amountBoxs, ladders, contador);

			}else{

				game.getBoxs().get(tail).setAction(true);
				game.getBoxs().get(tail).setTypeAction(true); //true es serpiente y false escalera
				game.getBoxs().get(tail).setSendTo(head);
				game.getBoxs().get(tail).setIdAction(String.valueOf(contador + 1));
	
				game.getBoxs().get(head).setAction(true);
				game.getBoxs().get(head).setTypeAction(false); 
				game.getBoxs().get(head).setIdAction(game.getBoxs().get(tail).getIdAction());
				setLadders(amountBoxs, ladders - 1, contador + 1);
			}

		}

		
	}

	/**
	 * this method randomly creates the snakes on the board
	 * @param amountBoxs board size
	 * @param snakes amount snakes
	 * @param contador
	 */
	public void setSnakes(int amountBoxs, int snakes, int contador) {

		if(contador < amountBoxs && snakes != 0){
			
			int head = (int) (Math.random() * (amountBoxs - 2) + 1);
			int tail = (int) (Math.random() * (head - 1) + 1);

			if (head == tail || game.getBoxs().get(head).getNumBoxInt() == game.getColums()*game.getRows() || game.getBoxs().get(head).getAction() || game.getBoxs().get(tail).getAction()) {
				setSnakes(amountBoxs, snakes, contador);

			}else{
				game.getBoxs().get(head).setAction(true);
				game.getBoxs().get(head).setTypeAction(true); //true es serpiente y false escalera
				game.getBoxs().get(head).setSendTo(tail);
				game.getBoxs().get(head).setIdAction(String.valueOf(Alphabet.letter(contador)));
			
				game.getBoxs().get(tail).setAction(true);
				game.getBoxs().get(tail).setTypeAction(false); 
				game.getBoxs().get(tail).setIdAction(game.getBoxs().get(head).getIdAction());
				setSnakes(amountBoxs, snakes - 1, contador + 1);
			}


		}

	}


	/**
	 * this method creates snakes-shaped board
	 * @param amountBoxs board size
	 * @param contador
	 * @param jump every X cycles a line break
	 * @param vali defines in which order the cells are added
	 * @param index shaped like a snake
	 */
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
    
 
	/**
	 * separates the string that the user entered into an array
	 * @param next format
	 * @param format Array with the format
	 * @param contador
	 * @return Array String[] with the format
	 */
	public String[] spliter(String next, String[] format, int contador) {

    	if(contador < 5) {
    		format[contador] =(next.split(" ")[contador]);
    		spliter(next, format, contador + 1);
    	}
    	
		return format;
    
    }


	/**
	 * this method is the board game
	 * @param scanner
	 * @param player player index
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void play(Scanner scanner, int player) throws IOException, ClassNotFoundException {

	    System.out.print("*********************************************************************************************\n");
		System.out.print("Tablero de Juego\n\n");
		showBoard(0, game.getColums() * game.getRows(), 0, game.getColums() * game.getRows() - game.getColums());
	    System.out.print("\n*********************************************************************************************\n");
	    System.out.println("*. Precione enter para tirar el dado (" + game.getPlayers().get(player).getToken() + "):");
		System.out.println("*. Escriba (num) para ir al Tablero de Comodines:");
		System.out.println("*. Escriba (simul) para generar una simulacion del juego:");
		System.out.println("*. Escriba (menu) para terminar el juego y ir al menu:");
	    System.out.print(">");

		String index = scanner.nextLine();

		switch(index){
			case "":
				rollDice(player, scanner, 0);
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

	}


	public void simul(Scanner scanner, int player) throws ClassNotFoundException, IOException{
		Timer timer = new Timer();


		System.out.print("*********************************************************************************************\n");
		System.out.print("Tablero de Juego\n\n");
		showBoard(0, game.getColums() * game.getRows(), 0, game.getColums() * game.getRows() - game.getColums());
	    System.out.print("\n*********************************************************************************************\n");

		TimerTask task = new TimerTask(){
			@Override
			public void run(){
				try {
					rollDice(player, scanner, 1);

				} catch (ClassNotFoundException | IOException e) {
					e.printStackTrace();

				}
			}
		};
		
		timer.schedule(task, 2000);

	}


	/**
	 * generates random dice to make X player move, on the other hand, it checks if the player reached the goal and declares him the winner by recording his information
	 * @param player player index
	 * @param scanner
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private void rollDice(int player, Scanner scanner, int index) throws IOException, ClassNotFoundException{
		int dice = (int) (Math.random() * 6 + 1);
		System.out.println("\nEl jugador " + game.getPlayers().get(player).getToken() + " ha lanzado el dado y obtuvo el puntaje " + dice + ".\n");
		boolean win = game.playerMove(game.getPlayers().get(player).getToken(), dice);

		if(win){
			Players winPlayer = game.searchBox(game.getPlayers().get(player).getToken(), 0, 0, 0).getPlayers().get(0);
			winPlayer.setScore(winPlayer.getMovement() * game.getColums() * game.getRows());
			game.setPlayers(new MeLinkedLists<Players>());
			game.setBoxs(new MeLinkedLists<Box>());
			game.setColums(0);
			game.setRows(0);

			System.out.print("*********************************************************************************************\n");
			System.out.print("Ganador\n\n");
			System.out.print("El jugador " + winPlayer.getToken() + " ha ganado el juego, con " + winPlayer.getMovement() + " movimientos");
			System.out.print("\n*********************************************************************************************\n");
			System.out.println("Escriba su nombre para registrarlo en la tabla:");
			System.out.print(">");
			winPlayer.setName(scanner.nextLine());

			game.getLeaderBoard().add(winPlayer);

			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("docs\\storage\\Data.txt"));
       		oos.writeObject(game);
        	oos.close();

			inGame("menu", scanner);


		}else{

			if(index == 0){
				if(player ==  game.getPlayers().size() - 1){

					play(scanner, 0);
		
				}else{
					play(scanner, player + 1);
				}

			}else{
				if(player ==  game.getPlayers().size() - 1){

					simul(scanner, 0);
		
				}else{
					simul(scanner, player + 1);
				}

			}
			
		}

		

		

	}
	
	/**
	 * organize the scenes in the game
	 * @param index control
	 * @param scanner
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void inGame(String index, Scanner scanner) throws IOException, ClassNotFoundException {

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

			case "num":
				inGame("main", scanner);
			
			break;

			case "simul":
				simul(scanner, 0);
				
			break;

			case "menu":
				Main main = new Main();
				game.setPlayers(new MeLinkedLists<Players>());
				game.setBoxs(new MeLinkedLists<Box>());
				game.setColums(0);
				game.setRows(0);
				main.init(main);
			
			break;
		}

	}


	/**
	 * show the board with its numbers of squares and its special squares
	 * @param contador
	 * @param sizeBoard board size
	 * @param jump every X cycles a line break
	 * @param index shaped like a snake
	 */
	public void showBoardWithEspecials(int contador, int sizeBoard, int jump, int index){//quitar 1 de size
		if(contador < sizeBoard){

			if(game.getBoxs().get(index + jump).getAction()){
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

	/**
	 * shows the board without numbers of squares, only players and special squares
	 * @param contador 
	 * @param sizeBoard board size
	 * @param jump every X cycles a line break
	 * @param index shaped like a snake
	 */
	public void showBoard(int contador, int sizeBoard, int jump, int index){

		if(contador < sizeBoard){

			if(game.getBoxs().get(index + jump).getAction()){
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

	/**
	 * load the object from Data.txt the current objent
	 * @param data object in .txt
	 */
	public void init(Game data) {
		game = new Game();
		game = data;
	}

	/**
	 * Clear windows
	 */
	public static void limpiarPantalla() {
        try {
            new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
        }
        catch (Exception e) {
            System.out.println(e);

        }
    }

	/**
	 * shows the leaderboard and returns to the menu
	 * @param scanner
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public void leaderBoard(Scanner scanner) throws ClassNotFoundException, IOException {

		limpiarPantalla();
		System.out.print("*********************************************************************************************\n");
		System.out.print("Tablero de Puntajes\n\n");
		game.getLeaderBoard().inOrden();
		System.out.print("\n\n*********************************************************************************************\n");
		System.out.println("Precione Enter para salir:");
		System.out.print(">");

		if(scanner.nextLine().equals("")){
			limpiarPantalla();
			inGame("menu", scanner);
		}

	}
	
}

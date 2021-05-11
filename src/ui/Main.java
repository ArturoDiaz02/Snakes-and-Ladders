package ui;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;

import model.Game;

public class Main{


	/**
	 * Read Objects
	 */
	private ObjectInputStream ois;

	/**
	 * Gui Object
	 */
	private SnakesAndLaddersGUI gui;

	/**
	 * Main constructor
	 * @throws IOException
	 */
	
	public Main() throws IOException {
		gui = new SnakesAndLaddersGUI();
	}

	/**
	 * Main Method
	 * @param args
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		Main main = new Main();
		
		main.init(main);
	
	}

	/**
	 * This method start the program, it read an object Game in a .txt
	 * @param main
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	
	public void init(Main main) throws IOException, ClassNotFoundException {
		ois = new ObjectInputStream(new FileInputStream("docs\\storage\\Data.txt"));
		
		Game game = (Game) ois.readObject();
		ois.close();
	
		main.getGui().init(game);
		main.select(main);
		
	}

	/**
	 * Shows what the user wants
	 * @param main
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	
	public void select(Main main) throws IOException, ClassNotFoundException{
		
		int comand = main.getGui().menus(new Scanner(System.in));
		
		if(comand == 1) {
			main.getGui().format(new Scanner(System.in));
			main.getGui().inGame("main", new Scanner(System.in));
			
		}else if(comand == 2) {
			main.getGui().leaderBoard(new Scanner(System.in));
			
		}else if(comand != 3) {
			select(main);
		}
		
	}

	/**
	 * Get Gui
	 * @return gui
	 */
	public SnakesAndLaddersGUI getGui() {
		return gui;
	}

	
	
	

}

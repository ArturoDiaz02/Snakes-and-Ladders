package ui;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import model.Game;

public class Main{
	
	private ObjectInputStream ois;
	private SnakesAndLaddersGUI gui;
	
	public Main() throws IOException {
		gui = new SnakesAndLaddersGUI();
	}

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		Main main = new Main();
		
		main.init(main);
		
		main.select(main);
		
	}
	
	public void init(Main main) throws IOException, ClassNotFoundException {
		/*Game game1 = new Game();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data\\Data.txt"));
        oos.writeObject(game1);
        oos.close();*/
		
		ois = new ObjectInputStream(new FileInputStream("data\\Data.txt"));
		
		Game game = (Game) ois.readObject();
		ois.close();
	
		main.getGui().init(game);
		
	}
	
	public void select(Main main){
		
		int comand = main.getGui().menus(new Scanner(System.in));
		
		if(comand == 1) {
			main.getGui().format(new Scanner(System.in));
			main.getGui().play(new Scanner(System.in), 0);
			
		}else if(comand == 2) {
			
		}else if(comand != 3) {
			select(main);
		}
		
	}

	public SnakesAndLaddersGUI getGui() {
		return gui;
	}

	
	
	

}

# Snakes And Ladders

## Technical conditions

    *. Programming language: Java 1.8
    *. Operating System: Windows 10
    *. Development environment: Virtual Studio Code and Eclipse
    *. Installation: It must be executed by console exclusively for Windows operating systems
 
___
## Description of the problem

    Develop the snakes and ladders game that can be played through the system console taking into account a specific format set by the user, it must also randomly generate the snakes and ladders that the user requests throughout the board, excluding the first and last boxes.

    The program must be able to simulate the game without waiting for user interaction but it must also be able to wait for their interaction, and it must allow the user to interact with the other screens while playing.

    Winners' information must be recorded through a binaryTree and then serialized that information.

    You can not use cycles, only the use of recursion is allowed, in the same way the information can only be handled through a linked list and serialized through a binaryTree.

___
## **Menus**  
      
    This menu shows a list of three options where the user can choose one of them by typing its index. 

    Example: 
  
![img](https://cdn.discordapp.com/attachments/835691957577056286/841476029368369192/unknown.png)  
  
  ### ***1. Game Option***
    When the user enters the index 1, a new game starts where the user will be asked for the format that he wants it to have through the following menu.

![img](https://cdn.discordapp.com/attachments/835691957577056286/841479998380179456/unknown.png)
  
  #### ***- Format***
    
    In this menu the user is asked to enter a specific format of the form (F C S E SP) where each acronym has a special object. 
  
    *. At least there must be one player
    *. The game shows nine acronyms for nine players but the game is designed to have infinite players 
    *. If the number of boxes on the board according to the values entered by the user (F * C) are LESS than the boxes necessary to meet the requirements of snakes and ladders that the user entered, the program will automatically adjust those values to adjust it. to available board size 
      
  #### ***- Joker board***   
      
      It is a small menu that shows the board already generated with the number of each square and showing the snakes and ladders on the board. 

![img](https://cdn.discordapp.com/attachments/835691957577056286/841484174745272340/unknown.png)

#### ***- Game board***

    This menu shows the game board where you can see the players and the boxes that have snakes or ladders, in addition to a series of options that the user can type on the keyboard to call certain functionalities of the code.

![img](https://cdn.discordapp.com/attachments/835691957577056286/841485466255753267/unknown.png)

    + Dice: roll the die and move the player on duty across the game board.
    + Num: show the joker board and then return to the game.
    + Simul: the game is played automatically with two-second intervals per move until one player wins
    + Menu: directs the user to the first menu by restarting the whole game

***- Extra***

    If a player manages to win the game, his name will be asked to register it in the game's leaderboard.

 ### ***2. Leaderboard***
    Show a list of all the players who have ever won the game, each with their name, game token, and a score (moves * board size).

 ### ***3. Leave***
    close the game.
___
## Instructions about how to play:

    The players have to advance on the game board by rolling a dice and move the amount dictated by it, they have to be careful because snakes live on the game board that can eat you and eject squares below your previous position, in the same way manerar stairs are scattered all over the board, take them and climb up the position in the game. 

        *. If the player falls on the head of a snake, it will go down it to its tail.
        *. If a player falls on the tail of a ladder, he will climb it, climbing from position in the game.
        *. To win, the player has to travel the board to the maximum square (it must be taken into account that the player must land exactly on the last square to win). 
        *. If the player, for example, is 2 squares away from winning and the die rolls the number 6, the player must advance 2 squares until reaching the maximum square and from there go back 4 squares to complete the movements thrown by the dice.




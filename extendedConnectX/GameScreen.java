package cpsc2150.extendedConnectX;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.RowFilter;

public class GameScreen{
    /**
     * @param: args
     * @return: void
     * @pre:
     * @post: the game is played through main. 
     *        After main has been ran through fully,
     *        the game is over.
     */
    public static void main(String[] args){
        char playAgain = 'y';
        char playAgainUpper = 'Y';
        Scanner userInput = new Scanner(System.in);
        IGameBoard board;
        
        while(playAgain == 'y' || playAgainUpper == 'Y'){
            //receives input from user for player number and determines if it is appopriate amounts
            int numPlayerInt;
            do{
                System.out.println("How many players?");
                numPlayerInt = userInput.nextInt();
                if(numPlayerInt < IGameBoard.MIN_PLAYER_COUNT){
                    System.out.println("Must be at least 2 players");
                    continue;
                }
                if(numPlayerInt > IGameBoard.MAX_PLAYER_COUNT){
                    System.out.println("Can not be greater than 10 players");
                    continue;
                }
            }while(numPlayerInt > IGameBoard.MAX_PLAYER_COUNT || numPlayerInt < IGameBoard.MIN_PLAYER_COUNT);
            
            //receives input from user for player characters for each player
            
            ArrayList<Character> tokensUsed;
            tokensUsed = new ArrayList<>();
            char tmp;
            for(int y = 1; y <= numPlayerInt; y++){
                do{
                    System.out.println("Enter the character to represent player " + y);
                    tmp = Character.toUpperCase(userInput.next().charAt(0));
                    if(tokensUsed.contains(tmp)){
                        System.out.println(tmp + " is already taken as a player token!");
                    }
                }while(tokensUsed.contains(tmp));
                tokensUsed.add(tmp);
            }
            
            
            //receives input from user for number of rows
            int rowIn;
            do{
            System.out.println("How many rows should be on the board?");
            rowIn = userInput.nextInt();
            if(rowIn>IGameBoard.MAX_ROW_VALUE){
                System.out.println("Maximum amount of rows is 100");
                continue;
            }
            if(rowIn<IGameBoard.MIN_ROW_VALUE){
                System.out.println("Minimum amount of rows is 3");
            }
        }while(rowIn > IGameBoard.MAX_ROW_VALUE || rowIn < IGameBoard.MIN_ROW_VALUE);
            
            //receives input from user for number of columns
            int colIn;
            do{
            System.out.println("How many columns should be on the board?");
            colIn = userInput.nextInt();
            if(colIn>IGameBoard.MAX_COL_VALUE){
                System.out.println("Maximum amount of columns is 100");
                continue;
            }
            if(colIn<IGameBoard.MIN_COL_VALUE){
                System.out.println("Minimum amount of columns is 3");
            }
        }while(colIn > IGameBoard.MAX_ROW_VALUE || colIn < IGameBoard.MIN_ROW_VALUE);
            //receives input from user for the number in a row needed to win
            int winInput;
            do{
                System.out.println("How many in a row to win?");
                winInput = userInput.nextInt();
                if(winInput > IGameBoard.MAX_WIN_NUM){
                    System.out.println("Can not have greater than 25 in a row to win");
                    continue;
                }
                if(winInput < IGameBoard.MIN_WIN_NUM){
                    System.out.println("Must have at least 3 in a row to win");
                    continue;
                }
                else if(winInput > colIn || winInput > rowIn){
                    System.out.println("Can not have greater win value than row or column value");
                    continue;
                }

            }while(winInput > IGameBoard.MAX_WIN_NUM || winInput < IGameBoard.MIN_WIN_NUM || (winInput > colIn || winInput > rowIn));

            //receives input from user for memory efficient or fast game
            char gameInput;
            do{
                System.out.println("Would you like a Fast Game (F/f) or a Memory Efficient Game (M/m)?");
                gameInput = userInput.next().charAt(0);
                if(gameInput!= 'F' && gameInput != 'f' && gameInput != 'M' && gameInput != 'm'){
                    System.out.println("Please enter F or M");
                }
            }while(gameInput!= 'F' && gameInput != 'f' && gameInput != 'M' && gameInput != 'm');
            
            if(gameInput == 'f' || gameInput == 'F'){
                board = new GameBoard(rowIn, colIn, winInput);
            }
            else{
                board = new GameBoardMem(rowIn, colIn, winInput);
            }
            //counter to cycle through the player tokens
            int counter;
            counter = 0;
            //blank board
            //board = new GameBoard(rowIn, colIn, winInput);
            System.out.println(board.toString());
            int columnNum;
            while(board.checkTie()!=true){
                //playerturn to cycle through the player tokens
                int playerTurn = counter%numPlayerInt;
                System.out.println("Player " + tokensUsed.get(playerTurn) + ", what column do you want to place your marker in?");
                columnNum = userInput.nextInt();
                if(columnNum >= board.getNumColumns() || columnNum < 0 || !board.checkIfFree(columnNum)){
                    if(columnNum>= board.getNumColumns()){
                        System.out.println("Column cannot be greater than " + board.getNumColumns());
                    }
                    if(columnNum < 0){
                        System.out.println("Column can not be less than 0");
                    }
                    else{
                        System.out.println("Column is full");
                    }
                    System.out.println("Player" + tokensUsed.get(playerTurn) + ", what column do you want to place your marker in?");
                }
                
                
                if(board.checkIfFree(columnNum)==true){
                    board.placeToken(tokensUsed.get(playerTurn), columnNum);
                    if(board.checkForWin(columnNum)==true){
                        System.out.println(board.toString());
                        System.out.println("Player " + tokensUsed.get(playerTurn) + " has won!");
                        System.out.println("Would you like to play again? (Y/N)");
                        char inputToPlay = userInput.next().charAt(0);
                        playAgain = inputToPlay;
                        if(playAgain== 'n' || playAgain== 'N'){
                            return;
                        }
                        break;
                    }

                }
                else{
                    System.out.println("Column is full");
                    continue;
                }
                System.out.println(board.toString());
                counter++;

            }
            if(board.checkTie()==true){
                System.out.println("The game ended in a tie!");
                System.out.println("Would you like to play again? (Y/N)");
                char againPlay;
                Scanner next = new Scanner(System.in);
                againPlay = next.next().charAt(0);
                playAgain = againPlay;
            }
            
            
        }
        return;
    }

    
}
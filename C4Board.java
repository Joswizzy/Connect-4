import java.util.*;
import java.util.Scanner;
import java.util.Random;
public class C4Board {
    //2D array, where first [] is row and second [] is column
    private int[][] board;

    public C4Board() {
        //initializes an empty board 2D array
        this.board = new int[6][7];
        //initializes board to a blank board
        resetBoard();
    }

    //method to ask for gamemode
    public int gameMode() {
        System.out.println("Which game mode would you like to play?");
        System.out.println("(1) human vs. human (challenging mode), " +
                           "or (2) human vs. computer (extremely easy mode)");
        
        Scanner input = new Scanner(System.in);
        int gamemode = input.nextInt();
        if (gamemode == 1) {
            return 1;
        }
        if (gamemode == 2) {
            return 2;
        }
        else {
            System.out.println("Invalid input, try again.");
            gameMode();
        }
        return 0;
    }

    //method to test game state-- beginning stages of coding the program
    public void test(int row, int col, int player) {
        board[row][col] = player;
    }

 //mapping of check() returns
    // return 0 if draw (no one wins)
    // return 1 if player 1 wins
    // return 2 if player 2 wins
    // return -2 if board is not yet full (*later: and no one has a connect-4)

    //function to drop chip into column index 0-6, from player 1 or 2
    public void dropChip(int col, int player) {
        for (int i = 5; i >= 0; i--) {
            if (board[i][col] == 0) {
                board[i][col] = player;
                //prints board after puck is dropped
                printBoard();
                //checking if the game has drawed
                int check = check();
                if (check == -2) {
                    break;
                }
                if (check == 0) {
                    endGame();
                }
                else {
                //break; stops the loop
                 endGame();
                }
            }
            if (board[0][col] == 1 || board[0][col] == 2) {
                System.out.println("This column is already full, try picking another column 1-7: ");
                int newCol = chooseCol();
                newCol = newCol - 1;
                dropChip(newCol, player);
            }
        }

    }

    public int chooseCol() {
        Scanner input = new Scanner(System.in);
        int col = input.nextInt();
        return col;
    }

    //method for computer to choose column at random (I don't know advanced algorithms yet lol)
    public int chooseRandomCol() {
        Random random = new Random();
        int randomCol = random.nextInt(7) + 1;
        return randomCol;
    }
    //method to check if any player wins
    // return 0 is no one wins
    // return 1 is player 1 wins
    // return 2 if player 2 wins
    // return -1 if draw
    public boolean checkVertical(int startRow, int startCol) {
        //first: check if row index is invalid for check
            //anything past row index 2 cannot be seen as a win (because one cannot connect-4 
            //past row index 2)
        if (startRow > 2) {return false;}
        //check if cell is not blank
        if (board[startRow][startCol] != 0) {
            //check equalities for a possible vertical connect-4
            if (board[startRow][startCol] == board[startRow +1][startCol]) {
                if (board[startRow][startCol] == board[startRow +2][startCol]) {
                    if (board[startRow][startCol] == board[startRow +3][startCol]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean checkHorizontal(int startRow, int startCol) {
        //logic similar to first check method
        if (startCol > 3) {return false;}

        if (board[startRow][startCol] != 0) {
            if (board[startRow][startCol] == board[startRow][startCol +1]) {
                if (board[startRow][startCol] == board[startRow][startCol +2]) {
                    if (board[startRow][startCol] == board[startRow][startCol +3]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean checkDiag1(int startRow, int startCol) {
        //logic similar to first check method
        if (startRow > 2 || startCol > 3) {return false;}

        if (board[startRow][startCol] != 0) {
            if (board[startRow][startCol] == board[startRow +1][startCol +1]) {
                if (board[startRow][startCol] == board[startRow +2][startCol +2]) {
                    if (board[startRow][startCol] == board[startRow +3][startCol +3]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean checkDiag2(int startRow, int startCol) {
        //logic similar to first check method
        if (startRow > 2 || startCol < 3) {return false;}

        if (board[startRow][startCol] != 0) {
            if (board[startRow][startCol] == board[startRow +1][startCol -1]) {
                if (board[startRow][startCol] == board[startRow +2][startCol -2]) {
                    if (board[startRow][startCol] == board[startRow +3][startCol -3]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //method(s) to check for a connect-4:
    // return 0 if draw (no one wins)
    // return 1 if player 1 wins
    // return 2 if player 2 wins
    // return -2 if board is not yet full (*later: and no one has a connect-4)
    public int check() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (checkVertical(i, j) == true || checkHorizontal(i, j) == true
                    || checkDiag1(i, j) == true || checkDiag2(i, j) == true) {
                        //index + 1 to display true row and column
                        System.out.println("Player " + board[i][j] + " wins!"
                                            + " At row " + (i + 1) + ", column " + (j + 1));
                    return board[i][j];
                    }
            }
        }
        int checkDraw = checkDraw();
        return checkDraw;
    }

    public int checkDraw() {
        boolean draw = true;
        for (int e = 0; e < board[0].length; e++) {
            if (board[0][e] == 0) {
               //will NOT be a draw if board is not full and no one has one yet
                draw = false;
                System.out.println("Next players turn.");
                return -2;
           }
        }
       if (draw) {
       System.out.println("There has been a draw. The board is full but no one won.");
       return 0;
       }
       return 0;
    }

    public void endGame() {
        System.out.println("The game has ended.");
        System.out.println("Would you like to play again? (y)es or (n)o.");
        Scanner gameover = new Scanner(System.in);
        String endOption = gameover.nextLine();
        if (endOption.equals("y")) {
            resetBoard();
            //is it convention to call a class outside of the main class?
            gameMode();
        }
        if (endOption.equals("n")) {
            System.out.println("Thank you for playing!");
            System.exit(0);
        }
        else {
            System.out.println("Invalid output, try again.");
            endGame();
        }

    }


    //method to reset board
    public void resetBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = 0;
            }
        }
    }

    //method to print board
    public void printBoard() {
        System.out.println("---------------------");
        for (int i = 0; i < board.length; i++) {
            //SSystem.out.println("-- -- -- -- -- -- --");
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 0) {
                    System.out.print(" ");
                    System.out.print("*");
                    System.out.print(" ");
                }
                if (board[i][j] == 1) {
                    System.out.print(" ");
                    System.out.print("X");
                    System.out.print(" ");
                }
                else if (board[i][j] == 2) {
                    System.out.print(" ");
                    System.out.print("O");
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
        System.out.println("---------------------");
    }

}

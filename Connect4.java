import java.util.*;
import java.util.Scanner;
import java.util.Random;
public class Connect4 {
    
    public static void gamemode1(C4Board board) {
        board.printBoard();
        System.out.println("Player 1 starts, your chips are 'X'.");
        System.out.println("Choose a column 1-7 to drop a chip.");
        System.out.println("Player 2 goes afterwards, your chips are 'O'.");
        System.out.println("You also choose a column 1-7 to drop a chip.");
        System.out.print("First player to connect 4 chips wins. ");
        System.out.println("If neither wins, the game will end in a draw.");

        //count array set to 42 because there is a maximum of 42 moves per blank board
        int[] count = new int[42];
        count[0] = 1;
        Scanner in = new Scanner(System.in);
        int col;
        for (int i = 0; i < count.length; i++) {
            //player 1 goes if count[i] is odd
            if (count[i] %2 != 0) {
                int player1 = 1;
                col = in.nextInt();
                //substracting 1 to match array index
                col = col - 1;
                board.dropChip(col, player1);
                //makes next count the player2's move
                count[i + 1] = 2;
            }
            if (count[i] %2 == 0) {
                int player2 = 2;
                col = in.nextInt();
                col = col - 1;
                board.dropChip(col, player2);
                count[i + 1] = 1;
            }
        }

    }

    public static void gamemode2(C4Board board) {

    }
    public static void main(String[] args) {
        C4Board board = new C4Board();
        
        int gamemode = board.gameMode();
        if (gamemode == 1) {
            gamemode1(board);
        }
        if (gamemode == 2) {
            gamemode2(board);
        }
        
    }
}

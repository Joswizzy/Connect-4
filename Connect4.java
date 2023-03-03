import java.util.*;
import java.util.Scanner;
import java.util.Random;
public class Connect4 {
    public static void main(String[] args) {
        C4Board start = new C4Board();
        //System.out.println(Arrays.toString(C4Board.getAvailSpot(start)));
        int count = 0;
        //42 is the total spaces in a stadard board
        while (count <= 42) {
            //asking which column player wants to place puck in
            System.out.println(C4Board.playerTurn(count));
            if (C4Board.playerTurn(count) == true) {
                Scanner input = new Scanner(System.in);
                int column = input.nextInt();
                C4Board.dropPuck(start, column);
                C4Board.printBoard(start);
                count = count + 1;
                C4Board.playerTurn(count);
            }
            else {
                Random random = new Random();
                //there are 7 columns (inclusive rando generator)
                int rando = random.nextInt(7) + 1;
                C4Board.dropPuck(start, rando);
                C4Board.printBoard(start);
                count = count + 1;
                C4Board.playerTurn(count);
            }
            System.out.println("# of moves so far: " + count);
        }
    }
}

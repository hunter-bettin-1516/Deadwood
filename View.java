/* View class to be implemented for Model View Controller implementation. For Assgn 2 it will just parse from the command line*/
import java.util.Scanner;
import java.util.*;

public class View {
    
    public static void main(String[] args) throws Exception {
        Moderator mod = new Moderator();
        Scanner scan = new Scanner(System.in);
        System.out.println("How many players?");
        int numPlayers = Integer.parseInt(scan.nextLine());
        mod.setPlayerCount(numPlayers);
        
        //initialize players and game board
        for (int i = 0; i < numPlayers; i++) {
            System.out.println("What is player " + (i + 1) + "'s name?");
            mod.inizializePlayers(scan.nextLine(), i);
        }
        mod.initializeGame();

        for (int i = 0; i < numPlayers; i++) {
            System.out.println(mod.displayPlayerStats(i));
            System.out.println();
        }

        




        scan.close();
        
    }


}

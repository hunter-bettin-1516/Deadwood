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

        while (mod.getGameOver() == false) {
            
            for (int i = 0; i < numPlayers; i++) {
                System.out.println(mod.displayPlayerStats(i));
                System.out.println();
                if (mod.getIsPlayerWorking(i) == true) {
                    
                    //currently working stuff

                } else {
                    System.out.println("What is " + mod.getPlayerName(i) + "'s decision? ('move', 'take a role', 'upgrade')");
                    //repear take a role code here
                    String decision = scan.nextLine();
                    if (decision.equals("move") || decision.equals("Move")) {
                        System.out.println("You can move to these locations: " + mod.getPlayersNeighbors(i) + ". Where would you like to go?");
                        String newLocation = scan.nextLine();
                        mod.move(newLocation, i);
                        System.out.println("You have moved to " + newLocation + ", filming " + mod.getMovieTitle(i) + " with a budget of $" + mod.getMovieBudget(i));
                        System.out.println("Would you like to 'take a role' or 'stay' on location: " + newLocation + "?");
                        String takeRoleOrStay = scan.nextLine();
                        if (takeRoleOrStay.equals("take a role")) {
                            System.out.println("Here is the list of main roles followed by the list of their required ranks: " + mod.getOnCardRoles(i) +", " + mod.getOnCardRanks(i));
                            System.out.println("Here is the list of supporting roles followed by the list of their required ranks: " + mod.getOffCardRoles(i) + ", " + mod.getOffCardRanks(i));
                            System.out.println("What is the name of the role that you would like to take?");   
                            String role = scan.nextLine();
                            mod.setPlayerRole(role, i);
                        } else {
                            System.out.println("Turn Over");
                        }
                    }
                }
            }

        }

        //print winner
        
        scan.close();
        
    }


}

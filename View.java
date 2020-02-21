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
                    System.out.println("This is the movie: " + mod.getMovieTitle(i) + "'s budget: $" + mod.getMovieBudget(i));
                    System.out.println("Would you like to 'act' or 'rehearse'?");
                    String actOrRehearse = scan.nextLine();
                    mod.work(actOrRehearse, i);

                } else {
                    System.out.println("What is " + mod.getPlayerName(i) + "'s decision? ('move', 'take a role', 'upgrade')");
                    //repear take a role code here
                    String decision = scan.nextLine();
                    if (decision.equals("move") || decision.equals("Move")) {
                        System.out.println('\n' + "You can move to these locations: " + mod.getPlayersNeighbors(i) + ". Where would you like to go?");
                        String newLocation = scan.nextLine();
                        mod.move(newLocation, i); //update player location
                        System.out.println('\n' + "You have moved to " + newLocation + ", filming: " + mod.getMovieTitle(i) + " with a budget of $" + mod.getMovieBudget(i) + '\n');
                        System.out.println("Here is the list of main roles followed by the list of their required ranks: " + mod.getOnCardRoles(i) +", " + mod.getOnCardRanks(i) + '\n');
                        System.out.println("Here is the list of supporting roles followed by the list of their required ranks: " + mod.getOffCardRoles(i) + ", " + mod.getOffCardRanks(i) + '\n');
                        System.out.println("Would you like to 'take a role' or 'stay' on location: " + newLocation + "? (Your rank must be >= the role rank.)");
                        String takeRoleOrStay = scan.nextLine();
                        if (takeRoleOrStay.equals("take a role")) {
                            //while loop for if user chooses a role that requires a higher rank than what they have
                            String onOrOff = "";
                            String role = "";
                            boolean validRole = false;
                            int j = 0;
                            while (validRole == false) {
                                if (j == 0) {
                                    System.out.println("Would you like to take a 'main role' or 'supporting role?' ");
                                    onOrOff = scan.nextLine();
                                    System.out.println("What is the name of the role that you would like to take?");   
                                } else {
                                    System.out.println("You do not have the required Rank to take that role.");
                                    System.out.println("Would you like to take a 'main role' or 'supporting role?' ");
                                    onOrOff = scan.nextLine();
                                    System.out.println("What is the name of the role that you would like to take?");  
                                }
                                role = scan.nextLine();
                                validRole = mod.verifyRole(onOrOff, role, i);
                                j++;
                            }
                            mod.setPlayerRole(onOrOff, role, i);
                            System.out.println("Turn Over");
                        } else if(decision.equals("upgrade")) {
                            if (true) { /*currentlocation == casting office*/
                                System.out.println("RANK   DOLLARS   CREDITS \n----   -------   ------- \n2   4   5 \n3   10   10 \n4   18   15 \n5   28   20 \n6   40   25");
                                System.out.println("\nWhat rank would you like to upgrade to?");
                                String toRank = scan.nextLine();
                                System.out.println("\nWould you like to upgrade your rank using 'dollars' or 'credits'");
                                String dollarsOrCredits = scan.nextLine();

                                mod.upgradeAtOffice(toRank, dollarsOrCredits, i);  
                            }
                        } else {
                            System.out.println("Turn Over");
                        }
                    } else if (decision.equals("take a role")) {
                            System.out.println("Here is the list of main roles followed by the list of their required ranks: " + mod.getOnCardRoles(i) +", " + mod.getOnCardRanks(i) + '\n');
                            System.out.println("Here is the list of supporting roles followed by the list of their required ranks: " + mod.getOffCardRoles(i) + ", " + mod.getOffCardRanks(i) + '\n');
                            System.out.println("Would you like to 'take a role' or 'stay'? (Your rank must be >= the role rank.)");
                            String onOrOff = "";
                            String role = "";
                            boolean validRole = false;
                            int j = 0;
                            while (validRole == false) {
                                if (j == 0) {
                                    System.out.println("Would you like to take a 'main role' or 'supporting role?' ");
                                    onOrOff = scan.nextLine();
                                    System.out.println("What is the name of the role that you would like to take?");   
                                } else {
                                    System.out.println("You do not have the required Rank to take that role.");
                                    System.out.println("Would you like to take a 'main role' or 'supporting role?' ");
                                    onOrOff = scan.nextLine();
                                    System.out.println("What is the name of the role that you would like to take?");  
                                }
                                role = scan.nextLine();
                                validRole = mod.verifyRole(onOrOff, role, i);
                                j++;
                            }
                            mod.setPlayerRole(onOrOff, role, i);
                            System.out.println("Turn Over");
                        } else if (decision.equals("upgrade")) {
                            if (true) { /*currentlocation == casting office*/
                                System.out.println("RANK   DOLLARS   CREDITS \n----   -------   ------- \n2   4   5 \n3   10   10 \n4   18   15 \n5   28   20 \n6   40   25");
                                System.out.println("\nWhat rank would you like to upgrade to?");
                                String toRank = scan.nextLine();
                                System.out.println("\nWould you like to upgrade your rank using 'dollars' or 'credits'");
                                String dollarsOrCredits = scan.nextLine();

                                mod.upgradeAtOffice(toRank, dollarsOrCredits, i);
                            }
                        } else {
                            System.out.println("Turn Over");
                        }
                    }
                    
                }
            }

        //print winner
        scan.close();
        }
}



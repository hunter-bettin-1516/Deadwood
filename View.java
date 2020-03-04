import java.util.*;
import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.event.*;
  
public class View {
    public static void main(String[] args) throws Exception {
        BoardLayersListener board = new BoardLayersListener();
        board.setVisible(true);
    
        // Take input from the user about number of players
        JOptionPane.showInputDialog(board, "How many players?"); 
        
        Moderator mod = new Moderator();
        Scanner scan = new Scanner(System.in);
        boolean loner = true;
        System.out.println("How many players?");
        int numPlayers = Integer.parseInt(scan.nextLine());
        if (numPlayers < 2) {
            System.out.println("\nDon't be a loner! Play Deadwood with a friend.");
        } else {
            loner = false;
        }
        
        while (loner == true) {
            System.out.println("\nHow many players?");
            numPlayers = Integer.parseInt(scan.nextLine());
            mod.setPlayerCount(numPlayers);

            if (numPlayers < 2) {
                System.out.println("\nDon't be a loner! Play Deadwood with a friend.");
            } else {
                loner = false;
            }
        }

        //initialize players and game board
        for (int i = 0; i < numPlayers; i++) {
            System.out.println("\nWhat is player " + (i + 1) + "'s name?");
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
                    System.out.println("\nWould you like to 'act' or 'rehearse'?");
                    String actOrRehearse = scan.nextLine();
                    mod.work(actOrRehearse, i);
                } else {
                    if (mod.getPlayerLocation(i).equals("office")) {
                        System.out.println("What is " + mod.getPlayerName(i) + "'s decision? ('move' or 'upgrade')");
                    } else {
                        System.out.println("What is " + mod.getPlayerName(i) + "'s decision? ('move', 'take a role', 'upgrade')");
                    }
                    //repeat take a role code here
                    String decision = scan.nextLine();
                    if (decision.equals("move") || decision.equals("Move")) {
                        System.out.println('\n' + "You can move to these locations: " + mod.getPlayersNeighbors(i) + ". Where would you like to go?");
                        String newLocation = scan.nextLine();
                        try {
                            if (mod.move(newLocation, i) == false) { //update player location
                                continue;
                            }
                        } catch (Exception e) {
                            continue;
                        }
                            
                        if (mod.getPlayerLocation(i).equals("office")) {
                            System.out.println("\nYou have moved to the Casting Office");
                            System.out.println("\nWould you like to 'upgrade or 'stay on location: office?");
                        } else {
                            System.out.println('\n' + "You have moved to " + newLocation + ", filming: " + mod.getMovieTitle(i) + " with a budget of $" + mod.getMovieBudget(i) + '\n');
                            System.out.println("Here is the list of main roles followed by the list of their required ranks: " + mod.getOnCardRoles(i) +", " + mod.getOnCardRanks(i) + '\n');
                            System.out.println("Here is the list of supporting roles followed by the list of their required ranks: " + mod.getOffCardRoles(i) + ", " + mod.getOffCardRanks(i) + '\n');
                            System.out.println("Would you like to 'take a role' or 'stay' on location: " + newLocation + "? (Your rank must be >= the role rank.)");
                        }
                        String takeRoleUpgradeOrStay = scan.nextLine();
                        if (takeRoleUpgradeOrStay.equals("take a role")) {
                            takeARole(mod, scan, i);
                        } else if(takeRoleUpgradeOrStay.equals("upgrade")) {
                            if (mod.getPlayerLocation(i).equals("office")) {
                                upgrade(mod, scan, i);
                            }
                        } else {
                            System.out.println("Turn Over");
                        }
                    } else if (decision.equals("take a role")) {
                            System.out.println("Here is the list of main roles followed by the list of their required ranks: " + mod.getOnCardRoles(i) +", " + mod.getOnCardRanks(i) + '\n');
                            System.out.println("Here is the list of supporting roles followed by the list of their required ranks: " + mod.getOffCardRoles(i) + ", " + mod.getOffCardRanks(i) + '\n');
                            System.out.println("Would you like to 'take a role' or 'stay'? (Your rank must be >= the role rank.)");
                            takeARole(mod, scan, i);
                        } else if (decision.equals("upgrade")) {
                            if (mod.getPlayerLocation(i).equals("office")) {
                                upgrade(mod, scan, i);
                            }
                        } else {
                            System.out.println("\nTurn Over");
                        }
                    }
                }   
            }
            scan.close();  
    }   

    public static void takeARole(Moderator mod, Scanner scan, int i) {
        //while loop for if user chooses a role that requires a higher rank than what they have
        String onOrOff = "";
        String role = "";
        boolean validRole = false;
        int j = 0;
        while (validRole == false) {
            if (j == 0) {
                System.out.println("\nWould you like to take a 'main role' or 'supporting role?' ");
                onOrOff = scan.nextLine();
                if (onOrOff.equals("main role")) {
                    System.out.println("\nWhat is the name of the role that you would like to take? " + mod.getOnCardRoles(i));  
                } else if (onOrOff.equals("supporting role")) {
                    System.out.println("\nWhat is the name of the role that you would like to take? " + mod.getOffCardRoles(i)); 
                }  
            } else {
                System.out.println("\nYou do not have the required Rank to take that role.");
                System.out.println("\nWould you like to take a 'main role' or 'supporting role?' ");
                onOrOff = scan.nextLine();
                if (onOrOff.equals("main role")) {
                    System.out.println("\nWhat is the name of the role that you would like to take? " + mod.getOnCardRoles(i));  
                } else if (onOrOff.equals("supporting role")) {
                    System.out.println("\nWhat is the name of the role that you would like to take? " + mod.getOffCardRoles(i)); 
                }
            }
            role = scan.nextLine();
            validRole = mod.verifyRole(onOrOff, role, i);
            j++;
        }
        mod.setPlayerRole(onOrOff, role, i);
        System.out.println("\nTurn Over");
    }

    public static void upgrade(Moderator mod, Scanner scan, int i) {
            boolean upgradeLoop = true;
            while (upgradeLoop == true) {
                System.out.println("\nRANK   DOLLARS   CREDITS \n----   -------   ------- \n2      4         5 \n3      10        10 \n4      18        15 \n5      28        20 \n6      40        25");
                System.out.println("\nWhat rank would you like to upgrade to?");
                String toRank = scan.nextLine();
                System.out.println("\nWould you like to upgrade your rank using 'dollars' or 'credits'");
                String dollarsOrCredits = scan.nextLine();

                if(mod.canPlayerUpgrade(toRank, dollarsOrCredits, i) == true) { //check if player has enough dollars or credits to rankup
                    mod.upgradeAtOffice(toRank, dollarsOrCredits, i); 
                    upgradeLoop = false;
                } else {
                    System.out.println("\nYou do not have enough "+ dollarsOrCredits + " to upgrade to rank: " + toRank);
                    System.out.println("\nWould you like to ranking up with another form of payment? ('yes' or 'no')");
                    String otherPayment = scan.nextLine();
                    if (otherPayment.equals("yes")) {
                        upgradeLoop = true;
                    } else if (otherPayment.equals("no")){
                        upgradeLoop = false;
                    } 
                }
            }
            System.out.println("\nTurn Over");
    }
}



import java.util.*;
import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.event.*;
  
public class View {
    private static Moderator mod; 
    private static BoardLayersListener board;
    public static void main(String[] args) throws Exception {
        board = new BoardLayersListener();
        board.setVisible(true);

        mod = new Moderator();
        
        board.setMod(mod);
        mod.setGUI(board);
        boolean loner = true;
        String numPlayersStr = JOptionPane.showInputDialog(board, "How many players?");
        int numPlayers = Integer.parseInt(numPlayersStr);
        if (numPlayers < 2 || numPlayers > 8) {
            System.out.println("\nDon't be a loner! Play Deadwood with a friend.");
        } else {
            loner = false;
        }
        
        while (loner == true) {
            numPlayersStr = JOptionPane.showInputDialog(board, "How many players?");
            numPlayers = Integer.parseInt(numPlayersStr);
            mod.setNumPlayers(numPlayers);

            if (numPlayers < 2 || numPlayers > 8) {
                
            } else {
                loner = false;
            }
        }

        //initialize players and game board
        
        for (int i = 0; i < numPlayers; i++) {
            String diceColor = "";
            String playerName = JOptionPane.showInputDialog(board, "What is player " + (i + 1) + "'s name?");
            if (i == 0) {
                diceColor = "blue";
            } else if (i == 1) {
                diceColor = "cyan";
            } else if (i == 2) {
                diceColor = "green";
            } else if (i == 3) {
                diceColor = "orange";
            } else if (i == 4) {
                diceColor = "pink";
            } else if (i == 5) {
                diceColor = "violet";
            } else if (i == 6) {
                diceColor = "white";
            } else if (i == 7) {
                diceColor = "yellow";
            }
            
            mod.initializePlayers(playerName, i, diceColor);
        }
        
        mod.setNumPlayers(numPlayers);
        mod.initializeGame();
        boolean initPlayerStats = true;
        while (mod.getGameOver() == false) {
            for (int i = 0; i < numPlayers; i++) {
                if (initPlayerStats == true){
                    board.userStats(mod, i, false);
                    initPlayerStats = false;
                } else {
                    board.userStats(mod, i, true);
                }

                mod.setCurrentPlayerIndex(i);
                board.makeMoveButtonVisable(true);
                boolean turnOver = false;
                while (turnOver == false) {
                    String input = mod.getInput();
                    System.out.print(""); // input will not ever change without this print ???
                    if (mod.getIsPlayerWorking(i) == true) {
                        board.makeMoveButtonVisable(false);
                        board.makeActButtonsVisable(true);
                        if (input.equals("act") || input.equals("rehearse")) {
                            mod.work(input, i);
                            board.makeActButtonsVisable(false);
                            mod.setInput("");
                            turnOver = true;
                        }    
                    }
                    if (input.equals("move")) {
                       
                        boolean validNeighbor = false;
                        while (validNeighbor == false) {
                            
                            Object[] neighborObjects = mod.getPlayersNeighbors(i).toArray();
                            String whereTo = JOptionPane.showInputDialog(board, "Where would you like to move?", mod.getPlayerName(i), JOptionPane.PLAIN_MESSAGE, null, neighborObjects, "--").toString();
                            if (mod.move(whereTo, i) == true) {
                                validNeighbor = true;
                            } else {
                                JOptionPane.showMessageDialog(board, "You can't move to a non-adjacent room.", mod.getPlayerName(i), JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                        String takeOrStayOrUpgrade;
                        if (mod.getPlayerLocation(i).equals("office")) {
                            Object[] takeOrStayObject = {"upgrade", "stay"};
                            takeOrStayOrUpgrade = JOptionPane.showInputDialog(board, "Would you like to 'take a role' or 'stay' on location: " + mod.getPlayerArray()[i].getLocation(), mod.getPlayerName(i), JOptionPane.PLAIN_MESSAGE, null, takeOrStayObject, "--").toString();
                        } else {
                            Object[] takeOrStayObject = {"take a role", "stay"};
                            takeOrStayOrUpgrade = JOptionPane.showInputDialog(board, "Would you like to 'take a role' or 'stay' on location: " + mod.getPlayerArray()[i].getLocation(), mod.getPlayerName(i), JOptionPane.PLAIN_MESSAGE, null, takeOrStayObject, "--").toString();
                        }
                        
                        if (takeOrStayOrUpgrade.equals("take a role")) {
                            takeARole(i);
                            turnOver = true;
                            mod.setInput("");
                            
                        } else if (takeOrStayOrUpgrade.equals("stay")) {
                            turnOver = true;
                            mod.setInput("");
                        } else if (takeOrStayOrUpgrade.equals("upgrade")) {
                            upgrade(i);
                            turnOver = true;
                            mod.setInput("");
                        }
                    } else if (mod.getPlayerLocation(i).equals("office")) {
                        board.makeUpgradeButtonVisable(true);
                        if (input.equals("upgrade")){
                            upgrade(i);
                            mod.setInput("move");
                        }
                    } else {
                        board.makeUpgradeButtonVisable(false);
                    }
    
                }
            }
        }
        return;

    }

    public static void takeARole(int i) {
        //while loop for if user chooses a role that requires a higher rank than what they have
        String onOrOff = "";
        String role = "";
        boolean validRole = false;
        Object[] onCardRolesObjects = mod.getOnCardRoles(i).toArray();
        Object[] offCardRolesObjects = mod.getOffCardRoles(i).toArray();
        Object[] onOrOffObject = {"main role", "supporting role"};
        int j = 0;
        while (validRole == false) {
            if (j == 0) {
                onOrOff = (String)JOptionPane.showInputDialog(null, "Would you like to take a 'main role' or 'supporting role?'" , mod.getPlayerName(i), JOptionPane.PLAIN_MESSAGE, null, onOrOffObject, "--").toString();
                if (onOrOff.equals("main role")) {
                    role = (String)JOptionPane.showInputDialog(null, "What is the name of the role that you would like to take? ", mod.getPlayerName(i), JOptionPane.PLAIN_MESSAGE, null, onCardRolesObjects, "--").toString();
                } else if (onOrOff.equals("supporting role")) {
                    role = (String)JOptionPane.showInputDialog(null, "What is the name of the role that you would like to take? ", mod.getPlayerName(i), JOptionPane.PLAIN_MESSAGE, null, offCardRolesObjects, "--").toString();
                }  
            } else { 
                onOrOff = (String)JOptionPane.showInputDialog(null, "Would you like to take a 'main role' or 'supporting role?'" , mod.getPlayerName(i), JOptionPane.PLAIN_MESSAGE, null, onOrOffObject, "--").toString();
                if (onOrOff.equals("main role")) {
                    role = JOptionPane.showInputDialog(null, "What is the name of the role that you would like to take? ", mod.getPlayerName(i), JOptionPane.PLAIN_MESSAGE, null, onCardRolesObjects, "--").toString(); 
                } else if (onOrOff.equals("supporting role")) {
                    role = JOptionPane.showInputDialog(null, "What is the name of the role that you would like to take? ", mod.getPlayerName(i), JOptionPane.PLAIN_MESSAGE, null, offCardRolesObjects, "--").toString();
                }
            }
            validRole = mod.verifyRole(onOrOff, role, i);
            j++;
        }
        mod.setPlayerRole(onOrOff, role, i);
    }

    public static void upgrade(int i) {
        boolean upgradeLoop = true;
        while (upgradeLoop == true) {
            ArrayList<Integer> ranksList = new ArrayList<Integer>();
            for (int j = mod.getPlayerArray()[i].getPlayerRank() + 1; j < 7; j++) {
                ranksList.add(j);
            }
            Object[] ranksObjects = ranksList.toArray();
            Object[] dollarsOrCreditsObjects = {"dollars", "credits"};
            Object[] otherPaymentObjects = {"yes", "no"};

            String toRank = JOptionPane.showInputDialog(null, "What rank would you like to upgrade to?", mod.getPlayerName(i), JOptionPane.PLAIN_MESSAGE, null, ranksObjects, "--").toString();
            String dollarsOrCredits = JOptionPane.showInputDialog(null, "Would you like to upgrade your rank using 'dollars' or 'credits'", mod.getPlayerName(i), JOptionPane.PLAIN_MESSAGE, null, dollarsOrCreditsObjects, "--").toString();

            if(mod.canPlayerUpgrade(toRank, dollarsOrCredits, i) == true) { //check if player has enough dollars or credits to rankup
                mod.upgradeAtOffice(toRank, dollarsOrCredits, i);
                board.upgradeDiceLabel(mod.getPlayerArray(), i);
                upgradeLoop = false;
            } else {
                JOptionPane.showMessageDialog(board, "You do not have enough "+ dollarsOrCredits + " to upgrade to rank: " + toRank, mod.getPlayerName(i), JOptionPane.INFORMATION_MESSAGE);
                String otherPayment = JOptionPane.showInputDialog(null, "Would you like to ranking up with another form of payment?", mod.getPlayerName(i), JOptionPane.PLAIN_MESSAGE, null, otherPaymentObjects, "--").toString();
                if (otherPayment.equals("yes")) {
                    upgradeLoop = true;
                } else if (otherPayment.equals("no")){
                    upgradeLoop = false;
                } 
            }
        }
    }
}
                



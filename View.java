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
        if (numPlayers < 2) {
            System.out.println("\nDon't be a loner! Play Deadwood with a friend.");
        } else {
            loner = false;
        }
        
        while (loner == true) {
            numPlayersStr = JOptionPane.showInputDialog(board, "How many players?");
            numPlayers = Integer.parseInt(numPlayersStr);
            mod.setPlayerCount(numPlayers);

            if (numPlayers < 2) {
                System.out.println("\nDon't be a loner! Play Deadwood with a friend.");
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

        int count = 0;
        while (mod.getGameOver() == false) {
            
            
            
            for (int i = 0; i < numPlayers; i++) {
                count+= 1;
                if (count == 1){
                    board.userStats(mod, i, false);
                } else {
                    board.userStats(mod, i, true);
                }

                mod.setCurrentPlayerIndex(i);
                
                boolean turnOver = false;
                while (turnOver == false) {
                    String input = mod.getInput();
                    System.out.print(""); // input will not ever change without this print ???
                    if (input.equals("move")) {
                       
                        boolean validNeighbor = false;
                        while (validNeighbor == false) {
                            String whereTo = JOptionPane.showInputDialog(board, "Where would you like to move?", mod.getPlayerName(i), JOptionPane.INFORMATION_MESSAGE);
                            if (mod.move(whereTo, i) == true) {
                                validNeighbor = true;
                            } else {
                                JOptionPane.showMessageDialog(board, "You can't move to a non-adjacent room.", mod.getPlayerName(i), JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                        String takeOrStay = JOptionPane.showInputDialog(board, "Would you like to 'take a role' or 'stay' on location: " + mod.getPlayerArray()[i].getLocation(), mod.getPlayerName(i), JOptionPane.INFORMATION_MESSAGE);
                        if (takeOrStay.equals("take a role")) {
                            takeARole(mod, i);
                            turnOver = true;
                            mod.setInput("");
                            
                        } else if (takeOrStay.equals("stay")) {
                            turnOver = true;
                            mod.setInput("");
                        } 
                    }
    
                }
            }
        }

    }

    public static void takeARole(Moderator mod, int i) {
        //while loop for if user chooses a role that requires a higher rank than what they have
        String onOrOff = "";
        String role = "";
        boolean validRole = false;
        int j = 0;
        while (validRole == false) {
            if (j == 0) {
                onOrOff = JOptionPane.showInputDialog(board, "Would you like to take a 'main role' or 'supporting role?'", mod.getPlayerName(i), JOptionPane.INFORMATION_MESSAGE);
                if (onOrOff.equals("main role")) {
                    role = JOptionPane.showInputDialog(board, "What is the name of the role that you would like to take? " + mod.getOnCardRoles(i), mod.getPlayerName(i), JOptionPane.INFORMATION_MESSAGE);
                } else if (onOrOff.equals("supporting role")) {
                    role = JOptionPane.showInputDialog(board, "What is the name of the role that you would like to take? " + mod.getOffCardRoles(i), mod.getPlayerName(i), JOptionPane.INFORMATION_MESSAGE);
                }  
            } else { 
                onOrOff = JOptionPane.showInputDialog(board, "You do not have the required Rank to take that role.\nWould you like to take a 'main role' or 'supporting role?'", mod.getPlayerName(i), JOptionPane.INFORMATION_MESSAGE);
                if (onOrOff.equals("main role")) {
                    role = JOptionPane.showInputDialog(board, "What is the name of the role that you would like to take? " + mod.getOnCardRoles(i), mod.getPlayerName(i), JOptionPane.INFORMATION_MESSAGE); 
                } else if (onOrOff.equals("supporting role")) {
                    role = JOptionPane.showInputDialog(board, "What is the name of the role that you would like to take? " + mod.getOffCardRoles(i), mod.getPlayerName(i), JOptionPane.INFORMATION_MESSAGE);
                }
            }
            validRole = mod.verifyRole(onOrOff, role, i);
            j++;
        }
        mod.setPlayerRole(onOrOff, role, i);
    }
}
                



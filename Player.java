import java.util.*;
import javax.swing.JLabel;

public class Player {
    private int credits;
    private int dollars;
    private String playerID;
    private int playerRank = 1;
    private int rehearsalCount = 0;
    private String currentLocation = "Trailer";
    private String currentRole = "No role";
    private boolean onCard = false;
    private boolean working = false;
    private char diceColor;
    private int[] coordinates = new int[2];
    private JLabel playerDice;

    
    public void setPlayerDice(JLabel playerDice) {
        this.playerDice = playerDice;
    }

    public JLabel getPlayerDice() {
        return this.playerDice;
    }

    public void setCoordinates(int[] coordinates) {
        this.coordinates = coordinates;
    }

    public int[] getCoordinates() {
        return coordinates;
    }

    public void setDiceColor(String str) {
        this.diceColor = str.charAt(0);
    }

    public char getDiceColor() {
        return this.diceColor;
    }

    public String getDiceFile() {
        return ("dice/"+this.diceColor + this.playerRank + ".png");
    
    }

    public void setPlayerID(String ID) {
        //set playerID;
        this.playerID = ID;
    }

    public String getPlayerID() {
        //return playerID;
        return this.playerID;
    }

    public void setRole(String role) {
        //set playerID;
        this.currentRole = role;
    }

    public boolean getOnCard() {
        return this.onCard;
    }

    public void setOnCard(boolean t) {
        this.onCard = t;
    }

    public boolean getWorking() {
        return this.working;
    }

    public void setWorking(boolean t) {
        this.working = t;
    }

    public int getRehearsalCount() {
        return this.rehearsalCount;
    }

    public void incrementRehearsalCount() {
        this.rehearsalCount = this.rehearsalCount + 1;
    }

    public void resetRehearsalCount() {
        this.rehearsalCount = 0;
    }


    public String getRole() {
        return this.currentRole;
    }

    public void setDollars(int dollars) {
        this.dollars = dollars;
    }

    public void addDollars(int dollars) {
        this.dollars = this.dollars + dollars;
    }

    public int getDollars() {
        //return dollars;
        return this.dollars;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public void addCredits(int credits) {
        this.credits = this.credits + credits;
    }

    public int getCredits() {
        //return credits;
        return this.credits;
    }

    public void setPlayerRank(int rank) {
        this.playerRank = rank;
    }

    public int getPlayerRank() {
        //return rank;
        return this.playerRank;
    }
    public void setLocation(String location) {
        //set location
        this.currentLocation = location;
    }

    public String getLocation() {
        //return current location
        return this.currentLocation;
    }

}


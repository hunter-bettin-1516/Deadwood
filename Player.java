import java.util.Random;

public class Player {
    private int credits;
    private int dollars;
    private String playerID;
    private int playerRank = 1;
    private int rehearsalCount = 0;
    private String currentLocation = "Trailer";
    private String currentRole = "No role";

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

    public int getRehearsalCount() {
        //return playerID;
        return this.rehearsalCount;
    }

    public void setRehearsalCount(int count) {
        //set playerID;
        this.rehearsalCount = count;
    }

    public String getRole() {
        //return playerID;
        return this.currentRole;
    }

    public void setDollars(int dollars) {
        //set dollars
    }

    public int getDollars() {
        //return dollars;
        return this.dollars;
    }

    public void setCredits(int credits) {
        this.credits = credits;
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



    public static void work(int playerID, String decision) {
        //act or rehearse for a specific player
    }

    public static void move(int playerID, String location) {
        //update Location 
    }


    public static void upgrade(int playerID) {
        //check location for casting office
        //
    }

}
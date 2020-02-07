Public class Moderator {

    private int playerCount;
    private int dayCount = 1;
    
    public static void setPlayerCount(int count) {
        this.playerCount = count;
    }

    public static void initializeGame(int playerCount){
        //update player class based on group size
        //loop creating multiple instances of players including ID,
        //parse board.xml to create multiple instances of location including name, neighbors, associated movie, etc.
    }

    public static void newDay() {
        //this.daycount = this.dayCount + 1;
        //reset board
    }

    public static void playerDecision(int playerID, String decision) {
        //invoke Player Class

    }

    

}
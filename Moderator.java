import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import java.util.*;
import java.util.Random;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.event.*;

public class Moderator {

    private int numPlayers = 0;
    private int dayCount = 1;
    private int maxDays = 4;
    private boolean gameOver = false;
    private int activeMovies = 10; 
    private ParseXML xml = new ParseXML();
    private Location[] locations = new Location[12];
    private Movie[] movies = new Movie [40];
    private HashMap<String, Location> locationMap = new HashMap<String, Location>();
    private Random random = new Random();
    private Player[] players = new Player[8];
    private int[] finalScores = new int[8];
    private PayoutPackage payPackage = new PayoutPackage();
    private CastingOffice castOffice = new CastingOffice();
    private int winner = -1;
    private BoardLayersListener GUI;
    private String playerInput = "";
    private int currentPlayerIndex;

   
    public int getCurrentPlayerIndex() {
        return this.currentPlayerIndex;
    }

    public void setCurrentPlayerIndex(int currentPlayerIndex) {
        this.currentPlayerIndex = currentPlayerIndex;
    }

    public String getInput() {
        return this.playerInput;
    }

    public void setInput(String input) {
        this.playerInput = input;
    }

    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
    }

    public int getNumPlayers() {
        return this.numPlayers;
    }

    public void setGUI(BoardLayersListener gui) {
        this.GUI = gui;
    }

    public BoardLayersListener getGUI() {
        return this.GUI;
    }

    public Player[] getPlayerArray() {
        return this.players;
    }

    public String getPlayerLocation(int i) {
        return this.players[i].getLocation();
    }

    public ArrayList<String> getOnCardRoles(int i) {
        return this.locationMap.get(this.players[i].getLocation()).getLocationsMovieCard().getPartNameList();
    }

    public ArrayList<Integer> getOnCardRanks(int i) {
        return this.locationMap.get(this.players[i].getLocation()).getLocationsMovieCard().getOnCardRolesList();
    }

    public ArrayList<String> getOffCardRoles(int i) {
        return this.locationMap.get(this.players[i].getLocation()).getPartNameList();
    }

    public ArrayList<Integer> getOffCardRanks(int i) {
        return this.locationMap.get(this.players[i].getLocation()).getOffCardRolesList();
    }

    public String getMovieTitle(int i) {
        return this.locationMap.get(this.players[i].getLocation()).getLocationsMovieCard().getMovieTitle();
    }

    public int getMovieBudget(int i) {
        return this.locationMap.get(this.players[i].getLocation()).getLocationsMovieCard().getMovieBudget();
    }

    public void setGameOver(boolean t) {
        this.gameOver = t;
    }

    public boolean getGameOver() {
        return this.gameOver;
    }


    public String getPlayerName(int i) {
        return this.players[i].getPlayerID();
    }

    public ArrayList<String> getPlayersNeighbors(int i){
        return this.locationMap.get(this.players[i].getLocation()).getNeighborList();
    }

    public boolean getIsPlayerWorking(int i) {
        return this.players[i].getWorking();
    }

    public void initializeGame() throws Exception { 
        Document doc = xml.getDocFromFile("board.xml");
        Document cardDoc = xml.getDocFromFile("cards.xml");
        
        //populate array of locations
        for (int i = 0; i < 12; i++) {
            this.locations[i] = new Location(); 
        }
        //populate array of Movies
        for (int i = 0; i < 40; i++) {
            this.movies[i] = new Movie(); 
        }
        //set proper movie attributes
        for (int i = 0; i < 40; i++) {
            this.movies[i].setMovieTitle(xml.getCardName(cardDoc, i)); //set movie role name for each instance 
            this.movies[i].setOnCardRolesList(xml.getOnCardRolesArrayList(cardDoc, i)); //set oncard role 
            this.movies[i].setOnCardRolesListCopy(xml.getOnCardRolesArrayList(cardDoc, i));
            this.movies[i].setMovieBudget(xml.getBudget(cardDoc, i)); //set movie budget
            this.movies[i].setPartNameList(xml.getPartNamesArrayList(cardDoc, i)); //set oncard role name
            this.movies[i].setPartNameListCopy(xml.getPartNamesArrayList(cardDoc, i));

            this.movies[i].setCardFile(xml.getCardImage(cardDoc, i));
            
            this.movies[i].setOnCardXCoordinates(xml.getonCardXCoordinateArrayList(cardDoc, i));
            

        }

        //set location name, neighbors, shot counters, and offcard roles for each instance
        for (int i = 0; i < 10; i++) {
            this.locations[i].setIndex(i);
            this.locations[i].setLocationName((xml.getSetName(doc, i)).toLowerCase()); 
            this.locations[i].setNeighbors(xml.getNeighborArrayList(doc, i));
            this.locations[i].setShotCounters(xml.getShotCountersArrayList(doc, i));
            this.locations[i].setOffCardRoles(xml.getOffCardRolesArrayList(doc, i));
            this.locations[i].setOffCardRolesCopy(xml.getOffCardRolesArrayList(doc, i));
            this.locations[i].setPartNameList(xml.getOffCardPartsArrayList(doc, i));
            this.locations[i].setPartNameListCopy(xml.getOffCardPartsArrayList(doc, i));
            //set coordinates
            this.locations[i].setShotCounterXCoords(xml.getShotCounterXCoordinateArrayList(doc, i));
            this.locations[i].setShotCounterYCoord(xml.getShotCounterYCoordinate(doc, i));
            this.locations[i].setOffCardRolesXCoordinates(xml.getOffCardRoleXCoordinates(doc, i));
            this.locations[i].setOffCardRolesYCoordinates(xml.getOffCardRoleYCoordinates(doc, i));
            this.locations[i].setMovieCardCoordinates(xml.getMovieCardCoordinatesArrayList(doc, i));
            //populate a random movie to each location
            boolean findNewCard = true;
            while (findNewCard == true) {
                int rand = random.nextInt(40);
                if(this.movies[rand].getUsedMovie() == false) { 
                    this.locations[i].setLocationsMovieCard(this.movies[rand]);
                    this.movies[rand].setUsedMovie(true);
                    findNewCard = false;
                }
            }
        }
        //call set movie card function in BoardLayersListener pass in whole locations array
        this.GUI.placeMovieCards(this.locations);
        this.GUI.placeShotCounterLabels(this.locations);
        
        

        //trailer and office are not a sets, so must be handled differently
        this.locations[10].setLocationName("trailer");
        ArrayList<String> trailerNeighbors = new ArrayList<String>(
            Arrays.asList("main street", "saloon", "hotel"));
        this.locations[10].setNeighbors(trailerNeighbors);
        this.locations[10].setIndex(10);



        this.locations[11].setLocationName("office");
        ArrayList<String> officeNeighbors = new ArrayList<String>(
            Arrays.asList("train station", "ranch", "secret hideout"));
        this.locations[11].setNeighbors(officeNeighbors);
        this.locations[11].setIndex(11);

        //populate (HashMap)locationMap. Key = Location Name, Value = Location instance.
        for (int i = 0; i < 12; i++) {
            this.locationMap.put(this.locations[i].getLocationName(), this.locations[i]);
        }

        GUI.initializePlayerDice(this.players, this.numPlayers);
        GUI.setPlayersToTrailer(this.players, this.numPlayers);
    }

    //init player instances
    public void initializePlayers(String ID, int i, String diceColor) {
        
        this.players[i] = new Player();
        
        this.players[i].setPlayerID(ID);

        this.players[i].setDiceColor(diceColor);

        if (this.numPlayers < 4) {
            this.maxDays = 3; 
        } 
        else if (this.numPlayers == 5) {
            this.players[i].setCredits(2);
        }
        else if (this.numPlayers == 6) {
                this.players[i].setCredits(4);
            
        } else {
                this.players[i].setPlayerRank(2);
        }
    }

    //castingOffice class - method call for upgrading Rank
    public void upgradeAtOffice(String toRank, String dollarsOrCredits, int i) {
        this.castOffice.upgradeRank(toRank, dollarsOrCredits, i, players);
        JOptionPane.showMessageDialog(null, "Congrats! You are now rank: " + this.players[i].getPlayerRank(), players[i].getPlayerID(), JOptionPane.INFORMATION_MESSAGE);
    }

    public boolean canPlayerUpgrade(String toRank, String dollarsOrCredits, int i) {
        int rank = Integer.parseInt(toRank);
        if (dollarsOrCredits.equals("dollars")) {
            int[] dollarArr = this.castOffice.getRankPriceDollars();

            if ((this.players[i].getDollars() - dollarArr[rank - 2]) >= 0) {
                return true;
            }
        } else if (dollarsOrCredits.equals("credits")) {
            int[] creditArr = this.castOffice.getRankPriceCredits();

            if ((this.players[i].getCredits() - creditArr[rank - 2]) >= 0) {
                return true;
            }
        }
        return false;
    }
    
    //displays user stats at start of every turn
    public String displayPlayerStats(int i) {
        String stats = "\nIt is " + players[i].getPlayerID() + "'s turn. ||| Current Location = " + players[i].getLocation() + " ||| Current role = " + players[i].getRole() + " ||| Rehearsal chips = " + players[i].getRehearsalCount() + " ||| Rank = " + players[i].getPlayerRank() + " ||| Dollars = " + players[i].getDollars() + " ||| Credits = " + players[i].getCredits(); 
        return stats;
    }

    //sets main/supporting roles to specified player[i]
    public void setPlayerRole(String onOrOff, String role, int i) {
        if(onOrOff.equals("main role")){
            this.locationMap.get(this.players[i].getLocation()).getLocationsMovieCard().addOnCardWorker(i);
            this.players[i].setOnCard(true);
            int roleIndex = this.locationMap.get(this.players[i].getLocation()).getLocationsMovieCard().getPartNameList().indexOf(role);
            this.locationMap.get(this.players[i].getLocation()).getLocationsMovieCard().getOnCardRolesList().remove(roleIndex); //remove role and role rank for future actors
            this.locationMap.get(this.players[i].getLocation()).getLocationsMovieCard().getPartNameList().remove(roleIndex);
        } else if (onOrOff.equals("supporting role")) {
            this.locationMap.get(this.players[i].getLocation()).addOffCardWorker(i);
            int roleIndex = this.locationMap.get(this.players[i].getLocation()).getPartNameList().indexOf(role);
            this.locationMap.get(this.players[i].getLocation()).getOffCardRolesList().remove(roleIndex); //remove role and role rank for future actors
            this.locationMap.get(this.players[i].getLocation()).getPartNameList().remove(roleIndex);
        }
        this.players[i].setRole(role);
        this.players[i].setWorking(true);
    }

    //boolean verifies if player[i] has required rank for a given role 
    public boolean verifyRole(String onOrOff, String role, int i){
        if (onOrOff.equals("main role")) {
            int roleIndex = this.locationMap.get(this.players[i].getLocation()).getLocationsMovieCard().getPartNameList().indexOf(role);
            int roleRank = this.locationMap.get(this.players[i].getLocation()).getLocationsMovieCard().getOnCardRolesList().get(roleIndex);
            if ((this.players[i].getPlayerRank()) >= roleRank ){
                //call GUI func to move player dice to oncard or off card role
                this.GUI.movePlayerDiceToRole(onOrOff, this.locationMap.get(this.players[i].getLocation()).getLocationsMovieCard().getPartNameListCopy().indexOf(role), this.locationMap, players[i]);
                return true;
            }
        } else if (onOrOff.equals("supporting role")) {
            int roleIndex = this.locationMap.get(this.players[i].getLocation()).getPartNameList().indexOf(role);
            int roleRank = this.locationMap.get(this.players[i].getLocation()).getOffCardRolesList().get(roleIndex);
            if ((this.players[i].getPlayerRank()) >= roleRank) {
                this.GUI.movePlayerDiceToRole(onOrOff, this.locationMap.get(this.players[i].getLocation()).getPartNameListCopy().indexOf(role), this.locationMap, players[i]);
                return true;
            }
        }
        return false;
    } 

    //act/rehearse function for specified player[i]
    public void work(String decision, int i) {
        int movieBudget = locationMap.get(players[i].getLocation()).getLocationsMovieCard().getMovieBudget();
        int roll = 0;
        if (decision.equals("act")) {
            roll = random.nextInt(6) + 1;
            if ((roll + this.players[i].getRehearsalCount()) >=  movieBudget) {
                JOptionPane.showMessageDialog(this.GUI, "You rolled a: " + roll + ". With a rehearsal count of: " + this.players[i].getRehearsalCount() + ". Act Successful!", this.players[i].getPlayerID() + ", acting as " + this.players[i].getRole() + ".", JOptionPane.INFORMATION_MESSAGE);
                this.SuccessfulAct(i);
            } else {
                JOptionPane.showMessageDialog(this.GUI, "You rolled a: " + roll + ". With a rehearsal count of: " + this.players[i].getRehearsalCount() + ". Act Unsuccessful. Sucks.", this.players[i].getPlayerID() + ", acting as " + this.players[i].getRole() + ".", JOptionPane.INFORMATION_MESSAGE);
                this.failedRoll(i);
            }
        } else if (decision.equals("rehearse")) {
            if (this.players[i].getRehearsalCount() == movieBudget) {
                JOptionPane.showMessageDialog(this.GUI, "Your rehearsal count is equal to the movie budget, acting success is guarenteed. baby.", this.players[i].getPlayerID() + ", rehearsing for " + this.players[i].getRole() + ".", JOptionPane.INFORMATION_MESSAGE);
                this.work("act", i);
            } else {
                this.players[i].incrementRehearsalCount();
                JOptionPane.showMessageDialog(this.GUI, "Your current rehearsal count is: " + this.players[i].getRehearsalCount() + ".", this.players[i].getPlayerID() + ", rehearsing for " + this.players[i].getRole() + ".", JOptionPane.INFORMATION_MESSAGE);                
            }
        }
    }

    //decrements shot counts, decrements activeMovies, calls payoutPackage class for payout calculations, calls newDay when only 1 movie is left
    public void SuccessfulAct(int i) {
        this.locationMap.get(this.players[i].getLocation()).removeShotCounter(); // remove shotcounter
        this.GUI.removeShotCounterLabel(this.locationMap, this.players[i]);
        //check to see if movie is a wrap
        if (this.locationMap.get(this.players[i].getLocation()).getShotCounters() == 0) {
            this.locationMap.get(this.players[i].getLocation()).getLocationsMovieCard().setMovieIsAWrap(true);
            this.GUI.removeMovieCard(this.locationMap, this.players[i]);
            JOptionPane.showMessageDialog(null, "Movie is a Wrap!", "", JOptionPane.PLAIN_MESSAGE);
            this.activeMovies = this.activeMovies - 1;
            if (this.activeMovies >= 1) { 
                ArrayList<Integer> onCardWorkerList = this.locationMap.get(this.players[i].getLocation()).getLocationsMovieCard().getOnCardWorkers();
                ArrayList<Integer> offCardWorkerList = this.locationMap.get(this.players[i].getLocation()).getOffCardWorkers();
                //check if people are working on card
                if (onCardWorkerList.size() > 0) {
                    this.payPackage.onCardPayout(onCardWorkerList, this.locationMap, this.players);
                    if (offCardWorkerList.size() > 0) {
                        this.payPackage.offCardPayout(offCardWorkerList, this.locationMap, this.players);
                    }
                }
                resetPlayerWorkingStats(onCardWorkerList, offCardWorkerList); 
            }
            //if only one movie card left, start new day
            if (this.activeMovies == 1) { 
                try {
                    this.newDay();
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("newDay() is bugged");
                }
            } 
        //else payments for successful act when movie is NOT a wrap (shoutCounters > 0)
        } else {
            if (this.players[i].getOnCard() == true) {
                this.players[i].addCredits(2);
            } else {
                this.players[i].addCredits(1);
                this.players[i].addDollars(1);
            }
        }
    }

    //payments for failed rolls
    public void failedRoll(int i) {
        if (players[i].getOnCard() == false) {
            this.players[i].addDollars(1);
            JOptionPane.showMessageDialog(null, "Thanks for nothing! Here's a dollar.");
        } else {
            JOptionPane.showMessageDialog(null, "Thanks for nothing! Should have rehearsed more.");
        }
    }

    //reset: player role, rehearsal chips and working flag when movie is a Wrap
    public void resetPlayerWorkingStats(ArrayList<Integer> onCardWorkers, ArrayList<Integer> offCardWorkers) {
        for (int i = 0; i < onCardWorkers.size(); i++) {
            int playerIndex = onCardWorkers.get(i);
            this.players[playerIndex].setWorking(false);
            this.players[playerIndex].resetRehearsalCount();
            this.players[playerIndex].setRole("No role");
        }
        for (int i = 0; i < offCardWorkers.size(); i++) {
            int playerIndex = offCardWorkers.get(i);
            this.players[playerIndex].setWorking(false);
            this.players[playerIndex].resetRehearsalCount();
            this.players[playerIndex].setRole("No role");
        }
    }
    
    //update player[i]s Location when they choose to move
    public boolean move(String location, int i) throws Exception {
        if (this.locationMap.get(this.players[i].getLocation()).getNeighborList().contains(location)) {
            this.players[i].setLocation(location);
            if (location.equalsIgnoreCase("office") || location.equalsIgnoreCase("trailer")) {
                
            } else {
                if (this.locationMap.get(location).getHasBeenVisited() == false) {
                    this.locationMap.get(location).setHasBeenVisited(true);
                    this.GUI.flipMovieCard(this.locationMap, location);
                }
            }
            this.GUI.movePlayerDice(this.players, this.locationMap, location, i);
            return true;
        } 
        return false;
    }

    //increments day counter, puts players back to 'trailer', reshuffles movie cards and shotcounters
    public void newDay() throws Exception {
        Document doc = xml.getDocFromFile("board.xml");
        this.dayCount = this.dayCount + 1;
        //call endGame when the last day is over
        if (this.dayCount > this.maxDays) {
            this.endGame();
        } else {
            JOptionPane.showMessageDialog(null, "It is now day: " + this.dayCount + ". Everyone is moved back to the Trailer");
            System.out.println("\n");
            for (int i = 0; i < this.numPlayers; i++) {
                this.players[i].setLocation("trailer");
                this.players[i].setRole("No role");
                this.players[i].setWorking(false);
                this.players[i].resetRehearsalCount();
            }
            for (int i = 0; i < 10; i++) {
                this.locations[i].setShotCounters(xml.getShotCountersArrayList(doc, i));
                this.locations[i].setOffCardRoles(xml.getOffCardRolesArrayList(doc, i));
                this.locations[i].setPartNameList(xml.getOffCardPartsArrayList(doc, i));
                this.locations[i].setHasBeenVisited(false);
                this.locations[i].removeAllShotCounterLabels();
                boolean findNewCard = true;
                while (findNewCard == true) {
                    int rand = random.nextInt(40);
                    if(this.movies[rand].getUsedMovie() == false) { 
                        this.locations[i].setLocationsMovieCard(this.movies[rand]);
                        this.movies[rand].setUsedMovie(true);
                        findNewCard = false;
                    }
                }
                this.locations[i].getLocationsMovieCard().setMovieIsAWrap(false);
            }
            this.GUI.deleteAllShotCountersFromBoard();
            this.GUI.removeOldMovieCards(this.locations);
            this.GUI.setPlayersToTrailer(this.players, this.numPlayers);
            this.GUI.placeMovieCards(this.locations);
            this.GUI.placeShotCounterLabels(this.locations);
            this.activeMovies = 10;
        }
    }

    //calculate winner and end game.
    public void endGame() {
        for (int i = 0; i < this.numPlayers; i++) {
            int rankScore = (this.players[i].getPlayerRank()) * 5;
            int credits = this.players[i].getCredits();
            int dollars = this.players[i].getDollars();
            int score = rankScore + credits + dollars;
            this.finalScores[i] = score;
        }
        int bestScore = 0;
        for (int i = 0; i < this.players.length; i++) {
            if (this.finalScores[i] > bestScore) {
                bestScore = this.finalScores[i];
                this.winner = i; 
            }
        } 
        this.gameOver = true;
        JOptionPane.showMessageDialog(null, "GAME-OVER\n THE WINNER IS: " + this.players[this.winner].getPlayerID() + ". With a score of: " + bestScore);
    }


    /*----------------------------------------------------------------TEST FUNCTIONS------------------------------------------------------------------------*/

    public void testLocations() {
        //loop for testing data is being stored properly within location
        for (int i = 0; i < 10; i++) {
            //test locations[] attribute population except for trailer
            System.out.println("this is the name: " + this.locations[i].getLocationName() + " ||| these are the neighbors: " + this.locations[i].getNeighborList() + " ||| these are shotcounters: " + this.locations[i].getShotCounters() + " ||| these are the offCardRole levels: " + this.locations[i].getOffCardRolesList() + " ||| these are the offCardRole names: " + this.locations[i].getPartNameList() + " ||| this is this locations random movie card: " + this.locations[i].getLocationsMovieCard().getMovieTitle()); //null pointer due to no movieCard assigned to trailer
            System.out.println("");
            System.out.println(""); 
            
            //test each location's linked random movie
            System.out.println("this is " + this.locations[i].getLocationName() + "'s random movie card: '" + this.locations[i].getLocationsMovieCard().getMovieTitle() + "' and this is its list of roles and levels: " + this.locations[i].getLocationsMovieCard().getPartNameList() + " , " + this.locations[i].getLocationsMovieCard().getOnCardRolesList());
            System.out.println("");
        }
    }

    public void testLocationsMap() {
        for (int i = 0; i < 10; i++) {
            //test hashmap population         
            System.out.println("these are the neighbors using the HashMap: " + this.locationMap.get(this.locations[i].getLocationName()).getNeighborList());
            System.out.println("");
            System.out.println("");
        }
    }

    public void testMovies() {
        for (int i = 0; i < 40; i++) {
            System.out.println("these are the movieTitles: " +  this.movies[i].getMovieTitle() + " this is the movies budget: $" + this.movies[i].getMovieBudget());
            System.out.println("");
            System.out.println("");
            System.out.println("these are the lists of onCard levels: " +  this.movies[i].getOnCardRolesList());
            System.out.println(""); 
        }
    }

    public void testPlayers() {
        //test player[] population
        for (int i = 0; i < this.numPlayers; i++) {
            System.out.println("these are " + this.players[i].getPlayerID() + "'s credits: " + this.players[i].getCredits());
            System.out.println("");
        }
    }

    public void testNewDay() {
        for (int i = 0; i < 10; i++) {
            System.out.println("this is " + this.locations[i].getLocationName() + "'s random movie card after the new day: '" + this.locations[i].getLocationsMovieCard().getMovieTitle() + "' and this is its list of roles and levels: " + this.locations[i].getLocationsMovieCard().getPartNameList() + " , " + this.locations[i].getLocationsMovieCard().getOnCardRolesList());
            System.out.println("");
        }
    }
}
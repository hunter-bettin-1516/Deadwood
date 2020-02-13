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

public class Moderator {

    private int playerCount; //length of players[]
    private int dayCount = 1;
    private int maxDays = 4;
    private ParseXML xml = new ParseXML();
    private Location[] locations = new Location[11];
    private Movie[] movies = new Movie [40];
    private HashMap<String, Location> locationMap = new HashMap<String, Location>();
    private Random random = new Random();
    private Player[] players = new Player[8];


    public void setPlayerCount(int count) {
        this.playerCount = count;
    }

    public void initializeGame() throws Exception { 
        Document doc = xml.getDocFromFile("board.xml");
        Document cardDoc = xml.getDocFromFile("cards.xml");
        
        //populate array of locations
        for (int i = 0; i < 11; i++) {
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
            this.movies[i].setMovieBudget(xml.getBudget(cardDoc, i)); //set movie budget
            this.movies[i].setPartNameList(xml.getPartNamesArrayList(cardDoc, i)); //set oncard role name
        }

        //set location name, neighbors, shot counters, and offcard roles for each instance
        for (int i = 0; i < 10; i++) {
            this.locations[i].setLocationName(xml.getSetName(doc, i)); 
            this.locations[i].setNeighbors(xml.getNeighborArrayList(doc, i));
            this.locations[i].setShotCounters(xml.getShotCountersArrayList(doc, i));
            this.locations[i].setOffCardRoles(xml.getOffCardRolesArrayList(doc, i));
            
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
        
        //trailer is not a set so must be handled differently
        this.locations[10].setLocationName("Trailer");
        ArrayList<String> trailerNeighbors = new ArrayList<String>(
            Arrays.asList("Main Station", "Saloon", "Hotel"));
        this.locations[10].setNeighbors(trailerNeighbors);

        //populate (HashMap)locationMap. Key = Location Name, Value = Location instance.
        for (int i = 0; i < 11; i++) {
            this.locationMap.put(this.locations[i].getLocationName(), this.locations[i]);
        }
    }

    public void inizializePlayers(String ID, int i) {
        
        this.players[i] = new Player();
        
        this.players[i].setPlayerID(ID);

        if (this.playerCount < 4) {
            this.maxDays = 3;
        } 
        else if (this.playerCount == 5) {
            this.players[i].setCredits(2);
        }
        else if (this.playerCount == 6) {
                this.players[i].setCredits(4);
            
        } else {
                this.players[i].setPlayerRank(2);
        }
    }
    
    public String displayPlayerStats(int i) {
        String stats = "It is " + players[i].getPlayerID() + "'s turn. ||| Current Location = " + players[i].getLocation() + " ||| Current role = " + players[i].getRole() + " ||| Rehearsal chips = " + players[i].getRehearsalCount() + " ||| Rank = " + players[i].getPlayerRank() + " ||| Dollars = " + players[i].getDollars() + " ||| Credits = " + players[i].getCredits(); 
        return stats;
    }

    public void newDay() throws Exception {
        Document doc = xml.getDocFromFile("board.xml");
        this.dayCount = this.dayCount + 1;
        //reset board
        for (int i = 0; i < playerCount; i++) {
            this.players[i].setLocation("Trailer");
        }
        //reshuffle new movie cards to locations
        for (int i = 0; i < 10; i++) {
            this.locations[i].setShotCounters(xml.getShotCountersArrayList(doc, i));
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


    }

    public void playerDecision(int playerID, String decision) {
        //invoke Player Class

    }

    public void testLocations() {
        //loop for testing data is being stored properly within location
        for (int i = 0; i < 10; i++) {
            //test locations[] attribute population except for trailer
            System.out.println("this is the name: " + this.locations[i].getLocationName() + " ||| these are the neighbors: " + this.locations[i].getNeighborList() + " ||| these are shotcounters: " + this.locations[i].getShotCounters() + " ||| these are the offCardRole levels: " + this.locations[i].getOffCardRolesList() + " ||| this is this locations random movie card: " + this.locations[i].getLocationsMovieCard().getMovieTitle()); //null pointer due to no movieCard assigned to trailer
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
            System.out.println(""); 
            
        }
    }

    public void testPlayers() {
        //test player[] population
        for (int i = 0; i < this.playerCount; i++) {
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
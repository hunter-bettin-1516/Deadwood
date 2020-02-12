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

    private int playerCount;
    private int dayCount = 1;
    private ParseXML xml = new ParseXML();
    private Location[] locations = new Location[11];
    private Movie[] movies = new Movie [40];
    private HashMap<String, Location> locationMap = new HashMap<String, Location>();
    private Random random = new Random();
    
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
        //loop for testing data is being stored properly within location
        
        for (int i = 0; i < 10; i++) {
            //test locations[] attribute population except for trailer
            System.out.println("this is the name: " + this.locations[i].getLocationName() + " ||| these are the neighbors: " + this.locations[i].getNeighborList() + " ||| these are shotcounters: " + this.locations[i].getShotCounters() + " ||| these are the offCardRole levels: " + this.locations[i].getOffCardRolesList() + " ||| this is this locations random movie card: " + this.locations[i].getLocationsMovieCard().getMovieTitle()); //null pointer due to no movieCard assigned to trailer
            System.out.println("");
            System.out.println(""); 
            
            //test each location's linked random movie
            System.out.println("this is " + this.locations[i].getLocationName() + "'s random movie card: '" + this.locations[i].getLocationsMovieCard().getMovieTitle() + "' and this is its list of roles and levels: " + this.locations[i].getLocationsMovieCard().getPartNameList() + " , " + this.locations[i].getLocationsMovieCard().getOnCardRolesList());
            System.out.println("");
            
            //test hashmap population         
            System.out.println("these are the neighbors using the HashMap: " + this.locationMap.get(this.locations[i].getLocationName()).getNeighborList());
            System.out.println("");
            System.out.println("");
            
        }
        //test movie[] population
        for (int i = 0; i < 40; i++) {
            
            System.out.println("these are the movieTitles: " +  this.movies[i].getMovieTitle() + " this is the movies budget: $" + this.movies[i].getMovieBudget());
            System.out.println("");
            System.out.println("");
            System.out.println("these are the lists of onCard levels: " +  this.movies[i].getOnCardRolesList());
            System.out.println(""); 
            System.out.println("");
        }

    }

    public void gameSize(int count){
        if(count < 4) {
            //endGame.numDays == 3
        }
        if(count == 5){
            //playerCredits +=2
        }
        if(count == 6){
            //playerCredits += 4
        }
        if(count >= 6){
            //playerRank =+1
        }
    }   

    public void newDay() {
        //this.daycount = this.dayCount + 1;
        //reset board
    }

    public void playerDecision(int playerID, String decision) {
        //invoke Player Class

    }

    

}
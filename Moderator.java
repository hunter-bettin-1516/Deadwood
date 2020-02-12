import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import java.util.*;

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
    private HashMap<String, Location> locationMap = new HashMap<String, Location>();

    
    public void setPlayerCount(int count) {
        this.playerCount = count;
    }

    public void initializeGame() throws Exception { 
        Document doc = xml.getDocFromFile("board.xml");
        
        //populate array of locations
        for (int i = 0; i < 11; i++) {
            this.locations[i] = new Location(); 
        }
        //set location name, neighbors, shot counters, and offcard roles for each instance
        for (int i = 0; i < 10; i++) {
            this.locations[i].setLocationName(xml.getSetName(doc, i)); 
            this.locations[i].setNeighbors(xml.getNeighborArrayList(doc, i));
            this.locations[i].setShotCounters(xml.getShotCountersArrayList(doc, i));
            this.locations[i].setOffCardRoles(xml.getOffCardRolesArrayList(doc, i));
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
        for (int i = 0; i < 11; i++) {
            //test locations[] population
            System.out.println("this is the name: " + this.locations[i].getLocationName() + " ||| these are the neighbors: " + this.locations[i].getNeighborList() + " ||| these are shotcounters: " + this.locations[i].getShotCounters() + " ||| these are the offCardRole levels: " + this.locations[i].getOffCardRolesList()); // work in progress
            System.out.println("");
            System.out.println("");
            System.out.println(""); 
            //test hashmap population         
            System.out.println("these are the neighbors using the HashMap: " + this.locationMap.get(this.locations[i].getLocationName()).getNeighborList());
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
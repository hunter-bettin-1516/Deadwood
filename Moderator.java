import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;


import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class Moderator {

    private int playerCount;
    private int dayCount = 1;
    private ParseXML xml = new ParseXML();
    private Location[] locations = new Location[10];

    
    public void setPlayerCount(int count) {
        this.playerCount = count;
    }

    public void initializeGame() throws Exception { 
        //update player class based on group size
        //loop creating multiple instances of players including ID,
        //parse board.xml to create multiple instances of location including name, neighbors, associated movie, etc.
       Document doc = xml.getDocFromFile("board.xml");
       //xml.readBoardData(doc);

       for (int i = 0; i < 10; i++) {
            String name = xml.getSetName(doc, i);
            this.locations[i].setLocationName(name); 
       }
       
       System.out.println(this.locations[0].getLocationName());

       System.out.println(this.locations[1].getLocationName());

       System.out.println(this.locations[5].getLocationName());




    }

    public void newDay() {
        //this.daycount = this.dayCount + 1;
        //reset board
    }

    public void playerDecision(int playerID, String decision) {
        //invoke Player Class

    }

    

}
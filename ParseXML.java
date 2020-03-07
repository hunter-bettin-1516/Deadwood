import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;


import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.*;

public class ParseXML{

        // building a document from the XML file
        // returns a Document object after loading the book.xml file.
        public Document getDocFromFile(String filename)
        throws ParserConfigurationException{
        {     
           DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
           DocumentBuilder db = dbf.newDocumentBuilder();
           Document doc = null;
           
           try{
               doc = db.parse(filename);
           } catch (Exception ex){
               System.out.println("XML parse failure");
               ex.printStackTrace();
           }
           return doc;
        } // exception handling
        
        } 
        
         //return card name string
         public String getCardName(Document d, int i) {
            Element root = d.getDocumentElement(); // <cards tag>
            NodeList cards = root.getElementsByTagName("card");
            Node card = cards.item(i);
            return card.getAttributes().getNamedItem("name").getNodeValue();
             
         }

       //return budget integer
         public int getBudget(Document d, int i) {
            Element root = d.getDocumentElement(); //<cards tag>
            NodeList cards = root.getElementsByTagName("card"); 
            Node card = cards.item(i);
            return Integer.parseInt(card.getAttributes().getNamedItem("budget").getNodeValue());  
         }

         //return on card roles array list
         public ArrayList<Integer> getOnCardRolesArrayList(Document d, int i) {
            Element root = d.getDocumentElement(); 
            ArrayList<Integer> partsArr = new ArrayList<Integer>();
            NodeList cards = root.getElementsByTagName("card"); 
            Node card = cards.item(i);         
            NodeList children = card.getChildNodes();
            for (int j=0; j< children.getLength(); j++){
               Node sub = children.item(j);
               if("part".equals(sub.getNodeName())){
                  partsArr.add(Integer.parseInt(sub.getAttributes().getNamedItem("level").getNodeValue())); //populate onCard arrayList                          
               }
            }
            return partsArr;
         }

         //return on card part names array list
         public ArrayList<String> getPartNamesArrayList(Document d, int i) {
            Element root = d.getDocumentElement(); // <board> tag
            ArrayList<String> partsArr = new ArrayList<String>();
            NodeList cards = root.getElementsByTagName("card"); 
            Node card = cards.item(i);         
            NodeList children = card.getChildNodes();
            for (int j=0; j< children.getLength(); j++){
               Node sub = children.item(j);
               if("part".equals(sub.getNodeName())){
                  partsArr.add(sub.getAttributes().getNamedItem("name").getNodeValue()); //populate on card part names arrayList                          
               }
            }
            return partsArr;
         }

         public String getSetName(Document d, int i) {
            Element root = d.getDocumentElement(); // <board> tag
            NodeList sets = root.getElementsByTagName("set");
            Node set = sets.item(i);
            return set.getAttributes().getNamedItem("name").getNodeValue();
        }


        //return neighbor array list
         public ArrayList<String> getNeighborArrayList(Document d, int i) {
            Element root = d.getDocumentElement(); // <board> tag
            ArrayList<String> neighborArr = new ArrayList<String>();
            NodeList sets = root.getElementsByTagName("set");
            //reads data from the nodes
            Node set = sets.item(i);
            //reads data         
            NodeList children = set.getChildNodes();
            for (int j=0; j< children.getLength(); j++){ 
               Node sub = children.item(j);
               
               if("neighbors".equals(sub.getNodeName())){
                  NodeList grandchildren = sub.getChildNodes();
                  for (int z=0; z< grandchildren.getLength(); z++) {
                     Node neighbor = grandchildren.item(z);
                     if("neighbor".equals(neighbor.getNodeName())) {  
                        neighborArr.add(neighbor.getAttributes().getNamedItem("name").getNodeValue()); //populate neighbor arrayList                        
                     }
                  }   
               }
            }
            return neighborArr;
         }

         //return shotCounters array list
         public ArrayList<String> getShotCountersArrayList(Document d, int i) {
            Element root = d.getDocumentElement(); // <board> tag
            ArrayList<String> takesArr = new ArrayList<String>();  
            NodeList sets = root.getElementsByTagName("set");
            //reads data from the nodes
            Node set = sets.item(i);
            //reads data         
            NodeList children = set.getChildNodes();
            for (int j=0; j< children.getLength(); j++){
               Node sub = children.item(j);
               if("takes".equals(sub.getNodeName())){
                  NodeList grandchildren = sub.getChildNodes();
                  for (int z=0; z< grandchildren.getLength(); z++) {
                     Node take = grandchildren.item(z);
                     if("take".equals(take.getNodeName())) {  
                        takesArr.add(take.getAttributes().getNamedItem("number").getNodeValue()); //populate shotcounter arrayList                        
                     }
                  }   
               }
            }
            return takesArr;
         }
         //return x coordinates for shotcounters for each location
         public ArrayList<Integer> getShotCounterXCoordinateArrayList(Document d, int i) {
            Element root = d.getDocumentElement(); // <board> tag
            ArrayList<Integer> XCoords = new ArrayList<Integer>();  
            NodeList sets = root.getElementsByTagName("set");
            //reads data from the nodes
            Node set = sets.item(i);
            //reads data         
            NodeList children = set.getChildNodes();
            for (int j=0; j< children.getLength(); j++){
               Node sub = children.item(j);
               if("takes".equals(sub.getNodeName())){
                  NodeList grandchildren = sub.getChildNodes();
                  for (int z=0; z< grandchildren.getLength(); z++) {
                     Node take = grandchildren.item(z);
                     if("take".equals(take.getNodeName())) {
                        NodeList greatgrandchildren = take.getChildNodes();
                        for (int y=0; y<greatgrandchildren.getLength(); y++) {
                           Node area = greatgrandchildren.item(y);
                           if ("area".equals(area.getNodeName())) {
                              XCoords.add(Integer.parseInt(area.getAttributes().getNamedItem("x").getNodeValue())); //populate shotcounter arrayList  
                           }
                        }  
                                              
                     }
                  }   
               }
            }
            return XCoords;
         }

         //return the y coordinate for each shotcounter
         public int getShotCounterYCoordinate(Document d, int i) {
            Element root = d.getDocumentElement(); // <board> tag  
            NodeList sets = root.getElementsByTagName("set");
            //reads data from the nodes
            int YCoord = 0;
            Node set = sets.item(i);
            //reads data         
            NodeList children = set.getChildNodes();
            for (int j=0; j< children.getLength(); j++){
               Node sub = children.item(j);
               if("takes".equals(sub.getNodeName())){
                  NodeList grandchildren = sub.getChildNodes();
                  for (int z=0; z< grandchildren.getLength(); z++) {
                     Node take = grandchildren.item(z);
                     if("take".equals(take.getNodeName())) {
                        NodeList greatgrandchildren = take.getChildNodes();
                        for (int y=0; y<greatgrandchildren.getLength(); y++) {
                           Node area = greatgrandchildren.item(y);
                           if ("area".equals(area.getNodeName())) {
                              YCoord = (Integer.parseInt(area.getAttributes().getNamedItem("y").getNodeValue())); //populate shotcounter arrayList  
                           }
                        }  
                                              
                     }
                  }   
               }
            }
            return YCoord;
         }

         // return X coordinates for offcard roles
         public ArrayList<Integer> getOffCardRoleXCoordinates(Document d, int i) {
            Element root = d.getDocumentElement(); // <board> tag
            ArrayList<Integer> XCoords = new ArrayList<Integer>();
            NodeList cards = root.getElementsByTagName("set"); 
            Node card = cards.item(i);         
            NodeList children = card.getChildNodes();
            for (int j=0; j< children.getLength(); j++){
               Node sub = children.item(j);
               if("part".equals(sub.getNodeName())){
                  NodeList grandchildren = sub.getChildNodes();
                  for (int z = 0; z < grandchildren.getLength(); z++) {
                     Node area = grandchildren.item(z);
                     if ("area".equals(area.getNodeName())) {
                        XCoords.add(Integer.parseInt(area.getAttributes().getNamedItem("x").getNodeValue()));
                     }
                  }                   
               }
            }
            return XCoords;
         }

         // return Y coordinates for offcard roles
         public ArrayList<Integer> getOffCardRoleYCoordinates(Document d, int i) {
            Element root = d.getDocumentElement(); // <board> tag
            ArrayList<Integer> YCoords = new ArrayList<Integer>();
            NodeList cards = root.getElementsByTagName("set"); 
            Node card = cards.item(i);         
            NodeList children = card.getChildNodes();
            for (int j=0; j< children.getLength(); j++){
               Node sub = children.item(j);
               if("part".equals(sub.getNodeName())){
                  NodeList grandchildren = sub.getChildNodes();
                  for (int z = 0; z < grandchildren.getLength(); z++) {
                     Node area = grandchildren.item(z);
                     if ("area".equals(area.getNodeName())) {
                        YCoords.add(Integer.parseInt(area.getAttributes().getNamedItem("y").getNodeValue()));
                     }
                  }                   
               }
            }
            return YCoords;
         }


         //return offcardroles array list
         public ArrayList<Integer> getOffCardRolesArrayList(Document d, int i) {
            Element root = d.getDocumentElement(); // <board> tag
            ArrayList<Integer> partsArr = new ArrayList<Integer>(); 
            NodeList sets = root.getElementsByTagName("set");
            Node set = sets.item(i);      
            NodeList children = set.getChildNodes(); 
            for (int j=0; j< children.getLength(); j++){   
               Node sub = children.item(j);
               if("parts".equals(sub.getNodeName())){
                  NodeList grandchildren = sub.getChildNodes();
                  for (int z=0; z< grandchildren.getLength(); z++) {
                     Node part = grandchildren.item(z);
                     if("part".equals(part.getNodeName())) {  
                        partsArr.add(Integer.parseInt(part.getAttributes().getNamedItem("level").getNodeValue())); //populate offcardroles arrayList                        
                     }
                  }   
               }
            }
            return partsArr;
         }

         public ArrayList<String> getOffCardPartsArrayList(Document d, int i) {
            Element root = d.getDocumentElement(); // <board> tag
            ArrayList<String> partsArr = new ArrayList<String>(); 
            NodeList sets = root.getElementsByTagName("set");
            Node set = sets.item(i);      
            NodeList children = set.getChildNodes(); 
            for (int j=0; j< children.getLength(); j++){   
               Node sub = children.item(j);
               if("parts".equals(sub.getNodeName())){
                  NodeList grandchildren = sub.getChildNodes();
                  for (int z=0; z< grandchildren.getLength(); z++) {
                     Node part = grandchildren.item(z);
                     if("part".equals(part.getNodeName())) {  
                        partsArr.add(part.getAttributes().getNamedItem("name").getNodeValue()); //populate offCardParts arrayList                        
                     }
                  }   
               }
            }
            return partsArr;
         }

         //return MovieCard coordinate
         public ArrayList<Integer> getMovieCardCoordinatesArrayList(Document d, int i) {
            Element root = d.getDocumentElement(); // <board> tag
            ArrayList<Integer> cardlocationArr = new ArrayList<Integer>();  
            NodeList sets = root.getElementsByTagName("set");
            //reads data from the nodes
            Node set = sets.item(i);
            //reads data         
            NodeList children = set.getChildNodes();
            for (int j=0; j< children.getLength(); j++){
               Node area = children.item(j);
               if("area".equals(area.getNodeName())){
                  cardlocationArr.add(Integer.parseInt(area.getAttributes().getNamedItem("x").getNodeValue()));  //populate x-coordinate for Movie card arrayList   
                  cardlocationArr.add(Integer.parseInt(area.getAttributes().getNamedItem("y").getNodeValue()));  //populate y-coordinate for Movie card arrayList
               }
            }
            return cardlocationArr;
         }

         //return onCard role x coordinate
         public ArrayList<Integer> getonCardXCoordinateArrayList(Document d, int i) {
            Element root = d.getDocumentElement(); // <board> tag
            ArrayList<Integer> areaArr = new ArrayList<Integer>();
            NodeList cards = root.getElementsByTagName("card"); 
            Node card = cards.item(i);         
            NodeList children = card.getChildNodes();
            for (int j=0; j< children.getLength(); j++){
               Node sub = children.item(j);
               if("area".equals(sub.getNodeName())){
                areaArr.add(Integer.parseInt((sub.getAttributes().getNamedItem("x").getNodeValue()))); //populate on card x coordinate arrayList                          
               }
            }
            return areaArr;
         }

         //return movie card image
         public String getCardImage(Document d, int i) {
            Element root = d.getDocumentElement(); //<cards tag>
            NodeList cards = root.getElementsByTagName("card"); 
            Node card = cards.item(i);
            return card.getAttributes().getNamedItem("img").getNodeValue();  
         }

}//class
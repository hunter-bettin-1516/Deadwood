// Example Code for parsing XML file
// Dr. Moushumi Sharmin
// CSCI 345

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
}//class
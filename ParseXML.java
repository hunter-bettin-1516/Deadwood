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

public class ParseXML{

   
        // building a document from the XML file
        // returns a Document object after loading the book.xml file.
        public static Document getDocFromFile(String filename)
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
        
        // reads data from XML file and prints data
        public static void readBoardData(Document d){
        
            Element root = d.getDocumentElement(); // <board> tag
            
            NodeList sets = root.getElementsByTagName("set");
            System.out.println(root.toString());
            System.out.println(sets.toString());
            for (int i=0; i<sets.getLength();i++){
                
                System.out.println("Printing information for sets "+(i+1));
                
                //reads data from the nodes
                Node set = sets.item(i);
                String setNeighbors = set.getAttributes().getNamedItem("name").getNodeValue();
                System.out.println("current set = "+setNeighbors);
                
                //reads data
                                             
                NodeList children = set.getChildNodes();
                
                for (int j=0; j< children.getLength(); j++){
                    
                  Node sub = children.item(j);
                
                  if("neighbors".equals(sub.getNodeName())){
                     NodeList grandchildren = sub.getChildNodes();
                     for (int z=0; z< grandchildren.getLength(); z++) {
                        Node neighbor = grandchildren.item(z);
                        if("neighbor".equals(neighbor.getNodeName())) {  
                           String neighborList = neighbor.getAttributes().getNamedItem("name").getNodeValue();
                           System.out.println("Neighbors = "+neighborList);
                        }
                     }   
                  }
                  
                  else if("parts".equals(sub.getNodeName())){
                     NodeList grandchildren = sub.getChildNodes();
                     for (int z=0; z< grandchildren.getLength(); z++) {
                        Node part = grandchildren.item(z);
                        if("part".equals(part.getNodeName())) {  
                           String partNameList = part.getAttributes().getNamedItem("name").getNodeValue();
                           System.out.println("part name = "+partNameList);
                           String partList = part.getAttributes().getNamedItem("level").getNodeValue();
                           System.out.println("part level = "+partList);
                        }
                     }   
                  }
                  else if("takes".equals(sub.getNodeName())){
                     NodeList grandchildren = sub.getChildNodes();
                     for (int z=0; z< grandchildren.getLength(); z++) {
                        Node take = grandchildren.item(z);
                        if("take".equals(take.getNodeName())) {  
                           String takesList = take.getAttributes().getNamedItem("number").getNodeValue();
                           System.out.println("Take number = "+takesList);
                        }
                     }   
                  }
                                 
                
                } //for childnodes
                
                System.out.println("\n");
                
            }//for book nodes
        
        }// method
    
    



}//class
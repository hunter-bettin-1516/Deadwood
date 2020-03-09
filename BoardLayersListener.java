/*

   Deadwood GUI helper file
   Author: Moushumi Sharmin
   This file shows how to create a simple GUI using Java Swing and Awt Library
   Classes Used: JFrame, JLabel, JButton, JLayeredPane

*/

import java.awt.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.event.*;

public class BoardLayersListener extends JFrame {

  static final long serialVersionUID = 10l;
  // JLabels
  JLabel boardlabel;
  JLabel cardlabel;
  JLabel playerlabel;
  JLabel mLabel;
  
  ImageIcon icon; //THE BOARD
  //JButtons
  JButton bAct;
  JButton bRehearse;
  JButton bMove;
  
  // JLayered Pane
  JLayeredPane bPane;

  //player stats 
  JLabel location;
  JLabel role;
  JLabel rehearsalChips;
  JLabel credits;
  JLabel dollars;
  JLabel rank;
  JLabel diceLabel;
  JLabel dicePNGLabel;
  //dice png
  ImageIcon playerIcon;

  ImageIcon[] movieCards = new ImageIcon[10];

  JLabel[] movieCardsLabel = new JLabel[10];

  ImageIcon shotCounterImg = new ImageIcon("shot.png");

  JLabel[] shotCounterLabels = new JLabel[22];
  
  // Constructor
  
  public BoardLayersListener() {
      
       // Set the title of the JFrame
       super("Deadwood");
       // Set the exit option for the JFrame
       setDefaultCloseOperation(EXIT_ON_CLOSE);
      
       // Create the JLayeredPane to hold the display, cards, dice and buttons
       bPane = getLayeredPane();
    
       // Create the deadwood board
       boardlabel = new JLabel();
       icon =  new ImageIcon("board.jpg");
       boardlabel.setIcon(icon); 
       boardlabel.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());
      
       // Add the board to the lowest layer
       bPane.add(boardlabel, Integer.valueOf(0));
      
       // Set the size of the GUI
       setSize(icon.getIconWidth()+200,icon.getIconHeight());
       
       // Add a scene card to this room
       cardlabel = new JLabel();
       ImageIcon cIcon =  new ImageIcon("01.png");
       cardlabel.setIcon(cIcon); 
       cardlabel.setBounds(20,65,cIcon.getIconWidth()+2,cIcon.getIconHeight());
       cardlabel.setOpaque(true);
      
       // Add the card to the lower layer
       bPane.add(cardlabel, Integer.valueOf(1));
       
      

    
       // Add a dice to represent a player. 
       // Role for Crusty the prospector. The x and y co-ordiantes are taken from Board.xml file
       playerlabel = new JLabel();
       ImageIcon pIcon = new ImageIcon("r2.png");
       playerlabel.setIcon(pIcon);
       //playerlabel.setBounds(114,227,pIcon.getIconWidth(),pIcon.getIconHeight());  
       playerlabel.setBounds(114,227,46,46);
       playerlabel.setVisible(false);
       bPane.add(playerlabel, Integer.valueOf(3));
      
       // Create the Menu for action buttons
       mLabel = new JLabel("MENU");
       mLabel.setBounds(icon.getIconWidth()+40,0,100,20);
       bPane.add(mLabel, Integer.valueOf(2));

       // Create Action buttons
       bAct = new JButton("ACT");
       bAct.setBackground(Color.white);
       bAct.setBounds(icon.getIconWidth()+10, 30,100, 20);
       bAct.addMouseListener(new boardMouseListener());
       
       bRehearse = new JButton("REHEARSE");
       bRehearse.setBackground(Color.white);
       bRehearse.setBounds(icon.getIconWidth()+10,60,110, 20);
       bRehearse.addMouseListener(new boardMouseListener());
       
       bMove = new JButton("MOVE");
       bMove.setBackground(Color.white);
       bMove.setBounds(icon.getIconWidth()+10,90,100, 20);
       bMove.addMouseListener(new boardMouseListener());

       // Place the action buttons in the top layer
       bPane.add(bAct, Integer.valueOf(2));
       bPane.add(bRehearse, Integer.valueOf(2));
       bPane.add(bMove, Integer.valueOf(2));
  
       
  
   }

   public void userStats(Moderator mod, int i, boolean remove) {
      if (remove == true) {
         bPane.remove(this.diceLabel);
         bPane.remove(this.dicePNGLabel);
         bPane.remove(this.location);
         bPane.remove(this.role);
         bPane.remove(this.rehearsalChips);
         bPane.remove(this.rank);
         bPane.remove(this.dollars);
         bPane.remove(this.credits);
      }
      
      this.diceLabel = new JLabel("Your dice:");
      diceLabel.setBounds(icon.getIconWidth()+10,260,100,20);
      
      String diceFile = mod.getPlayerArray()[i].getDiceFile();
      this.playerIcon =  new ImageIcon(diceFile);
      this.dicePNGLabel = new JLabel(playerIcon);
      this.dicePNGLabel.setBounds(icon.getIconWidth()+60,215,100,100);
      

      this.location = new JLabel("Your location:    " + mod.getPlayerLocation(i));
      this.location.setBounds(icon.getIconWidth()+10,290,500,20);

      this.role = new JLabel("Your role:    " + mod.getPlayerArray()[i].getRole());
      this.role.setBounds(icon.getIconWidth()+10,320,500,20);

      this.rehearsalChips = new JLabel("Rehearsal chips:    " + mod.getPlayerArray()[i].getRehearsalCount());
      this.rehearsalChips.setBounds(icon.getIconWidth()+10,350,500,20);

      this.rank = new JLabel("Your rank:    " + mod.getPlayerArray()[i].getPlayerRank());
      this.rank.setBounds(icon.getIconWidth()+10,380,500,20);

      this.dollars = new JLabel("Your dollars:    " + mod.getPlayerArray()[i].getDollars());
      this.dollars.setBounds(icon.getIconWidth()+10,410,500,20);

      this.credits = new JLabel("Your credits:    " + mod.getPlayerArray()[i].getCredits());
      this.credits.setBounds(icon.getIconWidth()+10,440,500,20);

      bPane.add(this.diceLabel, Integer.valueOf(2));
      bPane.add(this.dicePNGLabel, Integer.valueOf(2));
      bPane.add(this.location, Integer.valueOf(2));
      bPane.add(this.role, Integer.valueOf(2));
      bPane.add(this.rehearsalChips, Integer.valueOf(2));
      bPane.add(this.rank, Integer.valueOf(2));
      bPane.add(this.dollars, Integer.valueOf(2));
      bPane.add(this.credits, Integer.valueOf(2));
      bPane.validate();
  }

  public void placeMovieCards(Location[] locArr) {
      for (int i = 0; i < 10; i ++) {
         this.movieCards[i] = new ImageIcon(locArr[i].getLocationsMovieCard().getCardFile());

         this.movieCardsLabel[i] = new JLabel(this.movieCards[i]);

         this.movieCardsLabel[i].setBounds(locArr[i].getMovieCardCoordinates().get(0), locArr[i].getMovieCardCoordinates().get(1), 205, 115);

         bPane.add(this.movieCardsLabel[i], Integer.valueOf(2));
      } 
  }

  public void placeShotCounterLabels(Location[] locArr) { 
   //counter for iterating through each shot counter label
   int count = 0;
   for (int i = 0; i < 10; i ++) {
      for (int j = 0; j < locArr[i].getShotCounters(); j++) {
         if (locArr[i].getLocationName().equals("General Store")) {
            if (j == 0) {
               this.shotCounterLabels[count] = new JLabel(this.shotCounterImg);
               this.shotCounterLabels[count].setBounds(313, 330, 47, 47);
            } else {
               this.shotCounterLabels[count] = new JLabel(this.shotCounterImg);
               this.shotCounterLabels[count].setBounds(313, 277, 47, 47);
            }
            
         } else {
            this.shotCounterLabels[count] = new JLabel(this.shotCounterImg);
            this.shotCounterLabels[count].setBounds(locArr[i].getShotCounterXCoords().get(j), locArr[i].getShotCounterYCoord(), 47, 47);
         }
         
         locArr[i].addShotCounterLabel(this.shotCounterLabels[count]);

         bPane.add(this.shotCounterLabels[count], Integer.valueOf(2));
         count += 1;
      }
   }
  }



  
  
  // This class implements Mouse Events
  
  class boardMouseListener implements MouseListener{
  
      // Code for the different button clicks
      public void mouseClicked(MouseEvent e) {
         
         if (e.getSource()== bAct){
            playerlabel.setVisible(true);
            System.out.println("Acting is Selected\n");
         }
         else if (e.getSource()== bRehearse){
            System.out.println("Rehearse is Selected\n");
         }
         else if (e.getSource()== bMove){
            System.out.println("Move is Selected\n");
         }         
      }
      public void mousePressed(MouseEvent e) {
      }
      public void mouseReleased(MouseEvent e) {
      }
      public void mouseEntered(MouseEvent e) {
      }
      public void mouseExited(MouseEvent e) {
      }
   }
}
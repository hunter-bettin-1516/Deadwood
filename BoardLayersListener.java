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
       bRehearse.setBounds(icon.getIconWidth()+10,60,100, 20);
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

   public void userStats(Moderator mod, int i) {
      JLabel diceLabel = new JLabel("Your dice:");
      diceLabel.setBounds(icon.getIconWidth()+10,260,100,20);
      
      String diceFile = mod.getPlayerArray()[i].getDiceFile();
      ImageIcon playerIcon =  new ImageIcon(diceFile);
      JLabel dicePNGLabel = new JLabel(playerIcon);
      dicePNGLabel.setBounds(icon.getIconWidth()+60,215,100,100);
      

      JLabel location = new JLabel("Your location:    " + mod.getPlayerLocation(i));
      location.setBounds(icon.getIconWidth()+10,290,500,20);

      JLabel role = new JLabel("Your role:    " + mod.getPlayerArray()[i].getRole());
      role.setBounds(icon.getIconWidth()+10,320,500,20);

      JLabel rehearsalChips = new JLabel("Rehearsal chips:    " + mod.getPlayerArray()[i].getRehearsalCount());
      rehearsalChips.setBounds(icon.getIconWidth()+10,350,500,20);

      JLabel rank = new JLabel("Your rank:    " + mod.getPlayerArray()[i].getPlayerRank());
      rank.setBounds(icon.getIconWidth()+10,380,500,20);

      JLabel dollars = new JLabel("Your dollars:    " + mod.getPlayerArray()[i].getDollars());
      dollars.setBounds(icon.getIconWidth()+10,410,500,20);

      JLabel credits = new JLabel("Your credits:    " + mod.getPlayerArray()[i].getCredits());
      credits.setBounds(icon.getIconWidth()+10,440,500,20);

      bPane.add(diceLabel, Integer.valueOf(2));
      bPane.add(dicePNGLabel, Integer.valueOf(2));
      bPane.add(location, Integer.valueOf(2));
      bPane.add(role, Integer.valueOf(2));
      bPane.add(rehearsalChips, Integer.valueOf(2));
      bPane.add(rank, Integer.valueOf(2));
      bPane.add(dollars, Integer.valueOf(2));
      bPane.add(credits, Integer.valueOf(2));
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
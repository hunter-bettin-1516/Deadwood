import java.util.*;
public class CastingOffice{
    private static String location = "office";
    private static int[] rankPriceDollars = {4, 10, 18, 28, 40};
    private static int[] rankPriceCredits = {5, 10, 15, 20, 25};
    private static  ArrayList<String> neighbors = new ArrayList<String>(
        Arrays.asList("Train Station", "Ranch", "Secret Hideout"));//casting office neighbors.
    

    public void upgradeRank(String toRank, String dollarsOrCredits,int i, Player[] players) {
        //used to calculate which rank the player wants to upgrade to then subtracts credits/dollars.
        if (dollarsOrCredits.equals("dollars")) {
            int newRank = Integer.parseInt(toRank);
            players[i].addDollars(-(rankPriceDollars[newRank - 2]));
            players[i].setPlayerRank(newRank);
        } else if (dollarsOrCredits.equals("credits")) {
            int newRank = Integer.parseInt(toRank);
            players[i].addCredits(-(rankPriceCredits[newRank - 2]));
            players[i].setPlayerRank(newRank);
        }
    };
    
}
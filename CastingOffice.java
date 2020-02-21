import java.util.*;
public class CastingOffice{
    private int[] rankPriceDollars = {4, 10, 18, 28, 40};
    private int[] rankPriceCredits = {5, 10, 15, 20, 25};
    
        public int[] getRankPriceDollars() {
            return this.rankPriceDollars;
        }

        public int[] getRankPriceCredits() {
            return this.rankPriceCredits;
        }

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
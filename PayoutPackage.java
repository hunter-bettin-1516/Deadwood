import java.util.*;
public class PayoutPackage {

    public void onCardPayout(ArrayList<Integer> onCardList) {
        //algorithm to calculate oncard payout then add that to the players dollars/credits

    }

    public void offCardPayout(ArrayList<Integer> offCardList, HashMap<String, Location> locationMap, Player[] players) {
        //algorithm to calculate offcard payout then add that to the players dollars/credits
        for (int i = 0; i < offCardList.size(); i++) {
            int playerIndex = offCardList.get(i);
            String role = players[playerIndex].getRole();
            int roleIndex = locationMap.get(players[playerIndex].getLocation()).getPartNameListCopy().indexOf(role);
            int roleRank = locationMap.get(players[playerIndex].getLocation()).getOffCardRolesListCopy().get(roleIndex);

            players[playerIndex].addDollars(roleRank);
        }
        
    }
}
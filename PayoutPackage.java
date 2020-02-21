import java.util.*;
import java.util.Random;
public class PayoutPackage {

    public void onCardPayout(ArrayList<Integer> onCardList, HashMap<String, Location> locationMap, Player[] players) {
        //algorithm to calculate oncard payout then add that to the players dollars/credits
        Random rand = new Random();
        int budget = locationMap.get(players[onCardList.get(0)].getLocation()).getLocationsMovieCard().getMovieBudget();
        ArrayList<Integer> randomDice = new ArrayList<Integer>();

        int payoutPosition[] = new int[locationMap.get(players[onCardList.get(0)].getLocation()).getLocationsMovieCard().getOnCardRolesListCopy().size()];

        //populate arraylist of random rolls for a given budget
        for (int i = 0; i < budget; i++) {
            int randomRoll = rand.nextInt(6) + 1;
            randomDice.add(randomRoll);
        }
        Collections.sort(randomDice); //sort in ascending order

        System.out.println(" \n ############## THIS IS THE ARRAY OF SORTED RANDOM DICE ROLLS: " + randomDice);
            int numDice = randomDice.size() - 1;
            int onCardRolesLength = locationMap.get(players[onCardList.get(0)].getLocation()).getLocationsMovieCard().getOnCardRolesListCopy().size() -1;
            for (int i = onCardRolesLength; i >=0; i-- ) {
                if (numDice == -1) {
                    break;
                } else {
                    System.out.println("\n ###########this is numDice count: " + numDice);
                    payoutPosition[i] = payoutPosition[i] + randomDice.get(numDice);
                    System.out.println("\n #### Should be the total payout amount### this is payout position at index: " + i +"; " + payoutPosition[i]);
                    numDice--;

                }
                if (i == 0) {
                    i = onCardRolesLength + 1;
                }
            }

            for (int i = 0; i < onCardList.size(); i++) {
                String role = players[onCardList.get(i)].getRole();
                System.out.println("\n ########### this is player: " + players[onCardList.get(i)].getPlayerID() + "'s role: " + role);
                int roleIndex = locationMap.get(players[onCardList.get(i)].getLocation()).getLocationsMovieCard().getPartNameListCopy().indexOf(role);
                System.out.println("\n ### this is the index of the role, " + role +"------- index ="+ roleIndex);
                int payoutDollars = payoutPosition[roleIndex];
                players[onCardList.get(i)].addDollars(payoutDollars);
            }
    }

    public void offCardPayout(ArrayList<Integer> offCardList, HashMap<String, Location> locationMap, Player[] players) {
        //algorithm to calculate offcard payout then add that to the players dollars/credits
        for (int i = 0; i < offCardList.size(); i++) {
            int playerIndex = offCardList.get(i);
            String role = players[playerIndex].getRole();
            int roleIndex = locationMap.get(players[playerIndex].getLocation()).getPartNameListCopy().indexOf(role);
            int roleRank = locationMap.get(players[playerIndex].getLocation()).getOffCardRolesCopy().get(roleIndex);

            players[playerIndex].addDollars(roleRank);
        }
        
    }
}
import java.util.*;
public class PayoutPackage {

    //algorithm to calculate oncard payout then add that to the players dollars/credits
    public void onCardPayout(ArrayList<Integer> onCardList, HashMap<String, Location> locationMap, Player[] players) {
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

            int numDice = randomDice.size() - 1;
            int onCardRolesLength = locationMap.get(players[onCardList.get(0)].getLocation()).getLocationsMovieCard().getOnCardRolesListCopy().size() -1;
            for (int i = onCardRolesLength; i >=0; i-- ) {
                if (numDice == -1) {
                    break;
                } else {
                    payoutPosition[i] = payoutPosition[i] + randomDice.get(numDice);
                    numDice--;

                }
                if (i == 0) {
                    i = onCardRolesLength + 1;
                }
            }

            for (int i = 0; i < onCardList.size(); i++) {
                String role = players[onCardList.get(i)].getRole();
                int roleIndex = locationMap.get(players[onCardList.get(i)].getLocation()).getLocationsMovieCard().getPartNameListCopy().indexOf(role);
                int payoutDollars = payoutPosition[roleIndex];
                players[onCardList.get(i)].addDollars(payoutDollars);
            }
    }

    //algorithm to calculate offcard payout then add that to the players dollars/credits
    public void offCardPayout(ArrayList<Integer> offCardList, HashMap<String, Location> locationMap, Player[] players) {
        for (int i = 0; i < offCardList.size(); i++) {
            int playerIndex = offCardList.get(i);
            String role = players[playerIndex].getRole();
            int roleIndex = locationMap.get(players[playerIndex].getLocation()).getPartNameListCopy().indexOf(role);
            int roleRank = locationMap.get(players[playerIndex].getLocation()).getOffCardRolesCopy().get(roleIndex);

            players[playerIndex].addDollars(roleRank);
        }
    }
}
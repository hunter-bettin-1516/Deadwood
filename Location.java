import java.util.*;

public class Location {
    private String locationName;
    private ArrayList<String> neighbors = new ArrayList<String>();
    private ArrayList<Integer> offCardRoles = new ArrayList<Integer>(); //part levels in integers
    private ArrayList<String> partNameList = new ArrayList<String>();
    private ArrayList<Integer> offCardRolesCopy = new ArrayList<Integer>(); //part levels in integers
    private ArrayList<String> partNameListCopy = new ArrayList<String>();
    private int shotCounters = 0;
    private Movie movie;
    private ArrayList<Integer> offCardWorkers = new ArrayList<Integer>(); //stores the index of the player index that is on this locations offCard role

    //getters and setters for attributes

    public void setLocationName(String name) {
        this.locationName = name;
    }

    public String getLocationName() {
        return this.locationName;
    }

    public void setNeighbors(ArrayList<String> neighbors) {
        this.neighbors = neighbors;
    }

    public ArrayList<String> getNeighborList() {
        return this.neighbors;
    }

    public void setOffCardRoles(ArrayList<Integer> roles) {
        this.offCardRoles = roles;
    }

    public ArrayList<Integer> getOffCardRolesCopy() {
        return this.offCardRolesCopy;
    }

    public void setOffCardRolesCopy(ArrayList<Integer> roles) {
        this.offCardRolesCopy = roles;
    }

    public ArrayList<Integer> getOffCardRolesList() {
        return this.offCardRoles;
    }

    public void setPartNameList(ArrayList<String> parts) {
        this.partNameList = parts;
    }

    public ArrayList<String> getPartNameList() {
        return this.partNameList;
    }

    public void setPartNameListCopy(ArrayList<String> parts) {
        this.partNameListCopy = parts;
    }

    public ArrayList<String> getPartNameListCopy() {
        return this.partNameListCopy;
    }

    public ArrayList<Integer> getOffCardWorkers() {
        return this.offCardWorkers;
    }

    public void addOffCardWorker(int ID) {
        this.offCardWorkers.add(ID);
    }

    public void resetWorkerList() {
        for (int i = 0; i < this.offCardWorkers.size(); i++) {
            this.offCardWorkers.remove(i);
        }
    }

    public int getShotCounters() {
        return this.shotCounters;
    }

    public void removeShotCounter() {
        this.shotCounters--;
    }

    public void setShotCounters(ArrayList<String> countersList) {
        int maxCounter = 0;
        for (int i = 0; i < countersList.size(); i++) {
            if (Integer.parseInt(countersList.get(i)) > maxCounter) {
                maxCounter = Integer.parseInt(countersList.get(i));
            }
        }
        this.shotCounters = maxCounter;
    }
        

    public void setLocationsMovieCard(Movie movie) {
        this.movie = movie;
    }

    public Movie getLocationsMovieCard() {
        return this.movie;
    }


}
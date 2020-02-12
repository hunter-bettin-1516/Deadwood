import java.util.*;

public class Location {
    private String locationName;
    private ArrayList<String> neighbors;
    private ArrayList<Integer> offCardRoles; //part levels in integers
    private ArrayList<String> shotCounters;
    private Movie movie;

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

    public ArrayList<Integer> getOffCardRolesList() {
        return this.offCardRoles;
    }

    public void setShotCounters(ArrayList<String> counters) {
        this.shotCounters = counters;
    }

    public ArrayList<String> getShotCounters() {
        return this.shotCounters;
    }

    public void setLocationsMovieCard(Movie movie) {
        this.movie = movie;
    }

    public Movie getLocationsMovieCard() {
        return this.movie;
    }


}
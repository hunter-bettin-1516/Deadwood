import java.util.*;

public class Location {
    private String locationName;
    private ArrayList<String> neighbors;
    private int[] offCardRoles;
    private int[] shotCounters;

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


}
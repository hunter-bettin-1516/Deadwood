public class Location {
    private String locationName;
    private String[] neighbors;
    private int[] offCardRoles;
    private int[] shotCounters;

    //getters and setters for attributes

    public void setLocationName(String name) {
        this.locationName = name;
    }

    public String getLocationName() {
        return this.locationName;
    }


}
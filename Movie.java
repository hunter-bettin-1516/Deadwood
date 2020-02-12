import java.util.*;
public class Movie extends Location {
    private boolean movieIsAWrap = false;
    private int movieBudget;
    private String movieTitle;
    private String[] movieRolesName;
    private ArrayList<String> partNameList;
    private ArrayList<Integer> onCardroles;

    //getters and setters for these attributes
    public void setMovieBudget(int budget) {
        this.movieBudget = budget;
    }

    public int getMovieBudget() {
        return this.movieBudget;
    }

    public void setMovieTitle(String title) {
        this.movieTitle = title;
    }

    public String getMovieTitle() {
        return this.movieTitle;
    }

    public void setPartNameList(ArrayList<String> parts) {
        this.partNameList = parts;
    }

    public ArrayList<String> getPartNameList() {
        return this.partNameList;
    }
    
    public void setOnCardRolesList(ArrayList<Integer> roles) {
        this.onCardroles = roles;
    }

    public ArrayList<Integer> getOnCardRolesList() {
        return this.onCardroles;
    }

}
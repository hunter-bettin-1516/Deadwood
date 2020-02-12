import java.util.*;
public class Movie extends Location {
    private boolean movieIsAWrap = false;
    private int movieBudget;
    private String movieTitle;
    private String[] movieRolesName;
    private ArrayList<String> partNameList;
    private ArrayList<Integer> onCardroles;
    private boolean usedMovie = false; //flag for duplicate movies when shuffling random movie cards to each location

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

    public void setUsedMovie(boolean t) {
        this.usedMovie = t;
    }

    public boolean getUsedMovie() {
        return this.usedMovie;
    }

}
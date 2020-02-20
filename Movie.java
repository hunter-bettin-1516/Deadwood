import java.util.*;
public class Movie {
    private boolean movieIsAWrap = false;
    private int movieBudget;
    private String movieTitle;
    private ArrayList<String> partNameList = new ArrayList<String>(); //
    private ArrayList<Integer> onCardroles = new ArrayList<Integer>(); // ranks
    private ArrayList<String> partNameListCopy = new ArrayList<String>(); //
    private ArrayList<Integer> onCardrolesCopy = new ArrayList<Integer>();
    private boolean usedMovie = false; //flag for duplicate movies when shuffling random movie cards to each location
    private ArrayList<Integer> onCardWorkers = new ArrayList<Integer>(); //stores the index of the player index that is on this locations OnCard role

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

    public void setPartNameListCopy(ArrayList<String> parts) {
        this.partNameListCopy = parts;
    }

    public ArrayList<String> getPartNameListCopy() {
        return this.partNameListCopy;
    }
    
    public void setOnCardRolesList(ArrayList<Integer> roles) {
        this.onCardroles = roles;
    }

    public ArrayList<Integer> getOnCardRolesList() {
        return this.onCardroles;
    }

    public void setOnCardRolesListCopy(ArrayList<Integer> roles) {
        this.onCardrolesCopy = roles;
    }

    public ArrayList<Integer> getOnCardRolesListCopy() {
        return this.onCardrolesCopy;
    }

    public void setUsedMovie(boolean t) {
        this.usedMovie = t;
    }

    public boolean getUsedMovie() {
        return this.usedMovie;
    }

    public void setMovieIsAWrap(boolean t) {
        this.movieIsAWrap = t;
    }

    public boolean getMovieIsAWrap() {
        return this.movieIsAWrap;
    }

    public ArrayList<Integer> getOnCardWorkers() {
        return this.onCardWorkers;
    }

    public void addOnCardWorker(Integer ID) {
        this.onCardWorkers.add(ID);
    }

    public void resetOnCardWorkerList() {
        for (int i = 0; i < this.onCardWorkers.size(); i++) {
            this.onCardWorkers.remove(i);
        }
    }
}
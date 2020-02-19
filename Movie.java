import java.util.*;
public class Movie {
    private boolean movieIsAWrap = false;
    private int movieBudget;
    private String movieTitle;
    private ArrayList<String> partNameList; //
    private ArrayList<Integer> onCardroles; // ranks
    private boolean usedMovie = false; //flag for duplicate movies when shuffling random movie cards to each location
    private ArrayList<Integer> onCardWorkers; //stores the index of the player index that is on this locations OnCard role

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

    public ArrayList<Integer> getOnCardWorkers() {
        return this.onCardWorkers;
    }

    public void addOnCardWorker(int ID) {
        this.onCardWorkers.add(ID);
    }

    public void resetOnCardWorkerList() {
        for (int i = 0; i < this.onCardWorkers.size(); i++) {
            this.onCardWorkers.remove(i);
        }
    }
}
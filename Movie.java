import java.util.*;
public class Movie extends Location {
    private boolean movieIsAWrap = false;
    private int movieBudget;
    private String movieTitle;
    private String[] movieRolesName;
    private ArrayList<String> movie;
    private ArrayList<Integer> role;

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


    public void setMovie(ArrayList<String> movie) {
        this.movie = movie;
    }

    public ArrayList<String> getMovieList() {
        return this.movie;
    }
    
    public void setRole(ArrayList<Integer> role) {
        this.role = role;
    }

    public ArrayList<Integer> getRoleList() {
        return this.role;
    }

}
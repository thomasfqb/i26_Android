package fr.utt.if26.project.if26_android.Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MovieResult {

    private ArrayList<Movie> movies = new ArrayList<>();
    private int page;
    private int totalResults;
    private int totalPages;



    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void emptyMoviesList () {
        movies.clear();
    }

    public MovieResult() {
        this.movies = new ArrayList<>();
    }

    public MovieResult(JSONObject jsonResult) throws JSONException {
        JSONArray JSONMovies = jsonResult.getJSONArray("results");

        for(int i=0; i < JSONMovies.length(); i++){
            JSONObject JSONMovie = (JSONObject) JSONMovies.get(i);
            movies.add(new Movie(JSONMovie));
        }

        this.page = (int) jsonResult.get("page");
        this.totalResults = (int) jsonResult.get("total_results");
        this.totalPages = (int) jsonResult.get("total_pages");

    }


}

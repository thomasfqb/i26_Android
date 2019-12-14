package fr.utt.if26.project.if26_android.Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MovieResult {

    public ArrayList<Movie> movies = new ArrayList<>();
    public int page;
    public int totalResults;
    public int totalPages;

    // Mdr t'es une chevre thomas
    private void constructor() {}

    public MovieResult(JSONObject jsonResult) throws JSONException {
        JSONArray JSONMovies = jsonResult.getJSONArray("results");

        for(int i=0; i < JSONMovies.length(); i++){
            JSONObject JSONMovie = (JSONObject) JSONMovies.get(i);
            movies.add(new Movie(JSONMovie));
        }

        page = (int) jsonResult.get("page");
        totalResults = (int) jsonResult.get("total_results");
        totalPages = (int) jsonResult.get("total_pages");

    }


}

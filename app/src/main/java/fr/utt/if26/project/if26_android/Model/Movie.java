package fr.utt.if26.project.if26_android.Model;

import org.json.JSONException;
import org.json.JSONObject;

public class Movie {

    public int id;
    public String originalTitle;
    public String posterPath;
    public Number voteAverage;
    public String overview;
    public String releaseDate;
    public Video[] videos;


    public Movie(JSONObject jsonMovie) throws JSONException {
        id = (int) jsonMovie.get("id");
        originalTitle = (String) jsonMovie.get("original_title");
        posterPath = !jsonMovie.getString("poster_path").equals("null") ? (String) jsonMovie.get("poster_path") : "";
        voteAverage = ((Number) jsonMovie.get("vote_average")).doubleValue();
        overview = (String) jsonMovie.get("overview");
        releaseDate = (String) jsonMovie.get("release_date");
    }

}

package fr.utt.if26.project.if26_android.Model;

import org.json.JSONException;
import org.json.JSONObject;

public class Movie {

    public int id;
    public String originalTitle;
    public String posterPath;
    public Double voteAverage;
    public String overview;
    public String releaseDate;
    public Video[] videos;

    private void constructor() {}

    public Movie(JSONObject jsonMovie) throws JSONException {
        id = (int) jsonMovie.get("id");
        originalTitle = (String) jsonMovie.get("original_title");
        posterPath = (String) jsonMovie.get("poster_path");
        //voteAverage = (Double) jsonMovie.get("vote_average");
        overview = (String) jsonMovie.get("overview");
        releaseDate = (String) jsonMovie.get("release_date");
    }

}

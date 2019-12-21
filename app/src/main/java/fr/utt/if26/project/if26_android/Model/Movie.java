package fr.utt.if26.project.if26_android.Model;

import org.json.JSONException;
import org.json.JSONObject;

public class Movie {

    private int id;
    private String originalTitle;
    private String posterPath;
    private Number voteAverage;
    private String overview;
    private String releaseDate;
    private Video[] videos;

    public Movie(int id, String originalTitle, String posterPath, Number voteAverage, String overview, String releaseDate, Video[] videos) {
        this.id = id;
        this.originalTitle = originalTitle;
        this.posterPath = posterPath;
        this.voteAverage = voteAverage;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.videos = videos;
    }

    public Movie(JSONObject jsonMovie) throws JSONException {
        this.id = (int) jsonMovie.get("id");
        this.originalTitle = (String) jsonMovie.get("original_title");
        this.posterPath = !jsonMovie.getString("poster_path").equals("null") ? (String) jsonMovie.get("poster_path") : "";
        this.voteAverage = ((Number) jsonMovie.get("vote_average")).doubleValue();
        this.overview = (String) jsonMovie.get("overview");
        this.releaseDate = !jsonMovie.getString("release_date").equals("null") ? (String) jsonMovie.get("release_date") : "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public Number getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Number voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Video[] getVideos() {
        return videos;
    }

    public void setVideos(Video[] videos) {
        this.videos = videos;
    }
}

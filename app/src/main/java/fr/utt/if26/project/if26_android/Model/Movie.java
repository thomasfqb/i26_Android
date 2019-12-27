package fr.utt.if26.project.if26_android.Model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.json.JSONException;
import org.json.JSONObject;

@Entity (tableName = "movie_table")
public class Movie implements Parcelable {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "original_title")
    private String originalTitle;

    @ColumnInfo(name = "poster_path")
    private String posterPath;

    @ColumnInfo(name = "vote_average")
    private Double voteAverage;

    @ColumnInfo(name = "overview")
    private String overview;

    @ColumnInfo(name = "release_date")
    private String releaseDate;

   /*@ColumnInfo(name = "video")
    private Video[] videos;*/

    public Movie(JSONObject jsonMovie) throws JSONException {
        this.id = (int) jsonMovie.get("id");
        this.originalTitle = (String) jsonMovie.get("original_title");
        this.posterPath = !jsonMovie.getString("poster_path").equals("null") ? (String) jsonMovie.get("poster_path") : "";
        this.voteAverage = ((Number) jsonMovie.get("vote_average")).doubleValue();
        this.overview = (String) jsonMovie.get("overview");
        this.releaseDate = !jsonMovie.getString("release_date").equals("null") ? (String) jsonMovie.get("release_date") : "";
    }

    public Movie (String testDatabase) {
        this.id = 1;
        this.originalTitle = testDatabase;
        this.posterPath = testDatabase;
       //this.voteAverage = testDatabase;
        this.overview = testDatabase;
        this.releaseDate = testDatabase;
    }

    public Movie(String originalTitle, String posterPath, Double voteAverage, String overview, String releaseDate) {
        this.originalTitle = originalTitle;
        this.posterPath = posterPath;
        this.voteAverage = voteAverage;
        this.overview = overview;
        this.releaseDate = releaseDate;
        //this.videos = videos;
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

    public String getPosterPath() {
        return posterPath;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(originalTitle);
        dest.writeString(posterPath);

        dest.writeDouble(voteAverage);
        dest.writeString(overview);
        dest.writeString(releaseDate);
        //dest.writeString(video);

    }

    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };


    // example constructor that takes a Parcel and gives you an object populated with it's values
    /*Observe that in the case you have more than one field to retrieve from a given Parcel, you must do this in the same order you put them in (that is, in a FIFO approach).*/
    private Movie(Parcel in) {
        id = in.readInt();
        originalTitle = in.readString();
        posterPath = in.readString();
        voteAverage = in.readDouble();
        overview = in.readString();
        releaseDate = in.readString();
    }

    /*  public Video[] getVideos() {
        return videos;
    }

    public void setVideos(Video[] videos) {
        this.videos = videos;
    }*/
}

package fr.utt.if26.project.if26_android.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import fr.utt.if26.project.if26_android.Model.Movie;

@Dao
public interface MovieDao {

    // allowing the insert of the same movie multiple times by passing a
    // conflict resolution strategy
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Movie movie);

    @Query("DELETE FROM movie_table")
    void deleteAll();

    @Delete
    void delete(Movie movie);

    @Query("SELECT * from movie_table")
    LiveData<List<Movie>> getAllMovies();
}

package fr.utt.if26.project.if26_android.Model;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import fr.utt.if26.project.if26_android.Database.MovieDao;
import fr.utt.if26.project.if26_android.Database.MovieRoomDatabase;

public class MovieRepository {
    private MovieDao mMovieDao;
    private LiveData<List<Movie>> mAllMovies;

    MovieRepository(Application application) {
        MovieRoomDatabase db = MovieRoomDatabase.getDatabase(application);
        mMovieDao = db.movieDao();
        mAllMovies = mMovieDao.getAllMovies();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<Movie>> getAllMovies() {
        return mAllMovies;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insert(Movie movie) {
        MovieRoomDatabase.databaseWriteExecutor.execute(() -> {
            mMovieDao.insert(movie);
        });
    }
    void delete(Movie movie) {
        MovieRoomDatabase.databaseWriteExecutor.execute(() -> {
            mMovieDao.delete(movie);
        });
    }
}

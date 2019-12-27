package fr.utt.if26.project.if26_android.Model;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MovieViewModel extends AndroidViewModel {

    private MovieRepository mRepository;

    private LiveData<List<Movie>> mAllMovies;

    private List<Movie> mAllTypeMovies;

    public MovieViewModel (Application application) {
        super(application);
        mRepository = new MovieRepository(application);
        mAllMovies = mRepository.getAllMovies();
        mAllTypeMovies = mRepository.getAllTypeMovies();
    }

    public LiveData<List<Movie>> getAllMovies() { return mAllMovies; }

    public void insert(Movie movie) { mRepository.insert(movie); }

    public void delete(Movie movie) { mRepository.delete(movie); }

}

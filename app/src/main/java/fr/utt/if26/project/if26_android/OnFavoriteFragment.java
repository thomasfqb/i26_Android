package fr.utt.if26.project.if26_android;

import android.os.Build;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import java.util.List;

import fr.utt.if26.project.if26_android.Model.Movie;
import fr.utt.if26.project.if26_android.Model.MovieViewModel;

public class OnFavoriteFragment extends UserInteraction {

    private FavoriteFragment favoriteFragment;
    private MovieViewModel mMovieViewModel;

    OnFavoriteFragment(FavoriteFragment favoriteFragment) {
        this.favoriteFragment = favoriteFragment;
    }

    @Override
    void fetchMovies() {
        mMovieViewModel = ViewModelProviders.of(favoriteFragment.getActivity(), new ViewModelProvider.Factory() {
            @SuppressWarnings("unchecked") @Override public <T extends ViewModel> T create(final Class<T> modelClass) {
                if (modelClass.equals(MovieViewModel.class)) {
                    return (T) new MovieViewModel(favoriteFragment.getActivity().getApplication());
                } else {
                    return null;
                }
            }
        }).get(MovieViewModel.class);

        mMovieViewModel.getAllMovies().observe(favoriteFragment.getActivity(), new Observer<List<Movie>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onChanged(@Nullable final List<Movie> movies) {
               favoriteFragment.setmMovieResult(movies);
               favoriteFragment.initRecyclerView();
            }
        });
    }

    @Override
    void fetchMoreMovies() {

    }
}

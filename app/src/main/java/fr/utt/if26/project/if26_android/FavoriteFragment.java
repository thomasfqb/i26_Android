package fr.utt.if26.project.if26_android;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import fr.utt.if26.project.if26_android.Model.MovieResult;

public class FavoriteFragment extends Fragment {

    private static final String TAG = "HomeFragment";
    private MovieResult mMovieResult;
    private View view;
    private RecyclerViewAdapter adapter;
    private UserInteraction userInteraction = new OnFavoriteFragment(this);


    public MovieResult getmMovieResult() {
        return mMovieResult;
    }

    public RecyclerViewAdapter getAdapter() {
        return adapter;
    }

    public void setmMovieResult(MovieResult mMovieResult) {
        this.mMovieResult = mMovieResult;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        userInteraction.fetchMovies();
        return view;
    }


}

package fr.utt.if26.project.if26_android;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.utt.if26.project.if26_android.Model.Movie;
import fr.utt.if26.project.if26_android.Model.MovieResult;

public class FavoriteFragment extends Fragment {

    private static final String TAG = "HomeFragment";
    private List<Movie> mMovies;
    private View view;
    private RecyclerViewAdapter adapter;
    private UserInteraction userInteraction = new OnFavoriteFragment(this);

    void setmMovieResult(List<Movie> mMovieResult) {
        this.mMovies = mMovieResult;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        userInteraction.fetchMovies();
        return view;
    }

    public void initRecyclerView () {
        RecyclerView recyclerView = view.findViewById(R.id.recyvlerview_home);
        adapter = new RecyclerViewAdapter(view.getContext(), userInteraction, mMovies);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
    }


}

package fr.utt.if26.project.if26_android;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import fr.utt.if26.project.if26_android.Model.MovieResult;

public class HomeFragment extends Fragment{

    private static final String TAG = "HomeFragment";
    private MovieResult mMovieResult;
    private View view;
    private RecyclerViewAdapter adapter;
    private UserInteraction userInteraction = new OnHomeFragment(this);


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
        Log.d(TAG, "onCreateView: started");
        view = inflater.inflate(R.layout.fragment_home, container, false);
        userInteraction.fetchMovies();
        return view;
    }

    public void initRecyclerView () {
        RecyclerView recyclerView = view.findViewById(R.id.recyvlerview_home);
        adapter = new HomeRecyclerViewAdapter(view.getContext(), userInteraction, mMovieResult);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1)) {
                    fetchMoreMovies();
                }
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
    }


    private void fetchMoreMovies () {
        userInteraction.fetchMoreMovies();
    }
}


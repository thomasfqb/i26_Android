package fr.utt.if26.project.if26_android;

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
import fr.utt.if26.project.if26_android.Services.ResultHandler;
import fr.utt.if26.project.if26_android.Services.Service;

public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";


    private MovieResult mMovieResult;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: started");

        final View view = inflater.inflate(R.layout.fragment_home, container, false);

        // TEST API
        Service.service.fetchUpcomingMovie(getActivity(), 1, new ResultHandler<MovieResult>() {
            @Override
            public void onSuccess(MovieResult result) {
                mMovieResult = result;
                initRecyclerView(view);
            }

            @Override
            public void onFailure(Exception e) {
                Log.d(TAG, "onCallApi: succeed");
                System.out.println("failed to fetch movies:\\n" + e);
            }
        });
        // END TEST API
        return view;
    }


    private void initRecyclerView (final View view) {
        Log.d(TAG, "initRecyclerView: recyclerView");
        RecyclerView recyclerView = view.findViewById(R.id.recyvlerview_home);
        final RecyclerViewAdapter adapter = new HomeRecyclerViewAdapter(view.getContext(), mMovieResult.getMovies());
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1)) {
                    fetchMoreMovies(adapter);
                }
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
    }

    private void fetchMoreMovies (final RecyclerViewAdapter adapter) {
        if (mMovieResult == null) { return; }
        final int page = mMovieResult.getPage() + 1;

        Service.service.fetchUpcomingMovie(getActivity(), page, new ResultHandler<MovieResult>() {
            @Override
            public void onSuccess(MovieResult result) {
                if (!result.getMovies().isEmpty()) {
                    mMovieResult.getMovies().addAll(result.getMovies());
                    mMovieResult.setPage(page);
                    adapter.notifyDataSetChanged();
                }


            }

            @Override
            public void onFailure(Exception e) {
                Log.d(TAG, "onCallApi: succeed");
                System.out.println("failed to fetch movies:\\n" + e);
            }
        });
    }
}


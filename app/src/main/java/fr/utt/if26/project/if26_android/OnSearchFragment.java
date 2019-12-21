package fr.utt.if26.project.if26_android;

import android.os.Build;
import android.util.Log;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import java.util.List;
import java.util.stream.Collectors;

import fr.utt.if26.project.if26_android.Model.Movie;
import fr.utt.if26.project.if26_android.Model.MovieResult;
import fr.utt.if26.project.if26_android.Services.ResultHandler;
import fr.utt.if26.project.if26_android.Services.Service;

import static com.android.volley.VolleyLog.TAG;

public class OnSearchFragment extends UserInteraction {
    
    SearchFragment searchFragment;

    public OnSearchFragment(SearchFragment searchFragment) {
        this.searchFragment = searchFragment;
    }

    @Override
    public void fetchMovies() {
        Service.service.fetchMovies(searchFragment.getActivity(), searchFragment.getQuery(), 1, new ResultHandler<MovieResult>() {
            @Override
            public void onSuccess(MovieResult result) {
                searchFragment.setmMovieResult(result);
                searchFragment.initRecyclerView();
            }

            @Override
            public void onFailure(Exception e) {
                Log.d(TAG, "onCallApi: failed");
                System.out.println("failed to fetch movies:\\n" + e);
            }
        });
    }

    @Override
    public void fetchMoreMovies() {
        if (searchFragment.getmMovieResult() == null) { return; }
        if (searchFragment.getmMovieResult().getTotalPages() == searchFragment.getmMovieResult().getPage()) { return; }
        System.out.println(searchFragment.getmMovieResult().getTotalPages());
        final int page = searchFragment.getmMovieResult().getPage() + 1;
        Service.service.fetchMovies(searchFragment.getActivity(), searchFragment.getQuery(), page, new ResultHandler<MovieResult>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onSuccess(MovieResult result) {

                List<Integer> idTableMovies = searchFragment.getmMovieResult().getMovies().stream()
                        .map(Movie::getId)
                        .collect(Collectors.toList());

                List<Integer> idResultMovies = result.getMovies().stream()
                        .map(Movie::getId)
                        .collect(Collectors.toList());

                if (!idTableMovies.contains(idResultMovies.get(0))) {
                    searchFragment.getmMovieResult().getMovies().addAll(result.getMovies());
                    searchFragment.getmMovieResult().setPage(page);
                    searchFragment.getAdapter().notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Exception e) {
                Log.d(TAG, "onCallApi: failed");
                System.out.println("failed to fetch movies:\\n" + e);
            }
        });
    }
}

package fr.utt.if26.project.if26_android;

import android.util.Log;

import fr.utt.if26.project.if26_android.Model.MovieResult;
import fr.utt.if26.project.if26_android.Services.ResultHandler;
import fr.utt.if26.project.if26_android.Services.Service;

import static com.android.volley.VolleyLog.TAG;


public class OnHomeFragment extends UserInteraction {

    HomeFragment homeFragment;

    public OnHomeFragment(HomeFragment homeFragment) {
        this.homeFragment = homeFragment;
    }

    @Override
    public void fetchMovies() {
        Service.service.fetchUpcomingMovie(homeFragment.getActivity(), 1, new ResultHandler<MovieResult>() {
            @Override
            public void onSuccess(MovieResult result) {
                homeFragment.setmMovieResult(result);
                homeFragment.initRecyclerView();
            }

            @Override
            public void onFailure(Exception e) {
                Log.d(TAG, "onCallApi: succeed");
                System.out.println("failed to fetch movies:\\n" + e);
            }
        });
    }

    @Override
    public void fetchMoreMovies() {
        if (homeFragment.getmMovieResult() == null) { return; }
        final int page = homeFragment.getmMovieResult().getPage() + 1;
        if (homeFragment.getmMovieResult().getTotalPages() == homeFragment.getmMovieResult().getPage()) { return;}
        Service.service.fetchUpcomingMovie(homeFragment.getActivity(), page, new ResultHandler<MovieResult>() {
            @Override
            public void onSuccess(MovieResult result) {
                homeFragment.getmMovieResult().getMovies().addAll(result.getMovies());
                homeFragment.getmMovieResult().setPage(page);
                homeFragment.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onFailure(Exception e) {
                Log.d(TAG, "onCallApi: succeed");
                System.out.println("failed to fetch movies:\\n" + e);
            }
        });
    }
}

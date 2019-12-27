package fr.utt.if26.project.if26_android;

import android.content.Intent;
import android.view.View;

import java.util.List;

import fr.utt.if26.project.if26_android.Model.Movie;

abstract class UserInteraction {

    void recyclerViewListClicked(View view, int position, List<Movie> mMovieResult) {
        Movie movie = mMovieResult.get(position);
        System.out.println(movie.getOriginalTitle());
        Intent intent = new Intent(view.getContext(), MovieDetailsActivity.class);
        intent.putExtra("movie", movie);
        view.getContext().startActivity(intent);
    }

    abstract void fetchMovies();
    abstract void fetchMoreMovies();
}

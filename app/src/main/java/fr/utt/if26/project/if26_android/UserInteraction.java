package fr.utt.if26.project.if26_android;

import android.content.Intent;
import android.view.View;

import fr.utt.if26.project.if26_android.Model.Movie;
import fr.utt.if26.project.if26_android.Model.MovieResult;

public abstract class UserInteraction {

    public void recyclerViewListClicked(View view, int position, MovieResult mMovieResult) {
        Movie movie = mMovieResult.getMovies().get(position);
        System.out.println(movie.getOriginalTitle());
        Intent intent = new Intent(view.getContext(), MovieDetailsActivity.class);

        intent.putExtra("movie", movie);

        view.getContext().startActivity(intent);
    }

    abstract void fetchMovies();
    abstract void fetchMoreMovies();
}

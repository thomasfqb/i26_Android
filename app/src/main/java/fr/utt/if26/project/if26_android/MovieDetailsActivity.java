package fr.utt.if26.project.if26_android;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import fr.utt.if26.project.if26_android.Model.Movie;
import fr.utt.if26.project.if26_android.Model.MovieViewModel;
import fr.utt.if26.project.if26_android.Services.Service;

public class MovieDetailsActivity extends AppCompatActivity{

    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";
    private MovieViewModel mMovieViewModel;
    Movie movie;
    ImageView movieImage;
    ImageView addTofavorite;
    TextView titleMovieDetails;
    TextView releaseDate;
    TextView rateAverage;
    TextView movieDescription;
    boolean isFavorite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        movieImage = findViewById(R.id.movie_image_movie_detail);
        addTofavorite = findViewById(R.id.add_to_favorite);
        releaseDate = findViewById(R.id.release_date_movie_details);
        rateAverage = findViewById(R.id.note_average_movie_details);
        titleMovieDetails = findViewById(R.id.title_movie_details);
        movieDescription = findViewById(R.id.movie_description_movie_details);

        Intent intent = getIntent();

        movie = intent.getParcelableExtra("movie");

        mMovieViewModel = ViewModelProviders.of(this, new ViewModelProvider.Factory() {
            @SuppressWarnings("unchecked") @Override public <T extends ViewModel> T create(final Class<T> modelClass) {
                if (modelClass.equals(MovieViewModel.class)) {
                    return (T) new MovieViewModel(getApplication());
                } else {
                    return null;
                }
            }
        }).get(MovieViewModel.class);

        mMovieViewModel.getAllMovies().observe(this, new Observer<List<Movie>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onChanged(@Nullable final List<Movie> movies) {

                ArrayList<Integer> idList = (ArrayList<Integer>) movies.stream()
                        .map(Movie::getId)
                        .collect(Collectors.toList());

                isFavorite = idList.contains(movie.getId());
                if (isFavorite) {
                    addTofavorite.setImageResource(R.drawable.ic_favorite_white_24dp);
                } else {
                    addTofavorite.setImageResource(R.drawable.ic_favorite_border_white_24dp);
                }
            }
        });

        Glide.with(this)
                .asBitmap()
                .load(Service.service.imageBaseUrl + movie.getPosterPath())
                .into(movieImage);

        titleMovieDetails.setText(movie.getOriginalTitle());
        releaseDate.setText(movie.getReleaseDate());
        rateAverage.setText(movie.getVoteAverage().toString());
        movieDescription.setText(movie.getOverview());
    }

    public void addToFavorite(View view) {
        isFavorite = !isFavorite;
        if (isFavorite) {
            addTofavorite.setImageResource(R.drawable.ic_favorite_white_24dp);
            mMovieViewModel.insert(movie);
        } else {
            addTofavorite.setImageResource(R.drawable.ic_favorite_border_white_24dp);
            mMovieViewModel.delete(movie);
        }
    }
}

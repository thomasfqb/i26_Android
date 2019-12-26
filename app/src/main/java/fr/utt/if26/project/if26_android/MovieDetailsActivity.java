package fr.utt.if26.project.if26_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import fr.utt.if26.project.if26_android.Model.Movie;
import fr.utt.if26.project.if26_android.Services.Service;

public class MovieDetailsActivity extends AppCompatActivity{

    Movie movie;
    ImageView movieImage;
    ImageView addTofavorite;
    TextView titleMovieDetails;
    TextView releaseDate;
    TextView rateAverage;
    TextView movieDescription;


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

        movie = new Movie( intent.getStringExtra("originalTitle"),
                intent.getStringExtra("posterPath"),
                intent.getDoubleExtra("voteAverage", 0),
                intent.getStringExtra("overview"),
                intent.getStringExtra("releaseDate"));
                //intent.getStringExtra("videos"));

        Glide.with(this)
                .asBitmap()
                .load(Service.service.imageBaseUrl + movie.getPosterPath())
                .into(movieImage);

        titleMovieDetails.setText(movie.getOriginalTitle());

        releaseDate.setText(movie.getReleaseDate());
        rateAverage.setText(movie.getVoteAverage().toString());
        movieDescription.setText(movie.getOverview());
    }
}

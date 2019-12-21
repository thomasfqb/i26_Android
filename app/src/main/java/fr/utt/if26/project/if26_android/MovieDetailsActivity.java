package fr.utt.if26.project.if26_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import fr.utt.if26.project.if26_android.Model.Movie;

public class MovieDetailsActivity extends AppCompatActivity{

    Movie movie;
    TextView titleMovieDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        titleMovieDetails = findViewById(R.id.title_movie_details);

        //RECEIVE DATA VIA INTENT
        Intent i = getIntent();
        String title = i.getStringExtra("originalTitle");

        //SET DATA TO TEXTVIEWS
        titleMovieDetails.setText(title);
    }
}

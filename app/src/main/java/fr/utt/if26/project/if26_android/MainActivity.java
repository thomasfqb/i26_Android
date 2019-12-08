package fr.utt.if26.project.if26_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import fr.utt.if26.project.if26_android.Model.Movie;
import fr.utt.if26.project.if26_android.Model.MovieResult;
import fr.utt.if26.project.if26_android.Services.ResultHandler;
import fr.utt.if26.project.if26_android.Services.Service;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemReselectedListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();


        // TEST API
        Service.service.fetchUpcomingMovie(this, 1, new ResultHandler<MovieResult>() {
            @Override
            public void onSuccess(MovieResult result) {
                for (Movie movie : result.movies) {
                    System.out.println(movie.originalTitle);
                }
            }

            @Override
            public void onFailure(Exception e) {
                System.out.println("failed to fetch movies:\\n" + e);
            }
        });
        // END TEST API
    }


    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemReselectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;

                    switch (menuItem.getItemId()) {
                        case R.id.nav_home:
                            selectedFragment = new HomeFragment();
                            break;
                        case R.id.nav_favorites:
                            selectedFragment = new FavoriteFragment();
                            break;
                        case R.id.nav_search:
                            selectedFragment = new SearchFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                    return true;
                }
    };

}

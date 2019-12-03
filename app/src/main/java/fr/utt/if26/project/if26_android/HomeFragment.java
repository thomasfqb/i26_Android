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

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";
    //vars
    private ArrayList<String> mMovieImageDescription = new ArrayList<>();
    private ArrayList<String> mMovieImageUrls = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: started");

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        this.initBitmapsImage(view);

        return view;
    }

    private void initBitmapsImage (View view) {
        Log.d(TAG, "initBitmapsImage: preparing bitmaps");

        mMovieImageUrls.add("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg");
        mMovieImageDescription.add("Havasu Falls");

        mMovieImageUrls.add("https://i.redd.it/tpsnoz5bzo501.jpg");
        mMovieImageDescription.add("Trondheim");

        mMovieImageUrls.add("https://i.redd.it/qn7f9oqu7o501.jpg");
        mMovieImageDescription.add("Portugal");

        mMovieImageUrls.add("https://i.redd.it/j6myfqglup501.jpg");
        mMovieImageDescription.add("Rocky Mountain National Park");


        mMovieImageUrls.add("https://i.redd.it/0h2gm1ix6p501.jpg");
        mMovieImageDescription.add("Mahahual");

        mMovieImageUrls.add("https://i.redd.it/k98uzl68eh501.jpg");
        mMovieImageDescription.add("Frozen Lake");


        mMovieImageUrls.add("https://i.redd.it/glin0nwndo501.jpg");
        mMovieImageDescription.add("White Sands Desert");

        mMovieImageUrls.add("https://i.redd.it/obx4zydshg601.jpg");
        mMovieImageDescription.add("Austrailia");

        mMovieImageUrls.add("https://i.imgur.com/ZcLLrkY.jpg");
        mMovieImageDescription.add("Washington");

        initRecyclerView(view);
    }

    private void initRecyclerView (View view) {
        Log.d(TAG, "initRecyclerView: recyclerView");
        RecyclerView recyclerView = view.findViewById(R.id.recyvlerview);
        HomeRecyclerViewAdapter adapter = new HomeRecyclerViewAdapter(view.getContext(), mMovieImageDescription, mMovieImageUrls);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
    }
}

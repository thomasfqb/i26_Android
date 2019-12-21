package fr.utt.if26.project.if26_android;

import android.content.Context;
import java.util.ArrayList;

import fr.utt.if26.project.if26_android.Model.Movie;
import fr.utt.if26.project.if26_android.Model.MovieResult;


public class HomeRecyclerViewAdapter extends RecyclerViewAdapter {
    public HomeRecyclerViewAdapter(Context mContext, UserInteraction clickListener, MovieResult movies) {
        super(mContext,clickListener, movies);
    }
}

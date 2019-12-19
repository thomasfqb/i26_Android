package fr.utt.if26.project.if26_android;

import android.content.Context;
import java.util.ArrayList;

import fr.utt.if26.project.if26_android.Model.Movie;



public class HomeRecyclerViewAdapter extends RecyclerViewAdapter {
    public HomeRecyclerViewAdapter(Context mContext, RecyclerViewClickListener clickListener, ArrayList<Movie> movies) {
        super(mContext,clickListener, movies);
    }
}

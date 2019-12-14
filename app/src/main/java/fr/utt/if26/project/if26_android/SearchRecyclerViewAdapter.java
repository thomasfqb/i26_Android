package fr.utt.if26.project.if26_android;

import android.content.Context;

import java.util.ArrayList;

import fr.utt.if26.project.if26_android.Model.Movie;

public class SearchRecyclerViewAdapter extends RecyclerViewAdapter {
    public SearchRecyclerViewAdapter(Context mContext, ArrayList<Movie> movies) {
        super(mContext, movies);
    }
}

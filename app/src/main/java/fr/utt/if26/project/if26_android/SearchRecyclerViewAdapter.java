package fr.utt.if26.project.if26_android;

import android.content.Context;

import fr.utt.if26.project.if26_android.Model.MovieResult;

public class SearchRecyclerViewAdapter extends RecyclerViewAdapter {
    public SearchRecyclerViewAdapter(Context mContext, UserInteraction clickListener, MovieResult movies) {
        super(mContext,clickListener, movies);
    }
}

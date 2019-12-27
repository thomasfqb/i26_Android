package fr.utt.if26.project.if26_android;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import fr.utt.if26.project.if26_android.Model.Movie;
import fr.utt.if26.project.if26_android.Services.Service;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";

    private Context mContext;
    private UserInteraction userInteraction;
    private List<Movie> mMovies;



    public RecyclerViewAdapter(Context mContext, UserInteraction userInteraction, List<Movie> movies) {
        this.mMovies  = movies;
        this.mContext = mContext;
        this.userInteraction = userInteraction;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_item, parent, false);
        RecyclerViewAdapter.ViewHolder viewHolder = new RecyclerViewAdapter.ViewHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, final int position) {
        Log.i(TAG, "onBindViewHolder: called.");

        Glide.with(mContext)
                .asBitmap()
                .load(Service.service.imageBaseUrl + mMovies.get(position).getPosterPath())
                .into(holder.movieImage);

        holder.movieTitle.setText(mMovies.get(position).getOriginalTitle());
        holder.movieReleaseDate.setText(mMovies.get(position).getReleaseDate());
        holder.movieAverageRating.setText(mMovies.get(position).getVoteAverage().toString());
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView movieImage;
        TextView movieTitle;
        TextView movieReleaseDate;
        TextView movieAverageRating;

        RelativeLayout parentLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            movieImage = itemView.findViewById(R.id.movie_image);
            movieTitle = itemView.findViewById(R.id.movie_title);
            parentLayout = itemView.findViewById(R.id.parent_layout);
            movieReleaseDate = itemView.findViewById(R.id.movie_date_release);
            movieAverageRating = itemView.findViewById(R.id.movie_average_rating);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            userInteraction.recyclerViewListClicked(v, this.getLayoutPosition(), mMovies);
        }
    }
}


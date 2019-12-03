package fr.utt.if26.project.if26_android;

import android.content.Context;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<HomeRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "HomeRecyclerViewAdapter";

    private Context mContext;
    private ArrayList<String> mMovieImageDescriptions = new ArrayList<>();
    private ArrayList<String> mMovieImage = new ArrayList<>();

    public HomeRecyclerViewAdapter(Context mContext, ArrayList<String> mMovieImageDescriptions, ArrayList<String> mMovieImage) {
        this.mMovieImageDescriptions = mMovieImageDescriptions;
        this.mMovieImage = mMovieImage;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.i(TAG, "onBindViewHolder: called.");
        Glide.with(mContext)
                .asBitmap()
                .load(mMovieImage.get(position))
                .into(holder.movieImage);

        holder.movieImageDescription.setText(mMovieImageDescriptions.get(position));
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick: clicked on: " + mMovieImage.get(position));
                Toast.makeText(mContext, mMovieImageDescriptions.get(position), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMovieImage.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView movieImage;
        TextView movieImageDescription;
        RelativeLayout parentLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            movieImage = itemView.findViewById(R.id.movie_image);
            movieImageDescription = itemView.findViewById(R.id.movie_image_description);
            parentLayout = itemView.findViewById(R.id.parent_layout);


        }
    }
}

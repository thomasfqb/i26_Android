package fr.utt.if26.project.if26_android;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.appbar.AppBarLayout;
import java.util.Locale;
import java.util.Objects;

import fr.utt.if26.project.if26_android.Model.MovieResult;
import fr.utt.if26.project.if26_android.Services.ResultHandler;
import fr.utt.if26.project.if26_android.Services.Service;

public class SearchFragment extends Fragment {

    private static final String TAG = "SearchFragment";
    private static final int STANDARD_APPBAR = 0;
    private static final int SEARCH_APPBAR = 1;
    private int mAppBarState;

    private EditText mSearchMovies;

    private MovieResult mMovieResult = new MovieResult();

    private AppBarLayout viewMoviesBar, searchBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_search, container, false);

        mSearchMovies = view.findViewById(R.id.search_movie_query);
        viewMoviesBar = view.findViewById(R.id.view_movies_toolbar);
        searchBar = view.findViewById(R.id.searchToolbar);
        final TextView searchTextView = view.findViewById(R.id.tv_search_text);
        Log.d(TAG, "onCreateView: started");

        setAppBaeState(STANDARD_APPBAR);

        RelativeLayout searchLayoutClickable = view.findViewById(R.id.search_layout_clickable);
        searchLayoutClickable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked text.");
                toggleToolBarState();

            }
        });

        ImageView ivBackArrow = view.findViewById(R.id.return_black_arrow);
        ivBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked back arrow.");
                toggleToolBarState();

            }
        });

        mSearchMovies.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                final String query = mSearchMovies.getText().toString().toLowerCase(Locale.getDefault());
                if (query.length() > 2) {

                    Service.service.fetchMovies(getActivity(), query, 1, new ResultHandler<MovieResult>() {
                        @Override
                        public void onSuccess(MovieResult result) {
                            mMovieResult = result;
                            initRecyclerView(view, query);
                        }

                        @Override
                        public void onFailure(Exception e) {
                            Log.d(TAG, "onCallApi: failed");
                            System.out.println("failed to fetch movies:\\n" + e);
                        }
                    });

                }
            }
            @Override
            public void afterTextChanged(Editable s) {
                String text = mSearchMovies.getText().toString().toLowerCase(Locale.getDefault());
                searchTextView.setText(text);
            }
        });

        return view;
    }

    private void initRecyclerView (final View view, final String query) {
        Log.d(TAG, "initRecyclerView: recyclerView");
        RecyclerView recyclerView = view.findViewById(R.id.recyvlerview_search);
        final RecyclerViewAdapter adapter = new SearchRecyclerViewAdapter(view.getContext(), mMovieResult.getMovies());
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1)) {
                    fetchMoreMovies(adapter, query);
                }
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
    }

    private void fetchMoreMovies (final RecyclerViewAdapter adapter, final String query) {
        if (mMovieResult == null) { return; }

        final int page = mMovieResult.getPage() + 1;
        if (mMovieResult.getTotalPages() >= page) {
            Service.service.fetchMovies(getActivity(), query, page, new ResultHandler<MovieResult>() {
                @Override
                public void onSuccess(MovieResult result) {
                        mMovieResult.getMovies().addAll(result.getMovies());
                        mMovieResult.setPage(page);
                        adapter.notifyDataSetChanged();
                    }

                @Override
                public void onFailure(Exception e) {
                    Log.d(TAG, "onCallApi: failed");
                    System.out.println("failed to fetch more movies:\\n" + e);
                }
            });
        }
    }


    // Initiate toggle (it means when you click the search icon it pops up the editText and clicking the back button goes to the search icon again)
    private void toggleToolBarState() {
        Log.d(TAG, "toggleToolBarState: toggling AppBarState.");
        if (mAppBarState == STANDARD_APPBAR) {
            setAppBaeState(SEARCH_APPBAR);
        } else {
            setAppBaeState(STANDARD_APPBAR);
        }
    }

    // Sets the appbar state for either search mode or standard mode.
    private void setAppBaeState(int state) {
        Log.d(TAG, "setAppBaeState: changing app bar state to: " + state);
        mAppBarState = state;
        if (mAppBarState == STANDARD_APPBAR) {
            searchBar.setVisibility(View.GONE);
            viewMoviesBar.setVisibility(View.VISIBLE);

            View view = getView();
            InputMethodManager im = (InputMethodManager) Objects.requireNonNull(getActivity()).getSystemService(Context.INPUT_METHOD_SERVICE);
            try {
                im.hideSoftInputFromWindow(view.getWindowToken(), 0); // make keyboard hide
            } catch (NullPointerException e) {
                Log.d(TAG, "setAppBaeState: NullPointerException: " + e);
            }
        } else if (mAppBarState == SEARCH_APPBAR) {
            viewMoviesBar.setVisibility(View.GONE);
            searchBar.setVisibility(View.VISIBLE);
            mSearchMovies.requestFocus();
            InputMethodManager im = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            im.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0); // make keyboard popup
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        setAppBaeState(STANDARD_APPBAR);
    }


}

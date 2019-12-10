package fr.utt.if26.project.if26_android.Services;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import fr.utt.if26.project.if26_android.Model.Movie;
import fr.utt.if26.project.if26_android.Model.MovieResult;
import fr.utt.if26.project.if26_android.Model.Video;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Service /*extends AsyncTask<String, Void, ResultHandler>*/ {

    public static Service service = new Service();

    private void constructor() {}

    String apiKey = "6bf06b7a537c129fe359973f4cdc31f5";
    String apiPath = "https://api.themoviedb.org/3";
    public String imageBaseUrl = "https://image.tmdb.org/t/p/w400";


    public void fetchUpcomingMovie(Context context, int page, final ResultHandler<MovieResult> handler) {

        String url = apiPath + "/movie/now_playing?api_key=" + apiKey + "&page=" + page;
        Service.service.fetchGenericMovies(context, url, handler);
    }

    public void fetchVideoForMovie(Context context, Movie movie, final ResultHandler<List<Video>> handler) {
        String urlString = apiPath + "/movie/"+ movie.id +"/videos?api_key=" + apiKey;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, urlString, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            List<Video> videos = new ArrayList<Video>();
                            JSONArray jsonMovies = response.getJSONArray("results");

                            for(int i=0; i < jsonMovies.length(); i++){
                                JSONObject jsonVideo = (JSONObject) jsonMovies.get(i);
                                videos.add(new Video(jsonVideo));
                            }

                            handler.onSuccess(videos);
                        } catch (JSONException e) {
                            handler.onFailure(e);
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        handler.onFailure(error);
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(jsonObjectRequest);
    }

    public void fetchMovies(Context context, String searchTerm, int page, final ResultHandler<MovieResult> handler) {
        String urlString = apiPath + "/search/movie?api_key="+ apiKey+ "&language=en-US&query="+ searchTerm +"&page="+ page +"&include_adult=false";
        Service.service.fetchGenericMovies(context, urlString, handler);
    }

    private void fetchGenericMovies(Context context, String url, final ResultHandler<MovieResult> handler) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            MovieResult result = new MovieResult(response);
                            handler.onSuccess(result);
                        } catch (JSONException e) {
                            handler.onFailure(e);
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        handler.onFailure(error);
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(jsonObjectRequest);
    }

}

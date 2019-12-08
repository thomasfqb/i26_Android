package fr.utt.if26.project.if26_android.Model;

import org.json.JSONException;
import org.json.JSONObject;

public class Video {

    String id;
    String key;

    private void constructor() {};

    public Video(JSONObject jsonVideo) throws JSONException {
        id = (String) jsonVideo.get("id");
        key = (String) jsonVideo.get("key");
    }

}

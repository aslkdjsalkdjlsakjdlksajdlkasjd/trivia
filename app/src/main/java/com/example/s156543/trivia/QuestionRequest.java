package com.example.s156543.trivia;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import com.android.volley.RequestQueue;
import com.android.volley.Response;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;


/**
 * Created by s156543 on 17-3-2018.
 */

public class QuestionRequest implements
        Response.Listener<JSONObject>, Response.ErrorListener{

    Context context;
    Callback callback;

    public QuestionRequest(Context c) {

        context = c;
    }


    @Override
    public void onErrorResponse(VolleyError error) {
        callback.gotQuestionsError(error.getMessage());
    }



    @Override
    public void onResponse(JSONObject response) {

        JSONArray a = null;
        JSONObject o = null;
        try {
            a = response.getJSONArray("questions");
        } catch (JSONException e) {
            e.printStackTrace();
            return;
        }

//        try {
//            o = a.getJSONObject(0);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        ArrayList<Question> questions = new ArrayList<>();
//
//        for( int i = 0; i < a.length(); i++){
//            try {
//                questions.add(new Question(a.getJSONObject(i)));
//            } catch (JSONException e) {
//
//            }
//        }
        callback.gotQuestions(a);
    }


    public interface Callback {
        void gotQuestions(JSONArray questions);
        void gotQuestionsError(String message);

    }

    public void getQuestions(Callback activity){
        callback = activity;
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "http://jservice.io/api/random";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, this, this);
        queue.add(jsonObjectRequest);
    }
}

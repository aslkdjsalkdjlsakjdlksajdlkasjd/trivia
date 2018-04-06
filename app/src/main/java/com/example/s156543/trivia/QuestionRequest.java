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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;


/**
 * Created by s156543 on 17-3-2018.
 */

public class QuestionRequest implements
        Response.Listener<JSONArray>, Response.ErrorListener{

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
    public void onResponse(JSONArray response) {

        JSONObject object;
            try {
                System.out.println("try in onresponse");
                object = response.getJSONObject(0);
                System.out.println(object);


            } catch (JSONException e) {
                e.printStackTrace();
                return;
            }

        callback.gotQuestions(object);
    }


    public interface Callback {
        void gotQuestions(JSONObject questions);
        void gotQuestionsError(String message);

    }

    public void getQuestions(Callback activity){
        callback = activity;
        RequestQueue queue = Volley.newRequestQueue(context);

        String url = "http://jservice.io/api/random";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, this, this);
        queue.add(jsonArrayRequest);
    }
}

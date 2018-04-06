package com.example.s156543.trivia;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Logger;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class HighscoresHelper extends AppCompatActivity implements
        Response.Listener<JSONArray>, Response.ErrorListener {

    Context context;
    QuestionRequest.Callback callback;
    DatabaseReference dbr;

    FirebaseDatabase database = FirebaseDatabase.getInstance();


//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_highscores_helper);
//    }
    //context
    //delegate

    CategoriesRequest();
    postNewHighscore();
    public void getHighscores(final Score newScore){

        // Read from the database
        dbr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                ArrayList<Score> highscores = new ArrayList<>();

                highscores.add(newScore);
                String value = dataSnapshot.getValue(String.class);

                //Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                callback.gotHighscoresError(error.getMessage());
            }
        });

    }


    public HighscoresHelper(Context c, DatabaseReference dbref, Callback activity){
        context = c;
        callback = c;
        dbr = dbref;


    }



    @Override
    public void onErrorResponse(VolleyError error) {
        callback.gotQuestionsError(error.getMessage());
    }

    @Override
    public void onResponse(JSONArray response) {

    }

    public interface Callback {
        void gotHighscores(ArrayList<Score> Highscores);
        void gotHighscoresError(String message);

    }
}

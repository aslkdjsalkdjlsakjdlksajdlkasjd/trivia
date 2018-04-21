package com.example.s156543.trivia;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;

import java.util.ArrayList;

// Connects to Firebase, and posts and retrieves the data from there
public class HighscoresHelper extends AppCompatActivity implements
        Response.Listener<JSONArray>, Response.ErrorListener {

    Context context;
    Callback callback;
    int value;
    String name;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference dbr = database.getReference("scores");

    public HighscoresHelper(Context c, Callback activity,
                            String name, int value){
        context = c;
        callback = activity;
        this.value = value;
        this.name = name;
        getHighscores();
        postNewHighscore(name, value);
    }

    // Saves the incoming score to the database
    public void postNewHighscore(String name, int score){

        if (name != null) {
            DatabaseReference childRef = dbr.child(name);
            childRef.setValue(score);
        }
    }

    // Retrieve all score data ordered by value
    public void getHighscores(){

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("scores");

        ref.orderByValue().addListenerForSingleValueEvent(
                new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // Retrieves all children under "scores",
                // then iterates over them to retrieve their scores
                ArrayList<Score> highscores = new ArrayList<>();
                Iterable<DataSnapshot> users = dataSnapshot.getChildren();

                for(DataSnapshot u : users){
                    String name = u.getKey();
                    int scor = dataSnapshot.child(name).getValue(int.class);
                    Score newScore = new Score(name, scor);
                    highscores.add(newScore);
                }

                callback.gotHighscores(highscores);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                callback.gotHighscoresError(error.getMessage());
            }
        });
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        callback.gotHighscoresError(error.getMessage());
    }

    @Override
    public void onResponse(JSONArray response) {
    }

    public interface Callback {
        void gotHighscores(ArrayList<Score> Highscores);
        void gotHighscoresError(String message);

    }
}

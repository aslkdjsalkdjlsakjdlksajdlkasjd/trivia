package com.example.s156543.trivia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

// This acitivity sends the data from the previous activity to the firebase database,
// and displays the list of scores sorted from highest to lowest.
public class HighscoreActivity extends AppCompatActivity implements HighscoresHelper.Callback{

    String name = null;
    int value = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Retrieve name, and score from the previous correct answer
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            name = extras.getString("name");
            value = extras.getInt("value");
        }

        HighscoresHelper help = new HighscoresHelper(this, this, name, value);
    }


    @Override
    public void gotHighscores(ArrayList<Score> highscores) {

        ArrayAdapter<Score> highscoreAdapter =
                new HighscoresAdapter(this, R.layout.highscore_item, highscores);
        ListView lv = findViewById(R.id.highscoreList);
        lv.setAdapter(highscoreAdapter);
    }

    @Override
    public void gotHighscoresError(String message) {

        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(this, message, duration);
        toast.show();
    }
}

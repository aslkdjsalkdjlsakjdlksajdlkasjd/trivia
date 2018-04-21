package com.example.s156543.trivia;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

/**
 * Created by s156543 on 5-4-2018.
 */

// Adapter for displaying the users and their highscores in a list
public class HighscoresAdapter extends ArrayAdapter<Score> {

    ArrayList<Score> scoreList;


    public View getView(int position, View convertView,
                        ViewGroup parent){

        convertView = LayoutInflater.from(getContext()).inflate(R.layout.highscore_item, parent, false);

        TextView nameView = convertView.findViewById(R.id.name);
        TextView scoreView = convertView.findViewById(R.id.score);

        Score scoreObject = scoreList.get(position);
        String currentName = scoreObject.getUser();
        String currentScore = Objects.toString(scoreObject.getScore());

        nameView.setText(currentName);
        scoreView.setText(currentScore);

        return convertView;
    }

    public HighscoresAdapter(@NonNull Context context, int resource,
                             ArrayList<Score> highscores) {
        super(context, resource, highscores);
        Collections.reverse(highscores);
        scoreList = highscores;
    }
}

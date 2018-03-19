
package com.example.s156543.trivia;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class AnswerActivity extends AppCompatActivity implements QuestionRequest.Callback {
    Context context;
    Question q = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ListView QListView = findViewById(R.id.questionList);
        // QListView.setOnItemClickListener(new ListViewClickListener());
        QuestionRequest request = new QuestionRequest(this);
        request.getQuestions(this);
        context = this;

    }


//        }
public void gotQuestions(JSONArray question) {


    try {
       q = new Question(question.getJSONArray(0).getJSONObject(0));
    } catch (JSONException e) {
        e.printStackTrace();
    }

    TextView tv = findViewById(R.id.question);
    tv.setText(q.getQuestion());
}


public void gotQuestionsError(String message) {
    int duration = Toast.LENGTH_LONG;
    Toast toast = Toast.makeText(this, message, duration);
    toast.show();
}

public void writeAnswer(View view){
        TextView answerView = findViewById(R.id.answer);
        String a = Objects.toString(answerView.getText());

        if (a.equals(q.getAnswer())){

        finish();
    }
}


}


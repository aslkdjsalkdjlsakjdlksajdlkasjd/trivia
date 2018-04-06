
package com.example.s156543.trivia;

import android.content.Context;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.TextView;
import android.widget.Toast;

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
        setContentView(R.layout.activity_answer);

        //ListView QListView = findViewById(R.id.questionList);
        // QListView.setOnItemClickListener(new ListViewClickListener());
        QuestionRequest request = new QuestionRequest(this);
        request.getQuestions(this);
        context = this;

    }

public void gotQuestions(JSONObject question) {

    try {

        System.out.println("YO");

       q = new Question(question);
       System.out.println(q);

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


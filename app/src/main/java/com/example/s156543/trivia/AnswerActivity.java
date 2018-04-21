package com.example.s156543.trivia;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.Objects;

// This activity handles the retrieval, display and answering of the trivia questions
// For testing: the answers to the questions are shown in the System.out
public class AnswerActivity extends AppCompatActivity implements QuestionRequest.Callback {
    Context context;
    Question q = null;

    String a;
    String name;
    int v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        QuestionRequest request = new QuestionRequest(this);
        request.getQuestions(this);
        context = this;
    }

    // Checks whether the question is answered correctly (case insensitive)
    // and if so, directs the user to the highscoresactivity
    public void submit(View view) {

        writeAnswer(view);
        String question = q.getAnswer();

        if (a.toLowerCase().equals(question.toLowerCase())){

            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(this, "CORRECT", duration);
            toast.show();

            Intent intent = new Intent(AnswerActivity.this, HighscoreActivity.class);
            Bundle extras = new Bundle();
            extras.putString("name", name);
            extras.putInt("value", v);
            intent.putExtras(extras);
            startActivity(intent);

            finish();
        }

        else {

            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(this, q.getAnswer(), duration);
            toast.show();
            finish();
        }
    }

    // Handle text input
    public void writeAnswer(View view) {

        TextView answerView = findViewById(R.id.answer);
        TextView nameView = findViewById(R.id.name);
        TextView valueView = findViewById(R.id.value);

        a = Objects.toString(answerView.getText());
        v = Integer.parseInt(valueView.getText().toString());
        name = Objects.toString(nameView.getText());
    }

    // Retrieve questions
    public void gotQuestions(JSONObject question) {

        try {
            q = new Question(question);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        TextView qv = findViewById(R.id.question);
        TextView vv = findViewById(R.id.value);
        qv.setText(q.getQuestion());
        vv.setText(Objects.toString(q.getValue()));

        System.out.println("ANSWER TO THIS QUESTION:");
        System.out.println(q.getAnswer());
    }

    // Error Handling
    public void gotQuestionsError(String message) {

        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(this, message, duration);
        toast.show();
    }
}


package com.example.s156543.trivia;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by s156543 on 18-3-2018.
 */

// Class for Question objects
public class Question implements Serializable {

    String answer;
    String question;
    int value;
    long category_id;


    public Question(String answer, String question, int value,
                    long category_id) {
        setAnswer(answer);
        setCategory_id(category_id);
        setQuestion(question);
        setValue(value);
    }

    public Question(JSONObject o) throws JSONException {
        this(o.getString("answer"), o.getString("question"),
                o.getInt("value"), o.getLong("category_id"));
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setCategory_id(long category_id) { this.category_id = category_id; }

    public String getAnswer() { return answer; }

    public String getQuestion() {
        return question;
    }

    public int getValue() {
        return value;
    }

}
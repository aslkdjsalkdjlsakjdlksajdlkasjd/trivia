package com.example.s156543.trivia;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by s156543 on 18-3-2018.
 */

public class Question implements Serializable{

    String answer;
    String question;
    int value;
    long category_id;
    String title;
    int clues_count;

    public Question(String answer, String question, int value,
                    long category_id, String title, int clues_count) {
        setAnswer(answer);
        setCategory_id(category_id);
        setClues_count(clues_count);
        setQuestion(question);
        setTitle(title);
        setValue(value);
    }

    public Question(JSONObject o) throws JSONException {
        this(o.getString("answer"), o.getString("question"),
                o.getInt("value"), o.getLong("category_id"),
                o.getString("title"), o.getInt("clues_count"));
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

    public void setCategory_id(long category_id) {
        this.category_id = category_id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setClues_count(int clues_count) {
        this.clues_count = clues_count;
    }

    public String getAnswer() {

        return answer;
    }

    public String getQuestion() {
        return question;
    }

    public int getValue() {
        return value;
    }

    public long getCategory_id() {
        return category_id;
    }

    public String getTitle() {
        return title;
    }

    public int getClues_count() {
        return clues_count;
    }
}

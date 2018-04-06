package com.example.s156543.trivia;

/**
 * Created by s156543 on 5-4-2018.
 */

public class Score {

    public int score;
    public String user;

    public Score(String User, int Score){
        user = User;
        score = Score;
    }

    public int getScore() {
        return score;
    }

    public String getUser() {
        return user;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setUser(String user) {
        this.user = user;
    }
}

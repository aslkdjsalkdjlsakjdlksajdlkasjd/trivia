//package com.example.s156543.trivia;
//
//import android.content.Context;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//import android.widget.ArrayAdapter;
//
//
//import java.util.ArrayList;
//
///**
// * Created by s156543 on 18-3-2018.
// */
//
//public class QuestionAdapter extends ArrayAdapter<Question> {
//
//    ArrayList<Question> questions;
//    @NonNull
//
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//
//        convertView = LayoutInflater.from(getContext()).inflate(R.layout.question_item,
//                parent, false);
//
//        TextView questionView = (TextView) convertView.findViewById(R.id.question);
//        Question question = questions.get(position);
//        questionView.setText(question.getTitle());
//
//        return convertView;
//    }
//
//    public QuestionAdapter(@NonNull Context context, int resource,
//                           @NonNull ArrayList<Question> questions) {
//        super(context, resource, questions);
//        this.questions = questions;
//
//    }
//}

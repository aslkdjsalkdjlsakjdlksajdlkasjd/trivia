
//MINOR PROGRAMMEREN UNIT 6


// Nina Boelsums - 10742670
// door de griep ben ik veel te laat begonnen en is
// mijn laatste app helaas super incompleet en doet bijna niks het :(




package com.example.s156543.trivia;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Logger;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity{

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    String email;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        email = "n.boelsums@gmail.com";
        password = "ik wil slapen";

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null){
                    Log.d("signed in","onAuthStateChanged:signed_in" + user.getUid());

                } else{
                    Log.d("signed in","onAuthStateChanged:signed_out");
                }
            }
        };
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        database.setLogLevel(Logger.Level.DEBUG);

        DatabaseReference myRef = database.getReference("message");
        myRef.setValue("Hello, World!");

        createUser();

    }

    @Override
    public void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop(){
        super.onStop();
        if (mAuthListener != null){
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
    public void clickRandom(View view){
        Intent intent = new Intent(MainActivity.this, AnswerActivity.class);
        startActivity(intent);
    }

    public void createUser(){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task){
                        Log.d("create user", "createdUserWithEmail:onComplete:"+ task.isSuccessful());
                    if (!task.isSuccessful()){
                        Toast.makeText(MainActivity.this, "Authentication failed",
                        Toast.LENGTH_SHORT).show();
                        } else{
                        Toast.makeText(MainActivity.this, "User Created" + email,
                                Toast.LENGTH_SHORT).show();

                    }
                    }
                });
    }

    public void login(View view){

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("Signed in!", "signInWithEmail:onComplete:" + task.isSuccessful());

                        if(!task.isSuccessful()){
                            Log.w("Sign in failed", "SignInWithEmail", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed",
                                    Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(MainActivity.this, "User Logged in" + email,
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }
}

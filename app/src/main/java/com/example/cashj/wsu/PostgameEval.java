package com.example.cashj.wsu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by cashj on 9/20/2017.
 */

public class PostgameEval extends AppCompatActivity {

    /** Write to database
     * Created by Aaron on 10/1/17
     */


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.postgame_eval);

        String ID;
        String PostGameEval;
        String timestamp;
        /**DatabaseReference myRef =
                database.getReferenceFromUrl("https://wsu-baseball.firebaseio.com/");
         **/
        TextView q1 = (TextView) findViewById(R.id.q1);
        TextView q2 = (TextView) findViewById(R.id.q2);
        TextView q3 = (TextView) findViewById(R.id.q3);
        TextView q4 = (TextView) findViewById(R.id.q4);
        TextView q5 = (TextView) findViewById(R.id.q5);
        TextView q6 = (TextView) findViewById(R.id.q6);
        // To find the specific user and place data in the right branch of data frame
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        ID =  user.getUid();
        PostGameEval = "PostGameEval";
        timestamp = "timestamp";

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        // Gives instance of database
        DatabaseReference databaseReference = firebaseDatabase.getReference();
        //This is how we are going to write to each child
        databaseReference.child(ID).child("PostGameEval").child(timestamp).setValue("userinputq1");




        //This is different must fix
        /**DatabaseReference myRef =
                database.getReferenceFromUrl("https://wsu-baseball.firebaseio.com/" + ID
                + PostGameEval + timestamp);
        **/
        //(firebase.com/ID/PostGameEval/timestamp) is where data will be stored
        //q1 -q7 are the different question ids that will populated from the database
    }
}
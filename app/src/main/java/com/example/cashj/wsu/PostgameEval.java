package com.example.cashj.wsu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by cashj on 9/20/2017.
 */

public class PostgameEval extends AppCompatActivity {

    /** Write to database
     * Created by Aaron on 10/1/17
     */
    //FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    String ID;
    String PostGameEval;
    TextView q1;
    TextView q2;
    TextView q3;
    TextView q4;
    TextView q5;
    TextView q6;

    SeekBar Rseek;
    SeekBar Aseek;
    SeekBar Iseek;
    SeekBar Dseek;
    SeekBar Eseek;
    SeekBar R2seek;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.postgame_eval);


        // To find the specific user and place data in the right branch of data frame
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        ID =  user.getUid();

        Calendar calendar;
        SimpleDateFormat simpleDateFormat;
        final String Date;
        calendar = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy");
        Date = simpleDateFormat.format(calendar.getTime());

        q1 = (TextView) findViewById(R.id.q1);
        q2 = (TextView) findViewById(R.id.q2);
        q3 = (TextView) findViewById(R.id.q3);
        q4 = (TextView) findViewById(R.id.q4);
        q5 = (TextView) findViewById(R.id.q5);
        q6 = (TextView) findViewById(R.id.q6);

        Rseek = (SeekBar) findViewById(R.id.Rseek);
        Aseek = (SeekBar) findViewById(R.id.Aseek);
        Iseek = (SeekBar) findViewById(R.id.Iseek);
        Dseek = (SeekBar) findViewById(R.id.Dseek);
        Eseek = (SeekBar) findViewById(R.id.Eseek);
        R2seek = (SeekBar) findViewById(R.id.R2seek);

        PostGameEval = "PostGameEval";

        // Get instance of database
        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        // Get reference to database
        final DatabaseReference myRef = database
                .getReferenceFromUrl("https://wsu-baseball.firebaseio.com");


        database.getReference("q/q1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                q1.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        database.getReference("q/q2").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                q2.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        database.getReference("q/q3").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                q3.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        database.getReference("q/q4").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                q4.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        database.getReference("q/q5").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                q5.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        database.getReference("q/q6").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                q6.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        /**
        database.getReference("users/"+ID+"/PostGameEval").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                q6.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
         **/

        // Send data to Firebase with Submit button

        Button btnSendDataToServer = (Button) findViewById(R.id.submitBtn);
        btnSendDataToServer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                myRef.child("users").child(ID).child(PostGameEval)
                        .child(Date).child("q1A").setValue(Rseek.getProgress()+1);

                myRef.child("users").child(ID).child(PostGameEval)
                        .child(Date).child("q2A").setValue(Aseek.getProgress()+1);

                myRef.child("users").child(ID).child(PostGameEval)
                        .child(Date).child("q3A").setValue(Iseek.getProgress()+1);

                myRef.child("users").child(ID).child(PostGameEval)
                        .child(Date).child("q4A").setValue(Dseek.getProgress()+1);

                myRef.child("users").child(ID).child(PostGameEval)
                        .child(Date).child("q5A").setValue(Eseek.getProgress()+1);

                myRef.child("users").child(ID).child(PostGameEval)
                        .child(Date).child("q6A").setValue(R2seek.getProgress()+1);


                Intent home = new Intent(getApplicationContext(), Home.class);
                startActivity(home);
            }
        });



        //databaseReference.child(ID).child("PostGameEval").child(timestamp).setValue("userinputq1");




        //This is different must fix
        /**DatabaseReference myRef =
                database.getReferenceFromUrl("https://wsu-baseball.firebaseio.com/" + ID
                + PostGameEval + timestamp);
        **/
        //(firebase.com/ID/PostGameEval/timestamp) is where data will be stored
        //q1 -q7 are the different question ids that will populated from the database
    }
}
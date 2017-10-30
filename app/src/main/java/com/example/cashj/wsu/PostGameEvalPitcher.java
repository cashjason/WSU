package com.example.cashj.wsu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class PostGameEvalPitcher extends AppCompatActivity {

    String ID;
    String Eval;
    TextView q1, q2, q3, q4, q5,q6, pitch1Q, pitch2Q, pitch3Q, pitch4Q, pitch5Q, pitch6Q;
    SeekBar rSeek, aSeek, iSeek, dSeek, eSeek, r2Seek, pitch1Seek, pitch2Seek;
    EditText notes, pgn1, pgn2, pgn3, pgn4, aNotes;
    Calendar calendar;
    SimpleDateFormat simpleDateFormat;
    String Date;
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_game_eval_pitcher);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        ID =  user.getUid();
        calendar = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy");
        Date = simpleDateFormat.format(calendar.getTime());
        q1 = (TextView) findViewById(R.id.q1);
        q2 = (TextView) findViewById(R.id.q2);
        q3 = (TextView) findViewById(R.id.q3);
        q4 = (TextView) findViewById(R.id.q4);
        q5 = (TextView) findViewById(R.id.q5);
        q6 = (TextView) findViewById(R.id.q6);
        pitch1Q = (TextView) findViewById(R.id.pq1);
        pitch2Q = (TextView) findViewById(R.id.pq2);
        pitch3Q = (TextView) findViewById(R.id.pitch1Q);
        pitch4Q = (TextView) findViewById(R.id.pitch2Q);
        pitch5Q = (TextView) findViewById(R.id.pitch3Q);
        pitch6Q = (TextView) findViewById(R.id.pitch4Q);
        rSeek = (SeekBar) findViewById(R.id.Rseek);
        aSeek = (SeekBar) findViewById(R.id.Aseek);
        iSeek = (SeekBar) findViewById(R.id.Iseek);
        dSeek = (SeekBar) findViewById(R.id.Dseek);
        eSeek = (SeekBar) findViewById(R.id.Eseek);
        r2Seek = (SeekBar) findViewById(R.id.R2seek);
        pitch1Seek = (SeekBar) findViewById(R.id.pitch1Seek);
        pitch2Seek = (SeekBar) findViewById(R.id.pitch2Seek);
        notes = (EditText) findViewById(R.id.notesText);
        aNotes = (EditText) findViewById(R.id.aNotes);
        pgn1 = (EditText) findViewById(R.id.pitch1A);
        pgn2 = (EditText) findViewById(R.id.pitch2A);
        pgn3 = (EditText) findViewById(R.id.pitch3A);
        pgn4 = (EditText) findViewById(R.id.pitch4A);
        Eval = "PostGameEval";
        database = FirebaseDatabase.getInstance();
        myRef = database.getReferenceFromUrl("https://wsu-baseball.firebaseio.com");

        database.getReference("Questions/General/question1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                q1.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        database.getReference("Questions/General/question2").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                q2.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        database.getReference("Questions/General/question3").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                q3.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        database.getReference("Questions/General/question4").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                q4.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        database.getReference("Questions/General/question5").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                q5.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        database.getReference("Questions/General/question6").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                q6.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        database.getReference("Questions/Pitcher/game1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                pitch1Q.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        database.getReference("Questions/Pitcher/game2").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                pitch2Q.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        database.getReference("Questions/Pitcher/game3").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                pitch3Q.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        database.getReference("Questions/Pitcher/game4").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                pitch4Q.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        database.getReference("Questions/Pitcher/game5").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                pitch5Q.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        database.getReference("Questions/Pitcher/game6").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                pitch6Q.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Button btnSendDataToServer = (Button) findViewById(R.id.submitBtn);
        btnSendDataToServer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                myRef.child("users").child(ID).child(Eval)
                        .child(Date).child("question1Answer").setValue(rSeek.getProgress()+1);

                myRef.child("users").child(ID).child(Eval)
                        .child(Date).child("question2Answer").setValue(aSeek.getProgress()+1);

                myRef.child("users").child(ID).child(Eval)
                        .child(Date).child("question3Answer").setValue(iSeek.getProgress()+1);

                myRef.child("users").child(ID).child(Eval)
                        .child(Date).child("question4Answer").setValue(dSeek.getProgress()+1);

                myRef.child("users").child(ID).child(Eval)
                        .child(Date).child("question5Answer").setValue(eSeek.getProgress()+1);

                myRef.child("users").child(ID).child(Eval)
                        .child(Date).child("question6Answer").setValue(r2Seek.getProgress()+1);

                myRef.child("users").child(ID).child(Eval)
                        .child(Date).child("notes").setValue(notes.getText().toString());

                myRef.child("users").child(ID).child(Eval)
                        .child(Date).child("pitchQuestion1Answer").setValue(pitch1Seek.getProgress()+1);

                myRef.child("users").child(ID).child(Eval)
                        .child(Date).child("pitchQuestion2Answer").setValue(pitch2Seek.getProgress()+1);

                myRef.child("users").child(ID).child(Eval)
                        .child(Date).child("pitchQuestion3NotesAnswer").setValue(pgn1.getText().toString());

                myRef.child("users").child(ID).child(Eval)
                        .child(Date).child("pitchQuestion4NotesAnswer").setValue(pgn2.getText().toString());

                myRef.child("users").child(ID).child(Eval)
                        .child(Date).child("pitchQuestion5NotesAnswer").setValue(pgn3.getText().toString());

                myRef.child("users").child(ID).child(Eval)
                        .child(Date).child("pitchQuestion6NotesAnswer").setValue(pgn4.getText().toString());

                myRef.child("users").child(ID).child(Eval)
                        .child(Date).child("additionalNotes").setValue(aNotes.getText().toString());

                Intent home = new Intent(getApplicationContext(), Home.class);
                startActivity(home);
            }
        });
    }

}


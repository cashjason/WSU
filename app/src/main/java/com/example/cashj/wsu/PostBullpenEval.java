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

public class PostBullpenEval extends AppCompatActivity {

    String ID;
    String Eval;
    TextView q1, q2, q3, q4, q5,q6, bq1, bull1, bull2, bull3, bull4;
    SeekBar rSeek, aSeek, iSeek, dSeek, eSeek, r2Seek, bSeek;
    EditText notes, bull1Notes, bull2Notes, bull3Notes, bull4Notes;
    Calendar calendar;
    SimpleDateFormat simpleDateFormat;
    String Date;
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_bullpen_eval);

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
        bq1 = (TextView) findViewById(R.id.bq1);
        bull1 = (TextView) findViewById(R.id.bull1);
        bull2 = (TextView) findViewById(R.id.bull2);
        bull3 = (TextView) findViewById(R.id.bull3);
        bull4 = (TextView) findViewById(R.id.bull4);
        rSeek = (SeekBar) findViewById(R.id.Rseek);
        aSeek = (SeekBar) findViewById(R.id.Aseek);
        iSeek = (SeekBar) findViewById(R.id.Iseek);
        dSeek = (SeekBar) findViewById(R.id.Dseek);
        eSeek = (SeekBar) findViewById(R.id.Eseek);
        r2Seek = (SeekBar) findViewById(R.id.R2seek);
        bSeek = (SeekBar) findViewById(R.id.bpSeek);
        notes = (EditText) findViewById(R.id.notesText);
        bull1Notes = (EditText) findViewById(R.id.b1Notes);
        bull2Notes = (EditText) findViewById(R.id.b2Notes);
        bull3Notes = (EditText) findViewById(R.id.b3Notes);
        bull4Notes = (EditText) findViewById(R.id.b4Notes);
        Eval = "PostBullpenEval";
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

        database.getReference("Questions/Pitcher/bullpen").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                bq1.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        database.getReference("Questions/Pitcher/bullpen1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                bull1.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        database.getReference("Questions/Pitcher/bullpen2").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                bull1.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        database.getReference("Questions/Pitcher/bullpen2").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                bull1.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        database.getReference("Questions/Pitcher/bullpen3").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                bull1.setText(value);
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
                        .child(Date).child("bullpenQuestion1Answer").setValue(bSeek.getProgress()+1);

                myRef.child("users").child(ID).child(Eval)
                        .child(Date).child("bullpenQuestion1Notes").setValue(bull1Notes.getText().toString());

                myRef.child("users").child(ID).child(Eval)
                        .child(Date).child("bullpenQuestion2Notes").setValue(bull2Notes.getText().toString());

                myRef.child("users").child(ID).child(Eval)
                        .child(Date).child("bullpenQuestion3Notes").setValue(bull3Notes.getText().toString());

                myRef.child("users").child(ID).child(Eval)
                        .child(Date).child("bullpenQuestion4Notes").setValue(bull4Notes.getText().toString());

                Intent home = new Intent(getApplicationContext(), Home.class);
                startActivity(home);
            }
        });
    }

}


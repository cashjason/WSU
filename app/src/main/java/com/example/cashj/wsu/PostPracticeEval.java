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
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PostPracticeEval extends AppCompatActivity implements View.OnClickListener {

    String ID;
    String Eval;
    TextView q1, q2, q3, q4, q5,q6;
    EditText notesText;
    SeekBar rSeek, aSeek, iSeek, dSeek, eSeek, r2Seek;
    Button submit;
    String Date;
    FirebaseUser user;
    Calendar calendar;
    SimpleDateFormat simpleDateFormat;
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.postpractice);

        user = FirebaseAuth.getInstance().getCurrentUser();
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
        notesText = (EditText) findViewById(R.id.notesText);
        rSeek = (SeekBar) findViewById(R.id.Rseek);
        aSeek = (SeekBar) findViewById(R.id.Aseek);
        iSeek = (SeekBar) findViewById(R.id.Iseek);
        dSeek = (SeekBar) findViewById(R.id.Dseek);
        eSeek = (SeekBar) findViewById(R.id.Eseek);
        r2Seek = (SeekBar) findViewById(R.id.R2seek);
        submit = (Button) findViewById(R.id.submitBtn);
        submit.setOnClickListener(this);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReferenceFromUrl("https://wsu-baseball.firebaseio.com");
        Eval = "PostPracticeEval";

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
    }

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
                .child(Date).child("notes").setValue(notesText.getText().toString());

        Toast.makeText(this, "Post Practice Evaluation Submitted!", Toast.LENGTH_SHORT).show();
        Intent home = new Intent(getApplicationContext(), Home.class);
        startActivity(home);
    }
}
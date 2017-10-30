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

/**
 * Created by cashj on 9/20/2017.
 */

public class PostgameEval extends AppCompatActivity {

    String ID;
    String PostGameEval;
    TextView q1, q2, q3, q4, q5,q6, abr1, abr2;
    SeekBar rSeek, aSeek, iSeek, dSeek, eSeek, r2Seek, ab1Seek, ab2Seek;
    EditText notes, ab1Notes, ab2Notes, ab3Notes, ab4Notes, ab5Notes, ab6Notes, ab7Notes;
    Calendar calendar;
    SimpleDateFormat simpleDateFormat;
    String Date;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.postgame_eval);


        // To find the specific user and place data in the right branch of data frame
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
        abr1 = (TextView) findViewById(R.id.abd1);
        abr2 = (TextView) findViewById(R.id.abd2);
        rSeek = (SeekBar) findViewById(R.id.Rseek);
        aSeek = (SeekBar) findViewById(R.id.Aseek);
        iSeek = (SeekBar) findViewById(R.id.Iseek);
        dSeek = (SeekBar) findViewById(R.id.Dseek);
        eSeek = (SeekBar) findViewById(R.id.Eseek);
        r2Seek = (SeekBar) findViewById(R.id.R2seek);
        ab1Seek = (SeekBar) findViewById(R.id.abSeek);
        ab2Seek = (SeekBar) findViewById(R.id.ab2Seek);
        notes = (EditText) findViewById(R.id.notesText);
        ab1Notes = (EditText) findViewById(R.id.ab1Notes);
        ab2Notes = (EditText) findViewById(R.id.ab2Notes);
        ab3Notes = (EditText) findViewById(R.id.ab3Notes);
        ab4Notes = (EditText) findViewById(R.id.ab4Notes);
        ab5Notes = (EditText) findViewById(R.id.ab5Notes);
        ab6Notes = (EditText) findViewById(R.id.ab6Notes);
        ab7Notes = (EditText) findViewById(R.id.ab7Notes);

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

        database.getReference("q/abr1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                abr1.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        database.getReference("q/abr2").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                abr2.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Button btnSendDataToServer = (Button) findViewById(R.id.submitBtn);
        btnSendDataToServer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                myRef.child("users").child(ID).child(PostGameEval)
                        .child(Date).child("q1A").setValue(rSeek.getProgress()+1);

                myRef.child("users").child(ID).child(PostGameEval)
                        .child(Date).child("q2A").setValue(aSeek.getProgress()+1);

                myRef.child("users").child(ID).child(PostGameEval)
                        .child(Date).child("q3A").setValue(iSeek.getProgress()+1);

                myRef.child("users").child(ID).child(PostGameEval)
                        .child(Date).child("q4A").setValue(dSeek.getProgress()+1);

                myRef.child("users").child(ID).child(PostGameEval)
                        .child(Date).child("q5A").setValue(eSeek.getProgress()+1);

                myRef.child("users").child(ID).child(PostGameEval)
                        .child(Date).child("q6A").setValue(r2Seek.getProgress()+1);

                myRef.child("users").child(ID).child(PostGameEval)
                        .child(Date).child("notes").setValue(notes.getText().toString());

                myRef.child("users").child(ID).child(PostGameEval)
                        .child(Date).child("ab1A").setValue(ab1Seek.getProgress()+1);

                myRef.child("users").child(ID).child(PostGameEval)
                        .child(Date).child("ab2A").setValue(ab2Seek.getProgress()+1);

                myRef.child("users").child(ID).child(PostGameEval)
                        .child(Date).child("ab1Notes").setValue(ab1Notes.getText().toString());

                myRef.child("users").child(ID).child(PostGameEval)
                        .child(Date).child("ab2Notes").setValue(ab2Notes.getText().toString());

                myRef.child("users").child(ID).child(PostGameEval)
                        .child(Date).child("ab3Notes").setValue(ab3Notes.getText().toString());

                myRef.child("users").child(ID).child(PostGameEval)
                        .child(Date).child("ab4Notes").setValue(ab4Notes.getText().toString());

                myRef.child("users").child(ID).child(PostGameEval)
                        .child(Date).child("ab5Notes").setValue(ab5Notes.getText().toString());

                myRef.child("users").child(ID).child(PostGameEval)
                        .child(Date).child("ab6Notes").setValue(ab6Notes.getText().toString());

                myRef.child("users").child(ID).child(PostGameEval)
                        .child(Date).child("ab7Notes").setValue(ab7Notes.getText().toString());

                Intent home = new Intent(getApplicationContext(), Home.class);
                startActivity(home);
            }
        });
    }
}
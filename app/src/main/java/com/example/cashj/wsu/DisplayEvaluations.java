package com.example.cashj.wsu;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.github.sundeepk.compactcalendarview.domain.Event;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DisplayEvaluations extends AppCompatActivity {

    String ID;
    TextView title, q1, q2, q3, q4, q5,q6, bq1, bull1, bull2, bull3, bull4;
    SeekBar rSeek, aSeek, iSeek, dSeek, eSeek, r2Seek, bSeek;
    EditText notes, bull1Notes, bull2Notes, bull3Notes, bull4Notes;
    LinearLayout general, postGamePitcher, postGame, postBullpen;
    SimpleDateFormat simpleDateFormat;
    FirebaseDatabase database;
    String eval, date;
    DatabaseReference mDatabase;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_evaluatioins);
        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            eval = extras.getString("EVAL");
            date = extras.getString("DATE");
        }
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        ID = user.getUid();
        simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy");
        mDatabase = FirebaseDatabase.getInstance().getReference();

        title = (TextView) findViewById(R.id.evalTitle);
        title.setText(eval);
        q1 = (TextView) findViewById(R.id.q1);
        q2 = (TextView) findViewById(R.id.q2);
        q3 = (TextView) findViewById(R.id.q3);
        q4 = (TextView) findViewById(R.id.q4);
        q5 = (TextView) findViewById(R.id.q5);
        q6 = (TextView) findViewById(R.id.q6);

        rSeek = (SeekBar) findViewById(R.id.Rseek);
        aSeek = (SeekBar) findViewById(R.id.Aseek);
        iSeek = (SeekBar) findViewById(R.id.Iseek);
        dSeek = (SeekBar) findViewById(R.id.Dseek);
        eSeek = (SeekBar) findViewById(R.id.Eseek);
        r2Seek = (SeekBar) findViewById(R.id.R2seek);
        notes = (EditText) findViewById(R.id.notesText);

        general = (LinearLayout) findViewById(R.id.general);
        postGame = (LinearLayout) findViewById(R.id.postGameHitter);
        postGamePitcher = (LinearLayout) findViewById(R.id.postGamePitcher);
        postBullpen = (LinearLayout) findViewById(R.id.postBullpen);
        postGame.setVisibility(View.GONE);
        postGamePitcher.setVisibility(View.GONE);
        postBullpen.setVisibility(View.GONE);

        mDatabase.child("users/"+ID+"/PlayerInformation/Position").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" + dataSnapshot.getValue());
                if (dataSnapshot.getValue().toString().equals("Hitter")){
                    if (eval.equals("PostGameEval")){
                        postGame.setVisibility(View.VISIBLE);
                    }
                    else if (eval.equals("PostGameEval")){
                        postGame.setVisibility(View.VISIBLE);
                    }
                }else if (dataSnapshot.getValue().toString().equals("Pitcher")){
                    if (eval.equals("PostGameEval")){
                        postGamePitcher.setVisibility(View.VISIBLE);
                    }
                    else if (eval.equals("PostBullpenEval")){
                        postBullpen.setVisibility(View.VISIBLE);
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });//End of UserData Query

        mDatabase.child("users/"+ID+"/" + eval + "/" + date).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snap : dataSnapshot.getChildren()) {
                    //System.out.println("3333333333333333333333333333333333333333333333333333333333     " + snap.getKey() + "Has the value : " + snap.getValue());
                    String key = snap.getKey();
                    
                    if (key.equals("question1Answer")){
                        long val = (long) snap.getValue();
                        rSeek.setProgress((int) val);
                    }else if(key.equals("question2Answer")){
                        long val = (long) snap.getValue();
                        aSeek.setProgress((int) val);
                    }else if(key.equals("question3Answer")){
                        long val = (long) snap.getValue();
                        iSeek.setProgress((int) val);
                    }else if(key.equals("question4Answer")){
                        long val = (long) snap.getValue();
                        dSeek.setProgress((int) val);
                    }else if(key.equals("question5Answer")){
                        long val = (long) snap.getValue();
                        eSeek.setProgress((int) val);
                    }else if(key.equals("question6Answer")){
                        long val = (long) snap.getValue();
                        r2Seek.setProgress((int) val);
                    }else if(key.equals("notes")){
                        notes.setText((String)snap.getValue());
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });//End of UserData Query
    }

}

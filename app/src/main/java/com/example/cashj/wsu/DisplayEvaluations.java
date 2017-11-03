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

public class DisplayEvaluations extends AppCompatActivity {

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
    String eval, date;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_evaluatioins);
        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            eval = extras.getString("EVAL");
            date = extras.getString("DATE");
        }
        System.out.println("EVAL + DATE = " + eval + date);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        ID = user.getUid();
        calendar = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy");
        Date = simpleDateFormat.format(calendar.getTime());
    }

}

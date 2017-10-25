package com.example.cashj.wsu;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by cashj on 9/20/2017.
 */

@RequiresApi(api = Build.VERSION_CODES.N)
public class History extends AppCompatActivity implements View.OnClickListener {
// Modified by Aaron on 10/19/2017


    CompactCalendarView compactCalendar;
    TextView monthText;
    private SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MMMM- yyyy", Locale.getDefault());
    SimpleDateFormat sdf;
    FirebaseDatabase database;
    private DatabaseReference mDatabase;
    ArrayList list;
    Button postGame;
    Button postPractice;
    Button postBullpen;
    String ID;
    FirebaseUser user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);
        user = FirebaseAuth.getInstance().getCurrentUser();
        ID =  user.getUid();
        compactCalendar = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
        compactCalendar.setUseThreeLetterAbbreviation(true);
        compactCalendar.setFirstDayOfWeek(Calendar.SUNDAY);
        monthText = (TextView) findViewById(R.id.monthTitle);
        database = FirebaseDatabase.getInstance();
        postGame = (Button) findViewById(R.id.PostGameEvalBtn);
        postPractice = (Button) findViewById(R.id.PostPracticeEvalBtn);
        postBullpen = (Button) findViewById(R.id.PostBullpenBtn);
        postGame.setVisibility(View.GONE);
        postPractice.setVisibility(View.GONE);
        postBullpen.setVisibility(View.GONE);
        postGame.setOnClickListener(this);
        sdf = new SimpleDateFormat("MM-dd-yyyy");
        mDatabase = FirebaseDatabase.getInstance().getReference();
        //check postgame, postpractice, and post bullpen
        //Get all childeren that are time stamps!
        list = new ArrayList<String>();
        mDatabase.child("users/"+ID+"/PostGameEval/").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                list.add(dataSnapshot.getChildren().toString());
                for (DataSnapshot snap : dataSnapshot.getChildren()) {
                    Date date = null;
                    try {
                        date = sdf.parse(snap.getKey().toString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    long millis = date.getTime();
                    Event evt = new Event(Color.GREEN, millis);
                    compactCalendar.addEvent(evt);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });//End of UserData Query
        mDatabase.child("users/"+ID+"/PostPracticeEval/").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list.add(dataSnapshot.getChildren().toString());
                for (DataSnapshot snap : dataSnapshot.getChildren()) {
                    Date date = null;
                    try {
                        date = sdf.parse(snap.getKey().toString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    long millis = date.getTime();
                    Event evt = new Event(Color.RED, millis);
                    compactCalendar.addEvent(evt);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        //This is for the bullpen
//        mDatabase.child("users/"+ID+"/PostGameEval/").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                list.add(dataSnapshot.getChildren().toString());
//                for (DataSnapshot snap : dataSnapshot.getChildren()) {
////                    list.add(snap.toString());
////                    snap.getKey().toString();// This will get the date of all of the children of posgame evals
//                    System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + snap.getKey().toString() + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//                }
//            }
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//            }
//        });//End of UserData Query

        compactCalendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(final Date dateClicked) {
                //Check to see if evaluations completed that day and show the buttons for them.
                //check postgame, postpractice, and post bullpen
                mDatabase.child("users/"+ID+"/PostGameEval/").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        list.add(dataSnapshot.getChildren().toString());
                        postGame.setVisibility(View.GONE);
                        for (DataSnapshot snap : dataSnapshot.getChildren()) {
                            long currentDate = dateClicked.getTime();
                            Date evalDate = null;
                            try {
                                evalDate = sdf.parse(snap.getKey().toString());
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            long millis = evalDate.getTime();
                            if (currentDate == millis){
                                postGame.setVisibility(View.VISIBLE);
                            }

                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });//End of UserData Query
                mDatabase.child("users/"+ID+"/PostPracticeEval/").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        list.add(dataSnapshot.getChildren().toString());
                        postPractice.setVisibility(View.GONE);
                        for (DataSnapshot snap : dataSnapshot.getChildren()) {
                            long currentDate = dateClicked.getTime();
                            Date evalDate = null;
                            try {
                                evalDate = sdf.parse(snap.getKey().toString());
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            long millis = evalDate.getTime();
                            if (currentDate == millis){
                                postPractice.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });//End of UserData Query
            }
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                monthText.setText(dateFormatMonth.format(firstDayOfNewMonth));
            }
        });
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.PostPracticeEvalBtn) {
            Intent act = new Intent(getApplicationContext(), Evaluations.class);
            startActivity(act);
        }
        if (i == R.id.PostPracticeEvalBtn) {
            Intent act = new Intent(getApplicationContext(), History.class);
            startActivity(act);
        }
        if (i == R.id.PostBullpenBtn) {
            Intent act = new Intent(getApplicationContext(), Profile.class);
            startActivity(act);
        }
    }
}

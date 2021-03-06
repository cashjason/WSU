package com.example.cashj.wsu;

import android.content.Intent;
import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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
import java.util.Locale;

@RequiresApi(api = Build.VERSION_CODES.N)
public class History extends AppCompatActivity implements View.OnClickListener {

    CompactCalendarView compactCalendar;
    TextView monthText;
    SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MMMM- yyyy", Locale.getDefault());
    SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
    FirebaseDatabase database;
    DatabaseReference mDatabase;
    ArrayList list;
    Button postGame, postPractice, postBullpen;
    String ID;
    String selectedDate;
    FirebaseUser user;
    //TODO: Fix bug where when the cal is clicked the items appear. make show when cal opened.
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
        postBullpen.setOnClickListener(this);
        postGame.setOnClickListener(this);
        postPractice.setOnClickListener(this);
        postGame.setOnClickListener(this);
        selectedDate = "";
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("users/"+ID+"/PostGameEval/").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

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
        //TODO: Add bullpen evaluations to history section.
        //This is for the bullpen
        mDatabase.child("users/"+ID+"/PostBullpenEval/").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snap : dataSnapshot.getChildren()) {
                    Date date = null;
                    try {
                        date = sdf.parse(snap.getKey().toString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    long millis = date.getTime();
                    Event evt = new Event(Color.BLUE, millis);
                    compactCalendar.addEvent(evt);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });//End of UserData Query

        compactCalendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(final Date dateClicked) {
                mDatabase.child("users/"+ID+"/PostGameEval/").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        postGame.setVisibility(View.GONE);
                        for (DataSnapshot snap : dataSnapshot.getChildren()) {
                            long currentDate = dateClicked.getTime();
                            Date evalDate = null;
                            try {
                                evalDate = sdf.parse(snap.getKey().toString());
                                selectedDate = snap.getKey();

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
                });
                mDatabase.child("users/"+ID+"/PostPracticeEval/").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        postPractice.setVisibility(View.GONE);
                        for (DataSnapshot snap : dataSnapshot.getChildren()) {
                            long currentDate = dateClicked.getTime();
                            Date evalDate = null;
                            try {
                                evalDate = sdf.parse(snap.getKey().toString());
                                selectedDate = snap.getKey();
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
                });
                mDatabase.child("users/"+ID+"/PostBullpenEval/").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        postBullpen.setVisibility(View.GONE);
                        for (DataSnapshot snap : dataSnapshot.getChildren()) {
                            long currentDate = dateClicked.getTime();
                            Date evalDate = null;
                            try {
                                evalDate = sdf.parse(snap.getKey().toString());
                                selectedDate = snap.getKey();
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            long millis = evalDate.getTime();
                            if (currentDate == millis){
                                postBullpen.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
            }
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                monthText.setText(dateFormatMonth.format(firstDayOfNewMonth));
            }
        });
    }

//TODO: Link the correct evaluations to the correct buttons and show eval results.
    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.PostGameEvalBtn) {
            Intent act = new Intent(getApplicationContext(), DisplayEvaluations.class);
            act.putExtra("EVAL", "PostGameEval");
            act.putExtra("DATE", selectedDate);
            startActivity(act);
        }
        if (i == R.id.PostPracticeEvalBtn) {
            Intent act = new Intent(getApplicationContext(), DisplayEvaluations.class);
            act.putExtra("EVAL", "PostPracticeEval");
            act.putExtra("DATE", selectedDate);
            startActivity(act);
        }
        if (i == R.id.PostBullpenBtn) {
            Intent act = new Intent(getApplicationContext(), DisplayEvaluations.class);
            act.putExtra("EVAL", "PostBullpenEval");
            act.putExtra("DATE", selectedDate);
            startActivity(act);
        }
    }
}

package com.example.cashj.wsu;

import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.CalendarView;

/**
 * Created by cashj on 9/20/2017.
 */

public class History extends AppCompatActivity {
// Modified by Aaron on 10/19/2017
    Calendar mCurrentDate;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);


        mCurrentDate = Calendar.getInstance();
    }
}

package com.example.cashj.wsu;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.sundeepk.compactcalendarview.domain.Event;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.Series;

import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.example.cashj.wsu.R.id.imageView;

@RequiresApi(api = Build.VERSION_CODES.N)
public class Progress extends AppCompatActivity implements View.OnClickListener, ValueEventListener {

    String ID;
    FirebaseUser user;
    FirebaseDatabase database;
    ArrayList<Integer> R1, A, I, D, E, R2;
    DatabaseReference mDatabase;
    SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress);

        user = FirebaseAuth.getInstance().getCurrentUser();
        ID = user.getUid();
        database = FirebaseDatabase.getInstance();
        R1 = new ArrayList<>();
        A = new ArrayList<>();
        I = new ArrayList<>();
        D = new ArrayList<>();
        E = new ArrayList<>();
        R2 = new ArrayList<>();
        mDatabase = FirebaseDatabase.getInstance().getReference();


        mDatabase.child("users/"+ID+"/PostGameEval").addValueEventListener(this);


    }

    public double getAve(ArrayList<Integer> arr){
        double val = 0;

        for (int i = 0; i < arr.size(); i++){
            val = val + arr.get(i);
        }
        val = val / arr.size();
        return val;
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();

    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        for (DataSnapshot snap : dataSnapshot.getChildren()) {
            String s = snap.getValue().toString();
            s = s.substring(1, s.length()-1);
            List<String> list = new ArrayList<String>(Arrays.asList(s.split(", ")));
            for(int i = 0; i < list.size(); i++) {
                System.out.println("Values is " + list.get(i));
                List<String> items = new ArrayList<String>(Arrays.asList(list.get(i).split("=")));
                if(items.get(0).equals("question1Answer")){
                    R1.add(Integer.valueOf(items.get(1)));
                }else if(items.get(0).equals("question2Answer")){
                    A.add(Integer.valueOf(items.get(1)));
                }else if(items.get(0).equals("question3Answer")){
                    I.add(Integer.valueOf(items.get(1)));
                }else if(items.get(0).equals("question4Answer")){
                    D.add(Integer.valueOf(items.get(1)));
                }else if(items.get(0).equals("question5Answer")){
                    E.add(Integer.valueOf(items.get(1)));
                }else if(items.get(0).equals("question6Answer")){
                    R2.add(Integer.valueOf(items.get(1)));
                }
            }
        }
        System.out.println("R1 = " + R1);
        System.out.println("A = " + A);
        System.out.println("I = " + I);
        System.out.println("D = " + D);
        System.out.println("E = " + E);
        System.out.println("R2 = " + R2);
        GraphView graph = (GraphView) findViewById(R.id.graph);
        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(new DataPoint[]{
                new DataPoint(0,0),
                new DataPoint(1, 0 + getAve(R1)),
                new DataPoint(2, 0 + getAve(A)),
                new DataPoint(3, 0 + getAve(I)),
                new DataPoint(4, 0 + getAve(D)),
                new DataPoint(5, 0 + getAve(E)),
                new DataPoint(6, 0 + getAve(R2)),
                new DataPoint(7, 0 )
        });
//        BarGraphSeries<DataPoint> series2 = new BarGraphSeries<>(new DataPoint[]{
//                new DataPoint(0,0),
//                new DataPoint(7, 0 )
//        });
        series.setValueDependentColor(new ValueDependentColor<DataPoint>() {
            @Override
            public int get(DataPoint data) {
                return Color.rgb((int) data.getX()*255/4, (int) Math.abs(data.getY()*255/6), 100);
            }
        });
        series.setSpacing(25);
        series.setAnimated(true);
        series.setDrawValuesOnTop(false);
        series.setValuesOnTopColor(Color.WHITE);
        graph.addSeries(series);
        //graph.addSeries(series2);
        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
        staticLabelsFormatter.setHorizontalLabels(new String[] {"", "R", "A", "I", "D", "E", "R", ""});
        staticLabelsFormatter.setVerticalLabels(new String[] {"0","1", "2", "3", "4", "5", "6", "7", "8", "9", "10"});
        graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
        graph.getGridLabelRenderer().setGridStyle( GridLabelRenderer.GridStyle.HORIZONTAL );
        graph.setTitle("Average Post Game Scores");

        series.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {
                int value = (int)dataPoint.getX();
                if (value == 1){
                    Toast.makeText(Progress.this, "Series1: On Data Point clicked: "+value, Toast.LENGTH_SHORT).show();
                }else if(value == 2){
                    Toast.makeText(Progress.this, "Series1: On Data Point clicked: "+value, Toast.LENGTH_SHORT).show();
                }
                else if(value == 3){
                    Toast.makeText(Progress.this, "Series1: On Data Point clicked: "+value, Toast.LENGTH_SHORT).show();
                }
                else if(value == 4){
                    Toast.makeText(Progress.this, "Series1: On Data Point clicked: "+value, Toast.LENGTH_SHORT).show();
                }
                else if(value == 5){
                    Toast.makeText(Progress.this, "Series1: On Data Point clicked: "+value, Toast.LENGTH_SHORT).show();
                }
                else if(value == 6){
                    Toast.makeText(Progress.this, "Series1: On Data Point clicked: "+value, Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
}
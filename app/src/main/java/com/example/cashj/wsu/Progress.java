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
import java.util.Collections;
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
    ArrayList<Integer> R1, A, I, D, E, R2, newR1, newA, newI, newD, newE, newR2;
    ArrayList<Long> dates, datesSorted;
    DatabaseReference mDatabase;
    GraphView graph2;
    SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");

//Created double array and organize from least to greatest by the millis of time stamps gottens
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
        newR1 = new ArrayList<>();
        newA = new ArrayList<>();
        newI = new ArrayList<>();
        newD = new ArrayList<>();
        newE = new ArrayList<>();
        newR2 = new ArrayList<>();
        dates = new ArrayList<>();
        datesSorted = new ArrayList<>();
        graph2 = (GraphView) findViewById(R.id.graph2);
        graph2.setVisibility(View.GONE);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("users/"+ID+"/PostGameEval").addValueEventListener(this);
        mDatabase.child("users/"+ID+"/PostPracticeEval").addValueEventListener(this);
        mDatabase.child("users/"+ID+"/PostBullpenEval").addValueEventListener(this);
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
            Date date = null;
            try {
                date = sdf.parse(snap.getKey().toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            long millis = date.getTime();

            dates.add(millis);
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
        updateGraph();
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }

    public void updateGraph(){
        //System.out.println("Stuff  = " + dates.size() + datesSorted.size());
        datesSorted.clear();
        for (int i = 0; i < dates.size(); i++){
            datesSorted.add(dates.get(i));
        }
        newR1.clear();
        newA.clear();
        newI.clear();
        newD.clear();
        newE.clear();
        newR2.clear();
        Collections.sort(datesSorted);
        System.out.println("Dates = " + dates);
        System.out.println("Dates Sorted = " + datesSorted);
        for(int i = 0; i < dates.size(); i++){
            for(int j = 0; j < datesSorted.size(); j++){
                if(dates.get(i) == datesSorted.get(j)){
                    newR1.add(R1.get(j));
                    newA.add(A.get(j));
                    newI.add(I.get(j));
                    newD.add(D.get(j));
                    newE.add(E.get(j));
                    newR2.add(R2.get(j));
                }
            }
        }



        System.out.println("R1 = " + newR1);
        System.out.println("A = " + newA);
        System.out.println("I = " + newI);
        System.out.println("D = " + newD);
        System.out.println("E = " + newE);
        System.out.println("R2 = " + newR2);
        GraphView graph = (GraphView) findViewById(R.id.graph);
        graph.removeAllSeries();
        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(new DataPoint[]{
                new DataPoint(0,0),
                new DataPoint(1, 0 + getAve(newR1)),
                new DataPoint(2, 0 + getAve(newA)),
                new DataPoint(3, 0 + getAve(newI)),
                new DataPoint(4, 0 + getAve(newD)),
                new DataPoint(5, 0 + getAve(newE)),
                new DataPoint(6, 0 + getAve(newR2)),
                new DataPoint(7, 0 )
        });
        series.setValueDependentColor(new ValueDependentColor<DataPoint>() {
            @Override
            public int get(DataPoint data) {
                return Color.rgb((int)Math.abs(data.getX()*255/4), (int) Math.abs(data.getY()*255/6), 100);
            }
        });
        series.setSpacing(25);
        series.setAnimated(true);
        graph.addSeries(series);
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
                    showQuestionGraph(newR1);
                }else if(value == 2){
                    showQuestionGraph(newA);
                }
                else if(value == 3){
                    showQuestionGraph(newI);
                }
                else if(value == 4){
                    showQuestionGraph(newD);
                }
                else if(value == 5){
                    showQuestionGraph(newE);
                }
                else if(value == 6){
                    showQuestionGraph(newR2);
                }
            }
        });
    }

    public void showQuestionGraph(ArrayList<Integer> question){


        System.out.println("Dates = " + dates);

        graph2.setVisibility(View.VISIBLE);
        graph2.removeAllSeries();
        graph2.getViewport().setMaxY(10);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0,0 + question.get(0))
        });
        LineGraphSeries<DataPoint> series2 = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0,0)
        });

        for (int i = 1; i < question.size(); i++){
            series.appendData(new DataPoint(series.getHighestValueX()+1, 0 + question.get(i)), true, 40);
        }
        graph2.setMinimumHeight(10);
        series.setDrawAsPath(true);
        graph2.addSeries(series);
        graph2.addSeries(series2);
        series.setColor(Color.rgb(200,200,0));
        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph2);
        staticLabelsFormatter.setVerticalLabels(new String[] {"0","1", "2", "3", "4", "5", "6", "7", "8", "9", "10"});
        graph2.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
        graph2.getGridLabelRenderer().setGridStyle( GridLabelRenderer.GridStyle.HORIZONTAL );
    }
}
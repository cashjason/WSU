package com.example.cashj.wsu;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by cashj on 10/11/2017.
 */

public class Evaluations extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.evaluations);

        //Button bullPen = (Button) findViewById(R.id.evaluationsBtn);
        //bullpen.setOnClickListener(this);
        Button practiceEval = (Button) findViewById(R.id.postPreacticeEval);
        practiceEval.setOnClickListener(this);
        Button gameEval = (Button) findViewById(R.id.postGameEval);
        gameEval.setOnClickListener(this);
        final Button bullpenEval = (Button) findViewById(R.id.bullpenEval);
        bullpenEval.setOnClickListener(this);

        String ID;
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        ID =  user.getUid();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        database.getReference("users/"+ID+"/PlayerInformation/Position").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                if (value.equals("Hitter")){
                    bullpenEval.setVisibility(View.GONE);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        //if (i == R.id.bullPen) {
        //    Intent act = new Intent(getApplicationContext(), Evaluations.class);
        //    startActivity(act);
        //}
        if (i == R.id.postPreacticeEval) {
            Intent act = new Intent(getApplicationContext(), PostPracticeEval.class);
            startActivity(act);
        }
        if (i == R.id.postGameEval) {
            Intent act = new Intent(getApplicationContext(), PostgameEval.class);
            startActivity(act);
        }
    }
}
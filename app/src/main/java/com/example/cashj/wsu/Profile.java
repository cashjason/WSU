package com.example.cashj.wsu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

/**
 * Created by cashj on 10/11/2017.
 */

public class Profile extends AppCompatActivity implements View.OnClickListener {
    Button done;
    EditText pName;
    EditText pNum;
    EditText pPhone;
    Spinner pTeam;
    Spinner pYear;
    Spinner pPosition;
    EditText pHeight;
    EditText pWeight;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        Values v = new Values();

        done = (Button) findViewById(R.id.doneBtn);
        done.setOnClickListener(this);

        pName = (EditText) findViewById(R.id.profName);
        pName.setText(v.getName());
        pNum = (EditText) findViewById(R.id.profNum);
        pNum.setText(v.getNumber());
        pPhone = (EditText) findViewById(R.id.profPhone);
        pPhone.setText(v.getPhone());
        pHeight = (EditText) findViewById(R.id.profPhone);
        pHeight.setText(v.getHeight());
        pWeight = (EditText) findViewById(R.id.profPhone);
        pWeight.setText(v.getWeight());
        pTeam = (Spinner) findViewById(R.id.teamSpinner);
        pTeam = (Spinner) findViewById(R.id.teamSpinner);
        pTeam = (Spinner) findViewById(R.id.teamSpinner);
        //set all values to local variables then compare with the database to change if necessary

    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.doneBtn) {
            Values v = new Values();
            //set local text variables to current values then update to database
            v.setName(pName.getText().toString());
            v.setNumber(pNum.getText().toString());
            v.setPhone(pPhone.getText().toString());
            v.setHeight(pHeight.getText().toString());
            v.setWeight(pWeight.getText().toString());
           // v.setTeam(pTeam.

        }
    }
}
package com.example.cashj.wsu;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.util.jar.Attributes;


/**
 * Created by cashj on 10/11/2017.
 */


public class Profile extends AppCompatActivity implements View.OnClickListener {
    Button done;
    EditText pName, pNum, pPhone, pHeight, pWeight;
    Spinner pTeam, pYear, pPosition;
    String ID;
    FirebaseUser user;
    FirebaseDatabase database;
    private static final int PICK_IMAGE = 100;
    Uri imageUri;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        done = (Button) findViewById(R.id.doneBtn);
        done.setOnClickListener(this);
        pName = (EditText) findViewById(R.id.profName);
        pNum = (EditText) findViewById(R.id.profNum);
        pPhone = (EditText) findViewById(R.id.profPhone);
        pHeight = (EditText) findViewById(R.id.profHeight);
        pWeight = (EditText) findViewById(R.id.profWeight);
        pTeam = (Spinner) findViewById(R.id.teamSpinner);
        pYear = (Spinner) findViewById(R.id.yearSpinner);
        pPosition = (Spinner) findViewById(R.id.positionSpinner);
        user = FirebaseAuth.getInstance().getCurrentUser();
        database = FirebaseDatabase.getInstance();
        ID = user.getUid();

        database.getReference("users/" + ID + "/PlayerInformation/Name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                pName.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        database.getReference("users/" + ID + "/PlayerInformation/Number").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                pNum.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        database.getReference("users/" + ID + "/PlayerInformation/Phone").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                pPhone.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        database.getReference("users/" + ID + "/PlayerInformation/Height").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                pHeight.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        database.getReference("users/" + ID + "/PlayerInformation/Weight").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                pWeight.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        database.getReference("users/" + ID + "/PlayerInformation/Team").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                String[] pTeamArr = getResources().getStringArray(R.array.team_arrays);
                for (int j = 0; j < pTeamArr.length; j++) {
                    if (value.equals(pTeamArr[j])) {
                        pTeam.setSelection(j);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        database.getReference("users/" + ID + "/PlayerInformation/Year").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                String[] pYearArr = getResources().getStringArray(R.array.year_arrays);
                for (int j = 0; j < pYearArr.length; j++) {
                    if (value.equals(pYearArr[j])) {
                        pYear.setSelection(j);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        database.getReference("users/" + ID + "/PlayerInformation/Position").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                String[] pPosArr = getResources().getStringArray(R.array.position_arrays);
                for (int j = 0; j < pPosArr.length; j++) {
                    if (value.equals(pPosArr[j])) {
                        pPosition.setSelection(j);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
    //Edited by Aaron Madaris 11/2/2017

    /**Not sure why this isn't opening the gallery
    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageUri = data.getData();
        }
    }**/


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
            v.setTeam(pTeam.getSelectedItem().toString());
            v.setYear(pYear.getSelectedItem().toString());
            v.setPosition(pPosition.getSelectedItem().toString());
            Intent home = new Intent(getApplicationContext(), Home.class);
            startActivity(home);
        } /**else if (i == R.id.picBtn) {
            openGallery();

        }**/
    }








}
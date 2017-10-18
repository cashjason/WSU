package com.example.cashj.wsu;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by cashj on 10/16/2017.
 */

public class Values {
    private String name;
    private String number;
    private String phone;
    private String team;
    private String year;
    private String position;
    private String Height;
    private String Weight;
    // Modified by Aaron 10/17/2017
    String Date;
    String ID;

    //get name from db
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    // Get instance of database
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    // Get reference to database
    final DatabaseReference myRef = database.getReferenceFromUrl("https://wsu-baseball.firebaseio.com");


    public String getName() {
        ID =  user.getUid();
        database.getReference("users/"+ID+"/Player Information/Name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                name = dataSnapshot.getValue(String.class);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return name;
    }

    public void setName(String n) {
        //Set name in db
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        ID =  user.getUid();
        myRef.child("users").child(ID).child("PlayerInformation").child("Name").setValue(n.toString());
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getHeight() {
        return Height;
    }

    public void setHeight(String height) {
        Height = height;
    }

    public String getWeight() {
        return Weight;
    }

    public void setWeight(String weight) {
        Weight = weight;
    }
}

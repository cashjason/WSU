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

    Values(){
        name = getName();
        System.out.println(name + "Constructor is +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        database.getReference("users/"+ID+"/PlayerInformation/Name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                name = dataSnapshot.getValue(String.class);
//                System.out.println(name + "Constructor is +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}

        });
    }

// users  "users/"+ID+"/Player Information/Name"

    public String getName() {

        database.getReference("users/"+ID+"/PlayerInformation/Name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                name = dataSnapshot.getValue(String.class);
                System.out.println(name + "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}

        });
        System.out.println(name + " is Return name++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        return name;
    }

    public void setName(String n) {
        //Set name in db
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        ID =  user.getUid();
        myRef.child("users").child(ID).child("PlayerInformation").child("Name").setValue(n.toString());
    }

    public String getNumber() {
        ID =  user.getUid();
        database.getReference("users/"+ID+"/PlayerInformation/Number").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                number = dataSnapshot.getValue(String.class);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return number;
    }

    public void setNumber(String number) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        ID =  user.getUid();
        myRef.child("users").child(ID).child("PlayerInformation").child("Number").setValue(number.toString());

        this.number = number;
    }

    public String getPhone() {
        ID =  user.getUid();
        database.getReference("users/"+ID+"/PlayerInformation/Phone").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                phone = dataSnapshot.getValue(String.class);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return phone;
    }

    public void setPhone(String phone) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        ID =  user.getUid();
        myRef.child("users").child(ID).child("PlayerInformation").child("Phone").setValue(phone.toString());
        this.phone = phone;
    }

    public String getTeam() {
        ID =  user.getUid();
        database.getReference("users/"+ID+"/PlayerInformation/Team").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                team = dataSnapshot.getValue(String.class);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return team;
    }

    public void setTeam(String team) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        ID =  user.getUid();
        myRef.child("users").child(ID).child("PlayerInformation").child("Team").setValue(team.toString());

        this.team = team;
    }

    public String getYear() {

        ID =  user.getUid();
        database.getReference("users/"+ID+"/PlayerInformation/Year").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                year = dataSnapshot.getValue(String.class);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return year;
    }

    public void setYear(String year) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        ID =  user.getUid();
        myRef.child("users").child(ID).child("PlayerInformation").child("Year").setValue(year.toString());

        this.year = year;
    }

    public String getPosition() {
        ID =  user.getUid();
        database.getReference("users/"+ID+"/Player Information/Position").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                position = dataSnapshot.getValue(String.class);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return position;
    }

    public void setPosition(String position) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        ID =  user.getUid();
        myRef.child("users").child(ID).child("PlayerInformation").child("Position").setValue(position.toString());

        this.position = position;
    }

    public String getHeight() {
        ID =  user.getUid();
        database.getReference("users/"+ID+"/PlayerInformation/Height").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Height = dataSnapshot.getValue(String.class);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return Height;
    }

    public void setHeight(String height) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        ID =  user.getUid();
        myRef.child("users").child(ID).child("PlayerInformation").child("Height").setValue(height.toString());

        Height = height;
    }

    public String getWeight() {
        ID =  user.getUid();
        database.getReference("users/"+ID+"/PlayerInformation/Weight").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Weight = dataSnapshot.getValue(String.class);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return Weight;
    }

    public void setWeight(String weight) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        ID =  user.getUid();
        myRef.child("users").child(ID).child("PlayerInformation").child("Weight").setValue(weight.toString());

        Weight = weight;
    }
}

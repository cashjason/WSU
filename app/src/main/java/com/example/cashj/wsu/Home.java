package com.example.cashj.wsu;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

import java.io.File;
import java.io.IOException;

import static com.example.cashj.wsu.R.id.imageView;

/**
 * Created by cashj on 9/15/2017.
 */

public class Home extends AppCompatActivity implements View.OnClickListener {

    TextView pName;
    TextView pInfo;
    TextView pNumber;
    TextView pPosition;
    TextView pYear;
    ImageView profilePic;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        Button evals = (Button) findViewById(R.id.evaluationsBtn);
        evals.setOnClickListener(this);

        Button history = (Button) findViewById(R.id.historybtn);
        history.setOnClickListener(this);

        Button signOutBtn = (Button) findViewById(R.id.signOut);
        signOutBtn.setOnClickListener(this);

        Button profBtn = (Button) findViewById(R.id.profileBtn);
        profBtn.setOnClickListener(this);

        String ID;
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        ID =  user.getUid();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        pName = (TextView) findViewById(R.id.pName);
        pInfo = (TextView) findViewById(R.id.pInfo);
        pNumber = (TextView) findViewById(R.id.pNumber);
        pPosition = (TextView) findViewById(R.id.pPosition);
        pYear = (TextView) findViewById(R.id.pYear);
        database.getReference("users/"+ID+"/PlayerInformation/Name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                pName.setText(value);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        database.getReference("users/"+ID+"/PlayerInformation/Phone").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                pInfo.setText(value);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        database.getReference("users/"+ID+"/PlayerInformation/Number").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                pNumber.setText(value);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        database.getReference("users/"+ID+"/PlayerInformation/Position").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                pPosition.setText(value);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        database.getReference("users/"+ID+"/PlayerInformation/Year").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                pYear.setText(value);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


// THIS IS HOW AN UPLOADED IMAGE CAN BE DISPLAYED
// WHENEVER THEIR PROFILE PICTURE IS CHANGED, COMPARE TO CURRENT AND CHANGE IF NECESSARY
// WHEN CHANGED, DOWNLOAD LOCALLY TO THE APPLICATION
//        mAuth = FirebaseAuth.getInstance();
//        mStorageRef = FirebaseStorage.getInstance().getReference();
//        mStorageRef.child("profilePictures/none.jpg").getBytes(Long.MAX_VALUE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
//            @Override
//            public void onSuccess(byte[] bytes) {
//                // Use the bytes to display the image
//                //profilePic = bytes.
//                Bitmap bitmapFromByteArray = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
//                profilePic.setImageBitmap(bitmapFromByteArray);
//                Toast.makeText(Home.this, "IMAGE SUCCESS", Toast.LENGTH_SHORT).show();
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception exception) {
//                Toast.makeText(Home.this, "IMAGE FAILURE", Toast.LENGTH_SHORT).show();
//                // Handle any errors
//            }
//        });

    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.evaluationsBtn) {
            Intent act = new Intent(getApplicationContext(), Evaluations.class);
            startActivity(act);
        }
        if (i == R.id.historybtn) {
            Intent act = new Intent(getApplicationContext(), History.class);
            startActivity(act);
        }
        if (i == R.id.profileBtn) {
            Intent act = new Intent(getApplicationContext(), Profile.class);
            startActivity(act);
        }
        if (i == R.id.signOut) {
            FirebaseAuth.getInstance().signOut();
            Toast.makeText(this, "Signed Out", Toast.LENGTH_SHORT).show();
            Intent act = new Intent(getApplicationContext(), Login.class);
            startActivity(act);
        }
    }
}
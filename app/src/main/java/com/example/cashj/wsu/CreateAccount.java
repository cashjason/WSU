package com.example.cashj.wsu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by cashj on 9/20/2017.
 */

public class CreateAccount extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;
    EditText email;
    EditText emailVerify;
    EditText password;
    ProgressBar progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);

        Button create = (Button) findViewById(R.id.createAccount);
        create.setOnClickListener(this);

        email = (EditText) findViewById(R.id.emailEntry);
        emailVerify = (EditText) findViewById(R.id.emailVerify);
        password = (EditText) findViewById(R.id.passEntry);
        mAuth = FirebaseAuth.getInstance();
        progress = (ProgressBar) findViewById(R.id.progress);
        progress.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.createAccount) {
            String em = email.getText().toString();
            String emv = emailVerify.getText().toString();
            String pass = password.getText().toString();
            progress.setVisibility(View.VISIBLE);
            if (em.equals(emv)){
                try {
                    mAuth.createUserWithEmailAndPassword(em, pass)
                            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(CreateAccount.this, "Account Created",
                                                Toast.LENGTH_SHORT).show();
                                        progress.setVisibility(View.GONE);
                                    }else{
                                        Toast.makeText(CreateAccount.this, "Account Not Created",
                                                Toast.LENGTH_SHORT).show();
                                        progress.setVisibility(View.GONE);
                                    }
                                }
                            });
                }catch(Exception e){
                    Toast.makeText(CreateAccount.this, "Please enter your information",
                            Toast.LENGTH_LONG).show();
                    progress.setVisibility(View.GONE);
                }
            }else{
                Toast.makeText(CreateAccount.this, "Emails do not match",
                        Toast.LENGTH_LONG).show();
                progress.setVisibility(View.GONE);
            }

        }
    }
}

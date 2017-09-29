package com.example.cashj.wsu;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by cashj on 9/25/2017.
 */

public class ForgotPassword extends AppCompatActivity implements View.OnClickListener {

    Button verify;
    EditText email;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password);

        email = (EditText) findViewById(R.id.emailVerifyEntry);
        verify = (Button) findViewById(R.id.verifyButton);
        verify.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        //Send verification email
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String emailAddress = email.getText().toString();

        auth.sendPasswordResetEmail(emailAddress)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ForgotPassword.this, "Email Sent!", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(ForgotPassword.this, "No account with the email address entered!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
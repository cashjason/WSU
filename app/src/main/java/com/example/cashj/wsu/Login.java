package com.example.cashj.wsu;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;


public class Login extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    EditText email;
    EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Button loginBtn = (Button) findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(this);

        Button create = (Button) findViewById(R.id.createAccount);
        create.setOnClickListener(this);

        TextView forgotPass = (TextView) findViewById(R.id.fPass);
        forgotPass.setOnClickListener(this);

        email = (EditText) findViewById(R.id.emailEntry);
        password = (EditText) findViewById(R.id.passEntry);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // User is signed in
            Intent home = new Intent(getApplicationContext(), Home.class);
            startActivity(home);
        } else {
            // No user is signed in
        }
    }

    @Override
    public void onClick(View v) {
        String em = email.getText().toString();
        String pass = password.getText().toString();
        int i = v.getId();
        if (i == R.id.loginBtn) {
            try {
                mAuth.signInWithEmailAndPassword(em, pass)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent home = new Intent(getApplicationContext(), Home.class);
                            startActivity(home);
                        } else {
                            Toast.makeText(Login.this, "Invalid Login",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }catch(Exception e){
                Toast.makeText(Login.this, "Please enter your information",
                        Toast.LENGTH_LONG).show();
            }
        }
        if (i == R.id.createAccount){
            Intent account = new Intent(getApplicationContext(), CreateAccount.class);
            startActivity(account);
        }
        if (i == R.id.fPass){
            Intent act = new Intent(getApplicationContext(), ForgotPassword.class);
            startActivity(act);
        }
    }
}

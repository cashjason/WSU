package com.example.cashj.wsu;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Login extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;
    ProgressBar progress;
    EditText email, password;
    Button login, create;
    TextView forgotPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        login = (Button) findViewById(R.id.loginBtn);
        login.setOnClickListener(this);
        create = (Button) findViewById(R.id.createAccount);
        create.setOnClickListener(this);
        forgotPass = (TextView) findViewById(R.id.fPass);
        forgotPass.setOnClickListener(this);
        email = (EditText) findViewById(R.id.emailEntry);
        password = (EditText) findViewById(R.id.passEntry);
        mAuth = FirebaseAuth.getInstance();
        progress = (ProgressBar) findViewById(R.id.progress);
        progress.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
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
            progress.setVisibility(View.VISIBLE);
            try {
                mAuth.signInWithEmailAndPassword(em, pass)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progress.setVisibility(View.GONE);
                            Intent home = new Intent(getApplicationContext(), Home.class);
                            startActivity(home);
                        } else {
                            Toast.makeText(Login.this, "Invalid Login",
                                    Toast.LENGTH_LONG).show();
                            progress.setVisibility(View.GONE);
                        }
                    }
                });
            }catch(Exception e){
                Toast.makeText(Login.this, "Please enter your information",
                        Toast.LENGTH_LONG).show();
                progress.setVisibility(View.GONE);
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

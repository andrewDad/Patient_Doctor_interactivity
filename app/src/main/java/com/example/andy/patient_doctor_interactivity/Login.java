package com.example.andy.patient_doctor_interactivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "LoginActivity";
    private EditText email, password;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private ProgressDialog loginDialog;
    private Button start, stop, battrey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        start = (Button) findViewById(R.id.Start);
        stop = (Button) findViewById(R.id.Stop);
        battrey = (Button) findViewById(R.id.Betery_Level);

        start.setOnClickListener(this);
        stop.setOnClickListener(this);
        battrey.setOnClickListener(this);


        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        mAuth = FirebaseAuth.getInstance();
        loginDialog = new ProgressDialog(this);
        loginDialog.setMessage("Login..");
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() != null) {
                    startActivity(new Intent(Login.this, homeactivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                    finish();
                }
            }
        };


    }


    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    public void login(View view) {
        loginDialog.show();
        mAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    loginDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                }
                loginDialog.dismiss();
            }
        });
    }

    public void register(View view) {
        startActivity(new Intent(Login.this, RegisterActivity.class));
        finish();
    }

    @Override
    public void onClick(View view) {
        if (view == start) {
            startService(new Intent(this, Musicservice.class));
        } else if (view == stop) {
            stopService(new Intent(this, Musicservice.class));

        }
            Intent in = new Intent(Login.this, BattreyActivity.class);
            startActivity(in);


        }
    }

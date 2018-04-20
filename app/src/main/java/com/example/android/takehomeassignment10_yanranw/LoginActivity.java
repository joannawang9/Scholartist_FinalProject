package com.example.android.takehomeassignment10_yanranw;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText emailEditText;
    EditText passwordEditText;
    Button signInWithGoogle;
    private FirebaseAuth mAuth;
    private String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        signInWithGoogle = findViewById(R.id.sign_in_button);

    }

    public void signUp(View view) {
        email = emailEditText.getText().toString();
        password = passwordEditText.getText().toString();
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    Toast.makeText(LoginActivity.this, task.getException().toString() + "Authentication failed.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, FirebaseAuth.getInstance().getCurrentUser().getEmail() + "signed up successful", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    public void signIn(View view) {
        email = emailEditText.getText().toString();
        password = passwordEditText.getText().toString();
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    Toast.makeText(LoginActivity.this, task.getException().toString() + "Authentication failed.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, FirebaseAuth.getInstance().getCurrentUser().getEmail() + "logged in successful", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    public void googleSignIn(View view) {
        Intent intent = new Intent(this, GoogleSignInActivity.class);
        startActivity(intent);
    }


}

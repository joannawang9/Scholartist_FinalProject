package com.example.android.takehomeassignment10_yanranw;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText userInput;
    TextView displayText;
    private ArrayList<String> textList = new ArrayList<>();
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private DatabaseReference userRef;
    private DatabaseReference songsRef = database.getReference("song");
    private StorageReference mStorageRef;
    private FirebaseAuth.AuthStateListener authListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userInput = findViewById(R.id.user_input);
        displayText = findViewById(R.id.input_display);
        mStorageRef = FirebaseStorage.getInstance().getReference();
        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                } else {
                    userRef = database.getReference(user.getUid());
                    userRef.addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                            textList.add(dataSnapshot.getValue(String.class));
                            displaySongs();
                        }

                        @Override
                        public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                            Toast.makeText(MainActivity.this, dataSnapshot.getValue(String.class) + " has changed", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onChildRemoved(DataSnapshot dataSnapshot) {
                            Toast.makeText(MainActivity.this, dataSnapshot.getValue(String.class) + " is removed", Toast.LENGTH_SHORT).show();

                        }

                        @Override
                        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        auth.removeAuthStateListener(authListener);
    }

    private void displaySongs() {
        String text = "";
        for (String s : textList)
            text += s + "\n";
        displayText.setText(text);
    }
    public void logOut(View view){
        auth.signOut();
        textList.clear();
        displayText.setText("");
        userInput.setText("");
    }
    public void sendText(View view){
        FirebaseUser user = auth.getCurrentUser();
        userRef = database.getReference(user.getUid());
        String userText = userInput.getText().toString();
        userRef.push().setValue(userText);
    }

}



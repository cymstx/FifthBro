package com.example.fifthbro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Locale;

public class ActivityRegisterUser extends AppCompatActivity implements View.OnClickListener{

    // declare variable
    private EditText editTextName, editTextPassword, editTextClub, editTextEmail;
    private TextView textViewBanner;
    private Button buttonRegister;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    private DatabaseReference users;
    private DatabaseReference clubs;
    private DatabaseReference allClubs;

    private TextView showSnapshot;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        // initialize
        textViewBanner = (TextView) findViewById(R.id.banner);
        textViewBanner.setOnClickListener(this);

        editTextClub = (EditText) findViewById(R.id.club);
        editTextEmail = (EditText) findViewById(R.id.email);
        editTextName = (EditText) findViewById(R.id.name);
        editTextPassword = (EditText) findViewById(R.id.password);

        buttonRegister = (Button) findViewById(R.id.registerUser);
        buttonRegister.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        users = FirebaseDatabase.getInstance().getReference("Users");
        clubs = FirebaseDatabase.getInstance().getReference("Clubs");
        allClubs = FirebaseDatabase.getInstance().getReference("All Clubs");

        showSnapshot = (TextView) findViewById(R.id.showSnapshot);

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.registerUser:
                registerUser();
                break;
            case R.id.banner:
                // go back to home page
                startActivity(new Intent(ActivityRegisterUser.this, MainActivity.class));
                break;
        }

    }

    private void registerUser() {
        // get inputs
        String name = editTextName.getText().toString().trim();
        String club = editTextClub.getText().toString().trim().toLowerCase().replaceAll("\\s", "");
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();


        // validation
        if (club.isEmpty()) {
            editTextClub.setError("Please enter club");
            editTextClub.requestFocus();
            return;
        }
        if (name.isEmpty()) {
            editTextName.setError("Please enter name");
            editTextName.requestFocus();
            return;
        }
        if (email.isEmpty()) {
            editTextEmail.setError("Please enter email");
            editTextEmail.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            editTextPassword.setError("Please enter password");
            editTextPassword.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Please enter correct email format");
            editTextEmail.requestFocus();
            return;
        }
        if (password.length() < 6) {
            editTextPassword.setError("Please enter password minimum of 6 characters");
            editTextPassword.requestFocus();
            return;
        }

        // if all validated, store all information into User and Club object,
        // then store objects into Firebase
        //progressBar.setVisibility(View.VISIBLE);


        ArrayList<String> clubList = new ArrayList<>();

        // create mAuth
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    // if successfully created, create user class and store
                    UserClass userClass = new UserClass(club, name, email);
                    // store in firebase
                    users.child(mAuth.getCurrentUser().getUid()).setValue(userClass).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                // store all existing clubs into club list
                                clubs.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        for(DataSnapshot tempClub : snapshot.getChildren()){
                                            clubList.add(tempClub.getKey());
                                        }

                                        if (clubList.contains(club)){
                                            //showSnapshot.setText(clubList.get(0));
                                            // if club already exist, edit club users
                                            clubs.child(club).child("users").child(userClass.name).setValue(userClass);
                                        }
                                        else{
                                            // create new club class
                                            ClubClass clubClass = new ClubClass(club);

                                            clubs.child(club).child("users").child(userClass.name).setValue(userClass);
                                            clubs.child(club).setValue(clubClass).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if(task.isSuccessful()){
                                                        Toast.makeText(ActivityRegisterUser.this,"YAY", Toast.LENGTH_LONG).show();
                                                    }
                                                    else{
                                                        // store club class not successful
                                                        Toast.makeText(ActivityRegisterUser.this,"NAY", Toast.LENGTH_LONG).show();
                                                    }
                                                }
                                            });
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {
                                        Toast.makeText(ActivityRegisterUser.this, "error", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                            else{
                                // store user class not successful
                                Toast.makeText(ActivityRegisterUser.this, "failed to store user", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
                else{
                    // create mauth object not successful
                    Toast.makeText(ActivityRegisterUser.this,"failed to mauth", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
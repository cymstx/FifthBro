package com.example.testbro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Locale;

public class ActivityLoggedIn extends AppCompatActivity implements View.OnClickListener{

    // declaration
    private Button buttonInventory, buttonBorrow, buttonBookings;
    private FirebaseAuth mAuth;
    private FirebaseDatabase db;
    private String currentUserID;
    private DatabaseReference referenceUsers;
    private UserClass userClass;
    private ProgressBar progressBar;
    TextView textViewClub;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);

        // initialize
        buttonInventory = (Button) findViewById(R.id.inventory);
        buttonInventory.setOnClickListener(this);

        buttonBorrow = (Button) findViewById(R.id.borrow);
        buttonBorrow.setOnClickListener(this);

        buttonBookings = (Button) findViewById(R.id.bookings);
        buttonBookings.setOnClickListener(this);

        progressBar = findViewById(R.id.progressBar);

        final TextView textViewName = (TextView) findViewById(R.id.displayName);
        textViewClub = (TextView) findViewById(R.id.displayClub);

        mAuth = FirebaseAuth.getInstance();
        currentUserID = mAuth.getCurrentUser().getUid();
        db = FirebaseDatabase.getInstance();
        referenceUsers = db.getReference("Users");

        buttonBookings.setClickable(false);
        buttonInventory.setClickable(false);
        buttonBorrow.setClickable(false);



        // display current user name and club in activity, get user class
        referenceUsers.child(currentUserID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userClass = snapshot.getValue(UserClass.class);
                String currClub = userClass.getClubName();
                String name = userClass.getName();
                //String finalName = name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
                if(userClass!=null){
                    textViewClub.setText(currClub);
                    textViewName.setText(name);

                    buttonBookings.setClickable(true);
                    buttonInventory.setClickable(true);
                    buttonBorrow.setClickable(true);

                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ActivityLoggedIn.this,R.string.error_message, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.inventory:
                // pass database reference for current club to next activity (clubID)
                Intent i = new Intent(ActivityLoggedIn.this, ActivityShowInventory.class);
                i.putExtra("club", textViewClub.getText().toString().trim().toLowerCase().replaceAll("\\s", ""));
                startActivity(i);
                break;
            case R.id.borrow:
                // pass current club name to next activity
                Intent i2 = new Intent(ActivityLoggedIn.this, ActivityBorrow.class);
                i2.putExtra("currentClub", textViewClub.getText().toString());
                startActivity(i2);
                break;
            case R.id.bookings:
                // go to next activity, show all bookings of current user
                // pass over the current user id for reference
                startActivity(new Intent(ActivityLoggedIn.this, ActivityDisplayBookings.class));
                break;
        }
    }
}
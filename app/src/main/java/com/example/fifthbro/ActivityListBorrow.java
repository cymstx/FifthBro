package com.example.fifthbro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ActivityListBorrow extends AppCompatActivity implements View.OnClickListener{

    private Button list, borrow;
    private FirebaseAuth mAuth;
    private DatabaseReference referenceUsers;
    private FirebaseUser user;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_borrow);

        final TextView textViewName = (TextView) findViewById(R.id.nameInfo);
        final TextView textViewClub = (TextView) findViewById(R.id.clubInfo);

        list = (Button) findViewById(R.id.listItems);
        list.setOnClickListener(this);

        borrow = (Button) findViewById(R.id.borrowItems);
        borrow.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        referenceUsers = FirebaseDatabase.getInstance().getReference("Users");
        user = mAuth.getCurrentUser();
        userID = user.getUid();

        referenceUsers.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserClass userProfile = snapshot.getValue(UserClass.class);
                if (userProfile != null){
                    String name = userProfile.name;
                    String club = userProfile.club;

                    textViewName.setText(name);
                    textViewClub.setText(club);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ActivityListBorrow.this, "Something wrong happened", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.listItems:
                startActivity(new Intent(ActivityListBorrow.this, ActivityListItems.class));
                break;
            case R.id.borrowItems:
                break;
        }
    }
}
package com.example.fifthbro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ActivityListItems extends AppCompatActivity implements View.OnClickListener{

    private TextView textViewItemName, banner;
    private Button listItem;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private String userID;
    private DatabaseReference referenceUsers, referenceClubs;
    private ClubClass clubClass;
    private TextView showClub, showItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_items);

        banner = (TextView) findViewById(R.id.banner);
        banner.setOnClickListener(this);

        textViewItemName = (TextView) findViewById(R.id.itemName);

        listItem = (Button) findViewById(R.id.listItem);
        listItem.setOnClickListener(this);

        showClub = (TextView) findViewById(R.id.showClub);
        showItem = (TextView) findViewById(R.id.showItem);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        userID = user.getUid();
        referenceUsers = FirebaseDatabase.getInstance().getReference("Users");
        referenceClubs = FirebaseDatabase.getInstance().getReference("Clubs");

        referenceUsers.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserClass userClass = snapshot.getValue(UserClass.class);
                if (userClass != null){
                    String clubName = userClass.club;
                    //clubClass = ClubClass.clubList.get(clubName);

                    showClub.setText(clubName);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ActivityListItems.this, "cannot get club class", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.banner:
                startActivity(new Intent(ActivityListItems.this, ActivityListBorrow.class));
                break;
            case R.id.listItem:
                listItems();
                break;
        }
    }

    private void listItems() {
        // get item name as string
        String itemName = textViewItemName.getText().toString().trim();
        // create items object with item name
        ItemClass item = new ItemClass(itemName);
        // add created item to club inventory
        clubClass.inventory.put(itemName, item);
        // update inventory to firebase
        referenceClubs.child(clubClass.clubName).setValue(clubClass.inventory).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(ActivityListItems.this, "Successfully listed item", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(ActivityListItems.this, "List item not successful", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
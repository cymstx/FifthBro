package com.example.testbro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ActivityDisplayBookings extends AppCompatActivity implements MyBookingsAdapter.OnNoteListener {

    // declaration
    RecyclerView recyclerView;
    MyBookingsAdapter myBookingsAdapter;
    DatabaseReference referenceCurrentUser, referenceBookings;
    ArrayList<BookingObj> myBookings;
    ArrayList<String> myBookingIDs;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_bookings);

        // initialize
        recyclerView = findViewById(R.id.recyclerViewBookings);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        myBookings = new ArrayList<>();
        myBookingIDs = new ArrayList<>();

        myBookingsAdapter = new MyBookingsAdapter(this, myBookings, this);
        recyclerView.setAdapter(myBookingsAdapter);

        // set references
        userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        referenceCurrentUser = FirebaseDatabase.getInstance().getReference("Users").child(userID);
        referenceBookings = FirebaseDatabase.getInstance().getReference("Bookings");

        // get list of bookingIDs from current user, store into myBookingIDs
        referenceCurrentUser.child("bookings").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot bookingIDs : snapshot.getChildren()){
                    myBookingIDs.add(bookingIDs.getValue().toString());
                }

                referenceBookings.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot bookingIDs : snapshot.getChildren()){
                            if(myBookingIDs.contains(bookingIDs.getKey())){
                                BookingObj bookingObj = bookingIDs.getValue(BookingObj.class);
                                myBookings.add(bookingObj);
                            }
                        }
                        myBookingsAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }// myBookingIDs cannot leave here

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public void onNoteClick(int position) {
        BookingObj bookingObj = myBookings.get(position);
        Intent i = new Intent(ActivityDisplayBookings.this, ActivityItemCheckOut.class);
        i.putExtra("BookingObj", bookingObj);
        startActivity(i);
    }
}
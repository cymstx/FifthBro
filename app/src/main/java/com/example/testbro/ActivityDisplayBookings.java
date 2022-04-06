package com.example.testbro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ActivityDisplayBookings extends AppCompatActivity {

    // declaration
    RecyclerView recyclerView;
    MyBookingsAdapter myBookingsAdapter;
    DatabaseReference referenceCurrentUser;
    ArrayList<BookingObj> myBookings;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_bookings);

        // initialize
        userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        recyclerView = findViewById(R.id.recyclerViewBorrow);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        referenceCurrentUser = FirebaseDatabase.getInstance().getReference("Users").child(userID).child("bookings");

        myBookings = new ArrayList<>();

        myBookingsAdapter = new MyBookingsAdapter(this, myBookings);
        recyclerView.setAdapter(myBookingsAdapter);


        referenceCurrentUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                myBookings.clear();
                myBookingsAdapter.notifyDataSetChanged();

                for(DataSnapshot bookings : snapshot.getChildren()){
                    BookingObj bookingObj = bookings.getValue(BookingObj.class);
                    myBookings.add(bookingObj);
                }
                myBookingsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
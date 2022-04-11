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

public class ActivityShowInventoryLog extends AppCompatActivity {

    RecyclerView recyclerView;
    MyInventoryAdapter myInventoryAdapter;
    ItemClass item;
    Bundle extras;
    ArrayList<String> itemLog;
    DatabaseReference referenceItems;
    ArrayList<BookingObj> bookings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_inventory_log);

        recyclerView = findViewById(R.id.recyclerViewLog);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        extras = getIntent().getExtras();
        if(extras != null) {
            item = (ItemClass) extras.get("item");
        }

        itemLog = item.getLog();

        for (String itemId : itemLog) {
            referenceItems = FirebaseDatabase.getInstance().getReference("Bookings").child(itemId);
            referenceItems.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    bookings.clear();
                    myInventoryAdapter.notifyDataSetChanged();

                    for(DataSnapshot booking : snapshot.getChildren()) {
                        BookingObj bookingObj = booking.getValue(BookingObj.class);
                        bookings.add(bookingObj);
                    }
                    myInventoryAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        myInventoryAdapter = new MyInventoryAdapter(this, bookings);
        recyclerView.setAdapter(myInventoryAdapter);
    }
}

package com.amati.hungerbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DeliveryActivity extends AppCompatActivity {
    RecyclerView rv;
    DatabaseReference databaseReference;
    private Context mContext;
    private Activity mActivity;
    private ArrayList<donateInfo> donateList;
    private deliveryAdapter deliveryAdapter1 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);

        mActivity = DeliveryActivity.this;
        mContext = getApplicationContext();
        FirebaseApp.initializeApp(getApplicationContext());
        rv = findViewById(R.id.rvDelivery);
        rv.setHasFixedSize(true);

        rv.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        rv.setNestedScrollingEnabled(false);

        donateList = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference("donates");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                donateList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    donateInfo donateModel = dataSnapshot.getValue(donateInfo.class);
                    donateList.add(donateModel);
                }
                deliveryAdapter1 = new deliveryAdapter(mContext,mActivity, (ArrayList<donateInfo>) donateList);

                rv.setAdapter(deliveryAdapter1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),"Error:" + error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}
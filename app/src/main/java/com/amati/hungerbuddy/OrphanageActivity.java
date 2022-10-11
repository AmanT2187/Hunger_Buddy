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

public class OrphanageActivity extends AppCompatActivity {
    RecyclerView rv;
    DatabaseReference databaseReference;
    private Context mContext;
    private Activity mActivity;
    private ArrayList<orphanageInfo> orphanageInfos;
    private orphanageAdapter orphanageAdapters = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orphanage);

        mActivity = OrphanageActivity.this;
        mContext = getApplicationContext();
        FirebaseApp.initializeApp(getApplicationContext());
        rv = findViewById(R.id.rvOrphan);
        rv.setHasFixedSize(true);

        rv.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        rv.setNestedScrollingEnabled(false);

        orphanageInfos = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference("Orphange");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                orphanageInfos.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    orphanageInfo opModel = dataSnapshot.getValue(orphanageInfo.class);
                    orphanageInfos.add(opModel);
                }
                orphanageAdapters = new orphanageAdapter(mContext,mActivity, (ArrayList<orphanageInfo>) orphanageInfos);

                rv.setAdapter(orphanageAdapters);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),"Error:" + error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}
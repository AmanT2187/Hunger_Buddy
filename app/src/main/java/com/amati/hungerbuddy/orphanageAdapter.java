package com.amati.hungerbuddy;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Locale;

public class orphanageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private Activity mActivity;
    private ArrayList<orphanageInfo> mContentList;

    public orphanageAdapter(Context mContext, Activity mActivity, ArrayList<orphanageInfo> mContentList) {
        this.mContext = mContext;
        this.mActivity = mActivity;
        this.mContentList = mContentList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.orphanage_list, parent, false);
        return new ViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder holder1 = (ViewHolder) holder;
        final orphanageInfo model = mContentList.get(position);
        // setting data over views
        String imgUrl = model.getOpUrl();
        if (imgUrl != null && !imgUrl.isEmpty()) {
            Glide.with(mContext)
                    .load(imgUrl)
                    .into(holder1.imgView);
        }
        holder1.txt1.setText(model.getOpName());
        holder1.txt2.setText(model.getOpLocation());
//        holder1.txt3.setText(model.getFoodQuant());

        holder1.btnLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = model.getOpGps();
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//                intent.setPackage("com.google.android.apps.maps");
                try
                {
                    mActivity.startActivity(intent);
                }
                catch(ActivityNotFoundException ex)
                {
                    try
                    {
                        Intent unrestrictedIntent = new Intent(Intent.ACTION_VIEW, uri);
                        mActivity.startActivity(unrestrictedIntent);
                    }
                    catch(ActivityNotFoundException innerEx)
                    {
                        Toast.makeText(mContext.getApplicationContext(), "Please install a maps application", Toast.LENGTH_LONG).show();
                    }
                }

//                Toast.makeText(mContext, "Thanks to take responsibility", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mContentList.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgView;

        private ImageButton btnLoc;
        private TextView txt1, txt2;

//        private Button btn1;
        public ViewHolder(View itemView, int viewType) {
            super(itemView);
            // Find all views ids
            imgView = itemView.findViewById(R.id.imageView3);
            txt1 = itemView.findViewById(R.id.textView2);
            txt2 = itemView.findViewById(R.id.textView4);
//            txt3 = itemView.findViewById(R.id.txtItem);


            btnLoc = itemView.findViewById(R.id.btnLoc);
        }
    }
}

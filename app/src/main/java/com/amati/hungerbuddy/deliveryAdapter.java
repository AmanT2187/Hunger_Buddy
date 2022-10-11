package com.amati.hungerbuddy;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class deliveryAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private Activity mActivity;
    private ArrayList<donateInfo> mContentList;

    public deliveryAdapter(Context mContext, Activity mActivity, ArrayList<donateInfo> mContentList) {
        this.mContext = mContext;
        this.mActivity = mActivity;
        this.mContentList = mContentList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_list, parent, false);
        return new ViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder holder1 = (ViewHolder) holder;
        final donateInfo model = mContentList.get(position);
        // setting data over views
        String imgUrl = model.getImgUrl();
        if (imgUrl != null && !imgUrl.isEmpty()) {
            Glide.with(mContext)
                    .load(imgUrl)
                    .into(holder1.imgView);
        }
        holder1.txt1.setText(model.getPersonName());
        holder1.txt2.setText(model.getPersonNumber());
        holder1.txt3.setText(model.getFoodQuant());

        holder1.btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Thanks to take responsibility", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mContentList.size();
    }
        public static class ViewHolder extends RecyclerView.ViewHolder {
            private ImageView imgView;

            private TextView txt1, txt2, txt3;

            private Button btn1;
            public ViewHolder(View itemView, int viewType) {
                super(itemView);
                // Find all views ids
                imgView = (ImageView) itemView.findViewById(R.id.imgRestro);
                txt1 = itemView.findViewById(R.id.txtRestro);
                txt2 = itemView.findViewById(R.id.txtLocation);
                txt3 = itemView.findViewById(R.id.txtItem);


                btn1 = itemView.findViewById(R.id.btnAccept);
            }
        }
}

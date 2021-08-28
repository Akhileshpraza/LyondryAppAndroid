package com.lyondry.lyondry.Adapters;

import android.content.Context;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.lyondry.lyondry.Modals.Services_data;
import com.lyondry.lyondry.Modals.Services_responce_modal;
import com.lyondry.lyondry.R;

import java.util.List;

public class servicesAdapter extends RecyclerView.Adapter<servicesAdapter.ViewHolder> {


    List<Services_data> servicesData;
    Context context;

    public servicesAdapter(List<Services_data> servicesData, Context context) {
        this.servicesData = servicesData;
        this.context = context;
    }

    public servicesAdapter(List<Services_data> services_data) {

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.services_item_layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Services_data data = servicesData.get(position);
        holder.P_Name.setText(data.getServiceName());
        String img = data.getServiceIcon();
        Log.i("imageByteArray","*******img**********"+img);
        byte[] imageByteArray = Base64.decode(img, Base64.DEFAULT);
        //Log.i("imageByteArray","*****************"+imageByteArray);
        Glide.with(holder.itemView.getContext()).load(imageByteArray).into(holder.p_img);

    }

    @Override
    public int getItemCount() {
        return servicesData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

//        CircleImageView p_img;
        ImageView p_img;
        TextView P_Name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            p_img = itemView.findViewById(R.id.product_image);
            P_Name = itemView.findViewById(R.id.product_name);
        }
    }
}

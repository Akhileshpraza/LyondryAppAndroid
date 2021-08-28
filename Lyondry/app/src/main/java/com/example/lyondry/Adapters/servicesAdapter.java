package com.lyondry.lyondry.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lyondry.lyondry.Modals.servicesModal;
import com.lyondry.lyondry.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class servicesAdapter extends RecyclerView.Adapter<servicesAdapter.ViewHolder> {

    List<servicesModal> servicesModalList;

    public servicesAdapter(List<servicesModal> servicesModalList) {
        this.servicesModalList = servicesModalList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.services_item_layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        servicesModal servicesModal1  = servicesModalList.get(position);
        holder.P_Name.setText(servicesModal1.getProduct_name());
        holder.p_img.setImageResource(servicesModal1.getProduct_Img());
        holder.p_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"click on item: "+servicesModal1.getProduct_name(), Toast.LENGTH_LONG).show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return servicesModalList.size();
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

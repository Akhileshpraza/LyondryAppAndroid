package com.lyondry.lyondry.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lyondry.lyondry.Modals.SelectStore.SelectedStore_data;
import com.lyondry.lyondry.Modals.Services_data;
import com.lyondry.lyondry.Modals.StoresModal;
import com.lyondry.lyondry.R;

import java.util.List;

public class StoresAdapter extends RecyclerView.Adapter<StoresAdapter.ViewHolder> {

    List<SelectedStore_data> storesModalList;
    Context context;

    public StoresAdapter(List<SelectedStore_data> storesModalList, Context context) {
        this.storesModalList = storesModalList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.stores_fragment_item_layout, parent, false);

        return new StoresAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SelectedStore_data storeData = storesModalList.get(position);
        holder.storeName.setText(storeData.getStoreName());
        holder.storeLocation.setText(storeData.getStoreLocation());
        holder.storeContact = storeData.getStorePhone();
        String phoneNumber =holder.storeContact;
                holder.Btn_contact_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return storesModalList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView storeName,storeLocation;
        Button Btn_contact_now;
        String storeContact;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            storeName = itemView.findViewById(R.id.tv_store_name);
            storeLocation =itemView.findViewById(R.id.tv_store_location);
            Btn_contact_now =itemView.findViewById(R.id.btn_contact_now);

            context = itemView.getContext();
//            Btn_contact_now.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(context,"Contact Now",Toast.LENGTH_SHORT).show();
//
//                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null));
//                    context.startActivity(intent);
//                }
//            });

        }
    }
}

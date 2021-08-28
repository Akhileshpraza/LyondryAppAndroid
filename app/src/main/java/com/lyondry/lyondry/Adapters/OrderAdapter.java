package com.lyondry.lyondry.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.lyondry.lyondry.Activity.OrderDetailsActivity;
import com.lyondry.lyondry.Modals.Order.SelectOrder_data;
import com.lyondry.lyondry.Modals.Order.SelectOrder_respoce_modal;
import com.lyondry.lyondry.R;

import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    List<SelectOrder_data> selectOrder_data;
    Context context;
    String Date,OrderID,Status;
    public OrderAdapter(List<SelectOrder_data> selectOrder_data, Context context) {
        this.selectOrder_data = selectOrder_data;
        this.context = context;
    }

    public void filterList(ArrayList<SelectOrder_data > filteredList) {
        selectOrder_data = new ArrayList<>();
        selectOrder_data.addAll(filteredList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item_layout, parent, false);

        return new OrderAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SelectOrder_data orderModal = selectOrder_data.get(position);

        String PickupRequestId = String.valueOf(orderModal.getPickupRequestId());

        holder.Order_id.setText(PickupRequestId);
        holder.Develiverd.setText(orderModal.getPickupRequestStatus());
        holder.Date.setText(orderModal.getPickupRequestDate());
        holder.Order_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date = orderModal.getPickupRequestDate();
                OrderID = String.valueOf(orderModal.getPickupRequestId());
                Status = orderModal.getPickupRequestStatus();
                Log.i("PickupRequestId", "*******PickupRequestId**********" + PickupRequestId);

                Intent intent = new Intent(context, OrderDetailsActivity.class);
                intent.putExtra("Date", String.valueOf(Date));
                intent.putExtra("OrderID",OrderID);
                intent.putExtra("Status",Status);
                context.startActivity(intent);

            }
        });
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context,OrderDetailsActivity.class);
//                context.startActivity(intent);
//            }
//        });


    }

    @Override
    public int getItemCount() {
        return selectOrder_data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Order_id ,Develiverd,Date;
        CardView Order_card;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Order_id = itemView.findViewById(R.id.tv_order_id1);
            Develiverd = itemView.findViewById(R.id.tv_order_delivered);
            Date =itemView.findViewById(R.id.tv_date);
            Order_card = itemView.findViewById(R.id.order_cardview);
            context = itemView.getContext();

        }
    }
}

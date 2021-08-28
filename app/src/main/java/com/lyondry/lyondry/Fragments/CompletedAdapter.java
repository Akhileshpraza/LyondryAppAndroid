package com.lyondry.lyondry.Fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lyondry.lyondry.Modals.Payment.Invoice_data;
import com.lyondry.lyondry.R;

import java.util.List;

public class CompletedAdapter extends RecyclerView.Adapter<CompletedAdapter.ViewHolder> {

    List<Invoice_data> pendingModalList;
    Context context;

    public CompletedAdapter(List<Invoice_data> pendingModalList, Context context) {
        this.pendingModalList = pendingModalList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.completed_fragment_item_layout, parent, false);

        return new CompletedAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Invoice_data pendingModal = pendingModalList.get(position);
        holder.InvoiceNo.setText(pendingModal.getInvoiceNo());
        holder.Amount.setText("Rs."+String.valueOf(pendingModal.getInvoiceNetAmount()));
        holder.Date.setText(pendingModal.getInvoiceDateString());
    }

    @Override
    public int getItemCount() {
        return pendingModalList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView Date, InvoiceNo,Amount;
        Button Details;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            InvoiceNo = itemView.findViewById(R.id.tv_invoiceno);
            Date = itemView.findViewById(R.id.tv_date);
            Amount = itemView.findViewById(R.id.tv_amount);
            Details = itemView.findViewById(R.id.btn_details);

            context = itemView.getContext();
            Details.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,"Details",Toast.LENGTH_SHORT).show();

                }
            });
        }
    }
}

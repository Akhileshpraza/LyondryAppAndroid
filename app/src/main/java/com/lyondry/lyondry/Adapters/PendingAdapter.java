package com.lyondry.lyondry.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lyondry.lyondry.Activity.PayNowActivity;
import com.lyondry.lyondry.Classes.SharedPrefManager;
import com.lyondry.lyondry.Modals.Payment.Invoice_data;
import com.lyondry.lyondry.R;

import java.util.List;

public class PendingAdapter  extends RecyclerView.Adapter<PendingAdapter.ViewHolder> {

    List<Invoice_data> pendingModalList;
    Context context;
    SharedPrefManager sharedPrefManager;

    public PendingAdapter(List<Invoice_data> pendingModalList, Context context) {
        this.pendingModalList = pendingModalList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pending_fragment_item_layout, parent, false);

        return new PendingAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Invoice_data pendingModal = pendingModalList.get(position);
        holder.InvoiceNo.setText(pendingModal.getInvoiceNo());

        holder.Amount.setText("Rs."+String.valueOf(pendingModal.getInvoiceNetAmount()));
        holder.Date.setText(pendingModal.getInvoiceDateString());
        holder.Paynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String InvoiceNo =pendingModal.getInvoiceNo();
                String InvoiceDateString = pendingModal.getInvoiceDateString();
                String InvoiceTotalAmount = String.valueOf(pendingModal.getInvoiceTotalAmount());
                String InvoiceNetAmount = String.valueOf(pendingModal.getInvoiceNetAmount());
                String InvoicePickupDate = pendingModal.getInvoicePickupDate();
                String InvoiceStoreName = pendingModal.getInvoiceStoreName();
                String InvoiceOrderId = String.valueOf(pendingModal.getInvoiceOrderId());
                String InvoiceCouponAmount = String.valueOf(pendingModal.getInvoiceCouponAmount());
                String InvoicePoints = String.valueOf(pendingModal.getInvoicePoints());
                Intent intent = new Intent(v.getContext(), PayNowActivity.class);

                intent.putExtra("InvoiceNo",InvoiceNo);
                intent.putExtra("InvoiceDateString",InvoiceDateString);
                intent.putExtra("InvoiceTotalAmount",InvoiceTotalAmount);
                intent.putExtra("InvoiceNetAmount",InvoiceNetAmount);
                intent.putExtra("InvoicePickupDate",InvoicePickupDate);
                intent.putExtra("InvoiceStoreName",InvoiceStoreName);
                intent.putExtra("InvoiceOrderId",InvoiceOrderId);
                intent.putExtra("InvoiceCouponAmount",InvoiceCouponAmount);
                intent.putExtra("InvoicePoints",InvoicePoints);
//                sharedPrefManager.saveSelectInvoiceStatus(
//                        pendingModalList.get(position).getInvoiceNo(),
//                        pendingModalList.get(position).getInvoiceDateString(),
//                        String.valueOf(pendingModalList.get(position).getInvoiceTotalAmount()),
//                        String.valueOf(pendingModalList.get(position).getInvoiceNetAmount()),
//                        pendingModalList.get(position).getInvoiceStatus(),
//                        pendingModalList.get(position).getInvoicePickupDate(),
//                        pendingModalList.get(position).getInvoiceStoreName(),
//                        String.valueOf(pendingModalList.get(position).getInvoiceOrderId()),
//                        String.valueOf(pendingModalList.get(position).getInvoiceCouponAmount()),
//                        String.valueOf(pendingModalList.get(position).getInvoicePoints()));
                v.getContext().startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return pendingModalList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Date, InvoiceNo,Amount;
        Button Paynow;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            InvoiceNo = itemView.findViewById(R.id.tv_invoiceno);
            Date = itemView.findViewById(R.id.tv_date);
            Amount = itemView.findViewById(R.id.tv_amount);
            context = itemView.getContext();
            Paynow = itemView.findViewById(R.id.btn_paynow);
            sharedPrefManager = new SharedPrefManager(itemView.getContext());



//            Paynow.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(context, PayNowActivity.class);
//                    context.startActivity(intent);
//
//                }
//            });
        }
    }
}

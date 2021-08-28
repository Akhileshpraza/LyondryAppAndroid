package com.lyondry.lyondry.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lyondry.lyondry.Modals.PurchaseofferModal;
import com.lyondry.lyondry.R;

import java.util.List;

public class PuschaseofferAdapter extends RecyclerView.Adapter<PuschaseofferAdapter.ViewHolder> {

    List<PurchaseofferModal> purchaseofferModalList;
    Context context;

    public PuschaseofferAdapter(List<PurchaseofferModal> purchaseofferModalList, Context context) {
        this.purchaseofferModalList = purchaseofferModalList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.purchase_offer_fragment_item_layout, parent, false);

        return new PuschaseofferAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PurchaseofferModal purchaseofferModal = purchaseofferModalList.get(position);
        holder.Offer.setText(purchaseofferModal.getOffer());
        holder.Discount.setText(purchaseofferModal.getDiscount());
        holder.Upto.setText(purchaseofferModal.getUpto());
    }

    @Override
    public int getItemCount() {
        return purchaseofferModalList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Offer, Discount,Upto;
        Button Details;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Offer = itemView.findViewById(R.id.tv_oftter);
            Discount = itemView.findViewById(R.id.tv_discount);
            Upto =itemView.findViewById(R.id.tv_upto);
            Details = itemView.findViewById(R.id.btn_details1);

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

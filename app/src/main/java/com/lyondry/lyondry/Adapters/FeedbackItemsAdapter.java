package com.lyondry.lyondry.Adapters;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lyondry.lyondry.Activity.Feedback_RegisrationActivity;
import com.lyondry.lyondry.Classes.SharedPrefManager;
import com.lyondry.lyondry.Interface.RecycleViewItemsCheckInterface;
import com.lyondry.lyondry.Modals.InvoiceMaster.GridTran_data_invice;

import com.lyondry.lyondry.Modals.InvoiceMaster.MainInviceMaster_reponce_modal;
import com.lyondry.lyondry.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;

import static android.text.TextUtils.indexOf;

public class FeedbackItemsAdapter extends RecyclerView.Adapter<FeedbackItemsAdapter.ViewHolder> {
    List<GridTran_data_invice> gridTranDataInvices;
    public RecycleViewItemsCheckInterface recycleViewItemsCheckInterface;
    ItemsCheckInterface itemsCheckInterface;
    Context context;


    public FeedbackItemsAdapter(List<GridTran_data_invice> gridTranDataInvices,Context context) {
        this.gridTranDataInvices = gridTranDataInvices;
        this.itemsCheckInterface = itemsCheckInterface;
        this.context = context;

    }

    public FeedbackItemsAdapter(List<GridTran_data_invice> gridTranDataInvices,ItemsCheckInterface itemsCheckInterface,Context context) {
        this.gridTranDataInvices = gridTranDataInvices;
        this.itemsCheckInterface = itemsCheckInterface;
        this.context = context;

    }

//    public FeedbackItemsAdapter(List<GridTran_data_invice> gridTran_data_invice, Callback<MainInviceMaster_reponce_modal> mainInviceMaster_reponce_modalCallback, Context context) {
//        this.gridTranDataInvices = gridTran_data_invice;
//        this.recycleViewItemsCheckInterface = mainInviceMaster_reponce_modalCallback;
//        this.context = context;
//    }


    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feedback_items_layout, parent, false);

        return new FeedbackItemsAdapter.ViewHolder(view,itemsCheckInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        //mainInviceMaster_reponce_modal = mainInviceMaster_reponce_modal1.getInvoiceMaster();
        GridTran_data_invice gridTranDataInvice = gridTranDataInvices.get(position);
        String Itemname = gridTranDataInvice.getInvoiceTranDescription();
        String Itemcode = gridTranDataInvice.getInvoiceTranItemCode();

        int startPosition=indexOf(Itemname,"-",0);
        startPosition=startPosition-1;
        String Name = Itemname.substring(0,startPosition);

        holder.checkBox.setText(Name);

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                   // recycleViewItemsCheckInterface.onItemChecked(position);
                    //Toast.makeText(context, ""+Itemcode, Toast.LENGTH_SHORT).show();
                    //holder.checkBox.setText(Name);
                    itemsCheckInterface.onItemChecked(gridTranDataInvice);
                }
                else {


                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return gridTranDataInvices.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CheckBox checkBox;
        TextView ItemsName;
        ItemsCheckInterface itemsCheckInterface;

        public ViewHolder(@NonNull @NotNull View itemView,ItemsCheckInterface itemsCheckInterface) {
            super(itemView);
            this.itemsCheckInterface = itemsCheckInterface;
            checkBox = itemView.findViewById(R.id.checkitems);

           checkBox.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {

               }
           });


        }

    }

   public interface ItemsCheckInterface {
        void onItemChecked(GridTran_data_invice gridTranDataInvice);


    }

}

package com.lyondry.lyondry.Adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lyondry.lyondry.Classes.SharedPrefManager;
import com.lyondry.lyondry.Modals.GetAddress_data;
import com.lyondry.lyondry.Modals.address.Address;
import com.lyondry.lyondry.Modals.address.CustomerAddressList;
import com.lyondry.lyondry.Modals.address.MainDeleteAddressList;
import com.lyondry.lyondry.R;

import java.util.List;

public class SchedualPickupAdapter extends RecyclerView.Adapter<SchedualPickupAdapter.ViewHolder> {
    List<GetAddress_data> getAddressData;
    Context context;
    int addressId, AddressCustomerId;
    int AddressType;
    SharedPrefManager sharedPrefManager;
    String token, userMobileNumber;
    private int lastSelectedPosition = 0;
    SharedPreferences sp;
    boolean checkPosition = true;
    int firstItems=0;


    public SchedualPickupAdapter(List<GetAddress_data> getAddressData, Context context) {
        this.getAddressData = getAddressData;
        this.context = context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_schedual_pickup_item_layout, parent, false);
        sharedPrefManager = new SharedPrefManager(view.getContext());
        token = sharedPrefManager.getTokenOtp().getHttpResponseHeader();
        userMobileNumber = sharedPrefManager.getUser().getCustomerMobileNo();
        Log.i("Token1", "********************Token1**************************" + token);
        Log.i("mobile", "********************mobile**************************" + userMobileNumber);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        GetAddress_data data = getAddressData.get(position);
        holder.Address.setText(data.getAddressBuildingNoHouseName() + " " + data.getAddressStreet() + " " + data.getAddressLandmark() + " " + data.getAddressCity() + " " + data.getAddressPin());
        AddressType = Integer.parseInt(data.getAddressType());
        AddressCustomerId = data.getAddressCustomerId();
        addressId = data.getAddressId();

        checkPosition = lastSelectedPosition == position;

        holder.radioButton.setChecked(position == lastSelectedPosition);
        Log.e("position", "*****Positions*******" + position);

        if (AddressType == 0) {
            holder.Work.setText("Home Address");

        } else {
            holder.Work.setText("Office Address");
        }
    }

    @Override
    public int getItemCount() {
        return getAddressData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Address, Work;
        RadioButton radioButton;
        LinearLayout linearLayout5;
        RadioGroup radioGroupAddress;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            linearLayout5 = itemView.findViewById(R.id.linearLayout5);
            Address = itemView.findViewById(R.id.address_show);
            Work = itemView.findViewById(R.id.work_offfice);
            radioButton = itemView.findViewById(R.id.radioButton);
            radioGroupAddress = itemView.findViewById(R.id.radioGroupAddress);

            radioButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int copyOfLastCheckedPosition = lastSelectedPosition;
                    Log.e("LastCheckedPosition", "" + copyOfLastCheckedPosition);
                    lastSelectedPosition = getAdapterPosition();

                    Log.e("lastSelectedPosition", "" + lastSelectedPosition);
//                  if(copyOfLastCheckedPosition!=lastSelectedPosition){
                      notifyItemChanged(copyOfLastCheckedPosition);
                      notifyItemChanged(lastSelectedPosition);
//                  }

                    //notifyDataSetChanged();
                }
            });
        }
    }
}

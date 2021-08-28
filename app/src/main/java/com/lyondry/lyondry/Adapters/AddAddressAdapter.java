package com.lyondry.lyondry.Adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lyondry.lyondry.Activity.EditActivity;
import com.lyondry.lyondry.Activity.EditAddress;
import com.lyondry.lyondry.Activity.PickupAddress1Activity;
import com.lyondry.lyondry.Activity.ProfileActivity;
import com.lyondry.lyondry.Classes.SharedPrefManager;
import com.lyondry.lyondry.Modals.GetAddress_data;
import com.lyondry.lyondry.Modals.address.AddressDelete_data;
import com.lyondry.lyondry.Modals.address.AddressDelete_responce_modal;
import com.lyondry.lyondry.Modals.address.CustomerAddressList;
import com.lyondry.lyondry.Modals.address.MainDeleteAddressList;
import com.lyondry.lyondry.R;
import com.lyondry.lyondry.Retrofits.RetrofitClient;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddAddressAdapter extends RecyclerView.Adapter<AddAddressAdapter.ViewHolder> {

    List<GetAddress_data> getAddressData;
    Context context;
    String home, street,landmark,city,pincode,addressDefaults;
    int addressId,customerAddressId;
    int AddressType;
    CustomerAddressList[] customerAddressList = new CustomerAddressList[1];
    MainDeleteAddressList mainDeleteAddressList;
    SharedPrefManager sharedPrefManager;
    String token,userMobileNumber;
    SharedPrefManager sp;


    public AddAddressAdapter(List<GetAddress_data> getAddressData, Context context) {
        this.getAddressData = getAddressData;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclevview_add_address_items, parent, false);
        sharedPrefManager = new SharedPrefManager(view.getContext());
        token = sharedPrefManager.getTokenOtp().getHttpResponseHeader();
        userMobileNumber = sharedPrefManager.getUser().getCustomerMobileNo();
        Log.i("Token1", "********************Token1**************************" + token);
        Log.i("mobile", "********************mobile**************************" + userMobileNumber);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GetAddress_data data = getAddressData.get(position);
        holder.Address.setText(data.getAddressBuildingNoHouseName()+" "+data.getAddressStreet()+" "+data.getAddressLandmark()+" "+data.getAddressCity()+" "+data.getAddressPin());
        AddressType = Integer.parseInt(data.getAddressType());
        addressId = data.getAddressId();
        customerAddressId =data.getAddressCustomerId();

        sharedPrefManager.saveAddressId(getAddressData.get(position).getAddressId(),
                getAddressData.get(position).getAddressCustomerId());

       if (AddressType==0){
           holder.Work.setText("Home Address");
       }
       else {
           holder.Work.setText("Office Address");
       }



        holder.Edite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                home =data.getAddressBuildingNoHouseName();
                street =data.getAddressStreet();
                landmark = data.getAddressLandmark();
                city =data.getAddressCity();
                pincode =data.getAddressPin();
                addressId =data.getAddressId();
                customerAddressId =data.getAddressCustomerId();
                addressDefaults = data.getAddressDefault();
                Log.i("addressIdhh","**********************"+addressId);
                //Toast.makeText(v.getContext(),"Edit",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(v.getContext(), EditAddress.class);
                intent.putExtra("addressId",addressId);
                intent.putExtra("customerAddressId",customerAddressId);
                intent.putExtra("home",home);
                intent.putExtra("street",street);
                intent.putExtra("landmark",landmark);
                intent.putExtra("city",city);
                intent.putExtra("pincode",pincode);
                intent.putExtra("addressDefaults",addressDefaults);

                v.getContext().startActivity(intent);
                //v.getContext().startActivity(new Intent(v.getContext(), EditAddress.class));
            }
        });
       
        holder.Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                customerAddressList[0] = new CustomerAddressList(addressId, (long) customerAddressId);
                //customerAddressList[0] = new CustomerAddressList(11, (long) 30016);
                mainDeleteAddressList = new MainDeleteAddressList(userMobileNumber, customerAddressList);
                mainDeleteAddressList.setCustomerMobileNo(userMobileNumber);
                mainDeleteAddressList.setCustomerAddressList(customerAddressList);

                Call<AddressDelete_responce_modal> call = RetrofitClient
                        .getInstance()
                        .getApi()
                        .DeleteAddress(token, mainDeleteAddressList);

                call.enqueue(new Callback<AddressDelete_responce_modal>() {
                    @Override
                    public void onResponse(@NotNull Call<AddressDelete_responce_modal> call, Response<AddressDelete_responce_modal> response) {

                        if (response.isSuccessful()) {

                            AddressDelete_responce_modal addressDelete_responce_modal = response.body();

                            AddressDelete_data addressDelete_data = addressDelete_responce_modal.getData();
                            boolean successed = addressDelete_responce_modal.getSuccess();
                            Log.i("succ", "*************succ************" + successed);

                            if (addressDelete_responce_modal.getSuccess()) {
                                Intent intent = new Intent(v.getContext(), ProfileActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                v.getContext().startActivity(intent);

                                Toast.makeText(v.getContext(), "Delete Address Successfully", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(v.getContext(), addressDelete_responce_modal.getErrorMessage().toString(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(v.getContext(), response.message(), Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<AddressDelete_responce_modal> call, Throwable t) {

                        Toast.makeText(v.getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

        @Override
    public int getItemCount() {
        return getAddressData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Address,Work;
        ImageView Edite,Delete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Address = itemView.findViewById(R.id.address_show);
            Delete =itemView.findViewById(R.id.tv_address_delete);
            Edite = itemView.findViewById(R.id.tv_address_edit);
            Work = itemView.findViewById(R.id.work_offfice);

        }
    }
}

package com.lyondry.lyondry.Fragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lyondry.lyondry.Activity.EditActivity;
import com.lyondry.lyondry.Activity.MainActivity;
import com.lyondry.lyondry.Activity.PickupAddress1Activity;
import com.lyondry.lyondry.Activity.ProfileActivity;
import com.lyondry.lyondry.Activity.Schduail2Activity;
import com.lyondry.lyondry.Adapters.AddAddressAdapter;
import com.lyondry.lyondry.Adapters.SchedualPickupAdapter;
import com.lyondry.lyondry.Adapters.servicesAdapter;
import com.lyondry.lyondry.Classes.SharedPrefManager;
import com.lyondry.lyondry.Classes.SharedPrefence_main;
import com.lyondry.lyondry.Hnadler.RecyclerTouchListener;
import com.lyondry.lyondry.Modals.GetAddress_data;
import com.lyondry.lyondry.Modals.InsertPickup.InsertPickup_data;
import com.lyondry.lyondry.Modals.InsertPickup.InsertPickup_responce_modal;
import com.lyondry.lyondry.Modals.SelectStore.SelectedStore_data;
import com.lyondry.lyondry.Modals.SelectStore.SelectedStore_responce_Modal;
import com.lyondry.lyondry.Modals.Services_data;
import com.lyondry.lyondry.Modals.Services_responce_modal;
import com.lyondry.lyondry.Modals.TimeSlots.TimeSlots_data;
import com.lyondry.lyondry.Modals.TimeSlots.TimeSlots_responce_modal;
import com.lyondry.lyondry.Modals.address.GetAddress_responce_modal;
import com.lyondry.lyondry.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.lyondry.lyondry.Retrofits.RetrofitClient;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class SchedulePickupBottomSheetDialog extends BottomSheetDialogFragment {

    TextView FormShowDate;
    ImageView From_cal ;
    DatePickerDialog picker;
    TextView cancle,Btn_next,address;
    RadioGroup radioGroup;
    RadioButton normanlRadioButton,expressRadioButton;
    Spinner sp_service,sp_clean_store,sp_TimeSlots;
    String[] ab = {"m","f"};
    String SeviceName;
    SharedPrefManager sharedPrefManager;
    List<Services_data> services_data;
    List<Services_responce_modal> services_responce_modals;
    Context context;
    String select_service;
    String name;
    int id;
    List<SelectedStore_data> selectedStore_data;
    String[] stroeId;
    List<TimeSlots_data>timeSlotsData;
    String token, userMobileNumber;
    String currentDateandTime;
    String cDate;
    String selectedType="";
    String serviceId;
    String storeId;
    String timeSlotId;
    int addressId,AddressCustomerId;
    RecyclerView recyclerView;
    List<GetAddress_data>getAddressData =new ArrayList<>();
    List<GetAddress_data>getAddressData_temp=new ArrayList<>();
    SchedualPickupAdapter adapter;
    SharedPreferences sp,sp1;
    RadioGroup radioGroupAddress;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottomsheet_schedual_pickup, container, false);
        sp_service = view.findViewById(R.id.sp_laundry_services);
        sp_clean_store =view.findViewById(R.id.sp_clean_laundry_store);
        FormShowDate = view.findViewById(R.id.From_show_date1);
        From_cal = view.findViewById(R.id.From_calendar);
//        addresscheckBox = view.findViewById(R.id.checkBox);
        sp_TimeSlots = view.findViewById(R.id.sp_timeslot);
        radioGroup = view.findViewById(R.id.rg_normal_wash);
        normanlRadioButton =view.findViewById(R.id.rb_normal);
        expressRadioButton = view.findViewById(R.id.rb_express1);
        address = view.findViewById(R.id.btn_addnew_address);
        recyclerView =view.findViewById(R.id.addresspickup_recycleView);

        sharedPrefManager = new SharedPrefManager(getActivity());
        addressId= sharedPrefManager.getAddressData().getAddressId();
        AddressCustomerId =sharedPrefManager.getAddressData().getAddressCustomerId();

        Log.i("Addressid","*************Addressid*************"+addressId);
        Log.i("CustomerAddressId","*************CustomerAddressId************"+AddressCustomerId);

        selectedType =normanlRadioButton.getText().toString();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId==R.id.rb_normal){
                    selectedType =normanlRadioButton.getText().toString();
                }
                else{
                    selectedType =expressRadioButton.getText().toString();
                }
            }
        });

        cancle =view.findViewById(R.id.btn_cancle1);
        Btn_next =view.findViewById(R.id.txt_next1);
        sharedPrefManager = new SharedPrefManager(getActivity());
        token = sharedPrefManager.getTokenOtp().getHttpResponseHeader();
        Log.i("Token11", "********************Token11**************************" + token);
        userMobileNumber = sharedPrefManager.getUser().getCustomerMobileNo();
        Log.i("userMobileNumber", "********************userMobileNumber**************************" + userMobileNumber);

        SeviceName = sharedPrefManager.getServicesName().getServiceName();
        Log.i("ServiceName","&=*******************"+SeviceName);

        BtnNext();
        ShowDate();
        BtnCancel();
        NewAddress();
        //FromDate();
        //Timepiker();
        ServicesApi();
        SelectedStore();
        AddressRecycleView();
//        SelectedTimeSlots();
        //services();

        return view;

    }

    private void ShowDate(){
        // SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        currentDateandTime = inputFormat.format(new Date());
        String corrnetDate = currentDateandTime;
        Log.i("currentDateandTime","*******currentDateandTime*******"+corrnetDate);
        SimpleDateFormat spf= new SimpleDateFormat("dd-MM-yyyy");

        try {
            Date date = inputFormat.parse(corrnetDate);
            String date1 =spf.format(date);
            FormShowDate.setText(date1);
            Date date3 = inputFormat.parse(corrnetDate);
            cDate =inputFormat.format(date3);
            Log.i("cdate","**************changeDate2*************"+cDate);

            Log.i("newDate1","***************************"+date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        From_cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                Log.e("monthsss",""+month);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog

                picker = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                               String months = String.valueOf(monthOfYear+1);

                                if (monthOfYear<10){
                                    months="0"+months;
                                }

                                String corrnetDate = year + "-" + (months) + "-" + dayOfMonth;
                                Log.e("corrnetDate","************"+corrnetDate);
                                SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
                                SimpleDateFormat spf= new SimpleDateFormat("dd-MM-yyyy");

                                try {
                                    Date date = inputFormat.parse(corrnetDate);
                                    String date1 =spf.format(date);
                                    Log.e("date1","*****date1*******"+date1);
                                    Date date3 = inputFormat.parse(corrnetDate);
                                    Log.e("date3","*****date3*******"+date3);
                                    cDate =inputFormat.format(date3);

                                    Log.e("cDatecDate","*****cDate*******"+cDate);
                                    FormShowDate.setText(date1);
                                    SelectedTimeSlots(Integer.parseInt(storeId));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, year, month, day);
                picker.getDatePicker().setMinDate(System.currentTimeMillis() -1000);
                picker.show();
            }
        });

//        FormShowDate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SelectedTimeSlots(Integer.parseInt(storeId));
//            }
//        });
    }
    private void NewAddress(){
        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PickupAddress1Activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }
    private void BtnCancel(){
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), MainActivity.class);
//                startActivity(intent);
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                dismiss();
            }
        });
    }
    private void BtnNext(){

            Btn_next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (getAddressData.isEmpty()){
                        Btn_next.setEnabled(false);
                        Btn_next.setTextColor(ContextCompat.getColor(getActivity(), R.color.light_gray));
                        Toast.makeText(getActivity(), "Please Add Pickup address", Toast.LENGTH_SHORT).show();
                        Log.e("getAddressData",""+getAddressData);
                    }
                    else {
                        //Toast.makeText(getActivity(), "next", Toast.LENGTH_SHORT).show();
                        Btn_next.setEnabled(true);
                        Btn_next.setTextColor(ContextCompat.getColor(getActivity(), R.color.white));

                        Log.e("getAddressData2",""+getAddressData);
                        InsertPickup();
                        TermsAndConditionsBottomSheetDialog termsAndConditionsBottomSheetDialog = new TermsAndConditionsBottomSheetDialog();
                        termsAndConditionsBottomSheetDialog.show(getFragmentManager(),
                                "ModalBottomSheet");
                        dismiss();

                    }

                }
            });
       // }



    }
    private void FromDate(){
        From_cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog



                picker = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                                String months = String.valueOf(monthOfYear+1);

                                if (monthOfYear<10){
                                    months="0"+months;

                                }

                                String corrnetDate = year + "-" + (months) + "-" + dayOfMonth;
                                Log.e("corrnetDate","************"+corrnetDate);
                                SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
                                SimpleDateFormat spf= new SimpleDateFormat("dd-MM-yyyy");

                                try {
                                    Date date = inputFormat.parse(corrnetDate);
                                    String date1 =spf.format(date);
                                    Log.e("date1","*****date1*******"+date1);
                                    Date date3 = inputFormat.parse(corrnetDate);
                                    Log.e("date3","*****date3*******"+date3);
                                    cDate =inputFormat.format(date3);
                                    Log.i("cdate","**************changeDate1*************"+cDate);
                                    Log.e("cDatecDate","*****cDate*******"+cDate);
                                    FormShowDate.setText(cDate);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, year, month, day);
                picker.getDatePicker().setMinDate(System.currentTimeMillis() -1000);
                picker.show();
            }
        });
    }

    private void ServicesApi() {

        Long num = Long.valueOf(0);
        Call<Services_responce_modal> call = RetrofitClient
                .getInstance()
                .getApi()
                .SelectService(num);

        call.enqueue(new Callback<Services_responce_modal>() {
            @Override
            public void onResponse(@NotNull Call<Services_responce_modal> call, Response<Services_responce_modal> response) {

                if (response.isSuccessful()) {

                    Services_responce_modal services_responce_modal = response.body();

                    if (services_responce_modal.getSuccess()) {
                        services_data = services_responce_modal.getServiceList();
                        Services_data servicesData;
                        Log.i("services_dat","****services********"+services_data.size());
                        String[] name1 = new String[services_data.size() ];
                        String[] id1 = new String[services_data.size() ];
                        for (int i=0; i<services_data.size(); i++)
                        {
                            servicesData = (Services_data) services_data.get(i);
                            name = servicesData.getServiceName();
                            name1[i]=name;

                            id = servicesData.getServiceId();
                            id1[i]= String.valueOf(id);
                            Log.i("Service_Name","**************Service_Name***************"+name);
                            Log.i("Service_id","**************Service_id***************"+id);


                        }

                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, name1);
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        sp_service.setAdapter(dataAdapter);
                        sp_service.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                //Toast.makeText(getContext(), "" + sp_service.getSelectedItemPosition(), Toast.LENGTH_SHORT).show();
                                serviceId = String.valueOf(services_data.get(position).getServiceId());
                                Log.i("serviceId","**********serviceIddwdw************"+serviceId);

                                 select_service = String.valueOf(sp_service.getSelectedItemPosition());
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });


                    } else {
                        Toast.makeText(getContext(), services_responce_modal.getErrorMessage(), Toast.LENGTH_SHORT).show();

                    }

                } else {
                    Toast.makeText(getContext(), response.message(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Services_responce_modal> call, Throwable t) {

                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void SelectedStore() {

        Long num = Long.valueOf(0);
        Call<SelectedStore_responce_Modal> call = RetrofitClient
                .getInstance()
                .getApi()
                .SelectedStore(num);

        call.enqueue(new Callback<SelectedStore_responce_Modal>() {
            @Override
            public void onResponse(@NotNull Call<SelectedStore_responce_Modal> call, Response<SelectedStore_responce_Modal> response) {

                if (response.isSuccessful()) {
                    SelectedStore_responce_Modal selectedStore_responce_modal =response.body();

                    if (selectedStore_responce_modal.getSuccess()) {
                        selectedStore_data = selectedStore_responce_modal.getStoreList();
                        SelectedStore_data selectedStoreData;
                        String[] storeName = new String[selectedStore_data.size() ];
                        stroeId = new String[selectedStore_data.size() ];

                        for (int i = 0; i < selectedStore_data.size(); i++) {
                            selectedStoreData = (SelectedStore_data) selectedStore_data.get(i);
                            SelectedStore_data selectedStore = (SelectedStore_data) selectedStore_data.get(i);
                            int StoreId = selectedStore.getStoreId() ;
                            String StoreName = selectedStore.getStoreName();
                            storeName[i]=StoreName;
                            stroeId[i]= String.valueOf(StoreId);


                            Log.i("StoreId", "***********StoreId***************" + StoreId);
                            Log.i("StoreName", "***********StoreName***************" + StoreName);

                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, storeName);
                            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            sp_clean_store.setAdapter(dataAdapter);
                            sp_clean_store.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    //Toast.makeText(getContext(), "" + sp_clean_store.getSelectedItemPosition(), Toast.LENGTH_SHORT).show();
                                    SelectedTimeSlots(selectedStore_data.get(position).getStoreId());
                                    storeId = String.valueOf(selectedStore_data.get(position).getStoreId());
                                    Log.i("storeId","**********storeIdasda************"+storeId);

                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {

                                }
                            });


                        }
                    }
                    else {
                        Toast.makeText(getContext(), selectedStore_responce_modal.getErrorMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), response.message(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<SelectedStore_responce_Modal> call, Throwable t) {

                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void SelectedTimeSlots(int storeId ) {

        Log.i("cdate","**************changeDate*************"+cDate);
        Call<TimeSlots_responce_modal> call = RetrofitClient
                .getInstance()
                .getApi()
                .getTimeSlot(token,userMobileNumber, cDate,storeId);

        call.enqueue(new Callback<TimeSlots_responce_modal>() {
            @Override
            public void onResponse(@NotNull Call<TimeSlots_responce_modal> call, Response<TimeSlots_responce_modal> response) {

                if (response.isSuccessful()) {
                    TimeSlots_responce_modal timeSlots_responce_modal =response.body();
                    Log.i("selectItem","********************selectedStore_responce_modal"+timeSlots_responce_modal);

                    if (timeSlots_responce_modal.getSuccess()) {
                        timeSlotsData = timeSlots_responce_modal.getTimeSlotList();
                        TimeSlots_data timeSlots_data;
                        String[] TimeSlotStoreId = new String[timeSlotsData.size() ];
                        String[] TimeSlotValue = new String[timeSlotsData.size() ];

                        for (int i = 0; i < timeSlotsData.size(); i++) {
                            timeSlots_data = (TimeSlots_data) timeSlotsData.get(i);
                            TimeSlots_data timeSlots = (TimeSlots_data) timeSlotsData.get(i);
                            Long TimeSlotId1 = Long.valueOf(timeSlots.getTimeSlotId());
                            Long TimeSlotStoreId1 = Long.valueOf(timeSlots.getTimeSlotStoreId());
                            String TimeSlotValue1 = timeSlots.getTimeSlotValue();
                            TimeSlotValue[i]=TimeSlotValue1;
                            TimeSlotStoreId[i]= String.valueOf(TimeSlotValue1);


                            Log.i("TimeSlotId1", "***********TimeSlotId1***************" + TimeSlotId1);
                            Log.i("TimeSlotStoreId1", "***********TimeSlotStoreId1***************" + TimeSlotStoreId1);
                            Log.i("TimeSlotValue1", "***********TimeSlotValue1***************" + TimeSlotValue1);

                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, TimeSlotValue);
                            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            sp_TimeSlots.setAdapter(dataAdapter);
                            sp_TimeSlots.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                   // Toast.makeText(getContext(), "" + sp_TimeSlots.getSelectedItemPosition(), Toast.LENGTH_SHORT).show();
                                    timeSlotId = String.valueOf(timeSlots_responce_modal.getTimeSlotList().get(position).getTimeSlotId());
                                    Log.i("timeSlotId","**********timeSlotIdsdsd************"+timeSlotId);
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {

                                }
                            });


                        }
                    }
                    else {
                        Toast.makeText(getContext(), timeSlots_responce_modal.getErrorMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), response.message(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<TimeSlots_responce_modal> call, Throwable t) {

                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void AddressRecycleView() {

        Call<GetAddress_responce_modal> call = RetrofitClient
                .getInstance()
                .getApi()
                .getAddress(token, userMobileNumber);

        call.enqueue(new Callback<GetAddress_responce_modal>() {
            @Override
            public void onResponse(@NotNull Call<GetAddress_responce_modal> call, Response<GetAddress_responce_modal> response) {

                if (response.isSuccessful()) {

                    GetAddress_responce_modal getAddress_responce_modal = response.body();

                    if (getAddress_responce_modal.getSuccess()) {
                        getAddressData = getAddress_responce_modal.getCustomerAddressList();

                        adapter = new SchedualPickupAdapter(getAddressData, context);
                        recyclerView.setLayoutManager(new LinearLayoutManager(context));
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();

                        for (int i=0; i<getAddressData.size(); i++){
                            GetAddress_data data =(GetAddress_data) getAddressData.get(i);
//                            int addressId12 =data.getAddressId();
//                            AddressCustomerId =data.getAddressCustomerId();
////                            getAddressData.add(new GetAddress_data(
////                                    getAddressData_temp.get(i).getAddressId(),
////                                    getAddressData_temp.get(i).getAddressCustomerId(),
////                                    getAddressData_temp.get(i).getAddressBuildingNoHouseName(),
////                                    getAddressData_temp.get(i).getAddressStreet(),
////                                    getAddressData_temp.get(i).getAddressCity(),
////                                    getAddressData_temp.get(i).getAddressPin(),
////                                    getAddressData_temp.get(i).getAddressLandmark(),
////                                    getAddressData_temp.get(i).getAddressLocationLaLo(),
////                                    getAddressData_temp.get(i).getAddressType(),
////                                    getAddressData_temp.get(i).getAddressDefault(),
////                                    getAddressData_temp.get(i).getAddressActive(),
////                                    getAddressData_temp.get(i).getSessionID(),
////                                    getAddressData_temp.get(i).getDateTime(),
////                                    getAddressData_temp.get(i).getEntryStatus(),
////                                    getAddressData_temp.get(i).getError(),
////                                    getAddressData_temp.get(i).getErrorMessage(),
////                                    0));

//                               Log.i("Service_Name","**************Service_Name***************"+House);
                            //Log.i("addressIdidfd","*********addressId12**************"+addressId12);
                           // Log.i("customerAddressId9","*********customerAddressId9**************"+AddressCustomerId);


                        }

                    } else {
                        Toast.makeText(getContext(), getAddress_responce_modal.getErrorMessage(), Toast.LENGTH_SHORT).show();

                    }

                } else {
                    Toast.makeText(getContext(), response.message(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<GetAddress_responce_modal> call, Throwable t) {

                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void InsertPickup() {
        Log.i("addressId91","*********addressId91**************"+addressId);

        Call<InsertPickup_responce_modal> call = RetrofitClient
                .getInstance()
                .getApi()
                .insertPickup(token,userMobileNumber,AddressCustomerId, Integer.parseInt(timeSlotId), Integer.parseInt(serviceId), Integer.parseInt(storeId),addressId,1,selectedType);

        call.enqueue(new Callback<InsertPickup_responce_modal>() {
            @Override
            public void onResponse(@NotNull Call<InsertPickup_responce_modal> call, Response<InsertPickup_responce_modal> response) {

                if (response.isSuccessful()) {
                    InsertPickup_responce_modal insertPickup_responce_modal =response.body();
                    InsertPickup_data insertPickup_data = insertPickup_responce_modal.getData();
                    Log.i("selectItem","********************selectedStore_responce_modal"+insertPickup_responce_modal);

                    if (insertPickup_responce_modal.getSuccess()) {
                        InsertPickup_data insertAddress_data = insertPickup_responce_modal.getData();

                        int PickupRequestId = insertAddress_data.getPickupRequestId();
                        String PickupRequestStoreName = insertAddress_data.getPickupRequestStoreName();
                        String PickupRequestServiceType = insertAddress_data.getPickupRequestServiceType();
                        String PickupRequestDate = insertAddress_data.getPickupRequestDate();
                        String PickupRequestTime = insertAddress_data.getPickupRequestTime();
                        String PickupRequestAddress = insertAddress_data.getPickupRequestAddress();
                        String PickupRequestStatus = insertAddress_data.getPickupRequestStatus();

                        Log.i("PickupRequestId","***********PickupRequestId************"+PickupRequestId);
                        Log.i("PickupRequestStoreName","***********PickupRequestStoreName************"+PickupRequestStoreName);
                        Log.i("PickupServiceType","***********PickupRequestServiceType************"+PickupRequestServiceType);
                        Log.i("PickupRequestDate","***********PickupRequestDate************"+PickupRequestDate);
                        Log.i("PickupRequestTime","***********PickupRequestTime************"+PickupRequestTime);
                        Log.i("PickupRequestAddress","***********PickupRequestAddress************"+PickupRequestAddress);
                        Log.i("PickupRequestStatus","***********PickupRequestStatus************"+PickupRequestStatus);

                    }
                    else {
                        Toast.makeText(getContext(), insertPickup_responce_modal.getErrorMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), response.message(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<InsertPickup_responce_modal> call, Throwable t) {

                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}

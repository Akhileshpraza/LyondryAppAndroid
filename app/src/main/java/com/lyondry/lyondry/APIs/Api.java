package com.lyondry.lyondry.APIs;

import com.lyondry.lyondry.Modals.Company.CompanyMaster_responce_modal;
import com.lyondry.lyondry.Modals.Feedbacke.Feedbacke_responce_Modal;
import com.lyondry.lyondry.Modals.GetProfileImage_responce_Modal;
import com.lyondry.lyondry.Modals.GetUserProfile_responce;
import com.lyondry.lyondry.Modals.InsertAddress_responce;
import com.lyondry.lyondry.Modals.InsertPickup.InsertPickup_responce_modal;
import com.lyondry.lyondry.Modals.InvoiceMaster.MainInviceMaster_reponce_modal;
import com.lyondry.lyondry.Modals.Login_responce;
import com.lyondry.lyondry.Modals.Logout_responce_modal;
import com.lyondry.lyondry.Modals.Order.SelectOrder_respoce_modal;
import com.lyondry.lyondry.Modals.OtpResponceModal;
import com.lyondry.lyondry.Modals.Payment.SelectInvoiceStatus_responce_modal;
import com.lyondry.lyondry.Modals.SelectItem.SelectItem_responce_modal;
import com.lyondry.lyondry.Modals.SelectSchedule.SelectSchedule_responce_modal;
import com.lyondry.lyondry.Modals.SelectStore.SelectedStore_responce_Modal;
import com.lyondry.lyondry.Modals.Services_responce_modal;
import com.lyondry.lyondry.Modals.Signup_responce_model;
import com.lyondry.lyondry.Modals.TimeSlots.TimeSlots_responce_modal;
import com.lyondry.lyondry.Modals.UploadProfilePic_responce;
import com.lyondry.lyondry.Modals.UserUpdate_responce_Modal;
import com.lyondry.lyondry.Modals.address.AddressDelete_responce_modal;
import com.lyondry.lyondry.Modals.address.EditAddress_responce_modal;
import com.lyondry.lyondry.Modals.address.GetAddress_responce_modal;
import com.lyondry.lyondry.Modals.address.MainCustomerAddressList;
import com.lyondry.lyondry.Modals.address.MainDeleteAddressList;
import com.lyondry.lyondry.Modals.address.MainEditAddressList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {

    @FormUrlEncoded
    @POST("CustomerMasterApi/RegisterCustomer?resend=N")
    Call<Signup_responce_model> register(
            @Field("CustomerGroupId") int CustomerGroupId,
            @Field("CustomerName") String CustomerName,
            @Field("CustomerMobileNo") String CustomerMobileNo,
            @Field("CustomerEmailId") String CustomerEmailId,
            @Field("EntryStatus") String EntryStatus

    );

    @FormUrlEncoded
    @POST("OtpApi/Validate")
    Call<OtpResponceModal> otprequest(
            @Field("OtpType") String OtpType,
            @Field("OtpMobileNo") String OtpMobileNo,
            @Field("OtpNo") String OtpNo,
            @Field("OtpExpiryMinutes") int OtpExpiryMinutes

    );

    @POST("LoginApi/Login")
    Call<Login_responce> loginrequest(
            @Query("MobileNo") String MobileNo
    );


    @POST("LogoutApi/Logout")
    Call<Logout_responce_modal> logout(
            @Query("MobileNo") String MobileNo

    );

    @FormUrlEncoded
    @POST("CustomerUpdateApi/UpdateCustomer")
    Call<UserUpdate_responce_Modal> userUpdate(
            @Header("Token") String token,
            @Field("CustomerEmailId") String CustomerEmailId,
            @Field("CustomerName") String CustomerName,
            @Field("CustomerGender") String CustomerGender,
            @Field("CustomerDOB") String CustomerDOB,
            @Field("CustomerMobileNo") String CustomerMobileNo

    );


    @GET("CustomerUpdateApi/GetCustomerProfile")
    Call<GetUserProfile_responce> getUserProfile(
            @Header("Token") String token,
            @Header("MobileNo") String MobileNo
    );

    @FormUrlEncoded
    @POST("ProfilePictureApi/UploadProfilePic")
    Call<UploadProfilePic_responce> uploadProfilePic(
            @Header("Token") String token,
            @Field("CustomerMobileNo") String CustomerMobileNo,
            @Field("CustomerProfilePicStr") String CustomerProfilePicStr);



    @GET("ProfilePictureApi/GetProfilePic")
    Call<GetProfileImage_responce_Modal> GetProfileImage(
            @Header("Token") String token,
            @Header("MobileNo") String MobileNo);


    @POST("AddressApi/InsertAddress")
    Call<InsertAddress_responce> InsertAddress(
            @Header("Token") String Token,
            @Body MainCustomerAddressList mainCustomerAddressList);


    @GET("AddressApi/GetAddress")
    Call<GetAddress_responce_modal> getAddress(
            @Header("Token") String token,
            @Header("MobileNo") String MobileNo);

//new

@GET("CompanyMasterApi/SelectCompanyMaster")
Call<CompanyMaster_responce_modal> SelectCompanyMaster(
        @Query("CompanyId") Long CompanyId);

    @GET("ItemMasterApi/SelectItem")
    Call<SelectItem_responce_modal> SelectItem(
            @Query("ItemId") Long ItemId);



    @GET("ServiceApi/SelectService")
    Call<Services_responce_modal> SelectService(
            @Query("ServiceId") Long ServiceId);


    @GET("StoreApi/SelectStore")
    Call<SelectedStore_responce_Modal> SelectedStore(
            @Query("StoreId") Long StoreId);


    @GET("TimeSlotApi/SelectTimeSlot")
    Call<TimeSlots_responce_modal> getTimeSlot(
            @Header("Token") String token,
            @Header("MobileNo") String MobileNo,
            @Query("SlotDate") String SlotDate,
            @Query("StoreId") int StoreId);

    @FormUrlEncoded
    @POST("PickupRequestApi/InsertPickup")
    Call<InsertPickup_responce_modal> insertPickup(
            @Header("Token") String token,
            @Header("MobileNo") String MobileNo,
            @Field("PickupRequestCustomerId") int PickupRequestCustomerId,
            @Field("PickupRequestTimeSlotId") int PickupRequestTimeSlotId,
            @Field("PickupRequestServiceId") int PickupRequestServiceId,
            @Field("PickupRequestStoreId") int PickupRequestStoreId,
            @Field("PickupRequestAddressId") int PickupRequestAddressId,
            @Field("PickupRequestStatusId") int PickupRequestStatusId,
            @Field("PickupRequestServiceType") String PickupRequestServiceType);


    @POST("DeleteAddressApi/DeleteAddress")
    Call<AddressDelete_responce_modal> DeleteAddress(
            @Header("Token") String Token,
            @Body MainDeleteAddressList mainDeleteAddressList);

    @POST("UpdateAddressApi/UpdateAddress")
    Call<EditAddress_responce_modal> EditAddress(
            @Header("Token") String Token,
            @Body MainEditAddressList mainEditAddressList);

    @GET("PickupScheduleApi/SelectSchedule")
    Call<SelectSchedule_responce_modal> SelectSchedule(
            @Header("Token") String token,
            @Header("MobileNo") String MobileNo,
            @Header("PickupSchedulePickupRequestId") int PickupSchedulePickupRequestId);



    @GET("PickupRequestApi/SelectOrder")
    Call<SelectOrder_respoce_modal> SelectOrder(
            @Header("Token") String token,
            @Header("MobileNo") String MobileNo,
            @Header("PickupRequestCustomerId") int PickupRequestCustomerId

    );

    @GET("InvoiceApi/SelectInvoice")
    Call<MainInviceMaster_reponce_modal> InviceMaster(
            @Header("Token") String token,
            @Header("MobileNo") String MobileNo,
            @Header("InvoiceRequestId") int InvoiceRequestId);

    @POST("InvoiceApi/SelectInvoiceStatus")
    Call<SelectInvoiceStatus_responce_modal> SelectInvoiceStatus(
            @Header("Token") String Token,
            @Header("MobileNo") String MobileNo);

    @FormUrlEncoded
    @POST("FeedbackApi/InsertFeedback")
    Call<Feedbacke_responce_Modal> InsertFeedback(
            @Header("Token") String Token,
            @Header("MobileNo") String MobileNo,
            @Field("FeedbackDate")String FeedbackDate,
            @Field("FeedbackInvoiceNo") String FeedbackInvoiceNo,
            @Field("FeedBackItems") String FeedBackItems,
            @Field("FeedbackDescription") String FeedbackDescription,
            @Field("FeedbackImage") String FeedbackImage);



}



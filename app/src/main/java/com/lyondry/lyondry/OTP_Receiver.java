package com.lyondry.lyondry;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;

import com.alimuzaffar.lib.pin.PinEntryEditText;

import static android.text.TextUtils.indexOf;

public class OTP_Receiver extends BroadcastReceiver {

    private static PinEntryEditText pinEntry;

    public void setPinEntry(PinEntryEditText pinEntry)
    {
        OTP_Receiver.pinEntry = pinEntry;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        SmsMessage[] messages = Telephony.Sms.Intents.getMessagesFromIntent(intent);

        for (SmsMessage sms : messages)
        {
            String message = sms.getMessageBody();
            int startPosition=indexOf(message,",",0);
            startPosition=startPosition+2;
            Log.i("startPosition","***********startPosition******************"+startPosition);
            String otp = message.substring(startPosition,startPosition+6);
            Log.i("otp","***********otpp******************"+otp);
            pinEntry.setText(otp);


        }
    }
}

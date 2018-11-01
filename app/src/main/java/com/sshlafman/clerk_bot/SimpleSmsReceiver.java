package com.sshlafman.clerk_bot;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SimpleSmsReceiver extends BroadcastReceiver
{
    public static final String SMS_EXTRA_NAME = "pdus";

    @Override
    public void onReceive(Context context, Intent intent)
    {
        String messages = "";

        Bundle extras = intent.getExtras();

        if (extras != null)
        {
            Object[] smsExtras = (Object[]) extras.get(SMS_EXTRA_NAME);

            for (int i=0; i < smsExtras.length; ++i)
            {
                SmsMessage smsMessage =  SmsMessage.createFromPdu((byte[])smsExtras[i]);

                String body = smsMessage.getMessageBody();
                String address = smsMessage.getDisplayOriginatingAddress();

                messages += "SMS Received from " + address + ":\n";
                messages += body + "\n";
            }

            Toast.makeText(context, messages, Toast.LENGTH_SHORT).show();

            // WARNING!!!
            // If you uncomment the next line then received SMS will not be put to incoming.
            // Be careful!
            // this.abortBroadcast();
        }

    }
}

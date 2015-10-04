package se.uit.chichssssteam.quanlicuocdidong;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Message;
import android.provider.ContactsContract;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import android.provider.CallLog;
import android.provider.Telephony;
import android.telecom.Call;
import android.telephony.SmsManager;

/**
 * Created by justinvan on 03-Oct-15.
 */
public class PhoneLogManager {
    List<se.uit.chichssssteam.quanlicuocdidong.CallLog> _listCall;
    List<MessageLog> _listMessage;

    public PhoneLogManager() {
        _listCall = new ArrayList<se.uit.chichssssteam.quanlicuocdidong.CallLog>();
        _listMessage = new ArrayList<MessageLog>();
    }
    public String ConvertToTimeSpan(String date)
    {
        try {
            SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.ENGLISH);
            Date d = format.parse(date);
            SimpleDateFormat serverFormat = new SimpleDateFormat("HH:mm:ss",Locale.ENGLISH);
            return serverFormat.format(d);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    public List<se.uit.chichssssteam.quanlicuocdidong.CallLog> LoadCallLog(Context context, PackageFee p) {

        List<se.uit.chichssssteam.quanlicuocdidong.CallLog> _logList = new ArrayList<se.uit.chichssssteam.quanlicuocdidong.CallLog>();
        Cursor cursor = context.getContentResolver().query(CallLog.Calls.CONTENT_URI
                , null, null, null, CallLog.Calls.DATE + " DESC");
        int number = cursor.getColumnIndex(CallLog.Calls.NUMBER);
        int callType = cursor.getColumnIndex(CallLog.Calls.TYPE);
        int date = cursor.getColumnIndex(CallLog.Calls.DATE);
        int duration = cursor.getColumnIndex(CallLog.Calls.DURATION);
        if (cursor.moveToFirst()) {
            do {
                String _number = cursor.getString(number);
                String _callType = cursor.getString(callType);
                String _callDate = cursor.getString(date);
                int _callDuration = cursor.getInt(duration);
                p.set_outGoingPhoneNumber(_number);
                int dircode = Integer.parseInt(_callType);
                if (dircode == CallLog.Calls.OUTGOING_TYPE) {
                    p.set_callTime(ConvertToTimeSpan(_callDate));
                    int _fee = p.CalculateCallFee();
                    se.uit.chichssssteam.quanlicuocdidong.CallLog newElement = new
                            se.uit.chichssssteam.quanlicuocdidong.CallLog(-1, _callDate, _number, _callDuration, _fee);
                    _logList.add(newElement);
                }
            }
            while (cursor.moveToNext() == true);
        }
        return _logList;
    }

    public List<MessageLog> LoadMessageLog(Context context, PackageFee p) {
        List<MessageLog> _logList = new ArrayList<MessageLog>();
        Uri _uri = Uri.parse("content://sms/sent");

        Cursor cursor = context.getContentResolver().query(_uri, null, null, null, "date DESC");
        if (cursor.moveToFirst()) {
            do {

                String number = cursor.getString(cursor.getColumnIndexOrThrow("address"));
                String date = cursor.getString(cursor.getColumnIndexOrThrow("date"));
                p.set_outGoingPhoneNumber(number);
                p.set_sendMessageTime(this.ConvertToTimeSpan(date));
                int _fee = p.CalculateMessageFee();
                MessageLog newElement = new MessageLog(date, number, _fee);
                _logList.add(newElement);

            }
            while (cursor.moveToNext() == true);
        }
        return _logList;
    }

    public int CalculateTotalCallFee() {
        int totalFee = 0;
        for (Iterator<se.uit.chichssssteam.quanlicuocdidong.CallLog> i = _listCall.iterator(); i.hasNext(); ) {
            totalFee += i.next().get_callFee();
        }
        return totalFee;
    }

    public int CalculateTotalMessageFee()
    {
        int totalFee = 0;
        for(Iterator<MessageLog> i = _listMessage.iterator(); i.hasNext();)
        {
            totalFee += i.next().get_messageFee();
        }
        return totalFee;
    }
    public void UpdateCallLog(Context context, PackageFee p)
    {
        Cursor cursor = context.getContentResolver().query(CallLog.Calls.CONTENT_URI
                , null, null, null, CallLog.Calls.DATE + " DESC");
        int number = cursor.getColumnIndex(CallLog.Calls.NUMBER);
        int callType = cursor.getColumnIndex(CallLog.Calls.TYPE);
        int date = cursor.getColumnIndex(CallLog.Calls.DATE);
        int duration = cursor.getColumnIndex(CallLog.Calls.DURATION);
        if(cursor.moveToFirst())
        {
            String _number = cursor.getString(number);
            String _callType = cursor.getString(callType);
            String _callDate = cursor.getString(date);
            int _callDuration = cursor.getInt(duration);
            p.set_outGoingPhoneNumber(_number);
            int dircode = Integer.parseInt(_callType);
            if (dircode == CallLog.Calls.OUTGOING_TYPE)
            {
                int _fee = p.CalculateCallFee();
                se.uit.chichssssteam.quanlicuocdidong.CallLog newElement = new
                        se.uit.chichssssteam.quanlicuocdidong.CallLog(-1, _callDate, _number, _callDuration, _fee);
                this._listCall.add(0,newElement);
            }
        }
    }
    public void UpdateMessageLog(Context context, PackageFee p)
    {
        Uri _uri = Uri.parse("content://sms/sent");

        Cursor cursor = context.getContentResolver().query(_uri, null, null, null, "date DESC");
        if (cursor.moveToFirst())
        {
            String number = cursor.getString(cursor.getColumnIndexOrThrow("address"));
            String date = cursor.getString(cursor.getColumnIndexOrThrow("date"));
            p.set_outGoingPhoneNumber(number);
            int _fee = p.CalculateMessageFee();
            MessageLog newElement = new MessageLog(date, number, _fee);
            this._listMessage.add(0,newElement);
        }
    }

}

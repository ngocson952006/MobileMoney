package se.uit.chichssssteam.quanlicuocdidong.Manager;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import android.os.Message;
import android.provider.CallLog;
import android.telecom.Call;
import android.telephony.TelephonyManager;

import se.uit.chichssssteam.quanlicuocdidong.DB.*;

import se.uit.chichssssteam.quanlicuocdidong.NetworkPackage.PackageFee;

/**
 * Created by justinvan on 03-Oct-15.
 */
public class PhoneLogManager {
    List<se.uit.chichssssteam.quanlicuocdidong.DB.CallLog> _listCall;
    List<MessageLog> _listMessage;
    Context _context;
    PackageFee _packageFee;
    DateTimeManager _dateTimeManager;
    private static PhoneLogManager _instance;


    public static  synchronized  PhoneLogManager get_instance(Context context, PackageFee packageFee)
    {
        if(_instance == null)
            _instance = new PhoneLogManager(context,packageFee);
        return _instance;
    }
    public PhoneLogManager(Context context, PackageFee packageFee)
    {
        _listCall = new ArrayList<se.uit.chichssssteam.quanlicuocdidong.DB.CallLog>();
        _listMessage = new ArrayList<MessageLog>();
        _context = context;
        _packageFee = packageFee;

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
    public List<se.uit.chichssssteam.quanlicuocdidong.DB.CallLog> LoadCallLogFromPhone() {

        List<se.uit.chichssssteam.quanlicuocdidong.DB.CallLog> _logList = new ArrayList<se.uit.chichssssteam.quanlicuocdidong.DB.CallLog>();
        Cursor cursor = _context.getContentResolver().query(CallLog.Calls.CONTENT_URI
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

                int dircode = Integer.parseInt(_callType);
                if (dircode == CallLog.Calls.OUTGOING_TYPE)
                {
                    _packageFee.set_callDuration(_callDuration);
                    _packageFee.set_outGoingPhoneNumber(_number);
                    String time = _dateTimeManager.convertToHm(_callDate);
                    _packageFee.set_callTime(time);
                    int _fee = _packageFee.CalculateCallFee();
                    se.uit.chichssssteam.quanlicuocdidong.DB.CallLog newElement = new
                            se.uit.chichssssteam.quanlicuocdidong.DB.CallLog(-1, _callDate, _number, _callDuration, _fee,_packageFee.get_type());
                    _logList.add(newElement);
                }
            }
            while (cursor.moveToNext() == true);
        }
        cursor.close();
        return _logList;
    }
    public List<se.uit.chichssssteam.quanlicuocdidong.DB.CallLog> LoadCallLogAfterTimeSpan(long time)
    {
        List<se.uit.chichssssteam.quanlicuocdidong.DB.CallLog> _logList = new ArrayList<se.uit.chichssssteam.quanlicuocdidong.DB.CallLog>();
        Date temp = new Date(time);
        String formateddatetime = _dateTimeManager.normalizeDateTime(temp.toString());
        Cursor cursor = _context.getContentResolver().query(CallLog.Calls.CONTENT_URI, null, CallLog.Calls.DATE + " >= " + formateddatetime, null, CallLog.Calls.DATE + " DESC");
        int number = cursor.getColumnIndex(CallLog.Calls.NUMBER);
        int callType = cursor.getColumnIndex(CallLog.Calls.TYPE);
        int date = cursor.getColumnIndex(CallLog.Calls.DATE);
        int duration = cursor.getColumnIndex(CallLog.Calls.DURATION);
        if(cursor.moveToFirst())
        {
            do
            {
                String _number = cursor.getString(number);
                String _callType = cursor.getString(callType);
                String _callDate = cursor.getString(date);
                int _callDuration = cursor.getInt(duration);

                int dircode = Integer.parseInt(_callType);
                if (dircode == CallLog.Calls.OUTGOING_TYPE)
                {
                    _packageFee.set_callDuration(_callDuration);
                    _packageFee.set_outGoingPhoneNumber(_number);
                    String datetime = _dateTimeManager.convertToHm(_callDate);
                    _packageFee.set_callTime(datetime);
                    int _fee = _packageFee.CalculateCallFee();
                    se.uit.chichssssteam.quanlicuocdidong.DB.CallLog newElement = new
                            se.uit.chichssssteam.quanlicuocdidong.DB.CallLog(-1, _callDate, _number, _callDuration, _fee,_packageFee.get_type());
                    _logList.add(newElement);
                }
            }
            while(cursor.moveToNext() ==true);
        }
        cursor.close();
        return _logList;
    }
    public List<MessageLog> LoadMessageLogFromPhone() {
        List<MessageLog> _logList = new ArrayList<MessageLog>();
        Uri _uri = Uri.parse("content://sms/sent");

        Cursor cursor = _context.getContentResolver().query(_uri, null, null, null, "date DESC");
        if (cursor.moveToFirst()) {
            do {

                String number = cursor.getString(cursor.getColumnIndexOrThrow("address"));
                String date = cursor.getString(cursor.getColumnIndexOrThrow("date"));
                _packageFee.set_outGoingPhoneNumber(number);

                _packageFee.set_sendMessageTime(date);
                int _fee = _packageFee.CalculateMessageFee();
                MessageLog newElement = new MessageLog(date, number, _fee, _packageFee.get_type());
                _logList.add(newElement);

            }
            while (cursor.moveToNext() == true);
        }
        cursor.close();
        return _logList;
    }

    public List<MessageLog> LoadMessageLogAfterTimeSpan(long time)
    {
        List<MessageLog> _logList = new ArrayList<MessageLog>();
        Uri _uri = Uri.parse("content://sms/sent");
        Cursor cursor = _context.getContentResolver().query(_uri, null, null, null, "date DESC");
        if (cursor.moveToFirst()) {
            do {

                String number = cursor.getString(cursor.getColumnIndexOrThrow("address"));
                String date = cursor.getString(cursor.getColumnIndexOrThrow("date"));
                _packageFee.set_outGoingPhoneNumber(number);

                _packageFee.set_sendMessageTime(date);
                int _fee = _packageFee.CalculateMessageFee();
                MessageLog newElement = new MessageLog(date, number, _fee, _packageFee.get_type());
                _logList.add(newElement);

            }
            while (cursor.moveToNext() == true);
        }
        cursor.close();
        return _logList;
    }
    public int CalculateTotalCallFee() {
        int totalFee = 0;
        for (Iterator<se.uit.chichssssteam.quanlicuocdidong.DB.CallLog> i = _listCall.iterator(); i.hasNext(); ) {
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
    public void UpdateCallLog()
    {
        Cursor cursor = _context.getContentResolver().query(CallLog.Calls.CONTENT_URI
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
            _packageFee.set_outGoingPhoneNumber(_number);
            int dircode = Integer.parseInt(_callType);
            if (dircode == CallLog.Calls.OUTGOING_TYPE)
            {
                int _fee = _packageFee.CalculateCallFee();
                se.uit.chichssssteam.quanlicuocdidong.DB.CallLog newElement = new
                        se.uit.chichssssteam.quanlicuocdidong.DB.CallLog(-1, _callDate, _number, _callDuration, _fee,-1);
                this._listCall.add(0,newElement);
            }
        }
        cursor.close();
    }
    public void UpdateMessageLog()
    {
        Uri _uri = Uri.parse("content://sms/sent");

        Cursor cursor = _context.getContentResolver().query(_uri, null, null, null, "date DESC");
        if (cursor.moveToFirst())
        {
            String number = cursor.getString(cursor.getColumnIndexOrThrow("address"));
            String date = cursor.getString(cursor.getColumnIndexOrThrow("date"));
            _packageFee.set_outGoingPhoneNumber(number);
            int _fee = _packageFee.CalculateMessageFee();
            MessageLog newElement = new MessageLog(date, number, _fee, _packageFee.get_type());
            this._listMessage.add(0,newElement);
        }
        cursor.close();
    }

    public MessageLog GetLastedSentSMS()
    {

        Uri _uri = Uri.parse("content://sms/sent");
        Cursor cursor = _context.getContentResolver().query(_uri, null, null, null, "date DESC");

        if(cursor.moveToFirst())
        {
            String date = cursor.getString(cursor.getColumnIndexOrThrow("date"));
            String number = cursor.getString(cursor.getColumnIndexOrThrow("address"));
            _packageFee.set_sendMessageTime(date);
            _packageFee.set_outGoingPhoneNumber(number);
            int _fee = _packageFee.CalculateMessageFee();
            MessageLog row = new MessageLog(date,number,_fee,_packageFee.get_type());
            return row;
        }
        cursor.close();
        return null;
    }
    public se.uit.chichssssteam.quanlicuocdidong.DB.CallLog GetLastedOutGoingCall()
    {

        Cursor cursor = _context.getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null, null, CallLog.Calls.DATE + " DESC");
        int number = cursor.getColumnIndex(CallLog.Calls.NUMBER);
        int callType = cursor.getColumnIndex(CallLog.Calls.TYPE);
        int date = cursor.getColumnIndex(CallLog.Calls.DATE);
        int duration = cursor.getColumnIndex(CallLog.Calls.DURATION);
        if(cursor.moveToFirst()) {
            String _number = cursor.getString(number);
            String _callType = cursor.getString(callType);
            String _callDate = cursor.getString(date);
            int _callDuration = cursor.getInt(duration);
            int dircode = Integer.parseInt(_callType);
            if (dircode == CallLog.Calls.OUTGOING_TYPE) {
                _packageFee.set_callDuration(_callDuration);
                _packageFee.set_outGoingPhoneNumber(_number);
                _packageFee.set_callTime(ConvertToTimeSpan(_callDate));
                int _fee = _packageFee.CalculateCallFee();
                se.uit.chichssssteam.quanlicuocdidong.DB.CallLog target;
                target = new
                        se.uit.chichssssteam.quanlicuocdidong.DB.CallLog(-1, _callDate, _number, _callDuration, _fee, _packageFee.get_type());
                return target;

            }
        }
        cursor.close();
        return null;

    }

}

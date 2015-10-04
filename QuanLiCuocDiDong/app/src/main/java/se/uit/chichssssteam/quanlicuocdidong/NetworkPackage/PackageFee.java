package se.uit.chichssssteam.quanlicuocdidong.NetworkPackage;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
/**
 * Created by justinvan on 03-Oct-15.
 */
public abstract class PackageFee
{
    //Attributes
    protected String _ownNumber;
    protected String _outGoingPhoneNumber;
    protected int _callDuration;
    protected int _callFee;
    protected int _messageFee;
    protected NumberHeaderManager _numberHeader;
    //
    protected String _callTime;
    protected String _sendMessageTime;
    protected int _callBlock;
    protected int _internalCallFee;
    protected int _outerCallFee;
    protected int _internalMessageFee;
    protected int _outerMessageFee;
    //

    //get methods
    public String get_sendMessageTime(){return this._sendMessageTime;}
    public String get_callTime()
    {
        return this._callTime;
    }
    public String get_ownNumber()
    {
        return this._ownNumber;
    }
    public String get_outGoingPhoneNumber()
    {
        return this._outGoingPhoneNumber;
    }
    public int get_callDuration()
    {
        return this._callDuration;
    }
    public int get_callFee()
    {
        return this._callFee;
    }
    public int get_messageFee()
    {
        return this._messageFee;
    }

    //set methods
    public void set_sendMessageTime(String messageTime){this._sendMessageTime = messageTime;}
    public void set_callTime(String time)
    {
        this._callTime = time;
    }
    public void set_ownNumber(String number)
    {
        this._ownNumber = number;
    }
    public void set_outGoingPhoneNumber(String number)
    {
        this._outGoingPhoneNumber = number;
    }
    public void set_callDuration(int callduration)
    {
        this._callDuration = callduration;
    }
    public void set_callFee(int callFee)
    {
        this._callFee = callFee;
    }
    public void set_messageFee(int messageFee)
    {
        this._messageFee = messageFee;
    }

    //Methods
    public PackageFee()
    {
        _callTime = "";
        _sendMessageTime = "";
        _numberHeader = new NumberHeaderManager();
        _ownNumber="";
        _outGoingPhoneNumber ="";
        _callDuration = 0;
        _callFee = 0;
        _messageFee = 0;
        _callBlock = 6;

    }
    public PackageFee(String ownNumber, String outGoingPhoneNumber, int callDuration, int callFee, int messageFee)
    {

        _callTime = "";
        _sendMessageTime = "";
        _numberHeader = new NumberHeaderManager();
        _ownNumber = ownNumber;
        _outGoingPhoneNumber = outGoingPhoneNumber;
        _callDuration = callDuration;
        _callFee = callFee;
        _messageFee = messageFee;
        _callBlock = 6;

    }

    public int CalculateCallFee()
    {
        if(this._numberHeader.isEmergencyCall(this._outGoingPhoneNumber))
        {
            this._callFee = 0;
            return this._internalCallFee;
        }
        if(this._numberHeader.isInternalNetwork(this._ownNumber, this._outGoingPhoneNumber))
        {
            if(this._callDuration <= this._callBlock)
                this._callFee = this._internalCallFee/10;
            else
            {
                int remainDuration = this._callDuration- this._callBlock;
                this._callFee = (this._internalCallFee/10) + remainDuration*Math.round((this._internalCallFee/60));
            }
        }
        else
        {
            if(this._callDuration <= this._callBlock)
                this._callFee  = this._outerCallFee/10;
            else
            {
                int remainDuration = this._callDuration - this._callBlock;
                this._callFee  = (this._outerCallFee/10) + remainDuration*Math.round((float)(this._outerCallFee/60));
            }
        }
        return this._callFee ;
    }
    public boolean isSpecialTime()
    {
        try
        {
            String timeRange1 = "6:00";

            Date time1 = new SimpleDateFormat("HH:mm").parse(timeRange1);
            Calendar c1 = Calendar.getInstance();
            c1.setTime(time1);

            String timeRange2 = "8:00";
            Date time2 = new SimpleDateFormat("HH:mm").parse(timeRange2);
            Calendar c2 = Calendar.getInstance();
            c2.setTime(time2);
            c2.add(Calendar.DATE, 1);
            String timeRange3 = "12:00";
            Date time3 = new SimpleDateFormat("HH:mm").parse(timeRange3);
            Calendar c3= Calendar.getInstance();
            c3.setTime(time3);
            c3.add(Calendar.DATE, 1);

            String timeRange4 = "13:00";
            Date time4 = new SimpleDateFormat("HH:mm").parse(timeRange4);
            Calendar c4 = Calendar.getInstance();
            c4.setTime(time4);
            c4.add(Calendar.DATE, 1);

            Date timeSpan = new SimpleDateFormat("HH:mm").parse(_callTime);
            Calendar c5 = Calendar.getInstance();
            c5.setTime(timeSpan);
            c5.add(Calendar.DATE, 1);
            Date compareDate = c5.getTime();

            if(compareDate.compareTo(c1.getTime()) == 0)
                return true;
            if(compareDate.compareTo(c2.getTime()) == 0)
                return true;
            if(compareDate.compareTo(c3.getTime()) == 0)
                return true;
            if(compareDate.compareTo(c4.getTime()) == 0)
                return true;
            if(compareDate.after(c1.getTime()) && compareDate.before(c2.getTime()))
                return true;
            if(compareDate.after(c3.getTime()) && compareDate.before(c4.getTime()))
                return true;



        }
        catch(ParseException e)
        {
            e.printStackTrace();
        }
        return false;

    }
    public int CalculateMessageFee()
    {

        if(this._numberHeader.isInternalNetwork(this._ownNumber, this._outGoingPhoneNumber))
        {
            return this._internalMessageFee;
        }
        else
            return this._outerMessageFee;

    }

}
package se.uit.chichssssteam.quanlicuocdidong;

/**
 * Created by justinvan on 03-Oct-15.
 */
public class CallLog
{
    private int _callId;
    private String _callDate;
    private String _callNumber;
    private int _callDuration;
    private int _callFee;
    public CallLog()
    {
        _callId = -1;
        _callDate = "";
        _callNumber = "";
        _callDuration = 0;
        _callFee = 0;

    }
    public int get_callId()
    {
        return _callId;
    }
    public String get_callDate()
    {
        return _callDate;
    }
    public String get_callNumber()
    {
        return _callNumber;
    }
    public int get_callDuration()
    {
        return _callDuration;
    }
    public int get_callFee()
    {
        return _callFee;
    }

    public void set_callId(int callId)
    {
        _callId = callId;
    }
    public void set_callDate(String callDate)
    {
        _callDate = callDate;
    }
    public void set_callNumber(String callNumber)
    {
        _callNumber = callNumber;
    }
    public void set_callDuration(int callDuration)
    {
        _callDuration = callDuration;
    }
    public void set_callFee(int callFee)
    {
        _callFee = callFee;
    }
}

package se.uit.chichssssteam.quanlicuocdidong.DB;

/**
 * Created by justinvan on 03-Oct-15.
 */
public class MessageLog
{
    private int _messageId;
    private String _messageDate;
    private String _receiverNumber;
    private int _messageFee;

    public MessageLog()
    {
        _messageId = -1;
        _messageDate = "";
        _receiverNumber = "";
        _messageFee = 0;
    }

    public MessageLog(String date, String number, int fee)
    {
        _messageId = -1;
        _messageDate = date;
        _receiverNumber = number;
        _messageFee = fee;
    }

    public int get_messageId()
    {
        return _messageId;
    }
    public int get_messageFee()
    {
        return _messageFee;
    }
    public String get_messageDate()
    {
        return _messageDate;
    }
    public String get_receiverNumber()
    {
        return _receiverNumber;
    }

    public void set_messageId(int messageId)
    {
        _messageId = messageId;
    }
    public void set_messageDate(String messageDate)
    {
        _messageDate = messageDate;
    }
    public void set_messageFee(int messageFee)
    {
        _messageFee= messageFee;
    }
    public void set_recieverNumber(String recieverNumber)
    {
        _receiverNumber = recieverNumber;
    }
}

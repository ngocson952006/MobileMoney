package se.uit.battleteam.quanlicuocdidong.Manager;

/**
 * Created by QNghi on 16/12/2015.
 */
public class Contact {
    private String _name;
    private String _phoneNumber;
    public Contact(String name, String phoneNumber)
    {
        _name = name;
        _phoneNumber = phoneNumber;
    }

    public String get_name() {
        return _name;
    }

    public String get_phoneNumber() {
        return _phoneNumber;
    }
}

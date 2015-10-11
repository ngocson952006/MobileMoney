package se.uit.chichssssteam.quanlicuocdidong.DB;

/**
 * Created by justinvan on 03-Oct-15.
 */
public class Statistic
{
    private int _month;
    private int _year;
    private int _innerCallFee;
    private int _outerCallFee;
    private int _innerMessageFee;
    private int _outerMessageFee;

    public Statistic() {
        _month = 0;
        _year = 0;
        _innerCallFee = 0;
        _outerCallFee = 0;
        _innerMessageFee = 0;
        _outerMessageFee = 0;
    }

    public int get_month() {
        return _month;
    }

    public int get_year() {
        return _year;
    }

    public int get_innerCallFee() {
        return _innerCallFee;
    }

    public int get_outerCallFee() {
        return _outerCallFee;
    }

    public int get_innerMessageFee() {
        return _innerMessageFee;
    }

    public int get_outerMessageFee() {
        return _outerMessageFee;
    }

    public void set_month(int month) {
        _month = month;
    }

    public void set_year(int year) {
        _year = year;
    }

    public void set_innerCallFee(int innerCallFee) {
        _innerCallFee = innerCallFee;
    }

    public void set_outerCallFee(int outerCallFee)
    {
        _outerMessageFee = outerCallFee;
    }
    public void set_innerMessageFee(int innerMessageFee)
    {
        _innerMessageFee = innerMessageFee;
    }
    public void set_outerMessageFee(int outerMessageFee)
    {
        _outerMessageFee = outerMessageFee;
    }
}

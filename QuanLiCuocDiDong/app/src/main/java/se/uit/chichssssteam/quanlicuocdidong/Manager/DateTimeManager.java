package se.uit.chichssssteam.quanlicuocdidong.Manager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by justinvan on 22-Oct-15.
 */
public class DateTimeManager
{
    private static DateTimeManager _instance;
    public static synchronized  DateTimeManager get_instance()
    {
        if(_instance == null)
            _instance = new DateTimeManager();
        return _instance;
    }
    public String normalizeDateTime(String date)
    {
        SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.ENGLISH);
        try
        {
            Date d = format.parse(date);
            return d.toString();
        }
        catch(ParseException e)
        {
            e.printStackTrace();
        }
        return "";
    }
    public long convertToMilisec(String date)
    {
        try
        {
            SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.ENGLISH);
            Date d = format.parse(date);
            return d.getTime();
        }
        catch(ParseException e)
        {
            e.printStackTrace();
        }
        return 0;
    }
    public String convertToHm(String date)
    {
        try {
            SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.ENGLISH);
            Date d = format.parse(date);
            SimpleDateFormat serverFormat = new SimpleDateFormat("HH:mm",Locale.ENGLISH);
            return serverFormat.format(d);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    public String convertToDMYHms(String date)
    {
        try {
            SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.ENGLISH);
            Date d = format.parse(date);
            SimpleDateFormat serverFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss",Locale.ENGLISH);
            return serverFormat.format(d);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    public String convertMilisecToDatetime(long milisecs)
    {
        Date temp = new Date(milisecs);
        return temp.toString();
    }
    public int getMonth(String date)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss", Locale.ENGLISH);
        Calendar calendar = Calendar.getInstance();
        try
        {
            calendar.setTime(simpleDateFormat.parse(date));
            int month = calendar.get(Calendar.MONTH) +1;
            return month;

        }
        catch(ParseException e)
        {
            e.printStackTrace();
        }
        return 0;
    }
    public int getYear(String date)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss", Locale.ENGLISH);
        Calendar calendar = Calendar.getInstance();
        try
        {
            calendar.setTime(simpleDateFormat.parse(date));
            int year = calendar.get(Calendar.YEAR);
            return year;

        }
        catch(ParseException e)
        {
            e.printStackTrace();
        }
        return 0;
    }
    public String convertToMinutesAndSec( long sec)
    {
        int minutes = 0;
        if(sec > 60)
        {
            minutes = (int) (sec/60);
            sec = sec - minutes*60;
            return minutes + "p" + sec + "s";
        }
        return sec + "s";
    }
}

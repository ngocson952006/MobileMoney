package se.uit.chichssssteam.quanlicuocdidong.DB;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by justinvan on 03-Oct-15.
 */
public class DbHelper extends SQLiteOpenHelper
{
    private static DbHelper _singleton;
    //CALL LOG TABLE
    public static final String CALL_TABLE  = "CALLLOG";
    public static final String CALL_ID = "CallId";
    public static final String CALL_DATE = "CallDate";
    public static final String CALL_NUMBER = "CallNumber";
    public static final String DURATION = "Duration";
    public static final String CALL_FEE = "CallFee";
    public static final String CALL_TYPE = "CallType";
    //MESSAGE LOG TABLE

    public static final String MESSAGE_TABLE = "MESSAGELOG";
    public static final String MESS_ID = "MessageId";
    public static final String MESSAGE_DATE = "MessageDate";
    public static final String RECEIVER = "ReceiverNumber";
    public static final String MESSAGE_FEE = "MessageFee";
    public static final String MESSAGE_TYPE = "MessageType";

    //STATISTIC TABLE
    public static final String STATISTIC_TABLE = "STATISTIC";
    public static final String MONTH = "Month";
    public static final String YEAR = "Year";
    public static final String INNER_CALL_FEE = "InnerCallFee";
    public static final String OUTER_CALL_FEE = "OuterCallFee";
    public static final String INNER_MESSAGE_FEE = "InnerMessageFee";
    public static final String OUTER_MESSAGE_FEE = "OuterMessageFee";

    //DATABASE INFO
    private static final String DATABASE_NAME = "MyDatabase.db";
    private static final int DATABASE_VERSION = 1;

    //CREATE TABLE STATEMENT
    //CREATE CALL LOG TABLE
    private static final String CREATE_CALL_LOG_TABLE = "create table "+ CALL_TABLE + "("
            + CALL_ID + " integer primary key autoincrement,"
            + CALL_DATE + " bigint not null,"
            + CALL_NUMBER + " varchar(11) not null,"
            + DURATION + " integer not null,"
            + CALL_FEE + " integer,"
            + CALL_TYPE + " integer" +");";
    //CREATE MESSAGE LOG TABLE
    private static final String CREATE_MESSAGE_LOG_TABLE ="create table " + MESSAGE_TABLE + "("
            + MESS_ID + " integer primary key autoincrement,"
            + MESSAGE_DATE + " bigint not null,"
            + RECEIVER + " varchar(11) not null,"
            + MESSAGE_FEE + " integer,"
            + MESSAGE_TYPE + " integer" +");";
    //CREATE STATISTIC TABLE
    private static final String CREATE_STATISTIC_TABLE = "create table " + STATISTIC_TABLE + "("
            + MONTH + " integer,"
            + YEAR + " integer,"
            + INNER_CALL_FEE + " integer,"
            + OUTER_CALL_FEE + " integer,"
            + INNER_MESSAGE_FEE + " integer,"
            + OUTER_MESSAGE_FEE + " integer,"
            + "primary key (" + MONTH +"," + YEAR + ")" + ");";


    public DbHelper(Context context)
    {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }


    //Singletton Pattern
    public static synchronized DbHelper getInstance(Context context)
    {
        if(_singleton == null)
            _singleton = new DbHelper(context);
            return _singleton;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(CREATE_CALL_LOG_TABLE);
        db.execSQL(CREATE_MESSAGE_LOG_TABLE);
        db.execSQL(CREATE_STATISTIC_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        Log.w(DbHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to " + newVersion +
                        " , which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS" + CALL_TABLE);
        db.execSQL("DROP TABLE IF EXISTS" + MESSAGE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS" + STATISTIC_TABLE);

        onCreate(db);
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
    public String convertToDMYHms(String date)
    {
        try {
            SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.ENGLISH);
            Date d = format.parse(date);
            SimpleDateFormat serverFormat = new SimpleDateFormat("MMM dd yyyy HH:mm::ss",Locale.ENGLISH);
            return serverFormat.format(d);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}

package se.uit.chichssssteam.quanlicuocdidong.DB;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by justinvan on 03-Oct-15.
 */
public class DbHelper extends SQLiteOpenHelper
{
    //CALL LOG TABLE
    public static final String CALL_TABLE  = "CALLLOG";
    public static final String CALL_ID = "CallId";
    public static final String CALL_DATE = "CallDate";
    public static final String CALL_NUMBER = "CallNumber";
    public static final String DURATION = "Duration";
    public static final String CALL_FEE = "CallFee";

    //MESSAGE LOG TABLE

    public static final String MESSAGE_TABLE = "MESSAGELOG";
    public static final String MESS_ID = "MessageId";
    public static final String MESSAGE_DATE = "MessageDate";
    public static final String RECIEVER = "RecieverNumber";
    public static final String MESSAGE_FEE = "MessageFee";

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
            + CALL_DATE + " smalldatetime not null,"
            + CALL_NUMBER + " varchar(11) not null,"
            + DURATION + " integer not null,"
            + CALL_FEE + " integer" +");";
    //CREATE MESSAGE LOG TABLE
    private static final String CREATE_MESSAGE_LOG_TABLE ="create table " + MESSAGE_TABLE + "("
            + MESS_ID + " integer primary key autoincrement,"
            + MESSAGE_DATE + " smalldatetime not null,"
            + RECIEVER + " varchar(11) not null,"
            + MESSAGE_FEE + " integer"+ ");";
    //CREATE STATISTIC TABLE
    private static final String CREATE_STATISTIC_TABLE = "create table " + STATISTIC_TABLE + "("
            + MONTH + " integer,"
            + YEAR + " integer,"
            + INNER_CALL_FEE + " integer,"
            + OUTER_CALL_FEE + " integer,"
            + INNER_MESSAGE_FEE + " integer,"
            + OUTER_MESSAGE_FEE + " integer,"
            + "constraint PK_Month_Year primary key (" + MONTH +"," + YEAR + ")" + ");";


    public DbHelper(Context context)
    {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
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

}

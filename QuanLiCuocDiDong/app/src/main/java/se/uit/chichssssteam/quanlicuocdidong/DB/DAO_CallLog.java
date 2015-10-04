package se.uit.chichssssteam.quanlicuocdidong.DB;

/**
 * Created by justinvan on 03-Oct-15.
 */

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DAO_CallLog
{
    private SQLiteDatabase _database;
    private DbHelper _dbHelper;
    private String[] _listColumn = {_dbHelper.CALL_ID, _dbHelper.CALL_DATE,
            _dbHelper.CALL_NUMBER,_dbHelper.DURATION, _dbHelper.CALL_FEE};

    public DAO_CallLog(Context context)
    {
        _dbHelper = new DbHelper(context);
    }
    public void Open() throws  SQLException
    {
        _database = _dbHelper.getWritableDatabase();
        _database = _dbHelper.getReadableDatabase();
    }
    public void Close()
    {
        _dbHelper.close();
    }

    public CallLog CursortoCallLog(Cursor c)
    {
        CallLog row = new CallLog();
        row.set_callId(c.getInt(0));
        row.set_callDate(c.getString(1));
        row.set_callNumber(c.getString(2));
        row.set_callDuration(c.getInt(3));
        row.set_callFee(c.getInt(4));
        return row;
    }

    public CallLog CreateCallLogRow(String callDate, String callNumber, int duration, int callFee)
    {

        ContentValues values = new ContentValues();
        values.put(_dbHelper.CALL_DATE,callDate);
        values.put(_dbHelper.CALL_NUMBER, callNumber);
        values.put(_dbHelper.DURATION, duration);
        values.put(_dbHelper.CALL_FEE, callFee);
        long insertId = _database.insert(_dbHelper.CALL_TABLE,null, values);
        Cursor cursor = _database.query(_dbHelper.CALL_TABLE, _listColumn,_dbHelper.CALL_ID + " = " + insertId,null,null,null,null);
        cursor.moveToFirst();
        CallLog newRow = CursortoCallLog(cursor);
        cursor.close();
        return  newRow;

    }
    public void DeleteCallLogRow(int callId)
    {
        _database.delete(_dbHelper.CALL_TABLE, _dbHelper.CALL_ID + " = " + callId,null);
    }
    public List<CallLog> GetAllCallLog()
    {

        List<CallLog> _listCall = new ArrayList<CallLog>();
        Cursor cursor = _database.query(_dbHelper.CALL_TABLE,_listColumn,null,null,null,null,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast())
        {

            CallLog callLog = CursortoCallLog(cursor);
            _listCall.add(callLog);
            cursor.moveToNext();
        }
        cursor.close();
        return _listCall;
    }
    public CallLog FindCallLogById(int _id)
    {
        CallLog temp;
        Cursor cursor = _database.query(_dbHelper.CALL_TABLE,_listColumn,_dbHelper.CALL_ID + "=" + _id,null,null,null,null);
        if(cursor == null)
            return null;
        else
        {
            cursor.moveToFirst();
            temp = CursortoCallLog(cursor);
        }
        cursor.close();
        return temp;
    }
}

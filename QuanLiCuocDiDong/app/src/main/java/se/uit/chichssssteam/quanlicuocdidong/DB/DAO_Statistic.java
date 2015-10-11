package se.uit.chichssssteam.quanlicuocdidong.DB;


import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by justinvan on 03-Oct-15.
 */
public class DAO_Statistic
{
    private SQLiteDatabase _database;
    private DbHelper _dbHelper;
    private String[] _listColumn = {_dbHelper.MONTH, _dbHelper.YEAR, _dbHelper.INNER_CALL_FEE, _dbHelper.OUTER_CALL_FEE,
            _dbHelper.INNER_MESSAGE_FEE, _dbHelper.OUTER_MESSAGE_FEE};

    public DAO_Statistic(Context context)
    {
        _dbHelper = _dbHelper.getInstance(context);
    }
    public void Open() throws SQLException
    {
        _database = _dbHelper.getWritableDatabase();
        _database = _dbHelper.getReadableDatabase();
    }
    public void Close()
    {
        _dbHelper.close();
    }
    public Statistic CursortoStatistic(Cursor cursor)
    {
        Statistic row = new Statistic();
        row.set_month(cursor.getInt(0));
        row.set_year(cursor.getInt(1));
        row.set_innerCallFee(cursor.getInt(2));
        row.set_outerCallFee(cursor.getInt(3));
        row.set_innerMessageFee(cursor.getInt(4));
        row.set_outerMessageFee(cursor.getInt(5));
        return row;
    }
    public Statistic CreateStatisticRow(int month, int year, int innerCallFee,
                                        int outerCallFee, int innerMessageFee, int outerMessageFee)
    {
        ContentValues values = new ContentValues();
        values.put(_dbHelper.MONTH, month);
        values.put(_dbHelper.YEAR, year);
        values.put(_dbHelper.INNER_CALL_FEE, innerCallFee);
        values.put(_dbHelper.OUTER_CALL_FEE, outerCallFee);
        values.put(_dbHelper.INNER_MESSAGE_FEE, innerMessageFee);
        values.put(_dbHelper.OUTER_MESSAGE_FEE, outerCallFee);
        _database.insert(_dbHelper.STATISTIC_TABLE, null, values);
        Cursor cursor = _database.query(_dbHelper.STATISTIC_TABLE,_listColumn, _dbHelper.MONTH + "=" + month + " AND "
                + _dbHelper.YEAR + "=" + year,null,null,null,null);
        cursor.moveToFirst();
        Statistic row = CursortoStatistic(cursor);
        cursor.close();
        return row;
    }
    public void DeleteStatisticRow(int month, int year)
    {
        _database.delete(_dbHelper.STATISTIC_TABLE, _dbHelper.MONTH + " = " + month + " AND " +
                _dbHelper.YEAR + " = " + year,null);
    }
    public List<Statistic> GetAllStatistic()
    {
        List<Statistic> _listStatistic = new ArrayList<Statistic>();
        Cursor cursor = _database.query(_dbHelper.STATISTIC_TABLE, _listColumn, null,null,null,null,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast())
        {
            Statistic temp;
            temp = CursortoStatistic(cursor);
            _listStatistic.add(temp);
            cursor.moveToNext();
        }
        cursor.close();
        return _listStatistic;
    }
    public Statistic FindStatisticByMonthYear(int month, int year)
    {
        Statistic result;
        Cursor cursor = _database.query(_dbHelper.STATISTIC_TABLE, _listColumn,  _dbHelper.MONTH + "=" + month + " AND "
                + _dbHelper.YEAR + "=" + year,null,null,null,null);
        if(cursor == null)
            return null;
        else
        {
            result = CursortoStatistic(cursor);
        }
        cursor.close();
        return result;
    }
    public void UpdateStatisticRow(int month, int year, int value, String columnName)
    {
        ContentValues newValue = new ContentValues();
        newValue.put(columnName,value);
        String whereClause = _dbHelper.MONTH + " = " + month + " AND " + _dbHelper.YEAR + " = " +year;
        int _rowAffect;
        _rowAffect = _database.update(_dbHelper.STATISTIC_TABLE,newValue,whereClause, null);
    }

}

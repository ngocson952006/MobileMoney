package se.uit.battleteam.quanlicuocdidong.Manager;

import android.content.Context;
import android.os.SystemClock;
import android.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import se.uit.battleteam.quanlicuocdidong.DB.DAO_Statistic;
import se.uit.battleteam.quanlicuocdidong.DB.Statistic;
import se.uit.battleteam.quanlicuocdidong.R;

/**
 * Created by QNghi on 23/10/2015.
 */
public class DataMonthFee {
    public static Context _myContext;
    public static List<Statistic> monthStatistics;
    public static Statistic temp;
    public static String[] titles;
    public static MonthFee[][] monthFeess;
    public static List<Pair<String, List<MonthFee>>> getAllData(Context myContext) {
        _myContext = myContext;
        DAO_Statistic dao_statistic = new DAO_Statistic(_myContext);
        dao_statistic.Open();
        monthStatistics = dao_statistic.GetAllStatistic();
        int n = monthStatistics.size();
        titles = new String[n];
        monthFeess = new MonthFee[n][1];
        for (int i = 0; i < n; i++) {
            temp = monthStatistics.get(i);
            titles[i] = _myContext.getString(R.string.textThang) + temp.get_month() + "/" +  temp.get_year();
            monthFeess[i][0] = new MonthFee(String.valueOf(temp.get_month()),String.valueOf(temp.get_year()),
                                    DateTimeManager.get_instance().convertToMinutesAndSec(temp.get_innerCallDuration()),temp.get_innerCallFee(),
                                    DateTimeManager.get_instance().convertToMinutesAndSec(temp.get_outerCallDuration()),temp.get_outerCallFee(),
                                    temp.get_innerMessageCount(),temp.get_innerMessageFee(),
                                    temp.get_outerMessageCount(),temp.get_outerMessageFee());
        }
        List<Pair<String, List<MonthFee>>> res = new ArrayList<Pair<String, List<MonthFee>>>();

        for (int i = 0; i < n; i++) {
            res.add(getOneSection(i));
        }

        return res;
    }

    public static List<MonthFee> getFlattenedData() {
        List<MonthFee> res = new ArrayList<MonthFee>();

        for (int i = 0; i < 4; i++) {
            res.addAll(getOneSection(i).second);
        }

        return res;
    }

    public static Pair<Boolean, List<MonthFee>> getRows(int page) {
        List<MonthFee> flattenedData = getFlattenedData();
        if (page == 1) {
            return new Pair<Boolean, List<MonthFee>>(true, flattenedData.subList(0, 5));
        } else {
            SystemClock.sleep(2000); // simulate loading
            return new Pair<Boolean, List<MonthFee>>(page * 5 < flattenedData.size(), flattenedData.subList((page - 1) * 5, Math.min(page * 5, flattenedData.size())));
        }
    }

    public static Pair<String, List<MonthFee>> getOneSection(int index) {
        return new Pair<String, List<MonthFee>>(titles[index], Arrays.asList(monthFeess[index]));
    }
}

package se.uit.chichssssteam.quanlicuocdidong.Manager;

/**
 * Created by QNghi on 28/10/2015.
 */
public class DayFee extends MonthFee{
    private String day;
    public DayFee()
    {

    }
    public DayFee(String day, String month, String year,
                    String minutes_innerCall , int fee_innerCall,
                    String minutes_outerCall, int fee_outerCall,
                    int number_innerMess, int fee_innerMess,
                    int number_outerMess, int fee_outerMess) {
        super(month,year,minutes_innerCall,fee_innerCall,minutes_outerCall,fee_outerCall,
                number_innerMess,fee_innerMess,number_outerMess,fee_outerMess);
        this.day = day;
    }
    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}

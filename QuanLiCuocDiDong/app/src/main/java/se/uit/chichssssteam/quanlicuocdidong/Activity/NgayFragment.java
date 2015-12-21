package se.uit.chichssssteam.quanlicuocdidong.Activity;
import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import com.codetroopers.betterpickers.calendardatepicker.CalendarDatePickerDialogFragment;
import com.codetroopers.betterpickers.calendardatepicker.MonthAdapter;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import se.uit.chichssssteam.quanlicuocdidong.DB.CallLog;
import se.uit.chichssssteam.quanlicuocdidong.DB.DAO_CallLog;
import se.uit.chichssssteam.quanlicuocdidong.DB.DAO_MessageLog;
import se.uit.chichssssteam.quanlicuocdidong.DB.MessageLog;
import se.uit.chichssssteam.quanlicuocdidong.Manager.CalllogArrayAdapter;
import se.uit.chichssssteam.quanlicuocdidong.Manager.DateTimeManager;
import se.uit.chichssssteam.quanlicuocdidong.Manager.DayFee;
import se.uit.chichssssteam.quanlicuocdidong.Manager.MessLogArrayAdapter;
import se.uit.chichssssteam.quanlicuocdidong.Manager.OnFragmentInteractionListener;
import se.uit.chichssssteam.quanlicuocdidong.R;
public class NgayFragment extends Fragment
        implements CalendarDatePickerDialogFragment.OnDateSetListener  {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String FRAG_TAG_DATE_PICKER = "fragment_date_picker_name";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String currency;
    private OnFragmentInteractionListener mListener;
    private ImageButton imageButtonCalendar;
    private TextView textViewCalendarValue;
    private int day, month, year;
    private int oldestDay,oldestMonth,oldestYear;
    TextView textViewTongTienGoi;
    TextView textViewSoPhutGoiNoiMang;
    TextView textViewTienGoiNoiMang;
    TextView textViewSoPhutGoiNgoaiMang;
    TextView textViewTienGoiNgoaiMang;
    TextView textViewTongTienSmS;
    TextView textViewSoSmsNoiMang;
    TextView textViewTienSmSNoiMang;
    TextView textViewSoSmsNgoaiMang;
    TextView textViewTienSmsNgoaiMang;
    TextView textViewTongTien;
    List<CallLog> callLogList;
    List<MessageLog> messLogList;
    CalllogArrayAdapter calllogAdapter;
    MessLogArrayAdapter messLogAdapter;
    ListView listViewCallLog;
    ListView listViewMessLog;
    DAO_MessageLog dao_messageLog;
    DAO_CallLog dao_callLog;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NgayFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NgayFragment newInstance(String param1, String param2) {
        NgayFragment fragment = new NgayFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public NgayFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        currency =  getContext().getString(R.string.textCurrency);
        DateTime now = DateTime.now();
        this.year = now.getYear();
        this.month = now.getMonthOfYear();
        this.day = now.getDayOfMonth();
        createConnect();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ngay, container, false);
        getControl(view);
        getLog();
        addEvent();


        return view;
    }

    private void getControl(View view)
    {
        imageButtonCalendar = (ImageButton) view.findViewById(R.id.imageButtonCalendar);
        textViewCalendarValue = (TextView) view.findViewById(R.id.textViewValueOfCalendar);
        textViewTongTienGoi = (TextView) view.findViewById(R.id.textViewTongTienGoi);
        textViewSoPhutGoiNoiMang = (TextView) view.findViewById(R.id.textViewSoPhutGoiNoiMang);
        textViewTienGoiNoiMang = (TextView) view.findViewById(R.id.textViewTienGoiNoiMang);
        textViewSoPhutGoiNgoaiMang = (TextView) view.findViewById(R.id.textViewSoPhutGoiNgoaiMang);
        textViewTienGoiNgoaiMang = (TextView) view.findViewById(R.id.textViewTienGoiNgoaiMang);
        textViewTongTienSmS = (TextView) view.findViewById(R.id.textViewTongTienSmS);
        textViewSoSmsNoiMang = (TextView) view.findViewById(R.id.textViewSoSmsNoiMang);
        textViewTienSmSNoiMang = (TextView) view.findViewById(R.id.textViewTienSmSNoiMang);
        textViewSoSmsNgoaiMang = (TextView) view.findViewById(R.id.textViewSoSmsNgoaiMang);
        textViewTienSmsNgoaiMang = (TextView) view.findViewById(R.id.textViewTienSmsNgoaiMang);
        textViewTongTien = (TextView) view.findViewById(R.id.textViewTongTien);

        listViewCallLog = (ListView) view.findViewById(R.id.listViewCallLog);
        listViewMessLog = (ListView) view.findViewById(R.id.listViewMessLog);

        final TabHost tab = (TabHost) view.findViewById(R.id.tabLog);
        tab.setup();
        TabHost.TabSpec spec;
        //Tạo tab1
        spec = tab.newTabSpec("t1");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Cuộc gọi");
        tab.addTab(spec);
        //Tạo tab2
        spec = tab.newTabSpec("t2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Tin nhắn");
        tab.addTab(spec);
        //Thiết lập tab mặc định được chọn ban đầu là tab 0
        tab.setCurrentTab(0);

    }

    private void addEvent()
    {
        imageButtonCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                DateTime now = DateTime.now();
                CalendarDatePickerDialogFragment calendarDatePickerDialogFragment = CalendarDatePickerDialogFragment
                        .newInstance(NgayFragment.this, now.getYear(), now.getMonthOfYear() - 1,
                                now.getDayOfMonth());

                calendarDatePickerDialogFragment.setDateRange(
                        new MonthAdapter.CalendarDay(oldestYear, oldestMonth - 1,  oldestDay),
                        new MonthAdapter.CalendarDay(now.getYear(), now.getMonthOfYear() - 1, now.getDayOfMonth()));

                calendarDatePickerDialogFragment.show(fm, FRAG_TAG_DATE_PICKER);
            }
        });;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onNgayFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDateSet(CalendarDatePickerDialogFragment dialog, int year, int monthOfYear, int dayOfMonth) {
        if (this.day != dayOfMonth || this.month != monthOfYear || this.year != year)
        {
            this.day = dayOfMonth;
            this.month = monthOfYear +1 ;
            this.year = year;
            getLog();
        }
    }

    private void createConnect()
    {
        dao_callLog = new DAO_CallLog(getContext());
        dao_callLog.Open();
        long callTime = dao_callLog.getOldestCallTime();

        dao_messageLog = new DAO_MessageLog(getContext());
        dao_messageLog.Open();
        long messTime = dao_messageLog.GetOldestMessageTime();

        long oldestTime;
        if(callTime < messTime)
            oldestTime = callTime;
        else
            oldestTime = messTime;
        oldestYear = DateTimeManager.get_instance().getYearFromMilisecs(oldestTime);
        oldestMonth = DateTimeManager.get_instance().getMonthFromMilisecs(oldestTime);
        oldestDay = DateTimeManager.get_instance().getDayhFromMilisecs(oldestTime);
    }
    private void getLog()
    {
        int numCallLog;
        int numMessLog;
        DateTime dateTime = new DateTime(this.year,this.month,this.day,0,0);
        long millis = dateTime.getMillis();
        callLogList = dao_callLog.GetCallLogInDay(millis);
        numCallLog = callLogList.size();
        messLogList = dao_messageLog.GetMessageLogInDay(millis);
        numMessLog = messLogList.size();

        CallLog tempCall;
        MessageLog tempMess;
        DayFee dayFee = new DayFee(this.day,this.month,this.year);
        int minutes_innerCall = 0;
        int minutes_outerCall = 0;
        int i;

        for (i=0;i< numCallLog;i++)
        {
            tempCall = callLogList.get(i);
            if (tempCall.get_callType() == 0) {
                minutes_innerCall += tempCall.get_callDuration();
                dayFee.addFee_innerCall(tempCall.get_callFee());

            } else
            {
                minutes_outerCall += tempCall.get_callDuration();
                dayFee.addFee_outerCall(tempCall.get_callFee());
            }
        }
        dayFee.setMinutes_innerCall(DateTimeManager.get_instance().convertToMinutesAndSec(minutes_innerCall));
        dayFee.setMinutes_outerCall(DateTimeManager.get_instance().convertToMinutesAndSec(minutes_outerCall));
        textViewTongTienGoi.setText(String.valueOf(dayFee.getFee_innerCall() + dayFee.getFee_outerCall()) + currency);
        textViewSoPhutGoiNoiMang.setText(dayFee.getMinutes_innerCall());
        textViewTienGoiNoiMang.setText(String.valueOf(dayFee.getFee_innerCall()) + currency);
        textViewSoPhutGoiNgoaiMang.setText(dayFee.getMinutes_outerCall());
        textViewTienGoiNgoaiMang.setText(String.valueOf(dayFee.getFee_outerCall()) + currency);


        for (i=0;i< numMessLog;i++)
        {
            tempMess = messLogList.get(i);
            if (tempMess.get_messageType() == 0)
            {
                dayFee.countUpNumber_innerMess();
                dayFee.addFee_innerMess(tempMess.get_messageFee());

            } else
            {
                dayFee.countUpNumber_outerMess();
                dayFee.addFee_outerMess(tempMess.get_messageFee());
            }
        }
        textViewTongTienSmS.setText(String.valueOf(dayFee.getFee_innerMess() + dayFee.getFee_outerMess()) + currency);
        textViewSoSmsNoiMang.setText(String.valueOf(dayFee.getNumber_innerMess()));
        textViewTienSmSNoiMang.setText(String.valueOf(dayFee.getFee_innerMess()) + currency);
        textViewSoSmsNgoaiMang.setText(String.valueOf(dayFee.getNumber_outerMess()));
        textViewTienSmsNgoaiMang.setText(String.valueOf(dayFee.getFee_outerMess()) + currency);

        textViewTongTien.setText(String.valueOf(dayFee.getFee_innerCall() + dayFee.getFee_outerCall()
         + dayFee.getFee_innerMess() + dayFee.getFee_outerMess()) + currency);


        calllogAdapter = new CalllogArrayAdapter(getActivity(),R.layout.daydetail_item_listview,callLogList);
        listViewCallLog.setAdapter(calllogAdapter);
        calllogAdapter.notifyDataSetChanged();

        messLogAdapter = new MessLogArrayAdapter(getActivity(),R.layout.daydetail_item_listview,messLogList);
        listViewMessLog.setAdapter(messLogAdapter);
        messLogAdapter.notifyDataSetChanged();

        textViewCalendarValue.setText(String.valueOf(day) + "/" + String.valueOf(month) + "/" + String.valueOf(year));

    }

}

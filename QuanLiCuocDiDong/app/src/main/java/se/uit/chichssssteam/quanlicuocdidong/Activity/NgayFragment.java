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
import android.widget.TextView;
import android.widget.Toast;

import com.codetroopers.betterpickers.calendardatepicker.CalendarDatePickerDialogFragment;
import com.codetroopers.betterpickers.calendardatepicker.MonthAdapter;
import com.codetroopers.betterpickers.datepicker.DatePickerBuilder;
import com.codetroopers.betterpickers.datepicker.DatePickerDialogFragment;

import org.joda.time.DateTime;

import java.util.List;

import se.uit.chichssssteam.quanlicuocdidong.DB.CallLog;
import se.uit.chichssssteam.quanlicuocdidong.DB.DAO_CallLog;
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
    private OnFragmentInteractionListener mListener;
    private ImageButton imageButtonCalendar;
    private TextView textViewCalendarValue;
    private ImageButton imageButtonDetail;
    private int day;
    private int month;
    private int year;
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

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ngay, container, false);
        getControlAndAddEvent(view);
        DateTime now = DateTime.now();
        this.day = now.getYear();
        this.month = now.getMonthOfYear();
        this.year = now.getDayOfMonth();
        upDateData();

        return view;
    }

    private void getControlAndAddEvent(View view)
    {
        imageButtonCalendar = (ImageButton) view.findViewById(R.id.imageButtonCalendar);
        textViewCalendarValue = (TextView) view.findViewById(R.id.textViewValueOfCalendar);
        imageButtonDetail = (ImageButton) view.findViewById(R.id.imageButtonDetail);
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
        imageButtonCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                DateTime now = DateTime.now();
                CalendarDatePickerDialogFragment calendarDatePickerDialogFragment = CalendarDatePickerDialogFragment
                        .newInstance(NgayFragment.this, now.getYear(), now.getMonthOfYear() - 1,
                                now.getDayOfMonth());

                calendarDatePickerDialogFragment.setDateRange(
                        new MonthAdapter.CalendarDay(now.getYear(), now.getMonthOfYear() - 2, now.getDayOfMonth()),
                        new MonthAdapter.CalendarDay(now.getYear(), now.getMonthOfYear() - 1, now.getDayOfMonth()));

                calendarDatePickerDialogFragment.show(fm, FRAG_TAG_DATE_PICKER);
            }
        });
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
    public static String getNameFragment()
    {
        return "Tra theo ngày";
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDateSet(CalendarDatePickerDialogFragment dialog, int year, int monthOfYear, int dayOfMonth) {
        Toast.makeText(getContext(), "Year: " + year + "\nMonth: " + monthOfYear + "\nDay: " + dayOfMonth, Toast.LENGTH_SHORT).show();
        this.day = dayOfMonth;
        this.month = monthOfYear;
        this.year = year;
        upDateData();
    }
    private void upDateData()
    {
        List<CallLog> callLogList;
        int n;
        DAO_CallLog dao_callLog = new DAO_CallLog(getContext());
        dao_callLog.Open();
        callLogList = dao_callLog.GetAllCallLog();
        n = callLogList.size();
        for (int i=0;i<n;i++)
        {
            Toast.makeText(getContext(), callLogList.get(i).get_callDate(), Toast.LENGTH_SHORT).show();
        }

        /*MonthFee monthFee = getItem(position);
        textViewTongTienGoi.setText(String.valueOf(monthFee.getFee_innerCall() + monthFee.getFee_outerCall()) + "đ");
        textViewSoPhutGoiNoiMang.setText(monthFee.getMinutes_innerCall());
        textViewTienGoiNoiMang.setText(String.valueOf(monthFee.getFee_innerCall()) + "đ");
        textViewSoPhutGoiNgoaiMang.setText(monthFee.getMinutes_outerCall());
        textViewTienGoiNgoaiMang.setText(String.valueOf(monthFee.getFee_outerCall()) + "đ");
        textViewTongTienSmS.setText(String.valueOf(monthFee.getFee_innerMess() + monthFee.getFee_outerMess()) + "đ");
        textViewSoSmsNoiMang.setText(String.valueOf(monthFee.getNumber_innerMess()));
        textViewTienSmSNoiMang.setText(String.valueOf(monthFee.getFee_innerMess()) + "đ");
        textViewSoSmsNgoaiMang.setText(String.valueOf(monthFee.getNumber_outerMess()));
        textViewTienSmsNgoaiMang.setText(String.valueOf(monthFee.getFee_outerMess()) + "đ");
        textViewTongTien.setText(String.valueOf(Integer.parseInt(textViewTongTienGoi.getText().toString()
        ) + Integer.parseInt(textViewTongTienSmS.getText().toString())) + "đ");
        */
    }
}

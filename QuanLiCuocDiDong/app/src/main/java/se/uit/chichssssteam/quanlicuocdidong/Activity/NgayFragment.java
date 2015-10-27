package se.uit.chichssssteam.quanlicuocdidong.Activity;
import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import se.uit.chichssssteam.quanlicuocdidong.DB.CallLog;
import se.uit.chichssssteam.quanlicuocdidong.DB.DAO_CallLog;
import se.uit.chichssssteam.quanlicuocdidong.DB.DAO_MessageLog;
import se.uit.chichssssteam.quanlicuocdidong.DB.MessageLog;
import se.uit.chichssssteam.quanlicuocdidong.Manager.OnFragmentInteractionListener;
import se.uit.chichssssteam.quanlicuocdidong.NetworkPackage.PackageFee;
import se.uit.chichssssteam.quanlicuocdidong.R;
public class NgayFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private DAO_CallLog _mycallLog;
    private OnFragmentInteractionListener mListener;
    private MainActivity _myActivity;
    private DAO_MessageLog _myMessageLog;
    private List<CallLog> _listCall;
    private List<MessageLog> _listMessageLog;
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
        _myActivity = (MainActivity)getActivity();
        _mycallLog = new DAO_CallLog(_myActivity);
        _myMessageLog = new DAO_MessageLog(_myActivity);
        _mycallLog.Open();

    }
    public void DisplayLogOnScreen()
    {
        // Code display all log here
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment

        _listCall = _mycallLog.GetAllCallLog();
        _listMessageLog = _myMessageLog.GetAllMessageLog();

        return inflater.inflate(R.layout.fragment_ngay, container, false);
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
        _mycallLog.Close();
        _myMessageLog.Close();
        super.onDestroy();
    }


}

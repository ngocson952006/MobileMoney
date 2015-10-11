package se.uit.chichssssteam.quanlicuocdidong.Activity;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import se.uit.chichssssteam.quanlicuocdidong.Manager.MyArrayUtiltyAdapter;
import se.uit.chichssssteam.quanlicuocdidong.Manager.OnFragmentInteractionListener;
import se.uit.chichssssteam.quanlicuocdidong.Manager.UtiltyItem;
import se.uit.chichssssteam.quanlicuocdidong.R;


public class TienIchFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    ListView lstViewUtilty;
    ArrayList<UtiltyItem> arrayListUtiltyItems = new ArrayList<UtiltyItem>();
    MyArrayUtiltyAdapter adapter = null;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TienIchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TienIchFragment newInstance(String param1, String param2) {
        TienIchFragment fragment = new TienIchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public TienIchFragment() {
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
    public void initList()
    {
        arrayListUtiltyItems.add(new UtiltyItem("Tin khuyến mãi","Các thông tin khuyến ãi của nhà mạng",R.drawable.qteen));
        adapter = new MyArrayUtiltyAdapter(getActivity(),R.layout.custom_utiltyitem_layout,arrayListUtiltyItems);
        lstViewUtilty.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        Toast.makeText(getActivity(),"jjjjj",Toast.LENGTH_LONG).show();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tien_ich, container, false);
        lstViewUtilty = (ListView) view.findViewById(R.id.listViewUtilty);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onTienIchFragmentInteraction(uri);
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
        return "Tiện ích";
    }

}

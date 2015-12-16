package se.uit.chichssssteam.quanlicuocdidong.Activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import se.uit.chichssssteam.quanlicuocdidong.Manager.OnFragmentInteractionListener;
import se.uit.chichssssteam.quanlicuocdidong.R;


public class CaiDatFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private int idImage;

    private OnFragmentInteractionListener mListener;

    CheckBox checkBoxPopup;
    SharedPreferences settings;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CaiDatFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CaiDatFragment newInstance(String param1, String param2) {
        CaiDatFragment fragment = new CaiDatFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public CaiDatFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }*/
        settings = getActivity().getSharedPreferences(MainActivity.PREFS_NAME, 0);
        mParam1 = settings.getString(MainActivity.KEY_GOICUOC, MainActivity.VALUE_DEFAULT);
        mParam2 = settings.getString(MainActivity.KEY_NHAMANG, MainActivity.VALUE_DEFAULT);
        idImage = settings.getInt(MainActivity.KEY_IDIMAGE, 0);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cai_dat, container, false);

        checkBoxPopup = (CheckBox) view.findViewById(R.id.checkBoxPopUp);
        checkBoxPopup.setChecked(settings.getBoolean(MainActivity.KEY_ALLOWPOPUP,false));

        Button buttonChangeMobileNW = (Button) view.findViewById(R.id.buttonClear);
        buttonChangeMobileNW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonPressed();
            }
        });

        TextView textViewGoiCuoc = (TextView) view.findViewById(R.id.textViewTenGoiCuoc);
        textViewGoiCuoc.setText(mParam1);
        TextView textViewTenNhaMang = (TextView) view.findViewById(R.id.textViewTenNhaMang);
        textViewTenNhaMang.setText(mParam2);

        ImageView imageViewLogo = (ImageView) view.findViewById(R.id.imageViewLogoGoiCuoc);
        imageViewLogo.setImageResource(idImage);


        // Inflate the layout for this fragment
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed() {
        if (mListener != null) {
            mListener.onCaiDatFragmentInteraction();
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
    public void onStop()
    {
        settings = getActivity().getSharedPreferences(MainActivity.PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        boolean AllowPopup;
        if (checkBoxPopup.isChecked())
            AllowPopup = true;
        else
            AllowPopup = false;
        editor.putBoolean(MainActivity.KEY_ALLOWPOPUP,AllowPopup);
        // Commit the edits!
        editor.commit();
        super.onStop();
    }


    public static String getNameFragment()
    {
        return "Cài đặt";
    }

}

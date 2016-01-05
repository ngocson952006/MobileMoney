package se.uit.battleteam.quanlicuocdidong.Activity;

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

import se.uit.battleteam.quanlicuocdidong.Manager.OnFragmentInteractionListener;
import se.uit.battleteam.quanlicuocdidong.NetworkPackage.PackageFee;
import se.uit.battleteam.quanlicuocdidong.R;


public class CaiDatFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String goiCuoc;
    private String nhaMang;
    private int idImage;
    private PackageFee _myPackageFee;
    private OnFragmentInteractionListener mListener;

    CheckBox checkBoxPopup;
    SharedPreferences settings;

    // TODO: Rename and change types and number of parameters
    public static CaiDatFragment newInstance(PackageFee myPackageFee) {
        CaiDatFragment fragment = new CaiDatFragment();
        Bundle args = new Bundle();
        args.putSerializable(MainActivity.KEY_INFO_MOBILENETWORK,myPackageFee);
        fragment.setArguments(args);
        return fragment;
    }

    public CaiDatFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            _myPackageFee = (PackageFee)getArguments().getSerializable(MainActivity.KEY_INFO_MOBILENETWORK);
        }
        settings = getActivity().getSharedPreferences(MainActivity.PREFS_NAME, 0);
        goiCuoc = settings.getString(MainActivity.KEY_GOICUOC, MainActivity.VALUE_DEFAULT);
        nhaMang = settings.getString(MainActivity.KEY_NHAMANG, MainActivity.VALUE_DEFAULT);
        idImage = settings.getInt(MainActivity.KEY_IDIMAGE, 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cai_dat, container, false);

        TextView textViewGoiCuoc = (TextView) view.findViewById(R.id.textViewTenGoiCuoc);
        textViewGoiCuoc.setText(goiCuoc);
        TextView textViewTenNhaMang = (TextView) view.findViewById(R.id.textViewTenNhaMang);
        textViewTenNhaMang.setText(nhaMang);
        TextView textViewPhiGoiNoiMang = (TextView) view.findViewById(R.id.textViewPhiGoiNoiMang);
        textViewPhiGoiNoiMang.setText(String.valueOf(_myPackageFee.get_internalCallFee()));
        TextView textViewPhiGoiNgoaiMang = (TextView) view.findViewById(R.id.textViewPhiGoiNgoaiMang);
        textViewPhiGoiNgoaiMang.setText(String.valueOf(_myPackageFee.get_outerCallFee()));
        TextView textViewPhiNhanTinNoiMang = (TextView) view.findViewById(R.id.textViewPhiNhanTinNoiMang);
        textViewPhiNhanTinNoiMang.setText(String.valueOf(_myPackageFee.get_internalMessageFee()));
        TextView textViewPhiNhanTinNgoaiMang = (TextView) view.findViewById(R.id.textViewPhiNhanTinNgoaiMang);
        textViewPhiNhanTinNgoaiMang.setText(String.valueOf(_myPackageFee.get_outerMessageFee()));

        ImageView imageViewLogo = (ImageView) view.findViewById(R.id.imageViewLogoGoiCuoc);
        imageViewLogo.setImageResource(idImage);

        Button buttonChangeMobileNW = (Button) view.findViewById(R.id.buttonClear);
        buttonChangeMobileNW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonPressed();
            }
        });

        checkBoxPopup = (CheckBox) view.findViewById(R.id.checkBoxPopUp);
        checkBoxPopup.setChecked(settings.getBoolean(MainActivity.KEY_ALLOWPOPUP, false));
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

}

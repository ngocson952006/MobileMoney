package se.uit.chichssssteam.quanlicuocdidong.Activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import se.uit.chichssssteam.quanlicuocdidong.Manager.OnFragmentInteractionListener;
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
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tien_ich, container, false);
        getControlAndAddEvent(view);
        return view;
    }

    void getControlAndAddEvent(View view)
    {
        ImageView imgKM = (ImageView) view.findViewById(R.id.imageViewThumbaiKhuyenMai);
        ImageView imgUTN = (ImageView) view.findViewById(R.id.imageViewThumbaiUngTien);
        ImageView imgYCGL = (ImageView) view.findViewById(R.id.imageViewThumbaiGoiLai);
        ImageView imgTTTB = (ImageView) view.findViewById(R.id.imageViewThumbaiTraCuu);
        ImageView imgKTTK = (ImageView) view.findViewById(R.id.imageViewThumbaiKTSoDu);

        TextView tvTittleKM = (TextView) view.findViewById(R.id.textViewTitleKhuyenMai);
        TextView tvTittleUTN = (TextView) view.findViewById(R.id.textViewTitleUngTien);
        TextView tvTittleYCGL = (TextView) view.findViewById(R.id.textViewTitleGoiLai);
        TextView tvTittleTTTB = (TextView) view.findViewById(R.id.textViewTitleTraCuu);
        TextView tvTittleKTTK = (TextView) view.findViewById(R.id.textViewTitleKTSoDu);

        TextView tvDesKM = (TextView) view.findViewById(R.id.textViewDesKhuyenMai);
        TextView tvDesUTN = (TextView) view.findViewById(R.id.textViewDesUngTien);
        TextView tvDesYCGL = (TextView) view.findViewById(R.id.textViewDesGoiLai);
        TextView tvDesTTTB = (TextView) view.findViewById(R.id.textViewDesTraCuu);
        TextView tvDesKTTK = (TextView) view.findViewById(R.id.textViewDesKTSoDu);

        imgKM.setOnClickListener(new MyClickItemEvent());
        imgUTN.setOnClickListener(new MyClickItemEvent());
        imgYCGL.setOnClickListener(new MyClickItemEvent());
        imgTTTB.setOnClickListener(new MyClickItemEvent());
        imgKTTK.setOnClickListener(new MyClickItemEvent());

        tvTittleKM.setOnClickListener(new MyClickItemEvent());
        tvTittleUTN.setOnClickListener(new MyClickItemEvent());
        tvTittleYCGL.setOnClickListener(new MyClickItemEvent());
        tvTittleTTTB.setOnClickListener(new MyClickItemEvent());
        tvTittleKTTK.setOnClickListener(new MyClickItemEvent());

        tvDesKM.setOnClickListener(new MyClickItemEvent());
        tvDesUTN.setOnClickListener(new MyClickItemEvent());
        tvDesYCGL.setOnClickListener(new MyClickItemEvent());
        tvDesTTTB.setOnClickListener(new MyClickItemEvent());
        tvDesKTTK.setOnClickListener(new MyClickItemEvent());
    }
    private class MyClickItemEvent implements View.OnClickListener
    {
        @Override
        public void onClick(View arg0) {
            Class tienichClass = null;
            switch(arg0.getId())
            {
                case R.id.imageViewThumbaiKhuyenMai:case R.id.textViewTitleKhuyenMai:case R.id.textViewDesKhuyenMai:
                    tienichClass = KhuyenMaiActivity.class;
                    break;
                case R.id.imageViewThumbaiUngTien:case R.id.textViewTitleUngTien:case R.id.textViewDesUngTien:
                    tienichClass = UngTienActivity.class;
                    break;
                case R.id.imageViewThumbaiGoiLai:case R.id.textViewTitleGoiLai:case R.id.textViewDesGoiLai:
                    tienichClass = YeuCauGoiLaiActivity.class;
                    break;
                case R.id.imageViewThumbaiKTSoDu:case R.id.textViewTitleKTSoDu:case R.id.textViewDesKTSoDu:
                    tienichClass = KiemTraSoDuActivity.class;
                    break;
                case R.id.imageViewThumbaiTraCuu:case R.id.textViewTitleTraCuu:case R.id.textViewDesTraCuu:
                    tienichClass = TraCuuTTTBActivity.class;
                    break;
            }
            Intent myIntent = new Intent(getActivity(),tienichClass);
            startActivity(myIntent);
        }

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

    public static Uri ussdToCallableUri(String ussd) {

        String uriString = "";

        if(!ussd.startsWith("tel:"))
            uriString += "tel:";

        for(char c : ussd.toCharArray()) {

            if(c == '#')
                uriString += Uri.encode("#");
            else
                uriString += c;
        }

        return Uri.parse(uriString);
    }

}

package se.uit.chichssssteam.quanlicuocdidong.Activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import se.uit.chichssssteam.quanlicuocdidong.Manager.OnFragmentInteractionListener;
import se.uit.chichssssteam.quanlicuocdidong.Manager.TienIchArrayAdapter;
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
    List<UtiltyItem> listUtiltyItem = new ArrayList<UtiltyItem>();
    TienIchArrayAdapter tienIchArrayAdapter;
    ListView listViewUtilty;

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
        initData();
        return view;
    }

    void initData()
    {
        listUtiltyItem.add(new UtiltyItem("Tra cứu thông tin khuyến mãi","Khuyến mãi nạp thẻ, hoà mạng...",R.drawable.khuyenmai));
        listUtiltyItem.add(new UtiltyItem("Ứng tiền nhanh","Ứng tiền khi tài khoản của bạn hết tiền",R.drawable.ungtien));
        listUtiltyItem.add(new UtiltyItem("Yêu cầu gọi lại","Nhắn tin cho bạn bè nhờ gọi lại cho bạn",R.drawable.goilai));
        listUtiltyItem.add(new UtiltyItem("Tra cứu thông tin thuê bao","Tra cứu thông tin chi tiết thuê bao",R.drawable.tracuuthongtin));
        listUtiltyItem.add(new UtiltyItem("Kiểm tra tài khoản","Kiểm tra tài khoản gốc và khuyến mãi",R.drawable.kiemtrasodu));
        listUtiltyItem.add(new UtiltyItem("Số điện thoại hữu ích","Số khẩn cấp, số xe Taxi, số tư vấn ..",R.drawable.kiemtrasodu));
        tienIchArrayAdapter = new TienIchArrayAdapter(getActivity(),R.layout.tienich_listview_item,listUtiltyItem);
        listViewUtilty.setAdapter(tienIchArrayAdapter);
        tienIchArrayAdapter.notifyDataSetChanged();
    }
    void getControlAndAddEvent(View view)
    {
        listViewUtilty = (ListView) view.findViewById(R.id.listViewUtilty);
        listViewUtilty.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                Class tienichClass = null;
                switch(position) {
                    case 0:
                        tienichClass = KhuyenMaiActivity.class;
                        break;
                    case 1:
                        tienichClass = UngTienActivity.class;
                        break;
                    case 2:
                        tienichClass = YeuCauGoiLaiActivity.class;
                        break;
                    case 3:
                        tienichClass = TraCuuTTTBActivity.class;
                        break;
                    case 4:
                        tienichClass = KiemTraSoDuActivity.class;
                        break;
                    case 5:
                        tienichClass = DanhBaActivity.class;
                        break;
                }
                Intent myIntent = new Intent(getActivity(),tienichClass);
                startActivity(myIntent);
            }
        });
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

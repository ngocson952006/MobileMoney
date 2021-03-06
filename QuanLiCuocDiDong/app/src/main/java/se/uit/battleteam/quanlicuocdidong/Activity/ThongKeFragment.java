package se.uit.battleteam.quanlicuocdidong.Activity;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import se.uit.battleteam.quanlicuocdidong.Manager.AmazingAdapter;
import se.uit.battleteam.quanlicuocdidong.Manager.AmazingListView;
import se.uit.battleteam.quanlicuocdidong.Manager.DataMonthFee;
import se.uit.battleteam.quanlicuocdidong.Manager.MonthFee;
import se.uit.battleteam.quanlicuocdidong.Manager.OnFragmentInteractionListener;
import se.uit.battleteam.quanlicuocdidong.R;

public class ThongKeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    AmazingListView lsComposer;
    SectionMonthFeeAdapter adapter;
    private String currency;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ThongKeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ThongKeFragment newInstance(String param1, String param2) {
        ThongKeFragment fragment = new ThongKeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public ThongKeFragment() {
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

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thong_ke, container, false);

        lsComposer = (AmazingListView) view.findViewById(R.id.lsComposer);
        lsComposer.setPinnedHeaderView(LayoutInflater.from(getActivity()).inflate(R.layout.item_monthfee_header, lsComposer, false));
        lsComposer.setAdapter(adapter = new SectionMonthFeeAdapter());

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onThongKeFragmentInteraction(uri);
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
    class SectionMonthFeeAdapter extends AmazingAdapter {
        List<Pair<String, List<MonthFee>>> all = DataMonthFee.getAllData(getContext());

        @Override
        public int getCount() {
            int res = 0;
            for (int i = 0; i < all.size(); i++) {
                res += all.get(i).second.size();
            }
            return res;
        }

        @Override
        public MonthFee getItem(int position) {
            int c = 0;
            for (int i = 0; i < all.size(); i++) {
                if (position >= c && position < c + all.get(i).second.size()) {
                    return all.get(i).second.get(position - c);
                }
                c += all.get(i).second.size();
            }
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        protected void onNextPageRequested(int page) {
        }

        @Override
        protected void bindSectionHeader(View view, int position, boolean displaySectionHeader) {
            if (displaySectionHeader) {
                view.findViewById(R.id.header).setVisibility(View.VISIBLE);
                TextView lSectionTitle = (TextView) view.findViewById(R.id.header);
                lSectionTitle.setText(getSections()[getSectionForPosition(position)]);
            } else {
                view.findViewById(R.id.header).setVisibility(View.GONE);
            }
        }

        @Override
        public View getAmazingView(int position, View convertView, ViewGroup parent) {
            View res = convertView;
            if (res == null) res = getActivity().getLayoutInflater().inflate(R.layout.item_monthfee, null);

            TextView textViewTongTienGoi = (TextView) res.findViewById(R.id.textViewTongTienGoi);
            TextView textViewSoPhutGoiNoiMang = (TextView) res.findViewById(R.id.textViewSoPhutGoiNoiMang);
            TextView textViewTienGoiNoiMang = (TextView) res.findViewById(R.id.textViewTienGoiNoiMang);
            TextView textViewSoPhutGoiNgoaiMang = (TextView) res.findViewById(R.id.textViewSoPhutGoiNgoaiMang);
            TextView textViewTienGoiNgoaiMang = (TextView) res.findViewById(R.id.textViewTienGoiNgoaiMang);
            TextView textViewTongTienSmS = (TextView) res.findViewById(R.id.textViewTongTienSmS);
            TextView textViewSoSmsNoiMang = (TextView) res.findViewById(R.id.textViewSoSmsNoiMang);
            TextView textViewTienSmSNoiMang = (TextView) res.findViewById(R.id.textViewTienSmSNoiMang);
            TextView textViewSoSmsNgoaiMang = (TextView) res.findViewById(R.id.textViewSoSmsNgoaiMang);
            TextView textViewTienSmsNgoaiMang = (TextView) res.findViewById(R.id.textViewTienSmsNgoaiMang);
            TextView textViewTongTien = (TextView) res.findViewById(R.id.textViewTongTien);

            MonthFee monthFee = getItem(position);

            textViewTongTienGoi.setText(String.valueOf(monthFee.getFee_innerCall() + monthFee.getFee_outerCall()) + currency);
            textViewSoPhutGoiNoiMang.setText(monthFee.getMinutes_innerCall());
            textViewTienGoiNoiMang.setText(String.valueOf(monthFee.getFee_innerCall()) + currency);
            textViewSoPhutGoiNgoaiMang.setText(monthFee.getMinutes_outerCall());
            textViewTienGoiNgoaiMang.setText(String.valueOf(monthFee.getFee_outerCall()) + currency);
            textViewTongTienSmS.setText(String.valueOf(monthFee.getFee_innerMess() + monthFee.getFee_outerMess()) + currency);
            textViewSoSmsNoiMang.setText(String.valueOf(monthFee.getNumber_innerMess()));
            textViewTienSmSNoiMang.setText(String.valueOf(monthFee.getFee_innerMess()) + currency);
            textViewSoSmsNgoaiMang.setText(String.valueOf(monthFee.getNumber_outerMess()));
            textViewTienSmsNgoaiMang.setText(String.valueOf(monthFee.getFee_outerMess()) + currency);
            textViewTongTien.setText(String.valueOf(monthFee.getFee_innerCall() + monthFee.getFee_outerCall() +
                                                    monthFee.getFee_innerMess() + monthFee.getFee_outerMess()) + currency);

            return res;
        }

        @Override
        public void configurePinnedHeader(View header, int position, int alpha) {
            TextView lSectionHeader = (TextView)header;
            lSectionHeader.setText(getSections()[getSectionForPosition(position)]);
            //lSectionHeader.setBackgroundColor(alpha << 24 | (0xbbffbb));
            //lSectionHeader.setTextColor(alpha << 24 | (0x000000));
        }

        @Override
        public int getPositionForSection(int section) {
            if (section < 0) section = 0;
            if (section >= all.size()) section = all.size() - 1;
            int c = 0;
            for (int i = 0; i < all.size(); i++) {
                if (section == i) {
                    return c;
                }
                c += all.get(i).second.size();
            }
            return 0;
        }

        @Override
        public int getSectionForPosition(int position) {
            int c = 0;
            for (int i = 0; i < all.size(); i++) {
                if (position >= c && position < c + all.get(i).second.size()) {
                    return i;
                }
                c += all.get(i).second.size();
            }
            return -1;
        }

        @Override
        public String[] getSections() {
            String[] res = new String[all.size()];
            for (int i = 0; i < all.size(); i++) {
                res[i] = all.get(i).first;
            }
            return res;
        }

    }
}

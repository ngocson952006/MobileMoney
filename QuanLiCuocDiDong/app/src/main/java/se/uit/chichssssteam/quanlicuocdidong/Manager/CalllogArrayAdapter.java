package se.uit.chichssssteam.quanlicuocdidong.Manager;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import se.uit.chichssssteam.quanlicuocdidong.DB.CallLog;
import se.uit.chichssssteam.quanlicuocdidong.R;

/**
 * Created by QNghi on 24/11/2015.
 */
public class CalllogArrayAdapter extends ArrayAdapter<CallLog>
    {
        Activity context=null;
        List<CallLog> myArrayCalllog =null;
        int layoutId;

        public CalllogArrayAdapter(Activity context,
                                   int layoutId,
                                   List<CallLog> arr){
        super(context, layoutId, arr);
        this.context=context;
        this.layoutId=layoutId;
        this.myArrayCalllog =arr;
    }

    public View getView(int position, View convertView,
                        ViewGroup parent) {
        LayoutInflater inflater=
                context.getLayoutInflater();
        convertView=inflater.inflate(layoutId, null);
        if(myArrayCalllog.size()>0 && position>=0)
        {
            final TextView textViewPhoneNumber=(TextView)
                    convertView.findViewById(R.id.textViewPhoneNumber);
            final TextView textViewTime=(TextView)
                    convertView.findViewById(R.id.textViewTime);
            final TextView textViewfee=(TextView)
                    convertView.findViewById(R.id.textViewfee);
            final TextView textViewDuration=(TextView)
                    convertView.findViewById(R.id.textViewDuration);
            final ImageView imageViewTypeLog = (ImageView)
                    convertView.findViewById(R.id.imageViewTypeLog);

            final CallLog call = myArrayCalllog.get(position);

            imageViewTypeLog.setImageResource(R.drawable.ic_action_communication_call);
            textViewPhoneNumber.setText(call.get_callNumber());
            textViewTime.setText(DateTimeManager.get_instance().convertToDMYHms(call.get_callDate()).substring(11, 16));
            final String currency =  getContext().getString(R.string.textCurrency);
            textViewfee.setText(String.valueOf(call.get_callFee()) + currency);
            textViewDuration.setText(DateTimeManager.get_instance().convertToMinutesAndSec(call.get_callDuration()));

        }
        return convertView;
    }
}

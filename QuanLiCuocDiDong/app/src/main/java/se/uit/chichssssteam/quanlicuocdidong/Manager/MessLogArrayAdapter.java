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
import se.uit.chichssssteam.quanlicuocdidong.DB.MessageLog;
import se.uit.chichssssteam.quanlicuocdidong.R;

/**
 * Created by QNghi on 24/11/2015.
 */
public class MessLogArrayAdapter extends ArrayAdapter<MessageLog>
{
    Activity context=null;
    List<MessageLog> myArrayMessLog =null;
    int layoutId;

    public MessLogArrayAdapter(Activity context,
                               int layoutId,
                               List<MessageLog> arr){
        super(context, layoutId, arr);
        this.context=context;
        this.layoutId=layoutId;
        this.myArrayMessLog =arr;
    }

    public View getView(int position, View convertView,
                        ViewGroup parent) {
        LayoutInflater inflater=
                context.getLayoutInflater();
        convertView=inflater.inflate(layoutId, null);
        if(myArrayMessLog.size()>0 && position>=0)
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

            final MessageLog mess = myArrayMessLog.get(position);

            imageViewTypeLog.setImageResource(R.drawable.phoneicon);
            textViewPhoneNumber.setText(mess.get_receiverNumber());
            textViewTime.setText(DateTimeManager.get_instance().convertToDMYHms(mess.get_messageDate()).substring(11, 16));
            textViewfee.setText(String.valueOf(mess.get_messageFee()) + " đ");
            textViewDuration.setText("");

        }
        return convertView;
    }
}

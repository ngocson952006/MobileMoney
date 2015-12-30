package se.uit.battleteam.quanlicuocdidong.Manager;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import se.uit.battleteam.quanlicuocdidong.R;

/**
 * Created by QNghi on 25/11/2015.
 */
public class TienIchArrayAdapter extends ArrayAdapter<UtiltyItem> {
    Activity context = null;
    List<UtiltyItem> myArrayUtilty = null;
    int layoutId;

    public TienIchArrayAdapter(Activity context,
                               int layoutId,
                               List<UtiltyItem> arr) {
        super(context, layoutId, arr);
        this.context = context;
        this.layoutId = layoutId;
        this.myArrayUtilty = arr;
    }

    public View getView(int position, View convertView,
                        ViewGroup parent) {
        LayoutInflater inflater =
                context.getLayoutInflater();
        convertView = inflater.inflate(layoutId, null);
        if (myArrayUtilty.size() > 0 && position >= 0) {
            final TextView textViewTitleItem = (TextView)
                    convertView.findViewById(R.id.textViewTitleItem);
            final TextView textViewDesItem = (TextView)
                    convertView.findViewById(R.id.textViewDesItem);
            final ImageView imageViewThumbaiItem = (ImageView)
                    convertView.findViewById(R.id.imageViewThumbaiItem);

            final UtiltyItem call = myArrayUtilty.get(position);

            imageViewThumbaiItem.setImageResource(call.get_imageID());
            textViewTitleItem.setText(call.get_title());
            textViewDesItem.setText(call.get_des());

        }
        return convertView;
    }
}
package se.uit.chichssssteam.quanlicuocdidong.Manager;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import se.uit.chichssssteam.quanlicuocdidong.R;

/**
 * Created by QNghi on 11/10/2015.
 */
public class MyArrayUtiltyAdapter extends ArrayAdapter<UtiltyItem>
{
    Activity context=null;
    ArrayList<UtiltyItem>myUtiltyArray=null;
    int layoutId;

    public MyArrayUtiltyAdapter(Activity context,
                          int layoutId,
                          ArrayList<UtiltyItem>arr){
        super(context, layoutId, arr);
        this.context=context;
        this.layoutId=layoutId;
        this.myUtiltyArray=arr;
    }
    public View getView(int position, View convertView,
                        ViewGroup parent) {
        LayoutInflater inflater=
                context.getLayoutInflater();
        convertView=inflater.inflate(layoutId, null);
        if(myUtiltyArray.size()>0 && position>=0)
        {
            final UtiltyItem namePackage=myUtiltyArray.get(position);
            final TextView txtdisplayTitle=(TextView)
                    convertView.findViewById(R.id.txtitemTitle);
            txtdisplayTitle.setText(namePackage.getTitle());
            final TextView txtdisplayDes=(TextView)
                    convertView.findViewById(R.id.txtitemDes);
            txtdisplayDes.setText(namePackage.getDes());
            final ImageView imgitem=(ImageView)
                    convertView.findViewById(R.id.imgitem);
            imgitem.setImageResource(namePackage.getIdImage());
        }
        return convertView;
    }
}
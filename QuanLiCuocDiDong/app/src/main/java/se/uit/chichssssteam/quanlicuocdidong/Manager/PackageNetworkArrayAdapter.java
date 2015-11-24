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

public class PackageNetworkArrayAdapter extends
        ArrayAdapter<PackageNetwork>
{
    Activity context=null;
    ArrayList<PackageNetwork>myArrayPackageNetwork=null;
    int layoutId;

    public PackageNetworkArrayAdapter(Activity context,
                                      int layoutId,
                                      ArrayList<PackageNetwork> arr){
        super(context, layoutId, arr);
        this.context=context;
        this.layoutId=layoutId;
        this.myArrayPackageNetwork=arr;
    }

    public View getView(int position, View convertView,
                        ViewGroup parent) {
        LayoutInflater inflater=
                context.getLayoutInflater();
        convertView=inflater.inflate(layoutId, null);
        if(myArrayPackageNetwork.size()>0 && position>=0)
        {
            final TextView txtdisplay=(TextView)
                    convertView.findViewById(R.id.txtitem);
            final PackageNetwork namePackage=myArrayPackageNetwork.get(position);
            txtdisplay.setText(namePackage.getPackageName());
            final ImageView imgitem=(ImageView)
                    convertView.findViewById(R.id.imgitem);
            imgitem.setImageResource(namePackage.getIdResourceImage());
        }
        return convertView;
    }
}
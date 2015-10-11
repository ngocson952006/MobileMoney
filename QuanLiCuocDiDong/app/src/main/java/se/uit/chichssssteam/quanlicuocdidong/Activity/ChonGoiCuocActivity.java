package se.uit.chichssssteam.quanlicuocdidong.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import se.uit.chichssssteam.quanlicuocdidong.Manager.MyArrayAdapter;
import se.uit.chichssssteam.quanlicuocdidong.Manager.PackageNetwork;
import se.uit.chichssssteam.quanlicuocdidong.R;

public class ChonGoiCuocActivity extends Activity {

    String mangDiDong;
    ImageView imgViewMangDiDong;
    ListView lstViewGoiCuoc;
    TextView textViewTut;
    ArrayList<PackageNetwork> arrayListPackageNw = new ArrayList<PackageNetwork>();
    MyArrayAdapter adapter = null;
    String stringTut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_goi_cuoc);
        getControl();
        getMobileNetwork();
        addItemToListView();
        addEvents();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_chon_goi_cuoc, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void getControl()
    {
        imgViewMangDiDong = (ImageView) findViewById(R.id.imageViewMangDiDong);
        lstViewGoiCuoc = (ListView) findViewById(R.id.listViewGoiCuoc);
        textViewTut = (TextView) findViewById(R.id.textViewTut);
    }
    private void addEvents()
    {
        imgViewMangDiDong.setOnClickListener(new ProcessMyEvent());
        lstViewGoiCuoc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                Bundle myBundle = new Bundle();
                PackageNetwork myPN = (PackageNetwork) parent.getItemAtPosition(position);
                myBundle.putSerializable("PackageNetworkItem", myPN);
                Intent myIntent = new Intent(ChonGoiCuocActivity.this, MainActivity.class);
                myIntent.putExtra("Mang", myBundle);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(myIntent);
            }
        });
    }
    private class ProcessMyEvent implements View.OnClickListener
    {
        @Override
        public void onClick(View arg0) {
            switch(arg0.getId())
            {
                case R.id.imageViewMangDiDong: {
                    finish();
                    break;
                }
            }
        }
    }
    private void getMobileNetwork()
    {
        Intent callerIntent = getIntent();
        Bundle packegeFromCaller = callerIntent.getBundleExtra("Mang");
        mangDiDong = packegeFromCaller.getString("Mang Di Dong");
        switch(mangDiDong)
        {
            case "Mobifone": {
                stringTut = "Nhấn *101#";
                imgViewMangDiDong.setImageResource(R.drawable.mobifone);
                arrayListPackageNw.add(new PackageNetwork(mangDiDong,"Mobicard", R.drawable.mobicard));
                arrayListPackageNw.add(new PackageNetwork(mangDiDong,"MobiGold",R.drawable.mobigold));
                arrayListPackageNw.add(new PackageNetwork(mangDiDong,"MobiQ",R.drawable.mobiq));
                arrayListPackageNw.add(new PackageNetwork(mangDiDong,"Q-Student",R.drawable.qstudent));
                arrayListPackageNw.add(new PackageNetwork(mangDiDong,"Q-Teen",R.drawable.qteen));
                arrayListPackageNw.add(new PackageNetwork(mangDiDong,"Q-Kids",R.drawable.qkids));
                break;
            }
            case "VinaPhone": {
                stringTut = "Nhấn *110#";
                imgViewMangDiDong.setImageResource(R.drawable.vinaphonne);
                arrayListPackageNw.add(new PackageNetwork(mangDiDong,"VinaCard", R.drawable.vinacard));
                arrayListPackageNw.add(new PackageNetwork(mangDiDong,"VinaXtra", R.drawable.vinaxtra));
                arrayListPackageNw.add(new PackageNetwork(mangDiDong,"TalkEZ", R.drawable.talkez));
                break;
            }
            case "Viettel": {
                stringTut = "Gửi sms GC tới 195";
                imgViewMangDiDong.setImageResource(R.drawable.vietel);
                arrayListPackageNw.add(new PackageNetwork(mangDiDong,"Economy", R.drawable.vietel));
                arrayListPackageNw.add(new PackageNetwork(mangDiDong,"Tomato", R.drawable.vietel));
                arrayListPackageNw.add(new PackageNetwork(mangDiDong,"Student", R.drawable.vietel));
                arrayListPackageNw.add(new PackageNetwork(mangDiDong,"Sea+", R.drawable.vietel));
                arrayListPackageNw.add(new PackageNetwork(mangDiDong,"Hi School", R.drawable.vietel));
                arrayListPackageNw.add(new PackageNetwork(mangDiDong,"7Colors", R.drawable.vietel));
                arrayListPackageNw.add(new PackageNetwork(mangDiDong,"Buôn làng", R.drawable.vietel));
                break;
            }
            case "GMobile": {
                stringTut = "Nhấn *110#";
                imgViewMangDiDong.setImageResource(R.drawable.gmobile);
                arrayListPackageNw.add(new PackageNetwork(mangDiDong,"Big Save", R.drawable.gmobile));
                arrayListPackageNw.add(new PackageNetwork(mangDiDong,"Big & Kool", R.drawable.gmobile));
                arrayListPackageNw.add(new PackageNetwork(mangDiDong,"Tỉ phú 2", R.drawable.gmobile));
                arrayListPackageNw.add(new PackageNetwork(mangDiDong,"Tỉ phú 3", R.drawable.tiphu3));
                arrayListPackageNw.add(new PackageNetwork(mangDiDong,"Tỉ phú 5", R.drawable.tiphu5));
                break;
            }
            case "VietNamMobile": {
                stringTut = "Nhấn *101#";
                imgViewMangDiDong.setImageResource(R.drawable.vietnamobile);
                arrayListPackageNw.add(new PackageNetwork(mangDiDong,"VM One", R.drawable.vietnamobile));
                arrayListPackageNw.add(new PackageNetwork(mangDiDong,"VMax", R.drawable.vietnamobile));
                arrayListPackageNw.add(new PackageNetwork(mangDiDong,"SV 2014", R.drawable.vietnamobile));
                break;
            }
        }
        textViewTut.setText("*" + stringTut + " để kiểm tra gói cước đang sử dụng");
    }
    private void addItemToListView()
    {
        adapter = new MyArrayAdapter(this,R.layout.custom_listview,arrayListPackageNw);
        lstViewGoiCuoc.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}

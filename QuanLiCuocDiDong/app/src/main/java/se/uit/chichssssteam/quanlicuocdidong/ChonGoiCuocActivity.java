package se.uit.chichssssteam.quanlicuocdidong;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class ChonGoiCuocActivity extends Activity {

    String mangDiDong;
    ImageView imgViewMangDiDong;
    ListView lstViewGoiCuoc;
    ArrayList<PackageNetwork> arrayListPackageNw = new ArrayList<PackageNetwork>();
    MyArrayAdapter adapter = null;
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
    }
    private void addEvents()
    {
        imgViewMangDiDong.setOnClickListener(new ProcessMyEvent());
        lstViewGoiCuoc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                String goiCuoc = ((PackageNetwork)parent.getItemAtPosition(position)).getPackageName();
                Bundle myBundle = new Bundle();
                myBundle.putString("Mang Di Dong",mangDiDong);
                myBundle.putString("Goi Cuoc",goiCuoc);
                Intent myIntent = new Intent(ChonGoiCuocActivity.this,MainActivity.class);
                myIntent.putExtra("Mang",myBundle);
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
                imgViewMangDiDong.setImageResource(R.drawable.mobifone);
                arrayListPackageNw.add(new PackageNetwork("Mobicard", R.drawable.mobicard));
                arrayListPackageNw.add(new PackageNetwork("MobiGold",R.drawable.mobigold));
                arrayListPackageNw.add(new PackageNetwork("MobiQ",R.drawable.mobiq));
                arrayListPackageNw.add(new PackageNetwork("Q-Student",R.drawable.qstudent));
                arrayListPackageNw.add(new PackageNetwork("Q-Teen",R.drawable.qteen));
                arrayListPackageNw.add(new PackageNetwork("Q-Kids",R.drawable.qkids));
                break;
            }
            case "VinaPhone": {
                imgViewMangDiDong.setImageResource(R.drawable.vinaphonne);
                arrayListPackageNw.add(new PackageNetwork("VinaCard", R.drawable.vinacard));
                arrayListPackageNw.add(new PackageNetwork("VinaXtra", R.drawable.vinaxtra));
                arrayListPackageNw.add(new PackageNetwork("TalkEZ", R.drawable.talkez));
                break;
            }
            case "Viettel": {
                imgViewMangDiDong.setImageResource(R.drawable.vietel);
                arrayListPackageNw.add(new PackageNetwork("Economy", R.drawable.vietel));
                arrayListPackageNw.add(new PackageNetwork("Tomato", R.drawable.vietel));
                arrayListPackageNw.add(new PackageNetwork("Student", R.drawable.vietel));
                arrayListPackageNw.add(new PackageNetwork("Sea+", R.drawable.vietel));
                arrayListPackageNw.add(new PackageNetwork("Hi School", R.drawable.vietel));
                arrayListPackageNw.add(new PackageNetwork("7Colors", R.drawable.vietel));
                arrayListPackageNw.add(new PackageNetwork("Buôn làng", R.drawable.vietel));
                break;
            }
            case "GMobile": {
                imgViewMangDiDong.setImageResource(R.drawable.gmobile);
                arrayListPackageNw.add(new PackageNetwork("Big Save", R.drawable.gmobile));
                arrayListPackageNw.add(new PackageNetwork("Big & Kool", R.drawable.gmobile));
                arrayListPackageNw.add(new PackageNetwork("Tỉ phú 2", R.drawable.gmobile));
                arrayListPackageNw.add(new PackageNetwork("Tỉ phú 3", R.drawable.tiphu3));
                arrayListPackageNw.add(new PackageNetwork("Tỉ phú 5", R.drawable.tiphu5));
                break;
            }
            case "VietNamMobile": {
                imgViewMangDiDong.setImageResource(R.drawable.vietnamobile);
                arrayListPackageNw.add(new PackageNetwork("VM One", R.drawable.vietnamobile));
                arrayListPackageNw.add(new PackageNetwork("VMax", R.drawable.vietnamobile));
                arrayListPackageNw.add(new PackageNetwork("SV 2014", R.drawable.vietnamobile));
                break;
            }
        }
    }
    private void addItemToListView()
    {
        adapter = new MyArrayAdapter(this,R.layout.custom_listview,arrayListPackageNw);
        lstViewGoiCuoc.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}

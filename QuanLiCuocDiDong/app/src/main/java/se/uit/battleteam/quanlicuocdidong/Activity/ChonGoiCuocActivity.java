package se.uit.battleteam.quanlicuocdidong.Activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import se.uit.battleteam.quanlicuocdidong.Manager.PackageNetworkArrayAdapter;
import se.uit.battleteam.quanlicuocdidong.Manager.PackageNetwork;
import se.uit.battleteam.quanlicuocdidong.R;

public class ChonGoiCuocActivity extends Activity {

    private String mangDiDong;
    private ListView lstViewGoiCuoc;
    private TextView textViewTut;
    private ArrayList<PackageNetwork> arrayListPackageNw = new ArrayList<PackageNetwork>();
    private PackageNetworkArrayAdapter adapter = null;
    private String stringTut;
    public static final String MOBICARD = "Mobicard";
    public static final String MOBIGOLD = "MobiGold";
    public static final String MOBIQ = "MobiQ";
    public static final String QSTUDENT = "Q-Student";
    public static final String QTEEN = "Q-Teen";
    public static final String QKIDS = "Q-Kids";
    public static final String VINACARD = "VinaCard";
    public static final String VINAXTRA = "VinaXtra";
    public static final String TALKTEEN = "TalkTeen";
    public static final String TALKSTUDENT = "TalkStudent";
    public static final String ECONOMY = "Economy";
    public static final String TOMATO = "Tomato";
    public static final String STUDENT = "Student";
    public static final String SEA = "Sea+";
    public static final String HISCHOOL = "Hi School";
    public static final String SEVENCOLOR = "7Colors";
    public static final String BUONLANG = "Buôn làng";
    public static final String BIGSAVE = "Big Save";
    public static final String BIGKOOL = "Big & Kool";
    public static final String TIPHU2 = "Tỉ phú 2";
    public static final String TIPHU3 ="Tỉ phú 3";
    public static final String TIPHU5 = "Tỉ phú 5";
    public static final String VMONE = "VM One";
    public static final String VMAX = "VMax";
    public static final String SV2014 = "SV 2014";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_goi_cuoc);
        getControl();
        getMobileNetwork();
        editActionBar();
        addItemToListView();
        addEvents();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_chon_goi_cuoc, menu);
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
        if (id == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void getControl()
    {
        lstViewGoiCuoc = (ListView) findViewById(R.id.listViewGoiCuoc);
        textViewTut = (TextView) findViewById(R.id.textViewTut);
    }
    private void addEvents()
    {
        lstViewGoiCuoc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                Bundle myBundle = new Bundle();
                PackageNetwork myPN = (PackageNetwork) parent.getItemAtPosition(position);
                myBundle.putSerializable(MainActivity.KEY_GOICUOC, myPN);
                Intent myIntent = new Intent(ChonGoiCuocActivity.this, MainActivity.class);
                myIntent.putExtra(MainActivity.KEY_INFO_MOBILENETWORK, myBundle);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(myIntent);
            }
        });
    }
    private void getMobileNetwork()
    {
        Intent callerIntent = getIntent();
        Bundle packegeFromCaller = callerIntent.getBundleExtra(MainActivity.KEY_INFO_MOBILENETWORK);
        mangDiDong = packegeFromCaller.getString(MainActivity.KEY_NHAMANG);
        switch(mangDiDong)
        {
            case ChonMangDiDongActivity.MOBIFONE: {
                stringTut = "Nhấn *101#";
                arrayListPackageNw.add(new PackageNetwork(mangDiDong,MOBICARD, R.drawable.mobicard));
                arrayListPackageNw.add(new PackageNetwork(mangDiDong,MOBIGOLD,R.drawable.mobigold));
                arrayListPackageNw.add(new PackageNetwork(mangDiDong,MOBIQ,R.drawable.mobiq));
                arrayListPackageNw.add(new PackageNetwork(mangDiDong,QSTUDENT,R.drawable.qstudent));
                arrayListPackageNw.add(new PackageNetwork(mangDiDong,QTEEN,R.drawable.qteen));
                arrayListPackageNw.add(new PackageNetwork(mangDiDong,QKIDS,R.drawable.qkids));
                textViewTut.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        callUSSD("*101#");
                    }
                });
                break;
            }
            case ChonMangDiDongActivity.VINAPHONE: {
                stringTut = "Nhấn *110#";
                arrayListPackageNw.add(new PackageNetwork(mangDiDong,VINACARD, R.drawable.vinacard));
                arrayListPackageNw.add(new PackageNetwork(mangDiDong,VINAXTRA, R.drawable.vinaxtra));
                arrayListPackageNw.add(new PackageNetwork(mangDiDong,TALKTEEN, R.drawable.talkez));
                arrayListPackageNw.add(new PackageNetwork(mangDiDong,TALKSTUDENT, R.drawable.talkez));
                textViewTut.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        callUSSD("*110#");
                    }
                });
                break;
            }
            case ChonMangDiDongActivity.VIETTEL: {
                stringTut = "Gửi sms GC tới 195";

                arrayListPackageNw.add(new PackageNetwork(mangDiDong,ECONOMY, R.drawable.vietel));
                arrayListPackageNw.add(new PackageNetwork(mangDiDong,TOMATO, R.drawable.vietel));
                arrayListPackageNw.add(new PackageNetwork(mangDiDong,STUDENT, R.drawable.vietel));
                arrayListPackageNw.add(new PackageNetwork(mangDiDong,SEA, R.drawable.vietel));
                arrayListPackageNw.add(new PackageNetwork(mangDiDong,HISCHOOL, R.drawable.vietel));
                arrayListPackageNw.add(new PackageNetwork(mangDiDong,SEVENCOLOR, R.drawable.vietel));
                arrayListPackageNw.add(new PackageNetwork(mangDiDong,BUONLANG, R.drawable.vietel));
                textViewTut.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sendSmS("195","GC");
                    }
                });
                break;
            }
            case ChonMangDiDongActivity.GMOBILE: {
                stringTut = "Nhấn *110#";
                arrayListPackageNw.add(new PackageNetwork(mangDiDong,BIGSAVE, R.drawable.gmobile));
                arrayListPackageNw.add(new PackageNetwork(mangDiDong,BIGKOOL, R.drawable.gmobile));
                arrayListPackageNw.add(new PackageNetwork(mangDiDong,TIPHU2, R.drawable.gmobile));
                arrayListPackageNw.add(new PackageNetwork(mangDiDong,TIPHU3, R.drawable.tiphu3));
                arrayListPackageNw.add(new PackageNetwork(mangDiDong,TIPHU5, R.drawable.tiphu5));
                textViewTut.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        callUSSD("*110#");
                    }
                });
                break;
            }
            case ChonMangDiDongActivity.VIETNAMOBILE: {
                stringTut = "Nhấn *101#";
                arrayListPackageNw.add(new PackageNetwork(mangDiDong,VMONE, R.drawable.vietnamobile));
                arrayListPackageNw.add(new PackageNetwork(mangDiDong,VMAX, R.drawable.vietnamobile));
                arrayListPackageNw.add(new PackageNetwork(mangDiDong,SV2014, R.drawable.vietnamobile));
                textViewTut.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        callUSSD("*101#");
                    }
                });
                break;
            }
        }
        textViewTut.setText("*" + stringTut + " để kiểm tra gói cước đang sử dụng");
    }
    private void addItemToListView()
    {
        adapter = new PackageNetworkArrayAdapter(this,R.layout.custom_listview,arrayListPackageNw);
        lstViewGoiCuoc.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
    private void editActionBar()
    {
        ActionBar bar = getActionBar();
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setDisplayShowHomeEnabled(false);
        bar.setDisplayShowCustomEnabled(false);
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2196F3")));
        bar.setTitle(Html.fromHtml("<font color='#FFFFFF'>Nhà mạng: " + mangDiDong + "</font>"));
    }
    private void sendSmS(String address, String sms_body)
    {
        Intent smsIntent = new Intent(Intent.ACTION_VIEW);
        smsIntent.setType("vnd.android-dir/mms-sms");
        smsIntent.putExtra("address", address);
        smsIntent.putExtra("sms_body", sms_body);
        startActivity(smsIntent);
    }
    private void callUSSD(String code)
    {
        Intent callIntent = new Intent(Intent.ACTION_CALL, TienIchFragment.ussdToCallableUri(code));
        startActivity(callIntent);
    }
}

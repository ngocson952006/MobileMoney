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
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import se.uit.battleteam.quanlicuocdidong.R;

public class ChonMangDiDongActivity extends Activity {

    private ImageButton imageBtnMobi;
    private ImageButton imageBtnVina;
    private ImageButton imageBtnViettel;
    private ImageButton imageBtnGMobi;
    private ImageButton imageBtnVNMobile;
    private String mangDiDong;
    public static final String MOBIFONE = "Mobifone";
    public static final String VINAPHONE = "VinaPhone";
    public static final String VIETTEL = "Viettel";
    public static final String GMOBILE = "GMobile";
    public static final String VIETNAMOBILE = "VietNamobile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_mang_di_dong);
        editActionBar();
        getControl();
        this.addEvents();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_chon_mang_di_dong, menu);
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
    private void getControl() {

        imageBtnMobi = (ImageButton) findViewById(R.id.imageButtonMobifone);
        imageBtnVina = (ImageButton) findViewById(R.id.imageButtonVinaPhone);
        imageBtnViettel = (ImageButton) findViewById(R.id.imageButtonViettel);
        imageBtnGMobi = (ImageButton) findViewById(R.id.imageButtonGMobile);
        imageBtnVNMobile = (ImageButton) findViewById(R.id.imageButtonVietnamMobile);
    }
    private void addEvents()
    {
        imageBtnMobi.setOnClickListener(new ProcessMyEvent());
        imageBtnVina.setOnClickListener(new ProcessMyEvent());
        imageBtnViettel.setOnClickListener(new ProcessMyEvent());
        imageBtnGMobi.setOnClickListener(new ProcessMyEvent());
        imageBtnVNMobile.setOnClickListener(new ProcessMyEvent());
    }
    private class ProcessMyEvent implements OnClickListener
    {
        @Override
        public void onClick(View arg0) {
            switch(arg0.getId())
            {
                case R.id.imageButtonMobifone:
                    mangDiDong = MOBIFONE;
                    break;
                case R.id.imageButtonVinaPhone:
                    mangDiDong = VINAPHONE;
                    break;
                case R.id.imageButtonViettel:
                    mangDiDong = VIETTEL;
                    break;
                case R.id.imageButtonGMobile:
                    mangDiDong = GMOBILE;
                    break;
                case R.id.imageButtonVietnamMobile:
                    mangDiDong = VIETNAMOBILE;
                    break;
            }
            Bundle myBundle = new Bundle();
            myBundle.putString(MainActivity.KEY_NHAMANG, mangDiDong);
            Intent myIntent = new Intent(ChonMangDiDongActivity.this,ChonGoiCuocActivity.class);
            myIntent.putExtra(MainActivity.KEY_INFO_MOBILENETWORK,myBundle);
            startActivity(myIntent);
        }

    }
    private void editActionBar()
    {
        ActionBar bar = getActionBar();
        bar.setDisplayShowHomeEnabled(false);
        bar.setDisplayShowCustomEnabled(false);
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2196F3")));
        bar.setTitle(Html.fromHtml("<font color='#FFFFFF'>Chọn mạng di động</font>"));
    }
}

package se.uit.chichssssteam.quanlicuocdidong.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import se.uit.chichssssteam.quanlicuocdidong.R;

public class ChonMangDiDongActivity extends Activity {

    ImageButton imageBtnMobi;
    ImageButton imageBtnVina;
    ImageButton imageBtnViettel;
    ImageButton imageBtnGMobi;
    ImageButton imageBtnVNMobile;
    String mangDiDong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_mang_di_dong);
        this.getControl();
        this.addEvents();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_chon_mang_di_dong, menu);
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
                    mangDiDong = "Mobifone";
                    break;
                case R.id.imageButtonVinaPhone:
                    mangDiDong = "VinaPhone";
                    break;
                case R.id.imageButtonViettel:
                    mangDiDong = "Viettel";
                    break;
                case R.id.imageButtonGMobile:
                    mangDiDong = "GMobile";
                    break;
                case R.id.imageButtonVietnamMobile:
                    mangDiDong = "VietNamMobile";
                    break;
            }
            Bundle myBundle = new Bundle();
            myBundle.putString("Mang Di Dong",mangDiDong);
            Intent myIntent = new Intent(ChonMangDiDongActivity.this,ChonGoiCuocActivity.class);
            myIntent.putExtra("Mang",myBundle);
            startActivity(myIntent);
        }

    }
}

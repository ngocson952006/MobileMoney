package se.uit.battleteam.quanlicuocdidong.Activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import se.uit.battleteam.quanlicuocdidong.R;

public class KiemTraSoDuActivity extends Activity {

    private TextView textViewGT;
    private TextView textViewHD;
    private TextView textViewTTCT;
    private TextView textViewKM;
    private Button buttonChinh;
    private Button buttonKM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kiem_tra_so_du);
        editActionBar();
        getControl();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_kiem_tra_so_du, menu);
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
    private void editActionBar()
    {
        ActionBar bar = getActionBar();
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setDisplayShowHomeEnabled(false);
        bar.setDisplayShowCustomEnabled(false);
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor(getString(R.string.colorActionBar))));
        bar.setTitle(Html.fromHtml(getString(R.string.textTitlebarKTSoDu)));
    }
    private void getControl()
    {
        String mangDiDong;
        SharedPreferences settings = getSharedPreferences(MainActivity.PREFS_NAME, MODE_PRIVATE);
        mangDiDong = settings.getString(MainActivity.KEY_NHAMANG, MainActivity.VALUE_DEFAULT);
        textViewGT = (TextView) findViewById(R.id.textViewGioithieu);
        textViewHD = (TextView) findViewById(R.id.textViewHuongDan);
        textViewTTCT = (TextView) findViewById(R.id.textViewTTChiTiet);
        buttonChinh = (Button) findViewById(R.id.buttonChinh);
        buttonChinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL, TienIchFragment.ussdToCallableUri("*101#"));
                startActivity(callIntent);
            }
        });
        buttonKM= (Button) findViewById(R.id.buttonKM);
        textViewKM = (TextView) findViewById(R.id.textViewKhuyenMai);
        if (!mangDiDong.equals("Viettel"))
        {
            buttonKM.setVisibility(View.INVISIBLE);
            textViewKM.setVisibility(View.INVISIBLE);
            return;
        }
        buttonKM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL, TienIchFragment.ussdToCallableUri("*102#"));
                startActivity(callIntent);
            }
        });

    }
}


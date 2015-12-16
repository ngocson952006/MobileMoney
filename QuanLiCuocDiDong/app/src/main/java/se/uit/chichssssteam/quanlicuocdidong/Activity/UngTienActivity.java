package se.uit.chichssssteam.quanlicuocdidong.Activity;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import se.uit.chichssssteam.quanlicuocdidong.R;

public class UngTienActivity extends Activity {

    private TextView textViewGT;
    private TextView textViewHD;
    private TextView textViewTTCT;
    private Button buttonExcute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ung_tien);
        editActionBar();
        getControl();
        Init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_ung_tien, menu);
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
        bar.setTitle(Html.fromHtml(getString(R.string.textTitleBarUngTien)));
    }
    private void getControl()
    {
        textViewGT = (TextView) findViewById(R.id.textViewGioithieu);
        textViewHD = (TextView) findViewById(R.id.textViewHuongDan);
        textViewTTCT = (TextView) findViewById(R.id.textViewTTChiTiet);
        buttonExcute = (Button) findViewById(R.id.buttonOk);
    }

    private void sendSmS(String address, String sms_body)
    {
        Intent smsIntent = new Intent(Intent.ACTION_VIEW);
        smsIntent.setType("vnd.android-dir/mms-sms");
        smsIntent.putExtra("address", address);
        smsIntent.putExtra("sms_body",sms_body);
        startActivity(smsIntent);
    }
    private void callUSSD(String code)
    {
        Intent callIntent = new Intent(Intent.ACTION_CALL, TienIchFragment.ussdToCallableUri(code));
        startActivity(callIntent);
    }
    private void Init()
    {
        SharedPreferences settings = getSharedPreferences(MainActivity.PREFS_NAME, MODE_PRIVATE);
        String mangDiDong = settings.getString(MainActivity.KEY_NHAMANG, MainActivity.VALUE_DEFAULT);
        String huongdan = null;
        String gioithieu = null;
        String urlTTCT = null;
        switch (mangDiDong)
        {
            case "Mobifone": {
                gioithieu = getString(R.string.textUngTienMobi);
                huongdan = getString(R.string.textUngTienHDMobi);
                urlTTCT = getString(R.string.urlUngTienMobi);

                buttonExcute.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sendSmS("900", "UT");
                    }
                });
                break;
            }
            case "VinaPhone": {
                gioithieu = getString(R.string.textUngTienVina);
                huongdan = getString(R.string.textUngTienHDVina);
                urlTTCT = getString(R.string.urlUngTienVina);

                buttonExcute.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sendSmS("1576", "Y");
                    }
                });
                break;
            }
            case "Viettel": {
                gioithieu = getString(R.string.textUngTienViettel);
                huongdan = getString(R.string.textUngTienHDViettel);
                urlTTCT = getString(R.string.urlUngTienViettel);

                buttonExcute.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        callUSSD("*911#");
                    }
                });
                break;
            }
            case "GMobile": { //khong co dich vu nay
                break;
            }
            case "VietNamMobile": {
                gioithieu = getString(R.string.textUngTienVNMobi);
                huongdan = getString(R.string.textUngTienHDVNMobi);
                urlTTCT = getString(R.string.urlUngTienVNMobi);

                buttonExcute.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        callUSSD("*911#");
                    }
                });
                break;
            }
        }
        if (gioithieu == null)
        {
            AlertDialog.Builder dialog = new AlertDialog.Builder(UngTienActivity.this);
            dialog.setMessage(R.string.textServiceNotFound);
            dialog.setPositiveButton(R.string.textOK, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            dialog.create().show();
        }
        else
        {
            textViewGT.setText(gioithieu);
            textViewHD.setText(huongdan);
            textViewTTCT.setText(Html.fromHtml(getString(R.string.textTTCTpart2) + urlTTCT + getString(R.string.textTTCTpart1)));
            textViewTTCT.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }
}

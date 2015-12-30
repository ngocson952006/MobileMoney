package se.uit.battleteam.quanlicuocdidong.Activity;

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
import android.widget.EditText;
import android.widget.TextView;

import se.uit.battleteam.quanlicuocdidong.R;

public class YeuCauGoiLaiActivity extends Activity {

    private TextView textViewGT;
    private TextView textViewHD;
    private TextView textViewTTCT;
    private Button buttonExcute;
    private EditText editTextSDT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yeu_cau_goi_lai);
        editActionBar();
        getControl();
        Init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_yeu_cau_goi_lai, menu);
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
        bar.setTitle(Html.fromHtml(getString(R.string.textTitleBarYeuCauGoiLai)));
    }

    private void getControl()
    {
        textViewGT = (TextView) findViewById(R.id.textViewGioithieu);
        textViewHD = (TextView) findViewById(R.id.textViewHuongDan);
        textViewTTCT = (TextView) findViewById(R.id.textViewTTChiTiet);
        buttonExcute = (Button) findViewById(R.id.buttonOk);
        editTextSDT = (EditText) findViewById(R.id.editTextSDT);
    }

    private boolean checkEditTextSDT()
    {
        String SDT = editTextSDT.getText().toString();
        if(SDT.matches(""))
        {
            AlertDialog.Builder dialog = new AlertDialog.Builder(YeuCauGoiLaiActivity.this);
            dialog.setMessage(R.string.textNeedFillPhoneNumber);
            dialog.setPositiveButton(R.string.textOK, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    editTextSDT.requestFocus();
                }
            });
            dialog.create().show();
            return false;
        }
        return true;
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
                gioithieu = getString(R.string.textYCGLMobi);
                huongdan = getString(R.string.textHDMobi) +
                        getString(R.string.textYCGL_HD);
                urlTTCT = getString(R.string.urlTTCTMobi);

                buttonExcute.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        eventClick("*105*");
                    }
                });
                break;
            }
            case "VinaPhone": {
                gioithieu = getString(R.string.textYCGLVina);
                huongdan = getString(R.string.textHDVina) +
                        getString(R.string.textYCGL_HD);
                urlTTCT = getString(R.string.urlTTCTVina);

                buttonExcute.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        eventClick("*110*");
                    }
                });
                break;
            }
            case "Viettel": {
                gioithieu = getString(R.string.textYCGLViettel);
                huongdan = getString(R.string.textHDViettel) +
                        getString(R.string.textYCGL_HD);
                urlTTCT = getString(R.string.urlTTCTViettel);

                buttonExcute.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent smsIntent = new Intent(Intent.ACTION_VIEW);
                        smsIntent.setType("vnd.android-dir/mms-sms");
                        smsIntent.putExtra("address", "9119");
                        smsIntent.putExtra("sms_body",editTextSDT.getText().toString());
                        startActivity(smsIntent);
                    }
                });
                break;
            }
            case "GMobile": {
                gioithieu = getString(R.string.textYCGLGmobi);
                huongdan = getString(R.string.textHDGmobi) +
                        getString(R.string.textYCGL_HD);
                urlTTCT = getString(R.string.urlTTCTGmobi);

                buttonExcute.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        eventClick("*9119*");
                    }
                });
                break;
            }
            case "VietNamMobile": {
                break;
            }
        }
        if (gioithieu == null)
        {
            AlertDialog.Builder dialog = new AlertDialog.Builder(YeuCauGoiLaiActivity.this);
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
    private void eventClick(String string)
    {
        if(checkEditTextSDT())
        {
            Intent callIntent = new Intent(Intent.ACTION_CALL, TienIchFragment.ussdToCallableUri(string + editTextSDT.getText().toString() + "#"));
            startActivity(callIntent);
        }
    }
}

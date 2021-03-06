package se.uit.battleteam.quanlicuocdidong.Activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
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

import se.uit.battleteam.quanlicuocdidong.R;

public class TraCuuTTTBActivity extends Activity {

    private TextView textViewGT;
    private TextView textViewHD;
    private TextView textViewTTCT;
    private Button buttonExcute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tra_cuu_tttb);
        editActionBar();
        getControl();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_tra_cuu_tttb, menu);
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
        bar.setTitle(Html.fromHtml(getString(R.string.textTitleBarTraCuuTTTB)));
    }
    private void getControl()
    {
        textViewGT = (TextView) findViewById(R.id.textViewGioithieu);
        textViewHD = (TextView) findViewById(R.id.textViewHuongDan);
        textViewTTCT = (TextView) findViewById(R.id.textViewTTChiTiet);
        buttonExcute = (Button) findViewById(R.id.buttonOk);
        buttonExcute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent smsIntent = new Intent(Intent.ACTION_VIEW);
                smsIntent.setType("vnd.android-dir/mms-sms");
                smsIntent.putExtra("address", "1414");
                smsIntent.putExtra("sms_body","TTTB");
                startActivity(smsIntent);
            }
        });
        textViewTTCT.setText(Html.fromHtml(getString(R.string.urlTTCTTraCuuTTTB)));
        textViewTTCT.setMovementMethod(LinkMovementMethod.getInstance());
    }
}

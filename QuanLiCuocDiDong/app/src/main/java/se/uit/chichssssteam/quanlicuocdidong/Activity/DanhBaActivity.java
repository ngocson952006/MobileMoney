package se.uit.chichssssteam.quanlicuocdidong.Activity;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import se.uit.chichssssteam.quanlicuocdidong.R;

public class DanhBaActivity extends Activity {

    private String array_spinner[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_ba);
        editActionBar();
        getControl();
        array_spinner=new String[5];
        array_spinner[0]="option 1";
        array_spinner[1]="option 2";
        array_spinner[2]="option 3";
        array_spinner[3]="option 4";
        array_spinner[4]="option 5";
        Spinner s = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, array_spinner);
        s.setAdapter(adapter);
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
        bar.setTitle(Html.fromHtml(getString(R.string.titleBarDanhBa)));
    }
    private void getControl()
    {

    }
}

package se.uit.chichssssteam.quanlicuocdidong.Activity;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import se.uit.chichssssteam.quanlicuocdidong.R;

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
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2196F3")));
        bar.setTitle(Html.fromHtml("<font color='#FFFFFF'>Dịch vụ yêu cầu gọi lại </font>"));

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
            dialog.setMessage("Bạn phải nhập vào số diện thoại");
            dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
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
                gioithieu = "Call me là dịch vụ giúp bạn gửi tin nhắn đề nghị gọi lại đến thuê bao" +
                        " nội mạng, ngoại mạng MobiFone.";
                huongdan = "- Bạn nhập: *105*SDT#OK\n" +
                        "Trong đó: SDT là số điện thoại bạn muốn gửi đề nghị gọi lại.";
                urlTTCT = "http://www.mobifone.vn/wps/portal/public/dich-vu/tien-ich/tien-ich-chi-tiet/call-me";

                buttonExcute.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(checkEditTextSDT())
                        {
                            Intent callIntent = new Intent(Intent.ACTION_CALL, ussdToCallableUri("*105*"+ editTextSDT.getText().toString() +"#"));
                            startActivity(callIntent);
                        }
                    }
                });
                break;
            }
            case "VinaPhone": {
                gioithieu = "Dịch vụ Call Me Back là một tiện ích nhằm giúp các thuê bao VinaPhone " +
                        "trả trước, đặc biệt là các thuê bao VinaPhone trả trước không còn tiền " +
                        "trong tài khoản, thuê bao hết thời hạn sử dụng, thuê bao bị khóa 1 chiều" +
                        " có thể gửi đề nghị gọi lại cho thuê bao di động khác";
                huongdan = "- Bạn nhập: *110*SDT# rồi bấm OK\n" +
                        "Trong đó: SDT là số điện thoại bạn muốn gửi đề nghị gọi lại.";
                urlTTCT = "http://vinaphone.com.vn/services/cmb";

                buttonExcute.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(checkEditTextSDT())
                        {
                            Intent callIntent = new Intent(Intent.ACTION_CALL, ussdToCallableUri("*110*"+ editTextSDT.getText().toString() +"#"));
                            startActivity(callIntent);
                        }

                    }
                });
                break;
            }
            case "Viettel": {
                gioithieu = "Call me là dịch vụ giúp bạn gửi tin nhắn đề nghị gọi lại đến thuê bao" +
                        " nội mạng, ngoại mạng Viettel.";
                huongdan = "- Bạn soạn tin nhắn: SDT gửi 9119\n" +
                        "Trong đó: SDT là số điện thoại bạn muốn gửi đề nghị gọi lại.";
                urlTTCT = "http://vietteltelecom.vn/index.php/chi-tiet-gtgt/de-nghi-goi-lai-call-me-back";

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
                gioithieu = "Call me là dịch vụ giúp bạn gửi tin nhắn đề nghị gọi lại đến thuê bao" +
                        " nội mạng, ngoại mạng Gmobile.";
                huongdan = "- Bạn nhập: *9119*SDT#\n" +
                        "Trong đó: SDT là số điện thoại bạn muốn gửi đề nghị gọi lại.";
                urlTTCT = "http://gmobile.vn/content/740";

                buttonExcute.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(checkEditTextSDT())
                        {
                            Intent callIntent = new Intent(Intent.ACTION_CALL, ussdToCallableUri("*9119*"+ editTextSDT.getText().toString() +"#"));
                            startActivity(callIntent);
                        }
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
            dialog.setMessage("Xin lỗi, nhà mạng của bạn không có dịch vụ này");
            dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
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
            textViewTTCT.setText(Html.fromHtml("<a href=" + urlTTCT + "> Để biết thêm thông tin chi tiết của tiện ích, bạn có thể truy cập ở đây "));
            textViewTTCT.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }
    private Uri ussdToCallableUri(String ussd) {

        String uriString = "";

        if(!ussd.startsWith("tel:"))
            uriString += "tel:";

        for(char c : ussd.toCharArray()) {

            if(c == '#')
                uriString += Uri.encode("#");
            else
                uriString += c;
        }

        return Uri.parse(uriString);
    }
}

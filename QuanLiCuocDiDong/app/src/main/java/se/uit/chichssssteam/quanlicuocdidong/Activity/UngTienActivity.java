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
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2196F3")));
        bar.setTitle(Html.fromHtml("<font color='#FFFFFF'>Dịch vụ ứng tiền </font>"));
    }
    private void getControl()
    {
        textViewGT = (TextView) findViewById(R.id.textViewGioithieu);
        textViewHD = (TextView) findViewById(R.id.textViewHuongDan);
        textViewTTCT = (TextView) findViewById(R.id.textViewTTChiTiet);
        buttonExcute = (Button) findViewById(R.id.buttonOk);
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
                gioithieu = "Ứng tiền là dịch vụ giúp thuê bao trả trước của MobiFone hết tiền" +
                        " trong tài khoản được ứng trước 3.000 đồng để tiếp tục sử dụng.";
                huongdan = "- Bước 1: soạn UT gửi 900. Hệ thống sẽ gửi tin nhắn yêu cầu xác nhận " +
                        "việc ứng tiền.\n" +
                        "- Bước 2: soạn CO gửi 900 trong vòng 10 phút kể từ khi bạn nhận được tin nhắn" +
                        " yêu cầu xác nhận ứng tiền từ hệ thống.";
                urlTTCT = "http://www.mobifone.vn/wps/portal/public/dich-vu/dich-vu-khac/dich-vu-khac-chi-tiet/ung-tien";

                buttonExcute.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent smsIntent = new Intent(Intent.ACTION_VIEW);
                        smsIntent.setType("vnd.android-dir/mms-sms");
                        smsIntent.putExtra("address", "900");
                        smsIntent.putExtra("sms_body","UT");
                        startActivity(smsIntent);
                    }
                });
                break;
            }
            case "VinaPhone": {
                gioithieu = "Ứng tiền nhanh là dịch vụ ứng cho thuê bao trả trước của VinaPhone một" +
                        " số tiền nhất định khi tài khoản chính của thuê bao không còn nhiều tiền " +
                        "(bằng hoặc ít hơn 5.000 VNĐ) và thuê bao thỏa mãn các điều kiện sử dụng " +
                        "dịch vụ của theo quy định của VinaPhone.";
                huongdan = "- Soạn tin: Y gửi 1576.";
                urlTTCT = "http://vinaphone.com.vn/services/ungtiennhanh#gioithieu-tab";

                buttonExcute.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent smsIntent = new Intent(Intent.ACTION_VIEW);
                        smsIntent.setType("vnd.android-dir/mms-sms");
                        smsIntent.putExtra("address", "1576");
                        smsIntent.putExtra("sms_body","Y");
                        startActivity(smsIntent);
                    }
                });
                break;
            }
            case "Viettel": {
                gioithieu = "Ứng tiền khi hết tài khoản là dịch vụ giúp thuê bao di động trả " +
                        "trước của Viettel có thể ứng trước 3.000đ vào tài khoản gốc để tiếp tục " +
                        "tiêu dùng khi tài khoản hết tiền mà chưa kịp nạp thẻ.";
                huongdan = "- Bước 1: Khách hàng bấm trực tiếp từ màn hình chính *911#.\n" +
                        "- Bước 2: Khách hàng nhấn phím OK hoặc nhấn phím gọi.";
                urlTTCT = "http://vietteltelecom.vn/index.php/chi-tiet-gtgt/ung-tien-khi-het-tai-khoan";

                buttonExcute.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent callIntent = new Intent(Intent.ACTION_CALL, ussdToCallableUri("*911#"));
                        startActivity(callIntent);
                    }
                });
                break;
            }
            case "GMobile": { //khong co dich vu nay
                break;
            }
            case "VietNamMobile": {
                gioithieu = "Ứng tiền là dịch vụ giúp thuê bao trả trước của Vietnamobile hết tiền" +
                        " trong tài khoản được ứng trước 3.000 đồng để tiếp tục sử dụng.";
                huongdan = "- Khách hàng bấm trực tiếp từ màn hình chính *911#OK";
                urlTTCT = "http://www.vietnamobile.com.vn/layout1.php?pdid=66#";

                buttonExcute.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent callIntent = new Intent(Intent.ACTION_CALL, ussdToCallableUri("*911#"));
                        startActivity(callIntent);
                    }
                });
                break;
            }
        }
        if (gioithieu == null)
        {
            AlertDialog.Builder dialog = new AlertDialog.Builder(UngTienActivity.this);
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

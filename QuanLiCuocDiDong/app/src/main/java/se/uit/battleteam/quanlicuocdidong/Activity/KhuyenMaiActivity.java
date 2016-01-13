package se.uit.battleteam.quanlicuocdidong.Activity;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import se.uit.battleteam.quanlicuocdidong.Manager.PromotionItem;
import se.uit.battleteam.quanlicuocdidong.Manager.XMLDOMParser;
import se.uit.battleteam.quanlicuocdidong.R;

public class KhuyenMaiActivity extends Activity {

    private ListView lstViewKM;
    private ArrayList<PromotionItem> arrayListPromotion= new ArrayList<PromotionItem>();
    private ArrayAdapter<PromotionItem> arrayAdapterPromotion = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khuyen_mai);

        editActionBar();
        if(!isNetworkConnected()) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(KhuyenMaiActivity.this);
            dialog.setMessage(R.string.textNeedNetworkToUse);
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
            initListView();
            addEventListItems();
            if(getUrlPromotion() == "")
            {
                AlertDialog.Builder dialog = new AlertDialog.Builder(KhuyenMaiActivity.this);
                dialog.setMessage(R.string.textPromotionNotSupport);
                dialog.setPositiveButton(R.string.textOK, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                dialog.create().show();
            }
            else {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        new ReadXML().execute(getUrlPromotion());
                    }
                });
            }
        }
    }
    private void addEventListItems()
    {
        lstViewKM.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                String url = ((PromotionItem)parent.getItemAtPosition(position)).getUrl();
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }
    private void initListView()
    {
        lstViewKM = (ListView) findViewById(R.id.list);
        arrayAdapterPromotion = new ArrayAdapter<PromotionItem>(KhuyenMaiActivity.this,
                /*android.R.layout.simple_list_item_1*/R.layout.custom_promotion_items,
                arrayListPromotion);
        lstViewKM.setAdapter(arrayAdapterPromotion);
    }
    private void editActionBar()
    {
        ActionBar bar = getActionBar();
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setDisplayShowHomeEnabled(false);
        bar.setDisplayShowCustomEnabled(false);
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor(getString(R.string.colorActionBar))));
        bar.setTitle(Html.fromHtml(getString(R.string.textTitleBarKhuyenMai)));

    }

    private String getUrlPromotion()
    {
        SharedPreferences settings = getSharedPreferences(MainActivity.PREFS_NAME, MODE_PRIVATE);
        String mangDiDong = settings.getString(MainActivity.KEY_NHAMANG, MainActivity.VALUE_DEFAULT);
        String url = null;
        switch (mangDiDong)
        {
            case ChonMangDiDongActivity.MOBIFONE: {
                url = "http://qnghiauit.16mb.com/RssFile/mobifone.xml";
                break;
            }
            case ChonMangDiDongActivity.VINAPHONE: {
                url = "http://qnghiauit.16mb.com/RssFile/vinaphone.xml";
                break;
            }
            case ChonMangDiDongActivity.VIETTEL: {
                url = "http://qnghiauit.16mb.com/RssFile/viettel.xml";
                break;
            }
            case ChonMangDiDongActivity.GMOBILE: {
                url = "";
                break;
            }
            case ChonMangDiDongActivity.VIETNAMOBILE: {
                url = "http://qnghiauit.16mb.com/RssFile/vietnamobile.xml";
                break;
            }
        }
        return url;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_khuyen_mai, menu);
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



    class ReadXML extends AsyncTask<String, Intent, String>{
        @Override
        protected String doInBackground(String... params) {
            String kq = getXmlFromUrl(params[0]);
            return kq;
        }

        @Override
        protected void onPostExecute(String s) {
            XMLDOMParser parser = new XMLDOMParser();
            Document document = parser.getDocument(s);
            NodeList nodeList = document.getElementsByTagName("item");
            for(int i=0;i<nodeList.getLength();i++)
            {
                Element e = (Element) nodeList.item(i);
                PromotionItem item = new PromotionItem(parser.getValue(e, "title")
                        ,parser.getValue(e,"link"));
                arrayListPromotion.add(item);
                arrayAdapterPromotion.notifyDataSetChanged();
            }

        }
    }
    private String getXmlFromUrl(String urlString) {
        String xml = null;
        try {
            // defaultHttpClient lấy toàn bộ dữ liệu trong http đổ vào 1 chuỗi String
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(urlString);

            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            xml = EntityUtils.toString(httpEntity, HTTP.UTF_8);
            // set UTF-8 cho ra chữ unikey
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // return XML
        return xml;
    }
    private boolean isNetworkConnected() {
        Runtime runtime = Runtime.getRuntime();
        try {

            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int     exitValue = ipProcess.waitFor();
            return (exitValue == 0);

        } catch (IOException e)          { e.printStackTrace(); }
        catch (InterruptedException e) { e.printStackTrace(); }

        return false;
    }
}

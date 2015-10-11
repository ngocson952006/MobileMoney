package se.uit.chichssssteam.quanlicuocdidong.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
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

import se.uit.chichssssteam.quanlicuocdidong.Manager.PromotionItem;
import se.uit.chichssssteam.quanlicuocdidong.Manager.XMLDOMParser;
import se.uit.chichssssteam.quanlicuocdidong.R;

public class KhuyenMaiActivity extends Activity {

    ListView lstViewKM;
    ArrayList<PromotionItem> arrayListPromotion= new ArrayList<PromotionItem>();
    ArrayAdapter<PromotionItem> arrayAdapterPromotion = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khuyen_mai);

        editActionBar();
        initListView();
        addEventListItems();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new ReadXML().execute(getUrlPromotion());
            }
        });
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
                android.R.layout.simple_list_item_1,
                arrayListPromotion);
        lstViewKM.setAdapter(arrayAdapterPromotion);
    }
    private void editActionBar()
    {
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setDisplayShowHomeEnabled(false);
        getActionBar().setDisplayShowCustomEnabled(false);
        setTitle("Trang thông tin khuyến mãi");
    }

    private String getUrlPromotion()
    {
        SharedPreferences settings = getSharedPreferences(MainActivity.PREFS_NAME, MODE_PRIVATE);
        String mangDiDong = settings.getString(MainActivity.KEY_NHAMANG, MainActivity.VALUE_DEFAULT);
        String url = null;
        switch (mangDiDong)
        {
            case "Mobifone": {
                url = "http://qnghiauit.16mb.com/RssFile/mobiRss.xml";
                break;
            }
            case "VinaPhone": {
                url = "http://qnghiauit.16mb.com/RssFile/vinaphoneRss.xml";
                break;
            }
            case "Viettel": {
                url = "http://qnghiauit.16mb.com/RssFile/viettelRss.xml";
                break;
            }
            case "GMobile": {
                url = "http://vnexpress.net/rss/so-hoa.rss";
                break;
            }
            case "VietNamMobile": {
                url = "http://vnexpress.net/rss/so-hoa.rss";
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
}

package se.uit.battleteam.quanlicuocdidong.Activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import se.uit.battleteam.quanlicuocdidong.Manager.Contact;
import se.uit.battleteam.quanlicuocdidong.Manager.ExpandableListAdapter;
import se.uit.battleteam.quanlicuocdidong.R;

public class DanhBaActivity extends Activity {


    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<Contact> taxiHN,taxiDN,taxiHCM;
    List<Contact> tuVan,cuuHo;
    RadioGroup radioGroup;
    List<String> listDataHeader;
    HashMap<String, List<Contact>> listDataChild;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_ba);
        editActionBar();

        expListView = (ExpandableListView) findViewById(R.id.expandableListView);
        prepareListData();
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        expListView.setAdapter(listAdapter);
        RadioButton radioButton = (RadioButton) findViewById(R.id.radioButtonHCM);
        radioButton.setChecked(true);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup1);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                listDataChild.remove(listDataHeader.get(2));
                switch (checkedId) {
                    case R.id.radioButtonHN: {
                        listDataChild.put(listDataHeader.get(2), taxiHN);
                        break;
                    }
                    case R.id.radioButtonDn: {
                        listDataChild.put(listDataHeader.get(2), taxiDN);
                        break;
                    }
                    case R.id.radioButtonHCM: {
                        listDataChild.put(listDataHeader.get(2), taxiHCM);
                        break;
                    }
                }
                listAdapter.notifyDataSetChanged();
            }
        });

        // Listview on child click listener
        expListView.setOnChildClickListener(new OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" +
                        listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition).get_phoneNumber()));
                startActivity(intent);
                return false;
            }
        });

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
    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<Contact>>();

        // Adding child data
        listDataHeader.add(getString(R.string.textCuuHo));
        listDataHeader.add(getString(R.string.textTuVan));
        listDataHeader.add(getString(R.string.textTaxi));

        // Adding child data
        cuuHo = new ArrayList<Contact>();
        cuuHo.add(new Contact(getString(R.string.CongAn),"113"));
        cuuHo.add(new Contact(getString(R.string.CuuHoa),"114"));
        cuuHo.add(new Contact(getString(R.string.CuuThuong),"115"));


        tuVan = new ArrayList<Contact>();
        tuVan.add(new Contact(getString(R.string.TuVanGiaoDuc),"19006869"));
        tuVan.add(new Contact(getString(R.string.TuVanHonNhan),"19006590"));
        tuVan.add(new Contact(getString(R.string.TuVanTieuDung),"19006838"));
        tuVan.add(new Contact(getString(R.string.TuVanTongHop),"1080"));
        tuVan.add(new Contact(getString(R.string.TuVanLuat),"19006162"));
        tuVan.add(new Contact(getString(R.string.TuVanSinhsan),"19008909"));
        tuVan.add(new Contact(getString(R.string.TuVanSucKhoeMienPhi),"1800585865"));
        tuVan.add(new Contact(getString(R.string.TuVanSucKhoeEDocter),"19006115"));

        taxiHN = new ArrayList<Contact>();
        taxiHN.add(new Contact("Taxi Hà Nội","0438535353"));
        taxiHN.add(new Contact("Taxi Mai Linh","0438222666"));
        taxiHN.add(new Contact("Taxi Thăng Long ","0439717171"));
        taxiHN.add(new Contact("Taxi Thanh Nga ","0438215215"));
        taxiHN.add(new Contact("Taxi Nội bài","0438868888"));
        taxiHN.add(new Contact("Taxi Mỹ Đình ","0438333888"));
        taxiHN.add(new Contact("Taxi Vạn Xuân ","0438222888"));
        taxiHN.add(new Contact("Taxi Thăng Long ","0439717171"));
        taxiHN.add(new Contact("Taxi Thủ đô","0438333333"));
        taxiHN.add(new Contact("Taxi JAC","0438626262"));

        taxiDN = new ArrayList<Contact>();
        taxiDN.add(new Contact("Taxi Mai Linh","05113565656"));
        taxiDN.add(new Contact("Taxi Tiên Sa","05113797979"));
        taxiDN.add(new Contact("VinaSun Green Taxi","05113686868"));
        taxiDN.add(new Contact("Taxi Hàng Không (Airport Taxi)","05113272727"));
        taxiDN.add(new Contact("Taxi Sông Hàn","05113727272"));
        taxiDN.add(new Contact("Taxi Datranco (Dana Taxi)","05113815815"));
        taxiDN.add(new Contact("Taxi Hương Lúa","05113828282"));

        taxiHCM = new ArrayList<Contact>();
        taxiHCM.add(new Contact("Taxi Vinasun","0838272727"));
        taxiHCM.add(new Contact("Taxi Sài Gòn","0838232323"));
        taxiHCM.add(new Contact("Taxi Sài Gòn (SASCO)","0838424242"));
        taxiHCM.add(new Contact("Taxi Sài Gòn Tourist","0838464646"));
        taxiHCM.add(new Contact("Taxi Bến Thành","0838422422"));
        taxiHCM.add(new Contact("Taxi Petrolimex","0835121212"));
        taxiHCM.add(new Contact("Taxi Gia Định","0838989898"));
        taxiHCM.add(new Contact("Taxi Miền Đông","0835111111"));
        taxiHCM.add(new Contact("Taxi Hoàn Mỹ", "0838323323"));
        taxiHCM.add(new Contact("Taxi Chợ Lớn","0838363636"));

        listDataChild.put(listDataHeader.get(0), cuuHo); // Header, Child data
        listDataChild.put(listDataHeader.get(1), tuVan);
        listDataChild.put(listDataHeader.get(2), taxiHCM);
    }
}

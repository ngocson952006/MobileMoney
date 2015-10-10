package se.uit.chichssssteam.quanlicuocdidong.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import se.uit.chichssssteam.quanlicuocdidong.Manager.NavigationDrawerCallbacks;
import se.uit.chichssssteam.quanlicuocdidong.Manager.OnFragmentInteractionListener;
import se.uit.chichssssteam.quanlicuocdidong.Manager.PackageNetwork;
import se.uit.chichssssteam.quanlicuocdidong.R;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerCallbacks,OnFragmentInteractionListener {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    public static final String PREFS_NAME = "MySetting";
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private Toolbar mToolbar;
    private String mangDiDong;
    private String goiCuoc;
    private int idImage;
    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_drawer);

        // Set up the drawer.
        mNavigationDrawerFragment.setup(R.id.fragment_drawer, (DrawerLayout) findViewById(R.id.drawer), mToolbar);

        // Get infomation from ChonGoiCuocActivitity
        getMobileNetwork();
        setMobileNetworkUserData();


    }
    @Override
    protected void onStop(){
        // We need an Editor object to make preference changes.
        // All objects are from android.context.Context
        saveSharedPreferences();
        super.onStop();

    }

    public void saveSharedPreferences()
    {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("GoiCuoc", goiCuoc);
        editor.putString("NhaMang", mangDiDong);
        editor.putInt("idImage",idImage);
        // Commit the edits!
        editor.commit();
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        Fragment fragment = null;
        Class fragmentClass = null;

        switch(position) {
            case 0:
                fragmentClass = ThongKeFragment.class;
                setTitle(ThongKeFragment.getNameFragment());
                break;
            case 1:
                fragmentClass = ThangFragment.class;
                setTitle(ThangFragment.getNameFragment());
                break;
            case 2:
                fragmentClass = NgayFragment.class;
                setTitle(NgayFragment.getNameFragment());
                break;
            case 3:
                fragmentClass = CaiDatFragment.class;
                setTitle(CaiDatFragment.getNameFragment());
                break;
            case 4:
                fragmentClass = GioiThieuFragment.class;
                setTitle(GioiThieuFragment.getNameFragment());
                break;
        }
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();

    }


    @Override
    public void onBackPressed() {
        if (mNavigationDrawerFragment.isDrawerOpen())
            mNavigationDrawerFragment.closeDrawer();
        else
        {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }

            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Chạm lần nữa để thoát", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce=false;
                }
            }, 2000);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
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

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onThongKeFragmentInteraction(Uri uri){
        // Do different stuff
    }
    @Override
    public void onThangFragmentInteraction(Uri uri){
        // Do different stuff
    }
    @Override
    public void onNgayFragmentInteraction(Uri uri){
        // Do different stuff
    }
    @Override
    public void onCaiDatFragmentInteraction(){
        SharedPreferences settings = getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.clear();
        editor.commit();
        Intent myIntent = new Intent(MainActivity.this,ChonMangDiDongActivity.class);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(myIntent);
    }
    @Override
    public void onGioiThieuFragmentInteraction(Uri uri){

    }
    private void getMobileNetwork()
    {
        Intent callerIntent = getIntent();
        Bundle packegeFromCaller = callerIntent.getBundleExtra("Mang");
        if (packegeFromCaller != null) {
            mangDiDong = ((PackageNetwork)packegeFromCaller.getSerializable("PackageNetworkItem")).getNameNetwork();
            goiCuoc = ((PackageNetwork)packegeFromCaller.getSerializable("PackageNetworkItem")).getPackageName();
            idImage = ((PackageNetwork)packegeFromCaller.getSerializable("PackageNetworkItem")).getIdResourceImage();
        }
        else {
            // Restore preferences
            SharedPreferences settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
            goiCuoc = settings.getString("GoiCuoc", "Not found");
            mangDiDong = settings.getString("NhaMang","Not found");
            idImage = settings.getInt("idImage",0);
        }
        saveSharedPreferences();
    }
    private void setMobileNetworkUserData()
    {
        mNavigationDrawerFragment.setUserData("Nhà mạng: " + mangDiDong, "Gói cước: " + goiCuoc);
    }
    public String getGoiCuoc()
    {
        return goiCuoc;
    }

}

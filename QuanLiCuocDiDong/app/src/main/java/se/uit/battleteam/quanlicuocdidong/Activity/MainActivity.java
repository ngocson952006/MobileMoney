package se.uit.battleteam.quanlicuocdidong.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import se.uit.battleteam.quanlicuocdidong.BackgroundService.PhoneStateReceiver;
import se.uit.battleteam.quanlicuocdidong.DB.CallLog;
import se.uit.battleteam.quanlicuocdidong.DB.DAO_CallLog;
import se.uit.battleteam.quanlicuocdidong.DB.DAO_MessageLog;
import se.uit.battleteam.quanlicuocdidong.DB.DAO_Statistic;
import se.uit.battleteam.quanlicuocdidong.DB.MessageLog;
import se.uit.battleteam.quanlicuocdidong.Manager.DateTimeManager;
import se.uit.battleteam.quanlicuocdidong.Manager.NavigationDrawerCallbacks;
import se.uit.battleteam.quanlicuocdidong.Manager.NavigationDrawerFragment;
import se.uit.battleteam.quanlicuocdidong.Manager.OnFragmentInteractionListener;
import se.uit.battleteam.quanlicuocdidong.Manager.PackageNetwork;
import se.uit.battleteam.quanlicuocdidong.Manager.PhoneLogManager;
import se.uit.battleteam.quanlicuocdidong.NetworkPackage.BigKool;
import se.uit.battleteam.quanlicuocdidong.NetworkPackage.BigSave;
import se.uit.battleteam.quanlicuocdidong.NetworkPackage.BillionareFive;
import se.uit.battleteam.quanlicuocdidong.NetworkPackage.BillionareThree;
import se.uit.battleteam.quanlicuocdidong.NetworkPackage.BillionareTwo;
import se.uit.battleteam.quanlicuocdidong.NetworkPackage.Economy;
import se.uit.battleteam.quanlicuocdidong.NetworkPackage.HiSchool;
import se.uit.battleteam.quanlicuocdidong.NetworkPackage.MobiCard;
import se.uit.battleteam.quanlicuocdidong.NetworkPackage.MobiGold;
import se.uit.battleteam.quanlicuocdidong.NetworkPackage.MobiQ;
import se.uit.battleteam.quanlicuocdidong.NetworkPackage.NumberHeaderManager;
import se.uit.battleteam.quanlicuocdidong.NetworkPackage.PackageFee;
import se.uit.battleteam.quanlicuocdidong.NetworkPackage.QStudent;
import se.uit.battleteam.quanlicuocdidong.NetworkPackage.QTeen;
import se.uit.battleteam.quanlicuocdidong.NetworkPackage.Qkids;
import se.uit.battleteam.quanlicuocdidong.NetworkPackage.SV2014;
import se.uit.battleteam.quanlicuocdidong.NetworkPackage.SeaPlus;
import se.uit.battleteam.quanlicuocdidong.NetworkPackage.SevenColor;
import se.uit.battleteam.quanlicuocdidong.NetworkPackage.Student;
import se.uit.battleteam.quanlicuocdidong.NetworkPackage.Tomato;
import se.uit.battleteam.quanlicuocdidong.NetworkPackage.TomatoBL;
import se.uit.battleteam.quanlicuocdidong.NetworkPackage.VMOne;
import se.uit.battleteam.quanlicuocdidong.NetworkPackage.VMax;
import se.uit.battleteam.quanlicuocdidong.NetworkPackage.VinaCard;
import se.uit.battleteam.quanlicuocdidong.NetworkPackage.VinaXtra;
import se.uit.battleteam.quanlicuocdidong.R;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerCallbacks,OnFragmentInteractionListener {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    public static final String PREFS_NAME = "MySetting";
    public static final String KEY_GOICUOC = "GoiCuoc";
    public static final String KEY_NHAMANG = "NhaMang";
    public static final String KEY_IDIMAGE = "idImage";
    public static final String KEY_ALLOWPOPUP = "AllowPopup";
    public static final String VALUE_DEFAULT = "Not found";
    public static final String KEY_PACKAGEFEE = "PackageFee";

    /*Bo sung value luu trang thai kiem tra da cap nhat lan dau tien chua khi user thay sim moi*/
    public static final String KEY_UPDATE_STATE = "UpdateState";
    /**/
    public static final String KEY_LAST_TIME_UPDATE_CALL = "LastUpdateCall";
    public static final String KEY_LAST_TIME_UPDATE_MESSAGE = "LastUpdateMessage";
    public static final long TIME_DEDAULT = 0;
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private Toolbar mToolbar;
    private String mangDiDong;
    private String goiCuoc;
    private int idImage;
    boolean doubleBackToExitPressedOnce = false;
    private PackageFee _myPackageFee;
    private PhoneLogManager _logManager;
    private DAO_CallLog _callLogTableAdapter;
    private DAO_MessageLog _messageLogTableAdapter;
    private DAO_Statistic _statisticTableAdapter;
    private DateTimeManager _dateTimeManager;
    private long _lastCallUpdate;
    private long _lastMessageUpdate;
    private ProgressBar _progressBar;
    //Background service

    private PhoneStateReceiver _myPhoneStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        _dateTimeManager = DateTimeManager.get_instance();
       this._callLogTableAdapter = new DAO_CallLog(this);
        this._statisticTableAdapter = new DAO_Statistic(this);
        this._messageLogTableAdapter = new DAO_MessageLog(this);
        this._callLogTableAdapter.Open();
        this._messageLogTableAdapter.Open();
        this._statisticTableAdapter.Open();
        this._myPhoneStateListener = new PhoneStateReceiver();
        initNavigationDrawer();
        _progressBar = (ProgressBar)findViewById(R.id.progressBar);
        getNetwork();
        setMobileNetworkUserData();

        _progressBar.setVisibility(View.VISIBLE);

        new DatabaseExecuteTask(_lastCallUpdate,_lastMessageUpdate).execute();
    }


    @Override
    protected void onStop() {
        // We need an Editor object to make preference changes.
        // All objects are from android.context.Context
        saveSharedPreferences();
        super.onStop();

    }

    @Override
    protected void onDestroy() {

        saveSharedPreferences();
        super.onDestroy();
    }

    public void initNavigationDrawer() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        //mToolbar.setTitleTextColor(0xFFFFFFFF);

        setSupportActionBar(mToolbar);
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_drawer);

        // Set up the drawer.
        mNavigationDrawerFragment.setup(R.id.fragment_drawer, (DrawerLayout) findViewById(R.id.drawer), mToolbar);
    }

    public void saveSharedPreferences() {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(KEY_GOICUOC, goiCuoc);
        editor.putString(KEY_NHAMANG, mangDiDong);
        editor.putInt(KEY_IDIMAGE, idImage);
        editor.putLong(KEY_LAST_TIME_UPDATE_CALL, _lastCallUpdate);
        editor.putLong(KEY_LAST_TIME_UPDATE_MESSAGE, _lastMessageUpdate);
        // Commit the edits!
        editor.commit();
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        Fragment fragment = null;
        Class fragmentClass = null;
        if(position == 2)
        {
            setTitle(getString(R.string.titleCaiDatFragment));
            fragment = CaiDatFragment.newInstance(_myPackageFee); //Thay string = object packagefee
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();
            return;
        }
        switch (position) {
            case 0:
                fragmentClass = ThongKeFragment.class;
                setTitle(getString(R.string.titleThongKeFragment));
                break;
            case 1:
                fragmentClass = NgayFragment.class;
                setTitle(getString(R.string.titleNgayFragment));
                break;
            /*
            case 2:
                fragmentClass = CaiDatFragment.class;
                setTitle(getString(R.string.titleCaiDatFragment));
                break;
                */
            case 3:
                fragmentClass = TienIchFragment.class;
                setTitle(getString(R.string.titleTienIchFragment));
                break;
            case 4:
                fragmentClass = GioiThieuFragment.class;
                setTitle(getString(R.string.titleGioiThieuFragment));
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
        else {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }

            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Chạm lần nữa để thoát", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
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
    public void onThongKeFragmentInteraction(Uri uri) {
        // Do different stuff
    }

    @Override
    public void onThangFragmentInteraction(Uri uri) {
        // Do different stuff
    }

    @Override
    public void onNgayFragmentInteraction(Uri uri) {
        // Do different stuff
    }

    @Override
    public void onCaiDatFragmentInteraction() {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(KEY_GOICUOC, "");
        editor.putString(KEY_NHAMANG, "");
        editor.commit();
        Intent myIntent = new Intent(MainActivity.this, ChonMangDiDongActivity.class);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(myIntent);
    }

    @Override
    public void onGioiThieuFragmentInteraction(Uri uri) {

    }

    @Override
    public void onTienIchFragmentInteraction(Uri uri) {

    }
    public void RenewCallData(long time)
    {

        List<CallLog> _listCall = _logManager.LoadCallLogAfterTimeSpan(time);
        if(_listCall.isEmpty())
            return;
        for(CallLog i : _listCall)
        {
            if(i.get_callNumber().length() >=10) {
                _callLogTableAdapter.CreateCallLogRow(i);
                String callDate = _dateTimeManager.convertToDMYHms(i.get_callDate());
                int callFee = i.get_callFee();
                int callDuration = i.get_callDuration();
                if (_statisticTableAdapter.FindStatisticByMonthYear(_dateTimeManager.getMonth(callDate), _dateTimeManager.getYear(callDate)) == null) {
                    _statisticTableAdapter.CreateStatisticRow(_dateTimeManager.getMonth(callDate), _dateTimeManager.getYear(callDate));

                }
                if (i.get_callType() == 0) {
                    _statisticTableAdapter.UpdateInnerCallInfo(_dateTimeManager.getMonth(callDate),
                            _dateTimeManager.getYear(callDate), callFee, callDuration);


                } else if (i.get_callType() == 1) {
                    _statisticTableAdapter.UpdateOuterCallInfo(_dateTimeManager.getMonth(callDate),
                            _dateTimeManager.getYear(callDate), callFee, callDuration);

                }
            }
        }

        _listCall.clear();
        _lastCallUpdate = _logManager.GetLastedCallTime();
        saveSharedPreferences();

    }
    public void RenewMessageData(long time)
    {

        List<MessageLog> _listMessage = _logManager.LoadMessageLogAfterTimeSpan(time);
        if(_listMessage.isEmpty())
            return;
        for(MessageLog i : _listMessage)
        {
            if(i.get_receiverNumber().length() >=10) {
                _messageLogTableAdapter.CreateMessageLogRow(i);
                String messageDate = _dateTimeManager.convertToDMYHms(i.get_messageDate());
                int messageFee = i.get_messageFee();
                if (_statisticTableAdapter.FindStatisticByMonthYear(_dateTimeManager.getMonth(messageDate), _dateTimeManager.getYear(messageDate)) == null) {
                    _statisticTableAdapter.CreateStatisticRow(_dateTimeManager.getMonth(messageDate), _dateTimeManager.getYear(messageDate));

                }
                if (i.get_messageType() == 0) {
                    _statisticTableAdapter.UpdateInnerMessageInfo(_dateTimeManager.getMonth(messageDate), _dateTimeManager.getYear(messageDate), messageFee);
                } else if (i.get_messageType() == 1) {
                    _statisticTableAdapter.UpdateOuterMessageInfo(_dateTimeManager.getMonth(messageDate), _dateTimeManager.getYear(messageDate), messageFee);
                }
            }
        }
        _listMessage.clear();

        _lastMessageUpdate = _logManager.GetLastedMessageTime();
        saveSharedPreferences();

    }
   // public void RenewData()
   // {
        //RenewCallData();
        //RenewMessageData();
    //}
    public void FirstInitCallLog()
    {

        List<CallLog> _listCall = _logManager.LoadCallLogFromPhone();
        if(_listCall.isEmpty())
            return;
        for (CallLog i : _listCall)
        {
           // CallLog temp = i;
            if(i.get_callNumber().length() >=10) {
                _callLogTableAdapter.CreateCallLogRow(i);
                String callDate = _dateTimeManager.convertToDMYHms(i.get_callDate());
                int callFee = i.get_callFee();
                int callDuration = i.get_callDuration();
                if (_statisticTableAdapter.FindStatisticByMonthYear(_dateTimeManager.getMonth(callDate), _dateTimeManager.getYear(callDate)) == null) {
                    _statisticTableAdapter.CreateStatisticRow(_dateTimeManager.getMonth(callDate), _dateTimeManager.getYear(callDate));

                }
                if (i.get_callType() == 0) {
                    _statisticTableAdapter.UpdateInnerCallInfo(_dateTimeManager.getMonth(callDate),
                            _dateTimeManager.getYear(callDate), callFee, callDuration);


                } else if (i.get_callType() == 1) {
                    _statisticTableAdapter.UpdateOuterCallInfo(_dateTimeManager.getMonth(callDate),
                            _dateTimeManager.getYear(callDate), callFee, callDuration);

                }
            }
        }

        _listCall.clear();
        _lastCallUpdate = _logManager.GetLastedCallTime();
        saveSharedPreferences();
    }
    public void FirstInitMessageLog()
    {

        List<MessageLog> _listMessage = _logManager.LoadMessageLogFromPhone();
        if(_listMessage.isEmpty())
            return;
        for(MessageLog i: _listMessage)
        {
            if(i.get_receiverNumber().length() >= 10) {
                _messageLogTableAdapter.CreateMessageLogRow(i);
                String messageDate = _dateTimeManager.convertToDMYHms(i.get_messageDate());
                int messageFee = i.get_messageFee();
                if (_statisticTableAdapter.FindStatisticByMonthYear(_dateTimeManager.getMonth(messageDate), _dateTimeManager.getYear(messageDate)) == null) {
                    _statisticTableAdapter.CreateStatisticRow(_dateTimeManager.getMonth(messageDate), _dateTimeManager.getYear(messageDate));

                }
                if (i.get_messageType() == 0) {
                    _statisticTableAdapter.UpdateInnerMessageInfo(_dateTimeManager.getMonth(messageDate), _dateTimeManager.getYear(messageDate), messageFee);
                } else if (i.get_messageType() == 1) {

                    _statisticTableAdapter.UpdateOuterMessageInfo(_dateTimeManager.getMonth(messageDate), _dateTimeManager.getYear(messageDate), messageFee);
                }
            }
        }

        _listMessage.clear();
        _lastMessageUpdate = _logManager.GetLastedMessageTime();
        saveSharedPreferences();

    }

    public void getNetwork()
    {
        Intent callerIntent = getIntent();
        Bundle packegeFromCaller = callerIntent.getBundleExtra("Mang");
        if (packegeFromCaller != null)
        {
            mangDiDong = ((PackageNetwork) packegeFromCaller.getSerializable("PackageNetworkItem")).getNameNetwork();
            goiCuoc = ((PackageNetwork) packegeFromCaller.getSerializable("PackageNetworkItem")).getPackageName();
            idImage = ((PackageNetwork) packegeFromCaller.getSerializable("PackageNetworkItem")).getIdResourceImage();
            SharedPreferences settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
            _lastCallUpdate = settings.getLong(KEY_LAST_TIME_UPDATE_CALL, TIME_DEDAULT);
            _lastMessageUpdate = settings.getLong(KEY_LAST_TIME_UPDATE_MESSAGE, TIME_DEDAULT);
            this.InitPackage(this.goiCuoc);
            this._logManager = PhoneLogManager.get_instance(this, _myPackageFee);

        }
        else
        {
            // Restore preferences
            SharedPreferences settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
            goiCuoc = settings.getString(KEY_GOICUOC, VALUE_DEFAULT);
            mangDiDong = settings.getString(KEY_NHAMANG, VALUE_DEFAULT);
            idImage = settings.getInt(KEY_IDIMAGE, 0);
            _lastCallUpdate = settings.getLong(KEY_LAST_TIME_UPDATE_CALL, TIME_DEDAULT);
            _lastMessageUpdate = settings.getLong(KEY_LAST_TIME_UPDATE_MESSAGE, TIME_DEDAULT);
            this.InitPackage(this.goiCuoc);
            this._logManager = PhoneLogManager.get_instance(this, _myPackageFee);

        }
        saveSharedPreferences();
    }

    private void setMobileNetworkUserData() {
        mNavigationDrawerFragment.setUserData("Nhà mạng: " + mangDiDong, "Gói cước: " + goiCuoc);
    }

    public String getGoiCuoc() {
        return goiCuoc;
    }


    public void InitPackage(String _package) {
        switch (_package) {
            case "Mobicard": {
                _myPackageFee = new MobiCard();
                _myPackageFee.set_myNetwork(NumberHeaderManager.networkName.mobifone);
                break;
            }
            case "MobiGold": {
                _myPackageFee = new MobiGold();
                _myPackageFee.set_myNetwork(NumberHeaderManager.networkName.mobifone);
                break;
            }
            case "MobiQ": {
                _myPackageFee = new MobiQ();
                _myPackageFee.set_myNetwork(NumberHeaderManager.networkName.mobifone);
                break;
            }
            case "Q-Student": {
                _myPackageFee = new QStudent();
                _myPackageFee.set_myNetwork(NumberHeaderManager.networkName.mobifone);
                break;
            }
            case "Q-Teen": {
                _myPackageFee = new QTeen();
                _myPackageFee.set_myNetwork(NumberHeaderManager.networkName.mobifone);
                break;
            }
            case "Q-Kids": {
                _myPackageFee = new Qkids();
                _myPackageFee.set_myNetwork(NumberHeaderManager.networkName.mobifone);
                break;
            }
            case "VinaCard": {
                _myPackageFee = new VinaCard();
                _myPackageFee.set_myNetwork(NumberHeaderManager.networkName.vinaphone);
                break;
            }
            case "VinaXtra": {
                _myPackageFee = new VinaXtra();
                _myPackageFee.set_myNetwork(NumberHeaderManager.networkName.vinaphone);
                break;
            }
            case "Economy": {
                _myPackageFee = new Economy();
                _myPackageFee.set_myNetwork(NumberHeaderManager.networkName.viettel);
                break;
            }
            case "Tomato": {
                _myPackageFee = new Tomato();
                _myPackageFee.set_myNetwork(NumberHeaderManager.networkName.viettel);
                break;
            }
            case "Student": {
                _myPackageFee = new Student();
                _myPackageFee.set_myNetwork(NumberHeaderManager.networkName.viettel);
                break;
            }
            case "Sea+": {
                _myPackageFee = new SeaPlus();
                _myPackageFee.set_myNetwork(NumberHeaderManager.networkName.viettel);
                break;
            }
            case "Hi School": {
                _myPackageFee = new HiSchool();
                _myPackageFee.set_myNetwork(NumberHeaderManager.networkName.viettel);
                break;
            }
            case "7Colors": {
                _myPackageFee = new SevenColor();
                _myPackageFee.set_myNetwork(NumberHeaderManager.networkName.viettel);
                break;
            }
            case "Buôn làng": {
                _myPackageFee = new TomatoBL();
                _myPackageFee.set_myNetwork(NumberHeaderManager.networkName.viettel);
                break;
            }
            case "Big Save": {
                _myPackageFee = new BigSave();
                _myPackageFee.set_myNetwork(NumberHeaderManager.networkName.Gmobile);
                break;
            }
            case "Big & Kool": {
                _myPackageFee = new BigKool();
                _myPackageFee.set_myNetwork(NumberHeaderManager.networkName.Gmobile);
                break;
            }
            case "Tỉ phú 2": {
                _myPackageFee = new BillionareTwo();
                _myPackageFee.set_myNetwork(NumberHeaderManager.networkName.Gmobile);
                break;
            }
            case "Tỉ phú 3": {
                _myPackageFee = new BillionareThree();
                _myPackageFee.set_myNetwork(NumberHeaderManager.networkName.Gmobile);
                break;
            }
            case "Tỉ phú 5": {
                _myPackageFee = new BillionareFive();
                _myPackageFee.set_myNetwork(NumberHeaderManager.networkName.Gmobile);
                break;
            }
            case "VM One": {
                _myPackageFee = new VMOne();
                _myPackageFee.set_myNetwork(NumberHeaderManager.networkName.vietnamobile);
                break;
            }
            case "VMax": {
                _myPackageFee = new VMax();
                _myPackageFee.set_myNetwork(NumberHeaderManager.networkName.vietnamobile);
                break;
            }
            case "SV 2014": {
                _myPackageFee = new SV2014();
                _myPackageFee.set_myNetwork(NumberHeaderManager.networkName.vietnamobile);
                break;
            }

        }

    }
    private class DatabaseExecuteTask extends AsyncTask<Void,Integer,Void>
    {
        long lastCallTime;
        long lastMessageTime;
        public  DatabaseExecuteTask(long calltime, long messagetime)
        {
            lastCallTime = calltime;
            lastMessageTime = messagetime;
        }
        @Override
        protected  Void doInBackground(Void...params)
        {

            if(_lastCallUpdate == 0) {
                _callLogTableAdapter.DeleteAllData();
                _statisticTableAdapter.ResetCallData();
                FirstInitCallLog();
                saveSharedPreferences();
            }
            else {
                RenewCallData(_lastCallUpdate);
                saveSharedPreferences();
            }
            if(_lastMessageUpdate == 0) {
                _messageLogTableAdapter.DeleteAllData();
                _statisticTableAdapter.ResetMessageData();
                FirstInitMessageLog();
                saveSharedPreferences();
            }
            else
            {
                RenewMessageData(_lastMessageUpdate);
                saveSharedPreferences();
            }
           // saveSharedPreferences();
            return null;
        }
        @Override
        protected void onProgressUpdate(Integer...values)
        {
            _progressBar.setProgress(values[0]);
        }
        @Override
        protected void onPostExecute(Void result) {
            _progressBar.setVisibility(View.GONE);
            saveSharedPreferences();
        }
    }
}


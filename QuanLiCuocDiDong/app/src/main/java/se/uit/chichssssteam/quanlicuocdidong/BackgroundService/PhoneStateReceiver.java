package se.uit.chichssssteam.quanlicuocdidong.BackgroundService;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.PixelFormat;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Date;

import se.uit.chichssssteam.quanlicuocdidong.DB.CallLog;
import se.uit.chichssssteam.quanlicuocdidong.Manager.DateTimeManager;
import se.uit.chichssssteam.quanlicuocdidong.NetworkPackage.BigKool;
import se.uit.chichssssteam.quanlicuocdidong.NetworkPackage.BigSave;
import se.uit.chichssssteam.quanlicuocdidong.NetworkPackage.BillionareFive;
import se.uit.chichssssteam.quanlicuocdidong.NetworkPackage.BillionareThree;
import se.uit.chichssssteam.quanlicuocdidong.NetworkPackage.BillionareTwo;
import se.uit.chichssssteam.quanlicuocdidong.NetworkPackage.Economy;
import se.uit.chichssssteam.quanlicuocdidong.NetworkPackage.HiSchool;
import se.uit.chichssssteam.quanlicuocdidong.NetworkPackage.MobiCard;
import se.uit.chichssssteam.quanlicuocdidong.NetworkPackage.MobiGold;
import se.uit.chichssssteam.quanlicuocdidong.NetworkPackage.MobiQ;
import se.uit.chichssssteam.quanlicuocdidong.NetworkPackage.NumberHeaderManager;
import se.uit.chichssssteam.quanlicuocdidong.NetworkPackage.PackageFee;
import se.uit.chichssssteam.quanlicuocdidong.NetworkPackage.QStudent;
import se.uit.chichssssteam.quanlicuocdidong.NetworkPackage.QTeen;
import se.uit.chichssssteam.quanlicuocdidong.NetworkPackage.Qkids;
import se.uit.chichssssteam.quanlicuocdidong.NetworkPackage.SV2014;
import se.uit.chichssssteam.quanlicuocdidong.NetworkPackage.SeaPlus;
import se.uit.chichssssteam.quanlicuocdidong.NetworkPackage.SevenColor;
import se.uit.chichssssteam.quanlicuocdidong.NetworkPackage.Student;
import se.uit.chichssssteam.quanlicuocdidong.NetworkPackage.Tomato;
import se.uit.chichssssteam.quanlicuocdidong.NetworkPackage.TomatoBL;
import se.uit.chichssssteam.quanlicuocdidong.NetworkPackage.VMOne;
import se.uit.chichssssteam.quanlicuocdidong.NetworkPackage.VMax;
import se.uit.chichssssteam.quanlicuocdidong.NetworkPackage.VinaCard;
import se.uit.chichssssteam.quanlicuocdidong.NetworkPackage.VinaXtra;
import se.uit.chichssssteam.quanlicuocdidong.R;

//<item name="android:windowBackground">@color/myWindowBackground</item>
/**
 * Created by justinvan on 10-Oct-15.
 */
public class PhoneStateReceiver extends BroadcastReceiver {

    private static final String TAG = "PhoneStateReceiver";
    Context _context;
    String _incomingNumber;
    private int _prev_state;
    private boolean _isOutGoingCallEnd;
    //private boolean _isReceived;
    //private PhoneLogManager _phoneCallLog;
    //DAO_CallLog _callAdapter;
    //DAO_Statistic _statisticTableAdapter;
    private PackageFee _myPackageFee;

    public PhoneStateReceiver() {
        //_isReceived = false;
        _incomingNumber = "";
        _prev_state = -1;
        _isOutGoingCallEnd = false;

    }

    /*public boolean get_CallState() {
        return _isOutGoingCallEnd;
    }*/

    public void InitPackage() {
        SharedPreferences setting = _context.getSharedPreferences("MySetting", Context.MODE_PRIVATE);
        String _package = setting.getString("GoiCuoc", "Unknown");
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


    @Override
    public void onReceive(Context context, Intent intent) {
        if (!intent.getAction().equals("android.intent.action.PHONE_STATE"))
            return;
        _context = context;
        //_callAdapter = new DAO_CallLog(context);
        //_statisticTableAdapter = new DAO_Statistic(context);
        //_callAdapter.Open();
       // _statisticTableAdapter.Open();
        //_phoneCallLog = new PhoneLogManager(_context, _myPackageFee);
        this.InitPackage();
        _isOutGoingCallEnd = false;
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        CustomPhoneStateListener customPhoneStateListener = new CustomPhoneStateListener(_context,_myPackageFee);
        telephonyManager.listen(customPhoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);


    }

    public class CustomPhoneStateListener extends PhoneStateListener {


        private static final String TAG = "CustomPhoneStateListener";

        Context _context;
        PackageFee _package;
        //Intent _listenerIntent;
        boolean _isReceivingCall;
        public CustomPhoneStateListener(Context context, PackageFee packageFee)
        {
            super();
            this._context = context;
            this._package = packageFee;

            _isReceivingCall = false;
        }
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            if (incomingNumber != null && incomingNumber.length() > 0) {
                _incomingNumber = incomingNumber;
            }
           final WindowManager wm = (WindowManager) _context.getSystemService(Context.WINDOW_SERVICE);
            final LinearLayout ly;

            switch (state){
                case TelephonyManager.CALL_STATE_IDLE: {
                    CallLog lastCall = getNewCallLog();
                    if(lastCall.get_callDuration() >0) {
                        //I check my value here and it work properly
                        Toast.makeText(_context.getApplicationContext(),Integer.toString(lastCall.get_callDuration()) + "-" +Integer.toString(lastCall.get_callFee()),Toast.LENGTH_LONG).show();
                        WindowManager.LayoutParams params = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT,
                                WindowManager.LayoutParams.WRAP_CONTENT,
                                WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
                                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL|WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                                PixelFormat.TRANSPARENT);

                        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
                        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
                        params.format = PixelFormat.TRANSPARENT;
                        params.gravity = Gravity.TOP;


                        final LayoutInflater inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        ly = (LinearLayout) inflater.inflate(R.layout.custom_dialog, null);
                        TextView txtDuration =(TextView) ly.findViewById(R.id.txt_duration);

                        TextView txtCost = (TextView) ly.findViewById(R.id.txt_cost);
                        Button yesBtn = (Button) ly.findViewById(R.id.btn_yes);
                        //Set value for textView here
                        txtDuration.setText(Integer.toString(lastCall.get_callDuration()));
                        txtDuration.setText(Integer.toString(lastCall.get_callDuration()));

                        txtCost.setText(Integer.toString(lastCall.get_callFee()));
                        yesBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                wm.removeView(ly);
                            }
                        });
                        wm.addView(ly, params);
                    }
                    break;
                }
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    break;
                case TelephonyManager.CALL_STATE_RINGING:

                    break;
            }

        }
        public CallLog getNewCallLog()
        {
            CallLog _newCall = new CallLog();
            Cursor cursor = this._context.getContentResolver().query(android.provider.CallLog.Calls.CONTENT_URI, null, null,
                    null, android.provider.CallLog.Calls.DATE + " DESC");
            int number = cursor.getColumnIndex(android.provider.CallLog.Calls.NUMBER);
            int callType = cursor.getColumnIndex(android.provider.CallLog.Calls.TYPE);
            int date = cursor.getColumnIndex(android.provider.CallLog.Calls.DATE);
            int duration = cursor.getColumnIndex(android.provider.CallLog.Calls.DURATION);

            if(cursor.moveToFirst() )
            {
                String _callType = cursor.getString(callType);
                int dircode = Integer.parseInt(_callType);
                if(dircode == android.provider.CallLog.Calls.OUTGOING_TYPE) {
                    String _number = cursor.getString(number);
                    String _callDate = cursor.getString(date);
                    Date _callDayTime = new Date(Long.valueOf(_callDate));
                    int _callDuration = cursor.getInt(duration);
                    _package.set_callDuration(_callDuration);
                    _package.set_callTime(DateTimeManager.get_instance().convertToHm(_callDayTime.toString()));
                    _package.set_outGoingPhoneNumber(_number);
                    int fee = _package.CalculateCallFee();
                    _newCall.set_callDuration(_callDuration);
                    _newCall.set_callFee(fee);
                    return _newCall;
                }
            }
            return null;
        }

    }
}


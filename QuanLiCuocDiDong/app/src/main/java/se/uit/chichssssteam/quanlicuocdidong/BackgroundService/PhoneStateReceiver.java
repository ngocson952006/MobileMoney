package se.uit.chichssssteam.quanlicuocdidong.BackgroundService;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.PhoneStateListener;

import android.telephony.TelephonyManager;
import android.widget.Toast;

import se.uit.chichssssteam.quanlicuocdidong.DB.CallLog;
import se.uit.chichssssteam.quanlicuocdidong.DB.DAO_CallLog;
import se.uit.chichssssteam.quanlicuocdidong.Manager.PhoneLogManager;
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
import se.uit.chichssssteam.quanlicuocdidong.Activity.MainActivity;
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


/**
 * Created by justinvan on 10-Oct-15.
 */
public class PhoneStateReceiver extends BroadcastReceiver
{

    private static final String TAG = "PhoneStateReceiver";
    Context _context;
    String _incomingNumber;
    private int _prev_state;
    private boolean _isOutGoingCallEnd;
    private  boolean _isReceived;
    private PhoneLogManager _phoneCallLog;
    DAO_CallLog _callAdapter;
    private PackageFee _myPackageFee;

    public PhoneStateReceiver()
    {
        _isReceived=false;
        _incomingNumber = "";
        _prev_state = -1;
        _isOutGoingCallEnd = false;

    }
    public  boolean get_CallState()
    {
        return _isOutGoingCallEnd;
    }

    public void InitPackage()
    {
        SharedPreferences setting = _context.getSharedPreferences("MySetting", Context.MODE_PRIVATE);
        String _package = setting.getString("GoiCuoc", "Unknown");
        switch (_package)
        {
            case "Mobicard":
            {
                _myPackageFee = new MobiCard();
                _myPackageFee.set_myNetwork(NumberHeaderManager.networkName.mobifone);
                break;
            }
            case "MobiGold":
            {
                _myPackageFee = new MobiGold();
                _myPackageFee.set_myNetwork(NumberHeaderManager.networkName.mobifone);
                break;
            }
            case "MobiQ":
            {
                _myPackageFee = new MobiQ();
                _myPackageFee.set_myNetwork(NumberHeaderManager.networkName.mobifone);
                break;
            }
            case "Q-Student":
            {
                _myPackageFee = new QStudent();
                _myPackageFee.set_myNetwork(NumberHeaderManager.networkName.mobifone);
                break;
            }
            case "Q-Teen":
            {
                _myPackageFee = new QTeen();
                _myPackageFee.set_myNetwork(NumberHeaderManager.networkName.mobifone);
                break;
            }
            case "Q-Kids":
            {
                _myPackageFee = new Qkids();
                _myPackageFee.set_myNetwork(NumberHeaderManager.networkName.mobifone);
                break;
            }
            case "VinaCard":
            {
                _myPackageFee = new VinaCard();
                _myPackageFee.set_myNetwork(NumberHeaderManager.networkName.vinaphone);
                break;
            }
            case "VinaXtra":
            {
                _myPackageFee = new VinaXtra();
                _myPackageFee.set_myNetwork(NumberHeaderManager.networkName.vinaphone);
                break;
            }
            case "Economy":
            {
                _myPackageFee = new Economy();
                _myPackageFee.set_myNetwork(NumberHeaderManager.networkName.viettel);
                break;
            }
            case "Tomato":
            {
                _myPackageFee = new Tomato();
                _myPackageFee.set_myNetwork(NumberHeaderManager.networkName.viettel);
                break;
            }
            case "Student":
            {
                _myPackageFee = new Student();
                _myPackageFee.set_myNetwork(NumberHeaderManager.networkName.viettel);
                break;
            }
            case "Sea+":
            {
                _myPackageFee = new SeaPlus();
                _myPackageFee.set_myNetwork(NumberHeaderManager.networkName.viettel);
                break;
            }
            case "Hi School":
            {
                _myPackageFee = new HiSchool();
                _myPackageFee.set_myNetwork(NumberHeaderManager.networkName.viettel);
                break;
            }
            case "7Colors":
            {
                _myPackageFee = new SevenColor();
                _myPackageFee.set_myNetwork(NumberHeaderManager.networkName.viettel);
                break;
            }
            case "Buôn làng":
            {
                _myPackageFee = new TomatoBL();
                _myPackageFee.set_myNetwork(NumberHeaderManager.networkName.viettel);
                break;
            }
            case "Big Save":
            {
                _myPackageFee = new BigSave();
                _myPackageFee.set_myNetwork(NumberHeaderManager.networkName.Gmobile);
                break;
            }
            case "Big & Kool":
            {
                _myPackageFee = new BigKool();
                _myPackageFee.set_myNetwork(NumberHeaderManager.networkName.Gmobile);
                break;
            }
            case "Tỉ phú 2":
            {
                _myPackageFee = new BillionareTwo();
                _myPackageFee.set_myNetwork(NumberHeaderManager.networkName.Gmobile);
                break;
            }
            case "Tỉ phú 3":
            {
                _myPackageFee = new BillionareThree();
                _myPackageFee.set_myNetwork(NumberHeaderManager.networkName.Gmobile);
                break;
            }
            case "Tỉ phú 5":
            {
                _myPackageFee = new BillionareFive();
                _myPackageFee.set_myNetwork(NumberHeaderManager.networkName.Gmobile);
                break;
            }
            case "VM One":
            {
                _myPackageFee = new VMOne();
                _myPackageFee.set_myNetwork(NumberHeaderManager.networkName.vietnamobile);
                break;
            }
            case "VMax":
            {
                _myPackageFee = new VMax();
                _myPackageFee.set_myNetwork(NumberHeaderManager.networkName.vietnamobile);
                break;
            }
            case "SV 2014":
            {
                _myPackageFee = new SV2014();
                _myPackageFee.set_myNetwork(NumberHeaderManager.networkName.vietnamobile);
                break;
            }

        }

    }


    @Override
    public void onReceive(Context context, Intent intent)
    {
        _context = context;
        _callAdapter = new DAO_CallLog(context);
        _phoneCallLog = new PhoneLogManager(_context,_myPackageFee);
        this.InitPackage();
        TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        CustomPhoneStateListener customPhoneStateListener = new CustomPhoneStateListener();
        telephonyManager.listen(customPhoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);
        Bundle bundle = intent.getExtras();
        String phoneNumber = bundle.getString("incoming_number");



    }

    public class CustomPhoneStateListener extends PhoneStateListener
    {


        private static final String TAG = "CustomPhoneStateListener";

        @Override
        public void onCallStateChanged(int state, String incomingNumber)
        {
            if(_isReceived == true)
                return;
            if(incomingNumber != null && incomingNumber.length()>0)
            {
                _incomingNumber = incomingNumber;
            }
            switch(state)
            {
                case TelephonyManager.CALL_STATE_RINGING:
                {
                    _prev_state = state;
                    break;
                }
                case TelephonyManager.CALL_STATE_OFFHOOK:
                {
                    _prev_state = state;
                    break;
                }
                case TelephonyManager.CALL_STATE_IDLE:
                {
                    if(_prev_state == TelephonyManager.CALL_STATE_OFFHOOK)
                    {
                        _prev_state = state;
                        _isOutGoingCallEnd = true;
                    }
                    if (_prev_state == TelephonyManager.CALL_STATE_RINGING)
                    {
                        _prev_state = state;
                    }
                    break;
                }
            }
            if(_isOutGoingCallEnd == true)
            {
                //Toast.makeText(_context, "Cuoc goi di cua tao da xong!!!!!!", Toast.LENGTH_SHORT).show();
                CallLog lastCall = _phoneCallLog.GetLastestOutGoingCall();
                _myPackageFee.set_outGoingPhoneNumber(lastCall.get_callNumber());
                _myPackageFee.set_callTime(lastCall.get_callDate());
                _myPackageFee.set_callDuration(lastCall.get_callDuration());
                _myPackageFee.get_type();
                _callAdapter.Open();
                _callAdapter.CreateCallLogRow(lastCall.get_callDate(),lastCall.get_callNumber(),lastCall.get_callDuration(),_myPackageFee.CalculateCallFee(),_myPackageFee.get_type());
                _isOutGoingCallEnd = false;
            }

        }

    }
}

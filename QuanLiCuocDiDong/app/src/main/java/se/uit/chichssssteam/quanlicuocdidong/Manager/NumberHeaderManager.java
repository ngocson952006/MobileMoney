package se.uit.chichssssteam.quanlicuocdidong.Manager;


import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
/**
 * Created by justinvan on 03-Oct-15.
 */
public class NumberHeaderManager
{
    public enum networkName
    {
        unknown,
        mobifone,
        vinaphone,
        viettel,
        vietnamobile,
        Gmobile,
    }

    private List<String> _mobifone;
    private List<String> _vinaphone;
    private List<String> _viettel;
    private List<String> _vietnamobile;
    private List<String> _Gmobile;
    private List<String> _emergencyNumber;
    public NumberHeaderManager()
    {
        this._mobifone = Arrays.asList("090","093","0120","0121","0122","0126","0128");
        this._vinaphone = Arrays.asList("091","094","0123","0124","0125","0127","0129");
        this._viettel = Arrays.asList("096","097","098","0163","0164","0165","0166","0167","0168","0169");
        this._vietnamobile = Arrays.asList("092","0188", "0186");
        this._Gmobile = Arrays.asList("0993","0994","0995","0996","0199");
        this._emergencyNumber = Arrays.asList("113","114","115", "119");
    }

    public boolean isEmergencyCall(String phoneNumber)
    {
        for(Iterator<String> i = this._emergencyNumber.iterator(); i.hasNext();)
        {
            if(getHeadNumber(phoneNumber).equals(i.next()))
                return true;
        }
        return false;
    }

    public boolean isOldNumber(String phoneNumber)
    {
        if(phoneNumber.length() == 10)
            return true;
        return false;
    }
    public String getHeadNumber(String phoneNumber)
    {

        if(this.isOldNumber(phoneNumber))
            return phoneNumber.substring(0,3);
        else
            return phoneNumber.substring(0,4);

    }
    public boolean isMobifone(String phoneNumber)
    {
        for(Iterator<String> i = this._mobifone.iterator(); i.hasNext();)
        {
            if(getHeadNumber(phoneNumber).equals(i.next()))
                return true;
        }
        return false;
    }
    public boolean isVinaphone(String phoneNumber)
    {
        for(Iterator<String> i = this._vinaphone.iterator(); i.hasNext();)
        {
            if(getHeadNumber(phoneNumber).equals(i.next()))
                return true;
        }
        return false;

    }
    public boolean isViettel(String phoneNumber)
    {
        for(Iterator<String> i = this._viettel.iterator(); i.hasNext();)
        {
            if(getHeadNumber(phoneNumber).equals(i.next()))
                return true;
        }
        return false;
    }
    public boolean isVietnamobile(String phoneNumber)
    {
        for(Iterator<String> i = this._vietnamobile.iterator(); i.hasNext();)
        {
            if(getHeadNumber(phoneNumber).equals(i.next()))
                return true;
        }
        return false;
    }
    public boolean isGmobile(String phoneNumber)
    {
        for(Iterator<String> i = this._Gmobile.iterator(); i.hasNext();)
        {
            if(getHeadNumber(phoneNumber).equals(i.next()))
                return true;
        }
        return false;
    }

    public networkName VerifyNetwork(String phoneNumber)
    {
        if(this.isMobifone(phoneNumber))
            return networkName.mobifone;
        else if (this.isViettel(phoneNumber))
            return networkName.viettel;
        else if (this.isVinaphone(phoneNumber))
            return networkName.vinaphone;
        else if (this.isVietnamobile(phoneNumber))
            return networkName.vietnamobile;
        else if (this.isGmobile(phoneNumber))
            return networkName.Gmobile;
        else
            return networkName.unknown;

    }
    public boolean isInternalNetwork(String ownerNumber, String callNumber)
    {
        if(this.VerifyNetwork(ownerNumber).equals(this.VerifyNetwork(callNumber)))
            return true;
        return false;

    }
}

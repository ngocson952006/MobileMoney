package se.uit.chichssssteam.quanlicuocdidong.Manager;

import java.io.Serializable;

/**
 * Created by QNghi on 28/9/2015.
 */
public class PackageNetwork implements Serializable {
    private String namePackage;
    private int idResourceImage;
    private String nameNetwork;
    public PackageNetwork(String _nameNetwork, String _namePackage, int _idImage)
    {
        nameNetwork = _nameNetwork;
        namePackage = _namePackage;
        idResourceImage = _idImage;
    }
    public String getNameNetwork(){
        return nameNetwork;
    }
    public String getPackageName()
    {
        return namePackage;
    }
    public int getIdResourceImage()
    {
        return idResourceImage;
    }

}

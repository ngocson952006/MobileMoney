package se.uit.chichssssteam.quanlicuocdidong;

/**
 * Created by QNghi on 28/9/2015.
 */
public class PackageNetwork {
    private String namePackage;
    private int idResourceImage;
    public PackageNetwork(String _namePackage, int _idImage)
    {
        namePackage = _namePackage;
        idResourceImage = _idImage;
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

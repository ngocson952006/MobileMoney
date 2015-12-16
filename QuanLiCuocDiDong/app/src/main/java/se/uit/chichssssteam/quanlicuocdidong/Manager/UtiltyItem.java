package se.uit.chichssssteam.quanlicuocdidong.Manager;

/**
 * Created by QNghi on 25/11/2015.
 */
public class UtiltyItem {
    private String _title;
    private String _des;
    private int _imageID;
    public UtiltyItem(String title, String des, int id)
    {
        this._title = title;
        this._des = des;
        this._imageID = id;
    }
    public int get_imageID()
    {
        return _imageID;
    }
    public String get_title()
    {
        return _title;
    }
    public String get_des()
    {
        return _des;
    }
}

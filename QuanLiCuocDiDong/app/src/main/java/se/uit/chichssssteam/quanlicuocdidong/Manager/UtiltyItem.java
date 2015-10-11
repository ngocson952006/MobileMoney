package se.uit.chichssssteam.quanlicuocdidong.Manager;

/**
 * Created by QNghi on 11/10/2015.
 */
public class UtiltyItem {
    private String title;
    private String des;
    private int idImage;
    public UtiltyItem(String _title, String _des, int _idImage)
    {
        title = _title;
        des = _des;
        idImage = _idImage;
    }
    public String getTitle()
    {
        return title;
    }
    public String getDes()
    {
        return des;
    }
    public int getIdImage()
    {
        return idImage;
    }
}

package se.uit.chichssssteam.quanlicuocdidong.Manager;

/**
 * Created by QNghi on 10/10/2015.
 */
public class PromotionItem {
    String title;
    String url;
    public PromotionItem(String _title, String _url)
    {
        title = _title;
        url = _url;
    }
    public PromotionItem()
    {

    }
    public void setTitle(String _title)
    {
        title = _title;
    }
    public void setUrl(String _url)
    {
        url = _url;
    }
    public String getTitle()
    {
        return title;
    }
    public String getUrl()
    {
        return url;
    }

    @Override
    public String toString() {
        return title;
    }
}

package se.uit.battleteam.quanlicuocdidong.Manager;

import android.net.Uri;

/**
 * Created by QNghi on 4/10/2015.
 */
public interface OnFragmentInteractionListener {
    // TODO: Update argument type and name
    public void onThongKeFragmentInteraction(Uri uri);
    public void onThangFragmentInteraction(Uri uri);
    public void onNgayFragmentInteraction(Uri uri);
    public void onCaiDatFragmentInteraction();
    public void onGioiThieuFragmentInteraction(Uri uri);
    public void onTienIchFragmentInteraction(Uri uri);
}
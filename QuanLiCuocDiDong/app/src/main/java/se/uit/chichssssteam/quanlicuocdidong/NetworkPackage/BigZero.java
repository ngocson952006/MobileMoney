package se.uit.chichssssteam.quanlicuocdidong.NetworkPackage;

import se.uit.chichssssteam.quanlicuocdidong.NetworkPackage.PackageFee;

/**
 * Created by justinvan on 03-Oct-15.
 */
public class BigZero extends PackageFee
{
    public BigZero()
    {
        this._internalCallFee = 0;
        this._outerCallFee = 1350;
        this._internalMessageFee = 250;
        this._outerMessageFee = 350;
    }
}

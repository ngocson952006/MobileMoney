package se.uit.chichssssteam.quanlicuocdidong;

/**
 * Created by justinvan on 03-Oct-15.
 */
public class BigKool extends  PackageFee
{
    private int _initCallFee;
    public BigKool()
    {
        this._initCallFee = 200;
        this._internalCallFee = 900;
        this._outerCallFee = 900;
        this._internalMessageFee = 250;
        this._outerMessageFee = 350;
    }
    @Override
    public int CalculateCallFee()
    {
        int result = 0;
        result = this._initCallFee + super.CalculateCallFee();
        return result;
    }
}

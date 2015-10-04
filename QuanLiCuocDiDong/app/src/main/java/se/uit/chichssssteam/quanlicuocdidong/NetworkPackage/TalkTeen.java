package se.uit.chichssssteam.quanlicuocdidong.NetworkPackage;

import se.uit.chichssssteam.quanlicuocdidong.NetworkPackage.PackageFee;

/**
 * Created by justinvan on 03-Oct-15.
 */
public class TalkTeen extends PackageFee
{
    private int _innerExceptionCallFee;
    private int _outerExceptionCallFee;
    public TalkTeen()
    {
        this._internalCallFee = 1280;
        this._outerCallFee = 1480;
        this._internalMessageFee = 99;
        this._outerMessageFee = 250;
        this._innerExceptionCallFee= 640;
        this._outerExceptionCallFee = 740;
    }
    public int CalculateCallFee()
    {
        if(this._numberHeader.isInternalNetwork(this._ownNumber, this._outGoingPhoneNumber))
        {
            if(this.isSpecialTime())
            {
                if(this._callDuration <= this._callBlock)
                    this._callFee = this._innerExceptionCallFee/10;
                else
                {
                    int remainDuration  = this._callDuration - this._callBlock;
                    this._callFee = this._innerExceptionCallFee/10 +  remainDuration*Math.round((float)(this._innerExceptionCallFee/60));

                }
                return this._callFee;
            }

        }
        else
        {
            if(this.isSpecialTime())
            {
                if(this._callDuration <= this._callBlock)
                    this._callFee = this._outerExceptionCallFee/10;
                else
                {
                    int remainDuration  = this._callDuration - this._callBlock;
                    this._callFee = this._outerExceptionCallFee/10 +  remainDuration*Math.round((float)(this._outerExceptionCallFee/60));

                }
                return this._callFee;
            }

        }

        return super.CalculateCallFee();
    }
}

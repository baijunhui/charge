package com.bjh.charge;

import android.app.Application;

import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * Created by baijunhui on 17-3-7.
 */

public class ChargeApp extends Application {

    private static ChargeApp chargeApp;

    @Override
    public void onCreate() {
        super.onCreate();
        init();
        chargeApp = this;
    }

    public static ChargeApp instance() {
        return chargeApp;
    }

    private IWXAPI iwxapi;

    private void init() {
        iwxapi = WXAPIFactory.createWXAPI(this, "", false);
        iwxapi.registerApp("");
    }

    public IWXAPI getIwxapi() {
        return iwxapi;
    }


}

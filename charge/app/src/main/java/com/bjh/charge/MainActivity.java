package com.bjh.charge;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.alipay.sdk.app.PayTask;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.et_we_chat_input_money)
    EditText etWeChatInputMoney;
    @BindView(R.id.et_alibaba_input_money)
    EditText etAlibabaInputMoney;
    IWXAPI iwxapi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        iwxapi = ChargeApp.instance().getIwxapi();
    }

    @OnClick({R.id.btn_wechat_charge, R.id.btn_alibaba_charge})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_wechat_charge:
                String json = "{\n" +
                        "    \"package\": \"Sign=WXPay\",\n" +
                        "    \"appid\": \"wx2702248f6c46661c\",\n" +
                        "    \"sign\": \"AC1D515D1EFA9296A348D237E48085F0\",\n" +
                        "    \"partnerid\": \"1290815901\",\n" +
                        "    \"prepayid\": \"wx20170307165751ef6c92123a0721481931\",\n" +
                        "    \"noncestr\": \"DXL8D3W2G6AACYNX88PKYBLUELNG2RX8\",\n" +
                        "    \"timestamp\": \"1488877071\"\n" +
                        "}";
                wechatCharge(json);
                break;
            case R.id.btn_alibaba_charge:
                alibabaCharge();
                break;
        }
    }

    private void alibabaCharge() {
        //一定要异步操作
        new Thread(new Runnable() {
            @Override
            public void run() {
                PayTask task = new PayTask(MainActivity.this);
                Map<String, String> result = task.payV2("service=\"mobile.securitypay.pay\"&partner=\"2088001403805263\"&_input_charset=\"utf-8\"&notify_url=\"https%3A%2F%2Fpayplf-gate.yy.com%2Fch%2Fnotify%2Fzfbwapapp.do\"&out_trade_no=\"130201703072632136471\"&subject=\"我的手机充值\"&payment_type=\"1\"&seller_id=\"zhoushu@chinaduo.com\"&total_fee=\"1.00\"&sign_type=\"RSA\"&sign=\"N%2BCENNg5kVUqa2a1AFkB77g%2Fuw9Fr9sf%2BLUFNFUoNkX%2BRQceUOp8E788aEsTGD%2FtJAK3jF5CT8BvfuOqJv7QGK1sjMvDld3ALvE2eqQmUxghBfNnwm2RuUftw8j7AN%2BxO1Sp%2FiaHq2ZyBXADsP488aqXckZxRuKfAu8K6D8tb4w%3D\"", true);

            }
        }).start();
    }

    private void wechatCharge(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            PayReq request = new PayReq();
            request.appId = jsonObject.optString("appid");
            request.partnerId = jsonObject.optString("partnerid");
            request.prepayId= jsonObject.optString("prepayid");
            request.packageValue = jsonObject.optString("package");
            request.nonceStr= jsonObject.optString("noncestr");
            request.timeStamp= jsonObject.optString("timestamp");
            request.sign= jsonObject.optString("sign");
            iwxapi.sendReq(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package com.example.androidtemplate.user;


import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.androidtemplate.Http.HttpUtil;
import com.example.androidtemplate.R;
import com.example.androidtemplate.common.BaseActivity;
import com.example.androidtemplate.common.T;
import com.example.androidtemplate.manager.ManagerApp;
import com.example.androidtemplate.manager.ManagerConf;
import com.example.androidtemplate.utils.Utils;

import org.apache.http.conn.util.InetAddressUtils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class IpActivity extends BaseActivity {

    @Bind(R.id.left_tv)
    TextView leftTv;
    @Bind(R.id.title_tv)
    TextView titleTv;
    @Bind(R.id.right_tv)
    TextView rightTv;
    @Bind(R.id.title_ll)
    LinearLayout titleLl;
    @Bind(R.id.ip_et)
    EditText ipEt;
    @Bind(R.id.register_btn)
    Button registerBtn;
    @Bind(R.id.content_ll)
    LinearLayout contentLl;
    @Bind(R.id.ip_tv)
    TextView ipTv;

    @Override
    protected void initData() {
        setContentView(R.layout.activity_ip);
        ButterKnife.bind(this);

        ipTv.setText(getString(R.string.localhost_ip_str)+getlocalip());

        if (!TextUtils.isEmpty(ManagerConf.readFromLocal("server_ip"))) {
            ipEt.setText(ManagerConf.readFromLocal("server_ip"));
        }
    }

    @Override
    protected void recycle() {

    }


    @OnClick({R.id.left_tv, R.id.register_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.left_tv:
                finish();
                break;
            case R.id.register_btn:
                String ip = ipEt.getText().toString();
                if (Utils.isIp(ip)) {
                    HttpUtil.ip = ip + ":8080";
                    HttpUtil.BASE_URL = "http://" + HttpUtil.ip + "/";
                    HttpUtil.BASE_URL_UPLOAD = HttpUtil.BASE_URL + "/upload/";
                    ManagerConf.saveToLocal("server_ip", ip);
                    finish();
                } else {
                    T.showToast(this_, getString(R.string.ip_error_str));
                }
                break;
        }
    }

    /**
     * 获取IP
     * @return
     */
    public static String getlocalip() {
        WifiManager wifiManager = (WifiManager) ManagerApp.getAppContext().getSystemService(Context.WIFI_SERVICE);
        if(wifiManager != null) {
            if(wifiManager.isWifiEnabled()) {
                WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                if(wifiInfo != null) {
                    int ipAddress = wifiInfo.getIpAddress();
                    if (ipAddress != 0) {
                        return ((ipAddress & 0xff) + "." + (ipAddress >> 8 & 0xff) + "."
                                + (ipAddress >> 16 & 0xff) + "." + (ipAddress >> 24 & 0xff));
                    }
                }
            } else {
                String ip = getIPAddress(true);
                if(ip == null) {
                    return "请打开连接WIFI";
                }

                return ip;
            }
        }

        // 实在不行使用老的方法获取
        return getIPAddress(true);
    }

    public static String getIPAddress(boolean useIPv4) {
        try {
            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface intf : interfaces) {
                List<InetAddress> addrs = Collections.list(intf.getInetAddresses());
                for (InetAddress addr : addrs) {
                    if (!addr.isLoopbackAddress()) {
                        String sAddr = addr.getHostAddress().toUpperCase(Locale.CHINA);
                        boolean isIPv4 = InetAddressUtils.isIPv4Address(sAddr);
                        if (useIPv4) {
                            if (isIPv4)
                                return sAddr;
                        } else {
                            if (!isIPv4) {
                                int delim = sAddr.indexOf('%'); // drop ip6 port suffix
                                return delim<0 ? sAddr : sAddr.substring(0, delim);
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } // for now eat exceptions
        return "";
    }

}

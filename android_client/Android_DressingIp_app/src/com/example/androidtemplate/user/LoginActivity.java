package com.example.androidtemplate.user;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.example.androidtemplate.Http.HttpUtil;
import com.example.androidtemplate.R;
import com.example.androidtemplate.common.BaseActivity;
import com.example.androidtemplate.common.D;
import com.example.androidtemplate.common.T;
import com.example.androidtemplate.manager.ManagerApp;
import com.example.androidtemplate.manager.ManagerComm;
import com.example.androidtemplate.manager.ManagerConf;
import com.example.androidtemplate.mo.User;
import com.example.androidtemplate.utils.GsonUtil;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
import com.example.androidtemplate.MainActivity;

import org.apache.http.Header;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @Bind(R.id.left_tv)
    TextView leftTv;
    @Bind(R.id.title_tv)
    TextView titleTv;
    @Bind(R.id.right_tv)
    TextView rightTv;
    @Bind(R.id.title_ll)
    LinearLayout titleLl;
    @Bind(R.id.username_et)
    EditText usernameEt;
    @Bind(R.id.password_et)
    EditText passwordEt;
    @Bind(R.id.auto_login_sw)
    Switch autoLoginSw;
    @Bind(R.id.checkBox)
    CheckBox checkBox;
    @Bind(R.id.login_btn)
    Button loginBtn;
    @Bind(R.id.forget_pass_tv)
    TextView forgetPassTv;
    @Bind(R.id.switch_fun_tv)
    TextView switchFunTv;
    @Bind(R.id.content_ll)
    LinearLayout contentLl;
    @Bind(R.id.activity_login)
    LinearLayout activityLogin;

    @Override
    protected void initData() {
        setContentView(R.layout.a_activity_login);
        ButterKnife.bind(this);
        if(ManagerComm.loginUser != null){
            usernameEt.setText(ManagerComm.loginUser.getUsername());
            passwordEt.setText(ManagerComm.loginUser.getPasswd());
            autoLoginSw.setChecked(true);
            checkBox.setChecked(true);
        }

		String ip = ManagerConf.readFromLocal("server_ip");
        if(!TextUtils.isEmpty(ip)){
            HttpUtil.ip = ip + ":8080";
            HttpUtil.BASE_URL = "http://" + HttpUtil.ip + "/";
            HttpUtil.BASE_URL_UPLOAD = HttpUtil.BASE_URL + "/upload/";
        }

    }

    @Override
    protected void recycle() {

    }

    @OnClick({R.id.left_tv, R.id.right_tv, R.id.login_btn, R.id.forget_pass_tv, R.id.switch_fun_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.left_tv:
                ManagerApp.exitApp();
                break;
            case R.id.right_tv:
                startActivity(new Intent(this_, IpActivity.class));
                break;
            case R.id.login_btn:
                String username = usernameEt.getText().toString().trim();
                String passwd = passwordEt.getText().toString().trim();

                RequestParams params = new RequestParams();
                params.put("action","login");
                params.put("username",username);
                params.put("passwd",passwd);
                HttpUtil.post("ClientServlet", params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        D.out(throwable);
                        T.showToast(this_,getString(R.string.service_ip_error_str));
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        D.out(s);
                        if(isFail(s)){
                            T.showToast(this_,getString(R.string.username_or_pass_error_str));
                        }else{
                            T.showToast(this_,getString(R.string.login_success_str));
                            ManagerComm.loginUser = GsonUtil.getInstance().fromJson(s, User.class);
                            if(autoLoginSw.isChecked() || checkBox.isChecked()){
                                ManagerConf.saveToLocal("login_user_kaoshi",s);
                            }
                            startActivity(new Intent(this_, MainActivity.class));
                        }

                    }
                });

                break;
            case R.id.forget_pass_tv:
                startActivity(new Intent(this_,FindPasswordActivity.class));
                break;
            case R.id.switch_fun_tv:
                startActivity(new Intent(this_,RegisterActivity.class));
                //leftInOut();
                break;
        }
    }
}

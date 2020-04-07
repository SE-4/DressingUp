package com.example.androidtemplate;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.androidtemplate.Http.HttpUtil;
import com.example.androidtemplate.businmo.Tuijian;
import com.example.androidtemplate.common.BaseActivity;
import com.example.androidtemplate.common.D;
import com.example.androidtemplate.common.T;
import com.example.androidtemplate.manager.ManagerComm;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.apache.http.Header;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TuijianDescActivity extends BaseActivity {

    @Bind(R.id.left_tv)
    TextView leftTv;
    @Bind(R.id.title_tv)
    TextView titleTv;
    @Bind(R.id.right_tv)
    TextView rightTv;
    @Bind(R.id.title_ll)
    LinearLayout titleLl;
    @Bind(R.id.name_tv)
    TextView nameTv;
    @Bind(R.id.content_ll)
    LinearLayout contentLl;

    Tuijian item;
    @Bind(R.id.img_iv)
    ImageView imgIv;
    @Bind(R.id.msg_tv)
    TextView msgTv;

    @Override
    protected void initData() {
        setContentView(R.layout.activity_tuijian_desc);
        ButterKnife.bind(this);

        item = (Tuijian) getIntent().getSerializableExtra("item");


        if (item != null) {
            // get the value
            nameTv.setText(item.getName());
            msgTv.setText(item.getMsg());

            ImageLoader.getInstance().displayImage(HttpUtil.BASE_URL_UPLOAD + item.getImg(), imgIv, ManagerComm.displayImageOptions);
            imgIv.setVisibility(View.VISIBLE);

        }
    }

    @Override
    protected void recycle() {

    }


    @OnClick({R.id.left_tv, R.id.right_tv,R.id.ok_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.left_tv:
                finish();
                break;
            case R.id.right_tv:
                break;
            case R.id.ok_btn:

                RequestParams params = new RequestParams();
                params.add("action", "add");

                params.put("name", item.getId()+"");
                params.put("username",ManagerComm.loginUser.getUsername());
                HttpUtil.post("DianzanServlet", params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        D.out(throwable);
                        T.showToast(this_, "You have already give a like");
                        
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String responseString) {

                        T.showToast(this_, "You have successfully given a like");




                        if(TextUtils.isEmpty(item.getDianzan())){
                            item.setDianzan("0");
                        }
                        Integer count = Integer.parseInt(item.getDianzan()) +1;

                        RequestParams params = new RequestParams();
                        params.add("action", "edit");
                        params.put("id", item.getId());
                        params.put("dianzan", count);
                        params.put("msg",item.getMsg()+ManagerComm.loginUser.getUsername()+",");

                        HttpUtil.post("TuijianServlet", params, new TextHttpResponseHandler() {
                            @Override
                            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                                D.out(throwable);
                            }

                            @Override
                            public void onSuccess(int statusCode, Header[] headers, String responseString) {


                            }
                        });


                    }
                });

                break;
        }
    }

}

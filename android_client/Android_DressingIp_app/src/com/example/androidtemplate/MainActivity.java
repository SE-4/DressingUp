package com.example.androidtemplate;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.androidtemplate.Http.HttpUtil;
import com.example.androidtemplate.Http.JsonData;
import com.example.androidtemplate.businmo.Tuijian;
import com.example.androidtemplate.common.BaseActivity;
import com.example.androidtemplate.common.CommonAdapter;
import com.example.androidtemplate.common.D;
import com.example.androidtemplate.common.T;
import com.example.androidtemplate.common.ViewHolder;
import com.example.androidtemplate.manager.ManagerApp;
import com.example.androidtemplate.manager.ManagerComm;
import com.example.androidtemplate.user.PersonalActivity;
import com.example.androidtemplate.utils.GsonUtil;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.apache.http.Header;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Bind(R.id.left_tv)
    TextView leftTv;
    @Bind(R.id.title_tv)
    TextView titleTv;
    @Bind(R.id.right_tv)
    TextView rightTv;
    @Bind(R.id.title_ll)
    LinearLayout titleLl;
    @Bind(R.id.content_ll)
    LinearLayout contentLl;
    @Bind(R.id.shade_lv)
    LinearLayout shadeLv;
    @Bind(R.id.back_tv)
    TextView backTv;
    @Bind(R.id.back_iv)
    ImageView backIv;
    @Bind(R.id.one_tv)
    TextView oneTv;
    @Bind(R.id.two_tv)
    TextView twoTv;
    @Bind(R.id.three_tv)
    TextView threeTv;
    @Bind(R.id.four_tv)
    TextView fourTv;
    @Bind(R.id.five_tv)
    TextView fiveTv;
    @Bind(R.id.menu_lv)
    LinearLayout menuLv;
    @Bind(R.id.menu_rl)
    RelativeLayout menuRl;
    @Bind(R.id.img_iv)
    ImageView imgIv;
    @Bind(R.id.username_tv)
    TextView usernameTv;
    @Bind(R.id.listview)
    GridView listview;


    CommonAdapter<Tuijian> adapter;
    List<Tuijian> crudList = new ArrayList<Tuijian>();
    List<Tuijian> crudListAll = new ArrayList<Tuijian>();
    @Bind(R.id.category_sp)
    Spinner categorySp;

    @Override
    protected void initData() {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        menuRl.setVisibility(View.GONE);
        usernameTv.setText(ManagerComm.loginUser.getUsername());

        adapter = new CommonAdapter<Tuijian>(this_, crudList, R.layout.item_tuijian) {
            @Override
            public void convert(ViewHolder helper, Tuijian item) {
                helper.setImageUrl(R.id.img_iv, item.getImg());
                helper.setText(R.id.name_tv, item.getName());
                helper.setText(R.id.msg_tv, item.getMsg());

                if(TextUtils.isEmpty(item.getDianzan())){
                    item.setDianzan("0");
                }
                helper.setText(R.id.dianzan_tv," "+item.getDianzan()+" times like ");
            }
        };

        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(this_, TuijianDescActivity.class).putExtra("item", crudList.get(position)));
            }
        });



        categorySp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                crudList.clear();
                for(Tuijian shetuan:crudListAll){
                    if(shetuan.getCategory().equals(categorySp.getSelectedItem().toString())){
                        crudList.add(shetuan);
                    }
                }


                Collections.sort(crudList, new Comparator<Tuijian>() {
                    @Override
                    public int compare(Tuijian lhs, Tuijian rhs) {
                        return rhs.getDianzan().compareTo(lhs.getDianzan());
                    }
                });
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    protected void recycle() {

    }

    @OnClick({R.id.left_tv, R.id.right_tv, R.id.back_tv, R.id.back_iv, R.id.one_tv, R.id.two_tv, R.id.three_tv, R.id.four_tv, R.id.five_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.left_tv:
                menuRl.setVisibility(View.VISIBLE);
                Animation animation = AnimationUtils.loadAnimation(this_, R.anim.setting_mune_scale_right);
                menuLv.startAnimation(animation);
                break;
            case R.id.right_tv:
                break;
            case R.id.back_tv:
            case R.id.back_iv:
                Animation animation2 = AnimationUtils.loadAnimation(this_, R.anim.setting_mune_scale_left);
                menuLv.startAnimation(animation2);
                animation2.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        menuRl.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });


                break;
            case R.id.one_tv:

                Animation animation3 = AnimationUtils.loadAnimation(this_, R.anim.setting_mune_scale_left);
                menuLv.startAnimation(animation3);
                animation3.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        menuRl.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

                break;
            case R.id.two_tv:
                startActivity(new Intent(this_, TuijianActivity.class));
                break;
            case R.id.three_tv:
                startActivity(new Intent(this_, TuijianActivity.class).putExtra("type",1));
                break;
            case R.id.four_tv:
                startActivity(new Intent(this_, PersonalActivity.class));
                break;
            case R.id.five_tv:
                break;
        }
    }


    @Override
    protected void onResume() {
        if (!TextUtils.isEmpty(ManagerComm.loginUser.getImg())) {
            D.out("img:" + ManagerComm.loginUser.getImg());
            ImageLoader.getInstance().displayImage(HttpUtil.BASE_URL_UPLOAD + ManagerComm.loginUser.getImg(), imgIv, ManagerComm.displayImageOptions);
        }

        RequestParams params = new RequestParams();
        HttpUtil.post("TuijianServlet", params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(final int statusCode, Header[] headers, String responseString) {
                List<Tuijian> dates = GsonUtil.getInstance().fromJson(new JsonData(responseString).getData(), new TypeToken<List<Tuijian>>() {
                }.getType());

                crudList.clear();
                crudListAll.clear();
                if (dates == null) {
                    dates = new ArrayList<Tuijian>();
                }
                crudList.addAll(dates);
                crudListAll.addAll(dates);
               // adapter.notifyDataSetChanged();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        categorySp.setSelection(1,true);
                        categorySp.setSelection(0,true);
                    }
                },200);

            }
        });

        super.onResume();
    }

    private long exitTime = 0;

    @Override
    public void onBackPressed() {

        if ((System.currentTimeMillis() - exitTime) > 2000) {
            T.showToast(this_, "press one more time to quit");
            exitTime = System.currentTimeMillis();
        } else {

            super.onBackPressed();
            ManagerApp.exitApp();
        }

    }

}

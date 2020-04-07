package com.example.androidtemplate;


import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.androidtemplate.Http.HttpUtil;
import com.example.androidtemplate.Http.JsonData;
import com.example.androidtemplate.businmo.Tuijian;
import com.example.androidtemplate.common.BaseActivity;
import com.example.androidtemplate.common.CommonAdapter;
import com.example.androidtemplate.common.CustomDialog;
import com.example.androidtemplate.common.T;
import com.example.androidtemplate.common.ViewHolder;
import com.example.androidtemplate.manager.ManagerComm;
import com.example.androidtemplate.utils.GsonUtil;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TuijianActivity extends BaseActivity {

    @Bind(R.id.left_tv)
    TextView leftTv;
    @Bind(R.id.title_tv)
    TextView titleTv;
    @Bind(R.id.right_tv)
    TextView rightTv;
    @Bind(R.id.title_ll)
    LinearLayout titleLl;
    @Bind(R.id.listview)
    GridView listview;
    @Bind(R.id.content_ll)
    LinearLayout contentLl;

    CommonAdapter<Tuijian> adapter;
    List<Tuijian> crudList = new ArrayList<Tuijian>();

    int type = 0;
    @Override
    protected void initData() {
        setContentView(R.layout.activity_tuijian);
        ButterKnife.bind(this);
        type = getIntent().getIntExtra("type",0);

        if(type ==1){
            titleTv.setText("My thumb up");
            rightTv.setVisibility(View.INVISIBLE);
        }

        adapter = new CommonAdapter<Tuijian>(this_,crudList,R.layout.item_tuijian) {
            @Override
            public void convert(ViewHolder helper, Tuijian item) {
				helper.setImageUrl(R.id.img_iv,item.getImg());
                helper.setText(R.id.name_tv,item.getName());
                helper.setText(R.id.msg_tv,item.getMsg());
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
                startActivity(new Intent(this_,TuijianDescActivity.class).putExtra("item",crudList.get(position)));
            }
        });

        if(type ==0){
            listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                    final CustomDialog customDialog = CustomDialog.getInstance(this_);
                    customDialog.show();
                    customDialog.setHintMsg("delete or not？");
                    customDialog.setOkBtnOnClick(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            RequestParams params = new RequestParams();
                            params.put("action","delete");
                            params.put("id",crudList.get(position).getId());
                            HttpUtil.post("TuijianServlet", params, new TextHttpResponseHandler() {
                                @Override
                                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

                                }

                                @Override
                                public void onSuccess(int statusCode, Header[] headers, String responseString) {
                                    T.showToast(this_,"delete successfully");
                                    customDialog.dismiss();
                                    onResume();
                                }
                            });
                        }
                    });
                    return true;
                }
            });
        }


    }

    @Override
        protected void onResume() {

        RequestParams params = new RequestParams();
        if(type == 0){
            params.put("searchStr", ManagerComm.loginUser.getUsername());
        }

        HttpUtil.post("TuijianServlet", params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(final int statusCode, Header[] headers, String responseString) {
                List<Tuijian> dates = GsonUtil.getInstance().fromJson(new JsonData(responseString).getData(), new TypeToken<List<Tuijian>>() {
                }.getType());

                crudList.clear();
                if (dates == null) {
                    dates = new ArrayList<Tuijian>();
                }


                if(type == 1){

                    for(Tuijian tuijian:dates){
                        if(!TextUtils.isEmpty(tuijian.getMsg())){
                            String str[] = tuijian.getMsg().split(",");
                            if(Arrays.asList(str).contains(ManagerComm.loginUser.getUsername())){
                                crudList.add(tuijian);
                            }
                        }
                    }

                }else{
                    crudList.addAll(dates);
                }





                adapter.notifyDataSetChanged();

            }
        });

        super.onResume();
    }

    @Override
    protected void recycle() {

    }


    @OnClick({R.id.left_tv, R.id.right_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.left_tv:
                finish();
                break;
            case R.id.right_tv:
                startActivity(new Intent(this_,TuijianAddActivity.class));
                break;
        }
    }
}

package com.example.androidtemplate;


import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.text.TextUtils;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.SearchView;

import com.example.androidtemplate.Http.HttpUtil;
import com.example.androidtemplate.Http.JsonData;
import com.example.androidtemplate.businmo.Dianzan;
import com.example.androidtemplate.common.BaseActivity;
import com.example.androidtemplate.common.CommonAdapter;
import com.example.androidtemplate.common.CustomDialog;
import com.example.androidtemplate.common.T;
import com.example.androidtemplate.common.ViewHolder;
import com.example.androidtemplate.utils.GsonUtil;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DianzanActivity extends BaseActivity {

    @Bind(R.id.left_tv)
    TextView leftTv;
    @Bind(R.id.title_tv)
    TextView titleTv;
    @Bind(R.id.right_tv)
    TextView rightTv;
    @Bind(R.id.title_ll)
    LinearLayout titleLl;
    @Bind(R.id.listview)
    ListView listview;
    @Bind(R.id.content_ll)
    LinearLayout contentLl;
	@Bind(R.id.search_sv)
    SearchView searchSv;

    CommonAdapter<Dianzan> adapter;
    List<Dianzan> crudList = new ArrayList<Dianzan>();
    List<Dianzan> crudListAll = new ArrayList<Dianzan>();

    @Override
    protected void initData() {
        setContentView(R.layout.activity_dianzan);
        ButterKnife.bind(this);

        adapter = new CommonAdapter<Dianzan>(this_,crudList,R.layout.item_dianzan) {
            @Override
            public void convert(ViewHolder helper, Dianzan item) {
                helper.setText(R.id.name_tv,item.getName());
                helper.setText(R.id.msg_tv,item.getMsg());
            }
        };

        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(this_,DianzanDescActivity.class).putExtra("item",crudList.get(position)));
            }
        });

        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                final CustomDialog customDialog = CustomDialog.getInstance(this_);
                customDialog.show();
                customDialog.setHintMsg("delete or not?");
                customDialog.setOkBtnOnClick(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RequestParams params = new RequestParams();
                        params.put("action","delete");
                        params.put("id",crudList.get(position).getId());
                        HttpUtil.post("DianzanServlet", params, new TextHttpResponseHandler() {
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

	   searchSv.onActionViewExpanded();
       searchSv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
           @Override
           public boolean onQueryTextSubmit(String query) {
               return false;
           }

           @Override
           public boolean onQueryTextChange(String newText) {

               crudList.clear();

               if(!TextUtils.isEmpty(newText)){
                   for(Dianzan item:crudListAll){
                       if(item.getName().contains(newText)){
                           crudList.add(item);
                       }
                   }
               }else{
                   crudList.addAll(crudListAll);
               }
               adapter.notifyDataSetChanged();

               return false;
           }
       });
    }

    @Override
        protected void onResume() {

        RequestParams params = new RequestParams();
        HttpUtil.post("DianzanServlet", params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(final int statusCode, Header[] headers, String responseString) {
                List<Dianzan> dates = GsonUtil.getInstance().fromJson(new JsonData(responseString).getData(), new TypeToken<List<Dianzan>>() {
                }.getType());

                crudList.clear();
				crudListAll.clear();
                if (dates == null) {
                    dates = new ArrayList<Dianzan>();
                }
                crudList.addAll(dates);
				crudListAll.addAll(dates);
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
                startActivity(new Intent(this_,DianzanAddActivity.class));
                break;
        }
    }
}

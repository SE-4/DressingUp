package com.example.androidtemplate;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.androidtemplate.Http.HttpUtil;
import com.example.androidtemplate.businmo.Tuijian;
import com.example.androidtemplate.common.BaseActivity;
import com.example.androidtemplate.common.Constants;
import com.example.androidtemplate.common.D;
import com.example.androidtemplate.common.T;
import com.example.androidtemplate.manager.ManagerComm;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TuijianAddActivity extends BaseActivity {

    @Bind(R.id.left_tv)
    TextView leftTv;
    @Bind(R.id.title_tv)
    TextView titleTv;
    @Bind(R.id.right_tv)
    TextView rightTv;
    @Bind(R.id.title_ll)
    LinearLayout titleLl;
    @Bind(R.id.name_et)
    EditText nameEt;
    @Bind(R.id.ok_btn)
    Button okBtn;
    @Bind(R.id.content_ll)
    LinearLayout contentLl;


    Tuijian item;
    @Bind(R.id.pic_add_tv)
    TextView picAddTv;
    @Bind(R.id.pic_add2_tv)
    TextView picAdd2Tv;
    @Bind(R.id.img_iv)
    ImageView imgIv;
    @Bind(R.id.msg_et)
    EditText msgEt;
    @Bind(R.id.category_sp)
    Spinner categorySp;

    @Override
    protected void initData() {
        setContentView(R.layout.activity_tuijian_add);
        ButterKnife.bind(this);
        item = (Tuijian) getIntent().getSerializableExtra("item");

        if (item != null) {
            // get the value
            nameEt.setText(item.getName());
            msgEt.setText(item.getMsg());

            titleTv.setText("change");
            okBtn.setText("change successfully");

        }

    }

    @Override
    protected void recycle() {

    }


    @OnClick({R.id.left_tv, R.id.right_tv, R.id.ok_btn, R.id.pic_add_tv, R.id.pic_add2_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.left_tv:
                finish();
                break;
            case R.id.right_tv:
                break;
            case R.id.ok_btn:

                if (photo == null) {
                    T.showToast(this_, "Please select a image");
                    return;
                }

                final String name = nameEt.getText().toString().trim();
                final String msg = msgEt.getText().toString().trim();


                File file = new File(Constants.ImageTempPath);
                RequestParams requestParams = new RequestParams();
                try {
                    requestParams.put("file", file);
                    HttpUtil.post("UploadServlet", requestParams, new TextHttpResponseHandler() {
                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

                        }

                        @Override
                        public void onSuccess(int statusCode, Header[] headers, String responseString) {
                            RequestParams params = new RequestParams();
                            if (item != null) {
                                params.add("action", "edit");
                                params.put("id", item.getId());
                            } else {
                                params.add("action", "add");
                            }

                            params.put("name", name);
                            params.put("msg", msg);
                            params.put("img", responseString);
                            params.put("category", categorySp.getSelectedItem().toString());
                            params.put("username", ManagerComm.loginUser.getUsername());

                            HttpUtil.post("TuijianServlet", params, new TextHttpResponseHandler() {
                                @Override
                                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                                    D.out(throwable);
                                }

                                @Override
                                public void onSuccess(int statusCode, Header[] headers, String responseString) {

                                    if (item != null) {
                                        T.showToast(this_, "change successfully");
                                    } else {
                                        T.showToast(this_, "Upload Successfully");
                                    }
                                    finish();
                                }
                            });
                        }
                    });


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                break;
            case R.id.pic_add_tv:
                Intent intent = new Intent(Intent.ACTION_PICK, null);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, Constants.IMAGE_UNSPECIFIED);
                startActivityForResult(intent, Constants.ALBUM_REQUEST_CODE);
                break;
            case R.id.pic_add2_tv:
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.
                        getExternalStorageDirectory(), "temp.jpg")));
                startActivityForResult(intent, Constants.CROP_REQUEST_CODE);
                break;
        }
    }


    Bitmap photo;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case Constants.ALBUM_REQUEST_CODE:
                if (data == null) {
                    return;
                }
                startCrop(data.getData());
                break;
            case Constants.CAMERA_REQUEST_CODE:
                File picture = new File(Constants.ImageTempPath);
                startCrop(Uri.fromFile(picture));
                break;
            case Constants.CROP_REQUEST_CODE:


                photo = getLoacalBitmap(Constants.ImageTempPath);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                photo.compress(Bitmap.CompressFormat.JPEG, 75, stream);// (0-100)压缩文件
                imgIv.setImageBitmap(photo);
                imgIv.setVisibility(View.VISIBLE);

                break;
            default:
                break;
        }
    }

    /**
     * 开始裁剪
     *
     * @param uri
     */
    private void startCrop(Uri uri) {
        //调用Android系统自带的一个图片剪裁页面,
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, Constants.IMAGE_UNSPECIFIED);
        intent.putExtra("crop", "true");//进行修剪
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 500);
        intent.putExtra("outputY", 500);
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.
                getExternalStorageDirectory(), "temp.jpg")));
        startActivityForResult(intent, Constants.CROP_REQUEST_CODE);
    }

    public static Bitmap getLoacalBitmap(String url) {
        try {
            FileInputStream fis = new FileInputStream(url);
            return BitmapFactory.decodeStream(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}

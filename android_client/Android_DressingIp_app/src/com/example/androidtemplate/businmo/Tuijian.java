package com.example.androidtemplate.businmo;


import java.io.Serializable;

public class Tuijian extends BaseModel implements Serializable {
    public String img;
    public String dianzan;
    public String category;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDianzan() {
        return dianzan;
    }

    public void setDianzan(String dianzan) {
        this.dianzan = dianzan;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}

package com.example.demo.bean.app;

import com.example.demo.entity.TbUser;

public class InfoResult {
    int code;
    TbUser tbUser;

    public InfoResult(int code) {
        this.code = code;
    }

    public InfoResult(int code, TbUser tbUser) {
        this.code = code;
        this.tbUser = tbUser;
    }

    public TbUser getTbUser() {
        return tbUser;
    }

    public void setTbUser(TbUser tbUser) {
        this.tbUser = tbUser;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}

package com.lang.myshop.module.sys.dto;

import java.io.Serializable;

public class LoginDTO implements Serializable{
    private String loginID;
    private String loginPwd;
    private String isRemember;
    private String validateCode;

    public LoginDTO() {
    }

    public LoginDTO(String loginID, String loginPwd, String isRemember, String validateCode) {
        this.loginID = loginID;
        this.loginPwd = loginPwd;
        this.isRemember = isRemember;
        this.validateCode = validateCode;
    }

    public String getLoginID() {
        return loginID;
    }

    public void setLoginID(String loginID) {
        this.loginID = loginID;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public String getIsRemember() {
        return isRemember;
    }

    public void setIsRemember(String isRemember) {
        this.isRemember = isRemember;
    }

    public String getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }

    @Override
    public String toString() {
        return "LoginDTO{" +
                "loginID='" + loginID + '\'' +
                ", loginPwd='" + loginPwd + '\'' +
                ", isRemember='" + isRemember + '\'' +
                ", validateCode='" + validateCode + '\'' +
                '}';
    }
}

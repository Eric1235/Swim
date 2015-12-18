package com.scnu.swimmingtrainingsystem.event;

/**
 * Created by lixinkun on 15/12/18.
 */
public class LoginSucceedEvent {
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public LoginSucceedEvent(String msg) {

        this.msg = msg;
    }
}

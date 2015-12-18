package com.scnu.swimmingtrainingsystem.event;

/**
 * Created by lixinkun on 15/12/18.
 */
public class FirstLoginSucceedEvent {
    private String msg;

    public FirstLoginSucceedEvent(String msg) {
        this.msg = msg;
    }

    public String getMsg() {

        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

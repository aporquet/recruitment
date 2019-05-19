package com.recruitment.response;

public abstract class BaseResponse {

    protected String status;
    protected String type;

    public void setStatus(String status) {
        this.status = status;
    }

    public void setType(String type) {
        this.type = type;
    }

    public abstract boolean work();

}

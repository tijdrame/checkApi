package com.check.api.request;

public class CheckApiRequest {
    
    private String  url;

    public CheckApiRequest() {
    }

    public CheckApiRequest(String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public CheckApiRequest url(String url) {
        this.url = url;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            ", url='" + getUrl() + "'" +
            "}";
    }

}